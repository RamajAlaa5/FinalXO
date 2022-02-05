/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoeserver;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class ServerTest extends Application {
//Parent root;
    @Override
    public void start(Stage stage) throws Exception {

//registerBase base = new registerBase(stage);
//Parent root =FXMLLoader.load(getClass().getResource("Table.fxml"));
//ServerServiceBase base = new ServerServiceBase(stage);
        //new TicTacToeServer();
        Parent root =FXMLLoader.load(getClass().getResource("ServerDashboard.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        //ServerServiceBase server = new ServerServiceBase(stage);
        //registerBase server = new registerBase(stage);
        //Scene scene = new Scene(base);
        //stage.setScene(scene);
        //stage.setTitle("Registration Form");
        stage.show();
    }
    
     public static void main(String[] args) {

        launch(args);
    }
    
}
