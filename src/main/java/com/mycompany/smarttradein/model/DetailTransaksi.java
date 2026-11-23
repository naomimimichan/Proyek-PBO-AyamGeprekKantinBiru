/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smarttradein.model;

/**
 *
 * @author hasyanuramalinarahman
 */
public class DetailTransaksi {
    
    private int idDetail;
    private BarangElektronik barang;   
    private double harga;

    public DetailTransaksi(BarangElektronik barang, double harga) {
        this.barang = barang;
        this.harga = harga;
    }

    public int getIdDetail() { return idDetail; }
    public void setIdDetail(int idDetail) { this.idDetail = idDetail; }

    public BarangElektronik getBarang() { return barang; }
    public void setBarang(BarangElektronik barang) { this.barang = barang; }

    public double getHarga() { return harga; }
    public void setHarga(double harga) { this.harga = harga; }
}
