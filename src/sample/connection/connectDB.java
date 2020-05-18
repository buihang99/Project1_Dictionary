package sample.connection;

import sample.Model.Word;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class connectDB {
    // Tạo connection tới database
    public Connection connectDatabase() {
        Connection cnn = null;
        try {
            Class<?> clazz = connectDB.class;
            String url = "jdbc:sqlite:lib/Dictionary.db";
            cnn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return cnn;
    }

    public void thucThiSQL(String sql) throws Exception{
        //connectDB cnn = new connectDB();
        Connection connect = connectDatabase();
        Statement stmt = connect.createStatement();
        stmt.executeUpdate(sql);
    }
    public ResultSet chonDuLieu(String sql) throws Exception{
        //connectDB cnn = new connectDB();
        Connection connect = connectDatabase();
        Statement stmt = connect.createStatement();
        ResultSet rs= stmt.executeQuery(sql);
        return rs;
    }
}
