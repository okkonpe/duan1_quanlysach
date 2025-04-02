/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import Dao.ThongKeDao;
import Dao.ThongKeDaoILMP;
import ModelView.TongHoaDon;
import java.util.List;
import model.DoanhThu;
import ModelView.spbanchay;

/**
 *
 * @author phung
 */
public class ThongKeServiceIMPL implements ThongKeService{

    private ThongKeDao thongkedao = null;

    public ThongKeServiceIMPL() {
        thongkedao = new ThongKeDaoILMP();
    }
    
    
    
    @Override
    public List<DoanhThu> getlistbydoanhthu() {
       return thongkedao.getlistbydoanhthu();
    }

    @Override
    public List<spbanchay> getlistbyspbanchay() {
        return thongkedao.getlistbyspbanchay();
    }

    @Override
    public List<DoanhThu> getslistdoanhthutheongay(String ngaybatdau, String ngayketthuc) {
       return thongkedao.getslistdoanhthutheongay(ngaybatdau, ngayketthuc);
    }

    @Override
    public List<TongHoaDon> getslisttonghoadon() {
     return thongkedao.getslisttonghoadon();
    }

    @Override
    public List<TongHoaDon> getslisttonghoadontheongay(String ngaybatdau, String ngayketthuc) {
        return thongkedao.getslisttonghoadontheongay(ngaybatdau, ngayketthuc);
    }
    
}
