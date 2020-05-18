package sample.Control;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Model.Word;
import sample.connection.connectDB;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class SavedWord implements Initializable {
    @FXML
    private Button exitbutton;

    @FXML
    private TextArea englishTf;

    @FXML
    private TextArea vietnamTf;

    @FXML
    private TextArea typeTf;

    @FXML
    private TextArea exampleTf;

    @FXML
    private TableView<Word> table;

    @FXML
    private TableColumn<Word,String> englishCl;

    @FXML
    private TableColumn<Word,String> vietnamCl;

    @FXML
    private TableColumn<Word,String> typeCl;

    @FXML
    private TableColumn<Word,String> exampleCl;

    private ObservableList<Word> word;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            List<Word> list = hienthi();
            if(list.size()==0){
                word = FXCollections.observableArrayList(new Word("Empty","Empty","Empty","Empty"));
            } else{
                word = FXCollections.observableArrayList(list.get(0));
                for(int i=1 ; i < list.size(); i++ ){
                   word.add(list.get(i));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        englishCl.setCellValueFactory(new PropertyValueFactory<Word,String>("english_Word"));
        vietnamCl.setCellValueFactory(new PropertyValueFactory<Word,String>("vietnamese"));
        typeCl.setCellValueFactory(new PropertyValueFactory<Word, String>("type"));
        exampleCl.setCellValueFactory(new PropertyValueFactory<Word, String>("example"));
        table.setItems(word);
    }

    public List<Word> hienthi() throws Exception {
        //Lấy các từ đã lưu ở database
        String sql = "SELECT * FROM MyWord";
        connectDB cnn = new connectDB();
        ResultSet rs = cnn.chonDuLieu(sql);
        List<Word> list = new ArrayList<Word>();
        while(rs.next()){
            list.add(new Word(rs.getString(1),rs.getString(2),
                    rs.getString(3),rs.getString(4)));
        }
        return list;
    }

    public void add(ActionEvent event) {
        Word newWord = new Word();
        newWord.setEnglish_Word(englishTf.getText());
        newWord.setVietnamese(vietnamTf.getText());
        newWord.setType(typeTf.getText());
        newWord.setExample(exampleTf.getText());
        //Thêm vào database
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Notification");
        alert.setHeaderText("Notification");
        try{
            new AddDB(englishTf.getText(), vietnamTf.getText(), typeTf.getText(), exampleTf.getText());
            //Thêm vào bảng
            word.add(newWord);
            //alert.setContentText("Succeed ^^");
            //alert.showAndWait();
            englishTf.setText(null);
            vietnamTf.setText(null);
            typeTf.setText(null);
            exampleTf.setText(null);
        } catch (Exception e){
            alert.setContentText("Từ đã tồn tại!!!");
            alert.showAndWait();
            System.out.println(e.getMessage());
        }
    }

    public void delete() throws Exception {
        Word selected = table.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Do you want to DELETE?");
        ButtonType button1 = new ButtonType("Yes");
        ButtonType button2 = new ButtonType("No");
        alert.getButtonTypes().clear();
        alert.getButtonTypes().setAll(button1,button2);
        Optional<ButtonType> option = alert.showAndWait();
        if(option.get()==button1){
            word.remove(selected);
            DeleteDB deleteDB = new DeleteDB(selected.getEnglish_Word(),selected.getVietnamese());
        } else{
            alert.close();
        }
    }

    public void exit(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Do you want to exit?");
        ButtonType button1 = new ButtonType("Yes");
        ButtonType button2 = new ButtonType("No");
        alert.getButtonTypes().clear();
        alert.getButtonTypes().setAll(button1,button2);
        Optional<ButtonType> option = alert.showAndWait();
        if(option.get() == button1){
            Platform.exit();
            System.exit(0);
        } else{
            alert.close();
        }
    }

    public void back(ActionEvent event) throws IOException {
        Stage stage = (Stage) exitbutton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        Parent result = loader.load(getClass().getResource("/sample/View/start.fxml"));
        stage.setTitle("LaLaDi");
        stage.setScene(new Scene(result));
        stage.show();
    }

    public void search(ActionEvent event) throws IOException {
        Stage stage = (Stage) exitbutton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        Parent result = loader.load(getClass().getResource("/sample/View/search.fxml"));
        stage.setTitle("Search");
        stage.setScene(new Scene(result));
        stage.show();
    }

}
