package com.github.efritzsche.declunit.builder;

public interface TestDataExpectedVoid {

    TestCreator expectException(Class<? extends Throwable> expectedException);
}
