
/**
 * road object which utilizes the town class
 * @author Hana Fatima Shaikh
 */
public class Road implements Comparable<Road>{
	private Town source;
	private Town destination;
	private int weight;
	private String name;
	
	/**
	 * constructs a road object with parameters
	 * @param source is the starting town
	 * @param destination is the end town
	 * @param degrees is the length of the road in miles
	 * @param name is the name of the road
	 */
	public Road(Town source, Town destination, int degrees, String name) {
		this.source = new Town(source);
		this.destination = new Town(destination);
		this.weight = degrees;
		this.name = name;
	}
	
	/**
	 * constructs a road with default weight of 1
	 * @param source is the starting town
	 * @param destination is the end town
	 * @param name is the name of the town
	 */
	public Road(Town source, Town destination, String name) {
		this.source = new Town(source);
		this.destination = new Town(destination);
		weight = 1;
		this.name = name;
	}
	
	/**
	 * checks to see if parameter is reachable by the road object
	 * @param town is the town to be checked
	 * @return if the source or destination town is equal to the parameter
	 */
	public boolean contains(Town town) {
		if(town == null) {
			return false;
		} 
		
		return source.equals(town) || destination.equals(town);
	}
	
	/**
	 * create a string representation of the road
	 * @return a string containing the source, destination, weight, and name of road
	 */
	@Override
	public String toString() {
		return source.toString() + " via " + name + " to " + destination.toString() + weight + " mi";
	}
	
	/**
	 * retrieves the name of the road
	 * @return the name attribute of the road
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * retrieves the destination town
	 * @return the destination attribute
	 */
	public Town getDestination() {
		return destination;
	}
	
	/**
	 * retrieves the source town
	 * @return the source attribute
	 */
	public Town getSource() {
		return source;
	}
	
	/**
	 * compares two road objects
	 * @param o is the other road
	 * @return if the names are equal then return 0, otherwise the difference between the weight
	 */
	public int compareTo(Road o) {
		if(name.equals(o.getName())) {
			return 0;
		}
		
		return weight - o.getWeight();
	}
	
	/**
	 * retrieves the weight
	 * @return the weight of the road
	 */
	public int getWeight() {
		return weight;
	}
	
	/**
	 * checks whether two road objects are equal
	 * @param r is the other road to be checked
	 * @return true if they are equal, else false 
	 */
	@Override
	public boolean equals(Object r) {		
		boolean same = false, inverted = false;
		same = source.equals(((Road) r).getSource()) && destination.equals(((Road) r).getDestination());
		inverted = destination.equals(((Road) r).getSource()) && source.equals(((Road) r).getDestination());
		if(same || inverted) {
			if(compareTo((Road) r) == 0) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * sets the name of the road
	 * @param newName is the name to be set to
	 */
	public void setName(String newName) {
		name = newName;
	}
	
	/**
	 * sets the weight of the road
	 * @param newWeight is the weight to be set to
	 */
	public void setWeight(int newWeight) {
		weight = newWeight;
	}
}
