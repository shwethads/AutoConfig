package adapter;

import java.util.LinkedHashMap;

import util.Util;
import models.Automobile;
import exceptions.MyException;

/**
 * 
 * @author shwethadurgashankar
 *
 */
public class BuildAuto extends ProxyAutomobile implements CreateAuto, UpdateAuto {
	private Util util;

	public BuildAuto() throws MyException {
		util = new Util();
	}
	
	/**
	 * Build a car based on values in the file
	 * @param fileName
	 */
	public synchronized void createAuto() {
		LinkedHashMap<String, Automobile> autoCar = new LinkedHashMap<String, Automobile>();
		try {
			autoCar.put("Model 1", util.createAuto("Model 1"));
			autoCar.put("Model 2", util.createAuto("Model 2"));
			autoCar.put("Model 3", util.createAuto("Model 3"));
			autoCar.put("Model 4", util.createAuto("Model 4"));
		} catch(Exception e) {
			System.out.println(e);
		}
		super.setAutoCar(autoCar);
	}
	
	/**
	 * Sets flags for selected values
	 */
	public synchronized void setValues(String model, String color, String transmission, 
			String breaks, String airBags, String moonRoof) {
		LinkedHashMap<String, Automobile> autoCar= super.getAutoCar();
			Automobile car = autoCar.get(model);
			util.setValues(car, color, transmission, breaks, airBags, moonRoof);
			autoCar.remove(model);
			autoCar.put(model, car);
			super.setAutoCar(autoCar);
		}
	
	
	/**
	 * Returns car based on model
	 * @param model
	 * @return
	 */
	public synchronized Automobile getCar(String model) {
		return autoCar.get(model);
	}
	
	/**
	 * Returns entire LHM
	 * @return
	 */
	public synchronized LinkedHashMap<String, Automobile> getAutoCar() {
		return super.getAutoCar();
	}
	
	/**
	 * Calculates total price of the car
	 * @param car
	 * @return
	 * @throws MyException
	 */
	public synchronized float getTotalPrice(Automobile car) throws MyException {
		return util.getTotalPrice(car);
	}

}
