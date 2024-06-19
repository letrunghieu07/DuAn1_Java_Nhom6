/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;
import model.KhuyenMai;
//import views.quanLyKM;
import utilities.JdbcHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

public class KhuyenMaiRepository {
   
      public ArrayList getKhuyenMai () {
        ArrayList<KhuyenMai> listKhuyenMai = new ArrayList<>();
        String query = "select * from KHUYEN_MAI";
        try {
            Connection conn = JdbcHelper.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {                
                KhuyenMai kM =  new KhuyenMai();
                kM.setMaKM(rs.getInt("MaKM"));
                kM.setTenKhuyenMai(rs.getString("TenKhuyenMai"));
                kM.setNgayBD(rs.getDate("NgayBD"));
                kM.setNgayKT(rs.getDate("NgayKT"));
                kM.setMucGiam(rs.getFloat("MucGiam"));
                kM.setMaGiam(rs.getString("MaGiam"));
                if(rs.getInt("TrangThai") == 0) {
                    kM.setTrangThai("Đang hoạt động");
                } else {
                    kM.setTrangThai("Không hoạt động");
                }
                if(rs.getInt("DonVi") == 0) {
                    kM.setDonVi("VNĐ");
                } else {
                    kM.setDonVi("%");
                }
                listKhuyenMai.add(kM);
            }
            return listKhuyenMai;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
    
    public boolean insertKhuyenMai(String tenKhuyenMai, Date ngayBD, Date ngayKT, float mucGiam, String maGiam, int trangThai, int donVi) {
        String query = "insert into KHUYEN_MAI(TenKhuyenMai, NgayBD, NgayKT, MucGiam,DonVi,MaGiam, TrangThai)\n" +
                        "values(?, ?, ?, ?, ?,?, ?)";
        try {
            Connection conn = JdbcHelper.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1,tenKhuyenMai);
            pstmt.setDate(2,  ngayBD);
            pstmt.setDate(3,  ngayKT);
            pstmt.setFloat(4, mucGiam);
             pstmt.setInt(5, donVi);
            pstmt.setString(6, maGiam);
            pstmt.setInt(7, trangThai);
           
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    public boolean deleteKhuyenMai(int maKM) {
        String query = "DELETE FROM KHUYEN_MAI WHERE MaKM = ?";
        try {
            Connection conn = JdbcHelper.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, maKM);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    
    public boolean updateKhuyenMai(int maKM,String tenKhuyenMai, Date ngayBD, Date ngayKT, float mucGiam, String maGiam, int trangThai, int donVi) {
        String query = "Update KHUYEN_MAI\n" +
                    "set TenKhuyenMai = ?, NgayBD = ?, NgayKT= ?, MucGiam = ?,MaGiam = ?, TrangThai = ?, DonVi = ?\n" +
                    "where MaKM = ?";
        try {
            Connection conn = JdbcHelper.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1,tenKhuyenMai);
            pstmt.setDate(2,  ngayBD);
            pstmt.setDate(3,  ngayKT);
            pstmt.setFloat(4, mucGiam);
            pstmt.setString(5, maGiam);
            pstmt.setInt(6, trangThai);
            pstmt.setInt(7, donVi);
            pstmt.setInt(8, maKM);
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    
     public boolean kiemTraMaApDung(String maApDung) {
    String query = "SELECT COUNT(*) FROM KHUYEN_MAI WHERE MaGiam = ?";
    try {
        Connection conn = JdbcHelper.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, maApDung);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            int count = rs.getInt(1);
            return count > 0;
        }
    } catch (SQLException e) {
        System.out.println(e);
    }
    return false;
}
    
}
