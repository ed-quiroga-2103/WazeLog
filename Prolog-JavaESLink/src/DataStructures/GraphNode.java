package DataStructures;

import java.util.Vector;

public class GraphNode<T>{

    private T data;
    private int ID;
    private Vector<Arc<T>> arcList = new Vector<Arc<T>>();

    /**
     * Constructor for class
     * @param data
     * @param ID
     */
    GraphNode(T data, int ID){

        this.data = data;
        this.ID = ID;

    }

    /**
     * newArc creates an arc between the current node
     * and another node that is taken as input
     * @param newNode
     * @param weight
     *
     */
    public void newArc(GraphNode<T> newNode, int weight){

        Arc<T> newArc = new Arc<T>(newNode, weight);

        arcList.add(newArc);

        return;

    }

    /**
     * printNodes prints all the nodes connected to the current node
     * Input: None
     * Output: Connected nodes (Printed, not returned; returns void)
     */
    public void printNodes(){

        for (Arc<T> current:arcList) {

            System.out.println(current.getNode().getData());

        }

    }


    //Getters and Setters for all attributes of the class

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Vector<Arc<T>> getArcList() {
        return arcList;
    }

    public void setArcList(Vector<Arc<T>> arcList) {
        this.arcList = arcList;
    }

}
