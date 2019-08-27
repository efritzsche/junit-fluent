package com.github.efritzsche.junit.fluent.api;

public interface TestDataExpectedNoResult extends TestDataExpectedException {

    /**
     * Expects the test method to succeed with no result.
     *
     * @return next step: build next test or finish
     */
    TestCreator expectSuccess();
}
