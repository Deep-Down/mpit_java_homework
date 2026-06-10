package edu.phystech.hw3.result;

import java.util.function.Supplier;

public class ResultUtil {
    public static <T> Result<T> execute(Supplier<T> action) { // Переименовали run в execute
        try {
            return new Success<>(action.get());
        } catch (Throwable e) {
            return new Failure<>(e);
        }
    }
}