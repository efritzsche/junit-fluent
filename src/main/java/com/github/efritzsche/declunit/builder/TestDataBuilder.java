package com.github.efritzsche.declunit.builder;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import com.github.efritzsche.declunit.DeclTest;
import com.github.efritzsche.declunit.TestData;

public class TestDataBuilder<T, R> implements
        TestDataTarget<T, R>,
        TestDataMethod<T, R>,
        TestDataExpected<T, R>,
        TestDataCreator<T, R> {

    private TestBuilder rootBuilder;
    private TestData<T, R> data;


    TestDataBuilder(TestBuilder rootBuilder, String description) {
        if (description == null)
            throw new NullPointerException("description");
        if (description.isEmpty())
            throw new IllegalArgumentException("description is empty");

        data = new TestData<>();
        data.setDescription(description);

        this.rootBuilder = rootBuilder;
    }


    TestData<T, R> getData() {
        return data;
    }

    @Override
    public TestDataMethod<T, R> target(T target) {
        if (target == null)
            throw new NullPointerException("target");

        data.setTarget(target);
        return this;
    }

    @Override
    public TestDataExpected<T, R> apply(Function<T, R> method) {
        if (method == null)
            throw new NullPointerException("method");

        data.setMethod(method);
        return this;
    }

    @Override
    public TestDataExpectedVoid<T, R> applyVoid(Consumer<T> method) {
        if (method == null)
            throw new NullPointerException("method");

        data.setMethod(target -> {method.accept(target); return null;});
        return this;
    }

    @Override
    public TestDataCreator<T, R> expect(R expectedResult) {
        data.setExpectedResult(expectedResult);
        return this;
    }

    @Override
    public TestDataCreator<T, R> expectException(Class<? extends Throwable> expectedException) {
        if (expectedException == null)
            throw new NullPointerException("expectedException");

        data.setExpectedException(expectedException);
        return this;
    }

    @Override
    public <NT> TestDataTarget<NT, Object> add(String description) {
        rootBuilder.checkpoint(this);
        return rootBuilder.add(description);
    }

    @Override
    public List<DeclTest<?, ?>> build() {
        rootBuilder.checkpoint(this);
        return rootBuilder.build();
    }
}
