package com.github.efritzsche.declunit.builder;

public interface TestDataTarget<T, R> {

    TestDataMethod<T, R> target(T target);
}
