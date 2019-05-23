package GUI;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("test.fxml"));
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(new Scene(root, Controller.vBox.getPrefWidth(), Controller.vBox.getPrefHeight()));
        primaryStage.setResizable(false);
        primaryStage.show();



    }


    public static void main(String[] args) {
        launch(args);

    }


}
