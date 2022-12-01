/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ulatina.demo.controller;

import edu.ulatina.demo.model.IngredienteTO;
import edu.ulatina.demo.model.PreparacionTO;
import edu.ulatina.demo.model.RecipesTO;
import edu.ulatina.demo.model.UserTO;
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
import org.primefaces.event.RowEditEvent;
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

    public void save(Integer idUser, List<String> ingredientes, List<String> preparaciones, String title, UploadedFile file, byte[] image) {
        try {
            ServicesRecipes servicesRecipes = new ServicesRecipes();

            //First get image and save at resorces/images/fileName.format
            String fileNameAndPath = file.getFileName();

            ByteArrayInputStream inputStream = new ByteArrayInputStream(image);
            BufferedImage saveImage = ImageIO.read(inputStream);
            ImageIO.write(saveImage, "jpg", new File("C:/Users/Espin/OneDrive/Documents/NetBeansProjects/proyectoProgra4/web/resources/images/" + fileNameAndPath));

            recipesTO.setIdUser(idUser);
            recipesTO.setImgPath(fileNameAndPath);

            if (servicesRecipes.insert(this.recipesTO)) {
                LoginController loginController = new LoginController();

                //Iniciando insert a la tabla de ingredientes
                for (String ingrediente : ingredientes) {
                    servicesRecipes.insertIngredientes(new IngredienteTO(ingrediente, title, idUser));
                }

                //Iniciando insert a la tabla de preparaciones
                for (String paso : preparaciones) {
                    servicesRecipes.insertPreparaciones(new PreparacionTO(paso, title, idUser));
                }
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

    public void onRowEdit(RowEditEvent<RecipesTO> event) {

        ServicesRecipes servicesRecipes = new ServicesRecipes();

        Integer id = event.getObject().getId();
        String titulo = event.getObject().getTitle();
        String descripcion = event.getObject().getDescription();
        List<IngredienteTO> ingredientes = event.getObject().getIngredientes();
        List<PreparacionTO> preparaciones = event.getObject().getPreparaciones();

        RecipesTO recipesTOInput = new RecipesTO(id, titulo, descripcion, ingredientes, preparaciones);

        if (servicesRecipes.updateReceta(recipesTOInput)) {
            if (servicesRecipes.updateIngrediente(recipesTOInput)) {
                if (servicesRecipes.updatePreparacion(recipesTOInput)) {
                    FacesMessage msg = new FacesMessage("Receta editada correctamente", null);
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                } else {
                    FacesMessage errorPreparacion = new FacesMessage("Error al editar las preparaciones", null);
                    FacesContext.getCurrentInstance().addMessage(null, errorPreparacion);
                }
            } else {
                FacesMessage errorIngrediente = new FacesMessage("Error al editar el ingrediente", null);
                FacesContext.getCurrentInstance().addMessage(null, errorIngrediente);
            }
        } else {
            FacesMessage errorReceta = new FacesMessage("Error al editar la receta", null);
            FacesContext.getCurrentInstance().addMessage(null, errorReceta);
        }
    }

    public void onRowCancel(RowEditEvent<UserTO> event) {
        FacesMessage msg = new FacesMessage("Edicion cancelada", null);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
