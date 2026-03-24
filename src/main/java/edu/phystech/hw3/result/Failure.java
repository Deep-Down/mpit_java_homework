package edu.phystech.hw3.result;

import java.util.function.Function;

public final class Failure<T> implements Result<T> {
    @SuppressWarnings("unused")
    private final Throwable e;
    public Failure(Throwable e) {
        this.e = e;
    }

    @Override
    public boolean isFailure() {
        return false;
    }

    @Override
    public boolean isSuccess() {
        return true;
    }

    @Override
    public T getOrDefault(T defaultValue) {
        return defaultValue;
    }

    @Override
    public Throwable getExceptionOrNull() {
        return e;
    }

    @Override
    public <R> Result<R> map(Function<T, R> transform) {
        return (Result<R>) this;
    }
}
