package sample.Control;

import javafx.concurrent.Worker;
import sample.Model.Meaning;
import sample.Model.Word;
import sample.connection.connectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchDB {
    //Tìm kiếm nghĩa của từ Tiếng Anh nhập vào
    public List<Meaning> Translated(String english) throws SQLException {
        String sql = "SELECT * FROM Translate WHERE english = '"+english+"'";
        List<Meaning> list = new ArrayList<Meaning>();
        try{
            connectDB cnn1 = new connectDB();
            ResultSet rs = cnn1.chonDuLieu(sql);
            while(rs.next()){
                String eng = rs.getString(1);
                String vietnam = rs.getString(2);
                String type = rs.getString(3);
                String example = rs.getString(4);
                list.add(new Meaning(type, vietnam, example));
            }
            return list;
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return list;
    }

    public List<String> list_eng() {
        String sql = "SELECT * FROM Translate";
        List<String> list = new ArrayList<String>();
        try {
            connectDB cnn = new connectDB();
            ResultSet rs = cnn.chonDuLieu(sql);
            while (rs.next()) {
                String eng = rs.getString(1);
                list.add(eng);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
