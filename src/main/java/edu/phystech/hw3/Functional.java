package edu.phystech.hw3;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;

public class Functional {

    public static <T, R> List<R> map(List<? extends T> list, Function<? super T, ? extends R> mapper) {
        List<R> result = new ArrayList<>();
        for (T item : list) {
            result.add(mapper.apply(item));
        }
        return result;
    }

    public static <T, R> R reduce(List<? extends T> list, BiFunction<R, ? super T, R> accumulator, R initialValue) {
        R result = initialValue;
        for (T item : list) {
            result = accumulator.apply(result, item);
        }
        return result;
    }
}

