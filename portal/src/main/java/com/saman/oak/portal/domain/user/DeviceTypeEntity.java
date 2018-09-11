package com.saman.oak.portal.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.saman.oak.core.domain.BaseEntity;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * @author Saman Alishiri
 * @mail samanalishiri@gmail.com
 * @since yyyy-MM-dd
 */

@Entity(name = DeviceTypeEntity.ENTITY_NAME)
@Table(name = DeviceTypeEntity.TABLE_NAME, schema = DeviceTypeEntity.SCHEMA)
@XStreamAlias(DeviceTypeEntity.ENTITY_NAME)
public class DeviceTypeEntity extends BaseEntity implements DeviceTypeConstant {

    @Id
    @Column(name = ID_COLUMN, unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TABLE_NAME + GEN_SUFFIX)
    @SequenceGenerator(name = TABLE_NAME + GEN_SUFFIX, sequenceName = TABLE_NAME + SEQ_SUFFIX)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "ENABLED")
    @Type(type = "boolean")
    private boolean enabled = true;

    @JsonIgnore
    @XStreamOmitField
    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DeviceEntity> devices;

    @Override
    public Serializable getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public List<DeviceEntity> getDevices() {
        return devices;
    }

    public DeviceTypeEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public DeviceTypeEntity setName(String name) {
        this.name = name;
        return this;
    }

    public DeviceTypeEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public DeviceTypeEntity setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public DeviceTypeEntity setDevices(List<DeviceEntity> devices) {
        this.devices = devices;
        return this;
    }

}
