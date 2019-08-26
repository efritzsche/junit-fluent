package com.github.efritzsche.declunit.fluent;

import com.github.efritzsche.declunit.function.Method;
import com.github.efritzsche.declunit.function.VoidMethod;

public interface TestDataMethod<T> {

    /**
     * Method with no return value to call for testing. The test target is passed as the argument.
     *
     * @param method void method to call
     * @return next step: success or failure configuration
     */
    TestDataExpectedNoResult applyVoid(VoidMethod<T> method);

    /**
     * Method with some return value to call for testing. The test target is passed as the argument.
     *
     * @param method method to call
     * @param <R> result type
     * @return next step: success or failure configuration
     */
    <R> TestDataExpectedResult<R> apply(Method<T, R> method);
}
