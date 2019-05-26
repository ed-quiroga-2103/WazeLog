package Backbone;

import DataStructures.GraphicData;
import DataStructures.NodeData;
import GUI.PopupWindow;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
//Factory that returns nodes for the map display
public class GraphicNodeFactory {

    //New graphic node creation
    public static Label getCircle(Long x, Long y, Color color, GraphicData graphicData){

        //Creates a new circle object and a node
        Circle circle = new Circle();
        NodeData newNode = new NodeData();

        System.out.println("X: "+x.toString()+ " Y: "+y.toString());

        //Positioning the circle
        circle.setCenterX(x);
        circle.setCenterY(y);
        circle.setRadius(15);
        circle.setFill(color);

        //Adding data to NodeData
        newNode.setX(x);
        newNode.setY(y);

        //Creating the drawable label and relocating its position
        Label newLabel = new Label("",circle);
        newLabel.setStyle("-fx-background-color:ghostwhite");
        newLabel.relocate(x,y);

        try {
            //Opens the window to get the name
            PopupWindow popupWindow = new PopupWindow();
            popupWindow.start(newLabel, newNode, graphicData);


        }
        catch (Exception e){e.printStackTrace();}

        //returns the label with all the data
        return newLabel;


    }

    //Single node creation
    public static Label getCircle(Long x, Long y, String text, Color color){
        //Creates a new circle object and a node
        Circle circle = new Circle();
        NodeData newNode = new NodeData();

        System.out.println("X: "+x.toString()+ " Y: "+y.toString());

        //Positioning the circle
        circle.setCenterX(x);
        circle.setCenterY(y);
        circle.setRadius(15);
        circle.setFill(color);

        //Adding data to NodeData
        newNode.setX(x);
        newNode.setY(y);

        //Creating the drawable label and relocating its position
        Label newLabel = new Label(text,circle);
        newLabel.setStyle("-fx-background-color:ghostwhite");
        newLabel.relocate(x,y);



        return newLabel;


    }

}
