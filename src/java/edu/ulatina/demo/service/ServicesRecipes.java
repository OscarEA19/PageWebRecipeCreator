/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ulatina.demo.service;

import edu.ulatina.demo.model.IngredienteTO;
import edu.ulatina.demo.model.PreparacionTO;
import edu.ulatina.demo.model.RecipesTO;
import edu.ulatina.demo.model.UserTO;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author usuario
 */
public class ServicesRecipes extends Services {

    public List<RecipesTO> listAllRecipes(){
        List<RecipesTO> listRecipes = new ArrayList<>();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            connection();
            String sql = "Select idreceta, titulo, description, img, username FROM recetas where status = true";
            pstm = conn.prepareStatement(sql);

            rs = pstm.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("idreceta");
                String titulo = rs.getString("titulo");
                String description = rs.getString("description");
                String img = rs.getString("img");
                String username = rs.getString("username");
                List<IngredienteTO> ingredienteTOs = listIngredientesByID(id);
                List<PreparacionTO> preparacionTOs = listPreparacionByID(id);
                listRecipes.add(new RecipesTO(id,titulo,description,username,img,ingredienteTOs,preparacionTOs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResultSet(rs);
            closeStatement(pstm);
            desconect();
        }
        return listRecipes;
    }
    
    public List<RecipesTO> listRecipesByUser(UserTO userTO){
        List<RecipesTO> listRecipes = new ArrayList<>();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String usernameInput = userTO.getUsername();

        try {
            connection();
            String sql = "Select idreceta, titulo, description, img, username FROM recetas where username = ? and status = true";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, usernameInput);

            rs = pstm.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("idreceta");
                String titulo = rs.getString("titulo");
                String description = rs.getString("description");
                String img = rs.getString("img");
                String username = rs.getString("username");
                listRecipes.add(new RecipesTO(id,titulo,description,username,img));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResultSet(rs);
            closeStatement(pstm);
            desconect();
        }
        return listRecipes;
    }
    
    public Integer recipeByUsernameAndTitle(String username, String title){
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        try {
            connection();
            String sql = "Select idreceta FROM recetas where username = ? and titulo = ? ";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, username);
            pstm.setString(2, title);
           
            rs = pstm.executeQuery();
            if (rs.next()) {
                Integer id = rs.getInt("idreceta");
               return id;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResultSet(rs);
            closeStatement(pstm);
            desconect();
        }
        return null;
    }
    
    public RecipesTO listRecipesByID(Integer idInput){
        List<RecipesTO> listRecipes = new ArrayList<>();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            connection();
            String sql = "Select id, titulo, description, img, username FROM recetas where id = ? ";
            pstm = conn.prepareStatement(sql);
            //pstm.setInteger(1, idInput);

            rs = pstm.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String description = rs.getString("description");
                String img = rs.getString("img");
                String username = rs.getString("username");
                listRecipes.add(new RecipesTO(id,titulo,description,username,img));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResultSet(rs);
            closeStatement(pstm);
            desconect();
        }
        return null;
    }
    
    public List<IngredienteTO> listIngredientesByID(Integer idInput){
        List<IngredienteTO> ingredienteTOs = new ArrayList<>();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            connection();
            String sql = "Select id, ingrediente, idReceta FROM ingredientes where idReceta = ? ";
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, idInput);

            rs = pstm.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String ingrediente = rs.getString("ingrediente");
                Integer idReceta = rs.getInt("idReceta");
                ingredienteTOs.add(new IngredienteTO(id,ingrediente,idReceta));
            }
            return ingredienteTOs;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResultSet(rs);
            closeStatement(pstm);
            desconect();
        }
        return null;
    }
    
    public List<PreparacionTO> listPreparacionByID(Integer idInput){
        List<PreparacionTO> preparacionTOs = new ArrayList<>();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            connection();
            String sql = "Select id, paso, idReceta FROM preparacion where idReceta = ? ";
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, idInput);

            rs = pstm.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String paso = rs.getString("paso");
                Integer idReceta = rs.getInt("idReceta");
                preparacionTOs.add(new PreparacionTO(id,paso,idReceta));
            }
            return preparacionTOs;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResultSet(rs);
            closeStatement(pstm);
            desconect();
        }
        return null;
    }
    
    public boolean insert(RecipesTO recipesTO) {

        String titulo = recipesTO.getTitle();
        String description = recipesTO.getDescription();
        String img = recipesTO.getImgPath();
        String usename = recipesTO.getUsername();
        
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            connection();
            String sql = "insert into recetas (titulo, description, img, username,status,fec_ult_modi) values (?, ?, ?, ?, true,CURRENT_TIMESTAMP())";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, titulo);
            pstm.setString(2, description);
            pstm.setString(3, img);
            pstm.setString(4, usename);
            
            pstm.execute();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            closeResultSet(rs);
            closeStatement(pstm);
            desconect();
        }
    }
    public boolean insertIngredientes(IngredienteTO ingredienteTO) {

        String ingrediente = ingredienteTO.getIngrediente();
        String titulo = ingredienteTO.getTitulo();
        String username = ingredienteTO.getUsername();
        
        
        PreparedStatement pstm = null;
        ResultSet rs = null;

        //TODO: first call get id recipe
        Integer idReceta = recipeByUsernameAndTitle(username, titulo);
        
        try {
            connection();
            String sql = "insert into ingredientes (ingrediente, idReceta) values (?, ?)";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, ingrediente);
            pstm.setInt(2, idReceta);
            
            pstm.execute();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            closeResultSet(rs);
            closeStatement(pstm);
            desconect();
        }
    }
    
    public boolean insertPreparaciones(PreparacionTO preparacionTO) {

        String paso = preparacionTO.getPaso();
        String titulo = preparacionTO.getTitulo();
        String username = preparacionTO.getUsername();
        
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        //TODO: first call get id recipe
        Integer idReceta = recipeByUsernameAndTitle(username, titulo);

        try {
            connection();
            String sql = "insert into preparacion (paso,idreceta) values (?, ?)";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, paso);
            pstm.setInt(2, idReceta);            
            pstm.execute();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            closeResultSet(rs);
            closeStatement(pstm);
            desconect();
        }
    }
    
    public boolean eliminar(Integer id) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        try {
            connection();
            String sql = "update recetas set status = 0, fec_ult_modi = CURRENT_TIMESTAMP() where idReceta = ?";
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.execute();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            closeResultSet(rs);
            closeStatement(pstm);
            desconect();
        }
    }
}
