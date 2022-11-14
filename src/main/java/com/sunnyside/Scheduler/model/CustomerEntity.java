package com.sunnyside.Scheduler.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
@Getter
@Setter
public class CustomerEntity extends BaseEntity {

    private String name;
    private String address;
    private String phoneNumber;
    private String emailAddress;

    @OneToMany(mappedBy = "customer")
    private List<JobEntity> jobs;

}
