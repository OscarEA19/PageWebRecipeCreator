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

    public List<RecipesTO> listAllRecipes() {
        List<RecipesTO> listRecipes = new ArrayList<>();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            connection();
            String sql = "Select idreceta, titulo, description, img, username, likes FROM proyectorecetas.recetas,proyectorecetas.user where recetas.status = true and recetas.iduser = user.id";
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
                Integer likes = rs.getInt("likes");
                listRecipes.add(new RecipesTO(id, titulo, description, username, img, ingredienteTOs, preparacionTOs, likes));
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

    public List<RecipesTO> listRecipesByUser(UserTO userTO) {
        List<RecipesTO> listRecipes = new ArrayList<>();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Integer idUser = userTO.getId();

        try {
            connection();
            String sql = "Select idreceta, titulo, description, img, username,likes FROM proyectorecetas.recetas,proyectorecetas.user where idUser = ? and recetas.status = true and recetas.iduser = user.id";
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, idUser);

            rs = pstm.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("idreceta");
                String titulo = rs.getString("titulo");
                String description = rs.getString("description");
                String img = rs.getString("img");
                String username = rs.getString("username");
                List<IngredienteTO> ingredienteTOs = listIngredientesByID(id);
                List<PreparacionTO> preparacionTOs = listPreparacionByID(id);
                Integer likes = rs.getInt("likes");
                listRecipes.add(new RecipesTO(id, titulo, description, username, img, ingredienteTOs, preparacionTOs, likes));
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

    public Integer recipeByUsernameAndTitle(Integer idUser, String title) {
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            connection();
            String sql = "Select idreceta FROM recetas where idUser = ? and titulo = ? ";
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, idUser);
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

    public RecipesTO listRecipesByID(Integer idInput) {
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
                listRecipes.add(new RecipesTO(id, titulo, description, username, img));
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

    public List<IngredienteTO> listIngredientesByID(Integer idInput) {
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
                ingredienteTOs.add(new IngredienteTO(id, ingrediente, idReceta));
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

    public List<PreparacionTO> listPreparacionByID(Integer idInput) {
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
                preparacionTOs.add(new PreparacionTO(id, paso, idReceta));
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
        Integer idUser = recipesTO.getIdUser();

        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            connection();
            String sql = "insert into recetas (titulo, description, img, idUser ,status,fec_ult_modi) values (?, ?, ?, ?, true,CURRENT_TIMESTAMP())";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, titulo);
            pstm.setString(2, description);
            pstm.setString(3, img);
            pstm.setInt(4, idUser);

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
        Integer idUser = ingredienteTO.getIdUser();
        String title = ingredienteTO.getTitulo();

        PreparedStatement pstm = null;
        ResultSet rs = null;

        //TODO: first call get id recipe
        Integer idReceta = recipeByUsernameAndTitle(idUser, title);

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
        Integer idUser = preparacionTO.getIdUser();
        String titulo = preparacionTO.getTitulo();

        PreparedStatement pstm = null;
        ResultSet rs = null;

        //TODO: first call get id recipe
        Integer idReceta = recipeByUsernameAndTitle(idUser, titulo);

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

    public boolean updateReceta(RecipesTO recipesTO) {

        Integer idReceta = recipesTO.getId();
        String titulo = recipesTO.getTitle();
        String description = recipesTO.getDescription();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            connection();
            String sql = "update recetas set titulo = ? , description = ? where idReceta = ?";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, titulo);
            pstm.setString(2, description);
            pstm.setInt(3, idReceta);

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

    public boolean updateIngrediente(RecipesTO recipesTO) {

        List<IngredienteTO> ingredienteTOs = recipesTO.getIngredientes();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            connection();
            for (IngredienteTO ingredienteTO : ingredienteTOs) {

                String ingredienteField = ingredienteTO.getIngrediente();
                Integer id = ingredienteTO.getId();
                Integer idReceta = ingredienteTO.getIdReceta();

                String sql = "update ingredientes set ingrediente = ? where id = ? and idReceta = ? ";
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, ingredienteField);
                pstm.setInt(2, id);
                pstm.setInt(3, idReceta);
                pstm.execute();
            }
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

    public boolean updatePreparacion(RecipesTO recipesTO) {

        List<PreparacionTO> preparacionTOs = recipesTO.getPreparaciones();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            connection();
            for (PreparacionTO preparacionTO : preparacionTOs) {

                String pasoField = preparacionTO.getPaso();
                Integer id = preparacionTO.getId();
                Integer idReceta = preparacionTO.getIdReceta();

                String sql = "update preparacion set paso = ? where id = ? and idReceta = ? ";
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, pasoField);
                pstm.setInt(2, id);
                pstm.setInt(3, idReceta);
                pstm.execute();
            }
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

    public Integer getCurrentValueRecipeRank(Integer idReceta) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            connection();
            String sql = "Select likes FROM recetas where idreceta = ? ";
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, idReceta);

            rs = pstm.executeQuery();
            if (rs.next()) {
                Integer likes = rs.getInt("likes");
                return likes;
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
    
    public boolean updateRecipeRank(Integer idReceta,Integer newValue) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            connection();
            String sql = "update recetas set likes = ? where idreceta = ?";
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, newValue);
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
}
