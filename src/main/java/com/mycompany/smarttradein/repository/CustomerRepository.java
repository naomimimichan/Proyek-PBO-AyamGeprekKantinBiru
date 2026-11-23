/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smarttradein.repository;

import com.mycompany.smarttradein.database.DatabaseConnection;
import com.mycompany.smarttradein.exception.EmailSudahTerdaftarException;
import com.mycompany.smarttradein.model.Customer;
import java.sql.*;

/**
 *
 * @author hasyanuramalinarahman
 */
public class CustomerRepository {

    public void simpan(Customer customer) throws SQLException {
        if (cariByEmail(customer.getEmail()) != null) {
            throw new EmailSudahTerdaftarException(
                "Email " + customer.getEmail() + " sudah terdaftar!");
        }

        Connection conn = DatabaseConnection.getConnection();
        String sql = "INSERT INTO customers (nama, email, password) VALUES (?, ?, ?)";
        try (PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, customer.getNama());
            pst.setString(2, customer.getEmail());
            pst.setString(3, customer.getPassword());
            pst.executeUpdate();

            try (ResultSet rs = pst.getGeneratedKeys()) {
                if (rs.next()) {
                    customer.setIdCustomer(rs.getInt(1));
                }
            }
        }
    }

    public Customer cariByEmail(String email) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "SELECT id_customer, nama, email, password FROM customers WHERE email = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, email);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return new Customer(rs.getInt("id_customer"), rs.getString("nama"),
                                        rs.getString("email"), rs.getString("password"));
                }
            }
        }
        return null;
    }

    public Customer login(String email, String password) throws SQLException {
        Customer c = cariByEmail(email);
        if (c != null && c.getPassword().equals(password)) {
            return c;
        }
        return null;
    }
}
