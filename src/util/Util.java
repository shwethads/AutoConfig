package util;

import exceptions.MyException;
import models.Automobile;

/**
 * 
 * @author shwethadurgashankar
 *
 */
public class Util {
	private final int BASE_PRICE = 18445, SIZE = 5;
	/**
	 * Constructor
	 */
	public Util() {};
	
	public void receiveFile() {
		
	}
	
	public Automobile createAuto(String model) throws MyException {
		//System.out.println("Util create Auto...");
		Automobile car = new Automobile(SIZE, model, BASE_PRICE);
		car.createOpset();
		return car;
	}
	
	public Automobile setValues(Automobile car, String color, String transmission, String breaks, String airBags, String moonRoof) {
		car.createOptions(color, transmission, breaks, airBags, moonRoof);
		return car;
	}
	
	public float getPrice(Automobile car, String property) throws MyException {
		return car.getPrice(property);
	}
	
	public float getTotalPrice(Automobile car) throws MyException {
		return car.getTotalPrice();
	}
	
	public String[] getColorOptions(Automobile car) {
		return car.getAllOptions(10, 0);
	}
	
	public String[] getTransmissionOptions(Automobile car) {
		return car.getAllOptions(2, 1);
	}
	
	public String[] getBreakOptions(Automobile car) {
		return car.getAllOptions(3, 2);
	}
	
	public String[] getAirBagOptions(Automobile car) {
		return car.getAllOptions(2, 3);
	}
	
	public String[] getMoonRoofOptions(Automobile car) {
		return car.getAllOptions(2, 4);
	}
}
