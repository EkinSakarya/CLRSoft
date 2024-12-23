package com.RentACar.CLRSoft.invoice.entity;

import com.RentACar.CLRSoft.customer.entity.Customer;
import com.RentACar.CLRSoft.rental.entity.Rental;
import com.RentACar.CLRSoft.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "Invoice")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "RentalId", referencedColumnName = "Id")
    private Rental rental;

    @ManyToOne
    @JoinColumn(name = "CustomerId", referencedColumnName = "Id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "UserId", referencedColumnName = "Id")
    private User user;

    @Column(name = "TotalPrice", nullable = false)
    private double totalPrice;

    @Column(name = "TaxAmount", nullable = false)
    private double taxAmount;

    @Column(name = "IssueDate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date issueDate;

    @Column(name = "InvoiceStatus")
    private String invoiceStatus;

    @Column(name = "PaymentDate")
    @Temporal(TemporalType.DATE)
    private Date paymentDate;

    public Invoice(Rental rental, Customer customer, User user, double totalPrice, double taxAmount, Date issueDate, String invoiceStatus, Date paymentDate) {
        this.rental = rental;
        this.customer = customer;
        this.user = user;
        this.totalPrice = totalPrice;
        this.taxAmount = taxAmount;
        this.issueDate = issueDate;
        this.invoiceStatus = invoiceStatus;
        this.paymentDate = paymentDate;
    }
}