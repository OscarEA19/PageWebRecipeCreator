/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.junit.Test;

/**
 *
 * @author Espin
 */
public class Conectar {
    
    public static final String URL = "jdbc:mysql://localhost:3306/baseempleado";
    public static final String USER = "root";
    public static final String CLAVE = "root";

    @Test
    public void testConnection() {
         Connection con = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(URL, USER, CLAVE);
            Statement stmt=(Statement) con.createStatement();  
            ResultSet rs=stmt.executeQuery("show databases;");
            System.out.println("Connected");  
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
}


