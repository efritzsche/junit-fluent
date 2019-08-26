package com.github.efritzsche.declunit.builder;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import com.github.efritzsche.declunit.TestData;
import org.junit.jupiter.api.DynamicTest;

public class TestDataBuilder implements
        TestDataTarget,
        TestDataMethod<Object>,
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
    public <T> TestDataMethod<T> target(T target) {
        if (target == null)
            throw new NullPointerException("target");

        data.setTarget(target);
        return (TestDataMethod<T>) this;
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
    public TestDataTarget newTest(String description) {
        rootBuilder.addTest(data);
        return rootBuilder.newTest(description);
    }

    @Override
    public List<DynamicTest> build() {
        rootBuilder.addTest(data);
        return rootBuilder.build();
    }
}
