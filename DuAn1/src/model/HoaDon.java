/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author trinh thanh
 */
public class HoaDon {

    private String maHoaDon;
    private String khachHang;
    private String maNV;
    private String tenNV;
    private String maGG;
    private LocalDate ngayLap;
    private int tongTien;
    private int giamGia;
    private LocalDate ngaySua;
    private String trangThai;

    // Default constructor
    public HoaDon() {
    }

  

  
   

//    @Override
//    public String toString() {
//        return "HoaDon{"
//                + "maHoaDon='" + maHoaDon + '\''
//                + ", khachHang=" + khachHang
//                + ", manhanVien=" + maNV
//                + ", maGG='" + maGG + '\''
//                + ", ngayLap=" + ngayLap
//                + ", tongTien=" + tongTien
//                + ", giamGia=" + giamGia
//              
//                + ", trangThai=" + trangThai
//                + '}';
//    }

    public HoaDon(String maHoaDon, String khachHang, String maNV, String tenNV, String maGG, LocalDate ngayLap, int tongTien, int giamGia, LocalDate ngaySua, String trangThai) {
        this.maHoaDon = maHoaDon;
        this.khachHang = khachHang;
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.maGG = maGG;
        this.ngayLap = ngayLap;
        this.tongTien = tongTien;
        this.giamGia = giamGia;
        this.ngaySua = ngaySua;
        this.trangThai = trangThai;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(String khachHang) {
        this.khachHang = khachHang;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getMaGG() {
        return maGG;
    }

    public void setMaGG(String maGG) {
        this.maGG = maGG;
    }

    public LocalDate getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(LocalDate ngayLap) {
        this.ngayLap = ngayLap;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public int getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(int giamGia) {
        this.giamGia = giamGia;
    }

    public LocalDate getNgaySua() {
        return ngaySua;
    }

    public void setNgaySua(LocalDate ngaySua) {
        this.ngaySua = ngaySua;
    }

    public String isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }


    
}
