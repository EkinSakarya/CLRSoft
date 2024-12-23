package com.RentACar.CLRSoft.invoice.invoiceDal;

import com.RentACar.CLRSoft.invoice.entity.Invoice;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class InvoiceDal implements IInvoiceDal{

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public InvoiceDal(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public List<Invoice> findAll() {
        Session session = entityManager.unwrap(Session.class);
        List<Invoice> invoices = session.createQuery("from Invoice", Invoice.class).getResultList();
        return invoices;
    }


    @Override
    @Transactional
    public void add(Invoice invoice) {
        Session session =entityManager.unwrap(Session.class);
        session.save(invoice);
    }

    @Override
    @Transactional
    public void update(Invoice invoice) {
        Session session =entityManager.unwrap(Session.class);
        session.merge(invoice);
    }

    @Override
    @Transactional
    public void delete(Invoice invoice) {
        Session session =entityManager.unwrap(Session.class);
        session.remove(invoice);
    }
    @Override
    public Invoice findById(int id) {
        Session session = entityManager.unwrap(Session.class);
        Invoice invoice = session.get(Invoice.class, id);
        return invoice;
    }
}
