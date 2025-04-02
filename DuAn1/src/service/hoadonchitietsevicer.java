/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import model.HoaDonChiTiet;
import java.sql.*;
import java.time.LocalDate;
import model.DBConnect;

/**
 *
 * @author trinh thanh
 */
public class hoadonchitietsevicer {

    public ArrayList<HoaDonChiTiet> getAll() {
        ArrayList<HoaDonChiTiet> arr = new ArrayList<>();
        Connection con = DBConnect.getConnection();
        String sql = "SELECT [MaChiTietHD]\n"
                + "      ,[MaHoaDon]\n"
                + "      ,[MaSach]\n"
                + "      ,[SoLuong]\n"
                + "      ,[DonGia]\n"
                + "      ,[ThanhTien]\n"
                + "      ,[TrangThai]\n"
                + "  FROM [dbo].[ChiTietHoaDon]";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                HoaDonChiTiet hdc = new HoaDonChiTiet();
               
                hdc.setMaHoaDon(rs.getString("MaHoaDon"));
                hdc.setMaSach(rs.getString("MaSach"));
                hdc.setSoLuong(rs.getInt("SoLuong"));
                hdc.setDonGia(rs.getInt("DonGia"));
                hdc.setThanhTien(rs.getInt("ThanhTien"));
                hdc.setTrangThai(rs.getBoolean("TrangThai"));
                arr.add(hdc);
            }
        } catch (Exception e) {
        }
        return arr;
    }

    public ArrayList<HoaDonChiTiet> getTheoMaHD(String maHD) {
        ArrayList<HoaDonChiTiet> arr = new ArrayList<>();
        Connection con = DBConnect.getConnection();
        String sql = "SELECT h.MaHoaDon,h.MaSach,h.soLuong,h.donGia,h.thanhTien,h.trangThai,s.tensach \n"
                + "from ChiTietHoaDon h inner join sach s on h.masach=s.masach where h.MaHoaDon=?";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setObject(1, maHD);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                HoaDonChiTiet hdct = new HoaDonChiTiet();
                hdct.setMaHoaDon(rs.getString("MaHoaDon"));
                hdct.setMaSach(rs.getString("MaSach"));
                hdct.setTenSach(rs.getString("TenSach"));
                hdct.setSoLuong(rs.getInt("soLuong"));
                hdct.setDonGia(rs.getDouble("donGia"));
                hdct.setThanhTien(rs.getDouble("thanhTien"));
                hdct.setTrangThai(rs.getBoolean("trangThai"));

                arr.add(hdct);
            }
        } catch (Exception e) {
        }
        return arr;
    }

    public void add(HoaDonChiTiet hdct) {
        Connection con = DBConnect.getConnection();
            String sql = "INSERT INTO [dbo].[ChiTietHoaDon]\n"
                + "           ([MaHoaDon]\n"
                + "           ,[MaSach]\n"
                + "           ,[SoLuong]\n"
                + "           ,[DonGia]\n"
                + "           ,[ThanhTien]\n"               
                + "           ,[NgayTao]\n"
                + "           ,[NgaySua]\n"
                + "           ,[TrangThai])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setObject(1, hdct.getMaHoaDon());
            stm.setObject(2, hdct.getMaSach());
            stm.setObject(3, hdct.getSoLuong());
            stm.setObject(4, hdct.getDonGia());
            stm.setObject(5, hdct.getThanhTien());
            stm.setObject(6, hdct.getNgayTao());
            stm.setObject(7, hdct.getNgaySua());
            stm.setObject(8, hdct.isTrangThai());
         
            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
