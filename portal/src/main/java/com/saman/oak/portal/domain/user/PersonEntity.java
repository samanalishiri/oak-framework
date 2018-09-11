package com.saman.oak.portal.domain.user;

import com.saman.oak.core.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Saman Alishiri
 * @mail samanalishiri@gmail.com
 * @since yyyy-MM-dd
 */

@Embeddable
public class PersonEntity extends BaseEntity {

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "SURNAME", nullable = false)
    private String surname;

    @Column(name = "NATIONAL_CODE", unique = true, nullable = false)
    private String nationalCode;

    @Column(name = "ID_CARD_NO", nullable = false)
    private String icn;

    @Column(name = "ID_CARD_SERIAL", nullable = false)
    private String ics;

    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTH_DATE", nullable = false)
    private Date birthDate;

    @Column(name = "BIRTH_PLACE", nullable = false)
    private String birthPlace;

    @Override
    public Serializable getId() {
        return -1;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public String getIcn() {
        return icn;
    }

    public String getIcs() {
        return ics;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public PersonEntity setName(String name) {
        this.name = name;
        return this;
    }

    public PersonEntity setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public PersonEntity setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
        return this;
    }

    public PersonEntity setIcn(String icn) {
        this.icn = icn;
        return this;
    }

    public PersonEntity setIcs(String ics) {
        this.ics = ics;
        return this;
    }

    public PersonEntity setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public PersonEntity setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
        return this;
    }

}
