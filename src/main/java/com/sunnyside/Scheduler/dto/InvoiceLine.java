package com.sunnyside.Scheduler.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class InvoiceLine {

    private Integer id;
    private String description;
    private BigDecimal units;
    private BigDecimal value;
}
