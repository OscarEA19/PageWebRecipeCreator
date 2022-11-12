/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ulatina.demo.model;

/**
 *
 * @author Espin
 */
public class PreparacionTO {

    private Integer id;
    private String paso;
    private String titulo;
    private String username;

    public PreparacionTO(String paso, String titulo, String username) {
        this.paso = paso;
        this.titulo = titulo;
        this.username = username;
    }

    
    public PreparacionTO(Integer id, String paso, String titulo, String username) {
        this.id = id;
        this.paso = paso;
        this.titulo = titulo;
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPaso() {
        return paso;
    }

    public void setPaso(String paso) {
        this.paso = paso;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    
    

    
    
}
