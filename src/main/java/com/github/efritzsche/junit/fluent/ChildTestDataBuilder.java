package com.github.efritzsche.junit.fluent;

import java.util.function.Consumer;

import com.github.efritzsche.junit.fluent.TestData.TargetTransform;
import com.github.efritzsche.junit.fluent.api.ChildTestCreator;
import com.github.efritzsche.junit.fluent.api.ChildTestDataExpectedNoResult;
import com.github.efritzsche.junit.fluent.api.ChildTestDataExpectedResult;
import com.github.efritzsche.junit.fluent.api.ChildTestDataMethod;
import com.github.efritzsche.junit.fluent.api.ChildTestDataOptionalTargetSetup;
import com.github.efritzsche.junit.fluent.api.TestDataTarget;
import com.github.efritzsche.junit.fluent.method.Method;
import com.github.efritzsche.junit.fluent.method.Method.Method1;
import com.github.efritzsche.junit.fluent.method.Method.Method2;
import com.github.efritzsche.junit.fluent.method.Method.Method3;
import com.github.efritzsche.junit.fluent.method.VoidMethod;
import com.github.efritzsche.junit.fluent.method.VoidMethod.VoidMethod1;
import com.github.efritzsche.junit.fluent.method.VoidMethod.VoidMethod2;
import com.github.efritzsche.junit.fluent.method.VoidMethod.VoidMethod3;

/**
 * Internal class implementing the fluent builder API for child tests.
 */
class ChildTestDataBuilder implements
        ChildTestDataOptionalTargetSetup<Object>,
        ChildTestDataExpectedNoResult<Object>,
        ChildTestDataExpectedResult<Object, Object>,
        ChildTestCreator<Object> {

    private TestDataBuilder parentBuilder;
    private TestData parent;
    private TestData data;


    ChildTestDataBuilder(TestDataBuilder parentBuilder, TestData parent, String description) {
        if (description == null)
            throw new NullPointerException("description");
        if (description.isEmpty())
            throw new IllegalArgumentException("description is empty");

        data = new TestData();
        data.setDescription(description);
        data.setTargetSupplier(parent.getTargetSupplier());
        data.setTargetTransform(parent.getTargetTransform());

        this.parentBuilder = parentBuilder;
        this.parent = parent;
    }


    @Override
    public ChildTestDataMethod<Object> prepareTarget(Consumer<Object> prepare) {
        if (prepare == null)
            throw new NullPointerException("prepare");

        // chain transform if necessary
        TargetTransform<Object> transform = target -> {prepare.accept(target); return target;};
        data.setTargetTransform(
                data.getTargetTransform() != null
                        ? data.getTargetTransform().then(transform)
                        : transform);
        return this;
    }

    @Override
    public ChildTestDataMethod<Object> transformTarget(TargetTransform<Object> transform) {
        if (transform == null)
            throw new NullPointerException("transform");

        // chain transform if necessary
        data.setTargetTransform(
                data.getTargetTransform() != null
                        ? data.getTargetTransform().then(transform)
                        : transform);
        return this;
    }

    @Override
    public ChildTestDataExpectedNoResult<Object> applyVoid(VoidMethod<Object> method) {
        if (method == null)
            throw new NullPointerException("method");

        data.setMethod(target -> {method.apply(target); return null;});
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <R> ChildTestDataExpectedResult<Object, R> apply(Method<Object, R> method) {
        if (method == null)
            throw new NullPointerException("method");

        data.setMethod((Method<Object, Object>) method);
        return (ChildTestDataExpectedResult<Object, R>) this;
    }

    @Override
    public ChildTestCreator<Object> expectFailure(Class<? extends Throwable> expectedException) {
        if (expectedException == null)
            throw new NullPointerException("expectedException");

        data.setExpectedException(expectedException);
        return this;
    }

    @Override
    public ChildTestCreator<Object> expectSuccess() {
        data.setExpectedResult(null);
        return this;
    }

    @Override
    public ChildTestCreator<Object> expectSuccess(Object expectedResult) {
        data.setExpectedResult(expectedResult);
        return this;
    }

    @Override
    public ChildTestCreator<Object> expectSuccess(Consumer<Object> assertResult) {
        if (assertResult == null)
            throw new NullPointerException("assertResult");

        data.setAssertResult(assertResult);
        return this;
    }

    @Override
    public TestDataTarget newTest(String description) {
        parent.addChildTest(data);
        return parentBuilder.newTest(description);
    }

    @Override
    public ChildTestDataOptionalTargetSetup<Object> childTest(String description) {
        parent.addChildTest(data);
        return parentBuilder.childTest(description);
    }

    @Override
    public TestContainer build() {
        parent.addChildTest(data);
        return parentBuilder.build();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <R, A0> ChildTestDataExpectedResult<Object, R> apply(Method1<Object, R, A0> method, A0 arg0) {
        if (method == null)
            throw new NullPointerException("method");

        data.setMethod(target -> method.apply(target, arg0));
        return (ChildTestDataExpectedResult<Object, R>) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <R, A0, A1> ChildTestDataExpectedResult<Object, R> apply(
            Method2<Object, R, A0, A1> method, A0 arg0, A1 arg1) {
        if (method == null)
            throw new NullPointerException("method");

        data.setMethod(target -> method.apply(target, arg0, arg1));
        return (ChildTestDataExpectedResult<Object, R>) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <R, A0, A1, A2> ChildTestDataExpectedResult<Object, R> apply(
            Method3<Object, R, A0, A1, A2> method, A0 arg0, A1 arg1, A2 arg2) {
        if (method == null)
            throw new NullPointerException("method");

        data.setMethod(target -> method.apply(target, arg0, arg1, arg2));
        return (ChildTestDataExpectedResult<Object, R>) this;
    }

    @Override
    public <A0> ChildTestDataExpectedNoResult<Object> applyVoid(VoidMethod1<Object, A0> method, A0 arg0) {
        if (method == null)
            throw new NullPointerException("method");

        data.setMethod(target -> {method.apply(target, arg0); return null;});
        return this;
    }

    @Override
    public <A0, A1> ChildTestDataExpectedNoResult<Object> applyVoid(
            VoidMethod2<Object, A0, A1> method, A0 arg0, A1 arg1) {
        if (method == null)
            throw new NullPointerException("method");

        data.setMethod(target -> {method.apply(target, arg0, arg1); return null;});
        return this;
    }

    @Override
    public <A0, A1, A2> ChildTestDataExpectedNoResult<Object> applyVoid(
            VoidMethod3<Object, A0, A1, A2> method, A0 arg0, A1 arg1, A2 arg2) {
        if (method == null)
            throw new NullPointerException("method");

        data.setMethod(target -> {method.apply(target, arg0, arg1, arg2); return null;});
        return this;
    }
}
