package com.saman.oak.core.utils;

/**
 * Created by saman on 9/17/2017.
 */
public class IntHelper {

    private int number;

    public IntHelper(int number) {
        this.number = number;
    }

    public boolean biggerThan(int limit) {
        return (number > limit);
    }

    public boolean smallerThan(int limit) {
        return (number < limit);
    }

    public boolean between(int low, int high) {
        return (low < number && number < high);
    }
}
