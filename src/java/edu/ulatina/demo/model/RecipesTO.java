/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ulatina.demo.model;

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
    

    public RecipesTO() {
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
    
    
    
}
