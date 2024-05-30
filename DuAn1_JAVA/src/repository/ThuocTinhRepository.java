/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.ChatLieuDeGiay1;
import model.ChatLieuMatGiay1;
import model.DanhMuc1;
import model.GiamGia1;
import model.MauSac1;
import model.Size1;
import utilities.JdbcHelper;

/**
 *
 * @author DELL
 */
public class ThuocTinhRepository {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = "";

    public ThuocTinhRepository() {
//         try {
//            this.connection = JdbcHelper.getConnection();
//        } catch (SQLServerException ex) {
//            Logger.getLogger(ThuocTinhRepository.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    public List<GiamGia1> getGiamGia() {
        List<GiamGia1> listGg = new ArrayList<>();
        sql = "Select MaGG,TenMaGiam,MucGiam,NgayBatDau,NgayKetThuc,GhiChu from Giam_Gia";
        try {
            con = JdbcHelper.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                GiamGia1 gg = new GiamGia1();
                gg.setMaGG(rs.getInt(1));
                gg.setTenMaGiam(rs.getString(2));
                gg.setMucGiam(rs.getFloat(3));
                gg.setNgayBatDau(rs.getDate(4));
                gg.setNgayKetThuc(rs.getDate(5));
                gg.setGhiChu(rs.getString(6));
                listGg.add(gg);
            }
            return listGg;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ChatLieuDeGiay1> getChatLieuDeGiay1() {
        List<ChatLieuDeGiay1> listCldg1 = new ArrayList<>();
        sql = "Select MaCLDe,TenChatLieuDe,TrangThai From CHAT_LIEU_DE_GIAY";
        try {
            con = JdbcHelper.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ChatLieuDeGiay1 clsg1 = new ChatLieuDeGiay1();
                clsg1.setMaCLDe(rs.getInt(1));
                clsg1.setTenChatLieuDe(rs.getString(2));
                clsg1.setTrangThai(rs.getBoolean(3));
                listCldg1.add(clsg1);
            }
            return listCldg1;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ChatLieuMatGiay1> getChatLieuMatGiay1() {
        List<ChatLieuMatGiay1> listClmg1 = new ArrayList<>();
        sql = "Select MaCL,TenChatLieu,TrangThai From CHAT_LIEU";
        try {
            con = JdbcHelper.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ChatLieuMatGiay1 clmg1 = new ChatLieuMatGiay1();
                clmg1.setMaCL(rs.getInt(1));
                clmg1.setTenChatLieu(rs.getString(2));
                clmg1.setTrangThai(rs.getBoolean(3));
                listClmg1.add(clmg1);
            }
            return listClmg1;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<MauSac1> getMauSac1() {
        List<MauSac1> listMauSac = new ArrayList<>();
        sql = "Select MaMS,TenMau,TrangThai From MAU_SAC";
        try {
            con = JdbcHelper.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                MauSac1 ms = new MauSac1();
                ms.setMaMS(rs.getInt(1));
                ms.setTenMau(rs.getString(2));
                ms.setTrangThai(rs.getBoolean(3));
                listMauSac.add(ms);
            }
            return listMauSac;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<DanhMuc1> getDanhMuc() {
        List<DanhMuc1> listDanhMuc = new ArrayList<>();
        sql = "Select MaDM,TenDanhMuc,TrangThai From DANH_MUC";
        try {
            con = JdbcHelper.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                DanhMuc1 dm = new DanhMuc1();
                dm.setMaDM(rs.getInt(1));
                dm.setTenDanhMuc(rs.getString(2));
                dm.setTrangThai(rs.getBoolean(3));
                listDanhMuc.add(dm);
            }
            return listDanhMuc;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Size1> getSize() {
        List<Size1> listSize = new ArrayList<>();
        sql = "Select MaSize,KichThuoc,TrangThai From SIZE";
        try {
            con = JdbcHelper.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Size1 sz = new Size1();
                sz.setMaSize(rs.getInt(1));
                sz.setKichThuoc(rs.getString(2));
                sz.setTrangThai(rs.getBoolean(3));
                listSize.add(sz);
            }
            return listSize;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int addChatLieuDe(ChatLieuDeGiay1 cldg1){
        sql = "Insert into CHAT_LIEU_DE_GIAY(TenChatLieuDe,TrangThai) values (?,?)";
        try {
            con = JdbcHelper.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, cldg1.getTenChatLieuDe());
            ps.setObject(2, cldg1.isTrangThai());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    public int addChatLieuMat(ChatLieuMatGiay1 clmg1){
        sql = "Insert into CHAT_LIEU(TenChatLieu,TrangThai) values (?,?)";
        try {
            con = JdbcHelper.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, clmg1.getTenChatLieu());
            ps.setObject(2, clmg1.isTrangThai());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    public int addMauSac (MauSac1 ms){
        sql="Insert into MAU_SAC(TenMau,TrangThai) values (?,?)";
        try {
            con=JdbcHelper.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ms.getTenMau());
            ps.setObject(2, ms.isTrangThai());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    public int addDanhMuc(DanhMuc1 dm){
        sql="Insert into DANH_MUC(TenDanhMuc,TrangThai) values (?,?)";
        try {
            con = JdbcHelper.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, dm.getTenDanhMuc());
            ps.setObject(2, dm.isTrangThai());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    public int addSize(Size1 sz){
        sql="Insert into SIZE(KichThuoc,TrangThai) values (?,?)";
        try {
            con = JdbcHelper.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, sz.getKichThuoc());
            ps.setObject(2, sz.isTrangThai());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
