/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smarttradein.service;

import com.mycompany.smarttradein.model.BarangElektronik;
import com.mycompany.smarttradein.model.Penilaian;

/**
 *
 * @author hasyanuramalinarahman
 */
public class PenilaianService {

    public Penilaian nilai(BarangElektronik barang) {
        int skor = barang.getSkorKondisi();
        char grade = barang.tentukanGrade();            
        double estimasi = barang.hitungHargaTradeIn();  

        // dibulatkan jadi 2 angka desimal
        estimasi = Math.round(estimasi * 100.0) / 100.0;

        Penilaian p = new Penilaian(skor, grade, estimasi);
        p.setIdBarang(barang.getId());
        return p;
    }
}
