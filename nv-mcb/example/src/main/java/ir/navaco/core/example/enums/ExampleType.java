package ir.navaco.core.example.enums;

import ir.navaco.core.enums.Convertible;

public enum ExampleType implements Convertible<Integer> {
    A(1),
    B(2),
    C(3),
    D(4),
    ;

    private final Integer value;

    ExampleType(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
