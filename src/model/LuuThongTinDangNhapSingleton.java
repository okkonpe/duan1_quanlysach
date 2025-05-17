/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author trinh thanh
 */
public class LuuThongTinDangNhapSingleton {
    private static LuuThongTinDangNhapSingleton instance;
    private luuThongTinDangNhap luuThongTinDangNhap;

    private LuuThongTinDangNhapSingleton() {
        luuThongTinDangNhap = new luuThongTinDangNhap(); // Khởi tạo đối tượng lưu thông tin đăng nhập
    }

    // Phương thức lấy instance của Singleton
    public static LuuThongTinDangNhapSingleton getInstance() {
        if (instance == null) {
            instance = new LuuThongTinDangNhapSingleton();
        }
        return instance;
    }

    // Lấy đối tượng luuThongTinDangNhap
    public luuThongTinDangNhap getLuuThongTinDangNhap() {
        return luuThongTinDangNhap;
    }

    // Phương thức để lưu thông tin đăng nhập
    public void setThongTinDangNhap(luuThongTinDangNhap luuThongTin) {
        this.luuThongTinDangNhap = luuThongTin;
    }
}
