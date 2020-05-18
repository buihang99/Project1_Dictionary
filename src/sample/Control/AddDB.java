package sample.Control;

import sample.connection.connectDB;

public class AddDB {
    public AddDB(String englishTf, String vietnamTf, String typeTf, String exampleTf) throws Exception {
        String sql = "INSERT INTO MyWord VALUES ('"+englishTf+"','"+vietnamTf+"','"+typeTf+"','"+exampleTf+"')";
        connectDB cnn = new connectDB();
        cnn.thucThiSQL(sql);
    }

}
