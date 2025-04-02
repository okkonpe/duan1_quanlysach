/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import model.KhachHang;
import java.sql.*;
import java.util.List;
import model.DBConnect;

/**
 *
 * @author trinh thanh
 */
public class KhachHangServiec {

    public ArrayList<KhachHang> getAll() {
        ArrayList<KhachHang> arr = new ArrayList<>();
        Connection con = DBConnect.getConnection();
        String sql = "SELECT [MaKH]\n"
                + "      ,[HoTen]\n"
                + "      ,[NgaySinh]\n"
                + "      ,[SoDienThoai]\n"
                + "      ,[Email]\n"
                + "      ,[GioiTinh]\n"
                + "      ,[DiaChi]\n"
                + "  FROM [dbo].[KhachHang]";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaKhachHang(rs.getString("MaKH"));
                kh.setTenKh(rs.getString("HoTen"));
                kh.setsĐt(rs.getString("SoDienThoai"));
                kh.seteMail(rs.getString("Email"));
                kh.setGioiTinh(rs.getBoolean("GioiTinh"));
                kh.setNgaySinh(rs.getDate("NgaySinh"));
                kh.setDiaChi(rs.getString("DiaChi"));
                arr.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }

    public KhachHang getKHbySDT(String sdt) {
        KhachHang kh = new KhachHang();
        Connection con = DBConnect.getConnection();
        String sql = "SELECT [MaKH]\n"
                + "      ,[HoTen]\n"
                + "      ,[NgaySinh]\n"
                + "      ,[SoDienThoai]\n"
                + "      ,[Email]\n"
                + "      ,[GioiTinh]\n"
                + "      ,[DiaChi]\n"
                + "  FROM [dbo].[KhachHang] where sodienthoai=?";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setObject(1, sdt);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                kh.setMaKhachHang(rs.getString("MaKH"));
                kh.setTenKh(rs.getString("HoTen"));
                kh.setsĐt(rs.getString("SoDienThoai"));
                kh.seteMail(rs.getString("Email"));
                kh.setGioiTinh(rs.getBoolean("GioiTinh"));
                kh.setNgaySinh(rs.getDate("NgaySinh"));
                kh.setDiaChi(rs.getString("DiaChi"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kh;
    }

    public void addKhachHang(KhachHang kh) {
        Connection con = DBConnect.getConnection();
        String sql = "INSERT INTO [dbo].[KhachHang]\n"
                + "           ([maKH]\n"
                + "           ,[HoTen]\n"
                + "           ,[NgaySinh]\n"
                + "           ,[soDienthoai]\n"
                + "           ,[email]\n"
                + "           ,[GioiTinh]\n"
                + "           ,[DiaChi])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?)";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setObject(1, kh.getMaKhachHang());
            stm.setObject(2, kh.getTenKh());
            stm.setObject(3, kh.getNgaySinh());
            stm.setObject(4, kh.getsĐt());
            stm.setObject(5, kh.geteMail());
            stm.setObject(6, kh.isGioiTinh());
            stm.setObject(7, kh.getDiaChi());
            stm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateKhachHang(KhachHang kh) {
        Connection con = DBConnect.getConnection();
        String sql = "UPDATE [dbo].[KhachHang]\n"
                + "   SET \n"
                + "      [HoTen] = ?\n"
                + "      ,[NgaySinh] = ?\n"
                + "      ,[SoDienThoai] = ?\n"
                + "      ,[Email] =?\n"
                + "      ,[GioiTinh] = ?\n"
                + "      ,[DiaChi] = ?\n"
                + " WHERE [MaKH] = ?";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, kh.getTenKh());
            stm.setDate(2, (Date) kh.getNgaySinh());
            stm.setString(3, kh.getsĐt());
            stm.setString(4, kh.geteMail());
            stm.setBoolean(5, kh.isGioiTinh());
            stm.setString(6, kh.getDiaChi());
            stm.setString(7, kh.getMaKhachHang());
            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteKhachhang(String MaKH) {
        Connection con = DBConnect.getConnection();
        String sql = "DELETE FROM [dbo].[KhachHang]\n"
                + "      WHERE MaKH = ?";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, MaKH);
            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<KhachHang> searchKhachHang(String keyword) {
        List<KhachHang> result = new ArrayList<>();
        String sql = "SELECT * FROM khachHang WHERE MaKH LIKE ? OR HoTen LIKE ? OR Email LIKE ? OR DiaChi LIKE ? OR GioiTinh LIKE ? OR SoDienThoai LIKE ?";
        try (Connection conn = DBConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");
            stmt.setString(3, "%" + keyword + "%");
            stmt.setString(4, "%" + keyword + "%");
            stmt.setString(5, "%" + keyword + "%");
            stmt.setString(6, "%" + keyword + "%");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaKhachHang(rs.getString("MaKH"));
                kh.setTenKh(rs.getString("HoTen"));
                kh.setNgaySinh(rs.getDate("NgaySinh"));
                kh.setsĐt(rs.getString("SoDienThoai"));
                kh.seteMail(rs.getString("Email"));
                kh.setGioiTinh(rs.getBoolean("GioiTinh"));
                kh.setDiaChi(rs.getString("DiaChi"));
                result.add(kh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
