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
public class GiamGia {
    private String maGG;
    private String tenChuongTrinh;
    
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private int phanTramGG;
     private int tongTienApDung;
    private boolean trangThai;

    public GiamGia() {
    }

    public GiamGia(String maGG, String tenChuongTrinh, Date ngayBatDau, Date ngayKetThuc, int phanTramGG, int tongTienApDung, boolean trangThai) {
        this.maGG = maGG;
        this.tenChuongTrinh = tenChuongTrinh;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.phanTramGG = phanTramGG;
        this.tongTienApDung = tongTienApDung;
        this.trangThai = trangThai;
    }

    public int getTongTienApDung() {
        return tongTienApDung;
    }

    public void setTongTienApDung(int tongTienApDung) {
        this.tongTienApDung = tongTienApDung;
    }



    public String getMaGG() {
        return maGG;
    }

    public void setMaGG(String maGG) {
        this.maGG = maGG;
    }

    public String getTenChuongTrinh() {
        return tenChuongTrinh;
    }

    public void setTenChuongTrinh(String tenChuongTrinh) {
        this.tenChuongTrinh = tenChuongTrinh;
    }

   

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public int getPhanTramGG() {
        return phanTramGG;
    }

    public void setPhanTramGG(int phanTramGG) {
        this.phanTramGG = phanTramGG;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

   
    
}
