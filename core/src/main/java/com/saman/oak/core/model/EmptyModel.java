package com.saman.oak.core.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by saman on 9/4/2017.
 */
@Getter
@Setter
@Accessors(chain = true, fluent = true)
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EmptyModel extends BaseModel<Long> {
    private Long id = Long.valueOf(-1);

    public EmptyModel() {
    }

    @Override
    public Long getId() {
        return id;
    }
}
