
package com.taskApp.taskApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskAppApplication {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
                /*Connection con = null;
                try{
                Class.forName("org.h2.Driver");
                con = DriverManager.getConnection("jdbc:h2:file:C:\\Users\\Brisa\\OneDrive - UTN HAEDO\\Escritorio\\bdd\\taskbdd");
                System.out.println("Conexion exitosa con la base de datos");
                }catch(Exception ex){
                    System.out.println("Error en la conexion de base de datos"+ ex);
                }*/
		SpringApplication.run(TaskAppApplication.class, args);
	}

}
