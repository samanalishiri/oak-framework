package com.saman.oak.portal.constant;

/**
 * @author Saman Alishiri
 * @mail samanalishiri@gmail.com
 * @since yyyy-MM-dd
 */

public interface PathVariable {
    String FAILED = "failed";


    interface DefaultValue {
        String FAILED = "/" + Boolean.FALSE;

    }
}
