package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Base64;


public class InputStage {

    @FXML
    private TextArea textArea1;

    @FXML
    private TextArea textArea2;

    public void encryptMessage(ActionEvent actionEvent) {

        //textArea2.setText("");
        String text = textArea1.getText();
        System.out.println(text);

        String key = "Mary has one cat";

        try {
            textArea2.setText( new String( CryptoUtils.encryptText(key,  textArea1.getText() )));

        } catch (CryptoException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        textArea1.setText("");
    }

    public void decryptMessage(ActionEvent actionEvent) {

        String text = textArea1.getText();
        System.out.println(text);

        String key = "Mary has one cat";

        try {

            textArea2.setText( new String( CryptoUtils.decryptText(key, textArea1.getText())));

        } catch (CryptoException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        textArea1.setText("");
    }

}
