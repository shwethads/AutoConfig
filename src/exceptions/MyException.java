package exceptions;
/**
 * 
 * @author shwethadurgashankar
 *
 */
@SuppressWarnings("serial")
public class MyException extends Exception{
	private String message;
	
	/**
	 * Default constructor
	 */
	public MyException() {
        super();
    }
	
	/**
	 * Parameterized constructor
	 * @param message
	 */
	public MyException(String message){
		super(message);
		this.message = message;
	}
	
	/**
	 * Getter method for message
	 * @return message
	 */
	@Override
	public String getMessage() {
		return message;
	}

	/**
	 * Setter method for message
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}