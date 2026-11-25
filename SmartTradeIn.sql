CREATE DATABASE smarttradein;
USE smarttradein;

CREATE TABLE customers (
    id_customer INT PRIMARY KEY AUTO_INCREMENT,
    nama        VARCHAR(100) NOT NULL,
    email       VARCHAR(100) NOT NULL UNIQUE,
    password    VARCHAR(255) NOT NULL
);

CREATE TABLE barang (
    id_barang   INT PRIMARY KEY AUTO_INCREMENT,
    jenis       VARCHAR(20) NOT NULL,       
    nama        VARCHAR(100) NOT NULL,
    merek       VARCHAR(50) NOT NULL,
    harga_awal  DECIMAL(12,2) NOT NULL,
    spesifikasi VARCHAR(255),
    status      VARCHAR(20) DEFAULT 'Tersedia' 
);

CREATE TABLE penilaian (
    id_penilaian   INT PRIMARY KEY AUTO_INCREMENT,
    id_barang      INT NOT NULL UNIQUE,     
    skor_kondisi   INT NOT NULL,
    grade          CHAR(1) NOT NULL,
    estimasi_harga DECIMAL(12,2) NOT NULL,
    FOREIGN KEY (id_barang) REFERENCES barang(id_barang)
);

CREATE TABLE transaksi (
    id_transaksi INT PRIMARY KEY AUTO_INCREMENT,
    id_customer  INT NOT NULL,
    tanggal      DATETIME NOT NULL,
    total_harga  DECIMAL(12,2) NOT NULL,
    FOREIGN KEY (id_customer) REFERENCES customers(id_customer)
);

CREATE TABLE detail_transaksi (
    id_detail    INT PRIMARY KEY AUTO_INCREMENT,
    id_transaksi INT NOT NULL,
    id_barang    INT NOT NULL,
    harga        DECIMAL(12,2) NOT NULL,
    FOREIGN KEY (id_transaksi) REFERENCES transaksi(id_transaksi),
    FOREIGN KEY (id_barang) REFERENCES barang(id_barang)
);