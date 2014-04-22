package drivers;

import exceptions.MyException;
import adapter.BuildAuto;
import adapter.ProxyAutomobile;

public class Test {
	
	public static void main(String[] args){
		try {
			BuildAuto ba = new BuildAuto();
			ba.createAuto();
			ba.setValues("Model 1", "Infra-Red Clearcoat", "Automatic", "Standard", "Present", "Present");
			ba.printAuto("Model 1");
		} catch (MyException e) {
			e.printStackTrace();
		}
	}
}
