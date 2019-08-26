package com.github.efritzsche.declunit.fluent;

import java.util.function.Consumer;

public interface TestDataExpectedResult<R> extends TestDataExpectedException {

    TestCreator expectSuccess(R expectedResult);

    TestCreator expectSuccess(Consumer<R> assertResult);
}
