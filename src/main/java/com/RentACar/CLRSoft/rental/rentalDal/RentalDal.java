package com.RentACar.CLRSoft.rental.rentalDal;

import com.RentACar.CLRSoft.rental.entity.Rental;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class RentalDal implements IRentalDal {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public RentalDal(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public List<Rental> findAll() {
        Session session = entityManager.unwrap(Session.class);
        List<Rental> categories = session.createQuery("from Rental", Rental.class).getResultList();
        return categories;
    }


    @Override
    @Transactional
    public void add(Rental rental) {
        Session session = entityManager.unwrap(Session.class);
        session.save(rental);
    }

    @Override
    @Transactional
    public void update(Rental rental) {
        Session session = entityManager.unwrap(Session.class);
        session.merge(rental);
    }

    @Override
    public void delete(Rental rental) {
        Session session = entityManager.unwrap(Session.class);
        session.remove(rental);
    }

    @Override
    public Rental findById(int id) {
        Session session = entityManager.unwrap(Session.class);
        Rental rental = session.get(Rental.class, id);
        return rental;
    }
}
