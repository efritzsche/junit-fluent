package com.github.efritzsche.declunit.builder;

public interface TestDataExpected<R> extends TestDataExpectedVoid {

    TestCreator expect(R expectedResult);
}
