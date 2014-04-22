package adapter;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;

import util.Util;
import exceptions.MyException;
import models.Automobile;

/**
 * 
 * @author shwethadurgashankar
 *
 */
public abstract class ProxyAutomobile {
	protected LinkedHashMap<String, Automobile> autoCar ; 
	private String writeContent;
	
	/**
	 * Constructor
	 * @throws MyException 
	 */
	public ProxyAutomobile() throws MyException {
		super();
	}
	
	public abstract void setValues(String model, String color, String transmission, 
			String traction, String airBags, String moonRoof);
			
	
	public abstract void createAuto();

	/**
	 * Print output to file
	 * @param modelName
	 * @throws Exception 
	 */
	public synchronized void printAuto(String modelName) {
		Automobile car = autoCar.get(modelName);		
		writeContent = new String();
		float cost = 0;
		cost += car.getBasePrice();
		
		for(int i=0; i<5; i++) {
			try {
				writeContent += car.getPropertyName(i)+" "+car.getOptionName(i)+": "+car.getPrice(car.getOptionName(i))+"\n";
			} catch (Exception e) {
				writeContent += "\nCREATING OUTPUT EXCEPTION1:  "+e.toString() + "\n";
			}
		}
		
		try {
		writeContent += "Total Cost: "+ car.getTotalPrice();
		} catch(Exception e) {
			System.out.println(e);
		}
		
		System.out.println(writeContent);
	}
	
	/**
	 * Replace entire LHM
	 * @param autoCar
	 */
	protected void setAutoCar(LinkedHashMap<String, Automobile> autoCar) {
		this.autoCar = autoCar;
		System.out.println("Setted size = "+autoCar.size());
	}
	
	/**
	 * Return entire LHM
	 * @return
	 */
	protected LinkedHashMap<String, Automobile> getAutoCar() {
		return autoCar;
	}
	
}
