package com.wangjun.java8;

import java.util.function.BiFunction;
import java.util.function.Function;

public class FunctionalInterface {

    public static void main(String[] args) {

        FunctionalInterface fi = new FunctionalInterface();
        int compute = fi.compute(2, value -> value * 3, value -> value / value);
        System.out.println(compute);

        int compute2 = fi.compute2(4, value -> value * 2, value -> value + value);
        System.out.println(compute2);

        int compute3 = fi.compute3(2, 3, (v1, v2) -> v1 + v2);
        int compute4 = fi.compute3(2, 3, (v1, v2) -> v1 - v2);
        int compute5 = fi.compute3(2, 3, (v1, v2) -> v1 * v2);
        System.out.println(compute3 + " " + compute4 + " " + compute5);

    }

    public int compute(int a, Function<Integer, Integer> function1, Function<Integer, Integer> function2) {
        return function1.compose(function2).apply(a);
    }

    public int compute2(int a, Function<Integer, Integer> function1, Function<Integer, Integer> function2) {
        return function1.andThen(function2).apply(a);
    }

    public int compute3(int a, int b, BiFunction<Integer, Integer, Integer> biFunction) {
        return biFunction.apply(a, b);
    }


}
