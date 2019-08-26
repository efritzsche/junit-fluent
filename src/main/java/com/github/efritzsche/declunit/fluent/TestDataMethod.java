package com.github.efritzsche.declunit.fluent;

import com.github.efritzsche.declunit.function.Method;
import com.github.efritzsche.declunit.function.VoidMethod;

public interface TestDataMethod<T> {

    TestDataExpectedNoResult applyVoid(VoidMethod<T> method);

    <R> TestDataExpectedResult<R> apply(Method<T, R> method);
}
