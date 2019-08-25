package com.github.efritzsche.declunit.builder;

public interface TestDataExpected<R> extends TestDataExpectedVoid {

    TestDataCreator expect(R expectedResult);
}
