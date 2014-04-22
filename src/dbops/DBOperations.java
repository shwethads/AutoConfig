package dbops;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;

import exceptions.MyException;
import util.Util;
import models.Automobile;

public class DBOperations {
	protected LinkedHashMap<String, Automobile> autoCarDB ;
	private Util util;
	private ResultSet rs = null;
	private Connection con = null;
	private Statement stmt = null;
	private String sqlStmt = null;
	private String output;
	
	/**
	 * Deletes old table
	 * Makes new table
	 */
	public DBOperations() {
		util = new Util();
		try{
			Class.forName("com.mysql.jdbc.Driver");	
			con = DriverManager.getConnection("jdbc:mysql://localhost/auto");
			stmt = con.createStatement();
			sqlStmt = "drop table lhm";
			stmt.executeUpdate(sqlStmt);
			sqlStmt = "create table lhm(model VARCHAR(20) PRIMARY KEY, color VARCHAR(50), "
					+ "transmission VARCHAR(20), breaks VARCHAR(30), airbags VARCHAR(15), moonroof VARCHAR(15));";
			stmt.executeUpdate(sqlStmt);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates a new LHM
	 */
	public void createLHM() {
		autoCarDB = new LinkedHashMap<String, Automobile>();
		try {
			autoCarDB.put("Model 1", util.createAuto("Model 1"));
			autoCarDB.put("Model 2", util.createAuto("Model 2"));
			autoCarDB.put("Model 3", util.createAuto("Model 3"));
			autoCarDB.put("Model 4", util.createAuto("Model 4"));
		} catch(Exception e) {
			System.out.println(e);
			output += e;
		}
	}
	
	public void readFile(String fileName) throws SQLException {
		File file = new File(fileName);
		
		Automobile car = new Automobile();		
		String readContent = new String();
		int ch;
		StringBuffer buff = new StringBuffer("");
		String[] tokens = new String[2];
		String model = new String();
		String color = new String();
		String trans = new String();
		String brake = new String();
		String airBags = new String();
		String moonRoof = new String();
		
		try {
			FileInputStream fin = new FileInputStream(file);
			
			while((ch = fin.read()) != -1) {
				if((char)ch == '\n'){
					String str = buff.toString();
					tokens = str.split(":");
					if(tokens[0].equalsIgnoreCase("Model"))
						model = tokens[1];
					if(tokens[0].equalsIgnoreCase("Color"))
						color = tokens[1];
					if(tokens[0].equalsIgnoreCase("Transmission"))
						trans = tokens[1];
					if(tokens[0].equalsIgnoreCase("Brakes/Traction Control"))
						brake = tokens[1];
					if(tokens[0].equalsIgnoreCase("Side Impact Air Bags"))
						airBags = tokens[1];
					if(tokens[0].equalsIgnoreCase("Power Moonroof"))
						moonRoof = tokens[1];
					
					buff = new StringBuffer();
				}
				
				else
					buff.append((char)ch);
			}
			String str = buff.toString();
			tokens = str.split(":");
			if(tokens.length > 1) {
				if(tokens[0].equalsIgnoreCase("Color"))
					color = tokens[1];
				if(tokens[0].equalsIgnoreCase("Transmission"))
					trans = tokens[1];
				if(tokens[0].equalsIgnoreCase("Brakes/Traction Control"))
					brake = tokens[1];
				if(tokens[0].equalsIgnoreCase("Side Impact Air Bags"))
					airBags = tokens[1];
				if(tokens[0].equals("Power Moonroof"))
					moonRoof =tokens[1];
			}
			fin.close();
		} catch (Exception e) {
			readContent += "\nREAD FILE EXCEPTION:  "+e.toString() + "\n";
		}	

		setVals(model, color, trans, brake, airBags, moonRoof);

	}
	
	/**
	 * Sets values of all cars in LHM and updates database
	 * @throws SQLException
	 */
	public void setVals(String model, String color, String trans, String brake, 
			String airBags, String moonRoof) throws SQLException{
		Automobile car = autoCarDB.get(model);
		car.createOptions(color, trans, brake, airBags, moonRoof);
		autoCarDB.remove(model);
		autoCarDB.put(model, car);
		car = autoCarDB.get(model);
		sqlStmt = "insert into lhm values('"+car.getModel()+"',"
				+ "'"+car.getOption("Color")+"',"
				+ "'"+car.getOption("Transmission")+"',"
				+ "'"+car.getOption("Brakes/Traction Control")+"',"
				+"'"+car.getOption("Side Impact Air Bags")+"',"
				+"'"+car.getOption("Power Moonroof")+"')";
		stmt.executeUpdate(sqlStmt);
		sqlStmt = "select * from lhm";
		rs = stmt.executeQuery(sqlStmt);
		while(rs.next()) {
			String str = (rs.getString(1)+", "+rs.getString(2)+", "+rs.getString(3)+", "
					+rs.getString(4)+", "+rs.getString(5)+"\n");
			System.out.println(str);
			output += str;
		}
	}
	
	/**
	 * Edits value and updates database
	 * @param model
	 * @param property
	 * @param value
	 * @throws SQLException
	 */
	public void editVal(String model, String property, String value) throws SQLException {
		Automobile car = autoCarDB.get(model);
		car.edit(property, value);
		autoCarDB.remove(model);
		autoCarDB.put(model, car);
		
		sqlStmt = "delete from lhm where model = '"+model+"'";
		stmt.executeUpdate(sqlStmt);
		car = autoCarDB.get(model);
		sqlStmt = "insert into lhm values('"+car.getModel()+"',"
				+ "'"+car.getOption("Color")+"',"
				+ "'"+car.getOption("Transmission")+"',"
				+ "'"+car.getOption("Brakes/Traction Control")+"',"
				+"'"+car.getOption("Side Impact Air Bags")+"',"
				+"'"+car.getOption("Power Moonroof")+"')";
		stmt.executeUpdate(sqlStmt);
		sqlStmt = "select * from lhm where model='"+model+"'";
		rs = stmt.executeQuery(sqlStmt);
		rs.next();
		System.out.println(rs.getString(2));
		output += rs.getString(1)+rs.getString(2)+rs.getString(3)+
				rs.getString(4)+rs.getString(5);
	}
	
	/**
	 * Removes car from LHM and updates database
	 * @param model
	 */
	public void removeCar(String model) {
		sqlStmt = "delete from lhm where model = '"+model+"'";
		try {
			stmt.executeUpdate(sqlStmt);
			sqlStmt = "select * from lhm where model='"+model+"'";
			rs = stmt.executeQuery(sqlStmt);
			rs.next();
			System.out.println(rs.getString(2));
			output += rs.getString(1)+rs.getString(2)+rs.getString(3)+
					rs.getString(4)+rs.getString(5);
		} catch (SQLException e) {
			System.out.println("Exception thrown because record does not exist");
		}
	}
	
	public void serializeOutput(String fileName) throws Exception {
		FileOutputStream fileOut = new FileOutputStream(fileName);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		
		out.writeChars(output);
		out.close();
        fileOut.close();
		
	}

	
	/**
	 * Returns entire LHM
	 * @return
	 */
	public LinkedHashMap<String, Automobile> getAutoCarDB() {
		return autoCarDB;
	}
}
