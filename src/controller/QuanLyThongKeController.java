/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import ModelView.TongHoaDon;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.DoanhThu;
import ModelView.spbanchay;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import service.ThongKeService;
import service.ThongKeServiceIMPL;

/**
 *
 * @author phung
 */
public class QuanLyThongKeController {
   private ThongKeService thongKeService = null;

    public QuanLyThongKeController() {
        thongKeService = new ThongKeServiceIMPL();
    }
   
 public void bieudoDoanhthu(JPanel jPanel) {
    List<DoanhThu> list = thongKeService.getlistbydoanhthu();
    
    if (list != null && !list.isEmpty()) {  
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        for (DoanhThu dt : list) {
            dataset.addValue(dt.getTongtien(), "Doanh thu", dt.getNgaygiaodich());
        }
        
        JFreeChart chart = ChartFactory.createLineChart(
            "bieu do doanh thu",  
            "Ngày",  
            "Tổng tiền",  
            dataset,  
                PlotOrientation.VERTICAL,  
            true,  
            true,  
            false  
        );
        
        
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 300));  
        
      
        jPanel.removeAll();
        jPanel.setLayout(new CardLayout());
        jPanel.add(chartPanel);
        jPanel.validate();  
        jPanel.repaint();   
    } else {
        System.out.println("Danh sách doanh thu trống");
    }
}

    
      public void bieudoSanPham(JPanel jPanel){
        List<spbanchay> list = thongKeService.getlistbyspbanchay();
        
        if (list!=null) {
             DefaultCategoryDataset dataset = new DefaultCategoryDataset();
             for(spbanchay sp : list){
                 dataset.addValue(sp.getSoluong(), "top 3 san pham", sp.getTensp());
             }
             JFreeChart chart = ChartFactory.createBarChart("top 3 san pham ban chay nhat"
                     ,"tensp" ,"so luong", dataset);
             ChartPanel chartPanel = new ChartPanel(chart);
             chartPanel.setPreferredSize(new Dimension(500, 300));
             
             jPanel.removeAll();
             jPanel.setLayout(new CardLayout());
             jPanel.add(chartPanel);
             jPanel.validate();
             jPanel.repaint();
        }
    }
      
      

    public void bieudoDoanhThuTheoNgay(JPanel jpnview1, String startDateStr, String endDateStr) {
    List<DoanhThu> list = thongKeService.getslistdoanhthutheongay(startDateStr, endDateStr);

    if (list != null && !list.isEmpty()) {
        // Tạo dataset cho biểu đồ
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (DoanhThu dt : list) {
            dataset.addValue(dt.getTongtien(), "Doanh thu", dt.getNgaygiaodich());
        }

        // Tạo biểu đồ
        JFreeChart chart = ChartFactory.createLineChart(
                "Biểu đồ doanh thu",
                "Ngày",
                "Doanh thu (VNĐ)",
                dataset
        );

        // Hiển thị biểu đồ trong JPanel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(jpnview1.getWidth(), 300));

        jpnview1.removeAll();
        jpnview1.setLayout(new CardLayout());
        jpnview1.add(chartPanel);
        jpnview1.validate();
        jpnview1.repaint();
    } else {
        JOptionPane.showMessageDialog(jpnview1, "Không có dữ liệu trong khoảng thời gian đã chọn!");
    }
    }

    public void bieudoTongHoaDon(JPanel jPanel) {
    List<TongHoaDon> list = thongKeService.getslisttonghoadon();
    
    if (list != null && !list.isEmpty()) {  
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        // Thêm dữ liệu vào dataset
        for (TongHoaDon thd : list) {
            dataset.addValue(thd.getSoluong(), "Tổng số hóa đơn", thd.getNgay());
        }
        
        // Tạo biểu đồ bar chart
        JFreeChart chart = ChartFactory.createLineChart(
            "Tổng Số Hóa Đơn Theo Ngày",  // Tiêu đề biểu đồ
            "Ngày",  // Tiêu đề trục X
            "Số Lượng Hóa Đơn",  // Tiêu đề trục Y
            dataset  // Dữ liệu
        );
        
        // Tạo panel chứa biểu đồ
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 300));  
        
        // Cập nhật lại JPanel
        jPanel.removeAll();
        jPanel.setLayout(new CardLayout());
        jPanel.add(chartPanel);
        jPanel.validate();  
        jPanel.repaint();  
    } else {
        System.out.println("Danh sách tổng số hóa đơn trống");
    }
}

   public void bieudoTongHoaDonTheoNgay(JPanel jpnview1, String startDateStr, String endDateStr) {
    // Lấy danh sách tổng số hóa đơn theo ngày
    List<TongHoaDon> list = thongKeService.getslisttonghoadontheongay(startDateStr, endDateStr);

    if (list != null && !list.isEmpty()) {
        // Tạo dataset cho biểu đồ
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (TongHoaDon thd : list) {
            dataset.addValue(thd.getSoluong(), "Tổng số hóa đơn", thd.getNgay());
        }

        // Tạo biểu đồ
        JFreeChart chart = ChartFactory.createLineChart(
                "Biểu đồ tổng số hóa đơn",  // Tiêu đề biểu đồ
                "Ngày",                     // Trục X (Ngày)
                "Tổng số hóa đơn",          // Trục Y (Số lượng hóa đơn)
                dataset                     // Dữ liệu biểu đồ
        );

        // Hiển thị biểu đồ trong JPanel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(jpnview1.getWidth(), 300));

        // Cập nhật JPanel với biểu đồ
        jpnview1.removeAll();
        jpnview1.setLayout(new CardLayout());
        jpnview1.add(chartPanel);
        jpnview1.validate();
        jpnview1.repaint();
    } else {
        // Thông báo nếu không có dữ liệu
        JOptionPane.showMessageDialog(jpnview1, "Không có dữ liệu trong khoảng thời gian đã chọn!");
    }
}
 
 
 
}
