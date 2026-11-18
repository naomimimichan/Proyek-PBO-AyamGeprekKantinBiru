# Proyek-PBO-AyamGeprekKantinBiru
# SmartTrade-In

### Aplikasi Desktop Jual Beli dan Tukar Tambah Barang Elektronik Bekas

## Deskripsi Proyek
SmartTrade-In merupakan aplikasi desktop berbasis Java yang dirancang untuk memfasilitasi proses jual beli dan tukar tambah barang elektronik bekas, seperti smartphone, laptop, dan tablet.

Aplikasi ini menyediakan fitur penilaian kondisi barang, estimasi harga otomatis, katalog produk, pencarian barang, serta pencatatan transaksi.

## Tujuan
- Mempermudah proses jual beli barang elektronik bekas.
- Memberikan estimasi harga berdasarkan skor kondisi barang.
- Menyediakan katalog barang yang terorganisasi dan mudah dicari.
- Menerapkan konsep Pemrograman Berorientasi Objek (PBO) menggunakan Java.

## Fitur Utama
- **Login dan Registrasi:** Autentikasi pengguna sebelum masuk aplikasi.
- **CRUD Data Barang:** Menambah, melihat, mengubah, dan menghapus barang.
- **Penilaian Kondisi:** Memberikan skor kondisi 1–100 dan menentukan grade A, B, atau C.
- **Estimasi Harga:** Menghitung estimasi harga secara otomatis.
- **Katalog Produk:** Menampilkan barang elektronik yang tersedia.
- **Pencarian Barang:** Mencari dan mengurutkan barang berdasarkan nama.
- **Transaksi Pembelian:** Melakukan pembelian serta memperbarui status barang.

## Teknologi yang Digunakan
- Java
- Java Swing (GUI)
- MySQL
- JDBC (Java Database Connectivity)
- NetBeans
- UML Class Diagram

## Database
Aplikasi menggunakan MySQL dengan lima tabel utama:
1. `customers`
2. `barang`
3. `penilaian`
4. `transaksi`
5. `detail_transaksi`

## Konsep Pemrograman Berorientasi Objek
Implementasi konsep PBO meliputi:
- Class dan Object
- Constructor
- Encapsulation
- Inheritance
- Overriding
- Overloading
- Abstract Class
- Interface
- Polymorphism
- Exception Handling

## Struktur Package
- `model`
- `interface`
- `service`
- `repository`
- `exception`
- `view`
- `database`
- `main`

## Anggota dan Pembagian Tugas

**Anggota 1**
- Membuat database MySQL dan koneksi JDBC.
- Membuat class model barang.
- Membuat interface DapatDinilai dan DapatDijual.
- Membuat PenilaianService.
- Membuat repository.
- Membuat exception.
- Mengimplementasikan overloading dan validasi data.
- Pengujian backend dan penyusunan laporan.

**Anggota 2**
- Membuat class Customer, Transaksi, DetailTransaksi, dan Penilaian.
- Membuat BarangService dan TransaksiService.
- Membuat LoginFrame dan MainFrame.
- Membuat KatalogFrame dan PenjualFrame.
- Membuat TransaksiFrame.
- Menangani exception pada GUI.
- Pengujian GUI dan persiapan presentasi.

## Link Dokumen
[Rancangan Proyek SmartTrade-In](https://docs.google.com/document/d/16TTRXHoFHrtq0K0nEvOLQpvaUg_U1dZJ7xPD3ebyu4w/edit?usp=sharing)
