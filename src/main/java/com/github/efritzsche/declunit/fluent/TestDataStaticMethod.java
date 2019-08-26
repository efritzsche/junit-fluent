package com.github.efritzsche.declunit.fluent;

import com.github.efritzsche.declunit.function.StaticMethod;
import com.github.efritzsche.declunit.function.StaticVoidMethod;

public interface TestDataStaticMethod {

    TestDataExpectedNoResult applyVoid(StaticVoidMethod method);

    <R> TestDataExpectedResult<R> apply(StaticMethod<R> method);
}
