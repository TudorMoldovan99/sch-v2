package com.sunnyside.Scheduler.controller;

import com.sunnyside.Scheduler.dto.Invoice;
import com.sunnyside.Scheduler.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;


    @GetMapping("/list")
    public List<Invoice> getInvoices() {
        return invoiceService.getAllInvoices();
    }

    @GetMapping("/")
    public Invoice getInvoice(@RequestParam Integer invoiceId) {
        return invoiceService.getInvoice(invoiceId);
    }

    @PostMapping("/")
    public Invoice createInvoice(@RequestBody Invoice invoice) {
        return invoiceService.createInvoice(invoice);
    }
}
