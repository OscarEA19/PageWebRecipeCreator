/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ulatina.demo.model;

/**
 *
 * @author Espin
 */
public class IngredienteTO {
    
    private Integer id;
    private String ingrediente;
    private Integer idReceta;
    private String username;
    private String titulo;
    private Integer idUser;

    public IngredienteTO() {
    }

    public IngredienteTO(String ingrediente, Integer idReceta, Integer idUser) {
        this.ingrediente = ingrediente;
        this.idReceta = idReceta;
        this.idUser = idUser;
    }

    public IngredienteTO(String ingrediente, String titulo, Integer idUser) {
        this.ingrediente = ingrediente;
        this.titulo = titulo;
        this.idUser = idUser;
    }
    

    public IngredienteTO(Integer id, String ingrediente, Integer idReceta) {
        this.id = id;
        this.ingrediente = ingrediente;
        this.idReceta = idReceta;
    }

    public IngredienteTO(Integer idReceta, String username, String titulo) {
        this.idReceta = idReceta;
        this.username = username;
        this.titulo = titulo;
    }

    public IngredienteTO(String ingrediente, String username, String titulo) {
        this.ingrediente = ingrediente;
        this.username = username;
        this.titulo = titulo;
    }
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(String ingrediente) {
        this.ingrediente = ingrediente;
    }

    public Integer getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(Integer idReceta) {
        this.idReceta = idReceta;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    
   
    
}
