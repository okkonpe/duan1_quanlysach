/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import ModelView.TongHoaDon;
import java.util.List;
import model.DoanhThu;
import ModelView.spbanchay;

/**
 *
 * @author phung
 */
public interface ThongKeService {
    public List<DoanhThu> getlistbydoanhthu();
    
      public List<spbanchay> getlistbyspbanchay();
      
     public List<TongHoaDon> getslisttonghoadon();
      
       public List<DoanhThu> getslistdoanhthutheongay(String ngaybatdau, String ngayketthuc);
       
         public List<TongHoaDon> getslisttonghoadontheongay(String ngaybatdau, String ngayketthuc);
}
