package com.yutian.multi_task_board_backend.dao;

import com.yutian.multi_task_board_backend.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    private final EntityManager entityManager;

    public UserDaoImpl (EntityManager entityManager){
        this.entityManager=entityManager;

    }


    @Override
    public User findByUserName(String userName) {
        TypedQuery<User> theQuery=entityManager.createQuery("from User where userName=:uName and enabled=true", User.class);
        theQuery.setParameter("uName",userName);

        User result=null;
        try{
            result=theQuery.getSingleResult();
        } catch(Exception e){
            result=null;
        }
        return result;

    }
}
