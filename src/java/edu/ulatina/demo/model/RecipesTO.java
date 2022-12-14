/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ulatina.demo.model;

import java.util.List;


/**
 *
 * @author Espin
 */
public class RecipesTO {
    
    private Integer id;
    private String title;
    private String description;
    private String username;
    private String imgPath;
    private Integer idUser;
    private List<IngredienteTO> ingredientes;
    private List<PreparacionTO> preparaciones;
    private String ingrediente;
    private String preparacion;
    private List<String> ingredientesList;
    private List<String> preparacionesList;
    private Integer ratingStar;
   
    public RecipesTO() {
    }
    public RecipesTO(Integer id) {
        this.id = id;
    }

    public RecipesTO(Integer id, String title, String description, List<IngredienteTO> ingredientes, List<PreparacionTO> preparaciones) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.ingredientes = ingredientes;
        this.preparaciones = preparaciones;
    }

    public RecipesTO(String title, String description, String imgPath) {
        this.title = title;
        this.description = description;
        this.imgPath = imgPath;
    }
    

    public RecipesTO(Integer id, String title, String description, String username, String imgPath) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.username = username;
        this.imgPath = imgPath;
    }

    public RecipesTO(Integer id, String title, String description, String username, String imgPath, List<IngredienteTO> ingredientes, List<PreparacionTO> preparaciones,Integer likes) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.username = username;
        this.imgPath = imgPath;
        this.ingredientes = ingredientes;
        this.preparaciones = preparaciones;
        this.ratingStar = likes;
    }
    
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<IngredienteTO> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<IngredienteTO> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public List<PreparacionTO> getPreparaciones() {
        return preparaciones;
    }

    public void setPreparaciones(List<PreparacionTO> preparaciones) {
        this.preparaciones = preparaciones;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(String ingrediente) {
        this.ingrediente = ingrediente;
    }

    public String getPreparacion() {
        return preparacion;
    }

    public void setPreparacion(String preparacion) {
        this.preparacion = preparacion;
    }
    
    public void addPreparacion(String preparacion) {
        this.preparacionesList.add(preparacion);
    }

    public void addIngredientes(String ingrediente) {
        this.ingredientesList.add(ingrediente);
    }

    public Integer getRatingStar() {
        return ratingStar;
    }

    public void setRatingStar(Integer ratingStar) {
        this.ratingStar = ratingStar;
    }
    
    
}
