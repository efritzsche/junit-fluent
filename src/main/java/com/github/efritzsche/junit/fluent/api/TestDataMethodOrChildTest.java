package com.github.efritzsche.junit.fluent.api;

import com.github.efritzsche.junit.fluent.method.Method;
import com.github.efritzsche.junit.fluent.method.Method.Method1;
import com.github.efritzsche.junit.fluent.method.Method.Method2;
import com.github.efritzsche.junit.fluent.method.Method.Method3;
import com.github.efritzsche.junit.fluent.method.VoidMethod;
import com.github.efritzsche.junit.fluent.method.VoidMethod.VoidMethod1;
import com.github.efritzsche.junit.fluent.method.VoidMethod.VoidMethod2;
import com.github.efritzsche.junit.fluent.method.VoidMethod.VoidMethod3;

public interface TestDataMethodOrChildTest<T> {

    ChildTestDataOptionalTargetSetup<T> childTest(String description);

    /**
     * Method with no return value to call for testing. The test target is passed as the argument.
     *
     * @param method void method to call
     * @return next step: success or failure configuration
     */
    TestDataExpectedNoResult applyVoid(VoidMethod<T> method);

    <A0> TestDataExpectedNoResult applyVoid(VoidMethod1<T, A0> method, A0 arg0);

    <A0, A1> TestDataExpectedNoResult applyVoid(VoidMethod2<T, A0, A1> method, A0 arg0, A1 arg1);

    <A0, A1, A2> TestDataExpectedNoResult applyVoid(
            VoidMethod3<T, A0, A1, A2> method, A0 arg0, A1 arg1, A2 arg2);

    /**
     * Method with some return value to call for testing. The test target is passed as the argument.
     *
     * @param method method to call
     * @param <R> result type
     * @return next step: success or failure configuration
     */
    <R> TestDataExpectedResult<R> apply(Method<T, R> method);

    <R, A0> TestDataExpectedResult<R> apply(Method1<T, R, A0> method, A0 arg0);

    <R, A0, A1> TestDataExpectedResult<R> apply(Method2<T, R, A0, A1> method, A0 arg0, A1 arg1);

    <R, A0, A1, A2> TestDataExpectedResult<R> apply(
            Method3<T, R, A0, A1, A2> method, A0 arg0, A1 arg1, A2 arg2);
}
