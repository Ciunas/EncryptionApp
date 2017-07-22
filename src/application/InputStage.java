package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.awt.*;

public class InputStage {

    @FXML
    TextField textField1;

    public void encryptMessage(ActionEvent actionEvent) {

        String text = textField1.getText();
    }

}
