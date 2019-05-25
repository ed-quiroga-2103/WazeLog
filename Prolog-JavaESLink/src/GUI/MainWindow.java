package GUI;

import Backbone.JSONManager;
import DataStructures.GraphicData;

import DataStructures.NodeData;
import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.geometry.Pos;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import javafx.stage.Stage;

import org.json.simple.JSONObject;

import java.awt.geom.Arc2D;


public class MainWindow extends Application {
    //Controller and Logic declarations
    Controller controller = new Controller();
    GraphicData graphicData = new GraphicData();
    JSONManager jsManager = new JSONManager();

    //Graphic nodes declarations
    MenuBar menuBar = new MenuBar();
    TextField text = new TextField();
    Button button = new Button("Enviar");
    TextArea display = new TextArea();
    GridPane gridPane = new GridPane();
    Canvas canvas = new Canvas(600,300);
    HBox hBox = new HBox(10,text,button);
    Group root = new Group(gridPane);
    GridPane gridPane2 = new GridPane();

    VBox vBox = new VBox(0,menuBar,root,gridPane2);

    Color colorlist[] = {Color.BLUE, Color.RED, Color.CYAN, Color.GREEN, Color.YELLOW, Color.PURPLE};

    Integer colorInd = 0;





    @Override
    public void start(Stage primaryStage) throws Exception {
        //Logic Settings
        JSONObject jsonData = jsManager.readJSON("metadata.txt");
        graphicData = jsManager.getGraphicDataFromJson(jsonData);

        //Drawing nodes from metadata
        controller.drawExistingNodes(root, graphicData);

        //Graphic Settings
        gridPane.setAlignment(Pos.CENTER);
        gridPane2.setAlignment(Pos.CENTER);
        vBox.setAlignment(Pos.CENTER);

        Menu menu1 = new Menu("Opciones");
        MenuItem menuConnection = new MenuItem("Nueva conexion");

        menu1.getItems().add(menuConnection);
        menuBar.getMenus().add(menu1);

        gridPane.add(canvas, 0,0);
        gridPane2.add(display,0,1);
        display.setMinWidth(702.0);
        gridPane2.add(hBox,0,2);

        text.setMinWidth(577);
        button.setMinWidth(110);
        canvas.setWidth(602.0);

        vBox.setStyle("-fx-background-color: lightseagreen");
        gridPane.setStyle("-fx-background-color: ghostwhite");

        primaryStage.setScene(new Scene(vBox,Color.BLACK));
        primaryStage.setWidth(750);
        primaryStage.setResizable(false);
        display.setEditable(false);

        //Action Events
        menuConnection.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ConnectionWindow con = new ConnectionWindow();
                con.start(graphicData);
            }
        });

        //Sending text
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                controller.UpdateText(text,display);
            }
        });

        //Sending text
        text.setOnKeyPressed(ke -> {
            KeyCode keyCode = ke.getCode();
            if (keyCode.equals(KeyCode.ENTER) & text.getText().length() != 0) {
                controller.UpdateText(text,display);
            }
                }
        );

        //New node creation
        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                Double mouseEventX = mouseEvent.getX();
                Double mouseEventY = mouseEvent.getY();

                Label newCircle = controller.drawCircle(mouseEventX.longValue(),mouseEventY.longValue(),colorlist[colorInd],graphicData);
                colorInd = (colorInd+1)%colorlist.length;
                root.getChildren().add(newCircle);

            }
        });

        primaryStage.show();

    }


    public static void main(String[] args) throws Exception {
        launch(args);

/*
        GraphicData gd = new GraphicData();

        gd.newNode("Alajuela",0,0);
        gd.newNode("San Jose",1,1);
        gd.newNode("Cartago",2,2);
        gd.newNode("Heredia",3,3);

        gd.connectNodes("Alajuela","San Jose",true);
        gd.connectNodes("Heredia","San Jose", true);
        //gd.connectNodes("Cartago", "Heredia",false);

        JSONManager jsonManager = new JSONManager();
        jsonManager.writeJSON(gd.toJSON());
        JSONObject js = jsonManager.readJSON("metadata.txt");

        System.out.println(js.toString());
*/
    }


}
