/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ulatina.demo.service;

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
public class ServicioUsuario extends Servicio {

    public UserTO validarDB(String username, String password) {
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            connection();
            String sql = "Select id, username, lastname, email, fec_register FROM user where username = ? and password = ?";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, username);
            pstm.setString(2, password);
            rs = pstm.executeQuery();
             if (rs.next()) {
                Integer id = rs.getInt("id");
                String nombre = rs.getString("username");
                String apellido = rs.getString("lastname");
                String correo = rs.getString("email");
                return new UserTO(id, nombre, apellido, correo);
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

    public List<UserTO> listUser() {
        List<UserTO> listUser = new ArrayList<>();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            connection();
            String sql = "Select id, username, lastname, email, fec_Register FROM user";
            pstm = conn.prepareStatement(sql);

            rs = pstm.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String username = rs.getString("username");
                String lastname = rs.getString("lastname");
                String email = rs.getString("email");
                Date fechaRegistro = rs.getDate("fec_register");
                listUser.add(new UserTO(id, username, lastname, email, fechaRegistro));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResultSet(rs);
            closeStatement(pstm);
            desconect();
        }
        return listUser;
    }

//    public boolean insert(UserTO usuarioTO) {
//
//        String nombre = usuarioTO.getNombre();
//        String apellido = usuarioTO.getApellido();
//        String email = usuarioTO.getCorreo();
//        String clave =  usuarioTO.getClave();
//        
//        PreparedStatement pstm = null;
//        ResultSet rs = null;
//
//        try {
//            connection();
//            String sql = "insert into user (nombre, apellido, correo, clave ) values (?, ?, ?, ?)";
//            pstm = conn.prepareStatement(sql);
//            pstm.setString(1, nombre);
//            pstm.setString(2, apellido);
//            pstm.setString(3, email);
//            pstm.setString(4, clave);
//            
//            pstm.execute();
//            
//           return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        } finally {
//            closeResultSet(rs);
//            closeStatement(pstm);
//            desconect();
//        }
//    }
}
