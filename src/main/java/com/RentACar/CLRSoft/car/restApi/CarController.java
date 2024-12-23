package com.RentACar.CLRSoft.car.restApi;

import com.RentACar.CLRSoft.car.carRequest.CarAddRequest;
import com.RentACar.CLRSoft.car.carRequest.CarUpdateRequest;
import com.RentACar.CLRSoft.car.carResponse.CarResponse;
import com.RentACar.CLRSoft.car.carResponse.FindByIdCarResponse;
import com.RentACar.CLRSoft.car.carService.ICarService;
import com.RentACar.CLRSoft.car.entity.Car;
import com.RentACar.CLRSoft.category.categoryService.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CarController {
    private ICarService carService;

    @Autowired
    public  CarController(ICarService carService,ICategoryService categoryService){
        this.carService=carService;
    }

    @GetMapping("/cars")
    public List<CarResponse> findAll(){
        return carService.findAll(); }

    @PostMapping("/getcarbyid/{id}")
    public FindByIdCarResponse CarfindById(@PathVariable int id)
    {
        return carService.findById(id);
    }

    @PostMapping("/addcar")
    public FindByIdCarResponse addCar(@RequestBody CarAddRequest carAddRequest) {

        return carService.addCar(carAddRequest);
    }
    @PutMapping("/updatecar/{id}")
    public Car addCar(@RequestBody CarUpdateRequest carUpdateRequest, @PathVariable int id) {

        carUpdateRequest.setId(id);
        return carService.updateCar(carUpdateRequest);
    }
    @GetMapping("/activeCars")
    public ArrayList<Car> activeCars()
    {
        return carService.activeCars();
    }


}
