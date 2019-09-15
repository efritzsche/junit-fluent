package com.github.efritzsche.junit.fluent.api;

import java.util.function.Consumer;

public interface ChildTestDataExpectedResult<T, R>
        extends TestDataExpectedResult<R>, ChildTestDataExpectedException<T> {

    @Override
    ChildTestCreator<T> expectSuccess(R expectedResult);

    @Override
    ChildTestCreator<T> expectSuccess(Consumer<R> assertResult);
}
