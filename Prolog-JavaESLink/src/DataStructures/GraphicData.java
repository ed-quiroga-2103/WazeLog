package DataStructures;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Vector;

public class GraphicData {

    Vector<NodeData> list = new Vector<>();

    public void newNode(String text, Double x, Double y){

        NodeData newNode = new NodeData(x.longValue(),y.longValue(),text);
        list.add(newNode);

    }

    public void newNode(NodeData nodeData){

        list.add(nodeData);

    }

    public void connectNodes(String node1, String node2, Boolean isDouble){

        NodeData nodeData1 = this.getNode(node1);
        NodeData nodeData2 = this.getNode(node2);

        nodeData1.addNewConnection(nodeData2,isDouble);

        if(isDouble) nodeData2.addNewConnection(nodeData1,isDouble);


    }

    private NodeData getNode(String data){

        NodeData result = null;

        for (NodeData node:list) {

            if(node.getLabel() == data){

                result = node;
                break;

            }

        }

        return result;

    }

    public JSONObject toJSON(){

        JSONObject js = new JSONObject();

        JSONArray arr = new JSONArray();

        for (NodeData node:this.list) {

            arr.add(node.toJSON());

        }

        js.put("nodes", arr);

        return js;
    }

    //Gets the coordinates of all the nodes
    public Vector<Vector<Long>> getCoords(){

        Vector<Vector<Long>> coords = new Vector<>();

        Vector<Long> xCoords = new Vector<>();
        Vector<Long> yCoords = new Vector<>();

        for (NodeData node:this.list) {

            xCoords.add(node.getX());
            yCoords.add(node.getY());

        }

        coords.add(xCoords); coords.add(yCoords);

        return coords;
    }

    public Vector<String> getNames() {
        Vector<String> names = new Vector<>();

        for (NodeData node:this.list) {

            names.add(node.getLabel());

        }

        return names;
    }

    public Vector<NodeData> getList() {
        return list;
    }
}
