/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smarttradein.model;

import com.mycompany.smarttradein.interfaces.DapatDinilai;
import com.mycompany.smarttradein.interfaces.DapatDijual;
import com.mycompany.smarttradein.exception.DataTidakValidException;

/**
 *
 * @author hasyanuramalinarahman
 */
public abstract class BarangElektronik implements DapatDinilai, DapatDijual {

    // Encapsulation
    private int id;
    private String nama;
    private String merek;
    private double hargaAwal;
    private int skorKondisi;
    private String status = "Tersedia";

    // Constructor
    public BarangElektronik(int id, String nama, String merek, double hargaAwal, int skorKondisi) {
        this.id = id;
        this.nama = nama;
        this.merek = merek;
        setHargaAwal(hargaAwal);       
        setSkorKondisi(skorKondisi);
    }

    // Method
    public abstract double hitungHargaTradeIn();

    @Override
    public void prosesPenjualan() {
        this.status = "Terjual";
    }

    // Getter dan setter
    public int getId() { 
        return id; 
    }
    
    public void setId(int id) { 
        this.id = id; 
    }

    public String getNama() { 
        return nama; 
    }
    
    public void setNama(String nama) { 
        this.nama = nama; 
    }

    public String getMerek() { 
        return merek; 
    }
    
    public void setMerek(String merek) { 
        this.merek = merek; 
    }

    public double getHargaAwal() { 
        return hargaAwal; 
    }
    
    public void setHargaAwal(double hargaAwal) {
        if (hargaAwal <= 0) {
            throw new DataTidakValidException("Harga harus lebih dari 0!");
        }
        this.hargaAwal = hargaAwal;
    }


    public int getSkorKondisi() { 
        return skorKondisi; 
    }
    
    public void setSkorKondisi(int skorKondisi) {
        if (skorKondisi < 1 || skorKondisi > 100) {
            throw new DataTidakValidException("Skor kondisi harus 1-100!");
        }
        this.skorKondisi = skorKondisi;
    }
    
    public String getStatus() { 
        return status; 
    }
    
    public void setStatus(String status) { 
        this.status = status; 
    }
}
