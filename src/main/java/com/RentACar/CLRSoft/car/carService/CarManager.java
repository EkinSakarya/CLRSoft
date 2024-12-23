package com.RentACar.CLRSoft.car.carService;

import com.RentACar.CLRSoft.ClrSoftApplication;
import com.RentACar.CLRSoft.car.carDal.ICarDal;
import com.RentACar.CLRSoft.car.carRequest.CarAddRequest;
import com.RentACar.CLRSoft.car.carRequest.CarUpdateRequest;
import com.RentACar.CLRSoft.car.carResponse.CarResponse;
import com.RentACar.CLRSoft.car.carResponse.FindByIdCarResponse;
import com.RentACar.CLRSoft.car.entity.Car;
import com.RentACar.CLRSoft.category.categoryDal.ICategoryDal;
import com.RentACar.CLRSoft.category.entity.Category;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarManager implements ICarService {

    private final ICarDal carDal;
    private final ModelMapper modelMapper;
    private final ICategoryDal categoryDal;

    @Autowired
    public CarManager(ICarDal carDal, ModelMapper modelMapper,ICategoryDal categoryDal) {
        this.carDal = carDal;
        this.modelMapper = modelMapper;
        this.categoryDal=categoryDal;
    }

    @Override
    public List<CarResponse> findAll() {
        List<Car> cars = carDal.findAll();

        return cars.stream().map(
                        car -> modelMapper.map(car, CarResponse.class)).
                collect(Collectors.toList());
    }

    @Override
    public FindByIdCarResponse findById(int id) {
        Car car = carDal.findById(id);
        if (car == null) {
            return null;
        }
        return modelMapper.map(car, FindByIdCarResponse.class);
    }

    @Override
    public FindByIdCarResponse addCar(CarAddRequest carAddRequest) {
        if(ClrSoftApplication.userPermission==0)
        {
            return  null;
        }
        Car newCar = modelMapper.map(carAddRequest,Car.class);

        Category category = categoryDal.findById(carAddRequest.getCategoryId());

        newCar.setCategory(category);
        newCar.setAvailable(true);
        this.carDal.add(newCar);

        return this.findById(newCar.getId());
    }

    @Override
    public Car updateCar(CarUpdateRequest carUpdateRequest) {
        if(ClrSoftApplication.userPermission==0)
        {
            return  null;
        }

        Car oldCar =  carDal.findById(carUpdateRequest.getId());

        if (oldCar == null) {
            return null;
        }
        Car updatedcar =modelMapper.map(carUpdateRequest,Car.class);


        this.carDal.update(updatedcar);
        return updatedcar;
    }

    @Override
    public void delete(Car car) {
        carDal.delete(car);
    }
    public ArrayList<Car> activeCars()
    {
        if(ClrSoftApplication.userPermission==0)
        {
            return  null;
        }
        List<Car> cars=carDal.findAll();
        ArrayList<Car> activeCars=new ArrayList<Car>() ;
        for(Car car:cars)
        {
            if(car.isAvailable())
            {
                activeCars.add(car);
            }
        }
        return activeCars;
    }

    @Override
    public void deActivateCar(Car car) {
        car.setAvailable(false);
        carDal.update(car);
    }
}
