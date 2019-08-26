package com.github.efritzsche.declunit.builder;

public interface TestDataExpectedResult<R> extends TestDataExpectedException {

    TestCreator expectSuccess(R expectedResult);
}
