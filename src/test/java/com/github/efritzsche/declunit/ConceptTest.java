package com.github.efritzsche.declunit;

import java.util.List;

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
    public List<DynamicTest> testStateMath() {
        return new TestBuilder()
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

        public void setA(int a) {
            this.a = a;
        }

        public void setB(int b) {
            this.b = b;
        }

        public int add() {
            return a + b;
        }
    }
}
