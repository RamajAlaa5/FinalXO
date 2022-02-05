package xogame;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

public  class ipBase extends AnchorPane {

    protected final Label label;
    protected final TextField textField;
    protected final Button button;
    protected static Socket mySocket ;

    public ipBase() {

        label = new Label();
        textField = new TextField();
        button = new Button();

        setId("ippage");
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(423.0);
        setPrefWidth(474.0);
        getStylesheets().add("/xogame/style.css");

        label.setLayoutX(61.0);
        label.setLayoutY(101.0);
        label.setPrefHeight(68.0);
        label.setPrefWidth(72.0);
        label.setText("IP ");
        label.setTextFill(javafx.scene.paint.Color.valueOf("#7c541c"));
        label.setFont(new Font("System Bold Italic", 26.0));

        textField.setLayoutX(119.0);
        textField.setLayoutY(114.0);
        textField.setPrefHeight(39.0);
        textField.setPrefWidth(255.0);
        textField.setPromptText("Please ener ip");

        button.setLayoutX(118.0);
        button.setLayoutY(226.0);
        button.setMnemonicParsing(false);
        button.setPrefHeight(55.0);
        button.setPrefWidth(224.0);
        button.setStyle("-fx-background-color: #b69121 #b69121; -fx-background-radius: 100;");
        button.setText("Submit");
        button.setTextFill(javafx.scene.paint.Color.WHITE);
        button.setFont(new Font("System Bold", 22.0));

        getChildren().add(label);
        getChildren().add(textField);
        getChildren().add(button);

    }
    public boolean validate(final String ip) {
    String PATTERN = "^((0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)\\.){3}(0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)$";

    return ip.matches(PATTERN);
}
}
