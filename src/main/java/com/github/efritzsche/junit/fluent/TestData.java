package com.github.efritzsche.junit.fluent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import com.github.efritzsche.junit.fluent.method.Method;

/**
 * Data model defining a dynamic test. Configured and build by {@link TestBuilder}.
 */
public class TestData {

    private String description;
    private Supplier<Object> targetSupplier;
    private Function<Object, Object> targetTransform;

    private List<TestData> childTests;

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

    public Supplier<Object> getTargetSupplier() {
        return targetSupplier;
    }

    public void setTargetSupplier(Supplier<Object> targetSupplier) {
        this.targetSupplier = targetSupplier;
    }

    public Function<Object, Object> getTargetTransform() {
        return targetTransform;
    }

    public void setTargetTransform(Function<Object, Object> targetTransform) {
        this.targetTransform = targetTransform;
    }

    public boolean isParent() {
        return childTests != null;
    }

    public List<TestData> getChildTests() {
        return childTests;
    }

    public void addChildTest(TestData child) {
        if (childTests == null)
            childTests = new ArrayList<>();
        childTests.add(child);
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
