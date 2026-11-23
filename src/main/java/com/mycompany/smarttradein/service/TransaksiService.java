/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smarttradein.service;

import com.mycompany.smarttradein.exception.BarangTidakDitemukanException;
import com.mycompany.smarttradein.exception.StokTidakCukupException;
import com.mycompany.smarttradein.model.BarangElektronik;
import com.mycompany.smarttradein.model.Customer;
import com.mycompany.smarttradein.model.DetailTransaksi;
import com.mycompany.smarttradein.model.Transaksi;
import com.mycompany.smarttradein.repository.BarangRepository;
import com.mycompany.smarttradein.repository.TransaksiRepository;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author hasyanuramalinarahman
 */
public class TransaksiService {

    private BarangRepository barangRepo = new BarangRepository();
    private TransaksiRepository transaksiRepo = new TransaksiRepository();
    private PenilaianService penilaianService = new PenilaianService();

    // Beli 1 barang
    public Transaksi beli(Customer customer, int idBarang) throws SQLException {
        return beli(customer, Arrays.asList(idBarang));
    }

    // Beli banyak
    public Transaksi beli(Customer customer, List<Integer> daftarIdBarang) throws SQLException {
        if (customer == null) {
            throw new IllegalArgumentException("Customer belum login!");
        }
        if (daftarIdBarang == null || daftarIdBarang.isEmpty()) {
            throw new IllegalArgumentException("Keranjang masih kosong!");
        }

        Transaksi transaksi = new Transaksi(customer);

        for (int idBarang : daftarIdBarang) {
            BarangElektronik barang = barangRepo.cariById(idBarang);

            if (barang == null) {
                throw new BarangTidakDitemukanException(
                    "Barang dengan id " + idBarang + " tidak ditemukan!");
            }
            if (!barang.getStatus().equals("Tersedia")) {
                throw new StokTidakCukupException(
                    "Barang \"" + barang.getNama() + "\" sudah tidak tersedia!");
            }

            double harga = penilaianService.nilai(barang).getEstimasiHarga();
            transaksi.tambahDetail(new DetailTransaksi(barang, harga));
        }

        transaksiRepo.simpan(transaksi);
        return transaksi;
    }
}
