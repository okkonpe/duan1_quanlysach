/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author phung
 */
public class DoanhThu {
    private String ngaygiaodich;
    
    private Double tongtien;

    public DoanhThu() {
    }

    public DoanhThu(String ngaygiaodich, Double tongtien) {
        this.ngaygiaodich = ngaygiaodich;
        this.tongtien = tongtien;
    }

    public String getNgaygiaodich() {
        return ngaygiaodich;
    }

    public void setNgaygiaodich(String ngaygiaodich) {
        this.ngaygiaodich = ngaygiaodich;
    }

    public Double getTongtien() {
        return tongtien;
    }

    public void setTongtien(Double tongtien) {
        this.tongtien = tongtien;
    }
    
    
}
