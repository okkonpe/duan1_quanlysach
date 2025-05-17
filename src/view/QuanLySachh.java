/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import model.DBConnect;
import model.Sach;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import service.QuanLySachSevicer;

/**
 *
 * @author trinh thanh
 */
public class QuanLySachh extends javax.swing.JPanel {

    ArrayList<Sach> arr = new ArrayList<>();
    DefaultTableModel model = new DefaultTableModel();
    QuanLySachSevicer sevicer = new QuanLySachSevicer();
    private ArrayList<String> selectedImagePaths = new ArrayList<>();

    /**
     * Creates new form QuanLySachh
     */
    public QuanLySachh() {
        initComponents();
        
        arr = sevicer.getAll();
        model = (DefaultTableModel) tblquanlysach.getModel();
        populateComboBoxes();
        initComboBoxes();
        viewdata(arr);

    }

    public void viewdata(List<Sach> filteredList) {

        model.setRowCount(0);
        for (Sach sa : filteredList) {
            Object[] row = new Object[]{
                sa.getMaSach(),
                sa.getTenSach(),
                sa.getTenNXB(),
                sa.getTenTheLoai(),
                sa.getTenTacGia(),
                sa.getNamXuatBan(),
                sa.getSoLuong(),
                sa.getDongia(),
                sa.isTrangThai() ? "Đang Kinh Doanh" : "Ngừng Kinh Doanh"};

            model.addRow(row);
        }
    }
     public void viewdata1() {
   ArrayList<Sach> arr = new ArrayList<>();
    QuanLySachSevicer sevicer = new QuanLySachSevicer();
       arr = sevicer.getAll();
        model.setRowCount(0);
        for (Sach sa : arr) {
            Object[] row = new Object[]{
                sa.getMaSach(),
                sa.getTenSach(),
                sa.getTenNXB(),
                sa.getTenTheLoai(),
                sa.getTenTacGia(),
                sa.getNamXuatBan(),
                sa.getSoLuong(),
                sa.getDongia(),
                sa.isTrangThai() ? "Đang Kinh Doanh" : "Ngừng Kinh Doanh"};
            model.addRow(row);
        }
    }

    public void initdata(int index) {
        Sach sa = arr.get(index);
        txtdongia.setText(String.valueOf(sa.getDongia()));
        txtmasach.setText(sa.getMaSach());
        txtnamxuatban.setText(sa.getNamXuatBan());
        txtsoluong.setText(String.valueOf(sa.getSoLuong()));
        txttensach.setText(sa.getTenSach());
        rdodangkinhdoanh.setSelected(sa.isTrangThai());
        rdongungkinhdoanh.setSelected(!sa.isTrangThai());
        cbomatacgia.setSelectedItem(sa.getMaTacGia());
    }

    public String getMaNXB(String tenNXB) {
        String maNXB = null;
        Connection con = DBConnect.getConnection();
        String sql = "SELECT MaNXB FROM NhaXuatBan WHERE TenNXB = ?";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, tenNXB);
            System.out.println("Đang truy vấn MaNXB cho: " + tenNXB);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                maNXB = rs.getString("MaNXB");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Đã lấy được MaNXB: " + maNXB); // Log để kiểm tra
        return maNXB;
    }

// Tương tự cho getMaTheLoai và getMaTacGia
    public String getMaTheLoai(String tenTL) {
        String maTL = null;
        Connection con = DBConnect.getConnection();
        String sql = "SELECT MaTL FROM TheLoai WHERE TenTL = ?";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, tenTL);
            System.out.println("Đang truy vấn MaTL cho: " + tenTL);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                maTL = rs.getString("MaTL");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Đã lấy được MaTL: " + maTL);
        return maTL;
    }

    public String getMaTacGia(String tenTG) {
        String maTG = null;
        Connection con = DBConnect.getConnection();
        String sql = "SELECT MaTG FROM TacGia WHERE TenTG = ?";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, tenTG);
            System.out.println("Đang truy vấn MaTG cho: " + tenTG);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                maTG = rs.getString("MaTG");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Đã lấy được MaTG: " + maTG);
        return maTG;
    }

    private void populateComboBoxes() {
        // Giả sử bạn có các phương thức để lấy danh sách tên nhà xuất bản, thể loại, và tác giả
        List<String> nxbList = sevicer.getAllNXBNames();
        for (String nxb : nxbList) {
            cbotennxb.addItem(nxb);
        }

        List<String> tlList = sevicer.getAllTheLoaiNames();
        for (String tl : tlList) {
            cbotentheloai.addItem(tl);
        }

        List<String> tgList = sevicer.getAllTacGiaNames();
        for (String tg : tgList) {
            cbomatacgia.addItem(tg);
        }
    }

    private void addHinhAnh() {
        JFileChooser fileChooser = new JFileChooser("C:\\Users\\trinh\\Downloads");
        fileChooser.setDialogTitle("Chọn Hình Ảnh");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Hình ảnh", "jpg", "png", "gif"));

        int userSelection = fileChooser.showOpenDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToOpen = fileChooser.getSelectedFile();
            String hinhAnhPath = fileToOpen.getAbsolutePath();
            lblhinhanh.setText(hinhAnhPath); // Lưu đường dẫn đầy đủ

            // Hiển thị hình ảnh lên JLabel
            displayImage(hinhAnhPath);
        } else {
            System.out.println("Không chọn file.");
        }
    }

    private void displayImage(String hinhAnhPath) {
        try {
            if (hinhAnhPath != null && new File(hinhAnhPath).exists()) {
                ImageIcon icon = new ImageIcon(hinhAnhPath);
                Image img = icon.getImage();
                int labelWidth = lblhinhanh.getWidth();
                int labelHeight = lblhinhanh.getHeight();
                double aspectRatio = (double) img.getWidth(null) / img.getHeight(null);

                // Điều chỉnh kích thước hình ảnh theo tỷ lệ
                if (labelWidth / (double) labelHeight > aspectRatio) {
                    labelWidth = (int) (labelHeight * aspectRatio);
                } else {
                    labelHeight = (int) (labelWidth / aspectRatio);
                }

                // Resize và đặt hình ảnh lên JLabel
                Image newImg = img.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);
                lblhinhanh.setIcon(new ImageIcon(newImg));
                lblhinhanh.setHorizontalAlignment(SwingConstants.CENTER);
                lblhinhanh.setVerticalAlignment(SwingConstants.CENTER);
            } else {
                lblhinhanh.setIcon(null);  // Xóa icon nếu hình ảnh không hợp lệ
                JOptionPane.showMessageDialog(this, "Không tìm thấy tệp hình ảnh.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải hình ảnh: " + e.getMessage());
        }
    }

    private String createQRCode(Sach sanPham) {
        try {
            String maSanPham = sanPham.getMaSach(); // Lấy mã sản phẩm
            String filePath = "D:\\dự án 1 database" + maSanPham + ".png";

            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(maSanPham, BarcodeFormat.QR_CODE, 200, 200);
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", new File(filePath).toPath());

            return filePath;
        } catch (WriterException | IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi tạo mã QR: " + e.getMessage());
            return null;
        }
    }

    private void initComboBoxes() {
        // Khởi tạo giá trị ComboBox từ cơ sở dữ liệu
        cbotheloai.addItem("");
        cbonxb.addItem("");
        cbotacgia.addItem("");

        try (Connection conn = DBConnect.getConnection()) {
            // Thể Loại
            String sqlTheLoai = "SELECT TenTL FROM TheLoai";
            try (PreparedStatement ps = conn.prepareStatement(sqlTheLoai); ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    cbotheloai.addItem(rs.getString("TenTL"));
                }
            }

            // NXB
            String sqlNXB = "SELECT TenNXB FROM NhaXuatBan";
            try (PreparedStatement ps = conn.prepareStatement(sqlNXB); ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    cbonxb.addItem(rs.getString("TenNXB"));
                }
            }

            // Tác Giả
            String sqlTacGia = "SELECT TenTG FROM TacGia";
            try (PreparedStatement ps = conn.prepareStatement(sqlTacGia); ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    cbotacgia.addItem(rs.getString("TenTG"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void searchAndFilter() {
        // Lấy giá trị từ TextField và ComboBox
        String keyword = txttimkiem.getText().trim();
        String theLoai = cbotheloai.getSelectedItem() != null ? cbotheloai.getSelectedItem().toString() : "";
        String nxb = cbonxb.getSelectedItem() != null ? cbonxb.getSelectedItem().toString() : "";
        String tacGia = cbotacgia.getSelectedItem() != null ? cbotacgia.getSelectedItem().toString() : "";

        // Lấy trạng thái từ RadioButton
        Boolean trangThai = null;
        if (rdodangkinhdoanh.isSelected()) {
            trangThai = true;
        } else if (rdongungkinhdoanh.isSelected()) {
            trangThai = false;
        }

        // Lấy danh sách kết quả tìm kiếm từ service
        List<Sach> filteredList = sevicer.searchAndFilterSach(keyword, theLoai, nxb, tacGia, trangThai);

        // Cập nhật danh sách hiển thị trên bảng
        viewdata(filteredList);
    }



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        btnsua = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        txtmasach = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        lblhinhanh = new javax.swing.JLabel();
        txtdongia = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtsoluong = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtnamxuatban = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txttensach = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cbotennxb = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        cbomatacgia = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        cbotentheloai = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblquanlysach = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        btnthem = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        rdodangkinhdoanh = new javax.swing.JRadioButton();
        rdongungkinhdoanh = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txttimkiem = new javax.swing.JTextField();
        cbotheloai = new javax.swing.JComboBox<>();
        cbotacgia = new javax.swing.JComboBox<>();
        cbonxb = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1006, 767));

        btnsua.setBackground(new java.awt.Color(255, 204, 204));
        btnsua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnsua.setForeground(new java.awt.Color(255, 255, 255));
        btnsua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img/Edit.png"))); // NOI18N
        btnsua.setText("Sửa");
        btnsua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuaActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setText("QUẢN LÝ SÁCH");

        jButton4.setBackground(new java.awt.Color(51, 255, 51));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img/Refresh.png"))); // NOI18N
        jButton4.setText("Làm Mới");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("MÃ SÁCH:");

        jButton5.setBackground(new java.awt.Color(0, 204, 51));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("XUẤT EXCEL");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        txtmasach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmasachActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("ĐƠN GIÁ");

        lblhinhanh.setText("          Chọn Ảnh");
        lblhinhanh.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        lblhinhanh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblhinhanhMouseClicked(evt);
            }
        });

        txtdongia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdongiaActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("SỐ LƯỢNG");

        txtsoluong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsoluongActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("NĂM XUẤT BẢN");

        txtnamxuatban.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnamxuatbanActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("TÊN SÁCH");

        txttensach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttensachActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("TÊN NXB");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("TÊN TÁC GIẢ");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("TÊN THỂ LOẠI");

        tblquanlysach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Sách", "Tên Sách", "Tên NXB", "Tên Thể Loại", "Tên Tác Giả", "Năm Xuất Bản", "Số Lượng", "Đơn Giá", "Trạng Thái"
            }
        ));
        tblquanlysach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblquanlysachMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblquanlysach);

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        btnthem.setBackground(new java.awt.Color(0, 102, 255));
        btnthem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnthem.setForeground(new java.awt.Color(255, 255, 255));
        btnthem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img/Add.png"))); // NOI18N
        btnthem.setText("Thêm");
        btnthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("TRẠNG THÁI:");

        buttonGroup1.add(rdodangkinhdoanh);
        rdodangkinhdoanh.setText("Đang Kinh Doanh");
        rdodangkinhdoanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdodangkinhdoanhActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdongungkinhdoanh);
        rdongungkinhdoanh.setText("Ngừng Kinh Doanh");
        rdongungkinhdoanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdongungkinhdoanhActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(51, 255, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setText("TẠO QR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txttimkiem.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txttimkiemCaretUpdate(evt);
            }
        });
        txttimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttimkiemActionPerformed(evt);
            }
        });

        cbotheloai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbotheloaiActionPerformed(evt);
            }
        });

        cbotacgia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbotacgiaActionPerformed(evt);
            }
        });

        cbonxb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbonxbActionPerformed(evt);
            }
        });

        jLabel11.setText("Tìm Kiếm:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel11)
                .addGap(32, 32, 32)
                .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90)
                .addComponent(cbotheloai, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(cbonxb, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(cbotacgia, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(83, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbotheloai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbotacgia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbonxb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(330, 330, 330)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel2)
                            .addComponent(jLabel7)
                            .addComponent(jLabel10))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtmasach, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbotennxb, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbotentheloai, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(cbomatacgia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(39, 39, 39)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel3))
                                        .addGap(32, 32, 32)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtdongia, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtnamxuatban, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel6))
                                        .addGap(32, 32, 32)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtsoluong)
                                            .addComponent(txttensach, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(46, 46, 46)
                                .addComponent(lblhinhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rdodangkinhdoanh)
                                .addGap(34, 34, 34)
                                .addComponent(rdongungkinhdoanh)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addContainerGap(908, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 100, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(btnthem)
                        .addGap(47, 47, 47)
                        .addComponent(btnsua, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(jButton4)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtmasach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbotentheloai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))))
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(cbotennxb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbomatacgia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txttensach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtsoluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtnamxuatban, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtdongia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addComponent(lblhinhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(rdongungkinhdoanh)
                    .addComponent(rdodangkinhdoanh))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsua, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel20)
                .addGap(19, 19, 19))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtmasachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmasachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmasachActionPerformed

    private void txtdongiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdongiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdongiaActionPerformed

    private void txtsoluongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsoluongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsoluongActionPerformed

    private void txtnamxuatbanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnamxuatbanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnamxuatbanActionPerformed

    private void txttensachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttensachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttensachActionPerformed

    private void lblhinhanhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblhinhanhMouseClicked
        // TODO add your handling code here:                                    
        addHinhAnh();
    }//GEN-LAST:event_lblhinhanhMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        txtdongia.setText("");
        txtmasach.setText("");
        txtnamxuatban.setText("");
        txtsoluong.setText("");
        txttensach.setText("");
       txtmasach.setEditable(true);
        txttensach.setEditable(true);
        lblhinhanh.setText("");
        buttonGroup1.clearSelection();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed

        try {
            // Kiểm tra mã sách
            String maSach = txtmasach.getText().trim();
             String tensach = txttensach.getText().trim();
            if (maSach.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mã sách không được để trống.");
                return;
            }

            for (Sach sa : arr) {
                if (sa.getMaSach().equals(maSach)) {
                    JOptionPane.showMessageDialog(this, "Mã Sách Này Đã Tồn Tại!");
                    return;
                }
            }
               for (Sach sa : arr) {
                if (sa.getTenSach().equalsIgnoreCase(tensach)) {
                    JOptionPane.showMessageDialog(this, "Tên sách đã tồn tại!");
                    return;
                }
            }

            String tenSach = txttensach.getText();
            String tenNXB = (String) cbotennxb.getSelectedItem();
            String tenTL = (String) cbotentheloai.getSelectedItem();
            String tenTG = (String) cbomatacgia.getSelectedItem();
            String namXuatBan = txtnamxuatban.getText();

            // Kiểm tra các trường trống
            if (tenSach.isEmpty() || tenNXB == null || tenTL == null || tenTG == null || namXuatBan.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin.");
                return;
            }

            int soLuong;
            Double donGia;

            // Kiểm tra và parse dữ liệu số lượng và đơn giá
            try {
                soLuong = Integer.parseInt(txtsoluong.getText());
                if (soLuong <= 0) {
                    JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0.");
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Số lượng phải là một số nguyên hợp lệ.");
                return;
            }

            try {
                donGia = Double.parseDouble(txtdongia.getText());
                if (donGia <= 0) {
                    JOptionPane.showMessageDialog(this, "Đơn giá phải lớn hơn 0.");
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Đơn giá phải là một số thực hợp lệ.");
                return;
            }

            // Kiểm tra năm xuất bản
            try {
                int nam = Integer.parseInt(namXuatBan);
                int currentYear = java.time.Year.now().getValue();
                if (nam < 1900 || nam > currentYear) {
                    JOptionPane.showMessageDialog(this, "Năm xuất bản phải nằm trong khoảng từ 1900 đến " + currentYear + ".");
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Năm xuất bản phải là một số nguyên hợp lệ.");
                return;
            }

            String hinh = lblhinhanh.getText();

            // Kiểm tra mã cho các danh mục
            String maNXB = getMaNXB(tenNXB);
            String maTL = getMaTheLoai(tenTL);
            String maTG = getMaTacGia(tenTG);

            if (maNXB == null || maTL == null || maTG == null) {
                JOptionPane.showMessageDialog(this, "Một trong các tên được chọn không hợp lệ. Vui lòng kiểm tra lại.");
                return;
            }

            // Kiểm tra trạng thái (status) radio buttons
            boolean trangThai;
            if (rdodangkinhdoanh.isSelected()) {
                trangThai = true; // "Còn Hàng"
            } else if (rdongungkinhdoanh.isSelected()) {
                trangThai = false; // "Hết Hàng"
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn trạng thái của sách.");
                return;
            }

            // Tạo đối tượng Sach
            Sach sach = new Sach();
            sach.setMaSach(maSach);
            sach.setTenSach(tenSach);
            sach.setMaNXB(maNXB);
            sach.setMaTheLoai(maTL);
            sach.setMaTacGia(maTG);
            sach.setNamXuatBan(namXuatBan);
            sach.setSoLuong(soLuong);
            sach.setDongia(donGia);
            sach.setHinh(hinh);
            sach.setTrangThai(trangThai);

            arr.add(sach);

            // Thêm sách vào database
            sevicer.addSach(sach);
            viewdata1();
            JOptionPane.showMessageDialog(this, "Thêm sách thành công!");

            // Xóa các trường nhập liệu sau khi thêm
            txttensach.setText("");
            txtnamxuatban.setText("");
            txtsoluong.setText("");
            txtdongia.setText("");
            lblhinhanh.setText("");
            rdodangkinhdoanh.setSelected(false);
            rdongungkinhdoanh.setSelected(false);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi thêm sách. Kiểm tra chi tiết trong console.");
        }
    }//GEN-LAST:event_btnthemActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Lưu file Excel");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Excel Files", "xlsx"));

        // Hiện hộp thoại lưu file
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            // Kiểm tra xem file có đuôi .xlsx không
            if (!fileToSave.getAbsolutePath().endsWith(".xlsx")) {
                fileToSave = new File(fileToSave.getAbsolutePath() + ".xlsx");
            }

            try (Workbook workbook = new XSSFWorkbook()) {
                Sheet sheet = workbook.createSheet("Sách");
                Row headerRow = sheet.createRow(0);

                // Tạo tiêu đề cột
                headerRow.createCell(0).setCellValue("Mã Sách");
                headerRow.createCell(1).setCellValue("Tên Sách");
                headerRow.createCell(2).setCellValue("Tên Nhà Xuất Bản");
                headerRow.createCell(3).setCellValue("Tên Thể Loại");
                headerRow.createCell(4).setCellValue("Tên Tác Giả");
                headerRow.createCell(5).setCellValue("Năm Xuất Bản");
                headerRow.createCell(6).setCellValue("Số Lượng");
                headerRow.createCell(7).setCellValue("Đơn Giá");
                headerRow.createCell(8).setCellValue("Hình Ảnh");
                headerRow.createCell(9).setCellValue("Trạng Thái");

                // Xuất dữ liệu từ JTable ra file Excel
                for (int i = 0; i < model.getRowCount(); i++) {
                    Row row = sheet.createRow(i + 1);
                    for (int j = 0; j < model.getColumnCount(); j++) {
                        Object cellValue = model.getValueAt(i, j);
                        row.createCell(j).setCellValue(cellValue != null ? cellValue.toString() : "");
                    }
                }

                // Lưu file Excel
                try (FileOutputStream outputStream = new FileOutputStream(fileToSave)) {
                    workbook.write(outputStream);
                    JOptionPane.showMessageDialog(this, "Xuất Excel thành công!");
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi khi xuất Excel: " + e.getMessage());
            }
        }

    }//GEN-LAST:event_jButton5ActionPerformed

    private void tblquanlysachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblquanlysachMouseClicked
        int row = tblquanlysach.getSelectedRow();
        if (row >= 0) {
            try {
                // Lấy dữ liệu từ bảng
                String maSach = tblquanlysach.getValueAt(row, 0).toString();
                String tenSach = tblquanlysach.getValueAt(row, 1).toString();
                String tenNXB = tblquanlysach.getValueAt(row, 2).toString();
                String tenTL = tblquanlysach.getValueAt(row, 3).toString();
                String tenTG = tblquanlysach.getValueAt(row, 4).toString();
                String namXuatBan = tblquanlysach.getValueAt(row, 5).toString();
                int soLuong = Integer.parseInt(tblquanlysach.getValueAt(row, 6).toString());
                txtmasach.setEditable(false);
                txttensach.setEditable(false);

                // Sửa dòng này để chuyển giá trị sang kiểu Double thay vì Integer
                Double donGia = Double.parseDouble(tblquanlysach.getValueAt(row, 7).toString());

                String trangThai = tblquanlysach.getValueAt(row, 8).toString();

                // Tìm hình ảnh dựa trên mã sách (từ danh sách gốc)
                String hinh = "";
                for (Sach sach : arr) {
                    if (sach.getMaSach().equals(maSach)) {
                        hinh = sach.getHinh();
                        break;
                    }
                }
                Sach sa = new Sach();
                // Cập nhật giao diện
                txtmasach.setText(maSach);
                txttensach.setText(tenSach);
                cbotennxb.setSelectedItem(tenNXB);
                cbotentheloai.setSelectedItem(tenTL);
                cbomatacgia.setSelectedItem(tenTG);
                txtnamxuatban.setText(namXuatBan);
                txtsoluong.setText(String.valueOf(soLuong));
                txtdongia.setText(String.valueOf(donGia));
                lblhinhanh.setText(hinh);
                if (trangThai.equals("Đang Kinh Doanh")) {
                    rdodangkinhdoanh.setSelected(true);
                } else {
                    rdongungkinhdoanh.setSelected(true);
                }

                // Hiển thị hình ảnh
                displayImage1(hinh);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lỗi khi xử lý dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void displayImage1(String imagePath) {
        if (imagePath != null && !imagePath.isEmpty()) {
            try {
                ImageIcon imageIcon = new ImageIcon(imagePath);
                lblhinhanh.setIcon(imageIcon);
                lblhinhanh.setText(""); // Xóa văn bản nếu có hình ảnh
            } catch (Exception e) {
                lblhinhanh.setText("Không thể tải hình ảnh");
            }
        } else {
            lblhinhanh.setText("Không có hình ảnh");
            lblhinhanh.setIcon(null);
        }
        //txtmasach.setEditable(false);

    }//GEN-LAST:event_tblquanlysachMouseClicked

    private void btnsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaActionPerformed
        // TODO add your handling code here:
        int index = tblquanlysach.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Chưa chọn dòng để cập nhật!");
            return;
        } else {
//            String maSach = txtmasach.getText();
//
//            // Check for duplicate 'maSach' in the list, excluding the selected row
//            for (Sach sach : arr) {
//                if (sach.getMaSach().equals(maSach) && sach != arr.get(index)) {
//                    JOptionPane.showMessageDialog(this, "Mã sách đã trùng!");
//                    return;
//                }

            Sach sach = arr.get(index);
            sach.setMaSach(txtmasach.getText());
            sach.setTenSach(txttensach.getText());
            sach.setTenTheLoai((String) cbotentheloai.getSelectedItem());
            sach.setTenNXB((String) cbotennxb.getSelectedItem());
            sach.setTenTacGia((String) cbomatacgia.getSelectedItem());
            sach.setMaNXB(getMaNXB((String) cbotennxb.getSelectedItem()));
            sach.setMaTheLoai(getMaTheLoai((String) cbotentheloai.getSelectedItem()));
            sach.setMaTacGia(getMaTacGia((String) cbomatacgia.getSelectedItem()));
            sach.setNamXuatBan(txtnamxuatban.getText());

            boolean trangThai = rdodangkinhdoanh.isSelected(); // true nếu "Còn hàng" được chọn, false nếu "Hết hàng" được chọn
            sach.setTrangThai(trangThai);
            try {
                int soLuong = Integer.parseInt(txtsoluong.getText());
                sach.setSoLuong(soLuong);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Số lượng phải là một số nguyên hợp lệ!");
                return;
            }

            try {
                Double donGia = Double.parseDouble(txtdongia.getText()); // Đơn giá kiểu int
                sach.setDongia(donGia);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Đơn giá phải là một số nguyên hợp lệ!");
                return;
            }

            // Kiểm tra xem người dùng có chọn hình ảnh mới không
            String currentImage = sach.getHinh(); // Hình ảnh hiện tại
            String newImage = lblhinhanh.getText(); // Đường dẫn hình ảnh từ JLabel
            if (newImage != null && !newImage.isEmpty() && !newImage.equals(currentImage)) {
                // Nếu có hình ảnh mới, lưu và cập nhật
                try {
                    File sourceFile = new File(newImage);
                    String destinationPath = "C:\\Users\\trinh\\Downloads" + sourceFile.getName(); // Thư mục đích
                    File destinationFile = new File(destinationPath);

                    // Copy tệp tin vào thư mục đích
                    ImageIO.write(ImageIO.read(sourceFile), "png", destinationFile);

                    sach.setHinh(destinationPath); // Cập nhật đường dẫn hình ảnh mới
                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Lỗi khi lưu hình ảnh: " + e.getMessage());
                    return;
                }
            } else {
                // Nếu không chọn hình ảnh mới, giữ nguyên hình ảnh cũ
                sach.setHinh(currentImage);
            }

            // Cập nhật 'Sach' trong cơ sở dữ liệu
            sevicer.updateSach(sach);

            // Refresh lại bảng dữ liệu
            viewdata(arr);
            JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
        }

    }//GEN-LAST:event_btnsuaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int selectedIndex = tblquanlysach.getSelectedRow(); // Bảng chứa danh sách sản phẩm

        if (selectedIndex != -1) {
            // Lấy chi tiết sản phẩm từ dòng đã chọn
            Sach sanPham = sevicer.getAll().get(selectedIndex);
            String qrCodePath = createQRCode(sanPham);
            if (qrCodePath != null) {
                JOptionPane.showMessageDialog(null, "QR Code đã được tạo tại: " + qrCodePath);
            } else {
                JOptionPane.showMessageDialog(null, "Không thể tạo mã QR. Vui lòng thử lại.");
            }
        } else {
            // Thông báo nếu không có sản phẩm nào được chọn
            JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm để tạo mã QR.");
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void cbotacgiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbotacgiaActionPerformed
        // TODO add your handling code here:
        searchAndFilter();
    }//GEN-LAST:event_cbotacgiaActionPerformed

    private void txttimkiemCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txttimkiemCaretUpdate
        searchAndFilter();

    }//GEN-LAST:event_txttimkiemCaretUpdate

    private void txttimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttimkiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttimkiemActionPerformed

    private void cbotheloaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbotheloaiActionPerformed
        // TODO add your handling code here:
        searchAndFilter();
    }//GEN-LAST:event_cbotheloaiActionPerformed

    private void cbonxbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbonxbActionPerformed
        // TODO add your handling code here:
        searchAndFilter();
    }//GEN-LAST:event_cbonxbActionPerformed

    private void rdodangkinhdoanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdodangkinhdoanhActionPerformed
searchAndFilter() ;

    }//GEN-LAST:event_rdodangkinhdoanhActionPerformed

    private void rdongungkinhdoanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdongungkinhdoanhActionPerformed
searchAndFilter();        // TODO add your handling code here:
    }//GEN-LAST:event_rdongungkinhdoanhActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnsua;
    private javax.swing.JButton btnthem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbomatacgia;
    private javax.swing.JComboBox<String> cbonxb;
    private javax.swing.JComboBox<String> cbotacgia;
    private javax.swing.JComboBox<String> cbotennxb;
    private javax.swing.JComboBox<String> cbotentheloai;
    private javax.swing.JComboBox<String> cbotheloai;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblhinhanh;
    private javax.swing.JRadioButton rdodangkinhdoanh;
    private javax.swing.JRadioButton rdongungkinhdoanh;
    private javax.swing.JTable tblquanlysach;
    private javax.swing.JTextField txtdongia;
    private javax.swing.JTextField txtmasach;
    private javax.swing.JTextField txtnamxuatban;
    private javax.swing.JTextField txtsoluong;
    private javax.swing.JTextField txttensach;
    private javax.swing.JTextField txttimkiem;
    // End of variables declaration//GEN-END:variables
}
