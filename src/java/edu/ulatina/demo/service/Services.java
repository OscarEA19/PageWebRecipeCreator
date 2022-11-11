/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ulatina.demo.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Espin
 */
public class Services {
    protected Connection conn = null;
    public static final String URL = "jdbc:mysql://localhost:3306/proyectorecetas";
    public static final String USER = "root";
    public static final String CLAVE = "root";
    
    public void connection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, CLAVE);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void closeStatement(Statement stmt){
        try{
            if(stmt != null && !stmt.isClosed()){
                stmt.close();
                stmt = null;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }   
    }
    
    public void closeResultSet(ResultSet rs){
        try{
            if(rs != null && !rs.isClosed()){
                rs.close();
                rs = null;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }  
    }
    
    public void desconect (){
        try{
            if(conn != null && !conn.isClosed()){
                conn.close();
                conn = null;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }  
    } 
}