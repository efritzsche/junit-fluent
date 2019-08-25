package com.github.efritzsche.declunit.builder;

import java.util.List;

import org.junit.jupiter.api.DynamicTest;

public interface TestCreator {

    TestDataTarget newTest(String description);

    List<DynamicTest> build();
}
