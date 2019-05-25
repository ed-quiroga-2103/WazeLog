package Backbone;

import DataStructures.GraphicData;
import DataStructures.NodeData;
import GUI.PopupWindow;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GraphicNodeFactory {

    //Graphic node creation
    public static Label getCircle(Long x, Long y, Color color, GraphicData graphicData){

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
        newLabel.relocate(x,y);

        try {

            PopupWindow popupWindow = new PopupWindow();
            popupWindow.start(newLabel, newNode, graphicData);


        }
        catch (Exception e){e.printStackTrace();}


        return newLabel;


    }

    public static Label getCircle(Long x, Long y, String text, Color color){

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
        newLabel.relocate(x,y);



        return newLabel;


    }

}
