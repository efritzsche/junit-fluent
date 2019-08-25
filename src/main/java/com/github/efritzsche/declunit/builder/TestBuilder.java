package com.github.efritzsche.declunit.builder;

import java.util.ArrayList;
import java.util.List;

import com.github.efritzsche.declunit.DeclTest;
import com.github.efritzsche.declunit.TestData;

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
    public List<DeclTest> build() {
        List<DeclTest> declTests = new ArrayList<>(tests.size());

        for (TestData test : tests) declTests.add(new DeclTest(test));

        return declTests;
    }
}
