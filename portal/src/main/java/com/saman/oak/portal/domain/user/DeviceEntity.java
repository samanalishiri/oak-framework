package com.saman.oak.portal.domain.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.saman.oak.core.converter.IdentifiableSerializer;
import com.saman.oak.core.converter.IdentifiableToIdConverter;
import com.saman.oak.core.domain.BaseEntity;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by saman on 9/4/2017.
 */
@Getter
@Setter
@Accessors(chain = true, fluent = true)
@javax.persistence.Entity(name = DeviceEntity.ENTITY_NAME)
@Table(name = DeviceEntity.TABLE_NAME, schema = DeviceEntity.SCHEMA)
@XStreamAlias(DeviceEntity.ENTITY_NAME)
public class DeviceEntity extends BaseEntity implements DeviceConstant {

    @Id
    @Column(name = TABLE_NAME + ID_SUFFIX, unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TABLE_NAME + GEN_SUFFIX)
    @SequenceGenerator(name = TABLE_NAME + GEN_SUFFIX, sequenceName = TABLE_NAME + SEQ_SUFFIX)
    private Long id;

    @Column(name = "PREFIX", nullable = false)
    private String prefix;

    @Column(name = "IDENTITY_FIELD", nullable = false)
    private String identity;

    @Column(name = "ENABLED")
    @Type(type = "boolean")
    private boolean enabled = true;

    @JsonSerialize(using = IdentifiableSerializer.class)
    @JsonProperty("typeId")
    @XStreamConverter(value = IdentifiableToIdConverter.class)
    @XStreamAlias("typeId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TYPE", referencedColumnName = DeviceTypeEntity.TABLE_NAME + ID_SUFFIX, nullable = false)
    private DeviceTypeEntity type;

    @JsonSerialize(using = IdentifiableSerializer.class)
    @JsonProperty("userId")
    @XStreamConverter(value = IdentifiableToIdConverter.class)
    @XStreamAlias("userId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", referencedColumnName = UserEntity.TABLE_NAME + ID_SUFFIX, nullable = false)
    private UserEntity user;

    @Override
    public Serializable getId() {
        return id;
    }
}
