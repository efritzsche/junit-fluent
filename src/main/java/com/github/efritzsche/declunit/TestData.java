package com.github.efritzsche.declunit;

import java.util.function.Function;

public class TestData {

    private String description;
    private Object target;
    private Function<Object, Object> method;
    private Object expectedResult;
    private Class<? extends Throwable> expectedException;


    public TestData() {}


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Function<Object, Object> getMethod() {
        return method;
    }

    public void setMethod(Function<Object, Object> method) {
        this.method = method;
    }

    public Object getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(Object expectedResult) {
        this.expectedResult = expectedResult;
    }

    public Class<? extends Throwable> getExpectedException() {
        return expectedException;
    }

    public void setExpectedException(Class<? extends Throwable> expectedException) {
        this.expectedException = expectedException;
    }
}
