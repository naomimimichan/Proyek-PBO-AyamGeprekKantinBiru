/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smarttradein.view;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.mycompany.smarttradein.model.BarangElektronik;
import com.mycompany.smarttradein.model.Laptop;
import com.mycompany.smarttradein.model.Smartphone;
import com.mycompany.smarttradein.model.Tablet;

/**
 *
 * @author hasyanuramalinarahman
 */
public class Format {

    public static final DateTimeFormatter TANGGAL = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public static String rupiah(double nilai) {
        return String.format(Locale.forLanguageTag("id-ID"), "Rp %,.0f", nilai);
    }

    public static String jenis(BarangElektronik b) {
        if (b instanceof Smartphone) return "Smartphone";
        if (b instanceof Laptop) return "Laptop";
        return "Tablet";
    }

    public static String spesifikasi(BarangElektronik b) {
        if (b instanceof Smartphone) {
            Smartphone s = (Smartphone) b;
            return "RAM " + s.getKapasitasRAM() + " GB, " + s.getStorage() + " GB";
        } else if (b instanceof Laptop) {
            Laptop l = (Laptop) b;
            return l.getProcessor() + ", RAM " + l.getKapasitasRAM() + " GB";
        } else {
            Tablet t = (Tablet) b;
            return t.getStorage() + " GB, " + t.getUkuranLayar() + " inci";
        }
    }
}
