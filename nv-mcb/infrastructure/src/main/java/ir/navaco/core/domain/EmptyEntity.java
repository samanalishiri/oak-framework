package ir.navaco.core.domain;

import org.springframework.stereotype.Component;

@Component(EmptyEntity.NAME)
public class EmptyEntity extends AbstractEntity<Long> {

    public static final String NAME = "emptyEntity";

    @Override
    public Long getId() {
        return -1L;
    }
}
