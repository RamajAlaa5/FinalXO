/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoeserver;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TableController implements Initializable {

    @FXML
    private TableColumn<TableModel, Integer> id;
    @FXML
    private TableColumn<TableModel, String> name;
    @FXML
        private TableColumn<TableModel, String> password;

    @FXML
    private TableColumn<TableModel,Integer > score;
    @FXML
    private TableColumn<TableModel,Integer> status;
    
       @FXML
    private TableView<TableModel> playerTable;

    @FXML
    private Button btnBack;

    /**
     * Initializes the controller class.
     */
      ObservableList<TableModel> playerList =FXCollections.observableArrayList();
       private DataBaseConnection databaseConnection;
       //Connection con;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
           databaseConnection = new DataBaseConnection();

           ResultSet rs =databaseConnection.conn.createStatement().executeQuery("select * from players");
            while(rs.next()){
                playerList.add(new TableModel(rs.getInt("player_id"),rs.getString("player_name"),rs.getInt("score"),rs.getInt("status")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        }

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        score.setCellValueFactory(new PropertyValueFactory<>("score"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));

playerTable.setItems(playerList);

        btnBack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Parent root = null;

                try {
                    root = FXMLLoader.load(getClass().getResource("ServerDashboard.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }


                Scene scene = new Scene(root);
               Stage stage=(Stage) btnBack.getScene().getWindow();
                stage.setScene(scene);




            }
        });

    }    

   
    
}
