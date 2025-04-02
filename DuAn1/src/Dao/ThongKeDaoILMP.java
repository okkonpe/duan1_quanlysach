/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import ModelView.TongHoaDon;
import java.util.List;
import model.DoanhThu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DBConnect;
import ModelView.spbanchay;

/**
 *
 * @author phung
 */
public class ThongKeDaoILMP implements ThongKeDao{

    @Override
    public List<DoanhThu> getlistbydoanhthu() {
        try {
            Connection con = DBConnect.getConnection();
            String sql ="SELECT \n" +
"    H.NgayTao AS Ngay,\n" +
"    SUM(CT.ThanhTien) AS TongDoanhThu\n" +
"FROM \n" +
"    HoaDon H\n" +
"JOIN \n" +
"    ChiTietHoaDon CT ON H.MaHoaDon = CT.MaHoaDon\n" +
"GROUP BY \n" +
"    H.NgayTao\n" +
"ORDER BY \n" +
"    H.NgayTao;";
            List<DoanhThu> list = new ArrayList<>();
            PreparedStatement ps = con.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DoanhThu doanhThu = new DoanhThu();
                doanhThu.setNgaygiaodich(rs.getString("Ngay"));
                doanhThu.setTongtien(rs.getDouble("TongDoanhThu"));
                list.add(doanhThu);
                
            }
            return list;
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<spbanchay> getlistbyspbanchay() {
    try {
            Connection con = DBConnect.getConnection();
            String sql = "SELECT TOP 5 \n" +
"    [Sach].[TenSach] AS [TenSanPham],\n" +
"    SUM([ChiTietHoaDon].[SoLuong]) AS [TongSoLuongBan]\n" +
"FROM \n" +
"    [ChiTietHoaDon]\n" +
"INNER JOIN \n" +
"    [Sach] ON [ChiTietHoaDon].[MaSach] = [Sach].[MaSach]\n" +
"GROUP BY \n" +
"    [Sach].[TenSach]\n" +
"ORDER BY \n" +
"    [TongSoLuongBan] DESC;";
            List<spbanchay> list = new ArrayList<>();
            PreparedStatement ps = con.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                spbanchay spbc = new spbanchay();
                spbc.setSoluong(rs.getInt("TongSoLuongBan"));
                spbc.setTensp(rs.getString("TenSanPham"));
                list.add(spbc);
                
            }
            return list;
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
      return null;
    
    }  
  
    @Override
    public List<DoanhThu> getslistdoanhthutheongay(String ngaybatdau, String ngayketthuc) {
    try {
        Connection con = DBConnect.getConnection();
        String sql = "SELECT H.NgayTao AS Ngay, SUM(CT.ThanhTien) AS TongDoanhThu " +
                     "FROM HoaDon H " +
                     "JOIN ChiTietHoaDon CT ON H.MaHoaDon = CT.MaHoaDon " +
                     "WHERE H.NgayTao BETWEEN ? AND ? " +
                     "GROUP BY H.NgayTao " +
                     "ORDER BY H.NgayTao";

        List<DoanhThu> list = new ArrayList<>();
        PreparedStatement ps = con.prepareStatement(sql);
        
        // Thiết lập tham số vào PreparedStatement
        ps.setString(1, ngaybatdau);  // Ngày bắt đầu
        ps.setString(2, ngayketthuc); // Ngày kết thúc

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            DoanhThu doanhThu = new DoanhThu();
            doanhThu.setNgaygiaodich(rs.getString("Ngay"));
            doanhThu.setTongtien(rs.getDouble("TongDoanhThu"));
            list.add(doanhThu);
        }
        return list;
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return null;
}

    @Override
    public List<TongHoaDon> getslisttonghoadon() {
     try {
        // Kết nối tới cơ sở dữ liệu
        Connection con = DBConnect.getConnection();

        // Câu lệnh SQL để lấy tổng số hóa đơn theo ngày
        String sql = "SELECT H.NgayTao AS Ngay, COUNT(H.MaHoaDon) AS TongHoaDon " +
                     "FROM HoaDon H " +
                     "GROUP BY H.NgayTao " +
                     "ORDER BY H.NgayTao";

        // Danh sách kết quả
        List<TongHoaDon> list = new ArrayList<>();

        // Chuẩn bị câu lệnh SQL
        PreparedStatement ps = con.prepareStatement(sql);

        // Thực thi câu lệnh SQL
        ResultSet rs = ps.executeQuery();

        // Xử lý kết quả trả về
        while (rs.next()) {
            TongHoaDon tongHoaDon = new TongHoaDon();
            tongHoaDon.setNgay(rs.getString("Ngay"));  // Ngày tạo hóa đơn
            tongHoaDon.setSoluong(rs.getInt("TongHoaDon"));  // Tổng số hóa đơn trong ngày
            list.add(tongHoaDon);  // Thêm vào danh sách
        }

        // Trả về danh sách kết quả
        return list;

    } catch (SQLException ex) {
        ex.printStackTrace();  // In ra lỗi nếu có
    }

    // Trả về null nếu có lỗi
    return null;
   
}

    @Override
    public List<TongHoaDon> getslisttonghoadontheongay(String ngaybatdau, String ngayketthuc) {
      try {
        // Kết nối đến cơ sở dữ liệu
        Connection con = DBConnect.getConnection();

        // Câu lệnh SQL để lấy tổng số hóa đơn theo ngày trong khoảng thời gian
        String sql = "SELECT H.NgayTao AS Ngay, COUNT(H.MaHoaDon) AS TongHoaDon " +
                     "FROM HoaDon H " +
                     "WHERE H.NgayTao BETWEEN ? AND ? " +
                     "GROUP BY H.NgayTao " +
                     "ORDER BY H.NgayTao";

        // Danh sách kết quả trả về
        List<TongHoaDon> list = new ArrayList<>();

        // Chuẩn bị câu lệnh SQL
        PreparedStatement ps = con.prepareStatement(sql);
        
        // Thiết lập tham số vào PreparedStatement
        ps.setString(1, ngaybatdau);  // Ngày bắt đầu
        ps.setString(2, ngayketthuc); // Ngày kết thúc

        // Thực thi câu lệnh SQL
        ResultSet rs = ps.executeQuery();

        // Xử lý kết quả trả về
        while (rs.next()) {
            TongHoaDon tongHoaDon = new TongHoaDon();
            tongHoaDon.setNgay(rs.getString("Ngay"));  // Ngày tạo hóa đơn
            tongHoaDon.setSoluong(rs.getInt("TongHoaDon"));  // Tổng số hóa đơn
            list.add(tongHoaDon);  // Thêm vào danh sách
        }

        // Trả về danh sách kết quả
        return list;

    } catch (SQLException ex) {
        ex.printStackTrace();
    }

    // Trả về null nếu có lỗi
    return null;
    }
}
