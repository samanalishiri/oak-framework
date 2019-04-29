package com.saman.oak.core.domain;

import java.io.Serializable;

/**
 * @author Saman Alishiri, samanalishiri@gmail.com
 */
public interface Persistable<ID extends Serializable> {

    ID getId();
}
