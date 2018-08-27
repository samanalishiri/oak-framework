package com.saman.oak.core.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 9/29/2017.
 */
public interface Loggable {

    default Logger logger() {
        return LoggerFactory.getLogger(this.getClass().getSimpleName());
    }
}
