/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ulatina.demo.controller;

import edu.ulatina.demo.model.UserTO;
import edu.ulatina.demo.service.ServicioUsuario;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Espin
 */
@ManagedBean(name = "crudView")
@ViewScoped
public class CrudView {

    private UserTO usuarioTO;

    public CrudView() {
        this.usuarioTO = new UserTO();
    }

    public void openNew() {
        this.usuarioTO = new UserTO();
    }

    public void save() {
        ServicioUsuario servicioUsuario = new ServicioUsuario();
//        if (servicioUsuario.insert(this.usuarioTO)) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User Added Success"));
//            
//        }else{
//             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User Added Fail"));
//        }
        PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }

    public void delete() {

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }

    public UserTO getUsuarioTO() {
        return usuarioTO;
    }

    public void setUsuarioTO(UserTO usuarioTO) {
        this.usuarioTO = usuarioTO;
    }

}
