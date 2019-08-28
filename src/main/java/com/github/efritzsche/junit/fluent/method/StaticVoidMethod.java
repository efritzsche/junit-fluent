package com.github.efritzsche.junit.fluent.method;

/**
 * Equivalent to {@link Runnable} that can throw exceptions.
 */
@FunctionalInterface
public interface StaticVoidMethod {

    void apply() throws Throwable;


    @FunctionalInterface
    interface StaticVoidMethod1<A0> {

        void apply(A0 arg0) throws Throwable;
    }

    @FunctionalInterface
    interface StaticVoidMethod2<A0, A1> {

        void apply(A0 arg0, A1 arg1) throws Throwable;
    }

    @FunctionalInterface
    interface StaticVoidMethod3<A0, A1, A2> {

        void apply(A0 arg0, A1 arg1, A2 arg2) throws Throwable;
    }
}
