package com.yutian.multi_task_board_backend.dao;


import com.yutian.multi_task_board_backend.entity.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements RoleDao{

    private final EntityManager entityManager;

    public RoleDaoImpl(EntityManager entityManager){
        this.entityManager=entityManager;
    }


    @Override
    public Role findByRoleName(String roleName) {
        TypedQuery<Role> theQuery = entityManager.createQuery("from Role where name=:rName",Role.class);
        theQuery.setParameter("rName",roleName);

        Role result=null;
        try{
            result=theQuery.getSingleResult();
        } catch(NoResultException e){
            result=null;
        }
        return result;
    }
}
