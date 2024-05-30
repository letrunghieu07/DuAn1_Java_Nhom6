package utilities;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JdbcHelper {

    @SuppressWarnings("empty-statement")

    public static Connection getConnection() throws SQLServerException {

        SQLServerDataSource dataSource = new SQLServerDataSource();
        dataSource.setUser("sa");
        dataSource.setPassword("13092004");
        dataSource.setPortNumber(1433);
        dataSource.setEncrypt(Boolean.FALSE);
        dataSource.setDatabaseName("DuAn1_BanGiay_Nhom6");
        Connection connection = dataSource.getConnection();
        try {
            if (connection != null && !connection.isClosed()) {
                System.out.println("Kết nối thành công đến cơ sở dữ liệu.");
            } else {
                System.out.println("Không thể kết nối đến cơ sở dữ liệu.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(JdbcHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return connection;
    }

    public static void main(String[] args) {
        try {
            Connection connection = getConnection();
        } catch (SQLServerException ex) {
            System.out.println("Xảy ra lỗi khi kết nối đến cơ sở dữ liệu: " + ex.getMessage());
        }
    }
}
