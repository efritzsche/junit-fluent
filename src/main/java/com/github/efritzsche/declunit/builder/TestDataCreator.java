package com.github.efritzsche.declunit.builder;

import java.util.List;

import com.github.efritzsche.declunit.DeclTest;

public interface TestDataCreator<T, R> {

    <NT> TestDataTarget<NT, Object> add(String description);

    List<DeclTest<?, ?>> build();
}
