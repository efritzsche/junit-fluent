package com.github.efritzsche.declunit.function;

/**
 * Equivalent to {@link Runnable} that can throw exceptions.
 */
@FunctionalInterface
public interface StaticVoidMethod {

    void apply() throws Throwable;
}
