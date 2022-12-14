package com.sunnyside.Scheduler.model;


import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "job")
@Getter
@Setter
public class JobEntity extends BaseEntity {

    private String description;
    private Date date;
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private CustomerEntity customer;
}
