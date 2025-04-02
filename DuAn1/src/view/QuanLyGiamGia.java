/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.GiamGia;
import service.GiamGiaService;
import java.sql.*;
import model.DBConnect;

/**
 *
 * @author trinh thanh
 */
public class QuanLyGiamGia extends javax.swing.JPanel {

    ArrayList<model.GiamGia> arr = new ArrayList<>();
    DefaultTableModel model = new DefaultTableModel();
    GiamGiaService service = new GiamGiaService();

    /**
     * Creates new form GiamGia
     */
    public QuanLyGiamGia() {
        initComponents();
        arr = service.getAll();
        model = (DefaultTableModel) tblgiamgia.getModel();
        viewdata();
    }

    public void viewdata() {
        model.setRowCount(0);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        for (model.GiamGia gg : arr) {
            Object[] row = new Object[]{gg.getMaGG(), gg.getTenChuongTrinh(), gg.getNgayBatDau(), gg.getNgayKetThuc(), gg.getPhanTramGG(), gg.isTrangThai() ? "Còn Áp Dụng" : "Không Áp Dụng", gg.getTongTienApDung()};
            model.addRow(row);
        }
    }

    public void initdata(int index) {
        GiamGia gg = arr.get(index);
        txtmagg.setText(gg.getMaGG());
        txttenchuongtrinh.setText(gg.getTenChuongTrinh());
        txtTTAD.setText(String.valueOf(gg.getTongTienApDung()));
        txtphantramgiam.setText(String.valueOf(gg.getPhanTramGG()));
        datengaybatdau.setDate(gg.getNgayBatDau());
        datengayketthuc.setDate(gg.getNgayKetThuc());
        rdoconapdung.setSelected(gg.isTrangThai());
        rdokhongapdung.setSelected(!gg.isTrangThai());
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
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtmagg = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblgiamgia = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txttenchuongtrinh = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        datengayketthuc = new com.toedter.calendar.JDateChooser();
        datengaybatdau = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        ngaybatdautimkiem = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        ngayketthuctimkiem = new com.toedter.calendar.JDateChooser();
        jButton5 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        rdoconapdung = new javax.swing.JRadioButton();
        rdokhongapdung = new javax.swing.JRadioButton();
        txtphantramgiam = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtTTAD = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1006, 767));

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 102, 102));
        jLabel1.setText("CHƯƠNG TRÌNH GIẢM GIÁ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(401, 401, 401)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Mã Giảm Giá:");

        tblgiamgia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã GG", "Tên Chương Trình", "Ngày Bắt Đầu", "Ngày Kết Thúc", "Phầm Trăm Giảm", "Trạng Thái", "Tổng tiền áp dụng"
            }
        ));
        tblgiamgia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblgiamgiaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblgiamgia);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Tên Chương Trình:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Ngày Bắt Đầu:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Ngày Kết Thúc:");

        jButton1.setBackground(new java.awt.Color(102, 255, 102));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img/Add.png"))); // NOI18N
        jButton1.setText("Thêm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(51, 102, 255));
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img/Edit.png"))); // NOI18N
        jButton4.setText("Sửa");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Ngày Bắt Đầu");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Ngày Kết Thúc");

        jButton5.setBackground(new java.awt.Color(204, 255, 204));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton5.setText("Tìm Kiếm");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel7)
                .addGap(41, 41, 41)
                .addComponent(ngaybatdautimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(jLabel8)
                .addGap(41, 41, 41)
                .addComponent(ngayketthuctimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addGap(43, 43, 43))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel8)
                        .addComponent(ngayketthuctimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(ngaybatdautimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Trạng Thái:");

        buttonGroup1.add(rdoconapdung);
        rdoconapdung.setText("Còn Áp Dụng");

        buttonGroup1.add(rdokhongapdung);
        rdokhongapdung.setText("Không Áp Dụng");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Phần Trăm Giảm:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Tiền áp dụng");

        jButton3.setBackground(new java.awt.Color(0, 255, 255));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img/Refresh.png"))); // NOI18N
        jButton3.setText("Làm Mới");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel10)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(datengayketthuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(datengaybatdau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtTTAD, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(1, 1, 1))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(1, 1, 1)
                                    .addComponent(txtmagg, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(txttenchuongtrinh, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtphantramgiam, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdoconapdung)
                            .addComponent(rdokhongapdung))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(123, 123, 123)
                                .addComponent(jButton4))
                            .addComponent(jButton1))
                        .addGap(115, 115, 115)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtmagg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txttenchuongtrinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(txtphantramgiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txtTTAD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(datengaybatdau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(datengayketthuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel9))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(rdoconapdung)
                                .addGap(18, 18, 18)
                                .addComponent(rdokhongapdung)))))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton4)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(319, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        int index = tblgiamgia.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Bạn Chưa Chọn Dòng Cập Nhật!");
        }
        GiamGia gg = arr.get(index);
        gg.setMaGG(txtmagg.getText());
        gg.setTenChuongTrinh(txttenchuongtrinh.getText());
        gg.setTongTienApDung(Integer.parseInt(txtTTAD.getText()));
        gg.setPhanTramGG(Integer.parseInt(txtphantramgiam.getText()));
        
        java.util.Date utilDate = datengaybatdau.getDate();

        if (utilDate != null) {
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            gg.setNgayBatDau(sqlDate);
        }
        java.util.Date utilDate1 = datengayketthuc.getDate();

        if (utilDate1 != null) {
            java.sql.Date sqlDate = new java.sql.Date(utilDate1.getTime());
            gg.setNgayKetThuc(sqlDate);
        }
        if (rdoconapdung.isSelected()) {
            gg.setTrangThai(true);
        } else if (rdokhongapdung.isSelected()) {
            gg.setTrangThai(false);
        }
        service.updateGiamGia(gg);
        viewdata();
        JOptionPane.showMessageDialog(this, "Cập Nhật Thành Công!");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (txtmagg.getText().isEmpty() || txtphantramgiam.getText().isEmpty() || txttenchuongtrinh.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn Chưa Nhập Hết Dữ Liệu!");
            return;
        } else {
            String maGG = txtmagg.getText();
            for (GiamGia gg : arr) {
                if (gg.getMaGG().equals(maGG)) {
                    JOptionPane.showMessageDialog(this, "Mã Giảm Giá Đã Tồn Tại!");
                    return;
                }
            }
        }

        GiamGia gg = new GiamGia();
        gg.setMaGG(txtmagg.getText());
        gg.setTenChuongTrinh(txttenchuongtrinh.getText());
        gg.setTongTienApDung(Integer.parseInt(txtTTAD.getText()));
        gg.setPhanTramGG(Integer.parseInt(txtphantramgiam.getText()));

        java.util.Date utilDate = datengaybatdau.getDate();

        if (utilDate != null) {
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            gg.setNgayBatDau(sqlDate);
        }
        java.util.Date utilDate1 = datengayketthuc.getDate();

        if (utilDate1 != null) {
            java.sql.Date sqlDate = new java.sql.Date(utilDate1.getTime());
            gg.setNgayKetThuc(sqlDate);
        }
        if (rdoconapdung.isSelected()) {
            gg.setTrangThai(true);
        } else {
            gg.setTrangThai(false);
        }
        arr.add(gg);
        service.addGiamGia(gg);
        viewdata();
        JOptionPane.showMessageDialog(this, "Thêm Thành Công!");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tblgiamgiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblgiamgiaMouseClicked
        // TODO add your handling code here:
        int index = tblgiamgia.getSelectedRow();
        this.initdata(index);
        txtmagg.setEditable(false);
    }//GEN-LAST:event_tblgiamgiaMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        txtTTAD.setText("");
        txtmagg.setText("");
        txtphantramgiam.setText("");
        txttenchuongtrinh.setText("");
        buttonGroup1.clearSelection();
        txtmagg.setEditable(true);

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        java.util.Date ngayBatDau = ngaybatdautimkiem.getDate();
        java.util.Date ngayKetThuc = ngayketthuctimkiem.getDate();

        // Kiểm tra nếu ngày bắt đầu hoặc ngày kết thúc là null
        if (ngayBatDau == null || ngayKetThuc == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn đầy đủ ngày bắt đầu và ngày kết thúc.");
            return;
        }

        // Chuyển đổi ngày từ java.util.Date sang java.sql.Date nếu cần
        java.sql.Date sqlNgayBatDau = new java.sql.Date(ngayBatDau.getTime());
        java.sql.Date sqlNgayKetThuc = new java.sql.Date(ngayKetThuc.getTime());

        // Thực hiện tìm kiếm trong cơ sở dữ liệu hoặc bộ lọc dữ liệu
        // Giả sử bạn có phương thức `searchDiscountPrograms(Date startDate, Date endDate)`
        searchDiscountPrograms(sqlNgayBatDau, sqlNgayKetThuc);
    }

    private void searchDiscountPrograms(java.sql.Date startDate, java.sql.Date endDate) {
        Connection connection = DBConnect.getConnection();
        String query = "SELECT * FROM GiamGia WHERE NgayBatDau >= ? AND NgayKetThuc <= ? ORDER BY NgayBatDau";

        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setDate(1, startDate);
            stmt.setDate(2, endDate);

            ResultSet rs = stmt.executeQuery();

            // Process the result set and display it in the table (tblDiscountPrograms)
            DefaultTableModel model = (DefaultTableModel) tblgiamgia.getModel();
            model.setRowCount(0); // Clear existing data in the table

            while (rs.next()) {
                String maGG = rs.getString("MaGiamGia");
                String tenChuongTrinh = rs.getString("TenChuongTrinh");
                java.sql.Date ngayBatDau = rs.getDate("NgayBatDau");
                java.sql.Date ngayKetThuc = rs.getDate("NgayKetThuc");
                int phanTramGiamGia = rs.getInt("PhanTramGiamGia");
                String trangThai = rs.getString("TrangThai");
                int tongTienApDung = rs.getInt("TongTienApDung");

                model.addRow(new Object[]{maGG, tenChuongTrinh, ngayBatDau, ngayKetThuc, phanTramGiamGia, trangThai, tongTienApDung});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi tìm kiếm chương trình giảm giá.");

        }
    }//GEN-LAST:event_jButton5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private com.toedter.calendar.JDateChooser datengaybatdau;
    private com.toedter.calendar.JDateChooser datengayketthuc;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
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
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser ngaybatdautimkiem;
    private com.toedter.calendar.JDateChooser ngayketthuctimkiem;
    private javax.swing.JRadioButton rdoconapdung;
    private javax.swing.JRadioButton rdokhongapdung;
    private javax.swing.JTable tblgiamgia;
    private javax.swing.JTextField txtTTAD;
    private javax.swing.JTextField txtmagg;
    private javax.swing.JTextField txtphantramgiam;
    private javax.swing.JTextField txttenchuongtrinh;
    // End of variables declaration//GEN-END:variables
}
