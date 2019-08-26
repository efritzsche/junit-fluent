package com.github.efritzsche.declunit.function;

/**
 * Equivalent to {@link java.util.function.Supplier Supplier} that can throw exceptions.
 *
 * @param <R> result type
 */
@FunctionalInterface
public interface StaticMethod<R> {

    R apply() throws Throwable;
}
