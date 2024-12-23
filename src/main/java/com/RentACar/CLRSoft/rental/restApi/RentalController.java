package com.RentACar.CLRSoft.rental.restApi;

import com.RentACar.CLRSoft.ClrSoftApplication;
import com.RentACar.CLRSoft.rental.entity.Rental;
import com.RentACar.CLRSoft.rental.rentalRequest.RentalAddRequest;
import com.RentACar.CLRSoft.rental.rentalRequest.RentalUpdateRequest;
import com.RentACar.CLRSoft.rental.rentalResponse.FindByIdRentalResponse;
import com.RentACar.CLRSoft.rental.rentalResponse.RentalResponse;
import com.RentACar.CLRSoft.rental.rentalService.IRentalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RentalController {

    private IRentalService rentalService;

    public RentalController(IRentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping("/getrentals")
    public List<RentalResponse> findAll() {
        return rentalService.findAll();
    }

    @PutMapping("/rentaladd/{howManydays}")
    public FindByIdRentalResponse rentalAdd(@RequestBody RentalAddRequest rentalAddRequest, @PathVariable int howManydays) {
        rentalAddRequest.setUserId(ClrSoftApplication.userOrCustomerId);
        return rentalService.rentalAdd(rentalAddRequest, howManydays);
    }

    @PutMapping("/rentalupdate/{id}-{howManydays}")
    public Rental rentalupdate(@RequestBody RentalUpdateRequest rentalUpdateRequest, @PathVariable int id, @PathVariable int howManydays) {
        rentalUpdateRequest.setId(id);
        return rentalService.rentalUpdate(rentalUpdateRequest, howManydays);
    }

    @PostMapping("/rentalgetbyid/{id}")
    public FindByIdRentalResponse findByIdRental(@PathVariable int id) {
        return rentalService.findById(id);
    }

    @PostMapping("/rentalcurretuser")
    public List<Rental> findByCurrentUserId() {
        return rentalService.findByCurrentUserId(ClrSoftApplication.userOrCustomerId);

    }

    @PutMapping("/rentalcurrentuserAdd/{howManydays}")
    public FindByIdRentalResponse rentalAddCurrentUser(@RequestBody RentalAddRequest rentalAddRequest, @PathVariable int howManydays) {

        if (ClrSoftApplication.userPermission == 1) {
            rentalAddRequest.setCustomerId(0);
            rentalAddRequest.setUserId(ClrSoftApplication.userOrCustomerId);
            return rentalService.rentalAdd(rentalAddRequest, howManydays);
        }
        else
        {
            rentalAddRequest.setUserId(0);
            rentalAddRequest.setCustomerId(ClrSoftApplication.userOrCustomerId);
            return rentalService.rentalAddCustomer(rentalAddRequest, howManydays);
        }
    }


}
