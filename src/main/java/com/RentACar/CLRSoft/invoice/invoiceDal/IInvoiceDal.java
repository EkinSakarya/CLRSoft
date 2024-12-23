package com.RentACar.CLRSoft.invoice.invoiceDal;

import com.RentACar.CLRSoft.invoice.entity.Invoice;

import java.util.List;

public interface IInvoiceDal {

    List<Invoice> findAll();

    void add(Invoice invoice);

    void update(Invoice invoice);

    void delete(Invoice invoice);

    public Invoice findById(int id);
}
