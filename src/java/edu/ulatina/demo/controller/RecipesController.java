/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ulatina.demo.controller;

import edu.ulatina.demo.model.RecipesTO;
import edu.ulatina.demo.service.ServicesRecipes;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author usuario
 */
@ManagedBean(name = "recipesController")
@SessionScoped
public class RecipesController implements Serializable {

    private RecipesTO recipesTO = new RecipesTO();
    private List<RecipesTO> recipesList = new ArrayList<>();
    public ServicesRecipes servicesRecipes = new ServicesRecipes();
    private String ingrediente;
    private String preparacion;
    private List<String> ingredientes;
    private List<String> preparaciones;
    private UploadedFile file;

    public RecipesController() {
        this.ingredientes = new ArrayList<>();
        this.preparaciones = new ArrayList<>();
    }

    public void showAllRecipes() {
        this.recipesList = servicesRecipes.listAllRecipes();

    }

    public RecipesTO getRecipesTO() {
        return recipesTO;
    }

    public void setRecipesTO(RecipesTO recipesTO) {
        this.recipesTO = recipesTO;
    }

    public List<RecipesTO> getRecipesList() {
        return recipesList;
    }

    public void setRecipesList(List<RecipesTO> recipesList) {
        this.recipesList = recipesList;
    }

    public ServicesRecipes getServicesRecipes() {
        return servicesRecipes;
    }

    public void setServicesRecipes(ServicesRecipes servicesRecipes) {
        this.servicesRecipes = servicesRecipes;
    }

    public List<String> getPreparaciones() {
        return preparaciones;
    }

    public void setPreparaciones(List<String> preparaciones) {
        this.preparaciones = preparaciones;
    }

    public void addPreparacion(String preparacion) {
        this.preparaciones.add(preparacion);
    }

    public void addIngredientes(String ingrediente) {
        this.ingredientes.add(ingrediente);
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

    public List<String> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<String> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public UploadedFile getFile() {
        System.out.println("se esta obteniendo el file");
        return file;
    }

    public void setFile(UploadedFile file) {
        System.out.println("se esta guardando el file");
        this.file = file;
    }

    public void upload() {

        if (file != null) {
            System.out.println("si");
        } else {
            System.out.println("no");

        }

    }
}
