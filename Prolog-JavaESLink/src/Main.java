import DataStructures.Graph;
import DataStructures.GraphNode;
import DataStructures.PriorityStack;

public class Main {

    public static void main(String[] args) {

        Graph<String> graph = new Graph<String>();

        graph.newNode("Alajuela");
        graph.newNode("San Jose");
        graph.newNode("Cartago");
        graph.newNode("Heredia");
        graph.newNode("Limon");
        graph.newNode("Guanacaste");
        graph.newNode("Puntarenas");

        graph.connectNodes("Alajuela", "San Jose", 5);
        graph.connectNodes("San Jose", "Cartago", 3);
        graph.connectNodes("Cartago", "Limon", 7);
        graph.connectNodes("Heredia", "Alajuela", 3);
        graph.connectNodes("San Jose", "Heredia", 3);
        graph.connectNodes("Puntarenas", "Guanacaste", 8);
        graph.connectNodes("Alajuela", "Guanacaste", 11);

        graph.getRoute("Alajuela","Puntarenas");

    }
}
