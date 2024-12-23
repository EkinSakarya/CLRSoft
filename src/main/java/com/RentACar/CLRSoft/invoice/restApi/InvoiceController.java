package com.RentACar.CLRSoft.invoice.restApi;

import com.RentACar.CLRSoft.invoice.entity.Invoice;
import com.RentACar.CLRSoft.invoice.invoiceResponse.InvoiceResponse;
import com.RentACar.CLRSoft.invoice.invoiceService.IInvoiceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class InvoiceController {

    private IInvoiceService invoiceService;

    public InvoiceController(IInvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/getinvoices")
    public List<InvoiceResponse> findAll()
    {
        return invoiceService.findAll();
    }

    @GetMapping("/getdebtinvoices")
    public List<Invoice> findDebtInvoices()
    {
        return invoiceService.findDebtInvoices();
    }
    @PutMapping("/paybyidInvoice/{id}")
    public Invoice payByIdInvoice(@PathVariable int id)
    {
        return invoiceService.paybyIdInvoice(id);
    }
}
