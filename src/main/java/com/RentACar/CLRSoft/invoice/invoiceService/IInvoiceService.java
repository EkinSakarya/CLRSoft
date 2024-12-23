package com.RentACar.CLRSoft.invoice.invoiceService;

import com.RentACar.CLRSoft.invoice.entity.Invoice;
import com.RentACar.CLRSoft.invoice.invoiceResponse.FindByIdInvoiceResponse;
import com.RentACar.CLRSoft.invoice.invoiceResponse.InvoiceResponse;
import com.RentACar.CLRSoft.rental.entity.Rental;

import java.util.List;

public interface IInvoiceService {
    List<InvoiceResponse> findAll();

    void addInvoice(Rental rental);

    void update(Invoice invoice);
    public List<Invoice> findDebtInvoices();
    void delete(Invoice invoice);
    public FindByIdInvoiceResponse findById(int id);
    public Invoice paybyIdInvoice(int id);
}
