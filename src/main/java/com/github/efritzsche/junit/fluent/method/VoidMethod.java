package com.github.efritzsche.junit.fluent.method;

/**
 * Equivalent to {@link java.util.function.Consumer Consumer} that can throw exceptions.
 *
 * @param <T> argument type
 */
@FunctionalInterface
public interface VoidMethod<T> {

    void apply(T target) throws Throwable;
}
