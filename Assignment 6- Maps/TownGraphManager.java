import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * utilizes the Graph class to manage towns and roads
 * @author Hana Fatima Shaikh
 */
public class TownGraphManager implements TownGraphManagerInterface {
	private Graph<Town, Road> graph;
	
	/**
	 * constructor that initializes a graph
	 */
	public TownGraphManager() {
		graph = new Graph<>();
	}
	
	/**
	 * adds a road to the graph
	 * @param town1 is the source town
	 * @param town2 is the destination town
	 * @param weight is the weight of the road
	 * @param roadName is the name of the road
	 * @return whether the addition was successful
	 */
	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		Road r = (Road) graph.addEdge(getTown(town1), getTown(town2), weight, roadName);
		return r != null;
	}

	/**
	 * retrieves a road based on source and destination towns
	 * @param town1 is the source town
	 * @param town2 is the destination town
	 * @return the name of the road if found, else null
	 */
	@Override
	public String getRoad(String town1, String town2) {
		Road find = (Road) graph.getEdge(getTown(town1), getTown(town2));
		
		for(Road r : graph.edgesOf(getTown(town1))) {
			if(r.getSource().equals(getTown(town2)) || r.getDestination().equals(getTown(town2))){
				return find.getName();
			}
		}
		
		return null;
	}

	/**
	 * adds a town to the graph
	 * @return whether the addition was successful
	 */
	@Override
	public boolean addTown(String v) {
		return graph.addVertex(new Town(v));
	}

	/**
	 * retrieves a town based on name
	 * @param name is the name of the town to be found
	 * @return the town if found, else null
	 */
	@Override
	public Town getTown(String name) {
		for(Town t : graph.vertexSet()) {
			if(name.equals(t.getName())) {
				return t;
			}
		}
		return null;
	}

	/**
	 * checks whether a town is in the graph
	 * @param v is the name of the town to be found
	 * @return whether the town is present in the graph
	 */
	@Override
	public boolean containsTown(String v) {
		return graph.containsVertex(new Town(v));
	}

	/**
	 * checks whether there is connected between two towns
	 * @param town1 is a town the road should be connected to 
	 * @param town2 is a town the road should be connected to
	 * @return whether a road that connects to the two parameter towns exists 
	 */
	@Override
	public boolean containsRoadConnection(String town1, String town2) {
		return graph.containsEdge(getTown(town1), getTown(town2));
	}

	/**
	 * retrieves all the roads in the graph 
	 * @return an arraylist containing all the roads in string form
	 */
	@Override
	public ArrayList<String> allRoads() {
		ArrayList<String> allroads = new ArrayList<>();
		for(Road r : graph.edgeSet()) {
			allroads.add(r.getName());
		}
		
		Collections.sort(allroads);
		return allroads;
	}
	
	/**
	 * deletes a road connection between two towns
	 * @param town1 is one of the towns the road is connected to 
	 * @param town2 is one of the towns the road is connected to 
	 * @param road is the name of the road to be deleted
	 * @return whether the deletion was successful
	 */
	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		if(graph.containsEdge(getTown(town1), getTown(town2))) {
			Road hold = (Road) graph.getEdge(getTown(town1), getTown(town2));
			Road removed = (Road) graph.removeEdge(getTown(town1), getTown(town2), hold.getWeight(), road);
			
			if(removed.equals(hold)) {
				return true;
			}
		}
		
		
		return false;
	}

	/**
	 * deletes a town based on name
	 * @param the name of the vertex to be deleted
	 * @return whether the deletion was successful
	 */
	@Override
	public boolean deleteTown(String v) {
		return graph.removeVertex(new Town(v));
	}

	/**
	 * retrives all the towns in a graph
	 * @return an arraylist of all the towns in a graph
	 */
	@Override
	public ArrayList<String> allTowns() {
		ArrayList<String> alltowns = new ArrayList<>();
		
		for(Town t : graph.vertexSet()) {
			alltowns.add(t.getName());
		}
		
		Collections.sort(alltowns);
		return alltowns;
	}

	/**
	 * gets the shortest path between two towns
	 * @param town1 is one of the towns to find a path between
	 * @param town2 is one of the towns to find a path between
	 * @return an arraylist of the string representations of the roads making up the shortest connection between the two parameter towns
	 */
	@Override
	public ArrayList<String> getPath(String town1, String town2) {
		ArrayList<String> path = graph.shortestPath(getTown(town1), getTown(town2));
		
		return path;
	}
	
	/**
	 * populates the graph
	 * @param input the file with the information
	 * @throws FileNotFoundException
	 */
	public void populateTownGraph(File input) throws FileNotFoundException{
		ArrayList<String> list = new ArrayList<>();
		
		if (!input.exists())
			throw new FileNotFoundException();
		
		Scanner sc = new Scanner(input);
		
		while (sc.hasNextLine()) {
			list.add(sc.nextLine());
		}
		
		for (String line : list) {
			String[] split = line.split(";");
			int delim = split[0].indexOf(",");
			String roadName = split[0].substring(0,delim);
			String weight = split[0].substring(delim+1,split[0].length());
			String source = split[1];
			String destination = split[2];
			
			addTown(source);
			addTown(destination);
			
			addRoad(source, destination, Integer.parseInt(weight), roadName);
		}
		
		sc.close();
	}

}
