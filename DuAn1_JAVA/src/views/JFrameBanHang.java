/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.KhachHang;
import model.Voucher;
import model.chiTietSanPham;
import model.hoaDon;
import model.hoaDonChiTiet;
import repository.Auth;
import repository.BanHangRepository;
import repository.MsgBox;

/**
 *
 * @author trung
 */
public class JFrameBanHang extends javax.swing.JFrame {

    BanHangRepository banHangRepository = new BanHangRepository();

    /**
     * Creates new form JFrameBanHang
     */
    public JFrameBanHang() {
        initComponents();
        setLocationRelativeTo(null);
        fillTableDanhSachSP();
        fillHTTT();
        fillDSHDCho();
        rdoChuaThanhToan.setSelected(true);
    }

    // Fill danh sách sản phẩm
    void fillTableDanhSachSP() {
        List<chiTietSanPham> listCTSP = banHangRepository.listAll();

        DefaultTableModel model = (DefaultTableModel) tblDanhSachSP.getModel();
        model.setRowCount(0);
        for (chiTietSanPham CTSP : listCTSP) {
            Object[] data = {
                CTSP.getMaCTSP(), CTSP.getTenSP(), CTSP.getTenSize(), CTSP.getTenMS(), CTSP.getTenCL(),
                CTSP.getTenCLDe(), CTSP.getSoLuong(), CTSP.getDonGia()
            };
            model.addRow(data);
        }
    }

    // load HTTT
    void fillHTTT() {
        ArrayList<String> list = new ArrayList<>();
        list = banHangRepository.getHTTT();
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboHinhThucThanhToan.getModel();
        String[] httt = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            httt[i] = list.get(i);
        }
        cboHinhThucThanhToan.setModel(new javax.swing.DefaultComboBoxModel(httt));
    }

    // Fill hóa đơn chờ
    void fillDSHDCho() {
        System.out.println("abc");
        int trangThai = 0;
        if (rdoChuaThanhToan.isSelected()) {
            trangThai = 0;
            btnTaoHD.setEnabled(true);
        }
        if (rdoDaThanhToan.isSelected()) {
            trangThai = 1;
            btnTaoHD.setEnabled(true);
        }

        ArrayList<hoaDon> listHD = banHangRepository.getHoaDonCho(trangThai);

        DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
        model.setRowCount(0);

        for (hoaDon hd : listHD) {
            Object[] data = {
                hd.getMaHD(),
                hd.getTenNV(),
                hd.getNgayTao(),
                hd.getTrangThai() ? "Đã thanh toán" : "Chưa thanh toán"
            };
            model.addRow(data);
        }
    }

    //insert hóa đơn chờ
    void insertHDCho() {
        LocalDateTime ldt = LocalDateTime.now();
        String dateNow = (DateTimeFormatter.ofPattern("MM-dd-yyyy", Locale.ENGLISH).format(ldt));
        String ngayTao = dateNow;
        int trangThai = 0;
        int maNV;

        if (Auth.isLogin()) {
            JOptionPane.showMessageDialog(this, "Vui lòng đăng nhập");
            return;
        }
        maNV = Auth.User.getMaNv();
        boolean check = banHangRepository.insertHoaDonCho(maNV, ngayTao, trangThai);

        if (check == true) {
            JOptionPane.showMessageDialog(this, "Tạo hóa đơn thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Tạo hóa đơn thất bại");
            return;
        }
    }

    // HỦy hóa đơn chờ
    void huyHoaDonCho() {
        int row = tblHoaDon.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn");
            return;
        }

        int check = JOptionPane.showConfirmDialog(this, "Bạn có muốn hủy hóa đơn ko", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (check == 0) {
            int maHD = (int) tblHoaDon.getValueAt(row, 0);

            banHangRepository.huyHoaDonCho(maHD);
            fillDSHDCho();
        }
    }

    // Fill danh sách sản phẩm giỏ hàng
    void fillGioHang(int maHD) {
        ArrayList<hoaDonChiTiet> listHDCT = (ArrayList<hoaDonChiTiet>) banHangRepository.getHDCT(maHD);
        DefaultTableModel model = (DefaultTableModel) tblGioHang.getModel();
        model.setRowCount(0);

        for (hoaDonChiTiet hdct : listHDCT) {
            Object[] data = {
                hdct.getMaCTSP(), hdct.getTenSP(), hdct.getSoLuong(), hdct.getDonGia(), hdct.getMucGiam(),
                hdct.getSoLuong() * hdct.getDonGia()
            };
            model.addRow(data);
        }
    }

    // add sản phẩm giỏ hàng
    void addGioHang() {
        int rowSelectedSP = tblDanhSachSP.getSelectedRow();
        int rowSelectedHD = tblHoaDon.getSelectedRow();

        int maHD;
        int maCTSP;
        int soLuongSP;
        int soLuongThem;
        int soLuongCuoi;
        float donGia;

        if (rowSelectedHD < 0) {
            JOptionPane.showMessageDialog(this, "Chua chon hoa don");
            return;
        }

        maHD = (int) tblHoaDon.getValueAt(rowSelectedHD, 0);
        maCTSP = (int) tblDanhSachSP.getValueAt(rowSelectedSP, 0);

        soLuongSP = (int) tblDanhSachSP.getValueAt(rowSelectedSP, 6);
        donGia = (float) tblDanhSachSP.getValueAt(rowSelectedSP, 7);
        String check = JOptionPane.showInputDialog(this, "Nhap so luong");
        if (check == null) {
            return;
        }
        try {
            soLuongThem = Integer.parseInt(check);
            if (soLuongThem <= 0) {
                JOptionPane.showMessageDialog(this, "So luong khong hop le");
                return;
            }
            if (soLuongThem > soLuongSP) {
                JOptionPane.showMessageDialog(this, "San pham khong du");
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Sai dinh dang");
            return;
        }
        ArrayList<hoaDonChiTiet> listHDCT = banHangRepository.getHDCT(maHD);
        soLuongCuoi = soLuongSP - soLuongThem;

        for (hoaDonChiTiet hdct : listHDCT) {
            if (hdct.getMaCTSP() == maCTSP) {
                int soLuongTiep = hdct.getSoLuong() + soLuongThem;
                float thanhtien = soLuongTiep * donGia;
                banHangRepository.updateHDCT(maHD, maCTSP, thanhtien, soLuongTiep);
                fillGioHang(maHD);
                banHangRepository.updateCTSP(maCTSP, soLuongCuoi);
                fillTableDanhSachSP();
                int tongTien = tinhTongTien();
                String partten = "###,###,###";
                DecimalFormat format = new DecimalFormat(partten);
                String tienTe = format.format(tongTien);
                lblTongTien.setText(tienTe);
                return;
            }
        }

        banHangRepository.insertHDCT(maCTSP, maHD, soLuongThem, donGia);
        fillGioHang(maHD);
        banHangRepository.updateCTSP(maCTSP, soLuongCuoi);
        fillTableDanhSachSP();
        int tongTien2 = tinhTongTien();
        String patternTienTe = "###,###,###";
        DecimalFormat format = new DecimalFormat(patternTienTe);
        String tienTe = format.format(tongTien2);
        lblTongTien.setText(tienTe);

    }

    // Tính tổng tiền
    int tinhTongTien() {
        int rowSelected = tblHoaDon.getSelectedRow();
        int maHD = (int) tblHoaDon.getValueAt(rowSelected, 0);
        ArrayList<hoaDonChiTiet> listHDCT = banHangRepository.getHDCT(maHD);
        int tongTien = 0;
        for (hoaDonChiTiet hdct : listHDCT) {
            tongTien = (int) (tongTien + hdct.getSoLuong() * (hdct.getDonGia() - hdct.getMucGiam()));
        }
        return tongTien;
    }

    // get row hóa đơn
    void getDataRowHoaDon(int rowSelected) {
        txtMaHD.setText(tblHoaDon.getValueAt(rowSelected, 0) + "");
        txtNgayTao.setText((String) tblHoaDon.getValueAt(rowSelected, 2));
        txtTenNV.setText((String) tblHoaDon.getValueAt(rowSelected, 1));
    }

    // delete sản phẩm giỏ hàng
    void deleteSPGH() {
        int rowSelected = tblHoaDon.getSelectedRow();
        int maHD = (int) tblHoaDon.getValueAt(rowSelected, 0);
        ArrayList<hoaDonChiTiet> listHDCT = banHangRepository.getHDCT(maHD);
        int rowSelectedGH = tblGioHang.getSelectedRow();
        int maCTSP = (int) tblGioHang.getValueAt(rowSelectedGH, 0);
        int soLuongXoa = (int) tblGioHang.getValueAt(rowSelectedGH, 2);
        int maHDCT = listHDCT.get(rowSelectedGH).getMaHDCT();
        int choice = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa sản phẩm này");
        if (choice == 0) {
            banHangRepository.deleteHDCT(maHDCT);
            int soLuongSp = banHangRepository.getSoLuongCTSP(maCTSP);
            int soLuongCuoi = soLuongXoa + soLuongSp;
            fillGioHang(maHD);
            banHangRepository.updateCTSP(maCTSP, soLuongCuoi);
            int tongTien = tinhTongTien();
            String partten = "###,###,###";
            DecimalFormat formatTienTe = new DecimalFormat(partten);
            String StringTienTe = formatTienTe.format(tongTien);
            lblTongTien.setText(StringTienTe);
        }
    }

    // delete all giỏ hàng
    void deleteAllGH() {
        ArrayList<hoaDonChiTiet> listMaCTSP = new ArrayList<>();
        int rowSelectedHD = tblHoaDon.getSelectedRow();
        if (rowSelectedHD < 0) {
            return;
        }

        for (int i = 0; i < tblGioHang.getRowCount(); i++) {
            int maCTSP = (int) tblGioHang.getValueAt(i, 0);
            int soLuong = (int) tblGioHang.getValueAt(i, 2);
            hoaDonChiTiet hdct = new hoaDonChiTiet();
            hdct.setMaCTSP(maCTSP);
            hdct.setSoLuong(soLuong);
            listMaCTSP.add(hdct);
        }

        for (int i = 0; i < listMaCTSP.size(); i++) {
            int soLuongSP = banHangRepository.getSoLuongCTSP(listMaCTSP.get(i).getMaCTSP());
            int soLuongCuoi = listMaCTSP.get(i).getSoLuong() + soLuongSP;
            banHangRepository.updateCTSP(listMaCTSP.get(i).getMaCTSP(), soLuongCuoi);
        }

        int maHD = (int) tblHoaDon.getValueAt(rowSelectedHD, 0);
        ArrayList<hoaDonChiTiet> listHDCT = banHangRepository.getHDCT(maHD);
        for (hoaDonChiTiet hdct : listHDCT) {
            banHangRepository.deleteHDCT(hdct.getMaHDCT());
        }
        fillGioHang(maHD);
        fillTableDanhSachSP();
        int tongTien = tinhTongTien();
        String patternTienTe = "###,###,###";
        DecimalFormat formatTienTe = new DecimalFormat(patternTienTe);
        String stringTienTe = formatTienTe.format(tongTien);
        lblTongTien.setText(stringTienTe);
    }

    // update gio hang
    void updateGioHang() {
        int rowSelectedHD = tblHoaDon.getSelectedRow();
        int rowSelectedGH = tblGioHang.getSelectedRow();
        int soLuongSua;

        if (rowSelectedGH < 0) {
            JOptionPane.showMessageDialog(this, "Chua chon san pham trong gio hang!");
            return;
        }

        int maHD = (int) tblHoaDon.getValueAt(rowSelectedHD, 0);
        int maCTSP = (int) tblGioHang.getValueAt(rowSelectedGH, 0);
        String check = JOptionPane.showInputDialog(this, "Nhập số lượng muốn sửa");
        if (check == null) {
            return;
        }

        int soLuongGH = (int) tblGioHang.getValueAt(rowSelectedGH, 2);
        try {
            soLuongSua = Integer.parseInt(check);
            int soLuongCapNhat = soLuongGH - soLuongSua;
            float donGia = (float) tblGioHang.getValueAt(rowSelectedGH, 3);
            if (soLuongSua <= 0) {
                JOptionPane.showMessageDialog(this, "So luong khong hop le");
                return;
            }

            int soLuongSPCT = banHangRepository.getSoLuongCTSP(maCTSP);
            int tongSoLuongSP = soLuongSPCT + soLuongGH;
            int soLuongCapNhatSP;

            if (soLuongSua > tongSoLuongSP) {
                JOptionPane.showMessageDialog(this, "So luong vuot qua so luong hien tai");
                return;
            }

            soLuongCapNhatSP = tongSoLuongSP - soLuongSua;
            banHangRepository.updateHDCT(maHD, maCTSP, donGia, soLuongSua);
            banHangRepository.updateCTSP(maCTSP, soLuongCapNhatSP);
            fillTableDanhSachSP();
            fillGioHang(maHD);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Chi nhap so");
            return;
        }

    }

    // lấy thông tin khách hàng
    void getKhachHang() {
        ArrayList<KhachHang> listkh = banHangRepository.getKhachHang();
        String sdt = txtSDTKhach.getText();
        for (KhachHang kh : listkh) {
            if (!kh.getSdt().equals(sdt)) {
                lblTenKhach.setText("Không tim thấy!");
            } else {
                if (kh.getTrangThai() == 1) {
                    lblTenKhach.setText(kh.getTenKH());
                    return;
                }
            }
        }
    }

    // Lấy hóa đơn và tính tiền
    ArrayList<Voucher> listKM = banHangRepository.getVoucher();

    Voucher loadVoucher() {
        String maKhuyenMai = txtMaVoucher.getText();
        if (maKhuyenMai == null) {
            txtTenVoucher.setText("");
            String patternTienTe = "###,###,###";
            DecimalFormat formartTienTe = new DecimalFormat(patternTienTe);
            String stringTienTe = formartTienTe.format(tinhTongTien());
            lblTongTien.setText(stringTienTe);
            return null;
        }
        for (Voucher km : listKM) {
            if (km.getMaGiam().equals(maKhuyenMai)) {
                txtTenVoucher.setText(km.getTenKhuyenMai());
                lblMucGiam.setText(km.getMucGiam() + " " + km.getDonVi());
                tinhKhuyenMai(km.getDonVi(), km.getMucGiam());
                return km;

            } else {
                txtTenVoucher.setText("Không tìm thấy");
                String patternTienTe = "###,###,###";
                DecimalFormat formatTienTe = new DecimalFormat(patternTienTe);
                String stringTienTe = formatTienTe.format(tinhTongTien());
                lblTongTien.setText(stringTienTe);
                lblMucGiam.setText("");
            }

        }

        return null;
    }

    // Tính khuyến mại voucher
    void tinhKhuyenMai(String donVi, float mucGiam) {
        float mucGiam1 = mucGiam;
        if (donVi.equals("VNĐ")) {
            mucGiam1 = tinhTongTien() - mucGiam1;
            if (mucGiam1 <= 0) {
                lblTongTien.setText(0 + "");
                return;
            }
            String patternTienTe = "###,###,###";
            DecimalFormat formatTienTe = new DecimalFormat(patternTienTe);
            String stringTienTe = formatTienTe.format(mucGiam1);
            lblTongTien.setText(stringTienTe);
        } else {
            mucGiam1 = tinhTongTien() * ((100 - mucGiam) / 100);
            if (mucGiam1 <= 0) {
                lblTongTien.setText(0 + "");
                return;
            }
            String patternTienTe = "###,###,###";
            DecimalFormat formatTienTe = new DecimalFormat(patternTienTe);
            String stringTienTe = formatTienTe.format(mucGiam1);
            lblTongTien.setText(stringTienTe);
        }

    }

    void clearForm() {
        DefaultTableModel model = (DefaultTableModel) tblGioHang.getModel();
        model.setRowCount(0);
        txtMaHD.setText("");
        txtTenNV.setText("");
        txtNgayTao.setText("");
        txtSDTKhach.setText("");
        lblTenKhach.setText("");
        lblTongTien.setText("");
        txtMaVoucher.setText("");
        txtTenVoucher.setText("");
        lblMucGiam.setText("");
        cboHinhThucThanhToan.setSelectedIndex(0);
        txtTienKhachDua.setText("");
        lblTienThua.setText("");
    }

//    
//    
//    
    void hinhThucThanhToan() {
        int rowSelected = tblHoaDon.getSelectedRow();
        if (rowSelected < 0) {
            return;
        }

        if (cboHinhThucThanhToan.getSelectedIndex() == 0) {
            if (loadVoucher() == null) {
                int tongTien = tinhTongTien();
                txtTienKhachDua.setEditable(false);
                txtTienKhachDua.setText(tongTien + " ");
                tinhtienThua();
            } else {
                float khuyenMai = loadVoucher().getMucGiam();
                if (loadVoucher().getDonVi().equals("VNĐ")) {
                    khuyenMai = tinhTongTien() - khuyenMai;
                    if (khuyenMai <= 0) {
                        khuyenMai = 0;
                    }
                } else {
                    khuyenMai = tinhTongTien() * ((100 - khuyenMai) / 100);
                    if (khuyenMai <= 0) {
                        khuyenMai = 0;
                    }
                }
                txtTienKhachDua.setEditable(false);
                txtTienKhachDua.setText(khuyenMai + "");
                tinhtienThua();
            }

        } else {
            txtTienKhachDua.setEditable(true);
        }
    }

//Tính tiền thừa
    void tinhtienThua() {
        int rowSelected = tblHoaDon.getSelectedRow();
        if (rowSelected < 0) {
            return;
        }
        if (txtTienKhachDua.getText().isEmpty()) {
            lblTienThua.setText("");
            return;
        }

        int tongTien = tinhTongTien();
        float tienKhachDua;

        try {
            tienKhachDua = Float.parseFloat(txtTienKhachDua.getText());
            if (loadVoucher() != null) {
                Voucher km = loadVoucher();
                float giamGia = km.getMucGiam();
                String donVi = km.getDonVi();
                if (donVi.equals("VNĐ")) {
                    giamGia = tinhTongTien() - giamGia;
                    float tienThua = tienKhachDua - giamGia;
                    if (tienThua < 0) {
                        lblTienThua.setText("");
                        return;
                    }
                    if (tienThua == 0) {
                        lblTienThua.setText("0");
                        return;
                    }
                    String patternTienTe = "###,###,###";
                    DecimalFormat formatTienTe = new DecimalFormat(patternTienTe);
                    String stringTienTe = formatTienTe.format(tienThua);
                    lblTienThua.setText(stringTienTe + "");
                    return;
                } else {
                    giamGia = tinhTongTien() * ((100 - km.getMucGiam()) / 100);
                    float tienThua = tienKhachDua - giamGia;
                    if (tienThua < 0) {
                        lblTienThua.setText("");
                        return;
                    }
                    if (tienThua == 0) {
                        lblTienThua.setText("0");
                        return;
                    }
                    String patternTienTe = "###,###,###";
                    DecimalFormat formatTienTe = new DecimalFormat(patternTienTe);
                    String stringTienTe = formatTienTe.format(tienThua);
                    lblTienThua.setText(stringTienTe + "");
                    return;
                }
            }
            float tienThua = tienKhachDua - tongTien;
            if (tienThua < 0) {
                lblTienThua.setText("");
                return;
            }
            if (tienThua == 0) {
                lblTienThua.setText("0");
                return;
            }
            String patternTienTe = "###,###,###";
            DecimalFormat formatTienTe = new DecimalFormat(patternTienTe);
            String stringTienTe = formatTienTe.format(tienThua);
            lblTienThua.setText(stringTienTe + "");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Chỉ Nhập số");
            txtTienKhachDua.setText("");
            lblTienThua.setText("");
        }

    }

    void updateHoaDon(boolean trangThai, String message) {
        ArrayList<KhachHang> listkh = banHangRepository.getKhachHang();
        if (tblGioHang.getRowCount() <= 0) {
            MsgBox.alert(this, "Thanh toán thất bại: \n Hóa đơn trống");
//JOptionPane.showMessageDialog(this, "Thanh toán thất bại: \n Hóa đơn trống");
            return;
        }
        LocalDateTime ldt = LocalDateTime.now();
        String dateNow = (DateTimeFormatter.ofPattern("MM-dd-yyyy", Locale.ENGLISH).format(ldt));
        String ngayTao = dateNow;
        String sdt = txtSDTKhach.getText();
        String tienThua = lblTienThua.getText();
        if (tienThua.isEmpty()) {
            MsgBox.alert(this, "Chưa đủ tiền thanh toán");
            return;
        }

        int rowSelected = tblHoaDon.getSelectedRow();
        if (rowSelected < 0) {
            MsgBox.alert(this, "Chưa chọn hóa đơn thao tác");
            return;
        }
        int maHD = (int) tblHoaDon.getValueAt(rowSelected, 0);
        float tongTien = tinhTongTien();
        int maTTkH = 0;
        String hinThucThanhToan = cboHinhThucThanhToan.toString();
        for (KhachHang kh : listkh) {
            if (kh.getSdt().equals(sdt)) {
                maTTkH = kh.getMaKH();
            }
        }

        int choice = JOptionPane.showConfirmDialog(this, message, "Sneaker-Store", JOptionPane.YES_NO_OPTION);
        if (choice == 0) {
            if (loadVoucher() == null) {
                if (maTTkH == 0) {
                    banHangRepository.updateHoaDon2(maHD, ngayTao, trangThai, tongTien);
                    banHangRepository.insertThanhToan2(maHD, hinThucThanhToan);
                    fillDSHDCho();
                    clearForm();
                    return;
                }
                banHangRepository.updateHoaDon(maHD, ngayTao, trangThai, tongTien, maTTkH);
                banHangRepository.insertThanhToan(maHD, maTTkH, hinThucThanhToan);
                txtSDTKhach.setText("");
                lblTenKhach.setText("");
                fillDSHDCho();
                clearForm();
                return;
            } else {
                float giamGia = loadVoucher().getMucGiam();
                if (loadVoucher().getDonVi().equals("VNĐ")) {
                    giamGia = tinhTongTien() - giamGia;
                    if (giamGia <= 0) {
                        giamGia = 0;
                    }
                } else {
                    giamGia = tinhTongTien() * ((100 - giamGia) / 100);
                    if (giamGia <= 0) {
                        giamGia = 0;
                    }
                }

                if (maTTkH == 0) {
                    banHangRepository.updateHoaDon2(maHD, ngayTao, trangThai, tongTien);
                    banHangRepository.insertThanhToan2(maHD, hinThucThanhToan);
                    banHangRepository.insertHDKM(maHD, loadVoucher().getMaKM(), giamGia);
                    fillDSHDCho();
                    clearForm();
                    return;
                }
                banHangRepository.updateHoaDon(maHD, ngayTao, trangThai, tongTien, maTTkH);
                banHangRepository.insertThanhToan(maHD, maTTkH, hinThucThanhToan);
                banHangRepository.insertHDKM(maHD, loadVoucher().getMaKM(), giamGia);
                txtSDTKhach.setText("");
                lblTenKhach.setText("");
                fillDSHDCho();
                clearForm();

            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup3 = new javax.swing.ButtonGroup();
        jpnNavigation = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        btnBanHang = new javax.swing.JButton();
        btnHoaDon = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblGioHang = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDanhSachSP = new javax.swing.JTable();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtTenNV = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtNgayTao = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtSDTKhach = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        lblTenKhach = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtMaVoucher = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        lblMucGiam = new javax.swing.JLabel();
        cboHinhThucThanhToan = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtTienKhachDua = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        lblTienThua = new javax.swing.JLabel();
        btnTaoHD = new javax.swing.JButton();
        btnHuyHD = new javax.swing.JButton();
        btnThanhToan = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        txtTenVoucher = new javax.swing.JTextField();
        rdoDaThanhToan = new javax.swing.JRadioButton();
        rdoChuaThanhToan = new javax.swing.JRadioButton();
        btnXoa1 = new javax.swing.JButton();

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

        btnHome.setBackground(new java.awt.Color(255, 255, 153));
        btnHome.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnHome.setText("Home");
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
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

        btnHoaDon.setBackground(new java.awt.Color(204, 204, 255));
        btnHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnHoaDon.setForeground(new java.awt.Color(0, 0, 255));
        btnHoaDon.setText("Hóa Đơn");
        btnHoaDon.setBorder(new javax.swing.border.MatteBorder(null));
        btnHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoaDonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnNavigationLayout = new javax.swing.GroupLayout(jpnNavigation);
        jpnNavigation.setLayout(jpnNavigationLayout);
        jpnNavigationLayout.setHorizontalGroup(
            jpnNavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpnNavigationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnNavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBanHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnHoaDon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jpnNavigationLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(btnHome)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnNavigationLayout.setVerticalGroup(
            jpnNavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnNavigationLayout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnHome)
                .addGap(25, 25, 25))
        );

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Quản lý bán hàng");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(354, 354, 354)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 204));

        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã SPCT", "Tên SP", "Số Lượng", "Đơn giá", "Giảm giá", "Thành tiền"
            }
        ));
        jScrollPane2.setViewportView(tblGioHang);

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã HD", "Nhân viên bán hàng", "Ngày tạo", "Trạng thái"
            }
        ));
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDon);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Hóa đơn");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Giỏ hàng");

        jLabel13.setText("Tìm kiếm sản phẩm");

        jLabel4.setText("Danh sách sản phẩm");

        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });

        tblDanhSachSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SPCT", "Tên SP", "Size", "Màu", "Chất liệu", "Chất liệu đế", " Số lượng còn", "Đơn giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDanhSachSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhSachSPMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblDanhSachSPMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(tblDanhSachSP);

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 255));
        jLabel7.setText("Thông tin hóa đơn");

        jLabel8.setText("Mã HD");

        jLabel9.setText("Tên NV");

        jLabel10.setText("Ngày tạo");

        jLabel11.setText("SDT khách");

        txtSDTKhach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTKhachActionPerformed(evt);
            }
        });
        txtSDTKhach.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSDTKhachKeyReleased(evt);
            }
        });

        jLabel12.setText("Tên khách");

        lblTenKhach.setText("  ");
        lblTenKhach.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 0, 0)));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 0, 0));
        jLabel14.setText("Tổng tiền: ");

        lblTongTien.setText("  ");
        lblTongTien.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 51, 51)));

        jLabel16.setText("Mã Voucher");

        txtMaVoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaVoucherActionPerformed(evt);
            }
        });
        txtMaVoucher.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaVoucherKeyReleased(evt);
            }
        });

        jLabel17.setText("Mức giảm:");

        lblMucGiam.setText("  ");
        lblMucGiam.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 0, 0)));

        cboHinhThucThanhToan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboHinhThucThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboHinhThucThanhToanActionPerformed(evt);
            }
        });

        jLabel19.setText("Hình thức thanh toán");

        jLabel20.setText("Tiền khách đưa:");

        txtTienKhachDua.setText(" ");
        txtTienKhachDua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienKhachDuaActionPerformed(evt);
            }
        });
        txtTienKhachDua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTienKhachDuaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienKhachDuaKeyReleased(evt);
            }
        });

        jLabel21.setText("Tiền thừa");

        lblTienThua.setText(" ");
        lblTienThua.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 0, 0)));

        btnTaoHD.setText("Tạo HD");
        btnTaoHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHDActionPerformed(evt);
            }
        });

        btnHuyHD.setText("Hủy HD");
        btnHuyHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyHDActionPerformed(evt);
            }
        });

        btnThanhToan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconSwing/Custom-Icon-Design-Flatastic-11-Cash.32.png"))); // NOI18N
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        jLabel22.setText("Voucher");

        txtTenVoucher.setText(" ");
        txtTenVoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenVoucherActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnTaoHD)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnHuyHD))
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(26, 26, 26)
                                .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(27, 27, 27)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel11)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtSDTKhach))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel12)
                            .addGap(18, 18, 18)
                            .addComponent(lblTenKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel17)
                            .addGap(18, 18, 18)
                            .addComponent(lblMucGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel16)
                                .addComponent(jLabel22))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtTenVoucher)
                                .addComponent(txtMaVoucher))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel21)
                            .addComponent(jLabel20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTienKhachDua)
                            .addComponent(cboHinhThucThanhToan, 0, 169, Short.MAX_VALUE)
                            .addComponent(lblTienThua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtSDTKhach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTenKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txtTenVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtMaVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMucGiam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboHinhThucThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(lblTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(lblTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnHuyHD)
                    .addComponent(btnTaoHD, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        buttonGroup3.add(rdoDaThanhToan);
        rdoDaThanhToan.setText("Đã thanh toán");
        rdoDaThanhToan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoDaThanhToanMouseClicked(evt);
            }
        });

        buttonGroup3.add(rdoChuaThanhToan);
        rdoChuaThanhToan.setText("Chưa thanh toán");
        rdoChuaThanhToan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoChuaThanhToanMouseClicked(evt);
            }
        });

        btnXoa1.setText("Xóa All");
        btnXoa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel1))
                            .addComponent(jLabel3)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnSua)
                                            .addComponent(btnXoa)))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnXoa1))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel4))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(114, 114, 114)
                                .addComponent(jLabel13)
                                .addGap(50, 50, 50)
                                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 863, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 712, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(rdoDaThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(rdoChuaThanhToan)
                                .addGap(8, 8, 8)))))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(jLabel3)
                                .addGap(6, 6, 6)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addComponent(btnSua)
                                        .addGap(27, 27, 27)
                                        .addComponent(btnXoa)
                                        .addGap(24, 24, 24)
                                        .addComponent(btnXoa1)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(jLabel4)
                                .addGap(16, 16, 16)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13))
                                .addGap(12, 12, 12)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(rdoDaThanhToan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdoChuaThanhToan)
                                .addGap(0, 0, Short.MAX_VALUE))))))
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

    private void btnBanHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBanHangActionPerformed
        setVisible(false);
        new JFrameBanHang().setVisible(true);
    }//GEN-LAST:event_btnBanHangActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        setVisible(false);
        new JFrameMain().setVisible(true);
    }//GEN-LAST:event_btnHomeActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoaDonActionPerformed
        setVisible(false);
        new JFrameHoaDon().setVisible(true);
    }//GEN-LAST:event_btnHoaDonActionPerformed

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        // TODO add your handling code here:
        int rowSelected = tblHoaDon.getSelectedRow();
        if (rowSelected < 0) {
            txtMaVoucher.setEditable(false);
            return;
        } else {
            txtMaVoucher.setEnabled(true);
            txtTenVoucher.setEnabled(false);
            txtMaHD.setEnabled(false);
            txtTenNV.setEnabled(false);
            txtNgayTao.setEnabled(false);
        }
        getDataRowHoaDon(rowSelected);

        int maHD = (int) tblHoaDon.getValueAt(rowSelected, 0);
        fillGioHang(maHD);
        int tongTien = tinhTongTien();
        String patternTienTe = "###,###,###";
        DecimalFormat formatTienTe = new DecimalFormat(patternTienTe);
        String stringTienTe = formatTienTe.format(tongTien);
        lblTongTien.setText(stringTienTe);

    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void tblDanhSachSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachSPMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblDanhSachSPMouseClicked

    private void tblDanhSachSPMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachSPMousePressed
        addGioHang();
    }//GEN-LAST:event_tblDanhSachSPMousePressed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int rowSelected = tblHoaDon.getSelectedRow();
        int rowSelected2 = tblGioHang.getSelectedRow();

        if (rowSelected < 0) {
            JOptionPane.showMessageDialog(this, "Chua chon hoa don!");
            return;
        }
        if (rowSelected2 < 0) {
            JOptionPane.showMessageDialog(this, "Chua chon san pham thao tac!");
            return;
        }
        deleteSPGH();
        fillTableDanhSachSP();

    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        int rowSelected = tblHoaDon.getSelectedRow();
        if (rowSelected < 0) {
            JOptionPane.showMessageDialog(this, "Chua chon hoa don");
            return;
        }
        updateGioHang();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void txtSDTKhachKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSDTKhachKeyReleased
        getKhachHang();
    }//GEN-LAST:event_txtSDTKhachKeyReleased

    private void txtMaVoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaVoucherActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaVoucherActionPerformed

    private void txtMaVoucherKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaVoucherKeyReleased
        loadVoucher();
    }//GEN-LAST:event_txtMaVoucherKeyReleased

    private void cboHinhThucThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboHinhThucThanhToanActionPerformed
        hinhThucThanhToan();
    }//GEN-LAST:event_cboHinhThucThanhToanActionPerformed

    private void txtTienKhachDuaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKhachDuaKeyPressed

    }//GEN-LAST:event_txtTienKhachDuaKeyPressed

    private void txtTienKhachDuaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKhachDuaKeyReleased
        // TODO add your handling code here:
        tinhtienThua();
    }//GEN-LAST:event_txtTienKhachDuaKeyReleased

    private void btnTaoHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHDActionPerformed
        // TODO add your handling code here:
        if (tblHoaDon.getRowCount() == 10) {
            JOptionPane.showMessageDialog(this, "Tạo hóa đơn mới thất bại: \nSố Lượng hóa đơn chờ đạt giới hạn");
            return;
        }
        insertHDCho();
        fillDSHDCho();
    }//GEN-LAST:event_btnTaoHDActionPerformed

    private void btnHuyHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyHDActionPerformed
        huyHoaDonCho();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHuyHDActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // TODO add your handling code here:
        updateHoaDon(true, "Xác nhận thanh toán hóa đơn");
        JOptionPane.showMessageDialog(this, "Xác nhận thanh toán hóa đơn");
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void txtTenVoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenVoucherActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenVoucherActionPerformed

    private void rdoDaThanhToanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoDaThanhToanMouseClicked
        fillDSHDCho();
    }//GEN-LAST:event_rdoDaThanhToanMouseClicked

    private void rdoChuaThanhToanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoChuaThanhToanMouseClicked
        fillDSHDCho();
    }//GEN-LAST:event_rdoChuaThanhToanMouseClicked

    private void btnXoa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa1ActionPerformed
        deleteAllGH();
    }//GEN-LAST:event_btnXoa1ActionPerformed

    private void txtSDTKhachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTKhachActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtSDTKhachActionPerformed

    private void txtTienKhachDuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienKhachDuaActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtTienKhachDuaActionPerformed

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
            java.util.logging.Logger.getLogger(JFrameBanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameBanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameBanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameBanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameBanHang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBanHang;
    private javax.swing.JButton btnHoaDon;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnHuyHD;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnTaoHD;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXoa1;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JComboBox<String> cboHinhThucThanhToan;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel jpnNavigation;
    private javax.swing.JLabel lblMucGiam;
    private javax.swing.JLabel lblTenKhach;
    private javax.swing.JLabel lblTienThua;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JRadioButton rdoChuaThanhToan;
    private javax.swing.JRadioButton rdoDaThanhToan;
    private javax.swing.JTable tblDanhSachSP;
    private javax.swing.JTable tblGioHang;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtMaVoucher;
    private javax.swing.JTextField txtNgayTao;
    private javax.swing.JTextField txtSDTKhach;
    private javax.swing.JTextField txtTenNV;
    private javax.swing.JTextField txtTenVoucher;
    private javax.swing.JTextField txtTienKhachDua;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables

}
