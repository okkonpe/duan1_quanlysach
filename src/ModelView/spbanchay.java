/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModelView;

import model.*;

/**
 *
 * @author phung
 */
public class spbanchay {
    private int soluong;
    
    private String tensp;

    public spbanchay() {
    }

    public spbanchay(int soluong, String tensp) {
        this.soluong = soluong;
        this.tensp = tensp;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }
    
    
}
