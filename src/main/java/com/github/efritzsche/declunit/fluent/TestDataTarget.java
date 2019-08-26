package com.github.efritzsche.declunit.fluent;

public interface TestDataTarget {

    <T> TestDataArrangeTarget<T> target(T target);

    <T> TestDataStaticMethod target(Class<T> targetClass);
}
