package GUI;

import Backbone.JSONManager;
import DataStructures.GraphicData;
import DataStructures.NodeData;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PopupWindow{
    //Controller declaration
    private Controller controller = new Controller();

    //Graphic nodes declarations
    private GridPane gridPane = new GridPane();
    private Button button = new Button("Finalizar");
    private TextField name = new TextField();
    private Group root = new Group(gridPane);


    public void start(Label label, NodeData newNode, GraphicData graphicData) throws Exception {
        //Basic settings
        Stage primaryStage = new Stage();
        gridPane.add(button, 0 ,1);
        gridPane.add(name,0,0);

        //Adding node to the metadata file
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                controller.addNode(label,name.getText(),newNode,graphicData);
                primaryStage.close();
            }
        });

        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }
}
