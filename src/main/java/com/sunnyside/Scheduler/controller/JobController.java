package com.sunnyside.Scheduler.controller;

import com.sunnyside.Scheduler.dto.JobDTO;
import com.sunnyside.Scheduler.model.JobEntity;
import com.sunnyside.Scheduler.service.JobService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService;


    @GetMapping("/")
    public List<JobEntity> getJobs(){

        return jobService.getAllJobs();
    }

    @PostMapping("/")
    public JobDTO createJob(@RequestBody JobDTO jobDTO) {
        return jobService.createJob(jobDTO);
    }

    @PutMapping("/")
    public JobDTO updateJob(@RequestBody JobDTO jobDTO) {
        return jobService.updateJob(jobDTO);
    }
}
