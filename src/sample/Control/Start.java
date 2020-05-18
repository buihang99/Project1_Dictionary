package sample.Control;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class Start {
    @FXML
    private Button exitbutton1;

    public void search(ActionEvent event) throws IOException {
        Stage stage = (Stage) exitbutton1.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        Parent result = loader.load(getClass().getResource("/sample/View/search.fxml"));
        stage.setTitle("Search");
        stage.setScene(new Scene(result));
        stage.show();
    }

    public void saved(ActionEvent event) throws IOException{
        Stage stage = (Stage) exitbutton1.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        Parent result = loader.load(getClass().getResource("/sample/View/saved.fxml"));
        stage.setTitle("My Word");
        stage.setScene(new Scene(result));
        stage.show();
    }

    public void about(ActionEvent event) throws IOException {
        Stage stage = (Stage) exitbutton1.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        Parent result = loader.load(getClass().getResource("/sample/View/about_us.fxml"));
        stage.setTitle("About Us");
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
