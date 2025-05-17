/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.math.BigDecimal;
import java.util.ArrayList;
import model.Sach;
import java.sql.*;
import java.util.List;
import model.DBConnect;
import static model.DBConnect.getConnection;

public class QuanLySachSevicer {

    private Connection con;

    public QuanLySachSevicer() {
        con = DBConnect.getConnection(); // Khởi tạo kết nối
    }

    public ArrayList<Sach> getAll() {
        ArrayList<Sach> arr = new ArrayList<>();
        String sql = "SELECT dbo.Sach.MaSach, dbo.Sach.TenSach, dbo.NhaXuatBan.TenNXB, dbo.TheLoai.TenTL, "
                + "dbo.TacGia.TenTG, dbo.Sach.NamXuatBan, dbo.Sach.SoLuong, dbo.Sach.DonGia, dbo.Sach.HinhAnh, dbo.Sach.TrangThai "
                + "FROM dbo.NhaXuatBan INNER JOIN "
                + "dbo.Sach ON dbo.NhaXuatBan.MaNXB = dbo.Sach.MaNXB INNER JOIN "
                + "dbo.TacGia ON dbo.Sach.MaTG = dbo.TacGia.MaTG INNER JOIN "
                + "dbo.TheLoai ON dbo.Sach.MaTL = dbo.TheLoai.MaTL";

        try (PreparedStatement stm = con.prepareStatement(sql); ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                Sach sa = new Sach();
                sa.setMaSach(rs.getString("MaSach"));
                sa.setTenNXB(rs.getString("TenNXB"));
                sa.setTenTheLoai(rs.getString("TenTL"));
                sa.setTenTacGia(rs.getString("TenTG"));
                sa.setTenSach(rs.getString("TenSach"));
                sa.setNamXuatBan(rs.getString("NamXuatBan"));
                sa.setSoLuong(rs.getInt("SoLuong"));
                sa.setDongia(rs.getDouble("DonGia"));
                sa.setHinh(rs.getString("HinhAnh"));
                sa.setTrangThai(rs.getBoolean("TrangThai"));
                arr.add(sa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arr;
    }

    public int getSlSach(String maSach) {

        String query = "SELECT [soLuong]\n"
                + "  FROM [dbo].[Sach] WHERE Masach =?";

        Sach sh = new Sach();
        try (Connection conn = getConnection(); PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setString(1, maSach);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                sh.setSoLuong(rs.getInt("soLuong"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sh.getSoLuong();
    }

    public void addSach(Sach sa) {
        Connection con = DBConnect.getConnection();
        String sql = "INSERT INTO [dbo].[Sach]\n"
                + "           ([MaSach]\n"
                + "           ,[TenSach]\n"
                + "           ,[MaNXB]\n"
                + "           ,[MaTL]\n"
                + "           ,[MaTG]\n"
                + "           ,[NamXuatBan]\n"
                + "           ,[SoLuong]\n"
                + "           ,[DonGia]\n"
                + "           ,[HinhAnh]\n"
                + "           ,[TrangThai])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setObject(1, sa.getMaSach());
            stm.setObject(2, sa.getTenSach());
            stm.setObject(3, sa.getMaNXB());
            stm.setObject(4, sa.getMaTheLoai());
            stm.setObject(5, sa.getMaTacGia());
            stm.setObject(6, sa.getNamXuatBan());
            stm.setObject(7, sa.getSoLuong());
            stm.setObject(8, sa.getDongia());
            stm.setObject(9, sa.getHinh());
            stm.setObject(10, sa.isTrangThai());
            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateSach(Sach sa) {
        String sql = "UPDATE [dbo].[Sach] SET "
                + "[TenSach] = ?, [MaNXB] = ?, [MaTL] = ?, [MaTG] = ?, "
                + "[NamXuatBan] = ?, [SoLuong] = ?, [DonGia] = ?, [HinhAnh] = ?, [TrangThai] = ? "
                + "WHERE [MaSach] = ?";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, sa.getTenSach());
            stm.setString(2, sa.getMaNXB());
            stm.setString(3, sa.getMaTheLoai());
            stm.setString(4, sa.getMaTacGia());
            stm.setString(5, sa.getNamXuatBan());
            stm.setInt(6, sa.getSoLuong());
            stm.setDouble(7, sa.getDongia());
            stm.setString(8, sa.getHinh());
            stm.setBoolean(9, sa.isTrangThai());
            stm.setString(10, sa.getMaSach());
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to update book: " + e.getMessage());
        }
    }

    public List<String> getAllNXBNames() {
        List<String> nxbNames = new ArrayList<>();
        String sql = "SELECT TenNXB FROM NhaXuatBan"; // Lấy tất cả tên nhà xuất bản
        try (PreparedStatement stm = con.prepareStatement(sql); ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                nxbNames.add(rs.getString("TenNXB")); // Thêm tên nhà xuất bản vào danh sách
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nxbNames;
    }

    public List<String> getAllTheLoaiNames() {
        List<String> tlNames = new ArrayList<>();
        String sql = "SELECT TenTL FROM TheLoai"; // Lấy tất cả tên thể loại
        try (PreparedStatement stm = con.prepareStatement(sql); ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                tlNames.add(rs.getString("TenTL")); // Thêm tên thể loại vào danh sách
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tlNames;
    }

    public List<String> getAllTacGiaNames() {
        List<String> tgNames = new ArrayList<>();
        String sql = "SELECT TenTG FROM TacGia"; // Lấy tất cả tên tác giả
        try (PreparedStatement stm = con.prepareStatement(sql); ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                tgNames.add(rs.getString("TenTG")); // Thêm tên tác giả vào danh sách
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tgNames;
    }

    public void updateSoLuong(String maSach, Double sl) {
        String sql = "UPDATE [dbo].[Sach] SET "
                + " [SoLuong] = ? "
                + "WHERE [MaSach] = ?";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(2, maSach);
            stm.setDouble(1, sl);
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to update book: " + e.getMessage());
        }
    }

//    public ArrayList<Sach> search(String keyWord, Boolean trangThai) {
//        ArrayList<Sach> arr = new ArrayList<>();
//        Connection con = DBConnect.getConnection();
//
//        String sql = """
//    SELECT dbo.Sach.MaSach, dbo.Sach.TenSach, dbo.NhaXuatBan.TenNXB, dbo.TheLoai.TenTL, 
//           dbo.TacGia.TenTG, dbo.Sach.NamXuatBan, dbo.Sach.SoLuong, dbo.Sach.DonGia, 
//           dbo.Sach.HinhAnh, dbo.Sach.TrangThai 
//    FROM dbo.Sach
//    INNER JOIN dbo.NhaXuatBan ON dbo.Sach.MaNXB = dbo.NhaXuatBan.MaNXB
//    INNER JOIN dbo.TheLoai ON dbo.Sach.MaTL = dbo.TheLoai.MaTL
//    INNER JOIN dbo.TacGia ON dbo.Sach.MaTG = dbo.TacGia.MaTG
//    """;
//
//        if (keyWord.length() > 0 || trangThai != null) {
//            sql += " WHERE ";
//            // Điều kiện cho keyWord
//            if (keyWord.length() > 0) {
//                sql += "(dbo.Sach.MaSach LIKE ? OR dbo.Sach.TenSach LIKE ? OR dbo.NhaXuatBan.TenNXB LIKE ? OR dbo.TheLoai.TenTL LIKE ? OR dbo.TacGia.TenTG LIKE ?)";
//            }
//            // Điều kiện cho TrangThai
//            if (trangThai != null) {
//                if (keyWord.length() > 0) {
//                    sql += " AND ";
//                }
//                sql += "dbo.Sach.TrangThai = ?";
//            }
//        }
//
//        try (PreparedStatement stm = con.prepareStatement(sql)) {
//            int parameterIndex = 1;
//
//            // Set các tham số cho keyWord nếu có
//            if (keyWord.length() > 0) {
//                String values = "%" + keyWord + "%";
//                stm.setString(parameterIndex++, values);
//                stm.setString(parameterIndex++, values);
//                stm.setString(parameterIndex++, values);
//                stm.setString(parameterIndex++, values);
//                stm.setString(parameterIndex++, values);
//            }
//
//            // Set tham số cho TrangThai nếu có
//            if (trangThai != null) {
//                stm.setBoolean(parameterIndex, trangThai);
//            }
//
//            // Thực thi truy vấn và thêm kết quả vào danh sách
//            try (ResultSet rs = stm.executeQuery()) {
//                while (rs.next()) {
//                    Sach sa = new Sach();
//                    sa.setMaSach(rs.getString("MaSach"));
//                    sa.setTenSach(rs.getString("TenSach"));
//                    sa.setTenNXB(rs.getString("TenNXB"));
//                    sa.setTenTheLoai(rs.getString("TenTL"));
//                    sa.setTenTacGia(rs.getString("TenTG"));
//                    sa.setNamXuatBan(rs.getString("NamXuatBan"));
//                    sa.setSoLuong(rs.getInt("SoLuong"));
//                    sa.setDongia(rs.getDouble("DonGia"));
//                    sa.setHinh(rs.getString("HinhAnh"));
//                    sa.setTrangThai(rs.getBoolean("TrangThai"));
//                    arr.add(sa);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return arr;
//    }
//    public List<Sach> searchAndFilterSach(String keyword, String theLoai, String nxb, String tacGia, boolean trangThai) {
//        List<Sach> result = new ArrayList<>();
//        String sql = "SELECT s.MaSach, s.TenSach, t.TenTL, n.TenNXB, tg.TenTG, "
//                + "s.NamXuatBan, s.SoLuong, s.DonGia, s.TrangThai "
//                + "FROM Sach s "
//                + "JOIN TheLoai t ON s.MaTL = t.MaTL "
//                + "JOIN NhaXuatBan n ON s.MaNXB = n.MaNXB "
//                + "JOIN TacGia tg ON s.MaTG = tg.MaTG "
//                + "WHERE (s.MaSach LIKE ? OR s.TenSach LIKE ?) AND "
//                + "(? = '' OR t.TenTL = ?) AND "
//                + "(? = '' OR n.TenNXB = ?) AND "
//                + "(? = '' OR tg.TenTG = ?) AND "
//                + "(? = '' OR s.TrangThai = ?)";
//        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setString(1, "%" + keyword + "%");
//            stmt.setString(2, "%" + keyword + "%");
//            stmt.setString(3, theLoai);
//            stmt.setString(4, theLoai);
//            stmt.setString(5, nxb);
//            stmt.setString(6, nxb);
//            stmt.setString(7, tacGia);
//            stmt.setString(8, tacGia);
//            stmt.setBoolean(9, trangThai);
//            stmt.setBoolean(10, trangThai);
//
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                Sach sach = new Sach();
//                sach.setMaSach(rs.getString("MaSach"));
//                sach.setTenSach(rs.getString("TenSach"));
//                sach.setTenTheLoai(rs.getString("TenTL"));
//                sach.setTenNXB(rs.getString("TenNXB"));
//                sach.setTenTacGia(rs.getString("TenTG"));
//                sach.setNamXuatBan(rs.getString("NamXuatBan"));
//                sach.setSoLuong(rs.getInt("SoLuong"));
//                sach.setDongia(rs.getDouble("DonGia"));
//                sach.setTrangThai(rs.getBoolean("TrangThai"));
//                result.add(sach);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//}
    public List<Sach> searchAndFilterSach(String keyword, String theLoai, String nxb, String tacGia, Boolean trangThai) {
        List<Sach> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
                "SELECT s.MaSach, s.TenSach, t.TenTL, n.TenNXB, tg.TenTG, "
                + "s.NamXuatBan, s.SoLuong, s.DonGia, s.TrangThai "
                + "FROM Sach s "
                + "JOIN TheLoai t ON s.MaTL = t.MaTL "
                + "JOIN NhaXuatBan n ON s.MaNXB = n.MaNXB "
                + "JOIN TacGia tg ON s.MaTG = tg.MaTG "
                + "WHERE 1=1" // Điều kiện mặc định để dễ nối thêm các điều kiện
        );

        // Thêm điều kiện tìm kiếm từ keyword
        if (keyword != null && !keyword.isEmpty()) {
            sql.append(" AND (s.MaSach LIKE ? OR s.TenSach LIKE ?)");
        }

        // Thêm điều kiện tìm kiếm từ các ComboBox
        if (theLoai != null && !theLoai.isEmpty()) {
            sql.append(" AND t.TenTL = ?");
        }
        if (nxb != null && !nxb.isEmpty()) {
            sql.append(" AND n.TenNXB = ?");
        }
        if (tacGia != null && !tacGia.isEmpty()) {
            sql.append(" AND tg.TenTG = ?");
        }

        // Thêm điều kiện tìm kiếm từ trạng thái
        if (trangThai != null) {
            sql.append(" AND s.TrangThai = ?");
        }

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            int index = 1;

            // Gán giá trị cho keyword
            if (keyword != null && !keyword.isEmpty()) {
                stmt.setString(index++, "%" + keyword + "%");
                stmt.setString(index++, "%" + keyword + "%");
            }

            // Gán giá trị cho các ComboBox
            if (theLoai != null && !theLoai.isEmpty()) {
                stmt.setString(index++, theLoai);
            }
            if (nxb != null && !nxb.isEmpty()) {
                stmt.setString(index++, nxb);
            }
            if (tacGia != null && !tacGia.isEmpty()) {
                stmt.setString(index++, tacGia);
            }

            // Gán giá trị cho trạng thái
            if (trangThai != null) {
                stmt.setBoolean(index++, trangThai);
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Sach sach = new Sach();
                sach.setMaSach(rs.getString("MaSach"));
                sach.setTenSach(rs.getString("TenSach"));
                sach.setTenTheLoai(rs.getString("TenTL"));
                sach.setTenNXB(rs.getString("TenNXB"));
                sach.setTenTacGia(rs.getString("TenTG"));
                sach.setNamXuatBan(rs.getString("NamXuatBan"));
                sach.setSoLuong(rs.getInt("SoLuong"));
                sach.setDongia(rs.getDouble("DonGia"));
                sach.setTrangThai(rs.getBoolean("TrangThai"));
                result.add(sach);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
