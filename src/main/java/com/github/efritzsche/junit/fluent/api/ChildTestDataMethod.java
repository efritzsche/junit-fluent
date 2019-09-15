package com.github.efritzsche.junit.fluent.api;

import com.github.efritzsche.junit.fluent.method.Method;
import com.github.efritzsche.junit.fluent.method.Method.Method1;
import com.github.efritzsche.junit.fluent.method.Method.Method2;
import com.github.efritzsche.junit.fluent.method.Method.Method3;
import com.github.efritzsche.junit.fluent.method.VoidMethod;
import com.github.efritzsche.junit.fluent.method.VoidMethod.VoidMethod1;
import com.github.efritzsche.junit.fluent.method.VoidMethod.VoidMethod2;
import com.github.efritzsche.junit.fluent.method.VoidMethod.VoidMethod3;

public interface ChildTestDataMethod<T> {

    ChildTestDataExpectedNoResult<T> applyVoid(VoidMethod<T> method);

    <A0> ChildTestDataExpectedNoResult<T> applyVoid(VoidMethod1<T, A0> method, A0 arg0);

    <A0, A1> ChildTestDataExpectedNoResult<T> applyVoid(VoidMethod2<T, A0, A1> method, A0 arg0, A1 arg1);

    <A0, A1, A2> ChildTestDataExpectedNoResult<T> applyVoid(
            VoidMethod3<T, A0, A1, A2> method, A0 arg0, A1 arg1, A2 arg2);

    <R> ChildTestDataExpectedResult<T, R> apply(Method<T, R> method);

    <R, A0> ChildTestDataExpectedResult<T, R> apply(Method1<T, R, A0> method, A0 arg0);

    <R, A0, A1> ChildTestDataExpectedResult<T, R> apply(Method2<T, R, A0, A1> method, A0 arg0, A1 arg1);

    <R, A0, A1, A2> ChildTestDataExpectedResult<T, R> apply(
            Method3<T, R, A0, A1, A2> method, A0 arg0, A1 arg1, A2 arg2);
}
