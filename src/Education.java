import java.sql.*;
import java.util.Scanner;

public class Education{  
	public static void main(String [] args){  
		try{  

			int ID;
			String FName, LName, Response;


			Scanner input = new Scanner(System.in); 
			System.out.println("Would you like to add, update, delete or search a record.") ;
			Response = input.next(); 


			Class.forName("com.mysql.jdbc.Driver");  
			//System.out.println("Test");

			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Education","root","password");  
			PreparedStatement preparedStmt;

			switch(Response){

			case "add":  

				System.out.println("Enter Student ID");
				ID = input.nextInt();
				input.nextLine();
				System.out.println("Enter First Name");
				FName = input.nextLine();

				System.out.println("Enter Last Name");
				LName = input.nextLine();
				String query = ("INSERT into `Education`.`student`(`Stu_ID`, `F_Name`, `L_Name`) VALUES (?, ?, ?)");
				preparedStmt = con.prepareStatement(query);


				preparedStmt.setInt(1, ID);	
				preparedStmt.setString(2, FName);
				preparedStmt.setString(3, LName);


				preparedStmt.executeUpdate();
				System.out.println("Inserted ");


				break;

			case "update": 
				System.out.println("Lets update the course name");  

				System.out.println("What is the course ID?"); 
				String CRSID = input.next(); 

				System.out.println("What is the new name of the course you are updating?");
				String update_cname = input.next(); 

				query = ("UPDATE COURSE SET CRS_NAME  = ? WHERE CRS_ID = ? ");
				preparedStmt= con.prepareStatement(query); 
				preparedStmt.setString(1, update_cname); 
				preparedStmt.setString(2, CRSID); 

				preparedStmt.executeUpdate();
				System.out.println("All set!");


				break;	

			case "delete":  
				System.out.println("Let's delete a record in student."); 

				System.out.println("What is the Enrollment ID?");
				int ERLID = input.nextInt(); 

				query = (" Delete FROM Enrollment WHERE Enr_ID = ?");
				preparedStmt= con.prepareStatement(query); 

				preparedStmt.setInt(1, ERLID);

				preparedStmt.executeUpdate();
				System.out.println("The student has been removed!"); 


				break;

			case "search": 

				System.out.println("Let's display all the students in the system"); 
				query = ("SELECT* FROM student;"); 
				Statement stmt=con.createStatement();  // to provide methods to execute different SQL queries
				ResultSet rs=stmt.executeQuery(query);  //cursor pointer to the row of the table
				//executeQuery: to execute the select query
				while(rs.next()){  // from the current pointer till the end of the table
					System.out.println(rs.getInt(1)+"  "+rs.getString(2)
					+"  "+rs.getString(3)); } 
				con.close();  
			
				System.out.println("All done, goodbye");

				break; 

			}


			input.close();
			con.close(); 
		}catch(Exception e){ System.out.println(e);}  

	}  
}  