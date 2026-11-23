/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smarttradein.service;

import com.mycompany.smarttradein.exception.BarangTidakDitemukanException;
import com.mycompany.smarttradein.model.BarangElektronik;
import com.mycompany.smarttradein.model.Penilaian;
import com.mycompany.smarttradein.repository.BarangRepository;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author hasyanuramalinarahman
 */
public class BarangService {
    
    private BarangRepository barangRepo = new BarangRepository();
    private PenilaianService penilaianService = new PenilaianService();

    // Create
    public void tambahBarang(BarangElektronik barang) throws SQLException {
        validasiTeks(barang);
        Penilaian penilaian = penilaianService.nilai(barang);  // hitung grade + estimasi
        barangRepo.simpan(barang, penilaian);
    }

    // Read
    public ArrayList<BarangElektronik> daftarKatalog() throws SQLException {
        return barangRepo.cariSemuaTersedia();
    }

    public BarangElektronik ambilById(int idBarang) throws SQLException {
        BarangElektronik barang = barangRepo.cariById(idBarang);
        if (barang == null) {
            throw new BarangTidakDitemukanException(
                "Barang dengan id " + idBarang + " tidak ditemukan!");
        }
        return barang;
    }

    // Update
    public void ubahBarang(BarangElektronik barang) throws SQLException {
        BarangElektronik lama = ambilById(barang.getId());   // pastikan barangnya ada
        if (lama.getStatus().equals("Terjual")) {
            throw new IllegalStateException("Barang yang sudah terjual tidak dapat diubah!");
        }
        validasiTeks(barang);
        Penilaian penilaian = penilaianService.nilai(barang);  // nilai ulang
        barangRepo.update(barang, penilaian);
    }

    // Delete
    public void hapusBarang(int idBarang) throws SQLException {
        BarangElektronik barang = ambilById(idBarang);
        if (barang.getStatus().equals("Terjual")) {
            throw new IllegalStateException("Barang yang sudah terjual tidak dapat dihapus!");
        }
        barangRepo.hapus(idBarang);
    }

    // Cari berdasarkan nama
    public ArrayList<BarangElektronik> cariBarang(String nama) throws SQLException {
        return barangRepo.cariByNama(nama);
    }

    // Pengurutan berdasarkan nama
    public void urutkanByNama(ArrayList<BarangElektronik> daftar, boolean naik) {
        daftar.sort((a, b) -> naik
                ? a.getNama().compareToIgnoreCase(b.getNama())
                : b.getNama().compareToIgnoreCase(a.getNama()));
    }

    private void validasiTeks(BarangElektronik barang) {
        if (barang.getNama() == null || barang.getNama().trim().isEmpty()) {
            throw new IllegalArgumentException("Nama barang tidak boleh kosong!");
        }
        if (barang.getMerek() == null || barang.getMerek().trim().isEmpty()) {
            throw new IllegalArgumentException("Merek barang tidak boleh kosong!");
        }
    }
}
