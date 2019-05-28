package GUI;

import DataStructures.GraphicData;
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

public class ConsultWindow {
    //Controller
    Controller controller = new Controller();

    //Graphic nodes

    private GridPane gridPane = new GridPane();

    private Button button = new Button("Consultar");
    MenuBar menuBar = new MenuBar();
    VBox vBox = new VBox(0, menuBar,gridPane);
    private Stage primaryStage = new Stage();
    private ChoiceBox<String> originBox = new ChoiceBox<>();
    private ChoiceBox<String> destinyBox = new ChoiceBox<>();
    private TextField distance = new TextField();

    public void start(GraphicData graphicData, TextArea display) throws Exception{

        //Graphic settings
        originBox.setMinWidth(200);
        destinyBox.setMinWidth(200);
        gridPane.setPadding(new Insets(10, 10, 10, 10)); //margins around the whole grid

        Menu menu1 = new Menu("Destinos Intermedios");
        MenuItem menuNew = new MenuItem("Nuevo Destino Intermedio");
        MenuItem menuCon = new MenuItem("Eliminar Destino Intermedio");


        menu1.getItems().addAll(menuNew,menuCon);
        menuBar.getMenus().addAll(menu1);


        controller.fillBoxes(originBox, graphicData);
        destinyBox.setItems(originBox.getItems());
        gridPane.setHgap(10);
        gridPane.setVgap(10);


        gridPane.add(new Label("Origen:"), 0, 0);
        gridPane.add(originBox, 1, 0);

        gridPane.add(new Label("Destino"), 0, 1);
        gridPane.add(destinyBox, 1, 1);
        HBox buttonBox = new HBox(button);
        buttonBox.setAlignment(Pos.CENTER);
        gridPane.add(new Label("Destinos Intermedios:"), 0, 2);
        gridPane.add(distance, 1, 2);
        Label warning = new Label("");
        gridPane.add(warning, 0, 4, 2, 2);
        gridPane.add(buttonBox,0,6,2,2);


        //Action events
        menuNew.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                IDestWindow iDestWindow = new IDestWindow();

                try {
                    Boolean originEmpty = originBox.getValue() == null;
                    Boolean destinyEmpty = destinyBox.getValue() == null;


                    if (!originEmpty & !destinyEmpty) {
                        iDestWindow.start(distance,graphicData, originBox.getValue(), destinyBox.getValue());
                    } else {

                        warning.setTextFill(Color.RED);
                        warning.setText("Debe seleccionar un origen y un destino");
                    }
                }
                catch (Exception e ){e.printStackTrace();}            }
        });
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                Boolean originEmpty = originBox.getValue() == null;
                Boolean destinyEmpty = destinyBox.getValue() == null;


                if (!originEmpty & !destinyEmpty) {

                    String consult = controller.buildConsult(originBox.getValue().toLowerCase(), distance.getText().toLowerCase()
                            , destinyBox.getValue().toLowerCase());

                    controller.UpdateText(consult,display,true);
                    primaryStage.close();
                } else {

                    warning.setTextFill(Color.RED);
                    warning.setText("Todas las casillas deben tener la informacion requerida");

                }
            }
        });


        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);

        primaryStage.setScene(new Scene(vBox));
        primaryStage.setX(800);
        primaryStage.setY(400);

        primaryStage.show();

    }
}