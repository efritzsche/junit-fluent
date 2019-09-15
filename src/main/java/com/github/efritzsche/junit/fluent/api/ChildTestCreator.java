package com.github.efritzsche.junit.fluent.api;

public interface ChildTestCreator<T> extends TestCreator {

    ChildTestDataOptionalTargetSetup<T> childTest(String description);
}
