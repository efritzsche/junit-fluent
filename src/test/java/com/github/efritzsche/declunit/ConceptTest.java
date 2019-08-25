package com.github.efritzsche.declunit;

import java.util.List;

import com.github.efritzsche.declunit.builder.TestBuilder;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ConceptTest {

    @TestFactory
    public List<DynamicTest> testSimpleMath() {
        return new TestBuilder()
                .newTest("1 + 1 returns 2")
                    .target(new SimpleMath())
                    .apply(math -> math.add(1, 1))
                    .expect(2)
                .newTest("1 / 0 throws ArithmeticException")
                    .target(new SimpleMath())
                    .apply(math -> math.div(1, 0))
                    .expectException(ArithmeticException.class)
                .build();
    }


    public static class SimpleMath {

        public int add(int a, int b) {
            return a + b;
        }

        public int div(int a, int b) {
            return a / b;
        }
    }
}
