package DataStructures;

import java.util.Collections;
import java.util.Vector;

public class Graph<T> {


    private Integer currentID = 0;

    private Vector<GraphNode<T>> nodeList = new Vector<GraphNode<T>>();

    /**
     * Creates a new node and it is included in the current graph structure
     * @param data
     * Input: The data of the node that is about to be created
     * Output: None
     */
    public void newNode(T data){

        GraphNode<T> newNode = new GraphNode<T>(data, this.currentID);

        this.nodeList.add(newNode);
        this.currentID++;

    }

    public void getRoute(T origin, T destiny){

        PriorityStack<T> stack = new PriorityStack<>();
        GraphNode<T> current = this.getNode(origin);
        Vector<GraphNode<T>> visited = new Vector<GraphNode<T>>();
        Vector<Integer> distances = this.fillVector(this.nodeList.size());
        distances.set(this.nodeList.indexOf(current),0);
        stack.insertNode(current,null,0);

        while(visited.size() != this.nodeList.size()){

            for (Arc<T> adjacent :current.getArcList()) {

                Integer currentInd = this.nodeList.indexOf(current);
                Integer adjInd = this.nodeList.indexOf(adjacent.getNode());
                Integer distance = distances.get(currentInd) + adjacent.getWeight();

                if(distances.get(adjInd) == -1){
                    distances.set(adjInd,distance);
                    stack.insertNode(adjacent.getNode(),current,distance);

                }
                else if(distances.get(adjInd) > distance){
                    distances.set(adjInd,distance);
                    stack.insertNode(adjacent.getNode(),current,distance);

                }


            }

            visited.add(current);

            current = this.getNextUnvisited(visited,distances);

        }

        GraphNode<T> originNode = this.getNode(origin);
        GraphNode<T> destinyNode = this.getNode(destiny);
        Vector<GraphNode<T>> route = new Vector<>();

        while(destinyNode != originNode){


            GraphNode<T> prev = stack.getNode(destinyNode).getPrevious();

            route.add(destinyNode);

            destinyNode = prev;

        }

        route.add(originNode);

        Integer distance = distances.get(this.nodeList.indexOf(this.getNode(destiny)));

        System.out.println("Route: ");

        Collections.reverse(route);

        for (GraphNode<T> node:route) {

            System.out.print(node.getData()+">>");

        }
        System.out.println("Done");
        System.out.println("Distance: "+distance);




    }

    public Boolean connectNodes(T data1, T data2, Integer weight){

        if(this.nodeExists(data1) & this.nodeExists(data2)){

            GraphNode<T> node1 = this.getNode(data1);
            GraphNode<T> node2 = this.getNode(data2);

            node1.newArc(node2, weight);
            node2.newArc(node1, weight);

            return true;

        }

        return false;

    }

    public Boolean nodeExists(T data){

        for (GraphNode<T> current:this.nodeList) {

            if(current.getData() == data) return true;

        }

        return false;

    }

    public GraphNode<T> getNode(T data){

        for (GraphNode<T> current:this.nodeList) {

            if(current.getData() == data) return current;

        }

        return null;

    }

    public void getDijksDistances(T data) {

        GraphNode<T> current = this.getNode(data);
        Vector<GraphNode<T>> visited = new Vector<GraphNode<T>>();
        Vector<Integer> distances = this.fillVector(this.nodeList.size());
        distances.set(this.nodeList.indexOf(current),0);


        while(visited.size() != this.nodeList.size()){

            for (Arc<T> adjacent :current.getArcList()) {

                Integer currentInd = this.nodeList.indexOf(current);
                Integer adjInd = this.nodeList.indexOf(adjacent.getNode());
                Integer distance = distances.get(currentInd) + adjacent.getWeight();

                if(distances.get(adjInd) == -1){
                    distances.set(adjInd,distance);
                }
                else if(distances.get(adjInd) > distance){
                    distances.set(adjInd,distance);
                }

            }

            visited.add(current);

            current = this.getNextUnvisited(visited,distances);



        }


        for(int i = 0; i < this.nodeList.size(); i++){

            System.out.println("Node: " + this.nodeList.get(i).getData() + " Distance: "+ distances.get(i));

        }


    }

    private GraphNode<T> getNextUnvisited(Vector<GraphNode<T>> visited, Vector<Integer> distances){

        Integer min = -1;

        GraphNode<T> next = null;

        for(int i = 0; i < distances.size(); i++){

            if(distances.get(i)!=-1){

                if(min == -1 & !visited.contains(this.nodeList.get(i))){

                    next = this.nodeList.get(i);

                }
                else if(distances.get(i) < min & !visited.contains((this.nodeList.get(i)))){

                    next = this.nodeList.get(i);

                }

            }

        }

        return next;

    }

    public Vector<Integer> fillVector(Integer n){

        Vector<Integer> result = new Vector<Integer>();

        for (int i = 0; i < n; i++) {

            result.add(-1);

        }

        return result;

    }



}
