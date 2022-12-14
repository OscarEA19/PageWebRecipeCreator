/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ulatina.demo.controller;

import edu.ulatina.demo.model.RecipesTO;
import edu.ulatina.demo.service.ServicesRecipes;
import edu.ulatina.demo.service.ServicesUser;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
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
    private byte[] imagenArray;
    private RecipesTO informatioRecipes;

    public RecipesController() {
        this.ingredientes = new ArrayList<>();
        this.preparaciones = new ArrayList<>();
    }
    
    public void cleanList(){
        this.ingrediente = null;
        this.preparacion = null;
        this.ingredientes = new ArrayList<>();
        this.preparaciones = new ArrayList<>();
    }

    public void newinformacionReceta() {

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

    public void upload(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Imagen ", event.getFile().getFileName() + " se esta subiendo");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        this.setImagenArray(event.getFile().getContent());
        this.setFile(event.getFile());
    }

    public byte[] getImagenArray() {
        return imagenArray;
    }

    public void setImagenArray(byte[] imagenArray) {
        this.imagenArray = imagenArray;
    }

    public RecipesTO getInformatioRecipes() {
        return informatioRecipes;
    }

    public void setInformatioRecipes(RecipesTO informatioRecipes) {
        this.informatioRecipes = informatioRecipes;
    }

}
