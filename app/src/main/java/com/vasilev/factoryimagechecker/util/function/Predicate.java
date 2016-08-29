package com.vasilev.factoryimagechecker.util.function;

@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);
}
