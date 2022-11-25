/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ulatina.demo.controller;

import edu.ulatina.demo.model.IngredienteTO;
import edu.ulatina.demo.model.PreparacionTO;
import edu.ulatina.demo.model.RecipesTO;
import edu.ulatina.demo.service.ServicesRecipes;
import edu.ulatina.demo.service.ServicesUser;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.imageio.ImageIO;
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
    private RecipesTO informatioRecipes;
    private List<RecipesTO> myRecipesList = new ArrayList<>();

    public CrudRecipes() {
        this.recipesTO = new RecipesTO();
    }

    public void openNew() {
        this.recipesTO = new RecipesTO();
    }

    public void informacionReceta(Integer id) {

        this.informatioRecipes = new RecipesTO(id);

    }

    public void save(String username, List<String> ingredientes, List<String> preparaciones, String title, UploadedFile file, byte[] image) {
        try {
            ServicesRecipes servicesRecipes = new ServicesRecipes();

            //First get image and save at resorces/images/fileName.format
            String fileNameAndPath = file.getFileName();

            ByteArrayInputStream inputStream = new ByteArrayInputStream(image);
            BufferedImage saveImage = ImageIO.read(inputStream);
            ImageIO.write(saveImage, "jpg", new File("C:/Users/Espin/OneDrive/Documents/NetBeansProjects/proyectoProgra4/web/resources/images/" + fileNameAndPath));

            recipesTO.setUsername(username);
            recipesTO.setImgPath(fileNameAndPath);

            if (servicesRecipes.insert(this.recipesTO)) {
                LoginController loginController = new LoginController();

                //Iniciando insert a la tabla de ingredientes
                for (String ingrediente : ingredientes) {
                    servicesRecipes.insertIngredientes(new IngredienteTO(ingrediente, username, title));
                }

                //Iniciando insert a la tabla de preparaciones
                for (String paso : preparaciones) {
                    servicesRecipes.insertPreparaciones(new PreparacionTO(paso, title, username));
                }

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Recipe Added Success"));
                loginController.goingToHome();
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Recipe Added Failed, please try again"));
            }
            PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
            PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Integer id) {
        ServicesRecipes servicesRecipes = new ServicesRecipes();
        if (servicesRecipes.eliminar(id)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Receta eliminada correctamente"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error al intentar eliminar la receta"));
        }
        PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
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

    public RecipesTO getInformatioRecipes() {
        return informatioRecipes;
    }

    public void setInformatioRecipes(RecipesTO informatioRecipes) {
        this.informatioRecipes = informatioRecipes;
    }

}
