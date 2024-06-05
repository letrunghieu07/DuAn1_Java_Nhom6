/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import java.text.ParseException;
import model.KhuyenMai;
import repository.KhuyenMaiRepository;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
//import java.util.Locale;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author trung
 */
public class quanLyKM extends javax.swing.JFrame {
    
   
     private DefaultTableModel model = new DefaultTableModel();

    KhuyenMaiRepository repoKM = new KhuyenMaiRepository();
    Random generator = new Random();
    public quanLyKM() {
        initComponents();
        setLocationRelativeTo(null);
        fillKhuyenMaiTbl();
    }

    void fillKhuyenMaiTbl() {
        ArrayList<KhuyenMai> listKM = new ArrayList<>();
        listKM = repoKM.getKhuyenMai();
        DefaultTableModel model = (DefaultTableModel) khuyenMaiTbl.getModel();
        model.setRowCount(0);
        for (KhuyenMai kM : listKM) {
            Object[] data = {
                kM.getMaKM(), kM.getTenKhuyenMai(), kM.getNgayBD(), kM.getNgayKT(), kM.getMucGiam(),
                kM.getDonVi(), kM.getMaGiam(), kM.getTrangThai()
            };
            model.addRow(data);
        }
    }  
   
    void getDataRow() {
        int selectedRow = khuyenMaiTbl.getSelectedRow();
        if(selectedRow < 0) {
            return;
        }
        titelLBl.setText("Thông tin khuyến mãi - " + khuyenMaiTbl.getValueAt(selectedRow, 0).toString());
      
        tenKhuyenMaiTxt.setText("" +khuyenMaiTbl.getValueAt(selectedRow, 1));
        ngaybatdautxt.setText(""+ khuyenMaiTbl.getValueAt(selectedRow, 2));
        ngayketthuctxt.setText(""+ khuyenMaiTbl.getValueAt(selectedRow, 3));
        mucGiamGiaTxt.setText("" +khuyenMaiTbl.getValueAt(selectedRow, 4));
        if(khuyenMaiTbl.getValueAt(selectedRow, 5).equals("VNĐ")) {
            vNDRB.setSelected(true);
        } else {
            phanTramRB.setSelected(true);
        }
        maAPDungTxt.setText("" +khuyenMaiTbl.getValueAt(selectedRow, 6));
        trangThaiCB.setSelectedItem(khuyenMaiTbl.getValueAt(selectedRow, 7).toString());
    }
    
      void clearForm()  {
        tenKhuyenMaiTxt.setText("");
        ngaybatdautxt.setText("");
        ngayketthuctxt.setText("");
        mucGiamGiaTxt.setText("");
        maAPDungTxt.setText("");
        vNDRB.setSelected(true);
        trangThaiCB.setSelectedIndex(0);
    } 
    
    
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jpnNavigation = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        btnVoucher = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        btnVoucher1 = new javax.swing.JButton();
        btnVoucher2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        tenKhuyenMaiTxt = new javax.swing.JTextField();
        titelLBl = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ngaybatdautxt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        ngayketthuctxt = new javax.swing.JTextField();
        mucGiamGiaTxt = new javax.swing.JTextField();
        vNDRB = new javax.swing.JRadioButton();
        phanTramRB = new javax.swing.JRadioButton();
        maAPDungTxt = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        khuyenMaiTbl = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        trangThaiCB = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jpnCRUD4 = new javax.swing.JPanel();
        btnThemKM = new javax.swing.JButton();
        btnCapNhatKM = new javax.swing.JButton();
        btnXoaKM = new javax.swing.JButton();
        btnClearForm = new javax.swing.JButton();
        btnUpdateKM = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jpnNavigation.setBackground(new java.awt.Color(153, 153, 255));
        jpnNavigation.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton1.setBackground(new java.awt.Color(255, 204, 204));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconSwing/Shop.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnVoucher.setBackground(new java.awt.Color(204, 204, 255));
        btnVoucher.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnVoucher.setForeground(new java.awt.Color(0, 0, 255));
        btnVoucher.setText("Quản lý Voucher");
        btnVoucher.setBorder(new javax.swing.border.MatteBorder(null));
        btnVoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoucherActionPerformed(evt);
            }
        });

        btnHome.setBackground(new java.awt.Color(255, 255, 153));
        btnHome.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnHome.setText("Home");
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });

        btnVoucher1.setBackground(new java.awt.Color(204, 204, 255));
        btnVoucher1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnVoucher1.setForeground(new java.awt.Color(0, 0, 255));
        btnVoucher1.setText("Quản lý giảm giá");
        btnVoucher1.setBorder(new javax.swing.border.MatteBorder(null));
        btnVoucher1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoucher1ActionPerformed(evt);
            }
        });

        btnVoucher2.setBackground(new java.awt.Color(204, 204, 255));
        btnVoucher2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnVoucher2.setForeground(new java.awt.Color(0, 0, 255));
        btnVoucher2.setText("Add Mã Giảm Giá");
        btnVoucher2.setBorder(new javax.swing.border.MatteBorder(null));
        btnVoucher2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoucher2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnNavigationLayout = new javax.swing.GroupLayout(jpnNavigation);
        jpnNavigation.setLayout(jpnNavigationLayout);
        jpnNavigationLayout.setHorizontalGroup(
            jpnNavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
            .addGroup(jpnNavigationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnNavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnVoucher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnVoucher1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jpnNavigationLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(btnHome)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jpnNavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpnNavigationLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(btnVoucher2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jpnNavigationLayout.setVerticalGroup(
            jpnNavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnNavigationLayout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(btnVoucher1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnHome)
                .addGap(25, 25, 25))
            .addGroup(jpnNavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpnNavigationLayout.createSequentialGroup()
                    .addGap(285, 285, 285)
                    .addComponent(btnVoucher2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(286, Short.MAX_VALUE)))
        );

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Quản lý voucher");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(323, 323, 323)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(25, 25, 25))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 204));

        titelLBl.setText("Thông Tin Khuyến Mãi");

        jLabel3.setText("Ngày bắt đầu");

        ngaybatdautxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ngaybatdautxtActionPerformed(evt);
            }
        });

        jLabel4.setText("Ngày kết thúc");

        buttonGroup1.add(vNDRB);
        vNDRB.setText("VND");

        buttonGroup1.add(phanTramRB);
        phanTramRB.setText("%");

        khuyenMaiTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Khuyến Mãi", "Tên Khuyến Mãi", "Ngày Bắt Đầu", "Ngày Kết Thúc", "Mức Giảm", "Đơn vị", "Mã giảm", "Trạng thái"
            }
        ));
        khuyenMaiTbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                khuyenMaiTblMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(khuyenMaiTbl);

        jLabel5.setText("Mức Giảm");

        jLabel7.setText("Trạng thái");

        trangThaiCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đang hoạt động", "Không hoạt động" }));

        jLabel8.setText("Bảng khuyến mãi");

        jpnCRUD4.setBackground(new java.awt.Color(255, 204, 204));
        jpnCRUD4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chức năng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(255, 0, 51))); // NOI18N

        btnThemKM.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThemKM.setForeground(new java.awt.Color(0, 0, 255));
        btnThemKM.setText("Thêm");
        btnThemKM.setBorder(new javax.swing.border.MatteBorder(null));
        btnThemKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKMActionPerformed(evt);
            }
        });

        btnCapNhatKM.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCapNhatKM.setForeground(new java.awt.Color(0, 204, 0));
        btnCapNhatKM.setText("Cập nhật");
        btnCapNhatKM.setBorder(new javax.swing.border.MatteBorder(null));
        btnCapNhatKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatKMActionPerformed(evt);
            }
        });

        btnXoaKM.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXoaKM.setForeground(new java.awt.Color(255, 0, 0));
        btnXoaKM.setText("Xoá");
        btnXoaKM.setBorder(new javax.swing.border.MatteBorder(null));
        btnXoaKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaKMActionPerformed(evt);
            }
        });

        btnClearForm.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnClearForm.setText("Clear");
        btnClearForm.setBorder(new javax.swing.border.MatteBorder(null));
        btnClearForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearFormActionPerformed(evt);
            }
        });

        btnUpdateKM.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnUpdateKM.setForeground(new java.awt.Color(0, 204, 0));
        btnUpdateKM.setText("Cập nhật TT");
        btnUpdateKM.setBorder(new javax.swing.border.MatteBorder(null));
        btnUpdateKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateKMActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnCRUD4Layout = new javax.swing.GroupLayout(jpnCRUD4);
        jpnCRUD4.setLayout(jpnCRUD4Layout);
        jpnCRUD4Layout.setHorizontalGroup(
            jpnCRUD4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCRUD4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jpnCRUD4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnClearForm, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpnCRUD4Layout.createSequentialGroup()
                        .addGroup(jpnCRUD4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnThemKM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCapNhatKM, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE))
                        .addGroup(jpnCRUD4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpnCRUD4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnUpdateKM, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnCRUD4Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(btnXoaKM, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnCRUD4Layout.setVerticalGroup(
            jpnCRUD4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCRUD4Layout.createSequentialGroup()
                .addGroup(jpnCRUD4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemKM, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaKM, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpnCRUD4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCapNhatKM, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdateKM, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnClearForm, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jLabel1.setText("Mã App Dụng");

        jLabel6.setText("Tên Khuyến Mãi");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 356, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(titelLBl, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tenKhuyenMaiTxt)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(ngaybatdautxt)
                                .addComponent(mucGiamGiaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(vNDRB)
                                .addGap(18, 18, 18)
                                .addComponent(phanTramRB)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ngayketthuctxt, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(94, 94, 94))
                            .addComponent(trangThaiCB, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(maAPDungTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(95, 95, 95)
                .addComponent(jpnCRUD4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(99, 99, 99))
            .addComponent(jScrollPane3)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jpnCRUD4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(titelLBl, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(6, 6, 6)
                                .addComponent(tenKhuyenMaiTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ngaybatdautxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(mucGiamGiaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(vNDRB)
                                    .addComponent(phanTramRB)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(maAPDungTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ngayketthuctxt, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(trangThaiCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpnNavigation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnNavigation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnVoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoucherActionPerformed
        setVisible(false);
        new quanLyKM().setVisible(true);
    }//GEN-LAST:event_btnVoucherActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        setVisible(false);
        new JFrameMain().setVisible(true);
    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnVoucher1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoucher1ActionPerformed
        setVisible(false);
        new JFrameGiamGia().setVisible(true);
    }//GEN-LAST:event_btnVoucher1ActionPerformed

    private void btnVoucher2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoucher2ActionPerformed
        setVisible(false);
        new JFrameGiamGiaSanPham().setVisible(true);
    }//GEN-LAST:event_btnVoucher2ActionPerformed

    private void btnUpdateKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateKMActionPerformed

    }//GEN-LAST:event_btnUpdateKMActionPerformed

    private void btnClearFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearFormActionPerformed
        clearForm();
    }//GEN-LAST:event_btnClearFormActionPerformed

    private void btnXoaKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaKMActionPerformed
 try {
        int selectedRow = khuyenMaiTbl.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một khuyến mãi để xóa!");
            return;
        }

        int maKM = (int) khuyenMaiTbl.getValueAt(selectedRow, 0);

        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa khuyến mãi này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.NO_OPTION) {
            return;
        }

        boolean check = repoKM.deleteKhuyenMai(maKM);
        if (check) {
            JOptionPane.showMessageDialog(this, "Xóa khuyến mãi thành công!");
            fillKhuyenMaiTbl(); 
        } else {
            JOptionPane.showMessageDialog(this, "Xóa khuyến mãi thất bại!");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
    }
    }//GEN-LAST:event_btnXoaKMActionPerformed

    private void btnCapNhatKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatKMActionPerformed
    try {      
        int maKM = (int) khuyenMaiTbl.getValueAt(khuyenMaiTbl.getSelectedRow(), 0);
        String tenKhuyenMai = tenKhuyenMaiTxt.getText();
        String ngayBatDauStr = ngaybatdautxt.getText();
        String ngayKetThucStr = ngayketthuctxt.getText();
        float mucGiam = Float.parseFloat(mucGiamGiaTxt.getText());
        String maApDung = maAPDungTxt.getText();
        int trangThai = trangThaiCB.getSelectedIndex();
        int donVi = vNDRB.isSelected() ? 0 : 1;
    
        if (tenKhuyenMai.isEmpty() || tenKhuyenMai.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên khuyến mãi!");
            return;
        }

        if (ngayBatDauStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày bắt đầu!");
            return;
        }
        Date ngayBatDau = new SimpleDateFormat("dd-MM-yyyy").parse(ngayBatDauStr);

        if (ngayKetThucStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày kết thúc!");
            return;
        }
        Date ngayKetThuc = new SimpleDateFormat("dd-MM-yyyy").parse(ngayKetThucStr);

        if (!ngayKetThuc.after(ngayBatDau)) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc phải lớn hơn ngày bắt đầu!");
            return;
        }

        if (mucGiam <= 0) {
            JOptionPane.showMessageDialog(this, "Mức giảm phải lớn hơn 0!");
            return;
        }
        if (donVi == 1 && mucGiam > 100) {
            JOptionPane.showMessageDialog(this, "Phần trăm giảm phải nhỏ hơn hoặc bằng 100%!");
            return;
        }

        if (maApDung.isEmpty() || maApDung.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã áp dụng!");
            return;
        }
        if (maApDung.length() != 5) {
            JOptionPane.showMessageDialog(this, "Mã áp dụng phải có 5 ký tự!");
            return;
        }
        
        java.sql.Date ngayBatDauSql = new java.sql.Date(ngayBatDau.getTime());
        java.sql.Date ngayKetThucSql = new java.sql.Date(ngayKetThuc.getTime());
        boolean check = repoKM.updateKhuyenMai(maKM, tenKhuyenMai, ngayBatDauSql, ngayKetThucSql, mucGiam, maApDung, trangThai, donVi);
        if (check) {
            JOptionPane.showMessageDialog(this, "Cập nhật khuyến mãi thành công!");
            fillKhuyenMaiTbl();
        } else {
            JOptionPane.showMessageDialog(this, "Cập nhật khuyến mãi thất bại!");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
    }
    }//GEN-LAST:event_btnCapNhatKMActionPerformed

    private void btnThemKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKMActionPerformed
try {
        String tenKhuyenMai = tenKhuyenMaiTxt.getText();
        String ngayBatDauStr = ngaybatdautxt.getText();
        String ngayKetThucStr = ngayketthuctxt.getText();
        float mucGiam = Float.parseFloat(mucGiamGiaTxt.getText());
        String maApDung = maAPDungTxt.getText();
        int trangThai = trangThaiCB.getSelectedIndex();
        int donVi = vNDRB.isSelected() ? 0 : 1;

        if (tenKhuyenMai.isEmpty() || ngayBatDauStr.isEmpty() || ngayKetThucStr.isEmpty() || maApDung.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }

        Date ngayBatDau = new SimpleDateFormat("dd-MM-yyyy").parse(ngayBatDauStr);
        Date ngayKetThuc = new SimpleDateFormat("dd-MM-yyyy").parse(ngayKetThucStr);
        java.sql.Date ngayBatDauSql = new java.sql.Date(ngayBatDau.getTime());
        java.sql.Date ngayKetThucSql = new java.sql.Date(ngayKetThuc.getTime());

        if (!ngayKetThuc.after(ngayBatDau)) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc phải lớn hơn ngày bắt đầu!");
            return;
        }

        if (mucGiam <= 0) {
            JOptionPane.showMessageDialog(this, "Mức giảm phải lớn hơn 0!");
            return;
        }
        if (donVi == 1 && mucGiam > 100) {
            JOptionPane.showMessageDialog(this, "Phần trăm giảm phải nhỏ hơn hoặc bằng 100%!");
            return;
        }

        if (maApDung.isEmpty() || maApDung.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã áp dụng!");
            return;
        }
        if (maApDung.length() != 5) {
            JOptionPane.showMessageDialog(this, "Mã áp dụng phải có 5 ký tự!");
            return;
        }
        if (trangThai == 0) {
        trangThai = 0;
        } else {
        trangThai = 1;
    }

        boolean check = repoKM.insertKhuyenMai(tenKhuyenMai, ngayBatDauSql, ngayKetThucSql, mucGiam, maApDung, trangThai, donVi);
        if (check) {
            JOptionPane.showMessageDialog(this, "Thêm khuyến mãi thành công!");
            fillKhuyenMaiTbl(); 
            clearForm();
        } else {
            JOptionPane.showMessageDialog(this, "Thêm khuyến mãi thất bại!");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Lỗi các trường thông tin không được để trống ");
    }
    }//GEN-LAST:event_btnThemKMActionPerformed

    private void khuyenMaiTblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_khuyenMaiTblMouseClicked
        // TODO add your handling code here:
        getDataRow();
       
    }//GEN-LAST:event_khuyenMaiTblMouseClicked

    private void ngaybatdautxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ngaybatdautxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ngaybatdautxtActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(quanLyKM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(quanLyKM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(quanLyKM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(quanLyKM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new quanLyKM().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhatKM;
    private javax.swing.JButton btnClearForm;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnThemKM;
    private javax.swing.JButton btnUpdateKM;
    private javax.swing.JButton btnVoucher;
    private javax.swing.JButton btnVoucher1;
    private javax.swing.JButton btnVoucher2;
    private javax.swing.JButton btnXoaKM;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JPanel jpnCRUD4;
    private javax.swing.JPanel jpnNavigation;
    private javax.swing.JTable khuyenMaiTbl;
    private javax.swing.JTextField maAPDungTxt;
    private javax.swing.JTextField mucGiamGiaTxt;
    private javax.swing.JTextField ngaybatdautxt;
    private javax.swing.JTextField ngayketthuctxt;
    private javax.swing.JRadioButton phanTramRB;
    private javax.swing.JTextField tenKhuyenMaiTxt;
    private javax.swing.JLabel titelLBl;
    private javax.swing.JComboBox<String> trangThaiCB;
    private javax.swing.JRadioButton vNDRB;
    // End of variables declaration//GEN-END:variables

   
}
