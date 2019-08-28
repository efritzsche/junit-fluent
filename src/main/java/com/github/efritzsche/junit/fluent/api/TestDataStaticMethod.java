package com.github.efritzsche.junit.fluent.api;

import com.github.efritzsche.junit.fluent.method.StaticMethod;
import com.github.efritzsche.junit.fluent.method.StaticMethod.StaticMethod1;
import com.github.efritzsche.junit.fluent.method.StaticMethod.StaticMethod2;
import com.github.efritzsche.junit.fluent.method.StaticMethod.StaticMethod3;
import com.github.efritzsche.junit.fluent.method.StaticVoidMethod;
import com.github.efritzsche.junit.fluent.method.StaticVoidMethod.StaticVoidMethod1;
import com.github.efritzsche.junit.fluent.method.StaticVoidMethod.StaticVoidMethod2;
import com.github.efritzsche.junit.fluent.method.StaticVoidMethod.StaticVoidMethod3;

public interface TestDataStaticMethod {

    /**
     * Static method with no return value to call for testing. No arguments are passed.
     *
     * @param method void method to call
     * @return next step: success or failure configuration
     */
    TestDataExpectedNoResult applyVoid(StaticVoidMethod method);

    <A0> TestDataExpectedNoResult applyVoid(StaticVoidMethod1<A0> method, A0 arg0);

    <A0, A1> TestDataExpectedNoResult applyVoid(StaticVoidMethod2<A0, A1> method, A0 arg0, A1 arg1);

    <A0, A1, A2> TestDataExpectedNoResult applyVoid(
            StaticVoidMethod3<A0, A1, A2> method, A0 arg0, A1 arg1, A2 arg2);

    /**
     * Static method with some return value to call for testing. No arguments are passed.
     *
     * @param method method to call
     * @param <R> result type
     * @return next step: success or failure configuration
     */
    <R> TestDataExpectedResult<R> apply(StaticMethod<R> method);

    <R, A0> TestDataExpectedResult<R> apply(StaticMethod1<R, A0> method, A0 arg0);

    <R, A0, A1> TestDataExpectedResult<R> apply(StaticMethod2<R, A0, A1> method, A0 arg0, A1 arg1);

    <R, A0, A1, A2> TestDataExpectedResult<R> apply(
            StaticMethod3<R, A0, A1, A2> method, A0 arg0, A1 arg1, A2 arg2);
}
