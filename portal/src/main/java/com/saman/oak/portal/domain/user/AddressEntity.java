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
@javax.persistence.Entity(name = AddressEntity.ENTITY_NAME)
@Table(name = AddressEntity.TABLE_NAME, schema = AddressEntity.SCHEMA)
@XStreamAlias(AddressEntity.ENTITY_NAME)
public class AddressEntity extends BaseEntity implements AddressConstant {

    @Id
    @Column(name = TABLE_NAME + ID_SUFFIX, unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TABLE_NAME + GEN_SUFFIX)
    @SequenceGenerator(name = TABLE_NAME + GEN_SUFFIX, sequenceName = TABLE_NAME + SEQ_SUFFIX)
    private Long id;

    @Column(name = "COUNTRY", nullable = false)
    private String country;

    @Column(name = "PROVINCE", nullable = false)
    private String province;

    @Column(name = "CITY", nullable = false)
    private String city;

    @Column(name = "ADDITIONAL", nullable = false)
    private String additional;

    @Column(name = "ZIP_CODE", nullable = false)
    private String zipCode;

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
