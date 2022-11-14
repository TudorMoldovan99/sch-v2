package com.sunnyside.Scheduler.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@EntityListeners(EntityListener.class)
@Table(name = "invoice_line")
@Getter
@Setter
public class InvoiceLineEntity extends BaseEntity {

    private String description;
    private BigDecimal units;
    private BigDecimal value;

    @ManyToOne
    @JoinColumn(name = "invoiceId")
    private InvoiceEntity invoice;
}
