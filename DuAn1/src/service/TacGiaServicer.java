/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import model.TacGia;
import java.sql.*;
import model.DBConnect;

/**
 *
 * @author trinh thanh
 */
public class TacGiaServicer {

    public ArrayList<TacGia> getAll() {
        ArrayList<TacGia> arr = new ArrayList<>();
        Connection con = DBConnect.getConnection();
        String sql = "SELECT [MaTG]\n"
                + "      ,[TenTG]\n"
                + "      ,[NgaySinh]\n"
                + "      ,[QuocTich]\n"
                + "      ,[TrangThai]\n"
                + "  FROM [dbo].[TacGia]";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                TacGia tg = new TacGia();
                tg.setMaTacGia(rs.getString("MaTG"));
                tg.setTenTacGia(rs.getString("TenTG"));
                tg.setNgaySinh(rs.getDate("NgaySinh"));
                tg.setDiaChi(rs.getString("QuocTich"));
                tg.setTrangThai(rs.getBoolean("TrangThai")); // Thêm trạng thái
                arr.add(tg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }

    public void addTacGia(TacGia tg) {
        Connection con = DBConnect.getConnection();
        String sql = "INSERT INTO [dbo].[TacGia]\n"
                + "           ([MaTG]\n"
                + "           ,[TenTG]\n"
                + "           ,[NgaySinh]\n"
                + "           ,[QuocTich]\n"
                + "           ,[TrangThai])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?)";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setObject(1, tg.getMaTacGia());
            stm.setObject(2, tg.getTenTacGia());
            stm.setObject(3, tg.getNgaySinh());
            stm.setObject(4, tg.getDiaChi());
            stm.setObject(5, tg.isTrangThai()); // Thêm trạng thái
            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void UpdateTacGia(TacGia tg) {
        Connection con = DBConnect.getConnection();
        String sql = "UPDATE [dbo].[TacGia]\n"
                + "   SET\n"
                + "      [TenTG] = ?\n"
                + "      ,[NgaySinh] = ?\n"
                + "      ,[QuocTich] = ?\n"
                + "      ,[TrangThai] = ?\n" // Thêm trạng thái
                + " WHERE [MaTG] = ?";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setObject(1, tg.getTenTacGia());
            stm.setObject(2, tg.getNgaySinh());
            stm.setObject(3, tg.getDiaChi());
            stm.setObject(4, tg.isTrangThai()); // Thêm trạng thái
            stm.setObject(5, tg.getMaTacGia());
            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteTacGia(String MaTG) {
        Connection con = DBConnect.getConnection();
        String sql = "DELETE FROM [dbo].[TacGia]\n"
                + "      WHERE MaTG = ?";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setObject(1, MaTG);
            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
