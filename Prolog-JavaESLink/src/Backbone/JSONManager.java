package Backbone;

import DataStructures.ConnectionData;
import DataStructures.GraphicData;
import DataStructures.NodeData;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Node;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Vector;

public class JSONManager {
    //Writes a JSON Object into a text file
    public void writeJSON(JSONObject js) throws Exception{

        PrintWriter writer = new PrintWriter("metadata.txt", "UTF-8");

        writer.println(js.toString());

        writer.close();

    }

    //Reads a text file and parses it into a JSON Object
    public JSONObject readJSON(String path) {

        try {

            String data = new String(Files.readAllBytes(Paths.get(path)));

            JSONParser parser = new JSONParser();

            return (JSONObject) parser.parse(data);
        }
        catch (Exception e){

            return null;

        }
    }

    //Returns a GraphicData Object from a JSON Object
    public GraphicData getGraphicDataFromJson(JSONObject js){
        if (js != null) {
            GraphicData graphicData = new GraphicData();
            JSONArray arr = (JSONArray) js.get("nodes");

            for (int i = 0; i < arr.size(); i++) {

                graphicData.newNode(this.getNodeFromJSON((JSONObject) arr.get(i)));

            }

            return graphicData;
        }
        else {
            return new GraphicData();
        }
    }

    //Returns a NodeData Object from a JSON Object
    private NodeData getNodeFromJSON(JSONObject js){


        String label = (String) js.get("label");
        Long x = (Long) js.get("x");
        Long y = (Long) js.get("y");

        Vector<ConnectionData> connectionData = this.getConnectionFromJSON((JSONArray) js.get("connections"));

        NodeData newNode = new NodeData(x,y,label,connectionData);

        return newNode;
    }

    //Returns a Vector with ConnectionData Objects from a JSON Object
    private Vector<ConnectionData> getConnectionFromJSON(JSONArray arr){

        Vector<ConnectionData> result = new Vector<>();

        for (int i = 0; i < arr.size(); i++) {

            JSONObject js = (JSONObject) arr.get(i);

            result.add(new ConnectionData((Boolean) js.get("isDouble"),(Long) js.get("x"), (Long) js.get("y")));

        }

        return result;

    }

}
