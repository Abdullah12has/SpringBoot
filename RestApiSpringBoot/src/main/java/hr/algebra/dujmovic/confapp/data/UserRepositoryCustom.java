/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dujmovic.confapp.data;

import hr.algebra.dujmovic.confapp.model.User;

/**
 *
 * @author matij
 */
public interface UserRepositoryCustom {
    void saveUserWithAuthorities(User user);
}
