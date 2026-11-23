/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smarttradein.model;

/**
 *
 * @author hasyanuramalinarahman
 */
public class Smartphone extends BarangElektronik {

    private int kapasitasRAM;  
    private int storage;      

    public Smartphone(int id, String nama, String merek, double hargaAwal,
                      int skorKondisi, int kapasitasRAM, int storage) {
        super(id, nama, merek, hargaAwal, skorKondisi);  
        this.kapasitasRAM = kapasitasRAM;
        this.storage = storage;
    }

    @Override
    public double hitungHargaTradeIn() {
        // Smartphone biasanya cepet turun harga: 60% dari harga awal, dikali skor kondisi
        return getHargaAwal() * 0.60 * (getSkorKondisi() / 100.0);
    }

    @Override
    public String nilaiKondisi() {
        int skor = getSkorKondisi();
        if (skor >= 85) return "Sangat Baik";
        if (skor >= 60) return "Baik";
        return "Cukup";
    }

    @Override
    public char tentukanGrade() {
        int skor = getSkorKondisi();
        if (skor >= 85) return 'A';
        if (skor >= 60) return 'B';
        return 'C';
    }

    public int getKapasitasRAM() { return kapasitasRAM; }
    public int getStorage() { return storage; }
}
