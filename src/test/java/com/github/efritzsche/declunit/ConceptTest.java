package com.github.efritzsche.declunit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ConceptTest {

    @TestFactory
    public TestContainer testMath() {
        return TestBuilder
                .newTest("1 + 1 returns 2")
                    .target(Math.class)
                    .apply(() -> Math.addExact(1, 1))
                    .expectSuccess(2)
                .newTest("MAX_VALUE + 1 throws ArithmeticException")
                    .target(Math.class)
                    .apply(() -> Math.addExact(Integer.MAX_VALUE, 1))
                    .expectFailure(ArithmeticException.class)
                .build();
    }

    @TestFactory
    public TestContainer testSimpleMath() {
        return TestBuilder
                .newTest("1 + 1 returns 2")
                    .target(new SimpleMath())
                    .apply(math -> math.add(1, 1))
                    .expectSuccess(2)
                .newTest("2 / 2 returns 1")
                    .target(new SimpleMath())
                    .apply(math -> math.div(2, 2))
                    .expectSuccess(1)
                .newTest("1 / 0 throws ArithmeticException")
                    .target(new SimpleMath())
                    .apply(math -> math.div(1, 0))
                    .expectFailure(ArithmeticException.class)
                .build();
    }

    @TestFactory
    public TestContainer testStateMath() {
        return TestBuilder
                .newTest("construction")
                    .target(StateMath.class)
                    .apply(StateMath::new)
                    .expectSuccess(math -> {
                        Assertions.assertEquals(0, math.getA());
                        Assertions.assertEquals(0, math.getB());
                    })
                .newTest("1 + 1 returns 2")
                    .target(new StateMath())
                    .prepareTarget(math -> {
                        math.setA(1);
                        math.setB(1);
                    })
                    .apply(StateMath::add)
                    .expectSuccess(2)
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
