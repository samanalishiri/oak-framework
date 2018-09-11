package com.saman.oak.core.domain;

import org.springframework.hateoas.Identifiable;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Saman Alishiri
 * @mail samanalishiri@gmail.com
 * @since yyyy-MM-dd
 *
 */

@MappedSuperclass
public abstract class BaseEntity<ID extends Serializable> implements Identifiable<ID>, Comparable<BaseEntity>, Serializable {

    public static final String UNDER_LINE = "_";
    public static final String ID_COLUMN = "ID";
    public static final String ID_SUFFIX = UNDER_LINE + ID_COLUMN;
    public static final String SEQ_SUFFIX = UNDER_LINE + "SEQ";
    public static final String GEN_SUFFIX = UNDER_LINE + "GEN";
    public static final String UQ_SUFFIX = UNDER_LINE + "UQ";
    public static final String DOT = ".";

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (Objects.isNull(object)) {
            return false;
        }
        if (this.getClass() != object.getClass()) {
            return false;
        }

        BaseEntity<?> other = BaseEntity.class.cast(object);
        return Objects.equals(this.getId(), other.getId());
    }

    @Override
    public int compareTo(BaseEntity entity) {
        return Objects.equals(this, entity) ? CompareResult.IS_EQUAL : CompareResult.NO_EQUAL;
    }

    @Override
    public String toString() {
        return getId().toString();
    }

    public Class<? extends BaseEntity> getClassType() {
        return this.getClass();
    }
}
