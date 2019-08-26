package com.github.efritzsche.declunit.fluent;

public interface TestDataTarget {

    /**
     * Configures the target object of the current test.
     *
     * @param target target instance
     * @param <T> target type
     * @return next step: further setup of target or method call
     */
    <T> TestDataArrangeTarget<T> target(T target);

    /**
     * Configures the current test for testing of a static method.
     *
     * @param targetClass target class
     * @param <T> target type
     * @return next step: static method call
     */
    <T> TestDataStaticMethod target(Class<T> targetClass);
}
