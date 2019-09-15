package com.github.efritzsche.junit.fluent.api;

import java.util.function.Consumer;

import com.github.efritzsche.junit.fluent.TestData.TargetTransform;

public interface ChildTestDataOptionalTargetSetup<T> extends ChildTestDataMethod<T> {

    ChildTestDataMethod<T> prepareTarget(Consumer<T> prepare);

    ChildTestDataMethod<T> transformTarget(TargetTransform<T> transform);
}
