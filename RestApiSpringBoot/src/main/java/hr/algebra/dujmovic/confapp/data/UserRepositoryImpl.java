/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dujmovic.confapp.data;

import hr.algebra.dujmovic.confapp.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.stereotype.Repository;

/**
 *
 * @author matij
 */
@Repository
public class UserRepositoryImpl implements UserRepositoryCustom{

    @PersistenceContext
    EntityManager em;
    
    @Transactional
    @Override
    public void saveUserWithAuthorities(User user) {
        em.persist(user);
        em.createNativeQuery("INSERT INTO AUTHORITIES (USERNAME, AUTHORITY) VALUES ( ?1 , 'ROLE_USER')")
                .setParameter(1, user.getUsername())
                .executeUpdate();
    }
    
}
