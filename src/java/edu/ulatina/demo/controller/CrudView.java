/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ulatina.demo.controller;

import Utils.ValidationUtils;
import edu.ulatina.demo.model.RecipesTO;
import edu.ulatina.demo.model.UserTO;
import edu.ulatina.demo.service.ServicesUser;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;

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

    public void llenarUser(Integer id, String username, String lastname, String email) {
        this.usuarioTO.setId(id);
        this.usuarioTO.setUsername(username);
        this.usuarioTO.setLastname(lastname);
        this.usuarioTO.setEmail(email);
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

    public void update(Integer id, String username, String lastname, String email) {
        UserTO userTO = new UserTO(id, username, lastname, email);
        ServicesUser servicesUser = new ServicesUser();
        if (servicesUser.updateUsuario(userTO)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario actualizado correctamente"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error al intentar actualizar el usuario"));
        }
        PrimeFaces.current().executeScript("PF('manageUser').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }

    public void showUpdate() {
        PrimeFaces.current().executeScript("PF('manageUser').show()");
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

    public void onRowEdit(RowEditEvent<UserTO> event) {
        Integer id = event.getObject().getId();
        String nombre = event.getObject().getUsername();
        String apellido = event.getObject().getLastname();
        String correo = event.getObject().getEmail();
        UserTO userTO = new UserTO(id, nombre, apellido, correo);

        ServicesUser servicesUser = new ServicesUser();

        if (servicesUser.updateUsuario(userTO)) {
            FacesMessage msg = new FacesMessage("Usuario editado correctamente", null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            FacesMessage msg = new FacesMessage("Error al editar el usuario", null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }

    public void onRowCancel(RowEditEvent<UserTO> event) {
        FacesMessage msg = new FacesMessage("Edicion cancelada",null);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
