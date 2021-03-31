package basejava.anytype;

import java.util.List;
import java.util.Random;

/**
 * @author zzx
 * @version 1.0
 * @date 2021/3/21 19:52
 */

interface Generator<T> {
    int length = 0;

    T next();
}

class RandomGnerator {
    private static Random r = new Random(47);

    public static class String implements Generator<java.lang.String> {
        @Override
        public java.lang.String next() {
            return "abcd";
        }
    }

    public static class Integer implements Generator<java.lang.Integer> {
        @Override
        public java.lang.Integer next() {
            return "abcd".length();
        }
    }


}



interface Payable<T>{}
class Employee implements Payable<Employee>{}
//class Hourly extends Employee implements Payable<Hourly>{
//
//}

class UseList<V,W>{
    public <T> void use(List<V> list){}
//    public <W> void use(List<W> list){}

}
public class ProtypeType {
    static <T> T[] fill(T[] array, Generator<T> generator) {

        for (int index = 0; index < array.length; index++) {
            array[index] = generator.next();
        }
        return array;
    }


    public static void main(String[] args) {
        ProtypeType.fill(new java.lang.String[10],new RandomGnerator.String());

//        ProtypeType.fill(new int[10],new RandomGnerator.Integer());
    }
}
