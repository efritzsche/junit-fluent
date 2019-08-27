package com.github.efritzsche.junit.fluent;

import java.util.ArrayList;
import java.util.List;

import com.github.efritzsche.junit.fluent.api.TestDataTarget;

/**
 * Entry point for fluent test building. Use {@link #newTest(String)} to start building tests.
 */
public class TestBuilder {

    /**
     * Starts the build process of one or more tests. The fluent interfaces guide through the setup.
     * Setting of all required parameters and type safety is forced by the API.
     *
     * @param description description of the test
     * @return start of build chain of first test
     */
    public static TestDataTarget newTest(String description) {
        TestBuilder rootBuilder = new TestBuilder();
        return new TestDataBuilder(rootBuilder, description);
    }


    private final List<TestData> tests;


    private TestBuilder() {
        tests = new ArrayList<>();
    }


    void addTest(TestData data) {
        tests.add(data);
    }

    TestContainer buildAll() {
        return new TestContainer(tests);
    }
}
