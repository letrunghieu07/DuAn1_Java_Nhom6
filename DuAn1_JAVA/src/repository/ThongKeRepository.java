/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.hoaDon;
import utilities.JdbcHelper;

/**
 *
 * @author trung
 */
public class ThongKeRepository {

    //load hoa don chờ
    public ArrayList<hoaDon> getHoaDonCho(int trangThai) {
        ArrayList<hoaDon> listHDCho = new ArrayList<>();
        String query = """
               select HOA_DON.MaHD, NHAN_VIEN.HoTen, HOA_DON.NgayTao, HOA_DON.TrangThai 
               from HOA_DON 
               join NHAN_VIEN on HOA_DON.MaNV= NHAN_VIEN.MaNV
               where HOA_DON.TrangThai like ?
               order by MaHD desc """;

        try {
            Connection conn = JdbcHelper.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, "%" + trangThai + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                hoaDon hd = new hoaDon();
                hd.setMaHD(rs.getInt("MaHD"));
                hd.setTenNV(rs.getString("HoTen"));
                hd.setNgayTao(rs.getString("ngayTao"));
                hd.setTrangThai(rs.getBoolean("trangThai"));
                listHDCho.add(hd);
            }
            System.out.println("getdata");
            return listHDCho;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    // fill tổng số hóa đơn
    public int tongHoaDon() {
        int totalInvoices = 0;
        String sql = "SELECT COUNT(*) AS tongHoaDon FROM HOA_DON";

        try {
            Connection conn = JdbcHelper.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                totalInvoices = rs.getInt("tongHoaDon");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return totalInvoices;
    }

    // Đã thanh toán
    public int daThanhToan() {
        int totalPaidInvoices = 0;
        String sql = "SELECT COUNT(*) AS tongHoaDon FROM HOA_DON WHERE TrangThai = 1";

        try {
            Connection conn = JdbcHelper.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                totalPaidInvoices = rs.getInt("tongHoaDon");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return totalPaidInvoices;
    }

    // Hoad đơn chờ
    public int hoaDonCho() {
        int totalUnpaidInvoices = 0;
        String sql = "SELECT COUNT(*) AS tongHoaDon FROM HOA_DON WHERE TrangThai = 0";

        try {
            Connection conn = JdbcHelper.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                totalUnpaidInvoices = rs.getInt("tongHoaDon");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return totalUnpaidInvoices;
    }

    // Tổng doanh thu
    public Float sumTotal() {
        Float total = 0.0f;
        String sql = "SELECT SUM(TongTien) AS total_sum FROM HOA_DON WHERE TrangThai = 1;";
        try {
            Connection conn = JdbcHelper.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                total = rs.getFloat("total_sum");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return total;
    }
    
    
    // Tổng sp đã bán
    public int tongSanPhamDaBan() {
        int totalQuantity = 0;
        String sql = "SELECT SUM(SoLuong) AS TongSoLuong FROM HOA_DON_CHI_TIET "
                + "JOIN HOA_DON ON HOA_DON_CHI_TIET.MaHD = HOA_DON.MaHD "
                + "WHERE HOA_DON.TrangThai = 1";

        try {
            Connection conn = JdbcHelper.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                totalQuantity = resultSet.getInt("TongSoLuong");
            }

         } catch (Exception e) {
            e.printStackTrace();
        }

        return totalQuantity;
    }

}
