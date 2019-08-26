package com.github.efritzsche.declunit;

import java.util.function.Consumer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SelfTest {

    @TestFactory
    public TestContainer testBuilder() {
        return TestBuilder
        .newTest("full self test")
            .target(TestBuilder.class)
            .apply(() -> TestBuilder
                    .newTest("test")
                        .target(new Object())
                        .apply(Object::hashCode)
                        .expectSuccess(hash -> {})
                    .build())
            .expectSuccess(container -> {
                for (DynamicTest test : container) {
                    Assertions.assertEquals("test", test.getDisplayName());
                }
            })

        .newTest("null check description")
            .target(TestBuilder.class)
            .apply(() -> TestBuilder.newTest(null))
            .expectFailure(NullPointerException.class)

        .newTest("null check description")
            .target(TestBuilder.class)
            .apply(() -> TestBuilder.newTest(""))
            .expectFailure(IllegalArgumentException.class)

        .newTest("null check target instance")
            .target(TestBuilder.class)
            .apply(() -> TestBuilder.newTest("test").target((Object) null))
            .expectFailure(NullPointerException.class)

        .newTest("null check target class")
            .target(TestBuilder.class)
            .apply(() -> TestBuilder.newTest("test").target((Class<?>) null))
            .expectFailure(NullPointerException.class)

        .newTest("null check target preparation function")
            .target(TestBuilder.class)
            .apply(() -> TestBuilder.newTest("test").target(new Object()).prepareTarget(null))
            .expectFailure(NullPointerException.class)

        .newTest("null check target replacement function")
            .target(TestBuilder.class)
            .apply(() -> TestBuilder.newTest("test").target(new Object()).replaceTarget(null))
            .expectFailure(NullPointerException.class)

        .newTest("null check method")
            .target(TestBuilder.class)
            .apply(() -> TestBuilder.newTest("test").target(new Object()).apply(null))
            .expectFailure(NullPointerException.class)

        .newTest("null check void method")
            .target(TestBuilder.class)
            .apply(() -> TestBuilder.newTest("test").target(new Object()).applyVoid(null))
            .expectFailure(NullPointerException.class)

        .newTest("null check static method")
            .target(TestBuilder.class)
            .apply(() -> TestBuilder.newTest("test").target(Object.class).apply(null))
            .expectFailure(NullPointerException.class)

        .newTest("null check static void method")
            .target(TestBuilder.class)
            .apply(() -> TestBuilder.newTest("test").target(Object.class).applyVoid(null))
            .expectFailure(NullPointerException.class)

        .newTest("null check expected exception")
            .target(TestBuilder.class)
            .apply(() -> TestBuilder.newTest("test").target(Object.class).apply(() -> null).expectFailure(null))
            .expectFailure(NullPointerException.class)

        .newTest("null check assert result")
            .target(TestBuilder.class)
            .apply(() -> TestBuilder.newTest("test").target(Object.class).apply(() -> null).expectSuccess((Consumer<Object>) null))
            .expectFailure(NullPointerException.class)

        .newTest("allow null result")
            .target(TestBuilder.class)
            .apply(() -> TestBuilder.newTest("test").target(Object.class).apply(() -> null).expectSuccess((Object) null))
            .expectSuccess(creator -> {/* any result is fine */})

        .build();
    }
}
