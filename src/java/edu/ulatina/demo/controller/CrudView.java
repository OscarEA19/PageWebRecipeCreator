/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ulatina.demo.controller;

import Utils.ValidationUtils;
import edu.ulatina.demo.model.RecipesTO;
import edu.ulatina.demo.model.UserTO;
import edu.ulatina.demo.service.ServicesRecipes;
import edu.ulatina.demo.service.ServicesUser;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import org.apache.el.util.Validation;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Espin
 */
@ManagedBean(name = "crudView")
@ViewScoped
public class CrudView {

    private UserTO usuarioTO;
    private RecipesTO recipesTO;

    public CrudView() {
        this.usuarioTO = new UserTO();
        this.recipesTO = new RecipesTO();
    }

    public void openNew() {
        this.usuarioTO = new UserTO();
    }

    public void openNewRecipes() {
        this.recipesTO = new RecipesTO();
    }

    public void save() {
        ServicesUser servicesUser = new ServicesUser();
        if (ValidationUtils.isUserValid(this.usuarioTO)) {
            if (servicesUser.insert(this.usuarioTO)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario agregado correctamente"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error al crear el usuario"));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error al crear el usuario"));
        }
        PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }

    public void delete(String id) {
        ServicesUser servicesUser = new ServicesUser();
        if (servicesUser.eliminar(id)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario eliminado correctamente"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error al intentar eliminar el usuario"));
        }
        PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }

    public UserTO getUsuarioTO() {
        return usuarioTO;
    }

    public void setUsuarioTO(UserTO usuarioTO) {
        this.usuarioTO = usuarioTO;
    }

    public RecipesTO getRecipesTO() {
        return recipesTO;
    }

    public void setRecipesTO(RecipesTO recipesTo) {
        this.recipesTO = recipesTo;
    }
}
