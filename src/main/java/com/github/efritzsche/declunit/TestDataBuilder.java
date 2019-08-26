package com.github.efritzsche.declunit;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import com.github.efritzsche.declunit.fluent.TestCreator;
import com.github.efritzsche.declunit.fluent.TestDataArrangeTarget;
import com.github.efritzsche.declunit.fluent.TestDataExpectedNoResult;
import com.github.efritzsche.declunit.fluent.TestDataExpectedResult;
import com.github.efritzsche.declunit.fluent.TestDataMethod;
import com.github.efritzsche.declunit.fluent.TestDataStaticMethod;
import com.github.efritzsche.declunit.fluent.TestDataTarget;
import org.junit.jupiter.api.DynamicTest;

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
        Function<Object, Object> current = data.getArrangeTarget();
        data.setArrangeTarget(current != null
                ? current.andThen(target -> {arrangeTarget.accept(target); return target;})
                : target -> {arrangeTarget.accept(target); return target;});
        return this;
    }

    @Override
    public TestDataMethod<Object> replaceTarget(Function<Object, Object> arrangeTarget) {
        Function<Object, Object> current = data.getArrangeTarget();
        data.setArrangeTarget(current != null ? current.andThen(arrangeTarget) : arrangeTarget);
        return this;
    }

    @Override
    public TestDataExpectedNoResult applyVoid(Consumer<Object> method) {
        if (method == null)
            throw new NullPointerException("method");

        data.setMethod(target -> {method.accept(target); return null;});
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <R> TestDataExpectedResult<R> apply(Function<Object, R> method) {
        if (method == null)
            throw new NullPointerException("method");

        data.setMethod((Function<Object, Object>) method);
        return (TestDataExpectedResult<R>) this;
    }

    @Override
    public TestDataExpectedNoResult applyVoid(Runnable method) {
        if (method == null)
            throw new NullPointerException("method");

        data.setMethod(target -> {method.run(); return null;});
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <R> TestDataExpectedResult<R> apply(Supplier<R> method) {
        if (method == null)
            throw new NullPointerException("method");

        data.setMethod(target -> method.get());
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
        data.setAssertResult(assertResult);
        return this;
    }

    @Override
    public TestDataTarget newTest(String description) {
        rootBuilder.addTest(data);
        return rootBuilder.newTest(description);
    }

    @Override
    public List<DynamicTest> build() {
        rootBuilder.addTest(data);
        return rootBuilder.build();
    }


    private class TestDataStaticBuilder implements TestDataMethod<Class<?>> {

        @Override
        public TestDataExpectedNoResult applyVoid(Consumer<Class<?>> method) {
            if (method == null)
                throw new NullPointerException("method");

            data.setMethod(target -> {method.accept((Class<?>) target); return null;});
            return TestDataBuilder.this;
        }

        @Override
        @SuppressWarnings("unchecked")
        public <R> TestDataExpectedResult<R> apply(Function<Class<?>, R> method) {
            if (method == null)
                throw new NullPointerException("method");

            data.setMethod(target -> method.apply((Class<?>) target));
            return (TestDataExpectedResult<R>) TestDataBuilder.this;
        }
    }
}
