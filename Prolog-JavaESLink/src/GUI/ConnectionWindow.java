package GUI;

import DataStructures.GraphicData;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ConnectionWindow {
    //Controller
    Controller controller = new Controller();

    //Graphic nodes
    private GridPane gridPane = new GridPane();
    private Button button = new Button("Conectar");
    private Stage primaryStage = new Stage();
    private ChoiceBox<String> originBox = new ChoiceBox<>();
    private ChoiceBox<String> destinyBox = new ChoiceBox<>();
    private TextField distance = new TextField();
    private CheckBox isDouble = new CheckBox("Se conecta hacia ambas direcciones?");

    public void start(GraphicData graphicData, Group root) {

        //Graphic settings
        originBox.setMinWidth(200);
        destinyBox.setMinWidth(200);
        gridPane.setPadding(new Insets(10, 10, 10, 10)); //margins around the whole grid

        controller.fillBoxes(originBox, graphicData);
        destinyBox.setItems(originBox.getItems());
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        gridPane.add(new Label("Origen:"),0,0);
        gridPane.add(originBox,1,0);

        gridPane.add(new Label("Destino"),0,1);
        gridPane.add(destinyBox,1,1);
        HBox buttonBox = new HBox(button);
        buttonBox.setAlignment(Pos.CENTER);
        gridPane.add(new Label("Distancia:"),0,2);
        gridPane.add(distance,1,2);
        Label warning = new Label("");
        gridPane.add(warning,0,4,2,2);
        gridPane.add(isDouble,0,3);
        gridPane.add(buttonBox,0,6,2,2);

        //Button action event
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                Boolean originEmpty = originBox.getValue() == null;
                Boolean destinyEmpty = destinyBox.getValue() == null;



                if(!originEmpty & !destinyEmpty & distance.getText().length() != 0){
                    controller.connectNodes(originBox.getValue(), destinyBox.getValue(), 0, isDouble.isSelected(), graphicData, root);
                    //primaryStage.close();
                }
                else{

                    warning.setTextFill(Color.RED);
                    warning.setText("Todas las casillas deben tener la informacion requerida");

                }
            }
        });


        VBox vBox = new VBox(gridPane);
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);

        primaryStage.setScene(new Scene(vBox));
        primaryStage.setX(800);
        primaryStage.setY(400);

        primaryStage.show();


    }
}
