package com.saman.oak.portal.controller.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Saman Alishiri
 * @mail samanalishiri@gmail.com
 * @since yyyy-MM-dd
 */

@ControllerAdvice
public class ExceptionProvider {

    @ExceptionHandler(UsernameNotFoundException.class)
    public void userNull(UsernameNotFoundException e) {
        System.out.println("e.getMessage() = " + e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public void abc(IllegalArgumentException e) {
        System.out.println("e.getMessage() = " + e.getMessage());
    }

}
