package com.github.efritzsche.declunit;

import java.util.function.Function;

public class TestData<T, R> {

    private String description;
    private T target;
    private Function<T, R> method;
    private R expectedResult;
    private Class<? extends Throwable> expectedException;


    public TestData() {}


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public T getTarget() {
        return target;
    }

    public void setTarget(T target) {
        this.target = target;
    }

    public Function<T, R> getMethod() {
        return method;
    }

    public void setMethod(Function<T, R> method) {
        this.method = method;
    }

    public R getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(R expectedResult) {
        this.expectedResult = expectedResult;
    }

    public Class<? extends Throwable> getExpectedException() {
        return expectedException;
    }

    public void setExpectedException(Class<? extends Throwable> expectedException) {
        this.expectedException = expectedException;
    }
}
