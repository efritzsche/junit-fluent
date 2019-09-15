package com.github.efritzsche.junit.fluent.api;

import com.github.efritzsche.junit.fluent.TestData.TargetSupplier;

public interface TestDataTarget {

    /**
     * Configures the target object of the current test.
     *
     * @param targetSupplier supplier of the target instance
     * @param <T> target type
     * @return next step: further setup of target, method call or child test setup
     */
    <T> TestDataOptionalTargetSetup<T> target(TargetSupplier<T> targetSupplier);

    /**
     * Configures the current test for testing of a static method.
     *
     * @param targetClass target class
     * @param <T> target type
     * @return next step: static method call
     */
    <T> TestDataStaticMethod target(Class<T> targetClass);
}
