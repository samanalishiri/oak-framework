package com.saman.oak.portal.domain.user;

import com.saman.oak.core.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by saman on 9/4/2017.
 */
@Embeddable
@Getter
@Setter
@Accessors(chain = true, fluent = true)
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
        return null;
    }
}
