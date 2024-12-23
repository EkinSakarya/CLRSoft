package com.RentACar.CLRSoft.car.carDal;

import com.RentACar.CLRSoft.car.entity.Car;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CarDal implements ICarDal
{

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public CarDal(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public List<Car> findAll() {
        Session session = entityManager.unwrap(Session.class);
        List<Car> cars = session.createQuery("from Car", Car.class).getResultList();
        return cars;
    }
    @Override
    public Car findById(int id) {
        Session session = entityManager.unwrap(Session.class);
        Car car = session.get(Car.class, id);
        return car;
    }

    @Override
    @Transactional
    public void add(Car car) {
        Session session = entityManager.unwrap(Session.class);
        session.save(car);
    }

    @Override
    @Transactional
    public void update(Car car) {
        Session session = entityManager.unwrap(Session.class);
        session.merge(car);
    }

    @Override
    public void delete(Car car) {
        Session session = entityManager.unwrap(Session.class);
        session.remove(car);
    }
}
