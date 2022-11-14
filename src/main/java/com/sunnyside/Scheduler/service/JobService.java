package com.sunnyside.Scheduler.service;

import com.sunnyside.Scheduler.dto.Customer;
import com.sunnyside.Scheduler.dto.Job;
import com.sunnyside.Scheduler.model.CustomerEntity;
import com.sunnyside.Scheduler.model.JobEntity;
import com.sunnyside.Scheduler.repository.JobRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import javax.persistence.EntityNotFoundException;

@Service
@Slf4j
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CustomerService customerService;

    private final ModelMapper modelMapper = new ModelMapper();

    @Transactional(readOnly = true)
    public List<JobEntity> getAllJobs() {
        return jobRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Job getJob(Integer jobId) {
        return jobRepository.findById(jobId)
            .map(jobEntity -> modelMapper.map(jobEntity, Job.class))
            .orElseThrow(() -> new EntityNotFoundException("Job with id={} does not exist"));
    }

    public Job createJob(Job job) {

        JobEntity jobEntity = modelMapper.map(job, JobEntity.class);
        Customer customer = customerService.findCustomerById(job.getCustomerId());
        jobEntity.setCustomer(modelMapper.map(customer, CustomerEntity.class));
        log.info("Job entity:{} creation BEGIN", jobEntity.getDescription());
        JobEntity savedJob = jobRepository.save(jobEntity);
        log.info("Job saved:{} creation END", savedJob);
        return modelMapper.map(savedJob, Job.class);
    }

    public Job updateJob(Job job) {

        JobEntity jobEntity = jobRepository.findById(job.getId())
            .orElseThrow(() -> new EntityNotFoundException("job not found!"));

        jobEntity.setDescription(job.getDescription());

        jobRepository.save(jobEntity);

        return modelMapper.map(jobEntity, Job.class);

    }
}
