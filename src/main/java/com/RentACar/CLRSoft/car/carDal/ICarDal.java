package com.RentACar.CLRSoft.car.carDal;

import com.RentACar.CLRSoft.car.entity.Car;

import java.util.List;

public interface ICarDal {
    List<Car> findAll();

    void add(Car car);

    void update(Car car);

    void delete(Car car);

    public Car findById(int id);
}
