/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import model.Sach;
import java.sql.*;
import model.DBConnect;

/**
 *
 * @author trinh thanh
 */
public class SachSevicer {

    public ArrayList<Sach> getAll() {
        ArrayList<Sach> arr = new ArrayList<>();
        Connection con = DBConnect.getConnection();
        String sql = "SELECT dbo.Sach.MaSach, dbo.Sach.TenSach, dbo.Sach.DonGia, dbo.TheLoai.TenTL, dbo.TacGia.TenTG, dbo.NhaXuatBan.TenNXB, dbo.Sach.SoLuong, dbo.Sach.TrangThai "
                + "FROM dbo.NhaXuatBan "
                + "INNER JOIN dbo.Sach ON dbo.NhaXuatBan.MaNXB = dbo.Sach.MaNXB "
                + "INNER JOIN dbo.TacGia ON dbo.Sach.MaTG = dbo.TacGia.MaTG "
                + "INNER JOIN dbo.TheLoai ON dbo.Sach.MaTL = dbo.TheLoai.MaTL "
                + "WHERE dbo.Sach.TrangThai = 1";
        try {
            if (con == null) {
                System.out.println("Database connection failed!");
                return arr;
            }
            System.out.println("SQL Query: " + sql);
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Sach sa = new Sach();
                sa.setMaSach(rs.getString(1));
                sa.setTenSach(rs.getString(2));
                sa.setDongia(rs.getDouble(3));
                sa.setTenTheLoai(rs.getString(4));
                sa.setTenTacGia(rs.getString(5));
                sa.setTenNXB(rs.getString(6));
                sa.setSoLuong(rs.getInt(7));
                sa.setTrangThai(rs.getBoolean(8));
                arr.add(sa);
            }
        } catch (Exception e) {
            System.out.println("Error in getAll(): " + e.getMessage());
            e.printStackTrace();
        }
        return arr;
    }

}
