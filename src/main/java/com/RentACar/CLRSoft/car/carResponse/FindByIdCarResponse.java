package com.RentACar.CLRSoft.car.carResponse;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindByIdCarResponse {

    private int id;

    private String brand;

    private String model;

    private int productionYear;

    private double dailyRentalPrice;

    private boolean isAvailable;

    private int mileage;

    private String fuelType;

    private String transmissionType;

    private String color;

    private String licensePlate;

    private int categoryId ;
}
