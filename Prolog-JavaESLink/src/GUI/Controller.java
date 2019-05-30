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
import org.jpl7.Atom;
import org.jpl7.Query;
import org.jpl7.Term;
import org.jpl7.Variable;

import java.lang.Math;


import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class Controller {

    JSONManager jsManager = new JSONManager();

    String displayText = new String();

    Color colorlist[] = {Color.BLUE, Color.RED, Color.CYAN, Color.GREEN, Color.YELLOW, Color.PURPLE};

    Integer colorInd = 0;

    //Updating text on screen
    public void UpdateText(TextField textInput, TextArea textDisplay){

        String text = textInput.getText();

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
    //Fills the Choice Box excluding two arguments
    public void fillBoxesExcept(ChoiceBox<String> choiceBox, GraphicData graphicData, String exc1, String exc2){

        for (NodeData node:graphicData.getList()) {

            if(node.getLabel() != exc1 & node.getLabel() != exc2){

                choiceBox.getItems().add(node.getLabel());
            }
        }
    }
    //Builds the consult string for Prolog
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
    //Replaces a space with an underscore
    public String replaceSpace(String text){

        String result = text.replaceAll(" ", "_").toLowerCase();

        return result;

    }
    //Deletes an element from a string divided by commas
    public String deleteElement(String input, String element){
        String output = new String();
        String[] parts = input.split(",");
        LinkedList<String> holder = new LinkedList<String>();
        int i = 0;
        for (String part : parts) {
            holder.add(part);
        }
        holder.remove(element);
        for (String elem: holder){
            if(i==0){
                output = output.concat(elem);
                i++;
            }else{
                output = output.concat(" "+ elem);
            }
            output= output.replaceAll(" ", ",");
        }
        return output;
    }
    //Replaces an underscore with a space
    public String replaceUnderscore(String answer){
        String result = answer.replaceAll("_", " ");
        return result;
    }
    //Divides a string into pairs divided by commas
    public LinkedList<LinkedList<String>> getPairs(String consulta){
        // Split line on comma.
        LinkedList<String> temp = new LinkedList<String>();
        LinkedList<LinkedList<String>> output = new LinkedList<LinkedList<String>>();
        String[] parts = consulta.split(",");

        for (String part : parts) {
            temp.add(part);
        }
        for (int i = 1; i< temp.size(); i++){
            LinkedList<String> temp2 = new LinkedList<String>();
            temp2.add(temp.get(i-1));
            temp2.add(temp.get(i));

            output.add(temp2);
        }
        return output;

    }
    //Consults all the routes in a complete course
    public LinkedList<String> getAllRoutes(String route){

        LinkedList<LinkedList<String>> routes = this.getPairs(route);
        LinkedList<String> list = new LinkedList<>();
        for(int i =0; i<routes.size(); i++){

            list.add(i+1+". "+this.consultRoute(routes.get(i).get(0), routes.get(i).get(1)));

        }

        return list;

    }
    //Consults if a route exist
    public String consultRoute(String origin, String destiny){

        String finalRoute = "";

        Query q1 =
                new Query(
                        "consult",
                        new Term[]{new Atom("/home/eduardo/Documents/WazeLog/PrologFiles/Dijkstra.pl")}
                );
        System.out.println("Consult " + (q1.hasSolution() ? "succeeded" : "failed"));
        Variable X = new Variable("X");
        Variable Y = new Variable("Y");

        origin = this.replaceSpace(origin);
        destiny = this.replaceSpace(destiny);
        try {
            Query q4 =
                    new Query(
                            "go",
                            new Term[]{new Atom(origin), new Atom(destiny), X, Y}
                    );
            java.util.Map<String, Term> solution;

            solution = q4.oneSolution();

            Term[] arr = solution.get("X").toTermArray();

            finalRoute += "Su ruta es ";

            Controller cont = new Controller();

            for (int i = 0; i < arr.length; i++) {

                finalRoute += cont.replaceUnderscore(arr[i].toString()) + " ";

            }

            finalRoute += "y su distancia para esta ruta es ";
            finalRoute += solution.get("Y").toString();


            return finalRoute;
        }
        catch (Exception e){return "No hay ruta entre los puntos seleccionados";}
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


