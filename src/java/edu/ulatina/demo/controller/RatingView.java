/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ulatina.demo.controller;

import edu.ulatina.demo.service.ServicesRecipes;
import edu.ulatina.demo.service.ServicesUser;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RateEvent;

/**
 *
 * @author Espin
 */
@ManagedBean(name = "ratingView")
@RequestScoped
public class RatingView {

    private Integer rating1;
    private Integer rating2;
    private Integer rating3 = 4;

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
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al calificar la receta", "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void oncancel() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Raking cancelado", null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public Integer getRating1() {
        return rating1;
    }

    public void setRating1(Integer rating1) {
        this.rating1 = rating1;
    }

    public Integer getRating2() {
        return rating2;
    }

    public void setRating2(Integer rating2) {
        this.rating2 = rating2;
    }

    public Integer getRating3() {
        return rating3;
    }

    public void setRating3(Integer rating3) {
        this.rating3 = rating3;
    }
}
