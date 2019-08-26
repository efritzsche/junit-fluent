package com.github.efritzsche.declunit.fluent;

public interface TestDataExpectedException {

    TestCreator expectFailure(Class<? extends Throwable> expectedException);
}
