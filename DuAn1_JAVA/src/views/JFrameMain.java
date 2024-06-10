/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.util.logging.Level;
import java.util.logging.Logger;
import repository.Auth;
import repository.MsgBox;

/**
 *
 * @author trung
 */
public class JFrameMain extends javax.swing.JFrame {

    /**
     * Creates new form JFrameMain
     */
    public JFrameMain() {
        initComponents();
        setLocationRelativeTo(null);
        int admin = Auth.isManager();
        if (admin != 0) {
            btnTaiKhoan.setVisible(false);
            btnVoucher.setVisible(false);
            btnThongKe.setVisible(false);
            btnKhachHang.setVisible(false);
            btnSanPham.setVisible(false);

        }
        phanQuyen();

    }

    void phanQuyen() {
        String ten = Auth.User.getHoTen();
        int chucvu = Auth.User.getChucVu();
//        ?"Admin":"Nhân viên"
        lblNguoiDangNhap.setText(ten);
        lblChucVu.setText(chucvu == 0 ? "Admin" : "Nhân viên");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnNavigation = new javax.swing.JPanel();
        btnSanPham = new javax.swing.JButton();
        btnDangXuat = new javax.swing.JButton();
        btnKhachHang = new javax.swing.JButton();
        btnVoucher = new javax.swing.JButton();
        btnBanHang = new javax.swing.JButton();
        btnTaiKhoan = new javax.swing.JButton();
        btnThongKe = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lblNguoiDangNhap = new javax.swing.JLabel();
        lblChucVu = new javax.swing.JLabel();
        btnDoiMatKhau = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jpnNavigation.setBackground(new java.awt.Color(153, 153, 255));
        jpnNavigation.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnSanPham.setBackground(new java.awt.Color(204, 204, 255));
        btnSanPham.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSanPham.setForeground(new java.awt.Color(0, 0, 255));
        btnSanPham.setText("Quản lý sản phẩm");
        btnSanPham.setBorder(new javax.swing.border.MatteBorder(null));
        btnSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSanPhamActionPerformed(evt);
            }
        });

        btnDangXuat.setBackground(new java.awt.Color(255, 255, 153));
        btnDangXuat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDangXuat.setText("Đăng xuất");
        btnDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangXuatActionPerformed(evt);
            }
        });

        btnKhachHang.setBackground(new java.awt.Color(204, 204, 255));
        btnKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnKhachHang.setForeground(new java.awt.Color(0, 0, 255));
        btnKhachHang.setText("Quản lý khách hàng");
        btnKhachHang.setBorder(new javax.swing.border.MatteBorder(null));
        btnKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhachHangActionPerformed(evt);
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

        btnBanHang.setBackground(new java.awt.Color(204, 204, 255));
        btnBanHang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnBanHang.setForeground(new java.awt.Color(0, 0, 255));
        btnBanHang.setText("Quản lý bán hàng");
        btnBanHang.setBorder(new javax.swing.border.MatteBorder(null));
        btnBanHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBanHangActionPerformed(evt);
            }
        });

        btnTaiKhoan.setBackground(new java.awt.Color(204, 204, 255));
        btnTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTaiKhoan.setForeground(new java.awt.Color(0, 0, 255));
        btnTaiKhoan.setText("Quản lý Nhân viên");
        btnTaiKhoan.setBorder(new javax.swing.border.MatteBorder(null));
        btnTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaiKhoanActionPerformed(evt);
            }
        });

        btnThongKe.setBackground(new java.awt.Color(204, 204, 255));
        btnThongKe.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThongKe.setForeground(new java.awt.Color(0, 0, 255));
        btnThongKe.setText("Thống kê");
        btnThongKe.setBorder(new javax.swing.border.MatteBorder(null));
        btnThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(253, 248, 219));
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });

        lblNguoiDangNhap.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblNguoiDangNhap.setForeground(new java.awt.Color(51, 51, 255));
        lblNguoiDangNhap.setText("--");

        lblChucVu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblChucVu.setForeground(new java.awt.Color(0, 0, 255));
        lblChucVu.setText("--");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblNguoiDangNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                    .addComponent(lblChucVu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lblNguoiDangNhap)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblChucVu)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        btnDoiMatKhau.setBackground(new java.awt.Color(255, 255, 153));
        btnDoiMatKhau.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDoiMatKhau.setText("Đổi mật khẩu");
        btnDoiMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoiMatKhauActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnNavigationLayout = new javax.swing.GroupLayout(jpnNavigation);
        jpnNavigation.setLayout(jpnNavigationLayout);
        jpnNavigationLayout.setHorizontalGroup(
            jpnNavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnNavigationLayout.createSequentialGroup()
                .addGroup(jpnNavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnNavigationLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jpnNavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSanPham, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                            .addComponent(btnVoucher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnBanHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jpnNavigationLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jpnNavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnDoiMatKhau)
                            .addComponent(btnDangXuat))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpnNavigationLayout.setVerticalGroup(
            jpnNavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnNavigationLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDoiMatKhau)
                .addGap(18, 18, 18)
                .addComponent(btnDangXuat)
                .addGap(33, 33, 33))
        );

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 255));
        jLabel2.setText("Cửa Hàng Giày Sneaker");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(316, 316, 316)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(253, 248, 219));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconSwing/_01b9fd59-586b-4d89-9c78-6bd359185af5.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(213, 213, 213)
                .addComponent(jLabel1)
                .addContainerGap(244, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addContainerGap(55, Short.MAX_VALUE))
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
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnNavigation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void btnSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSanPhamActionPerformed

//        int us = Auth.isManager();
//        if (us == 0) {
//            setVisible(false);
//            new JFrameSanPham().setVisible(true);
//        } else {
//            MsgBox.alert(this, "Bạn không phải admin");
//        }
        setVisible(false);
        new JFrameSanPham().setVisible(true);
    }//GEN-LAST:event_btnSanPhamActionPerformed

    private void btnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuatActionPerformed
        setVisible(false);
        try {
            new JFrameLoginForm().setVisible(true);
        } catch (SQLServerException ex) {
            Logger.getLogger(JFrameMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDangXuatActionPerformed

    private void btnKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhachHangActionPerformed
        setVisible(false);
        new JFrameKhacHang().setVisible(true);
    }//GEN-LAST:event_btnKhachHangActionPerformed

    private void btnVoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoucherActionPerformed
        setVisible(false);
        new quanLyKM().setVisible(true);
    }//GEN-LAST:event_btnVoucherActionPerformed

    private void btnBanHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBanHangActionPerformed
        setVisible(false);
        new JFrameBanHang().setVisible(true);
    }//GEN-LAST:event_btnBanHangActionPerformed

    private void btnTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaiKhoanActionPerformed
        setVisible(false);
        try {
            new JFrameNhanVien().setVisible(true);
        } catch (SQLServerException ex) {
            Logger.getLogger(JFrameMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnTaiKhoanActionPerformed

    private void btnThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeActionPerformed
        setVisible(false);
        new JFrameThongKe().setVisible(true);
    }//GEN-LAST:event_btnThongKeActionPerformed

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked

    }//GEN-LAST:event_jPanel2MouseClicked

    private void btnDoiMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiMatKhauActionPerformed
        setVisible(false);
        new DoiMatKhauJdialog(this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_btnDoiMatKhauActionPerformed

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
            java.util.logging.Logger.getLogger(JFrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBanHang;
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JButton btnDoiMatKhau;
    private javax.swing.JButton btnKhachHang;
    private javax.swing.JButton btnSanPham;
    private javax.swing.JButton btnTaiKhoan;
    private javax.swing.JButton btnThongKe;
    private javax.swing.JButton btnVoucher;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jpnNavigation;
    private javax.swing.JLabel lblChucVu;
    private javax.swing.JLabel lblNguoiDangNhap;
    // End of variables declaration//GEN-END:variables

}
