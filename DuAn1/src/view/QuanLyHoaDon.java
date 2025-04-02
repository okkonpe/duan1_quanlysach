/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.DBConnect;
import model.HoaDon;
import model.HoaDonChiTiet;
import model.Sach;
import service.HoaDonSevicer;
import service.hoadonchitietsevicer;

/**
 *
 * @author trinh thanh
 */
public class QuanLyHoaDon extends javax.swing.JPanel {

    ArrayList<HoaDon> arr = new ArrayList<>();
    ArrayList<HoaDonChiTiet> arr1 = new ArrayList<>();
    DefaultTableModel model = new DefaultTableModel();
    HoaDonSevicer sevicer = new HoaDonSevicer();
    hoadonchitietsevicer sv = new hoadonchitietsevicer();
    DefaultTableModel model1 = new DefaultTableModel();

    /**
     * Creates new form BanSach
     */
    public QuanLyHoaDon() {
        initComponents();
        arr = sevicer.getAll();
        model = (DefaultTableModel) tblhoadon.getModel();
        arr1 = sv.getAll();
        model1 = (DefaultTableModel) tblChiTietHD.getModel();
        viewdata();
        initComboBoxes();
        viewdataa();
    }

    public void viewdata() {
        // Sắp xếp danh sách arr: Đưa các hóa đơn 'Đã Thanh Toán' lên trước và sắp xếp theo ngày lập (mới nhất lên đầu)
//        arr.sort((h1, h2) -> {
//            String trangThaiCompare = String.compare(h2.isTrangThai(), h1.isTrangThai()); // 'Đã Thanh Toán' trước
//            if (trangThaiCompare == 0) {
//                return h2.getNgayLap().compareTo(h1.getNgayLap()); // Mới hơn trước
//            }
//            return trangThaiCompare;
//        });

        // Xóa dữ liệu cũ trong model
        model.setNumRows(0);

        // Thêm dữ liệu mới vào model
        for (HoaDon hd : arr) {
            Object[] row = new Object[]{
                hd.getMaHoaDon(),
                hd.getKhachHang(),
                hd.getMaNV(),
                hd.getMaGG(),
                hd.getNgayLap(),
                hd.getGiamGia(),
                hd.getTongTien(),
                hd.isTrangThai()
            };
            model.addRow(row);
        }
    }
     public void viewdataFilter(List<HoaDon> listFilter) {
        // Sắp xếp danh sách arr: Đưa các hóa đơn 'Đã Thanh Toán' lên trước và sắp xếp theo ngày lập (mới nhất lên đầu)
//        arr.sort((h1, h2) -> {
//            String trangThaiCompare = String.compare(h2.isTrangThai(), h1.isTrangThai()); // 'Đã Thanh Toán' trước
//            if (trangThaiCompare == 0) {
//                return h2.getNgayLap().compareTo(h1.getNgayLap()); // Mới hơn trước
//            }
//            return trangThaiCompare;
//        });

        // Xóa dữ liệu cũ trong model
        model.setNumRows(0);

        // Thêm dữ liệu mới vào model
        for (HoaDon hd : listFilter) {
            Object[] row = new Object[]{
                hd.getMaHoaDon(),
                hd.getKhachHang(),
                hd.getMaNV(),
                hd.getMaGG(),
                hd.getNgayLap(),
                hd.getGiamGia(),
                hd.getTongTien(),
                hd.isTrangThai()
            };
            model.addRow(row);
        }
    }

    public void viewdataa() {
        model1.setNumRows(0);
        for (HoaDonChiTiet hdc : arr1) {
            Object[] row = new Object[]{hdc.getMaHoaDon(), hdc.getMaSach(), hdc.getSoLuong(), hdc.getDonGia(), hdc.getThanhTien(), hdc.isTrangThai() ? "Đã Thanh Toán" : "Chưa Thanh Toán"};
            model1.addRow(row);
        }
    }
  private void initComboBoxes() {
        // Khởi tạo giá trị ComboBox từ cơ sở dữ liệu
        
        cbxTrangThai.addItem("");
        cbxTrangThai.addItem("Đã thanh toán");
         cbxTrangThai.addItem("Đang chờ thanh toán");
          cbxTrangThai.addItem("Huỷ");

     
            // Tác Giả
          
    }
    private void searchAndFilter() {
        // Lấy giá trị từ TextField và ComboBox
        String keyword = txtTK.getText().trim();
        String trangThai = cbxTrangThai.getSelectedItem() != null ? cbxTrangThai.getSelectedItem().toString() : "";
       


        // Lấy trạng thái từ RadioButton
       
        // Lấy danh sách kết quả tìm kiếm từ service
        List<HoaDon> filteredList = sevicer.searchAndFilterHD(keyword,trangThai);

        // Cập nhật danh sách hiển thị trên bảng
        viewdataFilter(filteredList);
    }
//    public void initdata(int index) {
//        HoaDon hd = arr.get(index);
//        lblmahoadon.setText(hd.getMaHoaDon());
//        lblmakhachhang.setText(hd.getKhachHang());
//        lblmanhanvien.setText(hd.getMaNV());
//        lblmagg.setText(hd.getMaGG());
//        Object ngayLap = hd.getNgayLap(); // Kiểu dữ liệu trả về từ getNgayLap()
//
//        if (ngayLap instanceof java.util.Date) {
//            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//            lblngaylap.setText(sdf.format((java.util.Date) ngayLap));
//        } else if (ngayLap instanceof java.time.LocalDate) {
//            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//            lblngaylap.setText(((LocalDate) ngayLap).format(dtf));
//        } else if (ngayLap instanceof String) {
//            lblngaylap.setText((String) ngayLap); // Không cần xử lý nếu là chuỗi
//        } else {
//            lblngaylap.setText("Không rõ định dạng"); // Trường hợp không khớp
//        }
//        Object giamGia = hd.getGiamGia(); // Lấy dữ liệu từ phương thức
//
//        if (giamGia == null) {
//            lblgiamgia.setText("0"); // Giá trị mặc định
//        } else if (giamGia instanceof Number) {
//            lblgiamgia.setText(String.valueOf(giamGia)); // Hiển thị số
//        } else {
//            lblgiamgia.setText(giamGia.toString()); // Hiển thị chuỗi
//        }
//        DecimalFormat df = new DecimalFormat("#,###"); // Định dạng tiền tệ
//        lblthanhtien.setText(df.format(hd.getTongTien()));
//        if (hd.isTrangThai().equalsIgnoreCase("Đã thanh toán")) {
//            lbltrangthai.setText("Đã Thanh Toán");
//        } else {
//            lbltrangthai.setText("Chưa Thanh Toán");
//        }
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblhoadon = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblChiTietHD = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        lblmahoadon = new javax.swing.JLabel();
        lblmakhachhang = new javax.swing.JLabel();
        lblmanhanvien = new javax.swing.JLabel();
        lblngaylap = new javax.swing.JLabel();
        lblmagg = new javax.swing.JLabel();
        lblgiamgia = new javax.swing.JLabel();
        lblthanhtien = new javax.swing.JLabel();
        lbltrangthai = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtTK = new javax.swing.JTextField();
        cbxTrangThai = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1006, 767));

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblhoadon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã HĐ", "Mã KH", "Mã NV", "Mã GG", "Ngày Lập", "Giảm Giá", "Thành Tiền", "Trạng Thái"
            }
        ));
        tblhoadon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblhoadonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblhoadon);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 857, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblChiTietHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã HĐ", "Mã Sách", "Tên Sách", "Số Lượng", "Đơn Giá", "Thành Tiền"
            }
        ));
        jScrollPane3.setViewportView(tblChiTietHD);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 857, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel6.setText(" ");

        jLabel10.setText("Danh sách hoá đơn");

        jLabel11.setText("Danh sách sản phẩm");

        txtTK.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTKCaretUpdate(evt);
            }
        });

        cbxTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxTrangThaiActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 255));
        jLabel12.setText("Thông tin hoá đơn");

        jLabel1.setText("Tìm kiếm");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(71, 71, 71)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTK, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbxTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(11, 11, 11)
                        .addComponent(jLabel6))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(312, 312, 312)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblgiamgia, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblngaylap, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblmanhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblmakhachhang, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblmagg, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblmahoadon, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbltrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblthanhtien, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lblmahoadon, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lblmakhachhang, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(lblmanhanvien)
                                .addGap(49, 49, 49)
                                .addComponent(lblmagg)
                                .addGap(11, 11, 11)
                                .addComponent(lblngaylap)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblgiamgia, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(57, 57, 57))
                            .addComponent(lblthanhtien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)
                        .addComponent(lbltrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(cbxTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(txtTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 138, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblhoadonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblhoadonMouseClicked
        // TODO add your handling code here:
        int index = tblhoadon.getSelectedRow();

        // Khởi tạo lại dữ liệu hiển thị thông tin hóa đơn
     

        // Lấy mã hóa đơn từ bảng hóa đơn đã chọn
        String maHoaDon = (String) tblhoadon.getValueAt(tblhoadon.getSelectedRow(), 0);

        // Lấy danh sách chi tiết hóa đơn từ dịch vụ dựa trên mã hóa đơn
        ArrayList<HoaDonChiTiet> list = sv.getTheoMaHD(maHoaDon);

        // Xóa toàn bộ dữ liệu hiện tại trong bảng chi tiết hóa đơn trước khi thêm mới
        model1.setRowCount(0);

        // Thêm dữ liệu chi tiết hóa đơn vào bảng chi tiết
        for (HoaDonChiTiet hdct : list) {
            Object[] row = new Object[]{
                hdct.getMaHoaDon(),
                hdct.getMaSach(),
                hdct.getTenSach(),
                hdct.getSoLuong(),
                hdct.getDonGia(),
                hdct.getThanhTien()
            };
            model1.addRow(row);
        }
    }//GEN-LAST:event_tblhoadonMouseClicked

    private void txtTKCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTKCaretUpdate
      searchAndFilter();
    }//GEN-LAST:event_txtTKCaretUpdate

    private void cbxTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTrangThaiActionPerformed
      searchAndFilter();
    }//GEN-LAST:event_cbxTrangThaiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbxTrangThai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblgiamgia;
    private javax.swing.JLabel lblmagg;
    private javax.swing.JLabel lblmahoadon;
    private javax.swing.JLabel lblmakhachhang;
    private javax.swing.JLabel lblmanhanvien;
    private javax.swing.JLabel lblngaylap;
    private javax.swing.JLabel lblthanhtien;
    private javax.swing.JLabel lbltrangthai;
    private javax.swing.JTable tblChiTietHD;
    private javax.swing.JTable tblhoadon;
    private javax.swing.JTextField txtTK;
    // End of variables declaration//GEN-END:variables
}
