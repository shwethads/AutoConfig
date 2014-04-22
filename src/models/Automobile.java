package models;

import java.io.Serializable;

import models.OptionSet;
import exceptions.MyException;

/**
 * 
 * @author shwethadurgashankar
 *
 */

public class Automobile implements Serializable {
	private String model;
	private OptionSet[] opset;
	private float basePrice;

	/**
	 * Default Constructor --- blank!
	 */
	public Automobile() {};

	/**
	 * Parameterized Constructor
	 * @param size
	 * @param name
	 * @param basePrice
	 */
	public Automobile(int size, String model, int basePrice) {
		//System.out.println("Auto constructor");
		opset = new OptionSet[5];
		this.model = model;
		this.basePrice = basePrice;
	}

	public void createOpset() {
		//System.out.println("Creating opset...");
		opset[0] = new OptionSet("Color");
		opset[0].createColor();
		opset[1] = new OptionSet("Transmission");
		opset[1].createTransmission();
		opset[2] = new OptionSet("Brakes/Traction Control");
		opset[2].createBreaks();
		opset[3] = new OptionSet("Side Impact Air Bags");
		opset[3].createAirBags();
		opset[4] = new OptionSet("Power Moonroof");
		opset[4].createMoonRoof();
	}

	public void createOptions(String color, String transmission, 
			String breaks, String airBags, String moonRoof) {
		opset[0].setOpt(color);
		opset[1].setOpt(transmission);
		opset[2].setOpt(breaks);
		opset[3].setOpt(airBags);
		opset[4].setOpt(moonRoof);
	}
	
	public String getModel() {
		return model;
	}

	public String getOption(String optionName) {
		for(int i=0; i<opset.length; i++) {
			if(opset[i].getPropertyName().equalsIgnoreCase(optionName))
				return opset[i].getOption();
		}
		return null;
	}
	
	public String[] getAllOptions(int size, int index) {
		String[] options = new String[size];
		OptionSet property = opset[index];
		options = property.getAllOptions();
		return options;
	}
	

		/**
		 * Calculates total price for all properties
		 * @return
		 * @throws MyException 
		 */
		public synchronized float getTotalPrice() throws MyException {
			float price = basePrice;
			for(int i=0; i<opset.length; i++) {
				String property = opset[i].getPropertyName();
				String value = opset[i].getOption();
				price += checkPrice(property, value);
			}
			return price;
		}
		

		public float getPrice(String property) throws MyException {
			String value = new String();
			for(int i=0; i<opset.length; i++) {
				if(opset[i].getPropertyName().equalsIgnoreCase(property)) {
					value = opset[i].getOption();
					return checkPrice(property, value);
				}
			}
			return 0;
		}
		
		public float getBasePrice() {
			return basePrice;
		}


	public synchronized float checkPrice(String property, String value) throws MyException {
		if(property.equalsIgnoreCase("Color"))
			return 0;
		else if (property.equalsIgnoreCase("Transmission")) {
			if(value.equalsIgnoreCase("Standard"))
				return 0;
			else if(value.equalsIgnoreCase("Automatic"))
				return -815;
			else
				throw new MyException("Transmission not found for price");
		}
		else if (property.equalsIgnoreCase("Brakes/Traction Control")) {
			if(value.equalsIgnoreCase("Standard"))
				return 0;
			else if(value.equalsIgnoreCase("ABS"))
				return 400;
			else if(value.equalsIgnoreCase("ABS with Advance Trac"))
				return 1625;
			else
				throw new MyException("Brakes not found for price");
		}
		else if (property.equalsIgnoreCase("Side Impact Air Bags")) {
			if(value.equalsIgnoreCase("None"))
				return 0;
			else if(value.equalsIgnoreCase("Present"))
				return 350;
			else
				throw new MyException("Air bags not found for price");
		}
		else if (property.equalsIgnoreCase("Power Moonroof")) {
			if(value.equalsIgnoreCase("None"))
				return 0;
			else if(value.equalsIgnoreCase("Present"))
				return 595;
			throw new MyException("Moon roof not found for price");
		}
		throw new MyException("Option not found for price");
	}
	
	
	public void resetProperties() {
		opset = new OptionSet[5];
		createOpset();
	}

	public void edit(String property, String value) {
		for(int i=0; i<5; i++) {
			if(opset[i].getPropertyName().equalsIgnoreCase(property)) {
				opset[i].resetOptions();
				opset[i].setOpt(value);
			}
		}
	}

	/**
	 * Returns option name for a property
	 * @param index
	 * @return
	 */
	public synchronized String getOptionName(int index) {
		return opset[index].getOption();
	}
	
	public synchronized String getPropertyName(int index) {
		return opset[index].getPropertyName();
	}

}
