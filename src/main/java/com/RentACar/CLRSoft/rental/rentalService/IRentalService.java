package com.RentACar.CLRSoft.rental.rentalService;


import com.RentACar.CLRSoft.rental.entity.Rental;
import com.RentACar.CLRSoft.rental.rentalRequest.RentalAddRequest;
import com.RentACar.CLRSoft.rental.rentalRequest.RentalUpdateRequest;
import com.RentACar.CLRSoft.rental.rentalResponse.FindByIdRentalResponse;
import com.RentACar.CLRSoft.rental.rentalResponse.RentalResponse;

import java.util.List;

public interface IRentalService  {
    public List<RentalResponse> findAll();
    public FindByIdRentalResponse rentalAdd(RentalAddRequest rentalAddRequest, int howManyDays);
    public Rental rentalUpdate(RentalUpdateRequest rentalUpdateRequest,int howManyDays);
    public void delete(Rental rental);
    public FindByIdRentalResponse findById(int id);
    public List<Rental> findByCurrentUserId(int id);
    public FindByIdRentalResponse rentalAddCustomer(RentalAddRequest rentalAddRequest, int howManyDays);
}

