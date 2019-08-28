package com.github.efritzsche.junit.fluent.method;

/**
 * Equivalent to {@link java.util.function.Function Function} that can throw exceptions.
 *
 * @param <T> argument type
 * @param <R> result type
 */
@FunctionalInterface
public interface Method<T, R> {

    R apply(T target) throws Throwable;


    @FunctionalInterface
    interface Method1<T, R, A0> {

        R apply(T target, A0 arg0) throws Throwable;
    }

    @FunctionalInterface
    interface Method2<T, R, A0, A1> {

        R apply(T target, A0 arg0, A1 arg1) throws Throwable;
    }

    @FunctionalInterface
    interface Method3<T, R, A0, A1, A2> {

        R apply(T target, A0 arg0, A1 arg1, A2 arg2) throws Throwable;
    }
}
