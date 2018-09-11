package com.saman.oak.portal.domain.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.saman.oak.core.converter.IdentifiableSerializer;
import com.saman.oak.core.converter.IdentifiableToIdConverter;
import com.saman.oak.core.domain.BaseEntity;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

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

@Entity(name = AddressEntity.ENTITY_NAME)
@Table(name = AddressEntity.TABLE_NAME, schema = AddressEntity.SCHEMA)
@XStreamAlias(AddressEntity.ENTITY_NAME)
public class AddressEntity extends BaseEntity implements AddressConstant {

    @Id
    @Column(name = ID_COLUMN, unique = true, nullable = false)
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
    @JoinColumn(name = "USER_ID", referencedColumnName = ID_COLUMN, nullable = false)
    private UserEntity user;

    @Override
    public Serializable getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getAdditional() {
        return additional;
    }

    public String getZipCode() {
        return zipCode;
    }

    public UserEntity getUser() {
        return user;
    }

    public AddressEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public AddressEntity setCountry(String country) {
        this.country = country;
        return this;
    }

    public AddressEntity setProvince(String province) {
        this.province = province;
        return this;
    }

    public AddressEntity setCity(String city) {
        this.city = city;
        return this;
    }

    public AddressEntity setAdditional(String additional) {
        this.additional = additional;
        return this;
    }

    public AddressEntity setZipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public AddressEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

}
