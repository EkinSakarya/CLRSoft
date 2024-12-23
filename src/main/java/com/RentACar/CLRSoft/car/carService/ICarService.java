package com.RentACar.CLRSoft.car.carService;

import com.RentACar.CLRSoft.car.carRequest.CarAddRequest;
import com.RentACar.CLRSoft.car.carRequest.CarUpdateRequest;
import com.RentACar.CLRSoft.car.carResponse.CarResponse;
import com.RentACar.CLRSoft.car.carResponse.FindByIdCarResponse;
import com.RentACar.CLRSoft.car.entity.Car;

import java.util.ArrayList;
import java.util.List;

public interface ICarService
{
    public List<CarResponse> findAll();

    public FindByIdCarResponse findById(int id);

    public FindByIdCarResponse addCar(CarAddRequest carAddRequest);
    public Car updateCar(CarUpdateRequest carUpdateRequest);
    public void delete(Car car);
    public ArrayList<Car> activeCars();
    public void deActivateCar(Car car);
}
