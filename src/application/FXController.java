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
    private Button button;


    @FXML
    public void chooseFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {



            String key = "Mary has one cat";
            File inputFile = new File("/home/ciunas/test.txt");
            File encryptedFile = new File("/home/ciunas/test.encrypted");
            File decryptedFile = new File("/home/ciunas/test.decrypted");

            try {
                //CryptoUtils.encrypt(key, inputFile, encryptedFile);
                CryptoUtils.decrypt(key, encryptedFile, decryptedFile);
            } catch (CryptoException ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }

            label1.setText ( selectedFile +  " Encrytpted.");
        } else {
            label1.setText(selectedFile +  " not Encrytpted.");
        }
    }

    @FXML
    public void newScene(ActionEvent actionEvent) throws IOException {

//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("inputStage.fxml"));
//        Parent root1 = (Parent) fxmlLoader.load();
//        Stage stage = new Stage();
//        stage.initModality(Modality.APPLICATION_MODAL);
//        stage.initStyle(StageStyle.UNDECORATED);
//        stage.setTitle("ABC");
//        stage.setScene(new Scene(root1));
//        stage.show();


        Parent root1 = FXMLLoader.load(getClass().getResource("inputStage.fxml"));
        Stage stage1 = new Stage();
        stage1.setTitle("Text Encryption Application");
        stage1.setScene(new Scene(root1, 500, 600));
        stage1.show();
    }
}
