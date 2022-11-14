package com.sunnyside.Scheduler.controller;

import com.sunnyside.Scheduler.dto.Job;
import com.sunnyside.Scheduler.model.JobEntity;
import com.sunnyside.Scheduler.service.JobService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService;


    @GetMapping("/list")
    public List<JobEntity> getJobs() {

        return jobService.getAllJobs();
    }

    @GetMapping("/")
    public Job getJob(@RequestParam Integer jobId) {
        return jobService.getJob(jobId);
    }

    @PostMapping("/")
    public Job createJob(@RequestBody Job job) {
        return jobService.createJob(job);
    }

    @PutMapping("/")
    public Job updateJob(@RequestBody Job job) {
        return jobService.updateJob(job);
    }
}
