package com.saman.oak.core.domain;

public class EmptyEntity extends BaseEntity<Long> {

    private Long id = new Long(-1);

    public EmptyEntity() {
    }

    @Override
    public Long getId() {
        return id;
    }


}
