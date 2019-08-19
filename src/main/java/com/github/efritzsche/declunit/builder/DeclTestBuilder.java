package com.github.efritzsche.declunit.builder;

import com.github.efritzsche.declunit.DeclTest;

public class DeclTestBuilder {

    private DeclTest declTest;


    public DeclTestBuilder() {
        declTest = new DeclTest();
    }


    DeclTest checkpoint(TestDataBuilder<?, ?> builder) {
        declTest.addTest(builder.getData());
        return declTest;
    }

    public <T> TestDataBuilder<T, Object> add(String description) {
        return new TestDataBuilder<>(this, description);
    }
}
