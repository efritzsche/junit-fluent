package com.github.efritzsche.declunit.builder;

public interface TestDataTarget {

    <T> TestDataMethod<T> target(T target);
}
