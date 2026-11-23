/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smarttradein.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author hasyanuramalinarahman
 */
public class Transaksi {

    private int idTransaksi;
    private Customer customer;
    private LocalDateTime tanggal;
    private double totalHarga;
    private ArrayList<DetailTransaksi> daftarDetail = new ArrayList<>();

    public Transaksi(Customer customer) {
        this.customer = customer;
        this.tanggal = LocalDateTime.now();
    }

    // Menambah satu barang ke transaksi, terua total dihitung ulang
    public void tambahDetail(DetailTransaksi detail) {
        daftarDetail.add(detail);
        hitungTotal();
    }

    // Menjumlahkan harga semua detail
    public double hitungTotal() {
        double total = 0;
        for (DetailTransaksi d : daftarDetail) {
            total += d.getHarga();
        }
        this.totalHarga = total;
        return total;
    }

    public int getIdTransaksi() { return idTransaksi; }
    public void setIdTransaksi(int idTransaksi) { this.idTransaksi = idTransaksi; }

    public Customer getCustomer() { return customer; }
    public LocalDateTime getTanggal() { return tanggal; }
    public double getTotalHarga() { return totalHarga; }
    public ArrayList<DetailTransaksi> getDaftarDetail() { return daftarDetail; }
}
