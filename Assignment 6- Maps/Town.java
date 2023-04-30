
/**
 * town object with string name attribute
 * @author Hana Fatima Shaikh
 */
public class Town implements Comparable<Town>{
	private String name;
	
	/**
	 * constructs a town object with a name
	 * @param name is the name of the town object
	 */
	public Town(String name) {
		this.name = name;
	}
	
	/**
	 * constructs a town object based on another town object
	 * @param templateTown is the object to be copied
	 */
	public Town(Town templateTown) {
		name = templateTown.getName();
	}

	/**
	 * retrieves the name attribute of town
	 * @return the name attribute of the town
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * compares two town objects based on name 
	 * @param o is the other town object
	 * @return 0 if the names are equal, -1 if not
	 */
	public int compareTo(Town o) {
		if(name.equals(o.getName())){
			return 0;
		}
		return -1;
	}
	
	/**
	 * the name attribute of the town
	 * @return the name attribute
	 */
	@Override
	public String toString() {
		return name;
	}
	
	/**
	 * creates a hashcode of the town
	 * @return the hashcode based on the name attribute
	 */
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	/**
	 * whether the town equals another town
	 * @return true if towns equal based on name, else false 
	 */
	@Override
	public boolean equals(Object obj) {
		return this.name.equals(((Town) obj).getName());				
	}

}
