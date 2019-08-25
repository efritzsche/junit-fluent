package com.github.efritzsche.declunit.builder;

import java.util.ArrayList;
import java.util.List;

import com.github.efritzsche.declunit.DeclTest;
import com.github.efritzsche.declunit.TestData;

public class TestBuilder {

    private List<TestData> tests;


    public TestBuilder() {
        tests = new ArrayList<>();
    }


    void checkpoint(TestDataBuilder builder) {
        tests.add(builder.getData());
    }

    List<DeclTest> build() {
        List<DeclTest> declTests = new ArrayList<>(tests.size());

        for (TestData test : tests) declTests.add(new DeclTest(test));

        return declTests;
    }

    public TestDataTarget add(String description) {
        return new TestDataBuilder(this, description);
    }
}
