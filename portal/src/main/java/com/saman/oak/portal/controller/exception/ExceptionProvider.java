package com.saman.oak.portal.controller.exception;

import com.saman.oak.portal.controller.UserNullException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Saman Alishiri
 * @mail samanalishiri@gmail.com
 * @since yyyy-MM-dd
 */

@ControllerAdvice
public class ExceptionProvider {

    @ExceptionHandler(UserNullException.class)
    public void userNull(UserNullException e) {
        System.out.println("e.getMessage() = " + e.getMessage());
    }

}
