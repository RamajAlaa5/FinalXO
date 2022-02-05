package xogame;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import static xogame.XOGame.scene9;

public  class onlinePlayersUiBase extends AnchorPane {

    protected final ListView nameLView;
    protected final Button button;
    protected final Button button0;
    protected final ListView scoreLView;
    protected final Label label;
    protected final Label label0;
    protected final Button button1;

    public onlinePlayersUiBase() {

        nameLView = new ListView();
        button = new Button();
        button0 = new Button();
        scoreLView = new ListView();
        label = new Label();
        label0 = new Label();
        button1 = new Button();

        setId("background4");
        setPrefHeight(400.0);
        setPrefWidth(600.0);
        getStylesheets().add("/xogame/style.css");

        nameLView.setLayoutX(91.0);
        nameLView.setLayoutY(94.0);
        nameLView.setOpacity(0.49);
        nameLView.setPrefHeight(261.0);
        nameLView.setPrefWidth(200.0);

        button.setLayoutX(366.0);
        button.setLayoutY(100.0);
        button.setMnemonicParsing(false);
        button.setOnAction(this::requestGame);
        button.setPrefHeight(44.0);
        button.setPrefWidth(190.0);
        button.setStyle("-fx-background-radius: 100; -fx-background-color: #b69121;");
        button.setText("Request Match");
        button.setTextFill(javafx.scene.paint.Color.WHITE);
        button.setFont(new Font("System Bold", 20.0));

        button0.setLayoutX(366.0);
        button0.setLayoutY(243.0);
        button0.setMnemonicParsing(false);
        button0.setOnAction(this::takeMeHome);
        button0.setPrefHeight(44.0);
        button0.setPrefWidth(190.0);
        button0.setStyle("-fx-background-radius: 100; -fx-background-color: #b69121;");
        button0.setText("Home");
        button0.setTextFill(javafx.scene.paint.Color.WHITE);
        button0.setFont(new Font("System Bold", 20.0));
        
        scoreLView.setLayoutX(66.0);
        scoreLView.setLayoutY(100.0);
        scoreLView.setPrefHeight(200.0);
        scoreLView.setPrefWidth(34.0);

        label.setLayoutX(100.0);
        label.setLayoutY(60.0);
        label.setText("Player Name");
        label.setTextFill(javafx.scene.paint.Color.valueOf("#7c541c"));
        label.setFont(new Font("System Bold", 20.0));

        button1.setLayoutX(366.0);
        button1.setLayoutY(172.0);
        button1.setMnemonicParsing(false);
        button1.setOnAction(this::refreshOnlinePlayers);
        button1.setPrefHeight(44.0);
        button1.setPrefWidth(190.0);
        button1.setStyle("-fx-background-radius: 100; -fx-background-color: #b69121;");
        button1.setText("Refresh");
        button1.setTextFill(javafx.scene.paint.Color.WHITE);
        button1.setFont(new Font("System Bold", 20.0));

        getChildren().add(nameLView);
        getChildren().add(button);
        getChildren().add(button0);
        getChildren().add(label);
        getChildren().add(button1);
        nameLView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               // nameLView.sel
            }
        });


    }

    protected void requestGame(javafx.event.ActionEvent actionEvent){
       SignInnController.ps.println("invite." + XOGame.name + "." + nameLView.getSelectionModel().getSelectedItem().toString());
    }

     void startMatch() {
        try {
            
            scene9 = new Scene(FXMLLoader.load(getClass().getResource("OnlinePlayersBoardUi.fxml")));
            XOGame.window.setScene(scene9);
            XOGame.window.show();
        } catch (IOException ex) {
            Logger.getLogger(onlinePlayersUiBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void takeMeHome(javafx.event.ActionEvent actionEvent) {
        XOGame.bth();
    }

    protected void refreshOnlinePlayers(javafx.event.ActionEvent actionEvent) {
        SignInnController.ps.println("names.");
        try {
            Thread.sleep(300);
        } catch (InterruptedException ex) {
            Logger.getLogger(OnlinePlayersUiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        XOGame.Check();
    }

}
