/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smarttradein.repository;

import com.mycompany.smarttradein.database.DatabaseConnection;
import com.mycompany.smarttradein.model.*;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author hasyanuramalinarahman
 */
public class BarangRepository {

    private static final String SELECT_DASAR =
        "SELECT b.id_barang, b.jenis, b.nama, b.merek, b.harga_awal, " +
        "b.spesifikasi, b.status, p.skor_kondisi, p.grade, p.estimasi_harga " +
        "FROM barang b JOIN penilaian p ON b.id_barang = p.id_barang ";

    // Create
    public void simpan(BarangElektronik barang, Penilaian penilaian) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String sqlBarang = "INSERT INTO barang (jenis, nama, merek, harga_awal, spesifikasi, status) "
                         + "VALUES (?, ?, ?, ?, ?, ?)";
        String sqlNilai = "INSERT INTO penilaian (id_barang, skor_kondisi, grade, estimasi_harga) "
                        + "VALUES (?, ?, ?, ?)";
        try {
            conn.setAutoCommit(false);

            try (PreparedStatement pst = conn.prepareStatement(sqlBarang, Statement.RETURN_GENERATED_KEYS)) {
                pst.setString(1, jenisDari(barang));
                pst.setString(2, barang.getNama());
                pst.setString(3, barang.getMerek());
                pst.setDouble(4, barang.getHargaAwal());
                pst.setString(5, spesifikasiDari(barang));
                pst.setString(6, barang.getStatus());
                pst.executeUpdate();

                try (ResultSet rs = pst.getGeneratedKeys()) {
                    if (rs.next()) {
                        barang.setId(rs.getInt(1));
                    }
                }
            }

            penilaian.setIdBarang(barang.getId());
            try (PreparedStatement pst = conn.prepareStatement(sqlNilai)) {
                pst.setInt(1, penilaian.getIdBarang());
                pst.setInt(2, penilaian.getSkorKondisi());
                pst.setString(3, String.valueOf(penilaian.getGrade()));
                pst.setDouble(4, penilaian.getEstimasiHarga());
                pst.executeUpdate();
            }

            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    // Read
    public ArrayList<BarangElektronik> cariSemuaTersedia() throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = SELECT_DASAR + "WHERE b.status = 'Tersedia' ORDER BY b.nama";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            return bacaDaftar(pst);
        }
    }

    public ArrayList<BarangElektronik> cariByNama(String nama) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = SELECT_DASAR
                   + "WHERE b.status = 'Tersedia' AND b.nama LIKE ? ORDER BY b.nama";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, "%" + nama + "%");
            return bacaDaftar(pst);
        }
    }

    public BarangElektronik cariById(int idBarang) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = SELECT_DASAR + "WHERE b.id_barang = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idBarang);
            ArrayList<BarangElektronik> hasil = bacaDaftar(pst);
            return hasil.isEmpty() ? null : hasil.get(0);
        }
    }

    // Update
    public void update(BarangElektronik barang, Penilaian penilaian) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String sqlBarang = "UPDATE barang SET nama = ?, merek = ?, harga_awal = ?, spesifikasi = ? "
                         + "WHERE id_barang = ?";
        String sqlNilai = "UPDATE penilaian SET skor_kondisi = ?, grade = ?, estimasi_harga = ? "
                        + "WHERE id_barang = ?";
        try {
            conn.setAutoCommit(false);
            try (PreparedStatement pst = conn.prepareStatement(sqlBarang)) {
                pst.setString(1, barang.getNama());
                pst.setString(2, barang.getMerek());
                pst.setDouble(3, barang.getHargaAwal());
                pst.setString(4, spesifikasiDari(barang));
                pst.setInt(5, barang.getId());
                pst.executeUpdate();
            }
            try (PreparedStatement pst = conn.prepareStatement(sqlNilai)) {
                pst.setInt(1, penilaian.getSkorKondisi());
                pst.setString(2, String.valueOf(penilaian.getGrade()));
                pst.setDouble(3, penilaian.getEstimasiHarga());
                pst.setInt(4, barang.getId());
                pst.executeUpdate();
            }
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    // Delete
    public void hapus(int idBarang) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        try {
            conn.setAutoCommit(false);
            try (PreparedStatement pst = conn.prepareStatement("DELETE FROM penilaian WHERE id_barang = ?")) {
                pst.setInt(1, idBarang);
                pst.executeUpdate();
            }
            try (PreparedStatement pst = conn.prepareStatement("DELETE FROM barang WHERE id_barang = ?")) {
                pst.setInt(1, idBarang);
                pst.executeUpdate();
            }
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    private ArrayList<BarangElektronik> bacaDaftar(PreparedStatement pst) throws SQLException {
        ArrayList<BarangElektronik> daftar = new ArrayList<>();
        try (ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                daftar.add(dariResultSet(rs));
            }
        }
        return daftar;
    }

    private BarangElektronik dariResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id_barang");
        String jenis = rs.getString("jenis");
        String nama = rs.getString("nama");
        String merek = rs.getString("merek");
        double harga = rs.getDouble("harga_awal");
        int skor = rs.getInt("skor_kondisi");
        String[] spek = rs.getString("spesifikasi").split("\\|");

        BarangElektronik barang;
        if (jenis.equals("Smartphone")) {
            barang = new Smartphone(id, nama, merek, harga, skor,
                                    Integer.parseInt(spek[0]), Integer.parseInt(spek[1]));
        } else if (jenis.equals("Laptop")) {
            barang = new Laptop(id, nama, merek, harga, skor,
                                spek[0], Integer.parseInt(spek[1]));
        } else if (jenis.equals("Tablet")) {
            barang = new Tablet(id, nama, merek, harga, skor,
                                Integer.parseInt(spek[0]), Double.parseDouble(spek[1]));
        } else {
            throw new IllegalStateException("Jenis barang tidak dikenal: " + jenis);
        }
        barang.setStatus(rs.getString("status"));
        return barang;
    }

    private String jenisDari(BarangElektronik barang) {
        if (barang instanceof Smartphone) return "Smartphone";
        if (barang instanceof Laptop) return "Laptop";
        return "Tablet";
    }

    private String spesifikasiDari(BarangElektronik barang) {
        if (barang instanceof Smartphone) {
            Smartphone s = (Smartphone) barang;
            return s.getKapasitasRAM() + "|" + s.getStorage();
        } else if (barang instanceof Laptop) {
            Laptop l = (Laptop) barang;
            return l.getProcessor() + "|" + l.getKapasitasRAM();
        } else {
            Tablet t = (Tablet) barang;
            return t.getStorage() + "|" + t.getUkuranLayar();
        }
    }
}
