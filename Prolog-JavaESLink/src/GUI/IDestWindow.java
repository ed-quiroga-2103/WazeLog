package GUI;

import DataStructures.GraphicData;
import DataStructures.NodeData;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class IDestWindow {
    //Controller declaration
    private Controller controller = new Controller();

    //Graphic nodes declarations
    private GridPane gridPane = new GridPane();
    private Button button = new Button("Agregar");
    private ChoiceBox<String> placeBox = new ChoiceBox<>();
    private Group root = new Group(gridPane);


    public void start(TextField field, GraphicData graphicData, String exc1, String exc2) throws Exception {
        //Basic settings

        controller.fillBoxesExcept(placeBox, graphicData, exc1, exc2);
        gridPane.setPadding(new Insets(10, 10, 10, 10)); //margins around the whole grid

        Stage primaryStage = new Stage();
        gridPane.setPadding(new Insets(10, 10, 10, 10)); //margins around the whole grid
        gridPane.add(new Label("Destino:"),0,0);
        gridPane.add(placeBox,1,0);
        HBox buttonBox = new HBox(button);
        buttonBox.setAlignment(Pos.CENTER);
        gridPane.add(buttonBox,0,2,2,2);

        //Adding node to the metadata file
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String text = field.getText();

                if(text.length() != 0) {
                    text += "," + placeBox.getValue();

                    field.setText(text);
                }
                else{

                    text+=placeBox.getValue();
                    field.setText(text);

                }
                primaryStage.close();
            }
        });

        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

}
