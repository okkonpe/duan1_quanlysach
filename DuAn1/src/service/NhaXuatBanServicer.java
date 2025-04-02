/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import java.sql.*;
import model.DBConnect;
import model.NhaXuatBan;

/**
 *
 * @author trinh thanh
 */
public class NhaXuatBanServicer {

    public ArrayList<NhaXuatBan> getAll() {
        Connection con = DBConnect.getConnection();
        ArrayList<NhaXuatBan> arr = new ArrayList<>();
        String sql = """
                SELECT [MaNXB],
                       [TenNXB],
                       [DiaChi],
                       [Email],
                       [TrangThai]
                FROM [dbo].[NhaXuatBan]
                """;
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                NhaXuatBan nxb = new NhaXuatBan();
                nxb.setMaNXB(rs.getString("MaNXB"));
                nxb.setTenNXB(rs.getString("TenNXB"));
                nxb.setDiaChi(rs.getString("DiaChi"));
                nxb.setEmail(rs.getString("Email"));
                nxb.setTrangThai(rs.getBoolean("TrangThai"));
                arr.add(nxb);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }

    public void addNhaXuatBan(NhaXuatBan nxb) {
        Connection con = DBConnect.getConnection();
        String sql = """
                INSERT INTO [dbo].[NhaXuatBan]
                       ([MaNXB], [TenNXB], [DiaChi], [Email], [TrangThai])
                VALUES (?, ?, ?, ?, ?)
                """;
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setObject(1, nxb.getMaNXB());
            stm.setObject(2, nxb.getTenNXB());
            stm.setObject(3, nxb.getDiaChi());
            stm.setObject(4, nxb.getEmail());
            stm.setObject(5, nxb.isTrangThai());
            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateNhaXuatBan(NhaXuatBan nxb) {
        Connection con = DBConnect.getConnection();
        String sql = """
                UPDATE [dbo].[NhaXuatBan]
                SET [TenNXB] = ?, [DiaChi] = ?, [Email] = ?, [TrangThai] = ?
                WHERE [MaNXB] = ?
                """;
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setObject(1, nxb.getTenNXB());
            stm.setObject(2, nxb.getDiaChi());
            stm.setObject(3, nxb.getEmail());
            stm.setObject(4, nxb.isTrangThai());
            stm.setObject(5, nxb.getMaNXB());
            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteNhaXuatBan(String MaNXB) {
        Connection con = DBConnect.getConnection();
        String sql = "DELETE FROM [dbo].[NhaXuatBan] WHERE [MaNXB] = ?";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setObject(1, MaNXB);
            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<NhaXuatBan> search(String keyWord) {
        Connection con = DBConnect.getConnection();
        ArrayList<NhaXuatBan> arr = new ArrayList<>();

        String sql = """
                SELECT [MaNXB],
                       [TenNXB],
                       [DiaChi],
                       [Email],
                       [TrangThai]
                FROM [dbo].[NhaXuatBan]
                """;

        if (keyWord != null && !keyWord.trim().isEmpty()) {
            sql += """
                   WHERE (MaNXB LIKE ? OR
                          TenNXB LIKE ? OR
                          DiaChi LIKE ? OR
                          Email LIKE ?)
                   """;
        }

        try {
            PreparedStatement stm = con.prepareStatement(sql);

            if (keyWord != null && !keyWord.trim().isEmpty()) {
                String values = "%" + keyWord.trim() + "%";
                stm.setString(1, values);
                stm.setString(2, values);
                stm.setString(3, values);
                stm.setString(4, values);
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                NhaXuatBan nxb = new NhaXuatBan();
                nxb.setMaNXB(rs.getString("MaNXB"));
                nxb.setTenNXB(rs.getString("TenNXB"));
                nxb.setDiaChi(rs.getString("DiaChi"));
                nxb.setEmail(rs.getString("Email"));
                nxb.setTrangThai(rs.getBoolean("TrangThai"));
                arr.add(nxb);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return arr;
    }
}
