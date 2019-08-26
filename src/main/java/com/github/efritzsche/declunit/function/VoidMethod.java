package com.github.efritzsche.declunit.function;

@FunctionalInterface
public interface VoidMethod<T> {

    void apply(T target) throws Throwable;
}
