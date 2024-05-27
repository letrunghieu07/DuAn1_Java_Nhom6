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
import model.hoaDonChiTiet;
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

    // insert hóa đơn chờ
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
    
    // delete hóa đơn chờ
    public boolean huyHoaDonCho(int maDH) {
        String query = "delete from hoa_don where maHD =?";
        try {
            Connection conn = JdbcHelper.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, maDH);
            stmt.execute();
            return true;
        } catch (Exception e) {
            //e.printStackTrace();
            return false;
        }
    }
    
    // Hiển thị hóa đơn chi tiết
    public ArrayList<hoaDonChiTiet> getHDCT(int maHD) {
        String query = """
                      SELECT MaHDCT, MaHD, CHI_TIET_SAN_PHAM.MaCTSP,
                                            SAN_PHAM.TenSP,HOA_DON_CHI_TIET.SoLuong,
                                            HOA_DON_CHI_TIET.DonGia,Giam_Gia.MucGiam
                      
                      FROM HOA_DON_CHI_TIET join CHI_TIET_SAN_PHAM on HOA_DON_CHI_TIET.MaCTSP = CHI_TIET_SAN_PHAM.MaCTSP
                                        join SAN_PHAM on SAN_PHAM.MaSP = CHI_TIET_SAN_PHAM.MaSP
                                        join Giam_Gia on Giam_Gia.maGG= CHI_TIET_SAN_PHAM.maGG
                                        where MaHD =?""";
        ArrayList<hoaDonChiTiet> listHDCT = new ArrayList<>();
        try {
            Connection conn = JdbcHelper.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, maHD);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                hoaDonChiTiet hdct = new hoaDonChiTiet();

                hdct.setMaHDCT(rs.getInt("MaHDCT"));
                hdct.setMaHD(rs.getInt("MaHD"));
                hdct.setMaCTSP(rs.getInt("MaCTSP"));
                hdct.setTenSP(rs.getString("TenSP"));
                hdct.setSoLuong(rs.getInt("SoLuong"));
                hdct.setDonGia(rs.getFloat("DonGia"));
                hdct.setMucGiam(rs.getInt("MucGiam"));
                listHDCT.add(hdct);
            }
            return listHDCT;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // update hóa đơn chi tiết
    public boolean updateHDCT(int maHD, int maCTSP, float donGia, int soLuong) {
        String query = """
                      update HOA_DON_CHI_TIET
                                             set SoLuong = ?, DonGia = ? where MaHD = ? and MaCTSP = ?""";

        try {
            Connection conn = JdbcHelper.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, soLuong);
            stmt.setFloat(2, donGia);
            stmt.setInt(3, maHD);
            stmt.setInt(4, maCTSP);
            stmt.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // update chi tiết sản phẩm
    public boolean updateCTSP(int maCTSP, int soLuong) {
        String query = "update CHI_TIET_SAN_PHAM\n"
                + "                       set SoLuong = ? WHERE MaCTSP = ?";
        try {
            Connection conn = JdbcHelper.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, soLuong);
            pstmt.setInt(2, maCTSP);
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    
    // insert hóa đơn chi tiêt
    public boolean insertHDCT(int maCTSP, int maHD, int soLuong, float donGia) {
        String query = "insert into HOA_DON_CHI_TIET(MaCTSP, MaHD, SoLuong, DonGia)\n"
                + "                       values(?,?,?,?)";
        System.out.println("aaaaa");
        try {
            Connection conn = JdbcHelper.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, maCTSP);
            pstmt.setInt(2, maHD);
            pstmt.setInt(3, soLuong);
            pstmt.setFloat(4, donGia);
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
    
    // delete HDCT
     public boolean deleteHDCT(int maHDCT) {
        String query = "delete Hoa_Don_Chi_Tiet where maHDCT =?";

        try {
            Connection conn = JdbcHelper.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, maHDCT);
            stmt.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
     
     
     // load Số lượng CTSP
     public int getSoLuongCTSP(int maCTSP) {
        int soLuong = 0;
        String query = "select soluong from chi_tiet_san_pham where maCTSP =?";

        try {
            Connection conn = JdbcHelper.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, maCTSP);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                soLuong = rs.getInt("soluong");
            }
            return soLuong;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


}
