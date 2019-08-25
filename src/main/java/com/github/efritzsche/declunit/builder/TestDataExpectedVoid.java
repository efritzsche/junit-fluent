package com.github.efritzsche.declunit.builder;

public interface TestDataExpectedVoid {

    TestDataCreator expectException(Class<? extends Throwable> expectedException);
}
