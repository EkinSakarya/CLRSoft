package com.RentACar.CLRSoft.rental.rentalDal;

import com.RentACar.CLRSoft.rental.entity.Rental;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IRentalDal  {

    List<Rental> findAll();

    void add(Rental rental);

    void update(Rental rental);

    void delete(Rental rental);

    Rental findById(int id);
}
