package com.github.efritzsche.junit.fluent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.DynamicTest;

/**
 * Container holding {@link DynamicTest dynamic tests} for JUnit 5.
 *
 * @see org.junit.jupiter.api.TestFactory TestFactory
 */
public class TestContainer implements Iterable<DynamicNode> {

    private final List<DynamicNode> dynamicTests;


    TestContainer(List<TestData> tests) {
        dynamicTests = new ArrayList<>(tests.size());

        addNodes(dynamicTests, tests);
    }

    private void addNodes(List<DynamicNode> nodes, List<TestData> tests) {
        for (TestData test : tests) {
            if (test.isParent()) {
                List<DynamicNode> childNodes = new ArrayList<>();
                addNodes(childNodes, test.getChildTests());
                nodes.add(DynamicContainer.dynamicContainer(test.getDescription(), childNodes));
            } else {
                nodes.add(DynamicTest.dynamicTest(
                        test.getDescription(),
                        () -> executeTest(test)));
            }
        }
    }


    @Override
    public Iterator<DynamicNode> iterator() {
        return dynamicTests.iterator();
    }

    private void executeTest(TestData data) {
        Object target = data.getTargetTransform() != null
                ? data.getTargetTransform().apply(data.getTargetSupplier().get())
                : data.getTargetSupplier().get();

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
