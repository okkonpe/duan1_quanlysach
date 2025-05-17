/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import model.GiamGia;
import java.sql.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import model.DBConnect;

/**
 *
 * @author trinh thanh
 */
public class GiamGiaService {

    public ArrayList<GiamGia> getAll() {
        ArrayList<GiamGia> arr = new ArrayList<>();
        Connection con = DBConnect.getConnection();
        String sql = "SELECT [MaGiamGia]\n"
                + "      ,[TenChuongTrinh]\n"            
                + "      ,[NgayBatDau]\n"
                + "      ,[NgayKetThuc]\n"
                + "      ,[PhanTramGiamGia]\n"
                + "      ,[TrangThai]\n"
                    + "      ,[tongtienapdung]\n"
                + "  FROM [dbo].[GiamGia]";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                GiamGia gg = new GiamGia();
                gg.setMaGG(rs.getString("MaGiamGia"));
                gg.setTenChuongTrinh(rs.getString("TenChuongTrinh"));
                 gg.setTongTienApDung(rs.getInt("tongtienapdung"));
                gg.setNgayBatDau(rs.getDate("NgayBatDau"));
                gg.setNgayKetThuc(rs.getDate("NgayKetThuc"));
                gg.setPhanTramGG(rs.getInt("PhanTramGiamGia"));
                gg.setTrangThai(rs.getBoolean("TrangThai"));
                arr.add(gg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }
    

    public void addGiamGia(GiamGia gg) {
        Connection con = DBConnect.getConnection();
        String sql = "INSERT INTO [dbo].[GiamGia]\n"
                + "           ([MaGiamGia]\n"
                + "           ,[TenChuongTrinh]\n"
           
                + "           ,[NgayBatDau]\n"
                + "           ,[NgayKetThuc]\n"
                + "           ,[PhanTramGiamGia]\n"
                + "           ,[TrangThai]\n"
                + "      ,[tongtienapdung])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?)";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setObject(1, gg.getMaGG());
            stm.setObject(2, gg.getTenChuongTrinh());
            stm.setObject(3, gg.getNgayBatDau());
            stm.setObject(4, gg.getNgayKetThuc());
            stm.setObject(5, gg.getPhanTramGG());
            stm.setObject(6, gg.isTrangThai());
            stm.setObject(7, gg.getTongTienApDung());
              
            stm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateGiamGia(GiamGia gg) {
        Connection con = DBConnect.getConnection();
        String sql = "UPDATE [dbo].[GiamGia]\n"
                + "   SET \n"
                + "      [TenChuongTrinh] = ?\n"
                + "      ,[NgayBatDau] = ?\n"
                + "      ,[NgayKetThuc] = ?\n"
                + "      ,[PhanTramGiamGia] = ?\n"
                + "      ,[TrangThai] = ?\n"
                 + "      ,[tongtienapdung] = ?\n"
                + " WHERE [MaGiamGia] = ?";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setObject(7, gg.getMaGG());
            stm.setObject(1, gg.getTenChuongTrinh());      
            stm.setObject(2, gg.getNgayBatDau());
            stm.setObject(3, gg.getNgayKetThuc());
            stm.setObject(4, gg.getPhanTramGG());
            stm.setObject(5, gg.isTrangThai());
                   stm.setObject(6, gg.getTongTienApDung());
            stm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     public GiamGia getGGHD(int tongTien) {
 GiamGia gg = new GiamGia();
    this.getTTAD().sort(Comparator.reverseOrder());
       ArrayList<Integer> list= getTTAD();
       int ttad=0;
         for (Integer integer : list) {
             if (tongTien>integer) {
                 ttad =integer;
             }
         }
         
      int phanTramGiam=0;
        Connection con = DBConnect.getConnection();
        String sql = "SELECT [MaGiamGia]\n"
                + "      ,[TenChuongTrinh]\n"            
                + "      ,[NgayBatDau]\n"
                + "      ,[NgayKetThuc]\n"
                + "      ,[PhanTramGiamGia]\n"
                + "      ,[TrangThai]\n"
                + "  FROM [dbo].[GiamGia] WHERE TenChuongTrinh = ? and TongTienApDung = ? ";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, "TT");
            stm.setInt(2, ttad);
            ResultSet rs = stm.executeQuery();
             
              
            while (rs.next()) {
             gg.setMaGG(rs.getString("maGiamGia"));
             gg.setPhanTramGG(rs.getInt("PhanTramGiamGia"));
            }
          
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gg;
    }
     public int getVoucher(String maVoucher) {
     
      int phanTramGiam=0;
        Connection con = DBConnect.getConnection();
        String sql = "SELECT [MaGiamGia]\n"
                + "      ,[TenChuongTrinh]\n"                
                + "      ,[PhanTramGiamGia]\n"
                + "      ,[TrangThai]\n"
                + "  FROM [dbo].[GiamGia] WHERE magiamgia = ? and trangthai = ? ";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, maVoucher);
            stm.setInt(2, 1);
            ResultSet rs = stm.executeQuery();
             
              
            while (rs.next()) {
              
                  phanTramGiam=rs.getInt("PhanTramGiamGia");
            }
          
        } catch (Exception e) {
            e.printStackTrace();
        }
        return phanTramGiam;
    }
       public ArrayList<Integer> getTTAD() {
     ArrayList<Integer> list = new ArrayList<>();
 
        Connection con = DBConnect.getConnection();
        String sql =
                        
             
                 "    select  [TongTienApDung]\n"
                + "  FROM [dbo].[GiamGia] WHERE TenChuongTrinh = ? and trangthai = ? ";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, "TT");
            stm.setInt(2, 1);
            ResultSet rs = stm.executeQuery();
             
              
            while (rs.next()) {
              list.add(rs.getInt("TongTienApDung"));
                
            }
          
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void deleteGiamGia(String maGG) {
        Connection con = DBConnect.getConnection();
        String sql = "DELETE FROM [dbo].[GiamGia]\n"
                + "      WHERE MaGiamGia = ?";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setObject(1, maGG);
            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
