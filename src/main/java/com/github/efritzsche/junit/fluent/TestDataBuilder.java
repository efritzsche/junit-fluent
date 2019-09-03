package com.github.efritzsche.junit.fluent;

import java.util.function.Consumer;
import java.util.function.Function;

import com.github.efritzsche.junit.fluent.api.TestCreator;
import com.github.efritzsche.junit.fluent.api.TestDataExpectedNoResult;
import com.github.efritzsche.junit.fluent.api.TestDataExpectedResult;
import com.github.efritzsche.junit.fluent.api.TestDataMethod;
import com.github.efritzsche.junit.fluent.api.TestDataStaticMethod;
import com.github.efritzsche.junit.fluent.api.TestDataTarget;
import com.github.efritzsche.junit.fluent.api.TestDataTarget.TestDataMethodOrOptionalTargetSetup;
import com.github.efritzsche.junit.fluent.method.Method;
import com.github.efritzsche.junit.fluent.method.Method.Method1;
import com.github.efritzsche.junit.fluent.method.Method.Method2;
import com.github.efritzsche.junit.fluent.method.Method.Method3;
import com.github.efritzsche.junit.fluent.method.StaticMethod;
import com.github.efritzsche.junit.fluent.method.StaticMethod.StaticMethod1;
import com.github.efritzsche.junit.fluent.method.StaticMethod.StaticMethod2;
import com.github.efritzsche.junit.fluent.method.StaticMethod.StaticMethod3;
import com.github.efritzsche.junit.fluent.method.StaticVoidMethod;
import com.github.efritzsche.junit.fluent.method.StaticVoidMethod.StaticVoidMethod1;
import com.github.efritzsche.junit.fluent.method.StaticVoidMethod.StaticVoidMethod2;
import com.github.efritzsche.junit.fluent.method.StaticVoidMethod.StaticVoidMethod3;
import com.github.efritzsche.junit.fluent.method.VoidMethod;
import com.github.efritzsche.junit.fluent.method.VoidMethod.VoidMethod1;
import com.github.efritzsche.junit.fluent.method.VoidMethod.VoidMethod2;
import com.github.efritzsche.junit.fluent.method.VoidMethod.VoidMethod3;

/**
 * Internal class implementing the fluent builder API.
 *
 * @see TestBuilder
 */
class TestDataBuilder implements
        TestDataTarget,
        TestDataMethodOrOptionalTargetSetup<Object>,
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
    public <T> TestDataMethodOrOptionalTargetSetup<T> target(T target) {
        if (target == null)
            throw new NullPointerException("target");

        data.setTargetSupplier(() -> target);
        return (TestDataMethodOrOptionalTargetSetup<T>) this;
    }

    @Override
    public <T> TestDataStaticMethod target(Class<T> targetClass) {
        if (targetClass == null)
            throw new NullPointerException("targetClass");

        data.setTargetSupplier(() -> targetClass);
        return this;
    }

    @Override
    public TestDataMethod<Object> prepareTarget(Consumer<Object> prepare) {
        if (prepare == null)
            throw new NullPointerException("prepare");

        data.setTargetTransform(target -> {prepare.accept(target); return target;});
        return this;
    }

    @Override
    public TestDataMethod<Object> transformTarget(Function<Object, Object> transform) {
        if (transform == null)
            throw new NullPointerException("transform");

        data.setTargetTransform(transform);
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

    @Override
    @SuppressWarnings("unchecked")
    public <R, A0> TestDataExpectedResult<R> apply(Method1<Object, R, A0> method, A0 arg0) {
        if (method == null)
            throw new NullPointerException("method");

        data.setMethod(target -> method.apply(target, arg0));
        return (TestDataExpectedResult<R>) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <R, A0, A1> TestDataExpectedResult<R> apply(
            Method2<Object, R, A0, A1> method, A0 arg0, A1 arg1) {
        if (method == null)
            throw new NullPointerException("method");

        data.setMethod(target -> method.apply(target, arg0, arg1));
        return (TestDataExpectedResult<R>) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <R, A0, A1, A2> TestDataExpectedResult<R> apply(
            Method3<Object, R, A0, A1, A2> method, A0 arg0, A1 arg1, A2 arg2) {
        if (method == null)
            throw new NullPointerException("method");

        data.setMethod(target -> method.apply(target, arg0, arg1, arg2));
        return (TestDataExpectedResult<R>) this;
    }

    @Override
    public <A0> TestDataExpectedNoResult applyVoid(VoidMethod1<Object, A0> method, A0 arg0) {
        if (method == null)
            throw new NullPointerException("method");

        data.setMethod(target -> {method.apply(target, arg0); return null;});
        return this;
    }

    @Override
    public <A0, A1> TestDataExpectedNoResult applyVoid(
            VoidMethod2<Object, A0, A1> method, A0 arg0, A1 arg1) {
        if (method == null)
            throw new NullPointerException("method");

        data.setMethod(target -> {method.apply(target, arg0, arg1); return null;});
        return this;
    }

    @Override
    public <A0, A1, A2> TestDataExpectedNoResult applyVoid(
            VoidMethod3<Object, A0, A1, A2> method, A0 arg0, A1 arg1, A2 arg2) {
        if (method == null)
            throw new NullPointerException("method");

        data.setMethod(target -> {method.apply(target, arg0, arg1, arg2); return null;});
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <R, A0> TestDataExpectedResult<R> apply(StaticMethod1<R, A0> method, A0 arg0) {
        if (method == null)
            throw new NullPointerException("method");

        data.setMethod(target -> method.apply(arg0));
        return (TestDataExpectedResult<R>) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <R, A0, A1> TestDataExpectedResult<R> apply(
            StaticMethod2<R, A0, A1> method, A0 arg0, A1 arg1) {
        if (method == null)
            throw new NullPointerException("method");

        data.setMethod(target -> method.apply(arg0, arg1));
        return (TestDataExpectedResult<R>) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <R, A0, A1, A2> TestDataExpectedResult<R> apply(
            StaticMethod3<R, A0, A1, A2> method, A0 arg0, A1 arg1, A2 arg2) {
        if (method == null)
            throw new NullPointerException("method");

        data.setMethod(target -> method.apply(arg0, arg1, arg2));
        return (TestDataExpectedResult<R>) this;
    }

    @Override
    public <A0> TestDataExpectedNoResult applyVoid(StaticVoidMethod1<A0> method, A0 arg0) {
        if (method == null)
            throw new NullPointerException("method");

        data.setMethod(target -> {method.apply(arg0); return null;});
        return this;
    }

    @Override
    public <A0, A1> TestDataExpectedNoResult applyVoid(
            StaticVoidMethod2<A0, A1> method, A0 arg0, A1 arg1) {
        if (method == null)
            throw new NullPointerException("method");

        data.setMethod(target -> {method.apply(arg0, arg1); return null;});
        return this;
    }

    @Override
    public <A0, A1, A2> TestDataExpectedNoResult applyVoid(
            StaticVoidMethod3<A0, A1, A2> method, A0 arg0, A1 arg1, A2 arg2) {
        if (method == null)
            throw new NullPointerException("method");

        data.setMethod(target -> {method.apply(arg0, arg1, arg2); return null;});
        return this;
    }
}
