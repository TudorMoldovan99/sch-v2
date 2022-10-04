package com.sunnyside.Scheduler.dto;

import lombok.Data;

@Data
public class JobDTO {

    private Integer id;
    private String description;
    private String date;
    private Integer price;

}
