package com.github.efritzsche.junit.fluent.method;

/**
 * Equivalent to {@link java.util.function.Supplier Supplier} that can throw exceptions.
 *
 * @param <R> result type
 */
@FunctionalInterface
public interface StaticMethod<R> {

    R apply() throws Throwable;


    @FunctionalInterface
    interface StaticMethod1<R, A0> {

        R apply(A0 arg0) throws Throwable;
    }

    @FunctionalInterface
    interface StaticMethod2<R, A0, A1> {

        R apply(A0 arg0, A1 arg1) throws Throwable;
    }

    @FunctionalInterface
    interface StaticMethod3<R, A0, A1, A2> {

        R apply(A0 arg0, A1 arg1, A2 arg2) throws Throwable;
    }
}
