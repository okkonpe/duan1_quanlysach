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
public class KhachHang {

    private String maKhachHang;
    private String tenKh;
    private String sĐt;
    private String eMail;
    private boolean gioiTinh;
    private Date ngaySinh;
    private String diaChi;
    

    public KhachHang() {
    }

    public KhachHang(String maKhachHang, String tenKh, String sĐt, String eMail, boolean gioiTinh, Date ngaySinh, String diaChi) {
        this.maKhachHang = maKhachHang;
        this.tenKh = tenKh;
        this.sĐt = sĐt;
        this.eMail = eMail;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
    }

    public KhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }
    

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getTenKh() {
        return tenKh;
    }

    public void setTenKh(String tenKh) {
        this.tenKh = tenKh;
    }

    public String getsĐt() {
        return sĐt;
    }

    public void setsĐt(String sĐt) {
        this.sĐt = sĐt;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

   

}
