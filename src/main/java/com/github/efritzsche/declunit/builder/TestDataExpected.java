package com.github.efritzsche.declunit.builder;

public interface TestDataExpected<T, R> extends TestDataExpectedVoid<T, R> {

    TestDataCreator<T, R> expect(R expectedResult);
}
