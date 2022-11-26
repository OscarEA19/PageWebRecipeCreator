/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ulatina.demo.model;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author usuario
 */
public class UserTO implements Serializable {
    private int id;
    private String username;
    private String lastname;
    private String password;
    private String email;
    private Date fechaRegistro;
    private boolean admin;
    
    
    public UserTO() {
    }
    
     public UserTO(int id, String username, String lastname, String email, boolean admin) {
        this.id = id;
        this.username = username;
        this.lastname = lastname;
        this.email = email;
        this.admin = admin;
    }
     
     public UserTO(int id, String username, String lastname, String email) {
        this.id = id;
        this.username = username;
        this.lastname = lastname;
        this.email = email;
    }

    public UserTO(int id, String username, String lastname, String email, Date fechaRegistro) {
        this(id,username,lastname,email);
        this.fechaRegistro = fechaRegistro;
    }

    public UserTO(int id, String username, String password, String email, Date fechaRegistro,String lastname) {
        this(id,username,lastname,email,fechaRegistro);
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String Date) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    

    
}
