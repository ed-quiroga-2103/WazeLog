package DataStructures;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Vector;

public class NodeData {
    private Long x;
    private Long y;
    private String label;
    private Vector<ConnectionData> connections = new Vector<>();


    public NodeData(Long x, Long y, String label) {
        this.x = x;
        this.y = y;
        this.label = label;
    }

    public NodeData() {
    }

    public NodeData(Long x, Long y, String label, Vector<ConnectionData> connections) {
        this.x = x;
        this.y = y;
        this.label = label;
        this.connections = connections;
    }

    public void addNewConnection(NodeData newNode, Boolean isDouble){

        ConnectionData connection = new ConnectionData(isDouble, newNode);
        connections.add(connection);


    }

    public Vector<ConnectionData> getConnections() {
        return connections;
    }

    public JSONObject toJSON(){

        JSONObject js = new JSONObject();

        js.put("x",x);
        js.put("y",y);
        js.put("label", label);

        JSONArray connections = new JSONArray();

        for (ConnectionData conn:this.connections) {
            connections.add(conn.toJSON());
        }

        js.put("connections", connections);

        return js;


    }

    //----------------------------------------------

    public Long getX() {
        return x;
    }

    public void setX(Long x) {
        this.x = x;
    }

    public Long getY() {
        return y;
    }

    public void setY(Long y) {
        this.y = y;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
