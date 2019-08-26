package com.github.efritzsche.declunit.fluent;

public interface TestDataExpectedException {

    /**
     * Expects the test method to fail throwing an exception of the given type.
     *
     * @param expectedException type of the expected exception
     * @return next step: build next test or finish
     */
    TestCreator expectFailure(Class<? extends Throwable> expectedException);
}
