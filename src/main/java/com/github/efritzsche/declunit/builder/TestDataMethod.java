package com.github.efritzsche.declunit.builder;

import java.util.function.Consumer;
import java.util.function.Function;

public interface TestDataMethod<T, R> {

    TestDataExpected<T, R> apply(Function<T, R> method);

    TestDataExpectedVoid<T, R> applyVoid(Consumer<T> method);
}
