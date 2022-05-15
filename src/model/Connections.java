package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Connections {

	//A common method to connect to the DB
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");

	 //Provide the correct details: DBServer/DBName, username, password
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/tests", "root", "0752341290");
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 }
	
	public String insertConnections(String conName, String conType, String conDesc, String conAdminId)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting!."; }
	 // create a prepared statement
	 String query = " insert into connections(`conID`,`conName`,`conType`,`conDesc`,`conAdminId`)"
	 + " values (?, ?, ?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, conName);
	 preparedStmt.setString(3, conType);
	 preparedStmt.setString(4, conDesc);
	 preparedStmt.setString(5, conAdminId);
	
	 // execute the statement

	 preparedStmt.execute();
	 con.close();
	 String newConnections = readConnections();
	 output = "{\"status\":\"success\", \"data\": \"" +
	 newConnections + "\"}";
	 }
	 catch (Exception e)
	 {
	 output = "{\"status\":\"error\", \"data\":\"Error while inserting the item.\"}";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	

public String readConnections()
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for reading."; }
 // Prepare the html table to be displayed
 output = "<table border='1'><tr><th>Connection-Name</th>" +
 "<th>Connection-Type</th>" +
 "<th>Connection-Description</th>" +
 "<th>Connection-AdminId</th>" +
 "<th>Update</th><th>Remove</th></tr>";

 String query = "select * from connections";
 Statement stmt = con.createStatement();
 ResultSet rs = stmt.executeQuery(query);
 // iterate through the rows in the result set
 while (rs.next())
 {
 String conID = Integer.toString(rs.getInt("conID"));
 String conName = rs.getString("conName");
 String conType = rs.getString("conType");
 String conDesc = rs.getString("conDesc");
 String conAdminId = rs.getString("conAdminId");

 // Add into the html table

 output += "<td>" + conName + "</td>";
 output += "<td>" + conType + "</td>";
 output += "<td>" + conDesc + "</td>";
 output += "<td>" + conAdminId + "</td>";

//buttons
output += "<td><input name='btnUpdate' type='button' value='Update' "
+ "class='btnUpdate btn btn-secondary' data-itemid='" + conID + "'></td>"
+ "<td><input name='btnRemove' type='button' value='Remove' "
+ "class='btnRemove btn btn-danger' data-itemid='" + conID + "'></td></tr>";
}
 con.close();
 // Complete the html table
 output += "</table>";
 }
 catch (Exception e)
 {
 output = "Error while reading the nitems.";
 System.err.println(e.getMessage());
 }
 return output;
 }

public String updateConnections(String conID,String conName, String conType, String conDesc, String conAdminId)

{
String output = "";
try
{
Connection con = connect();
if (con == null)
{return "Error while connecting to the database for updating."; }
// create a prepared statement
String query = "UPDATE connections SET conName=?,conType=?,conDesc=?,conAdminId=?WHERE conID=?";
PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values

preparedStmt.setString(1, conName);
preparedStmt.setString(2, conType);
preparedStmt.setString(3, conDesc);
preparedStmt.setString(4, conAdminId);
preparedStmt.setInt(5, Integer.parseInt(conID));
// execute the statement
preparedStmt.execute();
con.close();
String newConnections = readConnections();
output = "{\"status\":\"success\", \"data\": \"" +
newConnections + "\"}";
}
catch (Exception e)
{
output = "{\"status\":\"error\", \"data\":\"Error while Updating the Connections.\"}";
System.err.println(e.getMessage());
}
return output;
}



public String deleteConnections(String conID)
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for deleting."; }
 // create a prepared statement
 String query = "delete from connections where conID=?";
 PreparedStatement preparedStmt = con.prepareStatement(query);
 // binding values
 preparedStmt.setInt(1, Integer.parseInt(conID));
 // execute the statement
 preparedStmt.execute();
 con.close();
 String newConnections= readConnections();
 output = "{\"status\":\"success\", \"data\": \"" +
 newConnections + "\"}";
 }
 catch (Exception e)
 {
 output = "{\"status\":\"error\", \"data\":\"Error while Deleting the Connections Details.\"}";
 System.err.println(e.getMessage());
 }
 return output;
 }
}
