package com.github.efritzsche.declunit.builder;

public interface TestDataExpectedException {

    TestCreator expectFailure(Class<? extends Throwable> expectedException);
}
