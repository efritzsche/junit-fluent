package com.github.efritzsche.junit.fluent.api;

public interface ChildTestDataExpectedNoResult<T>
        extends TestDataExpectedNoResult, ChildTestDataExpectedException<T> {

    @Override
    ChildTestCreator<T> expectSuccess();
}
