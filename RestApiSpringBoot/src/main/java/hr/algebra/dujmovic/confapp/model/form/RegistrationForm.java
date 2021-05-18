/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dujmovic.confapp.model.form;

import hr.algebra.dujmovic.confapp.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author matij
 */
public class RegistrationForm {
    
    private String username;
    private String password;

    public User toUser(PasswordEncoder passwordEncoder){
        return new User(username, passwordEncoder.encode(password));
    }
    
    public RegistrationForm() {
    }

    public RegistrationForm(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
        
    
}
