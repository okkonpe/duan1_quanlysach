/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import controller.TaiKhoanControolleerr;
import service.TaiKhoanSeicer;

/**
 *
 * @author trinh thanh
 */
public class luuThongTinDangNhap {

    private String tenTaiKhoan;
    private String maKhau;
    private String quyen;
    private String maNV;

    public luuThongTinDangNhap() {
    }

    public luuThongTinDangNhap(String tenTaiKhoan, String maKhau, String quyen, String maNV) {
        this.tenTaiKhoan = tenTaiKhoan;
        this.maKhau = maKhau;
        this.quyen = quyen;
        this.maNV = maNV;
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getMaKhau() {
        return maKhau;
    }

    public void setMaKhau(String maKhau) {
        this.maKhau = maKhau;
    }

    public String getQuyen() {
        return quyen;
    }

    public void setQuyen(String quyen) {
        this.quyen = quyen;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }



    
}
