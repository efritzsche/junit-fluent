package com.github.efritzsche.declunit.fluent;

import com.github.efritzsche.declunit.TestContainer;

public interface TestCreator {

    TestDataTarget newTest(String description);

    TestContainer build();
}
