package com.github.efritzsche.declunit.builder;

import java.util.List;
import java.util.function.Function;

import com.github.efritzsche.declunit.DeclTest;
import com.github.efritzsche.declunit.TestData;

public class TestDataBuilder<T, R> {

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

    public TestDataBuilder<T, R> target(T target) {
        data.setTarget(target);
        return this;
    }

    public TestDataExpectedBuilder<T, R> apply(Function<T, R> method) {
        data.setMethod(method);
        return new TestDataExpectedBuilder<>(this);
    }

    public <NT> TestDataBuilder<NT, Object> add(String description) {
        rootBuilder.checkpoint(this);
        return rootBuilder.add(description);
    }

    public List<DeclTest> build() {
        rootBuilder.checkpoint(this);
        return rootBuilder.build();
    }
}
