/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.Scanner;
import model.NhanVien;
import java.sql.*;
import model.DBConnect;

/**
 *
 * @author trinh thanh
 */
public class TaiKhoanControolleerr {

     public  static String maNhanVien; // Lưu mã nhân viên sau khi đăng nhập
 public static  String hoTenNhanVien; // Lưu họ tên nhân viên
    public static String quyenNhanVien; // Lưu quyền của nhân viên

    public boolean dangNhap(String tenDangNhap, String matKhau) {
        String sql = """
                 SELECT MaNV, HoTen, Quyen
                 FROM NhanVien
                 WHERE TenTaiKhoan = ? AND MatKhau = ?
                 """;

        try (Connection conn = DBConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tenDangNhap);
            stmt.setString(2, matKhau);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                maNhanVien = rs.getString("MaNV");
                hoTenNhanVien = rs.getString("HoTen");
                quyenNhanVien = rs.getString("Quyen");

                System.out.println("Đăng nhập thành công! Tên: " + hoTenNhanVien + ", Quyền: " + quyenNhanVien);
                return true;
            } else {
                System.out.println("Tên đăng nhập hoặc mật khẩu không đúng.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public String getHoTenNhanVien() {
        return hoTenNhanVien;
    }

    public String getQuyenNhanVien() {
        return quyenNhanVien;
    }

    public String layTenNhanVien(String TenNV) {
        String hoTen = null; // Khai báo biến HoTen
        String sql = "SELECT HoTen FROM NhanVien WHERE TenTaiKhoan = ?"; // Câu truy vấn SQL để lấy HoTen

        try (Connection con = DBConnect.getConnection()) {
            if (con == null) {
                System.out.println("Không thể kết nối cơ sở dữ liệu.");
                return null;
            }

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, TenNV);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                hoTen = rs.getString("HoTen");  // Lấy HoTen từ kết quả trả về
            } else {
                System.out.println("Không tìm thấy nhân viên với tài khoản: " + TenNV);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi truy vấn SQL: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Lỗi khác: " + e.getMessage());
            e.printStackTrace();
        }

        return hoTen;  // Trả về HoTen nếu tìm thấy
    }

    public String layIDNhaanVien(String taiKhoan) {
        String maNhanVien = null;
        String sql = "SELECT MaNV FROM NhanVien WHERE TenTaiKhoan = ?";
        try (Connection con = DBConnect.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, taiKhoan);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                maNhanVien = rs.getString("MaNV");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maNhanVien;
    }

    public String layVaiTro(String tenTaiKhoan) {
        String sql = "SELECT Quyen FROM NhanVien WHERE TenTaiKhoan = ?";
        String quyen = null;

        try (Connection conn = DBConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tenTaiKhoan);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                quyen = rs.getString("Quyen");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return quyen;
    }

    public boolean kiemTraQuyen(String quyen, String quyenCanKiemTra) {
        return quyen != null && quyen.equalsIgnoreCase(quyenCanKiemTra);
    }
}
