package com.github.efritzsche.declunit.function;

@FunctionalInterface
public interface StaticMethod<R> {

    R apply() throws Throwable;
}
