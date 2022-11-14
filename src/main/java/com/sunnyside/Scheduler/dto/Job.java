package com.sunnyside.Scheduler.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.sql.Date;


@Data
@NoArgsConstructor
public class Job {

    private Integer id;
    @NonNull
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(dataType = "java.sql.Date")
    private Date date;
    private Integer price;
    private Integer customerId;

}
