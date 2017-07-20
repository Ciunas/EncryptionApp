package application;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;

public class FXController  {

    @FXML
    private TextField text1;
    @FXML
    private Button button;


    @FXML
    public void chooseFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {

            text1.setText("File selected: " + selectedFile.getName());
        }
        else {
            text1.setText("File selection cancelled.");
        }
    }
}
