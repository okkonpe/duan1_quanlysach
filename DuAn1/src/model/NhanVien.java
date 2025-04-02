/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author trinh thanh
 */
public class NhanVien {

    private String maNhanVien;
    private String tenTaiKhoan;
    private String matKhau;
    private String quyen;
    private String ten;
    private boolean gioiTinh;
    private String diaChi;
    private String sđt;
    private boolean trangThai;

    public NhanVien() {

    }

    public NhanVien(String maNhanVien, String tenTaiKhoan, String matKhau, String quyen, String ten, boolean gioiTinh, String diaChi, String sđt, boolean trangThai) {
        this.maNhanVien = maNhanVien;
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
        this.quyen = quyen;
        this.ten = ten;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.sđt = sđt;
        this.trangThai = trangThai;
    }

    public NhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }
    

    public NhanVien(String maNhanVien, String ten) {
        this.maNhanVien = maNhanVien;
        this.ten = ten;
    }

    public NhanVien(String maNhanVien, String tenTaiKhoan, String matKhau, String ten) {
        this.maNhanVien = maNhanVien;
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
        this.ten = ten;
    }
    
    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getQuyen() {
        return quyen;
    }

    public void setQuyen(String quyen) {
        this.quyen = quyen;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSđt() {
        return sđt;
    }

    public void setSđt(String sđt) {
        this.sđt = sđt;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

}
