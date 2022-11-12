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
            String sql = "Select id, titulo, description, img, username FROM recetas";
            pstm = conn.prepareStatement(sql);

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
        return listRecipes;
    }
    
    public List<RecipesTO> listRecipesByUser(UserTO userTO){
        List<RecipesTO> listRecipes = new ArrayList<>();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String usernameInput = userTO.getUsername();

        try {
            connection();
            String sql = "Select id, titulo, description, img, username FROM recetas where username = ? ";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, usernameInput);

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
        return listRecipes;
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
            String sql = "insert into recetas (titulo, description, img, username) values (?, ?, ?, ?)";
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
        String username = ingredienteTO.getUsername();
        String titulo = ingredienteTO.getTitulo();
        
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            connection();
            String sql = "insert into ingredientes (ingrediente, username, titulo) values (?, ?, ?)";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, ingrediente);
            pstm.setString(2, username);
            pstm.setString(3, titulo);
            
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

        try {
            connection();
            String sql = "insert into preparacion (paso, titulo, username) values (?, ?, ?";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, paso);
            pstm.setString(2, titulo);
            pstm.setString(3, username);
            
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
