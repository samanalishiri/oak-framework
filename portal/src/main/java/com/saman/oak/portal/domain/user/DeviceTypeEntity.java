package com.saman.oak.portal.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.saman.oak.core.domain.BaseEntity;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
 * Created by saman on 9/4/2017.
 */
@Getter
@Setter
@Accessors(chain = true, fluent = true)
@javax.persistence.Entity(name = DeviceTypeEntity.ENTITY_NAME)
@Table(name = DeviceTypeEntity.TABLE_NAME, schema = DeviceTypeEntity.SCHEMA)
@XStreamAlias(DeviceTypeEntity.ENTITY_NAME)
public class DeviceTypeEntity extends BaseEntity implements DeviceTypeConstant {

    @Id
    @Column(name = TABLE_NAME + ID_SUFFIX, unique = true, nullable = false)
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
}
