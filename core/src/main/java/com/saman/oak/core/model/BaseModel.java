package com.saman.oak.core.model;

import com.saman.oak.core.domain.CompareResult;
import org.springframework.hateoas.Identifiable;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Saman Alishiri, samanalishiri@gmail.com
 */
public abstract class BaseModel<ID extends Serializable> implements Identifiable<ID>, Comparable<BaseModel>, Serializable {

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
        if (getClass() != object.getClass()) {
            return false;
        }

        BaseModel<?> other = BaseModel.class.cast(object);
        return Objects.equals(this.getId(), other.getId());
    }

    @Override
    public int compareTo(BaseModel entity) {
        return Objects.equals(this, entity) ? CompareResult.IS_EQUAL : CompareResult.NO_EQUAL;
    }

    @Override
    public String toString() {
        return getId().toString();
    }
}
