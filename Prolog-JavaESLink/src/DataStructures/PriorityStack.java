package DataStructures;

import java.util.Vector;

public class PriorityStack<T>{

    Vector<PriorityNode<T>> stack = new Vector<>();

    public PriorityNode<T> getNext(){

        if(this.stack.size() != 0) {
            PriorityNode<T> next = this.stack.firstElement();
            return next;
        }
        else return null;
    }
    public void printWeights(){

        for (PriorityNode<T> node: this.stack) {

            System.out.println(node.weight);

        }
    }


    public void insertNode(GraphNode<T> newNode, GraphNode<T> previous, int weight){

        if(!this.contains(newNode)) {

            for (int i = 0; i < this.stack.size(); i++) {

                if (this.stack.get(i).getWeight() > weight) {

                    this.stack.insertElementAt(new PriorityNode<T>(newNode, previous, weight), i);
                    return;
                }

            }
            this.stack.add(new PriorityNode<T>(newNode, previous, weight));
        }
        else{

            this.stack.remove(getNode(newNode));

            for (int i = 0; i < this.stack.size(); i++) {

                if (this.stack.get(i).getWeight() > weight) {

                    this.stack.insertElementAt(new PriorityNode<T>(newNode, previous, weight), i);
                    return;
                }

            }
            this.stack.add(new PriorityNode<T>(newNode, previous, weight));


        }
    }

    private boolean contains(GraphNode<T> node){

        for (PriorityNode<T> current: this.stack) {

            if(node == current.getNode()) return true;

        }

        return false;

    }
    public PriorityNode<T> getNode(GraphNode<T> node){

        for (PriorityNode<T> current:this.stack) {

            if(current.getNode() == node) return current;

        }
        return null;

    }

    public void printNodes(){

        for (PriorityNode<T> current:stack) {

            System.out.println("Current: "+current.getNode().getData());
            if(current.getPrevious() == null) System.out.println("Previous: null");
            else System.out.println("Previous: "+current.getPrevious().getData());
        }

    }

    public Vector<PriorityNode<T>> getStack() {
        return stack;
    }

    public void setStack(Vector<PriorityNode<T>> stack) {
        this.stack = stack;
    }
}
