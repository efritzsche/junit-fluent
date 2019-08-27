package com.github.efritzsche.junit.fluent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicTest;

/**
 * Container holding {@link DynamicTest dynamic tests} for JUnit 5.
 *
 * @see org.junit.jupiter.api.TestFactory TestFactory
 */
public class TestContainer implements Iterable<DynamicTest> {

    private final List<DynamicTest> dynamicTests;


    TestContainer(List<TestData> tests) {
        dynamicTests = new ArrayList<>(tests.size());

        for (TestData test : tests) {
            dynamicTests.add(DynamicTest.dynamicTest(
                    test.getDescription(),
                    () -> executeTest(test)));
        }
    }


    @Override
    public Iterator<DynamicTest> iterator() {
        return dynamicTests.iterator();
    }

    private void executeTest(TestData data) {
        Object target = data.getArrangeTarget() != null
                ? data.getArrangeTarget().apply(data.getTarget())
                : data.getTarget();

        if (data.getExpectedException() != null) {
            Assertions.assertThrows(
                    data.getExpectedException(),
                    () -> data.getMethod().apply(target)
            );
        } else if (data.getAssertResult() != null) {
            Object actualResult = Assertions.assertDoesNotThrow(
                    () -> data.getMethod().apply(target)
            );
            data.getAssertResult().accept(actualResult);
        } else {
            Object actualResult = Assertions.assertDoesNotThrow(
                    () -> data.getMethod().apply(target)
            );
            Assertions.assertEquals(data.getExpectedResult(), actualResult);
        }
    }
}
