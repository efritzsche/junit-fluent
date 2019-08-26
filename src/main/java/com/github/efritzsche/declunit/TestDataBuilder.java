package com.github.efritzsche.declunit;

import java.util.function.Consumer;
import java.util.function.Function;

import com.github.efritzsche.declunit.fluent.TestCreator;
import com.github.efritzsche.declunit.fluent.TestDataArrangeTarget;
import com.github.efritzsche.declunit.fluent.TestDataExpectedNoResult;
import com.github.efritzsche.declunit.fluent.TestDataExpectedResult;
import com.github.efritzsche.declunit.fluent.TestDataMethod;
import com.github.efritzsche.declunit.fluent.TestDataStaticMethod;
import com.github.efritzsche.declunit.fluent.TestDataTarget;
import com.github.efritzsche.declunit.function.Method;
import com.github.efritzsche.declunit.function.StaticMethod;
import com.github.efritzsche.declunit.function.StaticVoidMethod;
import com.github.efritzsche.declunit.function.VoidMethod;

/**
 * Internal class implementing the fluent builder API.
 *
 * @see TestBuilder
 */
class TestDataBuilder implements
        TestDataTarget,
        TestDataArrangeTarget<Object>,
        TestDataStaticMethod,
        TestDataExpectedNoResult,
        TestDataExpectedResult<Object>,
        TestCreator {

    private TestBuilder rootBuilder;
    private TestData data;


    TestDataBuilder(TestBuilder rootBuilder, String description) {
        if (description == null)
            throw new NullPointerException("description");
        if (description.isEmpty())
            throw new IllegalArgumentException("description is empty");

        data = new TestData();
        data.setDescription(description);

        this.rootBuilder = rootBuilder;
    }


    @Override
    @SuppressWarnings("unchecked")
    public <T> TestDataArrangeTarget<T> target(T target) {
        if (target == null)
            throw new NullPointerException("target");

        data.setTarget(target);
        return (TestDataArrangeTarget<T>) this;
    }

    @Override
    public <T> TestDataStaticMethod target(Class<T> targetClass) {
        if (targetClass == null)
            throw new NullPointerException("targetClass");

        data.setTarget(targetClass);
        return this;
    }

    @Override
    public TestDataMethod<Object> prepareTarget(Consumer<Object> arrangeTarget) {
        if (arrangeTarget == null)
            throw new NullPointerException("arrangeTarget");

        data.setArrangeTarget(target -> {arrangeTarget.accept(target); return target;});
        return this;
    }

    @Override
    public TestDataMethod<Object> replaceTarget(Function<Object, Object> arrangeTarget) {
        if (arrangeTarget == null)
            throw new NullPointerException("arrangeTarget");

        data.setArrangeTarget(arrangeTarget);
        return this;
    }

    @Override
    public TestDataExpectedNoResult applyVoid(VoidMethod<Object> method) {
        if (method == null)
            throw new NullPointerException("method");

        data.setMethod(target -> {method.apply(target); return null;});
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <R> TestDataExpectedResult<R> apply(Method<Object, R> method) {
        if (method == null)
            throw new NullPointerException("method");

        data.setMethod((Method<Object, Object>) method);
        return (TestDataExpectedResult<R>) this;
    }

    @Override
    public TestDataExpectedNoResult applyVoid(StaticVoidMethod method) {
        if (method == null)
            throw new NullPointerException("method");

        data.setMethod(target -> {method.apply(); return null;});
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <R> TestDataExpectedResult<R> apply(StaticMethod<R> method) {
        if (method == null)
            throw new NullPointerException("method");

        data.setMethod(target -> method.apply());
        return (TestDataExpectedResult<R>) this;
    }

    @Override
    public TestCreator expectFailure(Class<? extends Throwable> expectedException) {
        if (expectedException == null)
            throw new NullPointerException("expectedException");

        data.setExpectedException(expectedException);
        return this;
    }

    @Override
    public TestCreator expectSuccess() {
        data.setExpectedResult(null);
        return this;
    }

    @Override
    public TestCreator expectSuccess(Object expectedResult) {
        data.setExpectedResult(expectedResult);
        return this;
    }

    @Override
    public TestCreator expectSuccess(Consumer<Object> assertResult) {
        if (assertResult == null)
            throw new NullPointerException("assertResult");

        data.setAssertResult(assertResult);
        return this;
    }

    @Override
    public TestDataTarget newTest(String description) {
        rootBuilder.addTest(data);
        return new TestDataBuilder(rootBuilder, description);
    }

    @Override
    public TestContainer build() {
        rootBuilder.addTest(data);
        return rootBuilder.buildAll();
    }
}
