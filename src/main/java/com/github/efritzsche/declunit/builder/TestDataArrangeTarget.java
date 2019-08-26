package com.github.efritzsche.declunit.builder;

import java.util.function.Consumer;
import java.util.function.Function;

public interface TestDataArrangeTarget<T> extends TestDataMethod<T> {

    TestDataMethod<T> prepareTarget(Consumer<T> arrangeTarget);

    TestDataMethod<T> replaceTarget(Function<T, T> arrangeTarget);
}
