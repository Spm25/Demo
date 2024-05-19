package com.example.demo;

import java.io.Serializable;

public class Taxi_MaDe implements Serializable {
    private int id;
    private String soXe;
    private double quangDuong;
    private double donGia;
    private int khuyenMai;

    public Taxi_MaDe(int id, String soXe, double quangDuong, double donGia, int khuyenMai) {
        this.id = id;
        this.soXe = soXe;
        this.quangDuong = quangDuong;
        this.donGia = donGia;
        this.khuyenMai = khuyenMai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSoXe() {
        return soXe;
    }

    public void setSoXe(String soXe) {
        this.soXe = soXe;
    }

    public double getQuangDuong() {
        return quangDuong;
    }

    public void setQuangDuong(double quangDuong) {
        this.quangDuong = quangDuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public int getKhuyenMai() {
        return khuyenMai;
    }

    public void setKhuyenMai(int khuyenMai) {
        this.khuyenMai = khuyenMai;
    }
}
