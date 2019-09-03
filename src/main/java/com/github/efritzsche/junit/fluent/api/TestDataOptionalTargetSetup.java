package com.github.efritzsche.junit.fluent.api;

import java.util.function.Consumer;
import java.util.function.Function;

public interface TestDataOptionalTargetSetup<T> {

    /**
     * The given {@link Consumer} is executed before the tests and can change the state of the
     * target itself. The target is passed as the argument.
     *
     * @param prepare preparation and statechange of the test target
     * @return next step: method call
     */
    TestDataMethod<T> prepareTarget(Consumer<T> prepare);

    /**
     * The given {@link Function} is executed before the tests and replaces the target with the
     * return value of the function. The target is passed as the argument.
     *
     * @param transform setup and transformation of the test target
     * @return next step: method call
     */
    TestDataMethod<T> transformTarget(Function<T, T> transform);
}
