/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.PrimeFaces;
import org.primefaces.event.RateEvent;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author usuario
 */
@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController implements Serializable {

    private String username;
    private String password;
    private RecipesTO recipesTO;
    private UserTO usuarioTO = new UserTO();
    private List<UserTO> listUser = new ArrayList<>();
    private List<RecipesTO> listRecipes = new ArrayList<>();
    private List<RecipesTO> myListRecipes = new ArrayList<>();

    public LoginController() {
        this.recipesTO = new RecipesTO();
    }

    public LoginController(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void ingresar() {
        System.out.println("Username: " + this.username);
        System.out.println("Password: " + this.password);
        ServicesUser servicioUsuario = new ServicesUser();
        ServicesRecipes servicioRecipes = new ServicesRecipes();
        this.usuarioTO = servicioUsuario.validarDB(this.username, this.password);
        if (Objects.nonNull(this.usuarioTO)) {
            this.listUser = servicioUsuario.listUser();
            this.listRecipes = servicioRecipes.listAllRecipes();
            this.myListRecipes = servicioRecipes.listRecipesByUser(usuarioTO);
            this.redireccionar("/faces/home.xhtml");
        } else {
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al hacer login", "El nombre de usuario o la contraseña es invalida"));
        }
    }

    public void refreshPagePersona() {
        ServicesUser servicioUsuario = new ServicesUser();
        ServicesRecipes servicioRecipes = new ServicesRecipes();
        this.listUser = servicioUsuario.listUser();
        this.listRecipes = servicioRecipes.listAllRecipes();
        this.myListRecipes = servicioRecipes.listRecipesByUser(usuarioTO);
        this.redireccionar("/faces/mantenimientoPersona.xhtml");
    }

    public void refreshPageRecetas() {
        ServicesUser servicioUsuario = new ServicesUser();
        ServicesRecipes servicioRecipes = new ServicesRecipes();
        this.listUser = servicioUsuario.listUser();
        this.listRecipes = servicioRecipes.listAllRecipes();
        this.myListRecipes = servicioRecipes.listRecipesByUser(usuarioTO);
        this.redireccionar("/faces/mantenimientoRecetas.xhtml");
    }
    
    public void refreshPageRecetasHome() {
        ServicesUser servicioUsuario = new ServicesUser();
        ServicesRecipes servicioRecipes = new ServicesRecipes();
        this.listUser = servicioUsuario.listUser();
        this.listRecipes = servicioRecipes.listAllRecipes();
        this.myListRecipes = servicioRecipes.listRecipesByUser(usuarioTO);
        this.redireccionar("/faces/home.xhtml");
    }

    public void refreshPageRecetasAdmin() {
        ServicesUser servicioUsuario = new ServicesUser();
        ServicesRecipes servicioRecipes = new ServicesRecipes();
        this.listUser = servicioUsuario.listUser();
        this.listRecipes = servicioRecipes.listAllRecipes();
        this.myListRecipes = servicioRecipes.listRecipesByUser(usuarioTO);
        this.redireccionar("/faces/mantenimientoRecetasAdmin.xhtml");
    }

    public void goingToLogin() {
        if (Objects.nonNull(this.usuarioTO)) {
            ServicesUser servicioUsuario = new ServicesUser();
            this.listUser = servicioUsuario.listUser();
            this.redireccionar("/");
        } else {
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login Failed", "The username and password are invalid"));
        }
    }

    public void goingToHome() {
        ServicesUser servicioUsuario = new ServicesUser();
        ServicesRecipes servicioRecipes = new ServicesRecipes();
        this.listUser = servicioUsuario.listUser();
        this.listRecipes = servicioRecipes.listAllRecipes();
        this.myListRecipes = servicioRecipes.listRecipesByUser(usuarioTO);
        this.redireccionar("/faces/home.xhtml");
    }

    public static void redireccionar(String ruta) {
        HttpServletRequest request;
        try {
            request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + ruta);
        } catch (Exception e) {
        }
    }

    public UserTO getUsuarioTO() {
        return usuarioTO;
    }

    public void setUsuarioTO(UserTO usuarioTO) {
        this.usuarioTO = usuarioTO;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserTO> getListUser() {
        return listUser;
    }

    public void setListUser(List<UserTO> listUser) {
        this.listUser = listUser;
    }

    public List<RecipesTO> getListRecipes() {
        return listRecipes;
    }

    public void setListRecipes(List<RecipesTO> listRecipes) {
        this.listRecipes = listRecipes;
    }

    public List<RecipesTO> getMyListRecipes() {
        return myListRecipes;
    }

    public void setMyListRecipes(List<RecipesTO> myListRecipes) {
        this.myListRecipes = myListRecipes;
    }

    public void delete(Integer id) throws Exception {
        ServicesRecipes servicesRecipes = new ServicesRecipes();
        if (servicesRecipes.eliminar(id)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Receta eliminada correctamente"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error al intentar eliminar la receta"));
        }
        PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        refreshPageRecetas();
    }
    
    public void deleteAdmin(Integer id) throws Exception {
        ServicesRecipes servicesRecipes = new ServicesRecipes();
        if (servicesRecipes.eliminar(id)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Receta eliminada correctamente"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error al intentar eliminar la receta"));
        }
        PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        refreshPageRecetasAdmin();
    }

    public void save(Integer idUser, List<String> ingredientes, List<String> preparaciones, String title, String descrip, UploadedFile file, byte[] image, RecipesController recipesController) {
        try {               
            ServicesRecipes servicesRecipes = new ServicesRecipes();

            //First get image and save at resorces/images/fileName.format
            String fileNameAndPath = file.getFileName();

            ByteArrayInputStream inputStream = new ByteArrayInputStream(image);
            BufferedImage saveImage = ImageIO.read(inputStream);
            ImageIO.write(saveImage, "jpg", new File("C:/Users/Espin/OneDrive/Documents/NetBeansProjects/proyectoProgra4/web/resources/images/" + fileNameAndPath));

            recipesTO.setIdUser(idUser);
            recipesTO.setImgPath(fileNameAndPath);
            recipesTO.setDescription(descrip);
            recipesTO.setTitle(title);

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
                recipesController.setIngredientes(new ArrayList<>());
                recipesController.setPreparaciones(new ArrayList<>());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Recipe Added Success"));
            } else {
                recipesController.setIngredientes(new ArrayList<>());
                recipesController.setPreparaciones(new ArrayList<>());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Recipe Added Failed, please try again"));
            }
            PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
            PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
            refreshPageRecetas();
        } catch (Exception e) {
            e.printStackTrace();
            recipesController.setIngredientes(new ArrayList<>());
            recipesController.setPreparaciones(new ArrayList<>());
        }
    }

    public RecipesTO getRecipesTO() {
        return recipesTO;
    }

    public void setRecipesTO(RecipesTO recipesTO) {
        this.recipesTO = recipesTO;
    }

    public void deleteUser(String id) {
        ServicesUser servicesUser = new ServicesUser();
        if (servicesUser.eliminar(id)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario eliminado correctamente"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error al intentar eliminar el usuario"));
        }
        PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        refreshPagePersona();
    }

    public void saveUser(UserTO userTO) {        
        ServicesUser servicesUser = new ServicesUser();
        if (ValidationUtils.isUserValid(userTO)) {
            if (servicesUser.insert(userTO)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario agregado correctamente"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error al crear el usuario"));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error al crear el usuario"));
        }
        PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        refreshPagePersona();
    }
    
    public void onrate(RateEvent<Integer> rateEvent) {
        ServicesRecipes servicesRecipes = new ServicesRecipes();
        ServicesUser servicesUser = new ServicesUser();

        Integer idReceta = (Integer) rateEvent.getComponent().getAttributes().get("idReceta");
        Integer newValue = rateEvent.getRating();

        //get people
        Integer numberOfRating = servicesUser.getTotalPeople();

        //get current value
        Integer currentValue = servicesRecipes.getCurrentValueRecipeRank(idReceta);

        Integer total = currentValue * numberOfRating;
        total += newValue;
        total = total / (numberOfRating + 1);

        Integer valorEstrellaSave = total;
        if (servicesRecipes.updateRecipeRank(idReceta, valorEstrellaSave)) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardado Correctamente", "La receta fue calificada con: " + rateEvent.getRating() + " estrellas");
            FacesContext.getCurrentInstance().addMessage(null, message);
            refreshPageRecetasHome();
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al calificar la receta", "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    
    
}
