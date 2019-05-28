package Backbone;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

public class Messenger {

    LinkedList<String> facts = new LinkedList<String>();

    public void readFile() throws IOException {
        // Open this file.
        BufferedReader reader = new BufferedReader(new FileReader("/home/eduardo/Documents/WazeLog/PrologFiles/BaseConocimientos.pl"));

        // Read lines from file.
        while (true) {
            String line = reader.readLine();
            if (line == null) {
                break;
            }
            // Split line on comma.
            String[] parts = line.split("\n");
            for (String part : parts) {
                System.out.println(part);
                facts.add(part);
            }
            System.out.println();
        }

        reader.close();
    }

    public void addRoute(String from, String to, java.lang.Integer weight, java.lang.Boolean doble) throws IOException {
        readFile();
        String newRoute = "edge(" + from + "," + to + "," + weight + ").";
        boolean exists = check(newRoute);
        if (exists)
            System.out.println("Route Already exists");

        else {
            System.out.println("Adding the new Route");
            facts.add(newRoute);
            if(doble){

                String backRoute = "edge(" + to + "," + from + "," + weight + ").";
                facts.add(backRoute);

            }
            writeToFile();
            System.out.println("Route added succesfully");
        }
    }

    public void addPlace(String place) throws IOException {
        readFile();
        String newPlace = "lugar([" + place + "]).";
        boolean exists = check(newPlace);
        if (exists)
            System.out.println("Place Already exists");

        else {
            System.out.println("Adding the new Place");
            facts.addFirst(newPlace);
            writeToFile();
            System.out.println("Place added succesfully");
        }
    }

    public boolean check(String fact) {
        for (String existingFact : facts) {
            if (fact.equals(existingFact))
                return true;
        }
        return false;
    }

    public void writeToFile() throws IOException {

        PrintWriter cleaner = new PrintWriter("/home/eduardo/Documents/WazeLog/PrologFiles/BaseConocimientos.pl");
        cleaner.print("");
        cleaner.close();

        BufferedWriter writer = new BufferedWriter(new FileWriter("/home/eduardo/Documents/WazeLog/PrologFiles/BaseConocimientos.pl", true));
        for (String elem : facts) {
            writer.write(elem);
            writer.newLine();
        }
        writer.close();
    }
}