package com.github.efritzsche.declunit.fluent;

import java.util.function.Supplier;

public interface TestDataStaticMethod {

    TestDataExpectedNoResult applyVoid(Runnable method);

    <R> TestDataExpectedResult<R> apply(Supplier<R> method);
}
