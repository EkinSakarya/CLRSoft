package com.RentACar.CLRSoft.rental.rentalService;

import com.RentACar.CLRSoft.ClrSoftApplication;
import com.RentACar.CLRSoft.car.carDal.ICarDal;
import com.RentACar.CLRSoft.car.carService.CarManager;
import com.RentACar.CLRSoft.car.entity.Car;
import com.RentACar.CLRSoft.customer.customerDal.ICustomerDal;
import com.RentACar.CLRSoft.customer.customerService.CustomerManager;
import com.RentACar.CLRSoft.customer.entity.Customer;
import com.RentACar.CLRSoft.invoice.invoiceService.IInvoiceService;
import com.RentACar.CLRSoft.rental.entity.Rental;
import com.RentACar.CLRSoft.rental.rentalDal.IRentalDal;
import com.RentACar.CLRSoft.rental.rentalRequest.RentalAddRequest;
import com.RentACar.CLRSoft.rental.rentalRequest.RentalUpdateRequest;
import com.RentACar.CLRSoft.rental.rentalResponse.FindByIdRentalResponse;
import com.RentACar.CLRSoft.rental.rentalResponse.RentalResponse;
import com.RentACar.CLRSoft.user.entity.User;
import com.RentACar.CLRSoft.user.userDal.IUserDal;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentalManager implements IRentalService{

    private final IRentalDal rentalDal;
    private final ICarDal carDal;
    private final ICustomerDal customerDal;
    private final IUserDal userDal;
    private final IInvoiceService invoiceService;
    private final ModelMapper modelMapper;

    private final CustomerManager customerManager;
    private final CarManager carManager;

    public RentalManager(IRentalDal rentalDal,ModelMapper modelMapper,ICarDal carDal,ICustomerDal customerDal,IUserDal userDal,IInvoiceService invoiceService,CarManager carManager,CustomerManager customerManager)
    {
        this.rentalDal=rentalDal;
        this.modelMapper=modelMapper;
        this.carDal=carDal;
        this.customerDal=customerDal;
        this.userDal=userDal;
        this.invoiceService=invoiceService;
        this.carManager=carManager;
        this.customerManager=customerManager;
    }

    @Override
    public List<RentalResponse> findAll() {
        List<Rental> rentals = rentalDal.findAll();

        return rentals.stream().map(
                        rental -> modelMapper.map(rental, RentalResponse.class)).
                collect(Collectors.toList());
    }

    @Override
    public FindByIdRentalResponse rentalAdd(RentalAddRequest rentalAddRequest, int howManyDays) {

        double totalprice=0;

        Calendar calendar = Calendar.getInstance();
        Rental newRental = modelMapper.map(rentalAddRequest,Rental.class);
        Customer customer=new Customer();
        Car car =car = carDal.findById(rentalAddRequest.getCarId());

        if(!car.isAvailable()) {
            return null;
        }


        User user = userDal.findById(rentalAddRequest.getUserId());
        calendar.setTime(newRental.getRentalStartDate());
        calendar.add(Calendar.DAY_OF_MONTH, howManyDays);
        Date rentalEndDate =calendar.getTime();
        if(rentalAddRequest.getCustomerId()!=0)
        {
            customer = customerDal.findById(rentalAddRequest.getCustomerId());
            newRental.setCustomer(customer);
        }else {
            newRental.setCustomer(null);
        }

        newRental.setCar(car);
        newRental.setUser(user);
        newRental.setRentalEndDate(rentalEndDate);
        if(rentalAddRequest.getCustomerId()!=0) {
           totalprice= car.getDailyRentalPrice() * howManyDays;
        }else
        {
            totalprice=(car.getDailyRentalPrice()-((car.getDailyRentalPrice()*ClrSoftApplication.discount)/100))*howManyDays;
        }

        newRental.setTotalPrice(totalprice);

        this.rentalDal.add(newRental);
        invoiceService.addInvoice(rentalDal.findById(newRental.getId()));
        carManager.deActivateCar(car);

        if(rentalAddRequest.getCustomerId()!=0){
            customerManager.customerHistoryUpdate(customer,car);
        }
        return this.findById(newRental.getId());
    }

    @Override
    public Rental rentalUpdate(RentalUpdateRequest rentalUpdateRequest,int howManyDays) {

        if (ClrSoftApplication.userPermission ==0 ) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();

        Rental oldRental =  rentalDal.findById(rentalUpdateRequest.getId());

        if (oldRental == null ) {
            return null;
        }
        Rental updatedRental =modelMapper.map(rentalUpdateRequest,Rental.class);

        Car car = carDal.findById(rentalUpdateRequest.getCarId());
        User user = userDal.findById(rentalUpdateRequest.getUserId());
        Customer customer = customerDal.findById(rentalUpdateRequest.getCustomerId());

        calendar.setTime(rentalUpdateRequest.getRentalStartDate());
        calendar.add(Calendar.DAY_OF_MONTH, howManyDays);
        Date newRentalEndDate =calendar.getTime();

        updatedRental.setCustomer(customer);
        updatedRental.setCar(car);
        updatedRental.setUser(user);
        updatedRental.setRentalEndDate(newRentalEndDate);
        updatedRental.setTotalPrice(car.getDailyRentalPrice()*howManyDays);

        this.rentalDal.update(updatedRental);

        return updatedRental;
    }

    @Override
    public void delete(Rental rental) {
        rentalDal.delete(rental);
    }

    @Override
    public FindByIdRentalResponse findById(int id) {

        Rental rental = rentalDal.findById(id);
        if (rental == null) {
            return null;
        }
        return modelMapper.map(rental, FindByIdRentalResponse.class);
    }
    @Override
    public List<Rental> findByCurrentUserId(int id)
    {
        List<Rental> rentals= rentalDal.findAll();
        ArrayList<Rental> currentUserRentals=new ArrayList<Rental>();
        if(ClrSoftApplication.userPermission==1)
        {
            for(Rental rental :rentals)
            {
                if(rental.getUser().getId()==id)
                {
                    currentUserRentals.add(rental);
                }
            }
        }
        else
        {
            for(Rental rental :rentals)
            {
                if(rental.getCustomer().getId()==id)
                {
                    currentUserRentals.add(rental);
                }
            }
        }
        return currentUserRentals;
    }

    @Override
    public FindByIdRentalResponse rentalAddCustomer(RentalAddRequest rentalAddRequest, int howManyDays) {
        double totalprice=0;

        Calendar calendar = Calendar.getInstance();
        Rental newRental = modelMapper.map(rentalAddRequest,Rental.class);
        Customer  customer = customerDal.findById(rentalAddRequest.getCustomerId());
        Car car =car = carDal.findById(rentalAddRequest.getCarId());

        if(!car.isAvailable()) {
            return null;
        }

        calendar.setTime(newRental.getRentalStartDate());
        calendar.add(Calendar.DAY_OF_MONTH, howManyDays);
        Date rentalEndDate =calendar.getTime();

        newRental.setCar(car);
        newRental.setUser(null);
        newRental.setRentalEndDate(rentalEndDate);
        newRental.setCustomer(customer);


        totalprice= car.getDailyRentalPrice() * howManyDays;

        newRental.setTotalPrice(totalprice);

        this.rentalDal.add(newRental);
        invoiceService.addInvoice(rentalDal.findById(newRental.getId()));
        carManager.deActivateCar(car);
        customerManager.customerHistoryUpdate(customer,car);

        return this.findById(newRental.getId());
    }
}
