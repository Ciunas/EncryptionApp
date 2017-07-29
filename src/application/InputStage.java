package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;


public class InputStage {

    @FXML
    private TextArea textArea1;

    @FXML
    private TextArea textArea2;

    SecretKey secretKey = KeyGenerator.getInstance("DES").generateKey();

    public InputStage() throws NoSuchAlgorithmException {
    }

    public void encryptMessage(ActionEvent actionEvent) throws UnsupportedEncodingException {

        //textArea2.setText("");
        String text = new String ( textArea1.getText() );
        System.out.println(text);

        String key = "Mary has one cat";

        try {


            String temp = CryptoUtils.encrypt( text, secretKey);
            textArea2.setText(temp);



        } catch (CryptoException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        textArea1.setText("");
    }

    public void decryptMessage(ActionEvent actionEvent) throws UnsupportedEncodingException {

        String text = new String (textArea2.getText());
        System.out.println(text);

        String key = "Mary has one cat";

        try {


            String temp =  CryptoUtils.decrypt(text, secretKey);
            textArea1.setText(temp);

        } catch (CryptoException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        textArea2.setText("");
    }

}
