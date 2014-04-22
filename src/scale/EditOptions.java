package scale;

import exceptions.MyException;
import models.Automobile;

public class EditOptions {
	
	public EditOptions() {}
	
	/**
	 * Edit properties using threads
	 * @param al
	 * @param optionSetName
	 * @param newName
	 * @throws Exception
	 */
	public void edit(Automobile al, String optionSetName, String newName) throws Exception {
		Thread t = new EditThread(optionSetName, newName, al);
		t.run();
	}
	
	/**
	 * Method to demonstrate thread synchronization
	 * @param al
	 * @throws Exception
	 */
	public void syncDemo(Automobile al) throws Exception{
		Thread t1 = new EditThread("Power Moonroof", "None", al);
		Thread t2 = new EditThread("Power Moonroof", "Present", al);
		t1.run();
		t2.run();
		t1.sleep(10);
	}
}


/**
 * Thread class
 * @author shwethadurgashankar
 *
 */
class EditThread extends Thread{
	private String optionSetName, newName;
	Automobile al;
	
	public EditThread (String optionSetName, String newName, Automobile al) throws Exception {
		this.optionSetName = optionSetName;
		this.newName = newName;
		this.al = al;
		
	}
	
//	public void run(){
//		try {
//			al.updateProperty(optionSetName, newName);
//			System.out.println("Updated to "+newName);
//		} catch (MyException e) {
//			e.printStackTrace();
//		}
//	}
}
