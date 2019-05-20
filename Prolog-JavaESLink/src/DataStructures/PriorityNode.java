package DataStructures;

public class PriorityNode<T> {
    GraphNode<T> node;
    GraphNode<T> previous;
    int weight;

    public PriorityNode(GraphNode<T> node, GraphNode<T> previous, int weight) {
        this.node = node;
        this.previous = previous;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public GraphNode<T> getPrevious() {
        return previous;
    }

    public void setPrevious(GraphNode<T> previous) {
        this.previous = previous;
    }

    public GraphNode<T> getNode() {
        return node;
    }

    public void setNode(GraphNode<T> node) {
        this.node = node;
    }
}
