package com.github.efritzsche.declunit.builder;

public interface TestDataTarget {

    <T> TestDataArrangeTarget<T> target(T target);
}
