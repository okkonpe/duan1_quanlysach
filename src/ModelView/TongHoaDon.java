/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModelView;

/**
 *
 * @author phung
 */
public class TongHoaDon {
    private int soluong;
    private String Ngay;

    public TongHoaDon() {
    }

    public TongHoaDon(int soluong, String Ngay) {
        this.soluong = soluong;
        this.Ngay = Ngay;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getNgay() {
        return Ngay;
    }

    public void setNgay(String Ngay) {
        this.Ngay = Ngay;
    }
    
    
}
