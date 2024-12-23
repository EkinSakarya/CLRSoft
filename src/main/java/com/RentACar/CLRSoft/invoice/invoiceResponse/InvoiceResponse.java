package com.RentACar.CLRSoft.invoice.invoiceResponse;


import com.RentACar.CLRSoft.customer.entity.Customer;
import com.RentACar.CLRSoft.rental.entity.Rental;
import com.RentACar.CLRSoft.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceResponse {

    private int id;

    private Rental rental;

    private Customer customer;

    private User user;

    private double totalPrice;

    private double taxAmount;

    private Date issueDate;

    private String invoiceStatus;

    private Date paymentDate;
}
