/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;


/**
 *
 * @author trinh thanh
 */
public class TaiKhoanChuyenManHinh {
    private JDialog daDialog;
    private JButton submit;
    private JTextField tenDangNhap;
    private JTextField matKhau;
    private JLabel jLabel;

    public TaiKhoanChuyenManHinh(JDialog daDialog, JButton submit, JTextField tenDangNhap, JTextField matKhau, JLabel jLabel) {
        this.daDialog = daDialog;
        this.submit = submit;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.jLabel = jLabel;
    }
    
    public void setEvent(){
        
    }
}
