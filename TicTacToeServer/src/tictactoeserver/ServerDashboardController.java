/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoeserver;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ServerDashboardController implements Initializable {
@FXML
Button dashboard;
@FXML
Button list;
//ServerServiceBase serverBase;
//Scene scene;
Stage stage;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       dashboard.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Parent root = null;

                    //root = FXMLLoader.load(getClass().getResource("ServerService.fxml"));

                ServerServiceBase root = null;
                try {
                    root = new ServerServiceBase(stage);
                    //root = FXMLLoader.load(getClass().getResource("ServerService.fxml"));

                }   catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                Scene scene = new Scene(root);
                     stage=(Stage) dashboard.getScene().getWindow();
                    stage.setScene(scene);




            }
        });


        list.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Parent root = null;

                try {
                    root = FXMLLoader.load(getClass().getResource("Table.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }


                Scene scene = new Scene(root);
                stage=(Stage) dashboard.getScene().getWindow();
                stage.setScene(scene);




            }
        });
    }    
    
}
