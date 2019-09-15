package com.github.efritzsche.junit.fluent;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Showcase of API concept")
public class ConceptTest {

    @TestFactory
    @DisplayName("Tests for static methods of standard Math class")
    public TestContainer testMath() {
        return TestBuilder
                .newTest("1 + 1 returns 2")
                    .target(Math.class)
                    .apply(Math::addExact, 1, 1)
                    .expectSuccess(2)
                .newTest("MAX_VALUE + 1 throws ArithmeticException")
                    .target(Math.class)
                    .apply(Math::addExact, Integer.MAX_VALUE, 1)
                    .expectFailure(ArithmeticException.class)
                .build();
    }

    @TestFactory
    @DisplayName("Tests for member methods of SimpleMath class")
    public TestContainer testSimpleMath() {
        return TestBuilder
                .newTest("1 + 1 returns 2")
                    .target(SimpleMath::new)
                    .apply(SimpleMath::add, 1, 1)
                    .expectSuccess(2)
                .newTest("2 / 2 returns 1")
                    .target(SimpleMath::new)
                    .apply(SimpleMath::div, 2, 2)
                    .expectSuccess(1)
                .newTest("1 / 0 throws ArithmeticException")
                    .target(SimpleMath::new)
                    .apply(SimpleMath::div, 1, 0)
                    .expectFailure(ArithmeticException.class)
                .build();
    }

    @TestFactory
    @DisplayName("Tests for configuration of StateMath class")
    public TestContainer testStateMath() {
        return TestBuilder
                .newTest("Construction of new instance")
                    .target(StateMath.class)
                    .apply(StateMath::new)
                    .expectSuccess(math -> {
                        Assertions.assertEquals(0, math.getA());
                        Assertions.assertEquals(0, math.getB());
                    })
                .newTest("Test addition")
                    .target(StateMath::new)
                    .prepareTarget(math -> {
                        math.setA(1);
                        math.setB(1);
                    })
                    .childTest("1 + 1 returns 2")
                        .apply(StateMath::add)
                        .expectSuccess(2)
                    .childTest("(-2) + 1 returns -1")
                        .prepareTarget(math -> math.setA(-2))
                        .apply(StateMath::add)
                        .expectSuccess(-1)
                    .childTest("1 + 3 returns 4")
                        .prepareTarget(math -> math.setB(3))
                        .apply(StateMath::add)
                        .expectSuccess(4)
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

    public static class StateMath {

        private int a, b;


        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }

        public int getB() {
            return b;
        }

        public void setB(int b) {
            this.b = b;
        }

        public int add() {
            return a + b;
        }
    }
}
