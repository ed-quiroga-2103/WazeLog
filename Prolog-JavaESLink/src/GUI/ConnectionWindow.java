package GUI;

import DataStructures.GraphicData;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ConnectionWindow {
    Controller controller = new Controller();

    private GridPane gridPane = new GridPane();
    private Button button = new Button("Finalizar");
    private Stage primaryStage = new Stage();

    private ChoiceBox<String> originBox = new ChoiceBox<>();
    private ChoiceBox<String> destinyBox = new ChoiceBox<>();
    private CheckBox isDouble = new CheckBox("Se conecta hacia ambas direcciones?");

    public void start(GraphicData graphicData) {

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
        gridPane.add(isDouble,0,2);
        gridPane.add(buttonBox,0,3,2,2);


        VBox vBox = new VBox(gridPane);
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);

        primaryStage.setScene(new Scene(vBox));

        primaryStage.show();


    }
}
