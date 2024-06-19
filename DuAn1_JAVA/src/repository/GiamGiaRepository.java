/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.util.List;
import model.GiamGia;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import utilities.JdbcHelper;
public class GiamGiaRepository {
    
    public List<GiamGia> getAllGiamGia() {
        List<GiamGia> listGiamGia = new ArrayList<>();

        try (Connection connection = JdbcHelper.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Giam_Gia");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int maGG = resultSet.getInt("maGG");
                String tenMaGiam = resultSet.getString("tenMaGiam");
                Float mucGiam = resultSet.getFloat("mucGiam");
                Date ngayBatDau = resultSet.getDate("ngayBatDau");
                Date ngayKetThuc = resultSet.getDate("ngayKetThuc");
                String ghiChu = resultSet.getString("ghiChu");

                GiamGia giamGia = new GiamGia(maGG, tenMaGiam, mucGiam, ngayBatDau, ngayKetThuc, ghiChu);
                listGiamGia.add(giamGia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listGiamGia;
    }
    
}
