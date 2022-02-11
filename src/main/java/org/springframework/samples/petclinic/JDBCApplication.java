package org.springframework.samples.petclinic;


import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.owner.Pet;
import org.springframework.samples.petclinic.owner.PetType;




public class JDBCApplication {

	private static Owner owner = new Owner();
	
	public static void main(String[] args) {
		System.out.println("-------- Test de conexión con MySQL ------------");
		try {
     	   Class.forName("com.mysql.jdbc.Driver");
     	} catch (ClassNotFoundException e) {
     	   System.out.println("No encuentro el driver");
     	   e.printStackTrace();
     	   return;
     	}

     	System.out.println("Driver instalado y funcionando");
     	Connection connection = null;
     	Statement statement = null;
     	try {
     	   connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/petclinic", "root", "root");
     	if (connection != null)
     	   System.out.println("Conexión establecida");

     	statement = connection.createStatement();
     	
     	//Taller Part1
     	showOwners(statement);     	
     	//Taller Part2
     	//createOwner(statement);
     	//Taller Part3
     	//updateCity(statement);
     	//Taller Part4
     	findMatches(connection);     	
     	//Taller Part5
     	//createOwner2(connection);
     	
     	//Reto
     	owner.setId(13);
		owner.setFirstName("Francisco");
		owner.setLastName("Martínez");
		owner.setAddress("Dir de prueba");
		owner.setCity("León");
		owner.setTelephone("666666666");
		
		
     	//createOwner3(connection);
     	
     	//createPet(statement);
     	
     	removeOwner(statement);
     	
     	
     	    
     	} catch (SQLException e) {
     	   System.out.println("Connection Failed! Check output console");
     	   e.printStackTrace();
     	   return;
     	} finally {
     	   try {
     	   if(statement != null)
     	      connection.close();
     	   } catch (SQLException se) {}
     	   try {
     	      if(connection != null)
     	         connection.close();
     	   } catch (SQLException se) {
     	      se.printStackTrace();
     	   }
     	}
		
	}
	
	public static void showOwners(Statement statement) {
		//Taller1
     	String sqlOwners = "SELECT * FROM owners";
     	ResultSet rs;
		try {
			rs = statement.executeQuery(sqlOwners);
			while(rs.next()){
    	         int id = rs.getInt("id");
    	         String firstName = rs.getString("first_name");
    	         String lastName = rs.getString("last_name");
    	         String address = rs.getString("address");
    	         String city = rs.getString("city");
    	         String telephone = rs.getString("telephone");
    	         
    	         System.out.print("Id: " + id);
    	         System.out.print(", Nombre: " + firstName);
    	         System.out.print(", Apellidos: " + lastName);
    	         System.out.print(", Dirección: " + address);
    	         System.out.print(", Ciudad: " + city);
    	         System.out.println(", Teléfono: " + telephone);
    	         
    	}
    	rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     	
	}
	
	public static void createOwner(Statement statement) {
		//Taller2
		String sql = "INSERT INTO owners (first_name, last_name, address, city, telephone) "
		+ 
		"VALUES ('Francisco', 'Martínez', 'DirPrueba', 'León', '666666666')";
		try {
			int numeroDeFilasModificadas = statement.executeUpdate(sql);
			System.out.println("Owners añadidos: " + numeroDeFilasModificadas);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void updateCity(Statement statement) {
		//Taller3
		String sql = "UPDATE owners "
		           + "SET city = 'Sevilla'"
		           + "WHERE first_name = 'Francisco'";
		int numeroDeFilasModificadas;
		try {
			numeroDeFilasModificadas = statement.executeUpdate(sql);
			System.out.println("Campos actualizados: " + numeroDeFilasModificadas);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void findMatches(Connection connection) {
		//Taller4
		String sql = "SELECT * FROM owners WHERE first_name LIKE ? OR last_name LIKE ?";
		String busqueda = "Da";
		String termino = "%"+busqueda+"%";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, termino);
			preparedStatement.setString(2, termino);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
			         int id = rs.getInt("id");
			         String firstName = rs.getString("first_name");
			         String lastName = rs.getString("last_name");

			         System.out.print("Id: " + id);
			         System.out.print(", Nombre: " + firstName);
			         System.out.println(", Apellidos: " + lastName);
			}
			    rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static void createOwner2(Connection connection) {
		//Taller5
		String[] valores;
		valores = new String[] {"Marcos", "Ginel", "Mi casa", "Sevilla", "666666666"};
		String sql = "INSERT INTO owners (first_name, last_name, address, city, telephone)  VALUES(?,?,?,?,?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			for(int i=0; i < valores.length; i++)
			   preparedStatement.setString(i+1, valores[i]);

			int numeroDeFilasModificadas = preparedStatement.executeUpdate();
			System.out.println("Owners añadidos: " + numeroDeFilasModificadas);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void createOwner3(Connection connection) {
		//Reto
		String[] valores;
		valores = new String[] {owner.getFirstName(), owner.getLastName(), owner.getAddress(), owner.getCity(), owner.getTelephone()};
		String sql = "INSERT INTO owners (first_name, last_name, address, city, telephone)  VALUES(?,?,?,?,?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			for(int i=0; i < valores.length; i++)
			   preparedStatement.setString(i+1, valores[i]);

			int numeroDeFilasModificadas = preparedStatement.executeUpdate();
			System.out.println("Owners añadidos: " + numeroDeFilasModificadas);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void createPet(Statement statement) {
		//Reto
		Date date = new Date();
		date.setDate(9);
		date.setMonth(11);
		date.setYear(2012);
		
		PetType type = new PetType();
		type.setId(2);
		type.setName("dog");
		
		Pet pet = new Pet();
		pet.setName("Slash");
		pet.setBirthDate(date);
		pet.setType(type);
		pet.setOwner(owner);
		
		String sql = "INSERT INTO pets (name, birth_date, type_id, owner_id)" + 
		"  VALUES ( '" + pet.getName() + "' " + ", " + "'2012/11/09'" + ", " + pet.getType().getId() + ", " + pet.getOwner().getId() + ")";
		try {	
			int numeroDeFilasModificadas = statement.executeUpdate(sql);
			System.out.println("Pets añadidas: " + numeroDeFilasModificadas);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void removeOwner(Statement statement) {
		//Reto
		
		String sql = "DELETE FROM pets WHERE owner_id = 13";
		int numeroDeFilasModificadas;
		try {
			numeroDeFilasModificadas = statement.executeUpdate(sql);
			System.out.println("Pets eliminadas: " + numeroDeFilasModificadas);
			
			sql = "DELETE FROM owners WHERE first_name = 'Francisco'";
			numeroDeFilasModificadas = statement.executeUpdate(sql);
			System.out.println("Owners eliminados: " + numeroDeFilasModificadas);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
}
