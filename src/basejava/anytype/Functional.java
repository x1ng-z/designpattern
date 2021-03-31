package basejava.anytype;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;

/**
 * @author zzx
 * @version 1.0
 * @date 2021/3/23 8:18
 */


interface Combine<T> {
    T combine(T x, T y);
}

interface UnaryFunction<R, T> {
    R function(T x);
}

interface Collector<T> extends UnaryFunction<T, T> {
    T result();
}

interface UnaryPredicate<T> {
    boolean test(T t);
}


public class Functional {

    //accumute continue operate
    public static <T> T reduce(Iterable<T> seq, Combine<T> combine) {
        Iterator<T> iterator = seq.iterator();
        if (iterator.hasNext()) {
            T result = iterator.next();
            while (iterator.hasNext()) {
                result = combine.combine(result, iterator.next());
            }
            return result;
        }
        return null;
    }
    //
    public static <T> Collector<T> forEach(Iterable<T> iterable, Collector<T> collector) {
        for (T t : iterable) {
            collector.function(t);
        }
        return collector;
    }

    public static <R, T> List<R> transform(Iterable<T> seq, UnaryFunction<R, T> func) {
        List<R> result = new ArrayList<R>();
        for (T t : seq) {
            result.add(func.function(t));
        }
        return result;
    }

    public static <T> List<T> filter(Iterable<T> seq, UnaryPredicate<T> pred) {
        List<T> results = new ArrayList<>();
        for (T t : seq) {
            if (pred.test(t)) {
                results.add(t);
            }
        }
        return results;
    }

    static class IntegerAdd implements Combine<Integer>{
        @Override
        public Integer combine(Integer x, Integer y) {
            return x+y;
        }
    }

    static class IntegerSubtracker implements Combine<Integer>{
        @Override
        public Integer combine(Integer x, Integer y) {
            return x-y;
        }
    }

    static class AtomixLongAdd implements Combine<AtomicLong>{
        @Override
        public AtomicLong combine(AtomicLong x, AtomicLong y) {
            return new AtomicLong(x.addAndGet(y.get()));
        }
    }

    static class BigDecimaUlp implements UnaryFunction<BigDecimal,BigDecimal>{
        @Override
        public BigDecimal function(BigDecimal x) {
            return x.ulp();
        }
    }

    static class GreaterThan<T extends Comparable<T>> implements UnaryPredicate<T>{
        private final T t;

        public GreaterThan(T t) {
            this.t = t;
        }

        @Override
        public boolean test(T t) {
            return this.t.compareTo(t)>0;
        }
    }

    static class MultiplyingInteger implements Collector<Integer>{
        private  Integer val=1;

        @Override
        public Integer function(Integer x) {
            return (val*=x);
        }

        @Override
        public Integer result() {
            return val;
        }
    }

    public static void main(String[] args) {
        List<Integer> d=Arrays.asList(1,2,3);
    }


}
