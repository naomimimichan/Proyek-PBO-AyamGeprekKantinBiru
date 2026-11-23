/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smarttradein.repository;

import com.mycompany.smarttradein.database.DatabaseConnection;
import com.mycompany.smarttradein.exception.StokTidakCukupException;
import com.mycompany.smarttradein.model.DetailTransaksi;
import com.mycompany.smarttradein.model.Transaksi;
import java.sql.*;

/**
 *
 * @author hasyanuramalinarahman
 */
public class TransaksiRepository {
    
    public void simpan(Transaksi transaksi) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();

        String sqlTransaksi = "INSERT INTO transaksi (id_customer, tanggal, total_harga) VALUES (?, ?, ?)";
        String sqlDetail = "INSERT INTO detail_transaksi (id_transaksi, id_barang, harga) VALUES (?, ?, ?)";
        String sqlStatus = "UPDATE barang SET status = 'Terjual' "
                         + "WHERE id_barang = ? AND status = 'Tersedia'";
        try {
            conn.setAutoCommit(false);

            try (PreparedStatement pst = conn.prepareStatement(sqlTransaksi, Statement.RETURN_GENERATED_KEYS)) {
                pst.setInt(1, transaksi.getCustomer().getIdCustomer());
                pst.setTimestamp(2, Timestamp.valueOf(transaksi.getTanggal()));
                pst.setDouble(3, transaksi.getTotalHarga());
                pst.executeUpdate();

                try (ResultSet rs = pst.getGeneratedKeys()) {
                    if (rs.next()) {
                        transaksi.setIdTransaksi(rs.getInt(1));
                    }
                }
            }

            for (DetailTransaksi d : transaksi.getDaftarDetail()) {
                int idBarang = d.getBarang().getId();

                try (PreparedStatement pst = conn.prepareStatement(sqlStatus)) {
                    pst.setInt(1, idBarang);
                    int baris = pst.executeUpdate();
                    if (baris == 0) {
                        throw new StokTidakCukupException(
                            "Barang \"" + d.getBarang().getNama() + "\" sudah tidak tersedia!");
                    }
                }

                try (PreparedStatement pst = conn.prepareStatement(sqlDetail)) {
                    pst.setInt(1, transaksi.getIdTransaksi());
                    pst.setInt(2, idBarang);
                    pst.setDouble(3, d.getHarga());
                    pst.executeUpdate();
                }
                d.getBarang().prosesPenjualan();
            }

            conn.commit();
        } catch (SQLException | RuntimeException e) {
            conn.rollback(); 
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }
}
