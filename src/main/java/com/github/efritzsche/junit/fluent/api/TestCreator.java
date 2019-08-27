package com.github.efritzsche.junit.fluent.api;

import com.github.efritzsche.junit.fluent.TestContainer;

public interface TestCreator {

    /**
     * Finishes the setup of the current test and starts the build process for a new one.
     *
     * @param description description of the test
     * @return start of build chain of next test
     */
    TestDataTarget newTest(String description);

    /**
     * Finishes the setup of the current test and builds a collection of dynamic tests for all
     * test configured by this build chain.
     *
     * @return collection of dynamic tests
     */
    TestContainer build();
}
