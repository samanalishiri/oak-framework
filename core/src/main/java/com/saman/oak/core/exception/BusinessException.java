package com.saman.oak.core.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@Accessors(chain = true, fluent = true)
public class BusinessException extends BaseException {
    private HttpStatus httpStatus;

}
