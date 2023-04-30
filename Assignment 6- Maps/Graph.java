import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * utilizes the town and road classes to create a graph
 * @author Hana Fatima Shaikh
 */
public class Graph<V, E> implements GraphInterface<V,E>{
	
	private int adjacencyMatrix[][];
	private HashMap<Town, Integer> hashmap = new HashMap<>();
	private Set<Town> vertex_set = new HashSet<>();
	private Set<E> edge_set = new HashSet<>();
	private int numVertex;

	private Map<Town, Integer> miles;
	private Map<Town, Town> previous;
	private Set<Town> notVisited;
	private Set<Town> visited;
	Set<Town> adjacentTowns;
	
	/**
	 * constructor that initializes attributes
	 */
	public Graph() {
		adjacencyMatrix = new int[20][20];
		numVertex = 0;
		miles = new HashMap<>();
		previous = new HashMap<>();
		notVisited = new HashSet<>();
		visited = new HashSet<>();
	}
	
	/**
	 * retrieves an edge in a graph  based on source and destination verticies
	 * @param sourceVertex is the source town
	 * @param destinationVertex is the destination town
	 * @return Road if present in graph, null if not present
	 */
	public Object getEdge(Object sourceVertex, Object destinationVertex) {
		if(sourceVertex == null || destinationVertex == null) {
			return null;
		}
		
		for(E a: edge_set) {
			if(a.equals(new Road((Town)sourceVertex, (Town)destinationVertex, ((Road) a).getName()))) {
				return a;
			}
		}
		
		return null;
	}

	/**
	 * adds a road object to the graph
	 * @param sourceVertex is the source town
	 * @param destinationVertex is the destination town
	 * @param weight is the weight of the new road
	 * @param description is the ane of the new road
	 * @return the newly added road
	 */
	@SuppressWarnings("unchecked")
	public Object addEdge(Object sourceVertex, Object destinationVertex, int weight, String description) throws NullPointerException, IllegalArgumentException{
		if(sourceVertex == null || destinationVertex == null) {
			throw new NullPointerException();
		}
		
		boolean sourceB = false, destinationB = false;

		if(containsVertex(sourceVertex)) {
			sourceB = true;
		}
		if(containsVertex(destinationVertex)) {
			destinationB = true;
		}
		
		if(!(sourceB && destinationB)) {
			throw new IllegalArgumentException();
		}
		
		Road a = new Road((Town)sourceVertex, (Town)destinationVertex, weight, description);
		
		edge_set.add((E) a);
		
		int source = hashmap.get(sourceVertex);
		int destination = hashmap.get(destinationVertex);
		
		if(adjacencyMatrix[source][destination] == 0 && adjacencyMatrix[destination][source] == 0) {
			adjacencyMatrix[source][destination] = weight;
			adjacencyMatrix[destination][source] = weight;
		}
		
		return a;	
	}

	/**
	 * adds a town object to the graph
	 * @param v is the vertex to be added
	 * @return whether the vertex was added succesfully 
	 */
	public boolean addVertex(Object v) {
		
		Town add = new Town((Town)v);

		for(Town vertex: vertex_set) {
			if(vertex.equals(add)) {
				return false;
			}
		}
		
		vertex_set.add((Town)add);
		hashmap.put((Town) add, numVertex);
		numVertex++;
		return true;
	}
	
	/**
	 * checks whether an edge is present in the graph
	 * @param sourceVertex is the source town
	 * @param destintationVertex is the destination town
	 * @return whether the edge is present in the graph
	 */
	public boolean containsEdge(Object sourceVertex, Object destinationVertex) {
		Road a = new Road((Town) sourceVertex, (Town) destinationVertex, "test");
		for(E r: edge_set) {
			a.setName(((Road) r).getName());
			if(r.equals(a)) {
				return true;
			}
		}
		
		return false;
	}

	
	/**
	 * checks whether a town is present in the graph
	 * @param v is the town to be checked
	 * @return whether the town is present in the graph
	 */
	public boolean containsVertex(Object v) {
		for(Town t: vertex_set) {
			if(t.equals((Town) v)){
				return true;
			}
		}
		
		return false;
		
	}

	/**
	 * retrieves all the edges in the graph
	 * @return a set of all the roads in the graph
	 */
	public Set<E> edgeSet() {
		return edge_set;
	}

	/**
	 * retrieves all the edges of a particular vertex
	 * @param vertex is the town to find the edges of 
	 * @return a set of the edges of the parameter vertex
	 */
	@Override
	public Set<E> edgesOf(Object vertex) throws IllegalArgumentException, NullPointerException{
		if(vertex == null) {
			throw new NullPointerException();
		}
		
		if(!containsVertex(vertex)) {
			throw new IllegalArgumentException();
		}
		
		Set<E> edgesOfV = new HashSet<>();
		
		for(E e: edge_set) {
			if(((Road) e).contains((Town) vertex)) {
				edgesOfV.add(e);
			}
		}
		
		return edgesOfV;
	}

	/**
	 * removes an edge from the graph
	 * @param sourceVertex is the source town of the edge to be removed
	 * @param destinationVertex is the destination of the edge to be removed
	 * @param weight is the weight of the edge to be removed
	 * @param description is the name of the edge to be removed
	 * @return if the edge was in the graph return the edge, else return null
	 */
	@Override
	public Object removeEdge(Object sourceVertex, Object destinationVertex, int weight, String description) {
		Road edge = new Road((Town) sourceVertex, (Town) destinationVertex, weight, description);
		for(E r: edge_set) {
			if(weight < 1) {
				edge.setWeight(((Road) r).getWeight());
			}
			if(description == null) {
				edge.setName(((Town) r).getName());
			}
			
			if(edge.equals(r)){
				int source = hashmap.get((Town)sourceVertex);
				int destination = hashmap.get((Town)destinationVertex);
				adjacencyMatrix[source][destination] = 0;
				adjacencyMatrix[destination][source] = 0;
				edge_set.remove(r);
				return r;
			}
		}
		
		return null;		
	}

	/**
	 * removes a town from the graph
	 * @param v is the town to be removed
	 * @return whether the removal was successful
	 */
	@Override
	public boolean removeVertex(Object v) {
		if(containsVertex((Town) v)) {
			Set<E> vertexRoads = edgesOf(v);
			
			for(E e: vertexRoads) {
				removeEdge(((Road) e).getSource(), ((Road) e).getDestination(), ((Road) e).getWeight(), ((Road) e).getName());
			}
			vertex_set.remove(v);
			hashmap.remove(v);
			return true;
		}
		
		return false;
	}

	/**
	 * retrieves all the vertices in a graph 
	 * @return the set of all the vertices in the graph
	 */
	@Override
	public Set<V> vertexSet() {
		return (Set<V>) vertex_set;
	}
	
	/**
	 * finds the shortest path between two towns
	 * @param sourceVertex is the start of the path
	 * @param destintaionVertex is the end of the path
	 * @return an arraylist of the path as strings
	 */
	@Override
	public ArrayList<String> shortestPath(Object sourceVertex, Object destinationVertex) {
		ArrayList<String> path = new ArrayList<>();
		
		dijkstraShortestPath(sourceVertex);
		
		Town prev = (Town) destinationVertex;
		
		while(prev != null) {

			Town position = prev;
			prev = previous.get(prev);

			Road edge = (Road) getEdge(position, prev);
			if(prev != null) {
				path.add(prev.getName() + " via "+ edge.getName() + " to "+ position.getName() + " "+ edge.getWeight() + " mi");
			}
		}
		
		Collections.reverse(path);
		
		return path;
	}

	/**
	 * finds the shortest path to all the other vertices from the sourceVertex based on dijkstras algorithm
	 * @param sourceVertex is the vertex to find all the paths from
	 */
	@Override
	public void dijkstraShortestPath(Object sourceVertex) {
		for(Town t: vertex_set) {
			miles.put(t, Integer.MAX_VALUE);
			previous.put(t, null);
			notVisited.add(t);
		}
		
		miles.put((Town) sourceVertex,  0);
				
		while(notVisited.isEmpty() == false) {
			Town closest = getNearestNotVisited();

			notVisited.remove(closest);
			
			Set<Town> adjacentVerticies = getNotVisitedAdjacent(closest);
			
			for(Town t : adjacentVerticies) {
				if(t != null) {
					int netWeight = miles.get(closest) + ((Road) getEdge(closest, t)).getWeight();
					
					if(netWeight < miles.get(t)) {
						miles.put(t, netWeight);
						previous.put(t, closest);
					}
				}
			}
		}
	}

	/**
	 * finds the closest unvisited vertex 
	 * @return the town that is closest from the currently visited 
	 */
	public Town getNearestNotVisited() {
		Town closest = null;
		int smallest = Integer.MAX_VALUE;
		
		for(Town t: notVisited) {
			if(miles.get(t) <= smallest) {
				smallest = miles.get(t);
				closest = t;
			}
		}
		
		return closest;
	}

	/**
	 * get the vertices that haven't been visited that are connected to the parameter town 
	 * @param t is the town to find the unvisited neighbors of
	 * @return a set of all the unvisited neighbors of the parameter town
	 */
	public Set<Town> getNotVisitedAdjacent(Town t){
		adjacentTowns = new HashSet<>();
		
		for(E road : edgesOf(t)) {
			Road r = (Road) road;
			Town adjacent;
			
			if(r.getSource().equals(t)) {
				adjacent = r.getDestination();
			}
			else if(r.getDestination().equals(t)) {
				adjacent = r.getSource();
			}
			else {
				break;
			}
			
			if(notVisited.contains(adjacent)) {
				if(!visited.contains(adjacent)) {
					adjacentTowns.add(adjacent);
				}
			}
		}
			
		return adjacentTowns;
	}
	
}
