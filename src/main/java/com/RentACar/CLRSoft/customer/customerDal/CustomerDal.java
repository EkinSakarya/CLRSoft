package com.RentACar.CLRSoft.customer.customerDal;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import com.RentACar.CLRSoft.customer.entity.Customer;

@Repository
public  class CustomerDal implements ICustomerDal {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public CustomerDal(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public List<Customer> findAll() {
        Session session = entityManager.unwrap(Session.class);
        List<Customer> customers = session.createQuery("from Customer", Customer.class).getResultList();
        return customers;
    }

    @Override
    @Transactional
    public void add(Customer customer) {
        Session session =entityManager.unwrap(Session.class);
        session.save(customer);
    }

    @Override
    @Transactional
    public void update(Customer customer) {
        Session session =entityManager.unwrap(Session.class);
        session.merge(customer);
    }

    @Override
    @Transactional
    public void delete(Customer customer) {
        Session session =entityManager.unwrap(Session.class);
        session.remove(customer);
    }

    @Override
    public Customer findById(int id) {
        Session session = entityManager.unwrap(Session.class);
        Customer customer = session.get(Customer.class, id);
        return customer;
    }
}
