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
    private String username;
    private String titulo;

    public IngredienteTO(String ingrediente, String username, String titulo) {
        this.ingrediente = ingrediente;
        this.username = username;
        this.titulo = titulo;
    }

    
    
    public IngredienteTO(Integer id, String ingrediente, String username, String titulo) {
        this.id = id;
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
    
    
}