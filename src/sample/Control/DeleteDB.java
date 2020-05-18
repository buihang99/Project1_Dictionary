package sample.Control;

import sample.connection.connectDB;

public class DeleteDB {
    public DeleteDB(String english, String vietnam) throws Exception {
        String sql = "DELETE FROM MyWord WHERE english = '"+english+"' and vietnamese ='"+vietnam+"'";
        connectDB cnn = new connectDB();
        cnn.thucThiSQL(sql);
    }
}
