package com.sunnyside.Scheduler.model;

import java.time.LocalDateTime;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;

public class EntityListener {

    @PostUpdate
    private void postUpdate(Object entity) {

        if(entity instanceof BaseEntity) {
            BaseEntity base = (BaseEntity) entity;
            base.setLastUpdatedAt(LocalDateTime.now());
            base.setLastUpdatedBy("Tudor");
        }
    }

    @PostPersist
    private void postPersist(Object entity) {

        if(entity instanceof BaseEntity) {
            BaseEntity base = (BaseEntity) entity;
            base.setCreatedAt(LocalDateTime.now());
            base.setCreatedBy("Tudor");
        }
    }
}
