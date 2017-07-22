package application;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;

public class FXController {

    @FXML
    private Label label1;


    @FXML
    public void encrypt(ActionEvent actionEvent) {

        FileChooser fileChooser = new FileChooser();
        File inputFile = fileChooser.showOpenDialog(null);
        System.out.println(inputFile);
        if (inputFile != null) {

            String key = "Mary has one cat";
            File encryptedFile = new File("/home/ciunas/encryptedFile.encrypted");

            try {
                CryptoUtils.encrypt(key, inputFile, encryptedFile);
            } catch (CryptoException ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }

            label1.setText ( inputFile +  " Encrytpted.");
        } else {
            label1.setText(inputFile +  " not Encrytpted.");
        }
    }

    @FXML
    public void newScene(ActionEvent actionEvent) throws IOException {
        Parent root1 = FXMLLoader.load(getClass().getResource("inputStage.fxml"));
        Stage stage1 = new Stage();
        stage1.setTitle("Text Encryption Application");
        stage1.setScene(new Scene(root1, 500, 600));
        stage1.show();
    }

    public void decrypt(ActionEvent actionEvent) {

        FileChooser fileChooser = new FileChooser();
        File inputFile = fileChooser.showOpenDialog(null);
        System.out.println(inputFile);
        if (inputFile != null) {

            String key = "Mary has one cat";
            File decryptedFile = new File("/home/ciunas/decryptedFile.decrypted");

            try {
                CryptoUtils.decrypt(key, inputFile, decryptedFile);
            } catch (CryptoException ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }

            label1.setText ( inputFile +  " Decrytpted.");
        } else {
            label1.setText(inputFile +  " not Decrytpted.");
        }
    }
}
