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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.TouchPoint;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;
import sample.Model.Meaning;
import sample.Model.Word;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class Search implements Initializable {
    @FXML
    private TextField englishword;
    @FXML
    private Button exitbutton3;
    @FXML
    private TableView<Meaning> tableMean;
    @FXML
    private TableColumn<Meaning, String> type;
    @FXML
    private TableColumn<Meaning, String> vietnam;
    @FXML
    private TableColumn<Meaning, String> example;

    private ObservableList<Meaning> meaningList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Set các thuộc tính của bảng
        type.setCellValueFactory(new PropertyValueFactory<Meaning, String>("type"));
        vietnam.setCellValueFactory(new PropertyValueFactory<Meaning, String>("vietnam"));
        example.setCellValueFactory(new PropertyValueFactory<Meaning, String>("example"));

        //Tạo Autocomplete cho input người dùng nhập vào
        List<String> list = new ArrayList<String>();
        SearchDB l = new SearchDB();
        list = l.list_eng();
        TextFields.bindAutoCompletion(englishword,list);
    }

    //Hàm thực hiện nhận phím Enter khi người dùng nhập
    public void key(KeyEvent keyEvent) throws SQLException {
        if(keyEvent.getCode() == KeyCode.ENTER){
            String newword = englishword.getText().toLowerCase().trim();
            SearchDB vietnam = new SearchDB();
            List<Meaning> list = vietnam.Translated(newword);
            int n = list.size();
            if(n==0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Notification");
                alert.setHeaderText("Information");
                alert.setContentText("Rất tiếc từ này chưa có trong từ điển !");
                alert.showAndWait();
            } else{
                meaningList = FXCollections.observableArrayList(list.get(0));
                for(int i=1 ; i < n; i++ ){
                    meaningList.add(list.get(i));
                }
            }
            //Hiển thị meaningList ra table
            tableMean.setItems(meaningList);
        }
    }

    //Action của button Search
    public void search(ActionEvent event) throws SQLException {
        Word word = new Word();
        word.setEnglish_Word(englishword.getText().toLowerCase());
        SearchDB vietnam = new SearchDB();
        List<Meaning> list = vietnam.Translated(word.getEnglish_Word().trim());
            int n = list.size();
            if(n==0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Notification");
                alert.setHeaderText("Information");
                alert.setContentText("Rất tiếc từ này chưa có trong từ điển !");
                alert.showAndWait();
            } else{
                meaningList = FXCollections.observableArrayList(list.get(0));
                for(int i=1 ; i < n; i++ ){
                    meaningList.add(list.get(i));
                }
            }
        tableMean.setItems(meaningList);
    }

    public void save(ActionEvent event) {
        Meaning selected = tableMean.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Notification");
        alert.setHeaderText("Notification");
        try{
            new AddDB(englishword.getText(),selected.getVietnam(),selected.getType(),selected.getExample());
            alert.setContentText("Succeed ^^");
            alert.showAndWait();
        } catch (Exception e){
            alert.setContentText("Vui lòng chọn hàng bạn muốn lưu!!!");
            alert.showAndWait();
            System.out.println(e.getMessage());
        }
    }

    public void back(ActionEvent event) throws IOException {
        Stage stage = (Stage) exitbutton3.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        Parent result = loader.load(getClass().getResource("/sample/View/start.fxml"));
        stage.setTitle("LaLaDi");
        stage.setScene(new Scene(result));
        stage.show();
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
}
