/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ulatina.demo.controller;

import edu.ulatina.demo.model.RecipesTO;
import edu.ulatina.demo.model.UserTO;
import edu.ulatina.demo.service.ServicesRecipes;
import edu.ulatina.demo.service.ServicesUser;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author usuario
 */
@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController implements Serializable {

    private String username;
    private String password;
    private UserTO usuarioTO = new UserTO();
    private List<UserTO> listUser = new ArrayList<>();
    private List<RecipesTO> listRecipes = new ArrayList<>();
    private List<RecipesTO> myListRecipes = new ArrayList<>();

    public LoginController() {
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
        CrudRecipes crudRecipes = new CrudRecipes();
        crudRecipes.setUserTO(this.usuarioTO);
        this.usuarioTO = servicioUsuario.validarDB(this.username, this.password);
        if (Objects.nonNull(this.usuarioTO)) {
            this.listUser = servicioUsuario.listUser();
            this.listRecipes = servicioRecipes.listAllRecipes();
            this.myListRecipes = servicioRecipes.listRecipesByUser(usuarioTO);
            this.redireccionar("/faces/home.xhtml");
        } else {
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login Failed", "The username and password are invalid"));
        }
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
    

}
