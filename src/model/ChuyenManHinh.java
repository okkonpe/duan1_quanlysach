/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import view.BanHang;
import view.BanHang;
import view.QuanLyHoaDon;
import view.QuanLyGiamGia;
import view.QuanLyKhachHang;
import view.QuanLyNhanVien;
import view.QuanLySachh;
import view.QuanLyTacGia;
import view.QuanLyTheLoai;
import view.QuanLyThongKe;
import view.QuanNhaXuatBan;
import view.QuanThanhCon;

/**
 *
 * @author trinh thanh
 */
public class ChuyenManHinh {

    private JPanel root;
    private String kindSelected = "";
    private List<DanhMucBean> danhMucBeans = null;

    public ChuyenManHinh(JPanel jpnRoot) {
        this.root = jpnRoot;

    }

    public void setView(JPanel jpnItem, JLabel jlbItem) {
        kindSelected = "TrangChuChinh";

        root.removeAll();
        root.setLayout(new BorderLayout());
        root.add(new BanHang());
        root.validate();
        root.repaint();

    }

    public void setView1(JPanel jpnItem, JLabel jlbItem) {
        kindSelected = "TrangChuChinh";

        root.removeAll();
        root.setLayout(new BorderLayout());
        root.add(new QuanLySachh());
        root.validate();
        root.repaint();

    }

    public void setEvent(List<DanhMucBean> danhMucBeans) {
        this.danhMucBeans = danhMucBeans;
        for (DanhMucBean item : danhMucBeans) {
            item.getJlb().addMouseListener(new LabelEvent(item.getKind(), item.getJpn(), item.getJlb()));
        }
    }

    class LabelEvent implements MouseListener {

        private JPanel node;
        private String kind;

        private JPanel jpnItem;
        private JLabel jlbItem;

        public LabelEvent(String kind, JPanel jpnItem, JLabel jpbItem) {
            this.kind = kind;
            this.jpnItem = jpnItem;
            this.jlbItem = jpbItem;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            switch (kind) {
                case "TrangChu":
                    node = new BanHang();
                    break;
                case "QuanLySachh":
                    node = new QuanLySachh();
                    break;
                case "QuanLyNhanVien":
                    node = new QuanLyNhanVien();
                    break;
                case "QuanLyKhachHang":
                    node = new QuanLyKhachHang();
                    break;
                case "GiamGia":
                    node = new QuanLyGiamGia();
                    break;
                case "QuanLyHoaDon":
                    node = new QuanLyHoaDon();
                    break;
                case "QuanThanhCon":
                    node = new QuanThanhCon();
                    break;
                case "QuanLyNhaXuatBan":
                    node = new QuanNhaXuatBan();
                    break;
                case "QuanLyTheLoai":
                    node = new QuanLyTheLoai();
                    break;
                case "QuanLyTacGia":
                    node = new QuanLyTacGia();
                    break;
                case "QuanLyThongKe":
                    node = new QuanLyThongKe();
                    break;
                default:

            }

            root.removeAll();
            root.setLayout(new BorderLayout());
            root.add(node);
            root.validate();
            root.repaint();

        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

    }
}
