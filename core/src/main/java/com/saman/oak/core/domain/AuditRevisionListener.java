package com.saman.oak.core.domain;

import org.hibernate.envers.RevisionListener;

import static com.saman.oak.core.utils.SecurityUtils.getCurrentUsername;


/**
 * @author Saman Alishiri, samanalishiri@gmail.com
 */
public class AuditRevisionListener implements RevisionListener {

    @Override
    public void newRevision(Object revisionEntity) {
        AuditRevisionEntity.class
                .cast(revisionEntity)
                .setUsername(getCurrentUsername());
    }

}
