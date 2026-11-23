/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smarttradein.model;

/**
 *
 * @author hasyanuramalinarahman
 */
public class Tablet extends BarangElektronik {
    
    private int storage;          
    private double ukuranLayar;

    public Tablet(int id, String nama, String merek, double hargaAwal,
                  int skorKondisi, int storage, double ukuranLayar) {
        super(id, nama, merek, hargaAwal, skorKondisi);
        this.storage = storage;
        this.ukuranLayar = ukuranLayar;
    }

    @Override
    public double hitungHargaTradeIn() {
        // Kalau Tablet: 65% dari harga awal, dikali skor kondisi
        return getHargaAwal() * 0.65 * (getSkorKondisi() / 100.0);
    }

    @Override
    public String nilaiKondisi() {
        int skor = getSkorKondisi();
        if (skor >= 80) return "Sangat Baik";
        if (skor >= 60) return "Baik";
        return "Cukup";
    }

    @Override
    public char tentukanGrade() {
        int skor = getSkorKondisi();
        if (skor >= 80) return 'A';
        if (skor >= 60) return 'B';
        return 'C';
    }

    public int getStorage() { return storage; }
    public double getUkuranLayar() { return ukuranLayar; }
}
