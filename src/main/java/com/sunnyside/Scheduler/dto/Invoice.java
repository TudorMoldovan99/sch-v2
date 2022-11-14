package com.sunnyside.Scheduler.dto;

import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class Invoice {

    private Integer id;
    private String description;
    private Date date;
    private Date generatedDate;
    private Integer customerId;
    private List<InvoiceLine> invoiceLines;
}
