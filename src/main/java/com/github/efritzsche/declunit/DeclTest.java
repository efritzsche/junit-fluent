package com.github.efritzsche.declunit;

import java.util.ArrayList;
import java.util.List;

import com.github.efritzsche.declunit.builder.DeclTestBuilder;

public class DeclTest {

    public static DeclTestBuilder builder() {
        return new DeclTestBuilder();
    }


    private List<TestData<?, ?>> tests;

    public DeclTest() {
        tests = new ArrayList<>();
    }

    public List<TestData<?, ?>> getTests() {
        return tests;
    }

    public void addTest(TestData<?, ?> test) {
        tests.add(test);
    }
}
