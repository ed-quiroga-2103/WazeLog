package Communication;

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
        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\alekm\\Desktop\\Lenguajes\\New folder\\Written_Test.pl"));

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
	
	public void addRoute(String from, String to) throws IOException {
		String newRoute = "test("+ from + "," + to + ").";
		boolean exists = checkRoute(newRoute);
		if (exists)
			System.out.println("Route Already exists");
		
		else {
			System.out.println("Adding the new Route");
			facts.add(newRoute);
			writeRoute();
			System.out.println("Route added succesfully");
		}
	}
	
	public boolean checkRoute(String route) {
		for (String fact: facts) {
			if (fact.equals(route))
				return true;
		}
		return false;
	}
	public void writeRoute() throws IOException {
		
		PrintWriter cleaner = new PrintWriter("C:\\Users\\alekm\\Desktop\\Lenguajes\\New folder\\Written_Test.pl");
		cleaner.print("");
		cleaner.close();
			     
	    BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\alekm\\Desktop\\Lenguajes\\New folder\\Written_Test.pl",true));
	    for (String elem :facts) {
	    	writer.write(elem);
	    	writer.newLine();
	    }
	    writer.close();
	}
}