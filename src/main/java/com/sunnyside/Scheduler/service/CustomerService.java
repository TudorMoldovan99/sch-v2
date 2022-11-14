package com.sunnyside.Scheduler.service;

import com.sunnyside.Scheduler.dto.Customer;
import com.sunnyside.Scheduler.model.CustomerEntity;
import com.sunnyside.Scheduler.model.JobEntity;
import com.sunnyside.Scheduler.repository.CustomerRepository;
import com.sunnyside.Scheduler.repository.JobRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;

@Service
@Slf4j
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private JobRepository jobRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public Customer findCustomerById(Integer customerId) {

        CustomerEntity customerEntity = customerRepository.findById(customerId)
            .orElseThrow(() -> new EntityNotFoundException(String.format("Customer with id=%d does not exist!", customerId)));

        return modelMapper.map(customerEntity, Customer.class);
    }

    public List<Customer> findAll() {
        return customerRepository.findAll().stream()
            .map(customerEntity -> modelMapper.map(customerEntity, Customer.class))
            .collect(Collectors.toUnmodifiableList());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Customer createCustomer(Customer customerDTO) {

        CustomerEntity customer = modelMapper.map(customerDTO, CustomerEntity.class);

        CustomerEntity customerEntity = customerRepository.save(customer);

        if (customerDTO.getJobs() != null) {
            List<JobEntity> jobEntities = customerDTO.getJobs().stream()
                .map(jobDto -> modelMapper.map(jobDto, JobEntity.class))
                .peek(jobEntity -> jobEntity.setCustomer(customerEntity))
                .collect(Collectors.toUnmodifiableList());
            jobRepository.saveAll(jobEntities);
            customer.setJobs(jobEntities);
        }

        return modelMapper.map(customerEntity, Customer.class);
    }
}
