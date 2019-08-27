package com.github.efritzsche.junit.fluent;

import java.util.function.Consumer;
import java.util.function.Function;

import com.github.efritzsche.junit.fluent.method.Method;

/**
 * Data model defining a dynamic test. Configured and build by {@link TestBuilder}.
 */
public class TestData {

    private String description;
    private Object target;
    private Function<Object, Object> arrangeTarget;
    private Method<Object, Object> method;
    private Class<? extends Throwable> expectedException;
    private Object expectedResult;
    private Consumer<Object> assertResult;


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

    public Function<Object, Object> getArrangeTarget() {
        return arrangeTarget;
    }

    public void setArrangeTarget(Function<Object, Object> arrangeTarget) {
        this.arrangeTarget = arrangeTarget;
    }

    public Method<Object, Object> getMethod() {
        return method;
    }

    public void setMethod(Method<Object, Object> method) {
        this.method = method;
    }

    public Class<? extends Throwable> getExpectedException() {
        return expectedException;
    }

    public void setExpectedException(Class<? extends Throwable> expectedException) {
        this.expectedException = expectedException;
    }

    public Object getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(Object expectedResult) {
        this.expectedResult = expectedResult;
    }

    public Consumer<Object> getAssertResult() {
        return assertResult;
    }

    public void setAssertResult(Consumer<Object> assertResult) {
        this.assertResult = assertResult;
    }
}
