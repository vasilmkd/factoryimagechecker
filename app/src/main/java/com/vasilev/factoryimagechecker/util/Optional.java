package com.vasilev.factoryimagechecker.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.NoSuchElementException;

public final class Optional<T> {

    private final T value;

    private Optional(@Nullable T value) {
        this.value = value;
    }

    @NonNull
    public static <T> Optional<T> empty() {
        return new Optional<>(null);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null || obj.getClass() != this.getClass())
            return false;
        else {
            if (obj instanceof Optional<?>) {
                final Optional<?> optional = (Optional<?>) obj;
                if (optional.isPresent())
                    return optional.get().equals(this.value);
                else return !isPresent();
            }
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.value != null ? this.value.hashCode() : 0;
    }

    @NonNull
    public T get() {
        if (this.value != null)
            return this.value;
        throw new NoSuchElementException();
    }

    public boolean isPresent() {
        return this.value != null;
    }

    @NonNull
    public static <T> Optional<T> of(@NonNull T value) {
        return new Optional<>(value);
    }

    @NonNull
    public static <T> Optional<T> ofNullable(@Nullable T value) {
        return value != null ? new Optional<>(value) : empty();
    }

    @NonNull
    public T orElse(@NonNull T other) {
        return isPresent() ? get() : other;
    }

    @NonNull
    @Override
    public String toString() {
        return this.value != null ?
                "Optional(" + value.getClass().getSimpleName() + ": " + value.toString() + ")" :
                "Empty";
    }
}
