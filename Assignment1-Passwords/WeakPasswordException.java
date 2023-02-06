
@SuppressWarnings("serial")
public class WeakPasswordException extends Exception{
	public WeakPasswordException() {
		super("Password invalid length");
	}
	
	public WeakPasswordException(String message) {
		super(message);
	}
}
