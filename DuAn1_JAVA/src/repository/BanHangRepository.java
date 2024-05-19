/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.chiTietSanPham;
import model.hoaDon;
import utilities.JdbcHelper;

/**
 *
 * @author trung
 */
public class BanHangRepository {

    JdbcHelper jdbcHelper = new JdbcHelper();

    //lấy all chi tiet sản phẩm
    public List<chiTietSanPham> listAll() {
        List<chiTietSanPham> listAll = new ArrayList<>();
        String query = """
                   select CHI_TIET_SAN_PHAM.MaCTSP, SAN_PHAM.TenSP, CHAT_LIEU_DE_GIAY.TenChatLieuDe, 
                                                             SIZE.KichThuoc, MAU_SAC.TenMau, CHAT_LIEU.TenChatLieu,  
                                                             CHI_TIET_SAN_PHAM.DonGia,CHI_TIET_SAN_PHAM.SoLuong, Giam_Gia.MucGiam,CHI_TIET_SAN_PHAM.mota 
                                                             from CHI_TIET_SAN_PHAM 
                                                             
                                                             join SAN_PHAM on SAN_PHAM.MaSP =  CHI_TIET_SAN_PHAM.MaSP 
                                                             join CHAT_LIEU_DE_GIAY on CHAT_LIEU_DE_GIAY.MaCLDe  =  CHI_TIET_SAN_PHAM.MaCLDe
                                                             join SIZE on SIZE.MaSize =  CHI_TIET_SAN_PHAM.MaSize
                                                             join MAU_SAC on MAU_SAC.MaMS =  CHI_TIET_SAN_PHAM.MaMS
                                                             Left join Giam_Gia on Giam_Gia.MaGG= CHI_TIET_SAN_PHAM.MaGG
                                                             join CHAT_LIEU on CHAT_LIEU.MaCL =  CHI_TIET_SAN_PHAM.MaCL
                                                             where SAN_PHAM.TrangThai=1
                   """;
        Connection conn = null;
        try {
            conn = JdbcHelper.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                chiTietSanPham CTSP = new chiTietSanPham();
                CTSP.setMaCTSP(rs.getInt("MaCTSP"));
                CTSP.setTenSP(rs.getString("TenSP"));
                CTSP.setTenCLDe(rs.getString("TenChatLieuDe"));
                CTSP.setTenSize(rs.getInt("KichThuoc"));
                CTSP.setTenMS(rs.getString("TenMau"));
                CTSP.setTenCL(rs.getString("TenChatLieu"));
                CTSP.setDonGia(rs.getFloat("DonGia"));
                CTSP.setSoLuong(rs.getInt("SoLuong"));
                CTSP.setMucGG(rs.getFloat("MucGiam"));
                CTSP.setMoTa(rs.getString("mota"));
                listAll.add(CTSP);
            }
        } catch (Exception e) {
        }

        return listAll;

    }

    //load HTTT
    public ArrayList getHTTT() {
        String query = "select * from Thanh_Toan";
        ArrayList listTT = new ArrayList<>();
        try {
            Connection conn = JdbcHelper.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("MaTT");
                String hinThuc = rs.getString("HinhThucThanhToan");
                // hinhThucThanhToan httt = new hinhThucThanhToan(id, hinThuc);
                listTT.add(hinThuc);
            }
            return listTT;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Load hóa đơn 
    public ArrayList<hoaDon> getAllHD() {
        String query = """
               select HOA_DON.MaHD, NHAN_VIEN.HoTen, HOA_DON.NgayTao, HOA_DON.TrangThai 
               from HOA_DON 
               join THONG_TIN_KH on HOA_DON.MaTTKH= THONG_TIN_KH.MaTTKH
               join NHAN_VIEN on HOA_DON.MaNV= NHAN_VIEN.MaNV
               where HOA_DON.TrangThai = ?""";

        ArrayList<hoaDon> listAll = new ArrayList<>();
        try {
            Connection conn = JdbcHelper.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                hoaDon hd = new hoaDon();
                hd.setMaHD(rs.getInt("MaHD"));
                hd.setTenNV(rs.getString("HoTen"));
                hd.setNgayTao(rs.getString("ngayTao"));
                hd.setTrangThai(rs.getBoolean("trangThai"));
                listAll.add(hd);
            }
            return listAll;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

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

    public boolean insertHoaDonCho(int maNV, String ngayTao, int trangThai) {
        String query = """
                   INSERT INTO HOA_DON
                          (MaNV, NgayTao, TrangThai)
                   VALUES
                          (?, ?, ?)""";
        try {
            Connection conn = JdbcHelper.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, maNV);
            stmt.setString(2, ngayTao);
            stmt.setInt(3, trangThai);
            stmt.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
