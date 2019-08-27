package com.github.efritzsche.junit.fluent.api;

import java.util.function.Consumer;
import java.util.function.Function;

public interface TestDataArrangeTarget<T> extends TestDataMethod<T> {

    /**
     * The given {@link Consumer} is executed before the tests and can change the state of the
     * target itself. The target is passed as the argument.
     *
     * @param arrangeTarget preparation and statechange of the test target
     * @return next step: method call
     */
    TestDataMethod<T> prepareTarget(Consumer<T> arrangeTarget);

    /**
     * The given {@link Function} is executed before the tests and replaces the target with the
     * return value of the function. The target is passed as the argument.
     *
     * @param arrangeTarget preparation and replacement of the test target
     * @return next step: method call
     */
    TestDataMethod<T> replaceTarget(Function<T, T> arrangeTarget);
}
