package com.sunnyside.Scheduler.repository;

import com.sunnyside.Scheduler.model.InvoiceLineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceLineRepository extends JpaRepository<InvoiceLineEntity, Integer> {
}
