package edu.phystech.hw3;

import java.util.HashSet;
import java.util.Set;

public class SetUtils {
        public static <T> Set<T> union(Set<T> first, Set<T> second) {
        Set<T> result = new HashSet<>(first);
        result.addAll(second);
        return result;
    }

    public static <T> Set<T> intersection(Set<T> first, Set<T> second) {
        Set<T> result = new HashSet<>(first);
        result.retainAll(second);
        return result;
    }

    public static <T> Set<T> difference(Set<T> first, Set<T> second) {
        Set<T> result = new HashSet<>(first);
        result.removeAll(second);
        return result;
    }

    public static <T> Set<T> symmetricDifference(Set<T> first, Set<T> second) {
        Set<T> union = union(first, second);
        Set<T> intersection = intersection(first, second);
        
        union.removeAll(intersection);
        return union;
    }

}
