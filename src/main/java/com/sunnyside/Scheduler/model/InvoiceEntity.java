package com.sunnyside.Scheduler.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@EntityListeners(EntityListener.class)
@Table(name = "invoice")
@Getter
@Setter
public class InvoiceEntity extends BaseEntity {

    private String description;
    private Date date;

    private Date generatedDate;

    @OneToMany(mappedBy = "invoice")
    List<InvoiceLineEntity> invoiceLines;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private CustomerEntity customer;
}
