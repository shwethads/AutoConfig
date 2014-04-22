package dbops;

import java.sql.SQLException;

import exceptions.MyException;

public class RDBMSTest {	
	  
	public static void main(String[] args) throws Exception {
		DBOperations dbo = new DBOperations();
		dbo.createLHM();
		dbo.readFile("./src/dbops/sample1.txt");
		dbo.readFile("./src/dbops/sample2.txt");
		dbo.readFile("./src/dbops/sample3.txt");
		dbo.readFile("./src/dbops/sample4.txt");
		dbo.editVal("Model 1", "color","French Blue Clearcoat Metallic");
		dbo.removeCar("Model 1");
		dbo.serializeOutput("./src/dbops/outputSer.ser");
	}

}
