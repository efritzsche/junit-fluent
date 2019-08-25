package com.github.efritzsche.declunit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;

public class TestCase implements Executable {

    private final TestData data;


    public TestCase(TestData data) {
        this.data = data;
    }


    @Override
    public void execute() {
        if (data.getExpectedException() != null) {
            Assertions.assertThrows(
                    data.getExpectedException(),
                    () -> data.getMethod().apply(data.getTarget())
            );
        } else {
            Object actualResult = data.getMethod().apply(data.getTarget());
            Assertions.assertEquals(data.getExpectedResult(), actualResult);
        }
    }

    @Override
    public String toString() {
        return data.getDescription();
    }
}
