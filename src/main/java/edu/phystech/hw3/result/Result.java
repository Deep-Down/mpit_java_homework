package edu.phystech.hw3.result;

import java.util.function.Function;

public sealed interface Result<T> permits Success, Failure {
    boolean isSuccess();
    boolean isFailure();
    T getOrDefault(T defaultValue);
    Throwable getExceptionOrNull();
    <R> Result<R> map(Function<? super T, ? extends R> transform);
}