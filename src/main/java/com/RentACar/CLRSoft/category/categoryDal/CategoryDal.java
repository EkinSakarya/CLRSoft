package com.RentACar.CLRSoft.category.categoryDal;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.RentACar.CLRSoft.category.entity.Category;

@Repository
public class CategoryDal implements ICategoryDal {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public CategoryDal(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public List<Category> findAll() {
        Session session = entityManager.unwrap(Session.class);
        List<Category> categories = session.createQuery("from Category", Category.class).getResultList();
        return categories;
    }

    @Override
    @Transactional
    public void add(Category category) {
        Session session = entityManager.unwrap(Session.class);
        session.save(category);
    }

    @Override
    @Transactional
    public void update(Category category) {
        Session session = entityManager.unwrap(Session.class);
        session.merge(category);
    }

    @Override
    public void delete(Category category) {
        Session session = entityManager.unwrap(Session.class);
        session.remove(category);
    }

    @Override
    public Category findById(int id) {
        Session session = entityManager.unwrap(Session.class);
        Category category = session.get(Category.class, id);
        return category;
    }
}
