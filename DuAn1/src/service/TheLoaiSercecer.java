/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import model.TheLoai;
import java.sql.*;
import model.DBConnect;

/**
 *
 * @author trinh thanh
 */
public class TheLoaiSercecer {

    public ArrayList<TheLoai> getAll() {
        ArrayList<TheLoai> arr = new ArrayList<>();
        Connection con = DBConnect.getConnection();
        String sql = "SELECT [MaTL]\n"
                + "      ,[TenTL]\n"
                + "      ,[NgayTao]\n"
                + "      ,[NgaySua]\n"
                + "      ,[TrangThai]\n"
                + "  FROM [dbo].[TheLoai]";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                TheLoai tl = new TheLoai();
                tl.setMaTheLoai(rs.getString("MaTL"));
                tl.setTenTheLoai(rs.getString("TenTL"));
                tl.setTrangThai(rs.getBoolean("TrangThai"));
                arr.add(tl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }

    public void addTheLoai(TheLoai tl) {
        Connection con = DBConnect.getConnection();
        String sql = "INSERT INTO [dbo].[TheLoai]\n"
                + "           ([MaTL]\n"
                + "           ,[TenTL])\n"
                + "     VALUES\n"
                + "           (?,?)";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setObject(1, tl.getMaTheLoai());
            stm.setObject(2, tl.getTenTheLoai());
            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void UpdateTheLoai(TheLoai tl) {
        Connection con = DBConnect.getConnection();
        String sql = "UPDATE [dbo].[TheLoai]\n"
                + "   SET \n"
                + "      [TenTL] = ?\n"
                + " WHERE [MaTL] = ?";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setObject(1, tl.getTenTheLoai());
            stm.setObject(2, tl.getMaTheLoai());
            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteTheLoai(String MaTL) {
        Connection con = DBConnect.getConnection();
        String sql = "DELETE FROM [dbo].[TheLoai]\n"
                + "      WHERE MaTL = ?";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setObject(1, MaTL);
            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
