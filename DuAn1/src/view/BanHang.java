/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import controller.TaiKhoanControolleerr;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.DBConnect;
import model.NhanVien;
import model.luuThongTinDangNhap;
import service.HoaDonSevicer;
import java.sql.*;
import java.text.NumberFormat;
import java.util.Random;
import java.text.SimpleDateFormat; // Thêm import SimpleDateFormat
import java.util.Date; // Import Date
import java.time.LocalDate; // Import LocalDate để lấy ngày hiện tại
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import model.ChiTietGiamGia;
import model.HoaDon;
import model.HoaDonChiTiet;

import model.KhachHang;
import model.LuuThongTinDangNhapSingleton;
import model.Sach;
import service.GiamGiaService;

import service.KhachHangServiec;
import service.QuanLySachSevicer;
import service.SachSevicer;

/**
 *
 * @author trinh thanh
 */
public class BanHang extends javax.swing.JPanel implements Runnable, ThreadFactory {

    ArrayList<Sach> listSach = new ArrayList<>();
    DefaultTableModel modelSP = new DefaultTableModel();
    DefaultTableModel modelHDC = new DefaultTableModel();
    DefaultTableModel modelGH = new DefaultTableModel();
    SachSevicer sevicer = new SachSevicer();
    HoaDonSevicer hdService = new HoaDonSevicer();
    ArrayList<HoaDon> listHDC = new ArrayList<>();
    QuanLySachSevicer sachService = new QuanLySachSevicer();

    private Webcam webcam = null; // Webcam API
    private WebcamPanel panel = null;
    private Executor executor = Executors.newSingleThreadExecutor();

    /**
     * Creates new form BanHang1
     */
    public BanHang() {
        initComponents();
        tblsanpham.setDefaultEditor(Object.class, null);
        tblhoadoncho.setDefaultEditor(Object.class, null);
        tblgiohang.setDefaultEditor(Object.class, null);
        modelSP = (DefaultTableModel) tblsanpham.getModel();
        modelHDC = (DefaultTableModel) tblhoadoncho.getModel();
        modelGH = (DefaultTableModel) tblgiohang.getModel();
        //        arr1 = sevicer1.getPendingInvoicesByEmployee();
        loadTableSP();
        loadTableHDC();
        loadTableGH();
        initWebcam();
    }

    public void loadTableSP() {
        listSach = sevicer.getAll();
        modelSP.setRowCount(0);
        for (Sach bh : listSach) {

            Object[] row = new Object[]{bh.getMaSach(), bh.getSoLuong(), bh.getDongia(), bh.getTenSach(), bh.getTenTheLoai(), bh.getTenTacGia(), bh.getTenNXB(), bh.isTrangThai() ? "Đang Kinh Doanh" : "Ngừng Kinh Doanh"};
            modelSP.addRow(row);
        }
        //        tableModel.setRowCount(0);

        jPanel1.setBorder(BorderFactory.createTitledBorder("Hóa Đơn Chờ"));
        jPanel4.setBorder(BorderFactory.createTitledBorder("Danh Sách Sản Phẩm"));
        jPanel3.setBorder(BorderFactory.createTitledBorder("Giỏ Hàng"));
    }

    public void createPDF(String maHoaDon, String tenKH, String sdt, LocalDate ngayTao, double tongTien, double mucGiam, double tienKhachDua, javax.swing.JTable tblGH) throws Exception {
        // Đường dẫn lưu file
        String folderPath = "C:\\Users\\admin\\Desktop\\Du an 1\\pdfHD";
        File directory = new File(folderPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        String fileName = folderPath + "\\HoaDon_" + maHoaDon + ".pdf";

        // Tạo tài liệu PDF
        PdfWriter writer = new PdfWriter(fileName);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        // Nhúng font hỗ trợ tiếng Việt
        String fontPath = "C:/Windows/Fonts/times.ttf"; // Đường dẫn font
        PdfFont font = PdfFontFactory.createFont(fontPath, PdfEncodings.IDENTITY_H);

        // Tiêu đề
        Paragraph title = new Paragraph("HÓA ĐƠN THANH TOÁN")
                .setFont(font)
                .setTextAlignment(TextAlignment.CENTER)
                .setBold()
                .setFontSize(16);
        document.add(title);

        // Thông tin khách hàng
        document.add(new Paragraph("\nMã Hóa Đơn: " + maHoaDon).setFont(font));
        document.add(new Paragraph("Tên Khách Hàng: " + tenKH).setFont(font));
        document.add(new Paragraph("Số Điện Thoại: " + sdt).setFont(font));
        document.add(new Paragraph("Ngày Tạo: " + ngayTao).setFont(font));
        document.add(new Paragraph("Tổng Tiền: " + String.format("%,.2f", tongTien) + " VND").setFont(font));
        document.add(new Paragraph("Mức Giảm: " + String.format("%,.2f", mucGiam) + " VND").setFont(font));
        document.add(new Paragraph("Tiền Khách Đưa: " + String.format("%,.2f", tienKhachDua) + " VND").setFont(font));
      

        // Tạo bảng
        Table table = new Table(new float[]{1, 3, 1, 2, 2});
        table.setWidth(UnitValue.createPercentValue(100));

        // Header
        table.addHeaderCell(new Cell().add(new Paragraph("STT").setFont(font).setBold()));
        table.addHeaderCell(new Cell().add(new Paragraph("Tên Sách").setFont(font).setBold()));
        table.addHeaderCell(new Cell().add(new Paragraph("Số Lượng").setFont(font).setBold()));
        table.addHeaderCell(new Cell().add(new Paragraph("Đơn Giá").setFont(font).setBold()));
        table.addHeaderCell(new Cell().add(new Paragraph("Thành Tiền").setFont(font).setBold()));

        // Duyệt qua JTable
        for (int i = 0; i < tblGH.getRowCount(); i++) {
            table.addCell(new Cell().add(new Paragraph(String.valueOf(i + 1)).setFont(font))); // STT
            table.addCell(new Cell().add(new Paragraph(tblGH.getValueAt(i, 4).toString()).setFont(font))); // Tên Sách
            table.addCell(new Cell().add(new Paragraph(tblGH.getValueAt(i, 1).toString()).setFont(font))); // Số Lượng
            table.addCell(new Cell().add(new Paragraph(tblGH.getValueAt(i, 2).toString()).setFont(font))); // Đơn Giá
            table.addCell(new Cell().add(new Paragraph(tblGH.getValueAt(i, 3).toString()).setFont(font))); // Thành Tiền
        }

        document.add(table);
        document.close();

        System.out.println("PDF được lưu tại: " + fileName);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblhoadoncho = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnThanhToan = new javax.swing.JButton();
        btntaohoadon = new javax.swing.JButton();
        btnhuydonhang = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txttongtien = new javax.swing.JTextField();
        txttienkhachdua = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txttienthua = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtsdt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txttenkh = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtgiamgia = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtmahd = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtVoucher = new javax.swing.JTextField();
        btnApDung = new javax.swing.JButton();
        txtMaKH = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblgiohang = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        btnxoasanpham = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoaAll = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblsanpham = new javax.swing.JTable();
        btnthemvaogiohang = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        jpnwcam = new javax.swing.JPanel();

        setPreferredSize(new java.awt.Dimension(1109, 830));
        setVerifyInputWhenFocusTarget(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setFocusable(false);

        tblhoadoncho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Hóa Đơn", "Tên Nhân Viên", "Ngày Tạo", "Trạng Thái"
            }
        ));
        tblhoadoncho.setFocusTraversalPolicyProvider(true);
        tblhoadoncho.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblhoadoncho.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblhoadoncho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblhoadonchoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblhoadoncho);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnThanhToan.setText("Thanh Toán");
        btnThanhToan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThanhToanMouseClicked(evt);
            }
        });
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        btntaohoadon.setText("Tạo Hóa Đơn");
        btntaohoadon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btntaohoadonMouseClicked(evt);
            }
        });
        btntaohoadon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntaohoadonActionPerformed(evt);
            }
        });

        btnhuydonhang.setText("Hủy Đơn Hàng");
        btnhuydonhang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnhuydonhangMouseClicked(evt);
            }
        });
        btnhuydonhang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhuydonhangActionPerformed(evt);
            }
        });

        jLabel1.setText("Tổng Tiền");

        txttongtien.setEnabled(false);
        txttongtien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttongtienActionPerformed(evt);
            }
        });

        txttienkhachdua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txttienkhachduaKeyPressed(evt);
            }
        });

        jLabel2.setText("Tiền Khách Đưa");

        txttienthua.setEnabled(false);
        txttienthua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txttienthuaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttienthuaKeyReleased(evt);
            }
        });

        jLabel3.setText("Tiền Thừa");

        txtsdt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtsdtKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsdtKeyReleased(evt);
            }
        });

        jLabel4.setText("SĐT");

        jLabel5.setText("Tên KH:");

        txtgiamgia.setEnabled(false);
        txtgiamgia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtgiamgiaKeyReleased(evt);
            }
        });

        jLabel6.setText("Mức giảm");

        jLabel8.setText("Mã HĐ:");

        txtmahd.setEnabled(false);

        jLabel7.setText("Voucher");

        txtVoucher.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtVoucherKeyReleased(evt);
            }
        });

        btnApDung.setText("Áp dụng");
        btnApDung.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnApDungMouseClicked(evt);
            }
        });
        btnApDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApDungActionPerformed(evt);
            }
        });

        txtMaKH.setEnabled(false);
        txtMaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaKHActionPerformed(evt);
            }
        });

        jLabel9.setText("Mã KH");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtmahd, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtsdt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                            .addComponent(txttongtien)
                            .addComponent(txttenkh)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel7)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel6)))
                        .addGap(47, 47, 47)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtgiamgia, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txttienthua, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txttienkhachdua)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnApDung)))))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(btntaohoadon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnhuydonhang)
                .addGap(20, 20, 20))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtmahd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttenkh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txttienkhachdua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txttienthua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtgiamgia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttongtien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(btnApDung, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btntaohoadon)
                    .addComponent(btnhuydonhang))
                .addContainerGap(113, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel3.setFocusable(false);

        tblgiohang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã", "Số Lượng", "Giá", "Thành Tiền", "Tên Sách", "Thể Loại", "Tác Giả", "Nhà Xuất Bản"
            }
        ));
        tblgiohang.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblgiohang.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblgiohang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblgiohangMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblgiohang);

        jButton5.setText("Quét QR");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        btnxoasanpham.setText("Xóa Sản Phẩm");
        btnxoasanpham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnxoasanphamMouseClicked(evt);
            }
        });
        btnxoasanpham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoasanphamActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa Sản Phẩm");
        btnSua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSuaMouseClicked(evt);
            }
        });
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoaAll.setText("Xoá Tất Cả");
        btnXoaAll.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaAllMouseClicked(evt);
            }
        });
        btnXoaAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaAllActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnxoasanpham)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSua)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnXoaAll)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(btnxoasanpham)
                    .addComponent(btnSua)
                    .addComponent(btnXoaAll))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.setFocusable(false);

        tblsanpham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Sách", "Số Lượng", "Giá Sách", "Tên Sách", "Thể Loại", "Tác Giả", "Nhà Xuất Bản", "Trạng Thái"
            }
        ));
        tblsanpham.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tblsanpham);

        btnthemvaogiohang.setText("Thêm Vào Giỏ Hàng");
        btnthemvaogiohang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemvaogiohangActionPerformed(evt);
            }
        });

        jLabel10.setText("Tìm kiếm sản phẩm");

        txtTimKiem.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemCaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(btnthemvaogiohang)
                .addGap(180, 180, 180)
                .addComponent(jLabel10)
                .addGap(30, 30, 30)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                .addGap(105, 105, 105))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnthemvaogiohang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73))
        );

        jpnwcam.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jpnwcamLayout = new javax.swing.GroupLayout(jpnwcam);
        jpnwcam.setLayout(jpnwcamLayout);
        jpnwcamLayout.setHorizontalGroup(
            jpnwcamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jpnwcamLayout.setVerticalGroup(
            jpnwcamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jpnwcam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jpnwcam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(12, 12, 12)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(149, 149, 149))
        );
    }// </editor-fold>//GEN-END:initComponents
   String maGGToanCuc = null;
    private void btntaohoadonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntaohoadonActionPerformed
        String maNV = TaiKhoanControolleerr.maNhanVien;
        if (tblhoadoncho.getRowCount() >= 10) {
            JOptionPane.showMessageDialog(this, "Khong duoc tao qua 10 hoa don !");
            return;
        }
        hdService.createHoaDon(maNV);
        loadTableHDC();
    }//GEN-LAST:event_btntaohoadonActionPerformed
    public void loadTableHDC() {
        modelHDC.setRowCount(0);
        listHDC = hdService.getHDC();
        int i = 1;
        for (HoaDon hd : listHDC) {
            Object[] row = new Object[]{i++, hd.getMaHoaDon(), hd.getTenNV(), hd.getNgayLap(),hd.isTrangThai()};
            modelHDC.addRow(row);
        }
    }
 public String phanCach(Double x) {
        NumberFormat fm = NumberFormat.getNumberInstance(Locale.US);
        return fm.format(x);
    }

    public String boPhanCach(String x) {
        String so = x.replace(",", "");
        if (so.isBlank()) {
            return "0";
        }
        try {
            Double temp = Double.valueOf(so);
            return String.format("%.0f", temp);
        } catch (NumberFormatException e) {
            return "0";
        }
    }
    public void tongTien() {
        double tong = 0;
        for (int i = 0; i < tblgiohang.getRowCount(); i++) {
            double tt = Double.parseDouble(tblgiohang.getValueAt(i, 3).toString());
            tong += tt;
        }

        txttongtien.setText(phanCach(tong));
        GiamGiaService ggService = new GiamGiaService();

        if (txttongtien.getText() != null) {
            double tongTien = Double.parseDouble(boPhanCach(txttongtien.getText()));
            int soPhanTramGiam = ggService.getGGHD((int) tongTien).getPhanTramGG();
            maGGToanCuc = ggService.getGGHD((int) tongTien).getMaGG();
            double soTienGiam = (tongTien * soPhanTramGiam) / 100;
            txtgiamgia.setText(phanCach(soTienGiam));
        }

        if (txtgiamgia.getText() != null) {
            double soTienGiam = Double.parseDouble(boPhanCach(txtgiamgia.getText()));
            tong = tong - soTienGiam;
             txttongtien.setText(phanCach(tong));
        }

    }

    private void initWebcam() {
        Dimension size = WebcamResolution.QVGA.getSize();
        webcam = Webcam.getWebcams().get(0);
        if (webcam.isOpen()) {
            webcam.close();
        }
        webcam.setViewSize(size);
        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);

        jpnwcam.setLayout(new BorderLayout());
        jpnwcam.add(panel, 0);
        executor.execute(this);
    }

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Result result = null;
            BufferedImage image = null;

            if (webcam.isOpen()) {
                image = webcam.getImage();
                if (image == null) {
                    continue;
                }
            }

            try {
                LuminanceSource source = new BufferedImageLuminanceSource(image);
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
                result = new MultiFormatReader().decode(bitmap);
            } catch (Exception e) {
                // Xử lý lỗi giải mã
            }

            if (result != null) {
                handleQRCodeScan(result.getText());
            }

        } while (true);
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "WebcamThread");
        t.setDaemon(true);
        return t;

    }
    private void btnthemvaogiohangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemvaogiohangActionPerformed

        int selectedRow = tblsanpham.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm cần thêm vào giỏ hàng!");
            return;
        }
        int rowHDC = tblhoadoncho.getSelectedRow();
        if (rowHDC == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn hoá đơn !");
            return;
        }

// Lấy thông tin từ bảng Danh Sách Sản Phẩm
        DefaultTableModel modelSanPham = (DefaultTableModel) tblsanpham.getModel();
        String maSach = (modelSanPham.getValueAt(selectedRow, 0) != null) ? modelSanPham.getValueAt(selectedRow, 0).toString() : "";
        String tenSach = (modelSanPham.getValueAt(selectedRow, 3) != null) ? modelSanPham.getValueAt(selectedRow, 3).toString() : "";
        String theLoai = (modelSanPham.getValueAt(selectedRow, 4) != null) ? modelSanPham.getValueAt(selectedRow, 4).toString() : "";
        String tacGia = (modelSanPham.getValueAt(selectedRow, 5) != null) ? modelSanPham.getValueAt(selectedRow, 5).toString() : "";
        String nhaXuatBan = (modelSanPham.getValueAt(selectedRow, 6) != null) ? modelSanPham.getValueAt(selectedRow, 6).toString() : "";
        double gia = (modelSanPham.getValueAt(selectedRow, 2) != null) ? Double.parseDouble(modelSanPham.getValueAt(selectedRow, 2).toString()) : 0.0;

// Lấy số lượng còn lại trong kho
        int availableQuantity = (modelSanPham.getValueAt(selectedRow, 1) != null)
                ? Integer.parseInt(modelSanPham.getValueAt(selectedRow, 1).toString())
                : 0;

// Mở hộp thoại yêu cầu nhập số lượng
        String quantityInput = JOptionPane.showInputDialog(null, "Nhập số lượng sản phẩm:", "Số lượng", JOptionPane.PLAIN_MESSAGE);

// Kiểm tra nếu người dùng không nhập gì hoặc nhập giá trị không hợp lệ
        int soLuong = 1; // Số lượng mặc định nếu người dùng không nhập gì
        try {
            if (quantityInput != null && !quantityInput.trim().isEmpty()) {
                soLuong = Integer.parseInt(quantityInput.trim());
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Số lượng phải là một số hợp lệ!");
            return;
        }

        if (soLuong <= 0) {
            JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn 0!");
            return;
        }

// Kiểm tra số lượng có vượt quá số lượng còn lại trong kho không
        if (soLuong > availableQuantity) {
            JOptionPane.showMessageDialog(null, "Số lượng sản phẩm trong kho không đủ.");
            return;
        }

        double thanhTien = gia * soLuong; // Thành tiền ban đầu

// Thêm thông tin vào bảng Giỏ Hàng
        LocalDate ngayTao = LocalDate.now();
        String maHD = (String) tblhoadoncho.getValueAt(rowHDC, 1);

        ArrayList<HoaDonChiTiet> listGH = hdService.getGioHangByHoaDon(maHD);
        for (HoaDonChiTiet gh : listGH) {
            if (maSach.equals(gh.getMaSach())) {
                int slCu = gh.getSoLuong();
                int slMoi = soLuong + slCu;
                double donGia = gh.getDonGia();
                double tt = slMoi * donGia;
                hdService.capNhatSoLuongSanPham(maHD, maSach, slMoi, tt);
                double slSachCu = sachService.getSlSach(maSach);
                double slSachMoi = slSachCu - soLuong;
                sachService.updateSoLuong(maSach, slSachMoi);
                loadTableSP();
                loadTableGH();
                tongTien();
                return;
            }
        }
        hdService.themSanPhamVaoGioHang(maHD, maSach, soLuong, gia, thanhTien, ngayTao);
        double slSachCu = sachService.getSlSach(maSach);
        double slSachMoi = slSachCu - soLuong;
        sachService.updateSoLuong(maSach, slSachMoi);
        loadTableSP();
        loadTableGH();

        tongTien();

    }//GEN-LAST:event_btnthemvaogiohangActionPerformed

    public void loadTableGH() {
        int rowHDC = tblhoadoncho.getSelectedRow();
        if (rowHDC==-1) {
            modelGH.setRowCount(0);
            return;
        }
        String maHD = String.valueOf(tblhoadoncho.getValueAt(rowHDC, 1));
        ArrayList<HoaDonChiTiet> listGH = hdService.getGioHangByHoaDon(maHD);

        modelGH.setRowCount(0);
        for (HoaDonChiTiet gh : listGH) {

            Object[] row = new Object[]{gh.getMaSach(), gh.getSoLuong(), gh.getDonGia(), gh.getThanhTien(), gh.getTenSach(), gh.getTheLoai(), gh.getTacGia(), gh.getNxb()};
            modelGH.addRow(row);
        }
    }
// Hàm cập nhật giỏ hàng trong cơ sở dữ liệu

   

    private void handleQRCodeScan(String maSach) {
        ArrayList<Sach> ls = new ArrayList<>();
        String sql = "SELECT    dbo.Sach.MaSach, dbo.Sach.TenSach, dbo.Sach.DonGia, dbo.TheLoai.TenTL, dbo.TacGia.TenTG, dbo.NhaXuatBan.TenNXB, dbo.Sach.SoLuong, dbo.Sach.TrangThai\n"
                + "FROM         dbo.NhaXuatBan INNER JOIN\n"
                + "                      dbo.Sach ON dbo.NhaXuatBan.MaNXB = dbo.Sach.MaNXB INNER JOIN\n"
                + "                      dbo.TacGia ON dbo.Sach.MaTG = dbo.TacGia.MaTG INNER JOIN\n"
                + "                      dbo.TheLoai ON dbo.Sach.MaTL = dbo.TheLoai.MaTL"
                + "  where MaSach = ?";

        try (Connection con = DBConnect.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setObject(1, maSach);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Sach bh = new Sach();
                    bh.setMaSach(rs.getString("MaSach"));
                    bh.setTenSach(rs.getString("TenSach"));
                    bh.setDongia(rs.getDouble("DonGia"));
                    bh.setTenTheLoai(rs.getString("TenTL"));
                    bh.setTenTacGia(rs.getString("TenTG"));
                    bh.setTenNXB(rs.getString("TenNXB"));
                    bh.setSoLuong(rs.getInt("SoLuong"));
                    bh.setTrangThai(rs.getBoolean("TrangThai"));
                    ls.add(bh);
                } else {
                    System.out.println("Không tìm thấy sản phẩm với mã: " + maSach);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Nhập số lượng sản phẩm từ người dùng
        String input = javax.swing.JOptionPane.showInputDialog(null, "Nhập số lượng sản phẩm để thêm vào giỏ hàng:", "Số lượng sản phẩm", javax.swing.JOptionPane.QUESTION_MESSAGE);

        // Kiểm tra đầu vào
        if (input == null || input.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Số lượng không thể để trống.");
            return;
        }

        try {
            int soLuongg = Integer.parseInt(input.trim());  // Loại bỏ khoảng trắng trước và sau

            // Kiểm tra nếu số lượng nhập vào hợp lệ
            if (soLuongg <= 0) {
                JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn 0.");
            } else {
                // Gọi phương thức để thêm sản phẩm vào hóa đơn
                try {
                    addAllProductsToOrder(ls, soLuongg);
                    loadTableGH(); // Cập nhật bảng giỏ hàng
                    loadTableSP(); // Cập nhật bảng sản phẩm
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (NumberFormatException ex) {
            // Nếu không phải số nguyên hợp lệ
            JOptionPane.showMessageDialog(null, "Vui lòng nhập một số hợp lệ.");
        }
    }

    private void addAllProductsToOrder(ArrayList<Sach> ls, int soLuongg) {
        for (Sach sanPham : ls) {
            String maSanPham = sanPham.getMaSach();
            String tenSanPham = sanPham.getTenSach();
            Double giaBan = sanPham.getDongia(); // Giá bán kiểu Double

            if (soLuongg > 0) {
                // Thêm sản phẩm vào chi tiết hóa đơn và cập nhật thanh toán
                addProductToOrderDetailAndUpdatePayment(maSanPham, tenSanPham, giaBan, soLuongg);
                updateProductQuantity(maSanPham, soLuongg); // Cập nhật số lượng trong cơ sở dữ liệu 
                try {
                    loadTableGH();
                    System.out.println("Giỏ hàng đã được cập nhật.");
                } catch (Exception e) {
                    System.err.println("Lỗi khi tải bảng giỏ hàng: " + e.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Số lượng sản phẩm phải lớn hơn 0.");
            }
        }
    }

    private void addProductToOrderDetailAndUpdatePayment(String maSanPham, String tenSanPham, Double giaBan, int soLuongg) {
        // Lấy mã hóa đơn (có thể cần tạo mới hoặc lấy mã hóa đơn hiện tại)
        int rowHDC = tblhoadoncho.getSelectedRow();
        if (rowHDC < 0) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn hóa đơn chờ trước khi thêm sản phẩm.");
            return;
        }
        String maHoaDon = tblhoadoncho.getValueAt(rowHDC, 1).toString();

        // Kiểm tra xem sản phẩm đã tồn tại trong chi tiết hóa đơn hay chưa
        String checkExistSQL = "SELECT SoLuong, DonGia FROM ChiTietHoaDon WHERE MaHoaDon = ? AND MaSach = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement checkStmt = con.prepareStatement(checkExistSQL)) {
            checkStmt.setString(1, maHoaDon);
            checkStmt.setString(2, maSanPham);
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next()) {
                    // Nếu sản phẩm đã tồn tại, cập nhật số lượng và thành tiền
                    int existingQuantity = rs.getInt("SoLuong");
                    int updatedQuantity = existingQuantity + soLuongg;
                    double updatedTotalPrice = updatedQuantity * giaBan;

                    String updateChiTietHoaDonSQL = "UPDATE ChiTietHoaDon SET SoLuong = ?, ThanhTien = ? WHERE MaHoaDon = ? AND MaSach = ?";
                    try (PreparedStatement updateStmt = con.prepareStatement(updateChiTietHoaDonSQL)) {
                        updateStmt.setInt(1, updatedQuantity);
                        updateStmt.setDouble(2, updatedTotalPrice);
                        updateStmt.setString(3, maHoaDon);
                        updateStmt.setString(4, maSanPham);
                        updateStmt.executeUpdate();
                        System.out.println("Đã cập nhật số lượng và thành tiền cho sản phẩm trong chi tiết hóa đơn.");
                    }
                } else {
                    // Nếu sản phẩm chưa tồn tại, thêm sản phẩm mới vào chi tiết hóa đơn
                    String insertChiTietHoaDonSQL = "INSERT INTO ChiTietHoaDon (MaHoaDon, MaSach, SoLuong, DonGia, ThanhTien, NgayTao, TrangThai) "
                            + "VALUES (?, ?, ?, ?, ?, GETDATE(), 1)";  // TrangThai = 1 (Còn hiệu lực)
                    try (PreparedStatement insertStmt = con.prepareStatement(insertChiTietHoaDonSQL)) {
                        insertStmt.setString(1, maHoaDon);
                        insertStmt.setString(2, maSanPham);
                        insertStmt.setInt(3, soLuongg);
                        insertStmt.setDouble(4, giaBan);
                        insertStmt.setDouble(5, giaBan * soLuongg);
                        insertStmt.executeUpdate();
                        System.out.println("Đã thêm sản phẩm mới vào chi tiết hóa đơn.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Cập nhật tổng thanh toán cho hóa đơn
        String updateHoaDonSQL = "UPDATE HoaDon SET ThanhTien = ThanhTien + ? WHERE MaHoaDon = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement stmt = con.prepareStatement(updateHoaDonSQL)) {
            stmt.setDouble(1, giaBan * soLuongg);  // Cộng dồn tiền vào hóa đơn
            stmt.setString(2, maHoaDon);
            stmt.executeUpdate();
            System.out.println("Đã cập nhật thanh toán cho hóa đơn.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

// Cập nhật số lượng tồn kho trong cơ sở dữ liệu
    private void updateProductQuantity(String maSanPham, int soLuong) {
        String sql = "UPDATE Sach SET SoLuong = SoLuong - ? WHERE MaSach = ?"; // Giữ nguyên maSanPham là chuỗi
        try (Connection con = DBConnect.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, soLuong);  // Giảm số lượng
            stmt.setString(2, maSanPham);  // Truyền vào maSanPham là chuỗi

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Cập nhật số lượng tồn kho thành công.");
            } else {
                System.out.println("Không thể cập nhật số lượng tồn kho.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void txttongtienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttongtienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttongtienActionPerformed

    private void btnxoasanphamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoasanphamActionPerformed
        int rowGH = tblgiohang.getSelectedRow();
        int rowHDC = tblhoadoncho.getSelectedRow();
        if (rowGH == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm để xóa!");
            return;
        }
        String maHD = String.valueOf(tblhoadoncho.getValueAt(rowHDC, 1));
        String maSach = String.valueOf(tblgiohang.getValueAt(rowGH, 0));
        hdService.xoaSanPhamKhoiGioHang(maHD, maSach);
        double slSachCu = sachService.getSlSach(maSach);
        int slSachGH = Integer.parseInt(tblgiohang.getValueAt(rowGH, 1).toString());
        double slSachMoi = slSachCu + slSachGH;
        sachService.updateSoLuong(maSach, slSachMoi);
        loadTableGH();
        loadTableSP();
        tongTien();

    }//GEN-LAST:event_btnxoasanphamActionPerformed

    private void btntaohoadonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btntaohoadonMouseClicked

    }//GEN-LAST:event_btntaohoadonMouseClicked

    private void btnhuydonhangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnhuydonhangMouseClicked


    }//GEN-LAST:event_btnhuydonhangMouseClicked

    private void tblhoadonchoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblhoadonchoMouseClicked
        int selectedRow = tblhoadoncho.getSelectedRow();
        if (selectedRow != -1) {
            // Lấy mã hóa đơn từ bảng
            String maHoaDon = (String) tblhoadoncho.getValueAt(selectedRow, 1);
            txtmahd.setText(maHoaDon);

            // Hiển thị giỏ hàng cho mã hóa đơn đã chọn
            loadTableGH();
        }
        txtVoucher.setText("");
        txtsdt.setText("");
        txttenkh.setText("");
        txttienkhachdua.setText("");
        tongTien();


    }//GEN-LAST:event_tblhoadonchoMouseClicked

    private void btnxoasanphamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnxoasanphamMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_btnxoasanphamMouseClicked

    private void txtgiamgiaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtgiamgiaKeyReleased
        // TODO add your handling code here:
        int selectedRow = tblgiohang.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm trong giỏ hàng để áp dụng giảm giá!");
            return;
        }

        // Lấy giá trị giảm giá từ JTextField
        double giamGia;
        try {
            giamGia = Double.parseDouble(txtgiamgia.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Giảm giá phải là một số hợp lệ!");
            return;
        }

        // Lấy giá trị từ bảng
        DefaultTableModel model = (DefaultTableModel) tblgiohang.getModel();
        double gia = Double.parseDouble(model.getValueAt(selectedRow, 6).toString()); // Giá
        int soLuong = Integer.parseInt(model.getValueAt(selectedRow, 5).toString()); // Số lượng

        // Tính toán thành tiền sau giảm giá
        double thanhTien = (gia * soLuong) * (1 - giamGia / 100);

        // Cập nhật lại giá trị trong bảng
        model.setValueAt(thanhTien, selectedRow, 7);
    }//GEN-LAST:event_txtgiamgiaKeyReleased

    private void btnThanhToanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThanhToanMouseClicked


    }//GEN-LAST:event_btnThanhToanMouseClicked
    int rowGH;
    int slMClickGH;
    private void tblgiohangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblgiohangMouseClicked
        rowGH = tblgiohang.getSelectedRow();
        String maSach = String.valueOf(tblgiohang.getValueAt(rowGH, 0));
        for (int i = 0; i < tblsanpham.getRowCount(); i++) {
            if (maSach.equals(String.valueOf(tblsanpham.getValueAt(i, 0)))) {
                slMClickGH = Integer.parseInt(tblsanpham.getValueAt(i, 1).toString());
            }
        }

    }//GEN-LAST:event_tblgiohangMouseClicked

    private void txttienthuaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttienthuaKeyPressed

    }//GEN-LAST:event_txttienthuaKeyPressed

    private void txttienthuaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttienthuaKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_txttienthuaKeyReleased
    private String generateMaKH() {
        String prefix = "KH";
        String number = String.format("%04d", (int) (Math.random() * 101));
        String suffix = getRandomTwoLetters();
        return prefix + number + suffix;
    }

    private String getRandomTwoLetters() {
        Random random = new Random();
        char firstLetter = (char) (random.nextInt(26) + 'A');
        char secondLetter = (char) (random.nextInt(26) + 'A');
        return "" + firstLetter + secondLetter;
    }

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
//        try {
//            int rowHDC = tblhoadoncho.getSelectedRow();
//            KhachHang kh = new KhachHang();
//            KhachHangServiec khService = new KhachHangServiec();
//            ArrayList<KhachHang> list = khService.getAll();
//
//            double tt = Double.parseDouble(txttongtien.getText()); // Tổng tiền
//            String maKH = txtMaKH.getText();
//            String maGG = maGGToanCuc;
//
//            if (!txtVoucher.getText().isEmpty()) {
//                maGG = txtVoucher.getText();
//            }
//
//            double giamGia = Double.parseDouble(txtgiamgia.getText());
//            String maKHNew = generateMaKH();
//
//            if (!txtsdt.getText().isEmpty()) {
//                if (txtMaKH.getText().isEmpty()) {
//                    for (KhachHang listKH : list) {
//                        if (maKHNew != null && maKHNew.equalsIgnoreCase(listKH.getMaKhachHang())) {
//                            maKHNew = generateMaKH();
//                        }
//                    }
//                    kh.setMaKhachHang(maKHNew);
//                    kh.setsĐt(txtsdt.getText());
//                    kh.setTenKh(txttenkh.getText());
//                    khService.addKhachHang(kh);
//                    hdService.thanhToan(txtmahd.getText(), 1, tt, maKHNew, maGG, giamGia);
//                } else {
//                    hdService.thanhToan(txtmahd.getText(), 1, tt, maKH, maGG, giamGia);
//                }
//            } else {
//                hdService.thanhToan(txtmahd.getText(), 1, tt, null, maGG, giamGia);
//            }
//
//            loadTableHDC();
//            modelGH.setRowCount(0);
//            
//            // Lưu thông tin trước khi xóa trường nhập
//            String maHoaDon = txtmahd.getText();
//            String tenKH = txttenkh.getText();
//            String sdt = txtsdt.getText();
//            LocalDate ngayTao = LocalDate.now();
//
//            txtmahd.setText("");
//            txttongtien.setText("");
//            txtgiamgia.setText("");
//            txtMaKH.setText("");
//            txtVoucher.setText("");
//
//            // Tạo PDF
//            createPDF(maHoaDon, tenKH, sdt, ngayTao, tt, tblgiohang);
//            JOptionPane.showMessageDialog(this, "PDF xuất thành công!");
//            resetFormSauThanhToan();
//        } catch (Exception e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(this, "Lỗi khi xuất file PDF: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
//        }
//        
//    }  try {
        // Kiểm tra dữ liệu nhập

        // Kiểm tra dữ liệu nhập
        if (txtmahd.getText().isEmpty() ) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hoá đơn!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        double ttCheck = Double.parseDouble(boPhanCach(txttongtien.getText()));
         if (ttCheck==0.0 ) {
            JOptionPane.showMessageDialog(this, "Giỏ hàng trống!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
          if (txttienkhachdua.getText().isEmpty() ) {
            JOptionPane.showMessageDialog(this, "Nhập số tiền khách đưa!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int rowHDC = tblhoadoncho.getSelectedRow();
        KhachHang kh = new KhachHang();
        KhachHangServiec khService = new KhachHangServiec();
        ArrayList<KhachHang> list = khService.getAll();

        double tt = Double.parseDouble(boPhanCach(txttongtien.getText()));
        String maKH = txtMaKH.getText();
        String maGG = txtVoucher.getText().isEmpty() ? maGGToanCuc : txtVoucher.getText();
        double giamGia = Double.parseDouble(boPhanCach(txtgiamgia.getText()));
        String maKHNew = generateMaKH();

        if (!txtsdt.getText().isEmpty()) {
            if (txtMaKH.getText().isEmpty()) {
                Set<String> existingMaKH = list.stream()
                        .map(KhachHang::getMaKhachHang)
                        .collect(Collectors.toSet());
                while (existingMaKH.contains(maKHNew)) {
                    maKHNew = generateMaKH();
                }
                kh.setMaKhachHang(maKHNew);
                kh.setsĐt(txtsdt.getText());
                kh.setTenKh(txttenkh.getText());
                khService.addKhachHang(kh);
                hdService.thanhToan(txtmahd.getText(), "Đã thanh toán", tt, maKHNew, maGG, giamGia);
                 loadTableHDC();
            } else {
                hdService.thanhToan(txtmahd.getText(), "Đã thanh toán", tt, maKH, maGG, giamGia);
                 loadTableHDC();
            }
        } else {
            hdService.thanhToan(txtmahd.getText(), "Đã thanh toán", tt, null, maGG, giamGia);

            loadTableHDC();
        }
        try {
            // Lưu thông tin trước khi xóa trường nhập
            String maHoaDon = txtmahd.getText().trim();
            String tenKH = txttenkh.getText().trim();
            String sdt = txtsdt.getText().trim();
            LocalDate ngayTao = LocalDate.now();
            double tongTien = Double.parseDouble(boPhanCach(txttongtien.getText()).trim()); // Tổng tiền từ giao diện
            double mucGiam = Double.parseDouble(boPhanCach(txtgiamgia.getText()).trim()); // Mức giảm giá từ giao diện
            
           
                    double tienKhachDua = Double.parseDouble(txttienkhachdua.getText().trim()); 
            
       // Tiền khách đưa từ giao diện
//
//            // Tính tiền thừa
//            double tienThua = tienKhachDua - (tongTien - mucGiam);

            // Kiểm tra tính hợp lệ của số tiền khách đưa
//            if (tienThua < 0) {
//                JOptionPane.showMessageDialog(this, "Số tiền khách đưa không đủ để thanh toán!", "Lỗi", JOptionPane.ERROR_MESSAGE);
//                return;
//            }

            // Tạo PDF
            createPDF(maHoaDon, tenKH, sdt, ngayTao, tongTien, mucGiam,tienKhachDua, tblgiohang);
            JOptionPane.showMessageDialog(this, "PDF xuất thành công!");

            // Reset form và giỏ hàng
            resetFormSauThanhToan();
            modelGH.setRowCount(0);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng số cho các trường liên quan!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi xuất file PDF: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void resetFormSauThanhToan() {
        txttenkh.setText("");
        txtsdt.setText("");
        txttongtien.setText("0");
        txttienkhachdua.setText("");
        txttienthua.setText("");
        txtmahd.setText(""); // Nếu cần xóa mã hóa đơn
        txtgiamgia.setText("");
        txtMaKH.setText("");
        txtVoucher.setText("");

    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnSuaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSuaMouseClicked

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        int rowGH = tblgiohang.getSelectedRow();
        int rowHDC = tblhoadoncho.getSelectedRow();
        int rowSP = tblsanpham.getSelectedRow();
        if (rowGH == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm để sửa!");
            return;
        }
        int availableQuantity = slMClickGH;
// Mở hộp thoại yêu cầu nhập số lượng
        String quantityInput = JOptionPane.showInputDialog(null, "Nhập số lượng sản phẩm:", "Số lượng", JOptionPane.PLAIN_MESSAGE);
        int soLuong = 1; // Số lượng mặc định nếu người dùng không nhập gì
        try {
            if (quantityInput != null && !quantityInput.trim().isEmpty()) {
                soLuong = Integer.parseInt(quantityInput.trim());
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Số lượng phải là một số hợp lệ!");
            return;
        }

        if (soLuong <= 0) {
            JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn 0!");
            return;
        }
        if (soLuong > availableQuantity) {
            JOptionPane.showMessageDialog(null, "Số lượng sản phẩm trong kho không đủ.");
            return;
        }
        String maHD = String.valueOf(tblhoadoncho.getValueAt(rowHDC, 1));
        String maSach = String.valueOf(tblgiohang.getValueAt(rowGH, 0));

        int slMoi = soLuong;
        double donGia = Double.parseDouble(tblgiohang.getValueAt(rowGH, 2).toString());
        double tt = slMoi * donGia;
        hdService.capNhatSoLuongSanPham(maHD, maSach, slMoi, tt);
        double slSachCu = sachService.getSlSach(maSach);
        int slSachGH = Integer.parseInt(tblgiohang.getValueAt(rowGH, 1).toString());
        double slSachMoi = slSachCu + slSachGH - soLuong;
        sachService.updateSoLuong(maSach, slSachMoi);
        loadTableGH();
        loadTableSP();
        tongTien();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaAllMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaAllMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoaAllMouseClicked

    private void btnXoaAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaAllActionPerformed

        int rowHDC = tblhoadoncho.getSelectedRow();
        String maHD = String.valueOf(tblhoadoncho.getValueAt(rowHDC, 1));

        for (int i = 0; i < tblgiohang.getRowCount(); i++) {
            String maSach = String.valueOf(tblgiohang.getValueAt(i, 0));
            double slSachCu = sachService.getSlSach(maSach);
            int slSachGH = Integer.parseInt(tblgiohang.getValueAt(i, 1).toString());
            double slSachMoi = slSachCu + slSachGH;
            hdService.xoaSanPhamKhoiGioHang(maHD, maSach);
            sachService.updateSoLuong(maSach, slSachMoi);
        }

        loadTableGH();
        loadTableSP();
        tongTien();
    }//GEN-LAST:event_btnXoaAllActionPerformed

    private void btnhuydonhangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhuydonhangActionPerformed

        int rowHDC = tblhoadoncho.getSelectedRow();
        if (rowHDC == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn hoá đơn cần huỷ!");
            return;
        }
        String maHD = String.valueOf(tblhoadoncho.getValueAt(rowHDC, 1));
        for (int i = 0; i < tblgiohang.getRowCount(); i++) {
            String maSach = String.valueOf(tblgiohang.getValueAt(i, 0));
            double slSachCu = sachService.getSlSach(maSach);
            int slSachGH = Integer.parseInt(tblgiohang.getValueAt(i, 1).toString());
            double slSachMoi = slSachCu + slSachGH;
//            hdService.xoaSanPhamKhoiGioHang(maHD, maSach);
            sachService.updateSoLuong(maSach, slSachMoi);
        }

        
        hdService.huyDonHang(maHD,"Huỷ");
        loadTableSP();
        loadTableHDC();
        loadTableGH();
        txttongtien.setText("");
        txtVoucher.setText("");
        txtsdt.setText("");
        txttenkh.setText("");
        txtgiamgia.setText("");
        txttienkhachdua.setText("");
    }//GEN-LAST:event_btnhuydonhangActionPerformed

    private void txttienkhachduaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttienkhachduaKeyPressed
        try {
            char c = evt.getKeyChar();
            if ((Character.isDigit(c) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_ENTER))) {
                String txt = txttienkhachdua.getText().replaceAll("[^\\d]", "");
                txttienkhachdua.setText(txt);
                Double tongTien = Double.valueOf((txttongtien.getText()));
                Double tienKhach = Double.valueOf((txttienkhachdua.getText()));
                Double tienThua = tienKhach - tongTien;
                txttienthua.setText(String.valueOf(tienThua));
            }
        } catch (Exception e) {

            System.out.println("nhap so");
        }
    }//GEN-LAST:event_txttienkhachduaKeyPressed

    private void txtVoucherKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVoucherKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVoucherKeyReleased

    private void btnApDungMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnApDungMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnApDungMouseClicked

    private void btnApDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApDungActionPerformed
        GiamGiaService ggService = new GiamGiaService();

        if (txtVoucher.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Hãy nhập Voucher!");
            return;
        }
        int gg = ggService.getVoucher(txtVoucher.getText());
        if (gg == 0) {
            JOptionPane.showMessageDialog(this, "vOUCHER không còn áp dụng!");
            return;
        } else {
            double tong = 0;
            for (int i = 0; i < tblgiohang.getRowCount(); i++) {
                double tt = Double.parseDouble(tblgiohang.getValueAt(i, 3).toString());
                tong += tt;
            }
            txtgiamgia.setText(phanCach((gg * tong) / 100));
            double giamGia = Double.parseDouble(boPhanCach(txtgiamgia.getText()));
            double tongSauGG = tong - giamGia;
            txttongtien.setText(phanCach(tongSauGG));
        }
    }//GEN-LAST:event_btnApDungActionPerformed

    private void txtMaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaKHActionPerformed

    private void txtsdtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsdtKeyReleased
        KhachHangServiec khService = new KhachHangServiec();
        try {
            char c = evt.getKeyChar();
            if ((Character.isDigit(c) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_ENTER))) {
                String txt = txtsdt.getText().replaceAll("[^\\d]", "");
                txtsdt.setText(txt);

                KhachHang kh = khService.getKHbySDT(txtsdt.getText());
                if (kh != null) {
                    String maKH = kh.getMaKhachHang();
                    String tenKH = kh.getTenKh();
                    if (maKH != null) {
                        txtMaKH.setText(maKH);
                        txttenkh.setText(tenKH);
                    } else {
                        txtMaKH.setText("");
                        txttenkh.setText("");
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("nhap so");
            JOptionPane.showMessageDialog(this, "Nhap so !");
        }
    }//GEN-LAST:event_txtsdtKeyReleased

    private void txtsdtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsdtKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsdtKeyPressed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        try {
            if (webcam.isOpen()) {
                webcam.close();
            } else {
                jpnwcam.removeAll();
                jpnwcam.revalidate();
                jpnwcam.repaint();
                initWebcam();
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void txtTimKiemCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemCaretUpdate
        String keyWord = txtTimKiem.getText().trim();

        // Nếu không có từ khóa, chỉ cần hiển thị tất cả các sản phẩm
        if (keyWord.isEmpty()) {
            loadTableSP(); // Gọi lại phương thức để tải tất cả dữ liệu vào bảng
            return;
        }
        // Lọc dữ liệu từ listSach dựa trên từ khóa
        ArrayList<Sach> filteredList = new ArrayList<>();
        for (Sach sach : listSach) {
            // Kiểm tra nếu từ khóa tìm kiếm có xuất hiện trong các trường dữ liệu của sách
            boolean match = false;
            if (sach.getMaSach().contains(keyWord)
                    || sach.getTenSach().contains(keyWord)
                    || sach.getTenTheLoai().contains(keyWord)
                    || sach.getTenTacGia().contains(keyWord)
                    || sach.getTenNXB().contains(keyWord)) {
                match = true;
            }

            // Kiểm tra nếu từ khóa tìm kiếm có xuất hiện trong trạng thái
            // Kiểm tra trạng thái "Đang Kinh Doanh" hoặc "Ngừng Kinh Doanh"
            if (keyWord.equalsIgnoreCase("Đang Kinh Doanh") && sach.isTrangThai()) {
                match = true;
            } else if (keyWord.equalsIgnoreCase("Ngừng Kinh Doanh") && !sach.isTrangThai()) {
                match = true;
            }

            // Nếu có ít nhất một trường trùng khớp, thêm vào danh sách kết quả
            if (match) {
                filteredList.add(sach);
            }
        }

        // Cập nhật bảng với danh sách tìm kiếm
        modelSP.setRowCount(0); // Xóa dữ liệu cũ trong bảng
        for (Sach sach : filteredList) {
            Object[] row = new Object[]{
                sach.getMaSach(),
                sach.getSoLuong(),
                sach.getDongia(),
                sach.getTenSach(),
                sach.getTenTheLoai(),
                sach.getTenTacGia(),
                sach.getTenNXB(),
                sach.isTrangThai() ? "Đang Kinh Doanh" : "Ngừng Kinh Doanh"
            };
            modelSP.addRow(row); // Thêm các dòng vào bảng
        }
    }//GEN-LAST:event_txtTimKiemCaretUpdate


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApDung;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnXoaAll;
    private javax.swing.JButton btnhuydonhang;
    private javax.swing.JButton btntaohoadon;
    private javax.swing.JButton btnthemvaogiohang;
    private javax.swing.JButton btnxoasanpham;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel jpnwcam;
    private javax.swing.JTable tblgiohang;
    private javax.swing.JTable tblhoadoncho;
    private javax.swing.JTable tblsanpham;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtVoucher;
    private javax.swing.JTextField txtgiamgia;
    private javax.swing.JTextField txtmahd;
    private javax.swing.JTextField txtsdt;
    private javax.swing.JTextField txttenkh;
    private javax.swing.JTextField txttienkhachdua;
    private javax.swing.JTextField txttienthua;
    private javax.swing.JTextField txttongtien;
    // End of variables declaration//GEN-END:variables
}
