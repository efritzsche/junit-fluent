package com.github.efritzsche.junit.fluent.method;

/**
 * Equivalent to {@link java.util.function.Consumer Consumer} that can throw exceptions.
 *
 * @param <T> argument type
 */
@FunctionalInterface
public interface VoidMethod<T> {

    void apply(T target) throws Throwable;


    @FunctionalInterface
    interface VoidMethod1<T, A0> {

        void apply(T target, A0 arg0) throws Throwable;
    }

    @FunctionalInterface
    interface VoidMethod2<T, A0, A1> {

        void apply(T target, A0 arg0, A1 arg1) throws Throwable;
    }

    @FunctionalInterface
    interface VoidMethod3<T, A0, A1, A2> {

        void apply(T target, A0 arg0, A1 arg1, A2 arg2) throws Throwable;
    }
}
