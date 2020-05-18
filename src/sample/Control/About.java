package sample.Control;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.util.Optional;

public class About {
    @FXML
    private Button exitbutton2;

    public void back() throws IOException {
        Stage stage = (Stage) exitbutton2.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        Parent result = loader.load(getClass().getResource("/sample/View/start.fxml"));
        stage.setTitle("My Dictionary");
        stage.setScene(new Scene(result));
        stage.show();

    }

    public void exit(){
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
