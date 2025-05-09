/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.TacGia;
import service.TacGiaServicer;

/**
 *
 * @author trinh thanh
 */
public class QuanLyTacGia extends javax.swing.JPanel {

    ArrayList<TacGia> arr = new ArrayList<>();
    DefaultTableModel model = new DefaultTableModel();
    TacGiaServicer servicer = new TacGiaServicer();

    /**
     * Creates new form TacGia
     */
    public QuanLyTacGia() {
        initComponents();
        model = (DefaultTableModel) tbltacgia.getModel();   
          arr = servicer.getAll();
        viewdata();
    }

    public void viewdata() {
          arr = servicer.getAll();
        model.setRowCount(0);
        for (TacGia tg : arr) {
            Object[] row = new Object[]{tg.getMaTacGia(), tg.getTenTacGia(), tg.getNgaySinh(), tg.getDiaChi()};
            model.addRow(row);
        }
    }

    public void initdata(int index) {
        TacGia tg = arr.get(index);
        txtdiachi.setText(tg.getDiaChi());
        txtmatacgia.setText(tg.getMaTacGia());
        txttentacgia.setText(tg.getTenTacGia());
        datengaysinh.setDate(tg.getNgaySinh());}
      

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtmatacgia = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txttentacgia = new javax.swing.JTextField();
        txtdiachi = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        datengaysinh = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbltacgia = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1006, 767));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel1.setText("TÁC GIẢ");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("Mã Tác Giả:");

        jLabel3.setText("Tên Tác Giả");

        jLabel4.setText("Địa Chỉ:");

        jLabel5.setText("Ngày Sinh:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(txtdiachi)
                    .addComponent(jLabel3)
                    .addComponent(txttentacgia)
                    .addComponent(jLabel2)
                    .addComponent(txtmatacgia)
                    .addComponent(datengaysinh, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE))
                .addContainerGap(84, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(txtmatacgia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(txttentacgia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(txtdiachi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(datengaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tbltacgia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã Tác Giả", "Tên Tác Giả", "Ngày Sinh", "Quốc Tịch"
            }
        ));
        tbltacgia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbltacgiaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbltacgia);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img/Add.png"))); // NOI18N
        jButton1.setText("THÊM");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img/Edit.png"))); // NOI18N
        jButton2.setText("SỬA");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img/Refresh.png"))); // NOI18N
        jButton4.setText("LÀM MỚI");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jButton1)
                .addGap(42, 42, 42)
                .addComponent(jButton2)
                .addGap(63, 63, 63)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton4))
                .addGap(34, 34, 34))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(377, 377, 377)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(117, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (txtdiachi.getText().isEmpty() || txtmatacgia.getText().isEmpty() || txttentacgia.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn Chưa Nhập Thông Tin!");
            return;
        }
        String maNV = txtmatacgia.getText();
            for (TacGia tg : arr) {
                if (tg.getMaTacGia().equals(maNV) ) {
                    JOptionPane.showMessageDialog(this, "Mã Tác Giả Đã Trùng!");
                    return;
                }
            }
        TacGia tg = new TacGia();
        tg.setMaTacGia(txtmatacgia.getText());
        tg.setTenTacGia(txttentacgia.getText());
        tg.setDiaChi(txtdiachi.getText());
        tg.setNgaySinh(datengaysinh.getDate());
       
        arr.add(tg);
        servicer.addTacGia(tg);
        viewdata();
        JOptionPane.showMessageDialog(this, "Thêm Thành Công!");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tbltacgiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbltacgiaMouseClicked
        int row = tbltacgia.getSelectedRow();

        if (row != -1) { // Kiểm tra nếu có dòng được chọn
            // Lấy thông tin mã thể loại, tên thể loại và trạng thái từ bảng
            String maTheLoai = tbltacgia.getValueAt(row, 0).toString(); // Cột 0 là mã thể loại
            String tenTheLoai = tbltacgia.getValueAt(row, 1).toString();
            String ngaySinh = tbltacgia.getValueAt(row, 2).toString();// Cột 1 là tên thể loại
            String quocTich = tbltacgia.getValueAt(row, 3).toString();
          // Cột 2 là trạng thái boolean

            // Hiển thị thông tin lên các ô nhập liệu
            txtmatacgia.setText(maTheLoai);
            txttentacgia.setText(tenTheLoai);
            txtdiachi.setText(quocTich);
              txtmatacgia.setEditable(false);
            // Chuyển ngày sinh từ String thành Date và cập nhật cho JDateChooser
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date date = sdf.parse(ngaySinh);  // Chuyển String thành Date
                datengaysinh.setDate(date); // Đặt ngày cho JDateChooser
            } catch (Exception e) {
                e.printStackTrace();
            }
          

        }
       
    }//GEN-LAST:event_tbltacgiaMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int index = tbltacgia.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Chưa Chọn Dòng Để Cập Nhật!");
            return;
        } 
        TacGia tg = arr.get(index);
        tg.setMaTacGia(txtmatacgia.getText());
        tg.setTenTacGia(txttentacgia.getText());
        tg.setDiaChi(txtdiachi.getText());     
        tg.setNgaySinh(datengaysinh.getDate());     
        servicer.UpdateTacGia(tg);
        viewdata();
        JOptionPane.showMessageDialog(this, "Cập Nhật Thành Công!");   
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        txtdiachi.setText("");
        txtmatacgia.setText("");
        txttentacgia.setText("");
        datengaysinh.setDate(null);
        buttonGroup1.clearSelection();
        txtmatacgia.setEditable(true);
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private com.toedter.calendar.JDateChooser datengaysinh;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbltacgia;
    private javax.swing.JTextField txtdiachi;
    private javax.swing.JTextField txtmatacgia;
    private javax.swing.JTextField txttentacgia;
    // End of variables declaration//GEN-END:variables

}
