package com.sunnyside.Scheduler.dto;

import lombok.Data;

import java.util.List;

@Data
public class Customer {

    private Integer id;
    private String name;
    private String address;
    private String phoneNumber;
    private String emailAddress;
    private List<Job> jobs;

}
