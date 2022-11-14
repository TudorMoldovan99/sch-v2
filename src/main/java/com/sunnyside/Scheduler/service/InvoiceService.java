package com.sunnyside.Scheduler.service;

import com.sunnyside.Scheduler.dto.Invoice;
import com.sunnyside.Scheduler.model.InvoiceEntity;
import com.sunnyside.Scheduler.model.InvoiceLineEntity;
import com.sunnyside.Scheduler.repository.InvoiceLineRepository;
import com.sunnyside.Scheduler.repository.InvoiceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;

@Service
public class InvoiceService {

    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private InvoiceLineRepository invoiceLineRepository;

    @Transactional(readOnly = true)
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll().stream()
            .map(invoiceEntity -> modelMapper.map(invoiceEntity, Invoice.class))
            .collect(Collectors.toUnmodifiableList());
    }

    @Transactional(readOnly = true)
    public Invoice getInvoice(Integer invoiceId) {
        return invoiceRepository.findById(invoiceId)
            .map(invoiceEntity -> modelMapper.map(invoiceEntity, Invoice.class))
            .orElseThrow(() -> new EntityNotFoundException(String.format("Invoice with id=%d does not exist", invoiceId)));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Invoice createInvoice(Invoice invoice) {

        InvoiceEntity invoiceEntity = modelMapper.map(invoice, InvoiceEntity.class);
        InvoiceEntity savedInvoice = invoiceRepository.save(invoiceEntity);

        if (invoice.getInvoiceLines() != null) {
            List<InvoiceLineEntity> invoiceLineEntities = invoice.getInvoiceLines().stream()
                .map(invoiceLine -> modelMapper.map(invoiceLine, InvoiceLineEntity.class))
                .peek(invoiceLineEntity -> invoiceLineEntity.setInvoice(savedInvoice))
                .collect(Collectors.toUnmodifiableList());

            invoiceLineRepository.saveAll(invoiceLineEntities);
        }

        return modelMapper.map(savedInvoice, Invoice.class);
    }
}
