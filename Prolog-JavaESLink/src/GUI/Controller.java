package GUI;

import Backbone.GraphicNodeFactory;
import Backbone.JSONManager;
import DataStructures.GraphicData;
import DataStructures.NodeData;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.awt.*;
import java.util.Vector;

public class Controller {

    JSONManager jsManager = new JSONManager();

    String displayText = new String();

    Color colorlist[] = {Color.BLUE, Color.RED, Color.CYAN, Color.GREEN, Color.YELLOW, Color.PURPLE};

    Integer colorInd = 0;

    //Updating text on screen
    public void UpdateText(TextField textInput, TextArea textDisplay){

        displayText+= textInput.getText() + "\n";

        textDisplay.setText(this.displayText);

        textInput.clear();
        textDisplay.setScrollTop(Double.MAX_VALUE);

    }

    public void drawPath(){

    }

    public void fillBoxes(ChoiceBox<String> choiceBox, GraphicData graphicData){

        for (NodeData node:graphicData.getList()) {

            choiceBox.getItems().add(node.getLabel());

        }


    }

    public void drawExistingNodes(Group root, GraphicData graphicData){

        Vector<Vector<Long>> coords = graphicData.getCoords();
        Vector<String> names = graphicData.getNames();

        for (int i = 0; i < coords.get(0).size(); i++) {

            Label newCircle = this.drawCircle(coords.get(0).get(i),coords.get(1).get(i), names.get(i),colorlist[colorInd]);
            colorInd = (colorInd+1)%colorlist.length;
            root.getChildren().add(newCircle);

        }

    }

    //Drawing node on screen
    public Label drawCircle(Long x, Long y, Color color, GraphicData graphicData){

        return GraphicNodeFactory.getCircle(x,y,color,graphicData);

    }
    public Label drawCircle(Long x, Long y, String text, Color color){

        return GraphicNodeFactory.getCircle(x,y,text,color);

    }

    //Adding node to the metadata file
    public void addNode(Label label, String name, NodeData newNode, GraphicData graphicData){
        label.setText(name);
        newNode.setLabel(name);
        graphicData.newNode(newNode);
        try {
            jsManager.writeJSON(graphicData.toJSON());
        }
        catch (Exception e) {e.printStackTrace();}
    }

    public void connectNodes(String node1, String node2, Integer distance, Boolean isDouble, GraphicData graphicData){

        graphicData.connectNodes(node1,node2,isDouble);

    }

}


