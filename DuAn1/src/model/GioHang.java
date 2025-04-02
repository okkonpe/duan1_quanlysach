/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;

/**
 *
 * @author trinh thanh
 */
public class GioHang {

    private String maSach;
    private int soLuong;
    private BigDecimal giaSach;
    private String tenSach;
    private String theLoai;
    private String tacGia;
    private String nhaXuatBan;

    public GioHang() {
    }

    public GioHang(String maSach, int soLuong, BigDecimal giaSach, String tenSach, String theLoai, String tacGia, String nhaXuatBan) {
        this.maSach = maSach;
        this.soLuong = soLuong;
        this.giaSach = giaSach;
        this.tenSach = tenSach;
        this.theLoai = theLoai;
        this.tacGia = tacGia;
        this.nhaXuatBan = nhaXuatBan;
    }

    public BigDecimal getThanhTien() {
        // Calculate the total price as price * quantity
        return giaSach.multiply(BigDecimal.valueOf(soLuong));
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public BigDecimal getGiaSach() {
        return giaSach;
    }

    public void setGiaSach(BigDecimal giaSach) {
        this.giaSach = giaSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getNhaXuatBan() {
        return nhaXuatBan;
    }

    public void setNhaXuatBan(String nhaXuatBan) {
        this.nhaXuatBan = nhaXuatBan;
    }

    
}
