package com.github.efritzsche.declunit.function;

@FunctionalInterface
public interface Method<T, R> {

    R apply(T target) throws Throwable;
}
