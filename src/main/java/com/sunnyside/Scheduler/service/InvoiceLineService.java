package com.sunnyside.Scheduler.service;

import com.sunnyside.Scheduler.repository.InvoiceLineRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceLineService {

    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private InvoiceLineRepository invoiceLineRepository;
}
