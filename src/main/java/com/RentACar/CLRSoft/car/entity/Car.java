package com.RentACar.CLRSoft.car.entity;

import com.RentACar.CLRSoft.category.entity.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "car")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //
    @Column(name = "Id")
    private int id;

    @Column(name = "Brand")
    private String brand;

    @Column(name = "Model")
    private String model;

    @Column(name = "ProductionYear")
    private int productionYear;

    @Column(name = "DailyRentalPrice")
    private double dailyRentalPrice;

    @Column(name = "IsAvailable")
    private boolean isAvailable;

    @Column(name = "Mileage")
    private int mileage;

    @Column(name = "FuelType")
    private String fuelType;

    @Column(name = "TransmissionType")
    private String transmissionType;

    @Column(name = "Color")
    private String color;

    @Column(name = "LicensePlate")
    private String licensePlate;

    @ManyToOne
    @JoinColumn(name = "CategoryId", referencedColumnName = "id")
    private Category category;

    public Car(String brand, String model, int productionYear, double dailyRentalPrice, boolean isAvailable, int mileage, String fuelType, String transmissionType, String color, String licensePlate, Category category) {
        this.brand = brand;
        this.model = model;
        this.productionYear = productionYear;
        this.dailyRentalPrice = dailyRentalPrice;
        this.isAvailable = isAvailable;
        this.mileage = mileage;
        this.fuelType = fuelType;
        this.transmissionType = transmissionType;
        this.color = color;
        this.licensePlate = licensePlate;
        this.category=category;
    }
}
