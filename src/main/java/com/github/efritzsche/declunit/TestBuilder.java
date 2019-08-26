package com.github.efritzsche.declunit;

import java.util.ArrayList;
import java.util.List;

import com.github.efritzsche.declunit.fluent.TestDataTarget;

public class TestBuilder {

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
