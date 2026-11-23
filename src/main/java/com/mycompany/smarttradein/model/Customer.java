/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smarttradein.model;

/**
 *
 * @author hasyanuramalinarahman
 */
public class Customer {

    private int idCustomer;
    private String nama;
    private String email;
    private String password;

    // Constructor 1: buat customer baru
    public Customer(String nama, String email, String password) {
        this.nama = nama;
        this.email = email;
        this.password = password;
    }

    // Constructor 2 (overloading): customer yang sudah ada
    public Customer(int idCustomer, String nama, String email, String password) {
        this(nama, email, password);
        this.idCustomer = idCustomer;
    }

    public int getIdCustomer() { return idCustomer; }
    public void setIdCustomer(int idCustomer) { this.idCustomer = idCustomer; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
