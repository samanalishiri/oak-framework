package com.saman.oak.core.domain;

import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@RevisionEntity(AuditRevisionListener.class)
@Table(name = "REVINFO", schema = "BASE_INFO")
@AttributeOverrides({
        @AttributeOverride(name = "timestamp", column = @Column(name = "REVTSTMP")),
        @AttributeOverride(name = "id", column = @Column(name = "REV"))})
public class AuditRevisionEntity extends DefaultRevisionEntity {

    @Column(name = "USERNAME", nullable = false)
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
