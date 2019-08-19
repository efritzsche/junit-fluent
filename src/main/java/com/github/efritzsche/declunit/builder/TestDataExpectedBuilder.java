package com.github.efritzsche.declunit.builder;

public class TestDataExpectedBuilder<T, R> {

    private TestDataBuilder<T, R> builder;


    TestDataExpectedBuilder(TestDataBuilder<T, R> builder) {
        this.builder = builder;
    }


    public TestDataBuilder<T, R> expect(R expectedResult) {
        builder.getData().setExpectedResult(expectedResult);
        return builder;
    }

    public TestDataBuilder<T, R> expectVoid() {
        builder.getData().setExpectedResult(null);
        return builder;
    }

    public TestDataBuilder<T, R> expectException(Class<? extends Throwable> expectedException) {
        builder.getData().setExpectedException(expectedException);
        return builder;
    }
}
