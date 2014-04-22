package models;

import java.io.Serializable;

/**
 * 
 * @author shwethadurgashankar
 *
 */
public class OptionSet implements Serializable{
	private Option[] opt;
	private String propertyName;
	
	/**
	 * Constructor
	 * @param n
	 * @param size
	 */
	protected OptionSet(String n) {
		propertyName = n;
	}
	
	
	public void createColor () {
		opt = new Option[10];
		opt[0] = new Option("Fort Knox Gold Clearcoat Metallic");
		opt[1] = new Option("Liquid Grey Clearcoat Metallic");
		opt[2] = new Option("Infra-Red Clearcoat");
		opt[3] = new Option("Grabber Green Clearcoat Metallic");
		opt[4] = new Option("Sangria Red Clearcoat Metallic");
		opt[5] = new Option("French Blue Clearcoat Metallic");
		opt[6] = new Option("Twilight Blue Clearcoat Metallic");
		opt[7] = new Option("CD Silver Clearcoat Metallic");
		opt[8] = new Option("Pitch Black Clearcoat");
		opt[9] = new Option("Cloud 9 White Clearcoat");
	}
	
	public void createTransmission() {
		opt = new Option[2];
		opt[0] = new Option("Automatic");
		opt[1] = new Option("Manual");
	}
	
	public void createBreaks() {
		opt = new Option[3];
		opt[0] = new Option("Standard");
		opt[1] = new Option("ABS");
		opt[2] = new Option("ABS with Advance Trac");
	}
	
	public void createAirBags() {
		opt = new Option[2];
		opt[0] = new Option("Present");
		opt[1] = new Option("Not Present");
	}
	
	public void createMoonRoof() {
		opt = new Option[2];
		opt[0] = new Option("Present");
		opt[1] = new Option("Not Present");
	}

	public void setOpt(String value) {
		for(int i=0; i<opt.length; i++) {
			if(opt[i].getOptionName().equalsIgnoreCase(value)) {
				opt[i].setFlag(true);
				return;
			}
		}
	}
	
	public void resetOptions() {
		for(int i=0; i<opt.length; i++) {
			opt[i].setFlag(false);
		}
	}
	

	/**
	 * Returns name of the property
	 * @return name
	 */
	public String getPropertyName() {
		return propertyName;
	}

	/**
	 * Returns option for which flag is set to true
	 * @return
	 */
	public String getOption() {
		for(int i=0; i<opt.length; i++) {
			if(opt[i].isFlag())
				return opt[i].getOptionName();
		}
		return null;
	}
	
	public String[] getAllOptions() {
		int size = opt.length;
		String[] options = new String[size];
		for(int i=0; i<size; i++) {
			options[i] = opt[i].getOptionName();
		}
		return options;
	}
	
	
	private class Option implements Serializable{
		private String optionName;
		private boolean flag;
		
		protected Option(String name) {
			this.optionName = name;
			flag = false;
		}

		/**
		 * Returns name of the option
		 * @return name
		 */
		protected String getOptionName() {
			return optionName;
		}

		/**
		 * Sets name of the option
		 * @param name
		 */
		protected void setOptionName(String name) {
			this.optionName = name;
		}

		protected void setFlag(boolean flag) {
			this.flag = flag;
		}
		
		protected boolean isFlag() {
			return flag;
		}
	}
	
}
