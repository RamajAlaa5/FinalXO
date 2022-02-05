package tictactoeserver;


import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public  class ServerServiceBase extends AnchorPane {
    protected final Text text;
    protected final Button button;
    protected final Button button0;
    protected final Button btnBack;
    protected final PieChart pieChart;
    protected final Lighting lighting;
    private DataBaseConnection databaseConnection;
    public static ScheduledExecutorService scheduledExecutorService;
    static boolean isServerOn;
    //final Stage stage;
    Parent root;

    ObservableList<PieChart.Data> pieChartData;

    public ServerServiceBase(Stage stage) throws ClassNotFoundException {

        databaseConnection = new DataBaseConnection();
        text = new Text();
        button = new Button();
        button0 = new Button();
        lighting = new Lighting();
        btnBack= new Button();

        button0.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pieChart.setVisible(false);

                    //closingEverything();
//                    if (isServerOn) {
//                        scheduledExecutorService.shutdown();
//                    }
                  try{
                    TicTacToeServer.stopClients();}
                   catch (IOException e) {
                    e.printStackTrace();
                }


                    TicTacToeServer.PlayerHandler.flag=true;
                    System.out.println("Server Is Stopped"+TicTacToeServer.PlayerHandler.flag);


            }
        });

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
                refreshChart();
                pieChart.setVisible(true);
                //isServerOn = true;
                 new TicTacToeServer();
                TicTacToeServer.PlayerHandler.flag=false;
                System.out.println("Server Is Running");
            }
        });


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





        pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Offline", databaseConnection.offlinePlayersCount()),
                new PieChart.Data("Online", databaseConnection.olinePlayersCount()) //initialize pie chart
        );
        pieChart = new PieChart(pieChartData);
        pieChart.setTitle("Palyers' Statistics");







        setId("invitation");
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        getStylesheets().add("style.css");

        text.setFill(javafx.scene.paint.Color.valueOf("#76520e"));
        text.setLayoutX(172.0);
        text.setLayoutY(57.0);
        text.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text.setStrokeWidth(0.0);
        text.setText("Server Statistics");
        text.setFont(new Font("System Bold", 28.0));

        button.setLayoutX(76.0);
        button.setLayoutY(83.0);
        button.setMnemonicParsing(false);
        button.setPrefHeight(61.0);
        button.setPrefWidth(170.0);
        button.setStyle("-fx-background-color: #b69121; -fx-background-radius: 100;");
        button.setText("Start");
        button.setTextFill(javafx.scene.paint.Color.WHITE);
        button.setFont(new Font("System Bold", 20.0));

        button0.setLayoutX(325.0);
        button0.setLayoutY(83.0);
        button0.setMnemonicParsing(false);
        button0.setPrefHeight(61.0);
        button0.setPrefWidth(170.0);
        button0.setStyle("-fx-background-color: #b69121; -fx-background-radius: 100;");
        button0.setText("Stop");
        button0.setTextFill(javafx.scene.paint.Color.WHITE);
        button0.setFont(new Font("System Bold", 20.0));


        btnBack.setLayoutX(9.0);
        btnBack.setLayoutY(14.0);
        btnBack.setMnemonicParsing(false);
        btnBack.setPrefHeight(40.0);
        btnBack.setPrefWidth(77.0);
        btnBack.setStyle("-fx-background-color: #b69121; -fx-background-radius: 100;");
        btnBack.setText("Back");
        btnBack.setTextFill(javafx.scene.paint.Color.WHITE);
        btnBack.setFont(new Font("System Bold", 20.0));

        pieChart.setAnimated(false);
        pieChart.setLayoutX(132.0);
        pieChart.setLayoutY(163.0);
        pieChart.setLegendSide(javafx.geometry.Side.TOP);
        pieChart.setPrefHeight(182.0);
        pieChart.setPrefWidth(318.0);


        getChildren().add(text);
        getChildren().add(button);
        getChildren().add(button0);
        getChildren().add(pieChart);
        getChildren().add(btnBack);

        /**
         *
         */

    }

    public void refreshChart(){
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                // Update the chart
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        // put random number with current time
                        pieChartData.set(0, new PieChart.Data("Offline Players", databaseConnection.offlinePlayersCount()));
                        pieChartData.set(1, new PieChart.Data("Online Players", databaseConnection.olinePlayersCount()));

                        pieChartData.forEach(data ->
                                data.nameProperty().bind(
                                        Bindings.concat(
                                                data.getName(), " ", data.pieValueProperty(), " Players"
                                        )
                                )
                        );
                    }
                });
            }
        }, 0, 1, TimeUnit.SECONDS);

    }

    public static void closingEverything() throws IOException {
        if (isServerOn) {
            scheduledExecutorService.shutdown();
        }

        TicTacToeServer.serverHandler.close();//stops main server
        TicTacToeServer.stopClients(); //stops sockets threads at clients side
        TicTacToeServer.deleteInstance();
        isServerOn = false;

    }
}
