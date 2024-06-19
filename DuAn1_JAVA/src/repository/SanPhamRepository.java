/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.ChatLieuDeGiay1;
import model.ChatLieuMatGiay1;
import model.ChiTietSanPham1;
import model.DanhMuc1;
import model.GiamGia1;
import model.MauSac1;
import model.SanPham1;
import model.Size1;
import utilities.JdbcHelper;

/**
 *
 * @author DELL
 */
public class SanPhamRepository {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;

    public List<SanPham1> getSanPham() {
        List<SanPham1> listSp = new ArrayList<>();
        sql = "Select MaSP,TenSP,NgayNhap,NgayCapNhat,SAN_PHAM.TrangThai,DANH_MUC.TenDanhMuc From SAN_PHAM JOIN DANH_MUC on  SAN_PHAM.MaDM = DANH_MUC.MaDM";
        try {
            con = JdbcHelper.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                SanPham1 sp1 = new SanPham1();
                sp1.setMaSP(rs.getInt(1));
                sp1.setTenSP(rs.getString(2));
                java.sql.Date ngayNhapSqlDate = rs.getDate("NgayNhap");
                if (ngayNhapSqlDate != null) {
                    sp1.setNgayNhap(ngayNhapSqlDate.toLocalDate());
                }

                java.sql.Date ngayCapNhatSqlDate = rs.getDate("NgayCapNhat");
                if (ngayCapNhatSqlDate != null) {
                    sp1.setNgayCapNhat(ngayCapNhatSqlDate.toLocalDate());
                }
                sp1.setTrangThai(rs.getBoolean(5));
                DanhMuc1 dm1 = new DanhMuc1(rs.getString(6));
                sp1.setMaDM(dm1);
                listSp.add(sp1);
            }
            return listSp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ChiTietSanPham1> getChiTietSanPham1() {
        List<ChiTietSanPham1> listCtsp = new ArrayList<>();
        sql = "Select MaCTSP,SoLuong,DonGia,SAN_PHAM.TenSP,CHAT_LIEU_DE_GIAY.TenChatLieuDe,SIZE.KichThuoc,MAU_SAC.TenMau,CHAT_LIEU.TenChatLieu,MoTa,Giam_Gia.TenMaGiam From CHI_TIET_SAN_PHAM\n"
                + "Join SAN_PHAM on CHI_TIET_SAN_PHAM.MaSP = SAN_PHAM.MaSP\n"
                + "Join CHAT_LIEU_DE_GIAY on CHI_TIET_SAN_PHAM.MaCLDe = CHAT_LIEU_DE_GIAY.MaCLDe\n"
                + "Join SIZE on CHI_TIET_SAN_PHAM.MaSize = SIZE.MaSize\n"
                + "Join MAU_SAC on CHI_TIET_SAN_PHAM.MaMS = MAU_SAC.MaMS\n"
                + "Join CHAT_LIEU on CHI_TIET_SAN_PHAM.MaCL = CHAT_LIEU.MaCL\n"
                + "Join Giam_Gia on CHI_TIET_SAN_PHAM.MaGG = Giam_Gia.MaGG";
        try {
            con = JdbcHelper.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietSanPham1 ctsp = new ChiTietSanPham1();
                ctsp.setMaCTSP(rs.getInt(1));
                ctsp.setSoLuong(rs.getInt(2));
                ctsp.setDonGia(rs.getFloat(3));
                SanPham1 sp1 = new SanPham1(rs.getString(4));
                ctsp.setMaSP(sp1);
                ChatLieuDeGiay1 cldg = new ChatLieuDeGiay1(rs.getString(5));
                ctsp.setMaCLDe(cldg);
                Size1 sz = new Size1(rs.getString(6));
                ctsp.setMaSize(sz);
                MauSac1 ms = new MauSac1(rs.getString(7));
                ctsp.setMaMS(ms);
                ChatLieuMatGiay1 clmg = new ChatLieuMatGiay1(rs.getString(8));
                ctsp.setMaCL(clmg);
                ctsp.setMoTa(rs.getString(9));
                GiamGia1 gg = new GiamGia1(rs.getString(10));
                ctsp.setMaGG(gg);
                listCtsp.add(ctsp);
            }
            return listCtsp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public int addSanPham(SanPham1 sp){
        sql="Insert into SAN_PHAM(TenSP,NgayNhap,NgayCapNhat,TrangThai,MaDM) values (?,?,?,?,?)";
        try {
            con = JdbcHelper.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, sp.getTenSP());
            ps.setObject(2, sp.getNgayNhap());
            ps.setObject(3, sp.getNgayCapNhat());
            ps.setObject(4, sp.isTrangThai());
            ps.setObject(5, sp.getMaDM().getMaDM());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int addCtSanPham(ChiTietSanPham1 ctsp1){
        sql="Insert into CHI_TIET_SAN_PHAM(SoLuong,DonGia,MaSP,MaCLDe,MaSize,MaMS,MaCL,MoTa,MaGG) values (?,?,?,?,?,?,?,?,?)";
        try {
            con = JdbcHelper.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ctsp1.getSoLuong());
            ps.setObject(2, ctsp1.getDonGia());
            ps.setObject(3, ctsp1.getMaSP().getMaSP());
            ps.setObject(4, ctsp1.getMaCLDe().getMaCLDe());
            ps.setObject(5, ctsp1.getMaSize().getMaSize());
            ps.setObject(6, ctsp1.getMaMS().getMaMS());
            ps.setObject(7, ctsp1.getMaCL().getMaCL());
            ps.setObject(8, ctsp1.getMoTa());
            ps.setObject(9, ctsp1.getMaGG().getMaGG());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int updateSanPham(SanPham1 sp, Integer ma){
        sql="Update SAN_PHAM set TenSP = ?, NgayCapNhat = ?, TrangThai = ?, MaDM = ? where MaSP =?";
        try {
            con = JdbcHelper.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, sp.getTenSP());
//            ps.setObject(2, sp.getNgayNhap());
            ps.setObject(2, sp.getNgayCapNhat());
            ps.setObject(3, sp.isTrangThai());
            ps.setObject(4, sp.getMaDM().getMaDM());
            ps.setObject(5, ma);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
        public int updateSanPhamCT(ChiTietSanPham1 ctsp, Integer ma){
        sql="Update CHI_TIET_SAN_PHAM set SoLuong = ?, DonGia = ?,MaSP = ?, MaCLDe = ?,MaSize = ?, MaMS = ?,MaCL = ?, MoTa = ? where MaCTSP = ?";
        try {
            con = JdbcHelper.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ctsp.getSoLuong());
            ps.setObject(2, ctsp.getDonGia());
            ps.setObject(3, ctsp.getMaSP().getMaSP());
            ps.setObject(4, ctsp.getMaCLDe().getMaCLDe());
            ps.setObject(5, ctsp.getMaSize().getMaSize());
            ps.setObject(6, ctsp.getMaMS().getMaMS());
            ps.setObject(7, ctsp.getMaCL().getMaCL());
            ps.setObject(8, ctsp.getMoTa());
            ps.setObject(9, ma);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
