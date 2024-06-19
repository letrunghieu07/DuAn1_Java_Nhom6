package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.HoaDonChiTietq;

import model.hoaDon;
import utilities.JdbcHelper;

public class HoaDonRepository {

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

    public List<HoaDonChiTietq> timKiemHoaDonBangMa(int maHD) {
        List<HoaDonChiTietq> danhSachHoaDon = new ArrayList<>();
        try {
            JdbcHelper.getConnection();
            Connection con = JdbcHelper.getConnection();

            String sql = """
            SELECT 
                HDCT.MaHDCT, 
                HD.MaHD, 
                HDCT.MaCTSP, 
                NV.HoTen AS HoTenNV, 
                TTKH.TenKH, 
                SP.TenSP, 
                HDCT.SoLuong, 
                HDCT.DonGia, 
                CLDe.TenChatLieuDe, 
                SIZE.KichThuoc, 
                MS.TenMau, 
                HD.TongTien, 
                KM.MucGiam, 
                HDKM.SoTienConLai
            FROM 
                HOA_DON_CHI_TIET HDCT
            LEFT JOIN 
                HOA_DON HD ON HDCT.MaHD = HD.MaHD
            LEFT JOIN 
                SAN_PHAM SP ON HDCT.MaCTSP = SP.MaSP
            LEFT JOIN 
                CHI_TIET_SAN_PHAM CTSP ON HDCT.MaCTSP = CTSP.MaCTSP
            LEFT JOIN
                NHAN_VIEN NV ON HD.MaNV = NV.MaNV
            LEFT JOIN 
                THONG_TIN_KH TTKH ON HD.MaTTKH = TTKH.MaTTKH
            LEFT JOIN 
                CHAT_LIEU_DE_GIAY CLDe ON CTSP.MaCLDe = CLDe.MaCLDe
            LEFT JOIN 
                SIZE ON CTSP.MaSize = SIZE.MaSize
            LEFT JOIN 
                MAU_SAC MS ON CTSP.MaMS = MS.MaMS
            LEFT JOIN
                HOA_DON_KHUYEN_MAI HDKM ON HD.MaHD = HDKM.MaHD
            LEFT JOIN 
                KHUYEN_MAI KM ON HDKM.MaKM = KM.MaKM
            WHERE HD.MAHD=?
            """;

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, maHD);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int MaHDCT = rs.getInt("MaHDCT");
                int MaHD = rs.getInt("MaHD");
                int MaCTSP = rs.getInt("MaCTSP");
                int SoLuong = rs.getInt("SoLuong");
                double DonGia = rs.getDouble("DonGia");
                String hoTenNV = rs.getString("HoTenNV");
                String tenKH = rs.getString("TenKH");
                String tenSP = rs.getString("TenSP");
                String tenCLD = rs.getString("TenChatLieuDe");
                int size = rs.getInt("KichThuoc");
                String tenMau = rs.getString("TenMau");
                double tongTien = rs.getDouble("TongTien");
                double mucGiam = rs.getDouble("MucGiam");
                double soTienConLai = rs.getDouble("SoTienConLai");

                HoaDonChiTietq hoaDonChiTiet = new HoaDonChiTietq(MaHDCT, MaCTSP, MaHD, SoLuong, DonGia, hoTenNV, tenKH, tenSP, tenCLD, size, tenMau, mucGiam, soTienConLai, tongTien);

                danhSachHoaDon.add(hoaDonChiTiet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return danhSachHoaDon;
    }

}
