/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smarttradein.model;

/**
 *
 * @author hasyanuramalinarahman
 */
public class Penilaian {
   
    private int idBarang;        
    private int skorKondisi;
    private char grade;
    private double estimasiHarga;

    public Penilaian(int skorKondisi, char grade, double estimasiHarga) {
        this.skorKondisi = skorKondisi;
        this.grade = grade;
        this.estimasiHarga = estimasiHarga;
    }

    public int getIdBarang() { return idBarang; }
    public void setIdBarang(int idBarang) { this.idBarang = idBarang; }

    public int getSkorKondisi() { return skorKondisi; }
    public void setSkorKondisi(int skorKondisi) { this.skorKondisi = skorKondisi; }

    public char getGrade() { return grade; }
    public void setGrade(char grade) { this.grade = grade; }

    public double getEstimasiHarga() { return estimasiHarga; }
    public void setEstimasiHarga(double estimasiHarga) { this.estimasiHarga = estimasiHarga; }
}
