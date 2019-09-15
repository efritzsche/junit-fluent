package com.github.efritzsche.junit.fluent.api;

public interface ChildTestDataExpectedException<T> extends TestDataExpectedException {

    @Override
    ChildTestCreator<T> expectFailure(Class<? extends Throwable> expectedException);
}
