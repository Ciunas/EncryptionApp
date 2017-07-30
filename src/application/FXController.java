package application;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Pair;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

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

            label1.setText(inputFile + " Encrytpted.");
        } else {
            label1.setText(inputFile + " not Encrytpted.");
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

            dialogNew();


            String key = "Mary has one cat";
            File decryptedFile = new File("/home/ciunas/decryptedFile.decrypted");

            try {
                CryptoUtils.decrypt(key, inputFile, decryptedFile);
            } catch (CryptoException ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }

            label1.setText(inputFile + " Decrytpted.");
        } else {
            label1.setText(inputFile + " not Decrytpted.");
        }
    }


    public void login() {
        TextInputDialog dialog = new TextInputDialog("walter");
        dialog.setTitle("Text Input Dialog");
        dialog.setHeaderText("Look, a Text Input Dialog");
        dialog.setContentText("Please enter your name:");

// Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            System.out.println("Your name: " + result.get());
        }

// The Java 8 way to get the response value (with lambda expression).
        result.ifPresent(name -> System.out.println("Your name: " + name));
    }

    public String dialog() {
        // Create the custom dialog.
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Login Dialog");
        dialog.setHeaderText("Look, a Custom Login Dialog");

// Set the icon (must be included in the project).
        //dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));

// Set the button types.
        ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);

// Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));


        PasswordField password = new PasswordField();
        password.setPromptText("Password");

        grid.add(new Label("Password:"), 0, 0);
        grid.add(password, 1, 0);

// Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        //Do some validation (using the Java 8 lambda syntax).
        password.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

// Request focus on the username field by default.
        Platform.runLater(() -> password.requestFocus());

        //Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return password.getText();
            }
            return null;
        });

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(usernamePassword -> {
            System.out.println(", Password=");
        });
        return "help";
    }

    public void dialogNew() {

        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Login Dialog");
        dialog.setHeaderText("Look, a Custom Login Dialog");

        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        // Set the button types.
        ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);


        PasswordField password = new PasswordField();
        password.setPromptText("Password");

        grid.add(new Label("Password:"), 0, 0);

        // Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        // Do some validation (using the Java 8 lambda syntax).
        password.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });


        grid.add(password, 1, 0);

        dialog.getDialogPane().setContent(grid);

        // Request focus on the username field by default.
        Platform.runLater(() -> password.requestFocus());

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(usernamePassword -> {
            System.out.println(", Password=");
        });

    }
}
