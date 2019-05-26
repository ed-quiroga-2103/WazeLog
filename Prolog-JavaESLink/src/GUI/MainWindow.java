package GUI;

import Backbone.HelpTextFactory;
import Backbone.JSONManager;
import DataStructures.GraphicData;

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

import javafx.stage.Stage;

import org.jpl7.Atom;
import org.jpl7.Query;
import org.jpl7.Term;
import org.json.simple.JSONObject;



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
    Canvas canvas = new Canvas(600, 300);
    HBox hBox = new HBox(10, text, button);
    Group root = new Group(gridPane);
    GridPane gridPane2 = new GridPane();

    VBox vBox = new VBox(0, menuBar, root, gridPane2);

    Scene mainRoot = new Scene(vBox, Color.BLACK);

    Color colorlist[] = {Color.BLUE, Color.RED, Color.LIGHTPINK, Color.GREEN, Color.YELLOW, Color.PURPLE};

    Integer colorInd = 0;
    Boolean creatingNode = false;


    @Override
    public void start(Stage primaryStage) throws Exception {

        //Logic Settings
        JSONObject jsonData = jsManager.readJSON("metadata.txt");
        graphicData = jsManager.getGraphicDataFromJson(jsonData);
        controller.UpdateText("Bienvenido a Dr.Wazelog!\nLa mejor forma de llegar a sus destinos.\n" +
                "En la pantalla se muesta el mapa registrado. Las conexiones verdes van en ambos sentidos mientras que las amarillas " +
                "con verde solamente van en un sentido." +
                " El segmento verde de la linea parte del origen y el amarillo concluye en el destino.\n" +
                "Para realizar una consulta, envie un mensaje que diga 'Quiero realizar una consulta' o" +
                " presione el boton 'Realizar Consulta' en el menu 'Opciones'.", display, false);


        //Drawing nodes from metadata
        controller.drawExistingLines(root, graphicData);
        controller.drawExistingNodes(root, graphicData);
        display.setWrapText(true);

        //Graphic Settings
        gridPane.setAlignment(Pos.CENTER);
        gridPane2.setAlignment(Pos.CENTER);
        vBox.setAlignment(Pos.CENTER);

        Menu menu1 = new Menu("Opciones");
        MenuItem menuConsult = new MenuItem("Realizar Consulta");
        MenuItem menuConnection = new MenuItem("Nueva Conexion");
        MenuItem createNode = new MenuItem("Nueva Ubicacion");

        Menu menu2 = new Menu("Ayuda");
        Menu help = new Menu("Mostar Ayuda en Pantalla Sobre:");

        MenuItem helpCons = new MenuItem("Consultas");
        MenuItem helpMap = new MenuItem("Agregar Nuevos Lugares");
        MenuItem helpConn = new MenuItem("Agregar Nuevas Conexiones");
        MenuItem helpDis = new MenuItem("Informacion del Mapa");


        menu1.getItems().addAll(menuConsult,createNode,menuConnection);

        help.getItems().addAll(helpCons,helpMap, helpConn, helpDis);
        menu2.getItems().add(help);
        menuBar.getMenus().addAll(menu1,menu2);

        gridPane.add(canvas, 0, 0);
        gridPane2.add(display, 0, 1);
        display.setMinWidth(702.0);
        gridPane2.add(hBox, 0, 2);

        text.setMinWidth(577);
        button.setMinWidth(110);
        canvas.setWidth(602.0);


        //Background color
        vBox.setStyle("-fx-background-color: linear-gradient(\n" +
                "    from 0px 0px to 0px 600px, \n" +
                "      darkseagreen, darkslategray 100%" +
                "    )");
        gridPane.setStyle("-fx-background-color: ghostwhite");


        //Main window settings
        primaryStage.setScene(mainRoot);
        primaryStage.setWidth(750);
        primaryStage.setResizable(false);
        display.setEditable(false);

        //Action Events

        //Option actions
        createNode.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                creatingNode = true;
                controller.UpdateText("Dale click en la pantalla donde quieres colocar la nueva ubicacion.\n", display, true);
            }
        });
        menuConnection.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ConnectionWindow con = new ConnectionWindow();
                con.start(graphicData, root);
            }
        });
        //Help
        helpCons.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String helpText = HelpTextFactory.getHelp(1);

                controller.UpdateText(helpText,display,true);
            }
        });
        helpMap.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                String helpText = HelpTextFactory.getHelp(2);

                controller.UpdateText(helpText,display,true);
            }
        });
        helpConn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String helpText = HelpTextFactory.getHelp(3);

                controller.UpdateText(helpText,display,true);
            }
        });
        helpDis.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String helpText = HelpTextFactory.getHelp(4);

                controller.UpdateText(helpText,display,true);
            }
        });

        //Sending text
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (text.getText().length() != 0) {
                    controller.UpdateText(text, display);
                }
            }
        });

        //Sending text
        text.setOnKeyPressed(ke -> {
            KeyCode keyCode = ke.getCode();
            if (keyCode.equals(KeyCode.ENTER) & text.getText().length() != 0) {
                controller.UpdateText(text, display);
            }
                }
        );

        //New node creation
        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (creatingNode) {
                    Double mouseEventX = mouseEvent.getX();
                    Double mouseEventY = mouseEvent.getY();

                    Label newCircle = controller.drawCircle(mouseEventX.longValue(),
                            mouseEventY.longValue(), colorlist[colorInd], graphicData);
                    colorInd = (colorInd + 1) % colorlist.length;
                    root.getChildren().add(newCircle);

                    creatingNode = false;
                    controller.UpdateText("Nueva ubicacion colocada!\n", display, false);
                }
            }
        });

        primaryStage.show();

    }

    public static void main(String[] args) throws Exception {
        launch(args);
  /*      Query q1 =
                new Query(
                        "consult",
                        new Term[]{new Atom("test.pl")}
                );

        System.out.println( "consult " + (q1.hasSolution() ? "succeeded" : "failed"));
        Query q2 =
                new Query(
                        "child_of",
                        new Term[] {new Atom("joe"),new Atom("ralf")}
                );
        System.out.println(
                "child_of(joe,ralf) is " +
                        ( q2.hasSolution() ? "provable" : "not provable" )
        );
*/
    }
}
