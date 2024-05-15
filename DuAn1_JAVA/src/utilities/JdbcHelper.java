package utilities;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.*;


public class JdbcHelper {

    @SuppressWarnings("empty-statement")
    public static Connection getConnection() throws SQLServerException {

        SQLServerDataSource dataSource= new SQLServerDataSource();
        dataSource.setUser("sa");
        dataSource.setPassword("123456");
        dataSource.setPortNumber(1433);
        dataSource.setEncrypt(Boolean.FALSE);
        dataSource.setDatabaseName("DuAn1_BanGiay_Nhom6");
        return dataSource.getConnection();
    }
     public static void main(String[] args) throws SQLServerException {
        System.out.println(getConnection());

    }
}
