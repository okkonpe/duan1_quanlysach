/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import java.util.List;
import model.GioHang;
import model.Sach;

/**
 *
 * @author trinh thanh
 */
public class GioHangSevicer {

    private List<GioHang> arr = new ArrayList<>(); // Danh sách các sản phẩm trong giỏ hàng

    // Phương thức thêm sản phẩm vào giỏ hàng
    public boolean addToCart(Sach sach, int quantity) {
        if (sach.getSoLuong() < quantity) {
            return false; // Không đủ hàng trong kho
        }

        // Giảm số lượng sản phẩm trong kho
        sach.setSoLuong(sach.getSoLuong() - quantity);

        // Kiểm tra xem sản phẩm đã có trong giỏ hàng chưa
        for (GioHang item : arr) {
            if (item.getMaSach().equals(sach.getMaSach())) {
                item.setSoLuong(item.getSoLuong() + quantity);
                return true;
            }
        }

        // Nếu chưa có trong giỏ hàng, thêm sản phẩm mới vào giỏ
        GioHang cartItem = new GioHang();
        cartItem.setMaSach(sach.getMaSach());
        cartItem.setSoLuong(quantity);
//        cartItem.setGiaSach(sach.getDongia());
        cartItem.setTenSach(sach.getTenSach());
        cartItem.setTheLoai(sach.getTenTheLoai());
        cartItem.setTacGia(sach.getTenTacGia());
        cartItem.setNhaXuatBan(sach.getTenNXB());

        arr.add(cartItem);
        return true;
    }

    // Getter method to access cart items
    public List<GioHang> getCartItems() {
        return arr;
    }
    public boolean removeFromCart(String maSach) {
    for (GioHang item : arr) {
        if (item.getMaSach().equals(maSach)) {
            arr.remove(item); // Xóa sản phẩm khỏi giỏ hàng
            return true; // Xóa thành công
        }
    }
    return false; // Sản phẩm không tồn tại trong giỏ hàng
}

}
