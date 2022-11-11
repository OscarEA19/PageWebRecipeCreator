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
@ManagedBean(name = "recipesController")
@SessionScoped
public class RecipesController implements Serializable {

    
    private RecipesTO recipesTO = new RecipesTO();
    private UserTO userTO = new UserTO();
    private List<RecipesTO> recipesList = new ArrayList<>();
    public ServicesRecipes servicesRecipes = new ServicesRecipes();



    public RecipesController() {
    }
    
     public RecipesController(UserTO userTO) {
         this.userTO =  userTO;
    }
    
    
    public void showAllRecipes() {
        this.recipesList =  servicesRecipes.listAllRecipes();
        
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

    public UserTO getUserTO() {
        return userTO;
    }

    public void setUserTO(UserTO userTO) {
        this.userTO = userTO;
    }
    
    
}
