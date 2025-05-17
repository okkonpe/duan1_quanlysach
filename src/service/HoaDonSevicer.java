/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import model.HoaDon;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import model.DBConnect;
import static model.DBConnect.getConnection;
import model.HoaDonChiTiet;

import model.KhachHang;
import model.NhanVien;
import model.Sach;

/**
 *
 * @author trinh thanh
 */
public class HoaDonSevicer {

    public ArrayList<HoaDon> getPendingInvoicesByEmployee() {
        ArrayList<HoaDon> arr = new ArrayList<>();
        Connection con = DBConnect.getConnection();
        String sql = "SELECT hd.MaHoaDon, nv.HoTen AS TenNhanVien, hd.NgayTao, hd.TrangThai\n"
                + "FROM HoaDon hd\n"
                + "JOIN NhanVien nv ON hd.MaNV = nv.MaNV\n"
                + "WHERE hd.TrangThai = N'Đang Chờ Thanh Toán'"; // TrangThai = 0 for 'Chờ xử lý'

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHoaDon(rs.getString("MaHoaDon"));
                hd.setTenNV(rs.getString("TenNhanVien"));
                hd.setNgayLap(rs.getObject("ngayLap", LocalDate.class));
                hd.setTrangThai(rs.getString("Đang chờ thanh toán"));
                arr.add(hd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arr;
    }

    public void deleteHDC(String maHDC) {
        String sql = "DELETE FROM HoaDon WHERE MaHoaDon = ? ";

        try (Connection con = DBConnect.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, maHDC);

            pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<HoaDon> getHDC() {
        ArrayList<HoaDon> arr1 = new ArrayList<>();
        Connection con = DBConnect.getConnection();
        String sql = " SELECT h.MaHoaDon, h.MaKH\n"
                + ",h.MaNV\n"
                + ",h.MaGiamGia\n"
                + ",h.NgayTao\n"
                + "  ,h.ngaySua\n"
                + "  ,h.GiamGia\n"
                + "                   ,h.ThanhTien\n"
                + "                     ,h.TrangThai\n"
                + "                         ,n.hoten\n"
                + "                FROM HoaDon h inner join NhanVien n on h.maNV=n.maNV where h.trangthai=?";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, "Đang chờ thanh toán");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHoaDon(rs.getString("MaHoaDon"));
                hd.setKhachHang(rs.getString("MaKH"));
                hd.setMaNV(rs.getString("MaNV"));
                hd.setTenNV(rs.getString("hoten"));
                hd.setMaGG(rs.getString("MaGiamGia"));
                hd.setNgayLap(rs.getObject("ngayTao", LocalDate.class));
                hd.setGiamGia(rs.getInt("GiamGia"));
                hd.setTongTien(rs.getInt("thanhTien"));
                hd.setNgaySua(rs.getObject("ngaySua", LocalDate.class));
                hd.setTrangThai(rs.getString("TrangThai"));
                arr1.add(hd);
            }
        } catch (Exception e) {
        }
        return arr1;

    }

    public ArrayList<HoaDon> getAll() {
        ArrayList<HoaDon> arr1 = new ArrayList<>();
        Connection con = DBConnect.getConnection();
        String sql = "SELECT [MaHoaDon]\n"
                + "      ,[MaKH]\n"
                + "      ,[MaNV]\n"
                + "      ,[MaGiamGia]\n"
                + "      ,[NgayTao]\n"
                + "      ,[ngaySua]\n"
                + "      ,[GiamGia]\n"
                + "      ,[ThanhTien]\n"
                + "      ,[TrangThai]\n"
                + "  FROM [dbo].[HoaDon]";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHoaDon(rs.getString("MaHoaDon"));
                hd.setKhachHang(rs.getString("MaKH"));
                hd.setMaNV(rs.getString("MaNV"));
//                hd.setTenNV(rs.getString("hoten"));
                hd.setMaGG(rs.getString("MaGiamGia"));
                hd.setNgayLap(rs.getObject("ngayTao", LocalDate.class));
                hd.setGiamGia(rs.getInt("GiamGia"));
                hd.setTongTien(rs.getInt("thanhTien"));
                hd.setNgaySua(rs.getObject("ngaySua", LocalDate.class));
                hd.setTrangThai(rs.getString("TrangThai"));
                arr1.add(hd);
            }
        } catch (Exception e) {
        }
        return arr1;

    }

    public void themSanPhamVaoGioHang(String maHoaDon, String maSach, int soLuong, double donGia, double thanhTien, LocalDate ngayTao) {
        String sql = "INSERT INTO chitiethoadon (MaHoaDon, MaSach, SoLuong, DonGia, ThanhTien, ngayTao) VALUES (?, ?, ?, ?, ?,?)";

        try (Connection con = DBConnect.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, maHoaDon);
            pst.setString(2, maSach);
            pst.setInt(3, soLuong);
            pst.setDouble(4, donGia);
            pst.setDouble(5, thanhTien);
            pst.setObject(6, ngayTao);
            pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<HoaDonChiTiet> getGioHangByHoaDon(String maHoaDon) {
        ArrayList<HoaDonChiTiet> listGH = new ArrayList<>();
        String sql = "select h.MaSach,h.SoLuong,h.DonGia,h.ThanhTien,s.TenSach,t.TenTG,x.tenNXB,tl.TenTL from chitiethoadon h inner join sach s on h.masach=s.masach inner join tacgia t on s.matg=t.matg inner join theloai tl on s.matl=tl.matl inner join nhaxuatban x on s.manxb=x.manxb   WHERE MaHoaDon =?";

        try (Connection con = DBConnect.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, maHoaDon);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                HoaDonChiTiet gh = new HoaDonChiTiet();
                gh.setMaSach(rs.getString("MaSach"));
                gh.setSoLuong(rs.getInt("SoLuong"));
                gh.setDonGia(rs.getDouble("DonGia"));
                gh.setThanhTien(rs.getDouble("ThanhTien"));
                gh.setTenSach(rs.getString("TenSach"));
                gh.setTacGia(rs.getString("tenTG"));
                gh.setNxb(rs.getString("tenNXB"));
                gh.setTheLoai(rs.getString("tenTL"));

                listGH.add(gh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listGH;
    }

    public void capNhatSoLuongSanPham(String maHoaDon, String maSach, int soLuongMoi, double thanhTien) {
        String sql = "UPDATE ChiTietHoaDon SET SoLuong = ?, ThanhTien = ? WHERE MaHoaDon = ? AND MaSach = ?";

        try (Connection con = DBConnect.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, soLuongMoi);
            pst.setDouble(2, thanhTien);
            pst.setString(3, maHoaDon);
            pst.setString(4, maSach);

            int result = pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void thanhToan(String maHoaDon, String trangThai, double tongTien, String maKH, String maGG, double giamGia) {
        String sql = "UPDATE HoaDon SET trangThai = ?, ThanhTien = ? , maKH = ?, maGiamGia =?, giamGia = ? WHERE MaHoaDon = ?";

        try (Connection con = DBConnect.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, trangThai);
            pst.setDouble(2, tongTien);
            pst.setObject(3, maKH);
            pst.setString(4, maGG);
            pst.setObject(5, giamGia);
            pst.setObject(6, maHoaDon);

            pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
     public void huyDonHang(String maHoaDon, String trangThai) {
        String sql = "UPDATE HoaDon SET trangThai = ? WHERE MaHoaDon = ?";

        try (Connection con = DBConnect.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, trangThai);
            pst.setObject(2, maHoaDon);

            pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean xoaSanPhamKhoiGioHang(String maHoaDon, String maSach) {
        String sql = "DELETE FROM ChiTietHoaDon WHERE MaHoaDon = ? AND MaSach = ?";

        try (Connection con = DBConnect.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, maHoaDon);
            pst.setString(2, maSach);

            int result = pst.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private String generateMaHoaDon() {
        String prefix = "HD";
        String number = String.format("%04d", (int) (Math.random() * 1001));
        String suffix = getRandomTwoLetters();
        return prefix + number + suffix;
    }

    private String getRandomTwoLetters() {
        Random random = new Random();
        char firstLetter = (char) (random.nextInt(26) + 'A');
        char secondLetter = (char) (random.nextInt(26) + 'A');
        return "" + firstLetter + secondLetter;
    }

    public HoaDon createHoaDon(String maNV) {
        String maHD = generateMaHoaDon();
        ArrayList<HoaDon> listHD = this.getAll();

        while (true) {            
           for (HoaDon hd : listHD) {
            if (maHD.equalsIgnoreCase(hd.getMaHoaDon())) {
                maHD = generateMaHoaDon();
                break;
                
            }

        } 
           
              break;  
            
        }

        HoaDon hoaDon = new HoaDon();
        hoaDon.setMaHoaDon(maHD);
        hoaDon.setMaNV(maNV);
        hoaDon.setTongTien(0);
        hoaDon.setNgayLap(LocalDate.now());
        hoaDon.setTrangThai("Đang chờ thanh toán");
        Connection con = DBConnect.getConnection();
        String sql = "INSERT INTO [dbo].[HoaDon]\n"
                + "           ([MaHoaDon]\n"
                + "           ,[maNV]\n"
                + "           ,[NgayTao]\n"
                + "           ,[ThanhTien]\n"
                + "           ,[TrangThai])\n"
                + "     VALUES\n"
                + "	(?,?,?,?,?)";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setObject(1, hoaDon.getMaHoaDon());
            stm.setObject(2, hoaDon.getMaNV());
            stm.setObject(3, hoaDon.getNgayLap());
            stm.setObject(4, hoaDon.getTongTien());
            stm.setObject(5, hoaDon.isTrangThai());
            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hoaDon;
    }
    
      public List<HoaDon> searchAndFilterHD(String keyword,String trangThai) {
        List<HoaDon> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
                "SELECT s.mahoadon, s.makh, s.manv, s.magiamgia, s.ngaytao, "
                + "s.giamgia, s.thanhtien,s.TrangThai "
                + "FROM HoaDon s "              
                + "WHERE 1=1" // Điều kiện mặc định để dễ nối thêm các điều kiện
        );

        // Thêm điều kiện tìm kiếm từ keyword
        if (keyword != null && !keyword.isEmpty()) {
            sql.append(" AND (s.maHoadon LIKE ? OR s.makh LIKE ? OR s.manv LIKE ?)");
        }

        // Thêm điều kiện tìm kiếm từ các ComboBox
        if (trangThai != null && !trangThai.isEmpty()) {
            sql.append(" AND s.trangThai = ?");
        }
       

        // Thêm điều kiện tìm kiếm từ trạng thái
       

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            int index = 1;

            // Gán giá trị cho keyword
            if (keyword != null && !keyword.isEmpty()) {
                stmt.setString(index++, "%" + keyword + "%");
                stmt.setString(index++, "%" + keyword + "%");
                stmt.setString(index++, "%" + keyword + "%");
            }

            // Gán giá trị cho các ComboBox
           
            if (trangThai != null && !trangThai.isEmpty()) {
                stmt.setString(index++, trangThai);
            }

            // Gán giá trị cho trạng thái
         

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                 HoaDon hd = new HoaDon();
                hd.setMaHoaDon(rs.getString("MaHoaDon"));
                hd.setKhachHang(rs.getString("MaKH"));
                hd.setMaNV(rs.getString("MaNV"));
                hd.setMaGG(rs.getString("MaGiamGia"));
                hd.setNgayLap(rs.getObject("ngayTao", LocalDate.class));
                hd.setGiamGia(rs.getInt("GiamGia"));
                hd.setTongTien(rs.getInt("thanhTien"));
            
                hd.setTrangThai(rs.getString("TrangThai"));               
                result.add(hd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void add(HoaDon hd) {
        Connection con = DBConnect.getConnection();
        String sql = "INSERT INTO [dbo].[HoaDon]\n"
                + "           ([MaHoaDon]\n"
                + "           ,[MaKH]\n"
                + "           ,[MaNV]\n"
                + "           ,[MaGiamGia]\n"
                + "           ,[NgayTao]\n"
                + "           ,[GiamGia]\n"
                + "           ,[ThanhTien]\n"
                + "           ,[TrangThai])\n"
                + "     VALUES\n"
                + "	(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setObject(1, hd.getMaHoaDon());
            stm.setObject(2, hd.getKhachHang());
            stm.setObject(3, hd.getMaNV());
            stm.setObject(4, hd.getMaGG());
            stm.setObject(5, hd.getNgayLap());
            stm.setObject(6, hd.getGiamGia());
            stm.setObject(7, hd.getTongTien());
            stm.setObject(8, hd.isTrangThai());

            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
