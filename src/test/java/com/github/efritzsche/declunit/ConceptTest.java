package com.github.efritzsche.declunit;

import java.util.List;

import com.github.efritzsche.declunit.builder.TestBuilder;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ConceptTest {

    public List<DeclTest<?, ?>> getTests() {
        return new TestBuilder()
                .<SimpleMath>add("1 + 1 returns 2")
                    .target(new SimpleMath())
                    .apply(math -> math.add(1, 1))
                    .expect(2)
                .<SimpleMath>add("1 / 0 throws ArithmeticException")
                    .target(new SimpleMath())
                    .apply(math -> math.div(1, 0))
                    .expectException(ArithmeticException.class)
                .build();
    }

    @MethodSource("getTests")
    @ParameterizedTest
    public void test(DeclTest test) {
        test.run();
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
