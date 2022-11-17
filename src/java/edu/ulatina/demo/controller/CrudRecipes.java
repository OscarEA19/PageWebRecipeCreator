/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ulatina.demo.controller;

import Utils.ValidationUtils;
import edu.ulatina.demo.model.IngredienteTO;
import edu.ulatina.demo.model.PreparacionTO;
import edu.ulatina.demo.model.RecipesTO;
import edu.ulatina.demo.model.UserTO;
import edu.ulatina.demo.service.ServicesRecipes;
import edu.ulatina.demo.service.ServicesUser;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.imageio.ImageIO;
import org.apache.el.util.Validation;
import org.primefaces.PrimeFaces;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author Espin
 */
@ManagedBean(name = "crudRecipes")
@ViewScoped
public class CrudRecipes {

    private RecipesTO recipesTO;
    private List<RecipesTO> myRecipesList = new ArrayList<>();

    public CrudRecipes() {
        this.recipesTO = new RecipesTO();
    }

    public void openNew() {
        this.recipesTO = new RecipesTO();
    }

    public void showMyList() {

    }

    public void save(String username, List<String> ingredientes, List<String> preparaciones, String title, UploadedFile file) {
        try {
            ServicesRecipes servicesRecipes = new ServicesRecipes();

            //First get image and save at resorces/images/fileName.format
            
            String fileName = file.getFileName();
            byte[] image = file.getContent();

            ByteArrayInputStream bis = new ByteArrayInputStream(image);
            BufferedImage saveImage = ImageIO.read(bis);
            ImageIO.write(saveImage, "jpg", new File(""+fileName+".jpg"));
            System.out.println("Image generated from the byte array.");

            //Iniciando insert a la tabla de ingredientes
            for (String ingrediente : ingredientes) {
                servicesRecipes.insertIngredientes(new IngredienteTO(ingrediente, username, title));
            }

            //Iniciando insert a la tabla de preparaciones
            for (String paso : preparaciones) {
                servicesRecipes.insertPreparaciones(new PreparacionTO(paso, title, username));
            }
            recipesTO.setUsername(username);
            recipesTO.setImgPath(fileName);
            if (servicesRecipes.insert(this.recipesTO)) {
                LoginController loginController = new LoginController();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Recipe Added Success"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Recipe Added Failed, please try again"));
            }
            PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
            PrimeFaces.current().ajax().update("form:messages", "form:dt-products");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete() {

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }

    public RecipesTO getRecipesTO() {
        return recipesTO;
    }

    public void setRecipesTO(RecipesTO recipesTO) {
        this.recipesTO = recipesTO;
    }

    public List<RecipesTO> getMyRecipesList() {
        return myRecipesList;
    }

    public void setMyRecipesList(List<RecipesTO> myRecipesList) {
        this.myRecipesList = myRecipesList;
    }

}
