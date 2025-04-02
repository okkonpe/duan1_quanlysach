/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import model.NhanVien;
import java.sql.*;
import java.util.List;
import model.DBConnect;

/**
 *
 * @author trinh thanh
 */
public class NhanVienSevicer {

    public ArrayList<NhanVien> getAll() {
        ArrayList<NhanVien> arr = new ArrayList<>();
        Connection con = DBConnect.getConnection();
        String sql = "SELECT [MaNV]\n"
                + "      ,[TenTaiKhoan]\n"
                + "      ,[MatKhau]\n"
                + "      ,[Quyen]\n"
                + "      ,[HoTen]\n"
                + "      ,[GioiTinh]\n"
                + "      ,[DiaChi]\n"
                + "      ,[SoDienThoai]\n"
                + "      ,[TrangThai]\n"
                + "  FROM [dbo].[NhanVien]";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNhanVien(rs.getString("MaNV"));
                nv.setTenTaiKhoan(rs.getString("TenTaiKhoan"));
                nv.setMatKhau(rs.getString("MatKhau"));
                nv.setQuyen(rs.getString("Quyen"));
                nv.setTen(rs.getString("HoTen"));
                nv.setGioiTinh(rs.getBoolean("GioiTinh"));
                nv.setDiaChi(rs.getString("DiaChi"));
                nv.setSđt(rs.getString("SoDienThoai"));
                nv.setTrangThai(rs.getBoolean("TrangThai"));
                arr.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }

    public void addNhanVien(NhanVien nv) {
        Connection con = DBConnect.getConnection();
        String sql = "INSERT INTO [dbo].[NhanVien]\n"
                + "           ([MaNV]\n"
                + "           ,[TenTaiKhoan]\n"
                + "           ,[MatKhau]\n"
                + "           ,[Quyen]\n"
                + "           ,[HoTen]\n"
                + "           ,[GioiTinh]\n"
                + "           ,[DiaChi]\n"
                + "           ,[SoDienThoai]\n"
                + "           ,[TrangThai])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setObject(1, nv.getMaNhanVien());
            stm.setObject(2, nv.getTenTaiKhoan());
            stm.setObject(3, nv.getMatKhau());
            stm.setObject(4, nv.getQuyen());
            stm.setObject(5, nv.getTen());
            stm.setObject(6, nv.isGioiTinh());
            stm.setObject(7, nv.getDiaChi());
            stm.setObject(8, nv.getSđt());
            stm.setObject(9, nv.isTrangThai());
            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteNhanVien(String MaNV) {
        Connection con = DBConnect.getConnection();
        String sql = "DELETE FROM [dbo].[NhanVien]\n"
                + "      WHERE MaNV = ?";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, MaNV);
            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateNhanVien(NhanVien nv) {
        Connection con = DBConnect.getConnection();
        String sql = "UPDATE [dbo].[NhanVien]\n"
                + "   SET \n"
                + "		[TenTaiKhoan] = ?\n"
                + "      ,[MatKhau] = ?\n"
                + "      ,[Quyen] = ?\n"
                + "      ,[HoTen] = ?\n"
                + "      ,[GioiTinh] = ?\n"
                + "      ,[DiaChi] = ?\n"
                + "      ,[SoDienThoai] = ?\n"
                + "      ,[TrangThai] = ?\n"
                + " WHERE [MaNV] = ?";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setObject(9, nv.getMaNhanVien());
            stm.setObject(1, nv.getTenTaiKhoan());
            stm.setObject(2, nv.getMatKhau());
            stm.setObject(3, nv.getQuyen());
            stm.setObject(4, nv.getTen());
            stm.setObject(5, nv.isGioiTinh());
            stm.setObject(6, nv.getDiaChi());
            stm.setObject(7, nv.getSđt());
            stm.setObject(8, nv.isTrangThai());
            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<NhanVien> searchNhanVien(String keyword) {
        List<NhanVien> result = new ArrayList<>();
        String sql = """
        SELECT * FROM NhanVien 
        WHERE MaNV LIKE ? 
           OR TenTaiKhoan LIKE ? 
           OR MatKhau LIKE ? 
           OR Quyen LIKE ? 
           OR HoTen LIKE ? 
           OR DiaChi LIKE ? 
           OR SoDienThoai LIKE ? 
           OR CAST(GioiTinh AS CHAR) LIKE ? 
           OR CAST(TrangThai AS CHAR) LIKE ?
    """;

        try (Connection conn = DBConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            String searchKeyword = "%" + keyword + "%";

            // Đặt tham số tìm kiếm
            for (int i = 1; i <= 9; i++) {
                stmt.setString(i, searchKeyword);
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNhanVien(rs.getString("MaNV"));
                nv.setTenTaiKhoan(rs.getString("TenTaiKhoan"));
                nv.setMatKhau(rs.getString("MatKhau"));
                nv.setQuyen(rs.getString("Quyen"));
                nv.setTen(rs.getString("HoTen"));
                nv.setGioiTinh(rs.getBoolean("GioiTinh"));
                nv.setDiaChi(rs.getString("DiaChi"));
                nv.setSđt(rs.getString("SoDienThoai"));
                nv.setTrangThai(rs.getBoolean("TrangThai"));
                result.add(nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
