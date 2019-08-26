package com.github.efritzsche.declunit.function;

/**
 * Equivalent to {@link java.util.function.Function Function} that can throw exceptions.
 *
 * @param <T> argument type
 * @param <R> result type
 */
@FunctionalInterface
public interface Method<T, R> {

    R apply(T target) throws Throwable;
}
