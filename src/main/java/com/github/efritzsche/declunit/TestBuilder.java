package com.github.efritzsche.declunit;

import java.util.ArrayList;
import java.util.List;

import com.github.efritzsche.declunit.fluent.TestCreator;
import com.github.efritzsche.declunit.fluent.TestDataTarget;
import org.junit.jupiter.api.DynamicTest;

public class TestBuilder implements TestCreator {

    private final List<TestData> tests;


    public TestBuilder() {
        tests = new ArrayList<>();
    }


    void addTest(TestData data) {
        tests.add(data);
    }

    @Override
    public TestDataTarget newTest(String description) {
        return new TestDataBuilder(this, description);
    }

    @Override
    public List<DynamicTest> build() {
        List<DynamicTest> testCases = new ArrayList<>(tests.size());

        for (TestData test : tests) {
            testCases.add(DynamicTest.dynamicTest(test.getDescription(), new TestCase(test)));
        }

        return testCases;
    }
}
