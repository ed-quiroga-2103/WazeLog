package DataStructures;


public class Arc<T> {

    private GraphNode<T> node;
    private Integer weight = 0;

    Arc(GraphNode<T> node, Integer weight){

        this.node = node;
        this.weight = weight;

    }


    //Getters and Setters for all attributes of the class
    public GraphNode<T> getNode() {
        return node;
    }

    public void setNode(GraphNode<T> node) {
        this.node = node;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
