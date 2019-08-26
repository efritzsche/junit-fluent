package com.github.efritzsche.declunit.fluent;

import com.github.efritzsche.declunit.function.StaticMethod;
import com.github.efritzsche.declunit.function.StaticVoidMethod;

public interface TestDataStaticMethod {

    /**
     * Static method with no return value to call for testing. No arguments are passed.
     *
     * @param method void method to call
     * @return next step: success or failure configuration
     */
    TestDataExpectedNoResult applyVoid(StaticVoidMethod method);

    /**
     * Static method with some return value to call for testing. No arguments are passed.
     *
     * @param method method to call
     * @param <R> result type
     * @return next step: success or failure configuration
     */
    <R> TestDataExpectedResult<R> apply(StaticMethod<R> method);
}
