package adapter;

/**
 * 
 * @author shwethadurgashankar
 *
 */
public interface CreateAuto {
	/**
	 * Build a car based on file inputs
	 * @param fileName
	 */
	public void createAuto();
	
	/**
	 * Print output to a file
	 * @param modelName
	 */
	public void printAuto(String modelName);

}
