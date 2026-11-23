/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smarttradein.model;

/**
 *
 * @author hasyanuramalinarahman
 */
public class Laptop extends BarangElektronik {

    private String processor;
    private int kapasitasRAM;

    public Laptop(int id, String nama, String merek, double hargaAwal,
                  int skorKondisi, String processor, int kapasitasRAM) {
        super(id, nama, merek, hargaAwal, skorKondisi);
        this.processor = processor;
        this.kapasitasRAM = kapasitasRAM;
    }

    @Override
    public double hitungHargaTradeIn() {
        // Laptop biasanya lebih awet: 70% dari harga awal, dikali skor kondisi
        return getHargaAwal() * 0.70 * (getSkorKondisi() / 100.0);
    }

    @Override
    public String nilaiKondisi() {
        int skor = getSkorKondisi();
        if (skor >= 80) return "Sangat Baik";
        if (skor >= 55) return "Baik";
        return "Cukup";
    }

    @Override
    public char tentukanGrade() {
        int skor = getSkorKondisi();
        if (skor >= 80) return 'A';
        if (skor >= 55) return 'B';
        return 'C';
    }

    public String getProcessor() { return processor; }
    public int getKapasitasRAM() { return kapasitasRAM; }
}
