package com.github.efritzsche.junit.fluent.api;

import java.util.function.Consumer;

public interface TestDataExpectedResult<R> extends TestDataExpectedException {

    /**
     * Expects the test method to succeed with a result equal to the given value.
     *
     * @param expectedResult value of the expected result
     * @return next step: build next test or finish
     */
    TestCreator expectSuccess(R expectedResult);

    /**
     * Calls the given {@link Consumer} using the actual result of the test as the argument. If the
     * result should be rejected an unchecked exception has to be thrown. It is advised but not
     * required to throw an {@link org.opentest4j.AssertionFailedError AssertionFailedError}.
     *
     * @param assertResult check of actual test result
     * @return next step: build next test or finish
     */
    TestCreator expectSuccess(Consumer<R> assertResult);
}
