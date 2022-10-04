package com.sunnyside.Scheduler.service;

import com.sunnyside.Scheduler.dto.JobDTO;
import com.sunnyside.Scheduler.mapper.JobMapper;
import com.sunnyside.Scheduler.model.JobEntity;
import com.sunnyside.Scheduler.repository.JobRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.persistence.EntityNotFoundException;

@Service
@Slf4j
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public List<JobEntity> getAllJobs() {
        return jobRepository.findAll();
    }

    public JobDTO createJob(JobDTO jobDTO) {

        JobEntity jobEntity = modelMapper.map(jobDTO, JobEntity.class);
        log.info("Job entity:{} creation BEGIN", jobEntity.getDescription());
        JobEntity savedJob = jobRepository.save(jobEntity);
        log.info("Job saved:{} creation END", savedJob);
        return modelMapper.map(savedJob, JobDTO.class);
    }

    public JobDTO updateJob(JobDTO jobDTO) {

        JobEntity jobEntity = jobRepository.findById(jobDTO.getId())
            .orElseThrow(() -> new EntityNotFoundException("job not found!"));

        jobEntity.setDescription(jobDTO.getDescription());

        jobRepository.save(jobEntity);

        return modelMapper.map(jobEntity, JobDTO.class);

    }
}
