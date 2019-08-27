package com.github.efritzsche.junit.fluent.method;

/**
 * Equivalent to {@link Runnable} that can throw exceptions.
 */
@FunctionalInterface
public interface StaticVoidMethod {

    void apply() throws Throwable;
}
