/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author trinh thanh
 */
public class ChiTietGiamGia {
    private String maCTGG;
    private String maGG;
    private String maSach;
    private int phanTramGG;

    public ChiTietGiamGia() {
    }

    public ChiTietGiamGia(String maCTGG, String maGG, String maSach, int phanTramGG) {
        this.maCTGG = maCTGG;
        this.maGG = maGG;
        this.maSach = maSach;
        this.phanTramGG = phanTramGG;
    }

    public String getMaCTGG() {
        return maCTGG;
    }

    public void setMaCTGG(String maCTGG) {
        this.maCTGG = maCTGG;
    }

    public String getMaGG() {
        return maGG;
    }

    public void setMaGG(String maGG) {
        this.maGG = maGG;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public int getPhanTramGG() {
        return phanTramGG;
    }

    public void setPhanTramGG(int phanTramGG) {
        this.phanTramGG = phanTramGG;
    }

    
    
}
