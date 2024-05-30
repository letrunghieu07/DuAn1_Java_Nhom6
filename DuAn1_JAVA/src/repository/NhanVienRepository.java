/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.NhanVien;
import utilities.JdbcHelper;

/**
 *
 * @author trung
 */
public class NhanVienRepository {

    private Connection connection;

    
    public NhanVienRepository() {
        try {
            this.connection = JdbcHelper.getConnection();
        } catch (SQLServerException ex) {
            Logger.getLogger(NhanVienRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Đọc thông tin nhân viên bằng ID
    public NhanVien getNhanVienById(int id) {
        String query = "SELECT * FROM NHAN_VIEN WHERE MaNV = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int maNV = resultSet.getInt("MaNV");
                String tenDangNhap = resultSet.getString("TenDangNhap");
                String matKhau = resultSet.getString("MatKhau");
                String hoTen = resultSet.getString("HoTen");
                int gioiTinh = resultSet.getInt("GioiTinh");
                String dienThoai = resultSet.getString("DienThoai");
                String email = resultSet.getString("Email");
                java.sql.Date ngayTao = resultSet.getDate("NgayTao");
                int chucVu = resultSet.getInt("ChucVu");
                int trangThai = resultSet.getInt("TrangThai");

                return new NhanVien(maNV, tenDangNhap, matKhau, hoTen, gioiTinh, dienThoai, email, ngayTao, chucVu, trangThai);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Lấy danh sách tất cả nhân viên
    public List<NhanVien> getAllNhanViens() {
        String query = "SELECT * FROM NHAN_VIEN";
        List<NhanVien> nhanViens = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int maNV = resultSet.getInt("MaNV");
                String tenDangNhap = resultSet.getString("TenDangNhap");
                String matKhau = resultSet.getString("MatKhau");
                String hoTen = resultSet.getString("HoTen");
                int gioiTinh = resultSet.getInt("GioiTinh");
                String dienThoai = resultSet.getString("DienThoai");
                String email = resultSet.getString("Email");
                java.sql.Date ngayTao = resultSet.getDate("NgayTao");
                int chucVu = resultSet.getInt("ChucVu");
                int trangThai = resultSet.getInt("TrangThai");

                NhanVien nhanVien = new NhanVien(maNV, tenDangNhap, matKhau, hoTen, gioiTinh, dienThoai, email, ngayTao, chucVu, trangThai);
                nhanViens.add(nhanVien);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nhanViens;

    }

     
    // Đăng nhập
    public NhanVien getNhanVienByTenDangNhap(String tenDangNhap) {
    String query = "SELECT * FROM NHAN_VIEN WHERE TenDangNhap = ?";
    try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, tenDangNhap);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            int maNV = resultSet.getInt("MaNV");
            String matKhau = resultSet.getString("MatKhau");
            String hoTen = resultSet.getString("HoTen");
            int gioiTinh = resultSet.getInt("GioiTinh");
            String dienThoai = resultSet.getString("DienThoai");
            String email = resultSet.getString("Email");
            java.sql.Date ngayTao = resultSet.getDate("NgayTao");
            int chucVu = resultSet.getInt("ChucVu");
            int trangThai = resultSet.getInt("TrangThai");

            return new NhanVien(maNV, tenDangNhap, matKhau, hoTen, gioiTinh, dienThoai, email, ngayTao, chucVu, trangThai);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}
    



}
