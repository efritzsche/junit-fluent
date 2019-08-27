package com.github.efritzsche.junit.fluent.method;

/**
 * Equivalent to {@link java.util.function.Supplier Supplier} that can throw exceptions.
 *
 * @param <R> result type
 */
@FunctionalInterface
public interface StaticMethod<R> {

    R apply() throws Throwable;
}
