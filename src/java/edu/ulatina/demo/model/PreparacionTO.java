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
    private Integer idReceta;
    private String username;
    private String titulo;
    private Integer idUser;

    public PreparacionTO() {
    }

    public PreparacionTO(String paso, Integer idReceta, Integer idUser) {
        this.paso = paso;
        this.idReceta = idReceta;
        this.idUser = idUser;
    }

    public PreparacionTO(String paso, String titulo, Integer idUser) {
        this.paso = paso;
        this.titulo = titulo;
        this.idUser = idUser;
    }
    

    public PreparacionTO(Integer id, String paso, Integer idReceta) {
        this.id = id;
        this.paso = paso;
        this.idReceta = idReceta;
    }

    public PreparacionTO(String paso, String titulo, String username) {
        this.paso = paso;
        this.username = username;
        this.titulo = titulo;
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
