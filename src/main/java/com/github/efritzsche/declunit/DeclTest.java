package com.github.efritzsche.declunit;

import org.junit.jupiter.api.Assertions;

public class DeclTest implements Runnable {

    private TestData data;


    public DeclTest(TestData data) {
        this.data = data;
    }


    @Override
    public void run() {
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
