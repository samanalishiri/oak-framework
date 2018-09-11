package com.saman.oak.portal.domain.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.saman.oak.core.converter.IdentifiableSerializer;
import com.saman.oak.core.converter.IdentifiableToIdConverter;
import com.saman.oak.core.domain.BaseEntity;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
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
 * @author Saman Alishiri
 * @mail samanalishiri@gmail.com
 * @since yyyy-MM-dd
 */

@Entity(name = DeviceEntity.ENTITY_NAME)
@Table(name = DeviceEntity.TABLE_NAME, schema = DeviceEntity.SCHEMA)
@XStreamAlias(DeviceEntity.ENTITY_NAME)
public class DeviceEntity extends BaseEntity implements DeviceConstant {

    @Id
    @Column(name = ID_COLUMN, unique = true, nullable = false)
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
    @JoinColumn(name = "TYPE", referencedColumnName = ID_COLUMN, nullable = false)
    private DeviceTypeEntity type;

    @JsonSerialize(using = IdentifiableSerializer.class)
    @JsonProperty("userId")
    @XStreamConverter(value = IdentifiableToIdConverter.class)
    @XStreamAlias("userId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", referencedColumnName = ID_COLUMN, nullable = false)
    private UserEntity user;

    @Override
    public Serializable getId() {
        return id;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getIdentity() {
        return identity;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public DeviceTypeEntity getType() {
        return type;
    }

    public UserEntity getUser() {
        return user;
    }

    public DeviceEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public DeviceEntity setPrefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    public DeviceEntity setIdentity(String identity) {
        this.identity = identity;
        return this;
    }

    public DeviceEntity setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public DeviceEntity setType(DeviceTypeEntity type) {
        this.type = type;
        return this;
    }

    public DeviceEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

}
