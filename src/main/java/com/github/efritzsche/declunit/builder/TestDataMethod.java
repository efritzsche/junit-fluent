package com.github.efritzsche.declunit.builder;

import java.util.function.Consumer;
import java.util.function.Function;

public interface TestDataMethod<T> {

    TestDataExpectedNoResult applyVoid(Consumer<T> method);

    <R> TestDataExpectedResult<R> apply(Function<T, R> method);
}
