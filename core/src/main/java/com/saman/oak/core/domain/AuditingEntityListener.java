package com.saman.oak.core.domain;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * @author Saman Alishiri, samanalishiri@gmail.com
 */
public class AuditingEntityListener {

    @PrePersist
    public void touchForCreate(Object target) {

    }

    @PreUpdate
    public void touchForUpdate(Object target) {

    }
}