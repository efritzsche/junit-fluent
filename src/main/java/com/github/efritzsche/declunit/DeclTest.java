package com.github.efritzsche.declunit;

import org.junit.jupiter.api.Assertions;

public class DeclTest<T, R> implements Runnable {

    private TestData<T, R> data;


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
            R actualResult = data.getMethod().apply(data.getTarget());
            Assertions.assertEquals(data.getExpectedResult(), actualResult);
        }
    }
}