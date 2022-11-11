/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import com.mysql.cj.util.StringUtils;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import edu.ulatina.demo.model.UserTO;
import java.util.Objects;
import java.util.regex.Matcher;

/**
 *
 * @author Espin
 */
public class ValidationUtils {
    
    private static final String PATTERN_EMAIL = "^(.+)@(.+)$";
        
    public static boolean isUserValid (UserTO userTo){
        if(Objects.isNull(userTo)){
            return false;
        }else{
            String username = userTo.getUsername();
            String lastName = userTo.getLastname();
            String password =  userTo.getPassword();
            String email = userTo.getEmail();
            
            if(StringUtils.isNullOrEmpty(username)){
                return false;
            }   
            if(StringUtils.isNullOrEmpty(lastName)){
                return false;
            }
            if(StringUtils.isNullOrEmpty(password)){
                return false;
            } 
            if(!email.matches(PATTERN_EMAIL)){
                return false;       
            }
            
            return true;
        }
    }
    
    
    
    
   
}
