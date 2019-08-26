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
        Object target = data.getArrangeTarget() != null
                ? data.getArrangeTarget().apply(data.getTarget())
                : data.getTarget();

        if (data.getExpectedException() != null) {
            Assertions.assertThrows(
                    data.getExpectedException(),
                    () -> data.getMethod().apply(target)
            );
        } else if (data.getAssertResult() != null) {
            Object actualResult = data.getMethod().apply(target);
            data.getAssertResult().accept(actualResult);
        } else {
            Object actualResult = data.getMethod().apply(target);
            Assertions.assertEquals(data.getExpectedResult(), actualResult);
        }
    }

    @Override
    public String toString() {
        return data.getDescription();
    }
}
