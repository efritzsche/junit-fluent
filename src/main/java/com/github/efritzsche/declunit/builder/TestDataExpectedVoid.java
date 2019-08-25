package com.github.efritzsche.declunit.builder;

public interface TestDataExpectedVoid<T, R> {

    TestDataCreator<T, R> expectException(Class<? extends Throwable> expectedException);
}
