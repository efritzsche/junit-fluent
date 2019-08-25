package com.github.efritzsche.declunit.builder;

import java.util.List;

import com.github.efritzsche.declunit.DeclTest;

public interface TestCreator {

    TestDataTarget newTest(String description);

    List<DeclTest> build();
}
