package com.RentACar.CLRSoft.invoice.invoiceService;

import com.RentACar.CLRSoft.invoice.entity.Invoice;
import com.RentACar.CLRSoft.invoice.invoiceDal.IInvoiceDal;
import com.RentACar.CLRSoft.invoice.invoiceResponse.FindByIdInvoiceResponse;
import com.RentACar.CLRSoft.invoice.invoiceResponse.InvoiceResponse;
import com.RentACar.CLRSoft.rental.entity.Rental;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.text.DecimalFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceManager implements IInvoiceService {

    private final IInvoiceDal invoiceDal;
    private final ModelMapper modelMapper;
    private final int TaxAmount;

    @Autowired
    public InvoiceManager(IInvoiceDal invoiceDal, ModelMapper modelMapper) {
        this.invoiceDal = invoiceDal;
        this.modelMapper = modelMapper;
        this.TaxAmount=20;
    }

    @Override
    @Transactional
    public List<InvoiceResponse> findAll() {
        List<Invoice> invoices = invoiceDal.findAll();

        return invoices.stream().map(
                        invoice -> modelMapper.map(invoice, InvoiceResponse.class)).
                collect(Collectors.toList());
    }

    @Override
    public void addInvoice(Rental rental)
    {
        DecimalFormat format = new DecimalFormat("#.00");

        double tax= ((double) this.TaxAmount /100)*rental.getTotalPrice();

         Invoice invoice =new Invoice();
         invoice.setCustomer(rental.getCustomer());
         invoice.setInvoiceStatus("ödenmedi");
         invoice.setRental(rental);
         invoice.setUser(rental.getUser());
         invoice.setIssueDate(rental.getRentalStartDate());
         invoice.setPaymentDate(null);
         invoice.setTaxAmount(tax);
         invoice.setTotalPrice(tax+rental.getTotalPrice());

        invoiceDal.add(invoice);
    }

    public void update(Invoice invoice) {
        invoiceDal.update(invoice);
    }

    @Override
    public void delete(Invoice invoice) {
        invoiceDal.delete(invoice);
    }

    @Override
    public FindByIdInvoiceResponse findById(int id) {
        Invoice invoice = invoiceDal.findById(id);
        if (invoice == null) {
            return null;
        }
        return modelMapper.map(invoice, FindByIdInvoiceResponse.class);
    }
    @Override
    public List<Invoice> findDebtInvoices()
    {
        List<Invoice> invoices =invoiceDal.findAll();
        ArrayList<Invoice> debtInvoices =new ArrayList<Invoice>();
        for(Invoice invoice:invoices)
        {
            if(invoice.getInvoiceStatus().equals("ödenmedi"))
            {
                debtInvoices.add(invoice);
            }
        }
        return debtInvoices;
    }
    @Override
    public Invoice paybyIdInvoice(int id)
    {
        Invoice invoice = invoiceDal.findById(id);
        invoice.setInvoiceStatus("Ödendi");
        invoice.setPaymentDate(new Date());
        update(invoice);

        return invoice;
    }
}
