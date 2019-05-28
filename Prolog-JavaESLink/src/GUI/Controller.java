package GUI;

import Backbone.GraphicNodeFactory;
import Backbone.JSONManager;
import DataStructures.ConnectionData;
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
import javafx.scene.shape.Line;
import java.lang.Math;


import java.awt.*;
import java.util.LinkedList;
import java.util.Vector;

public class Controller {

    JSONManager jsManager = new JSONManager();

    String displayText = new String();

    Color colorlist[] = {Color.BLUE, Color.RED, Color.CYAN, Color.GREEN, Color.YELLOW, Color.PURPLE};

    Integer colorInd = 0;

    //Updating text on screen
    public void UpdateText(TextField textInput, TextArea textDisplay){

        String text = this.replaceSpace(textInput.getText());

        displayText+= text + "\n";

        textDisplay.setText(this.displayText);

        textInput.clear();
        textDisplay.setScrollTop(Double.MAX_VALUE);

    }
    public void UpdateText(String text, TextArea textDisplay, Boolean clear){
        if(clear) {
            displayText = "";
            displayText += text;
            textDisplay.setText(this.displayText);

            textDisplay.setScrollTop(Double.MAX_VALUE);
        }
        else{
            displayText += text;
            textDisplay.setText(this.displayText);

            textDisplay.setScrollTop(Double.MAX_VALUE);
        }
    }

    //Fills the Choice Box with the nodes data
    public void fillBoxes(ChoiceBox<String> choiceBox, GraphicData graphicData){

        for (NodeData node:graphicData.getList()) {

            choiceBox.getItems().add(node.getLabel());

        }


    }

    public void fillBoxesExcept(ChoiceBox<String> choiceBox, GraphicData graphicData, String exc1, String exc2){

        for (NodeData node:graphicData.getList()) {

            if(node.getLabel() != exc1 & node.getLabel() != exc2){

                choiceBox.getItems().add(node.getLabel());
            }
        }
    }

    public String buildConsult(String origin, String interm, String destiny){
        String consult;

        if(interm.length()!= 0) {
            consult = origin + "," + interm + "," + destiny;
        }
        else{
            consult = origin+","+destiny;
        }
        return consult;

    }

    public String replaceSpace(String text){

        String result = text.replaceAll(" ", "_").toLowerCase();

        return result;

    }

    public String deleteElement(String input, String element){
        String output = new String();
        String[] parts = input.split(",");
        LinkedList<String> holder = new LinkedList<String>();
        int i = 0;
        for (String part : parts) {
            System.out.println(part);
            holder.add(part);
        }
        holder.remove(element);
        for (String elem: holder){
            if(i==0){
                output.concat(elem);
                i++;
            }else{
                output.concat(" "+ elem);
            }
            output= output.replaceAll(" ", ",");
        }
        System.out.println(output);
        return output;
    }

    public String replaceUnderscore(String answer){
        String result = answer.replaceAll("_", "_");
        return result;
    }

    //Draws nodes from metadata
    public void drawExistingNodes(Group root, GraphicData graphicData){

        Vector<Vector<Long>> coords = graphicData.getCoords();
        Vector<String> names = graphicData.getNames();

        //Draws a circle from the coordinates and assigns its corresponding name
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

    public void connectNodes(String node1, String node2, Integer distance, Boolean isDouble, GraphicData graphicData, Group root){

        graphicData.connectNodes(node1,node2,isDouble);

        NodeData node = graphicData.getNode(node1);
        NodeData conn = graphicData.getNode(node2);

        Double x1 = (double) node.getX()+15;
        Double x2 = (double) conn.getX()+15;
        Double y1 = (double) node.getY()+15;
        Double y2 = (double) conn.getY()+15;

        if(isDouble) {
            Line line = new Line(x1, y1, x2, y2);
            line.toBack();
            line.setStrokeWidth(5);
            line.setStroke(Color.GREEN);
            root.getChildren().add(1,line);

        }
        else{
            Double midX = (x1+x2)/2;
            Double midY = (y1+y2)/2;

            Line line = new Line(x1, y1, midX, midY);
            line.setStrokeWidth(5);
            line.setStroke(Color.GREEN);
            root.getChildren().add(1,line);

            Line line2 = new Line(midX, midY, x2, y2);
            line2.setStrokeWidth(5);
            line2.setStroke(Color.YELLOW);
            root.getChildren().add(1,line2);

        }


        try {
            jsManager.writeJSON(graphicData.toJSON());
        }
        catch (Exception e) {e.printStackTrace();}


    }

    //Draws lines from metadata
    public void drawExistingLines(Group root, GraphicData graphicData){

        for (NodeData node:graphicData.getList()) {

            for (ConnectionData conn:node.getConnections()) {

                Double x1 = (double) node.getX()+15;
                Double x2 = (double) conn.getX()+15;
                Double y1 = (double) node.getY()+15;
                Double y2 = (double) conn.getY()+15;

                if(conn.getDouble()) {
                    Line line = new Line(x1, y1, x2, y2);
                    line.setStrokeWidth(5);
                    line.setStroke(Color.GREEN);
                    root.getChildren().add(line);
                }
                else{

                    Double midX = (x1+x2)/2;
                    Double midY = (y1+y2)/2;

                    Line line = new Line(x1, y1, midX, midY);
                    line.setStrokeWidth(5);
                    line.setStroke(Color.GREEN);
                    root.getChildren().add(line);

                    Line line2 = new Line(midX, midY, x2, y2);
                    line2.setStrokeWidth(5);
                    line2.setStroke(Color.YELLOW);
                    root.getChildren().add(line2);


                }
            }

        }

    }


}


