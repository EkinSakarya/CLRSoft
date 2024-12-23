package com.RentACar.CLRSoft.user.userDal;

import com.RentACar.CLRSoft.user.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository
public class UserDal implements IUserDal {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public UserDal(EntityManager entityManager)
    {
        this.entityManager=entityManager;
    }

    @Override
    @Transactional
    public List<User> findAll() {
        Session session = entityManager.unwrap(Session.class);
        List<User> users = session.createQuery("from User", User.class).getResultList();
        return users;
    }

    @Override
    @Transactional
    public void add(User user) {
        Session session=entityManager.unwrap(Session.class);
        session.save(user);

    }

    @Override
    @Transactional
    public void update(User user) {
        Session session=entityManager.unwrap(Session.class);
        session.merge(user);
    }

    @Override
    @Transactional
    public void delete(User user) {
        Session session=entityManager.unwrap(Session.class);
        session.remove(user);
    }

    @Override
    public User findById(int id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(User.class, id);
    }
}
