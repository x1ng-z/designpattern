package basejava.anytype;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzx
 * @version 1.0
 * @date 2021/3/21 0:24
 * generic and covariant
 */

class Read {
    public static <T> T readexact(List<T> list) {
        return list.get(0);
    }
}

class GenericRead<T> {
    public  T genericread(List<T> list) {
        return list.get(0);
    }
}


class CovRead<T> {
    public T genericread(List<? extends T> list) {
        return list.get(0);
    }
}

class Generic1<T>{
    T o;

    public Generic1(T o) {
        this.o = o;
    }

    void put(T o){
       this.o=o;
   }
}

class Generic2<T>{
    T o;

    public Generic2(T o) {
        this.o = o;
    }

    T get(){
        return o;
    }
}

class GenericMethod{
    public static <T> void call(Generic1<? super T> g1,T a){
         g1.put(a);
    }

    public static <T> T call(Generic2<? extends T> g2){
        return g2.get();
    }
}


public class Covariant {

    public static void main(String[] args) {
        List<Apple> apples=new ArrayList<>();
        apples.add(new Apple());
        List<Friut> friuts=new ArrayList<>();
        friuts.add(new Friut());
        Friut friut=Read.readexact(apples);

        Friut friut1=new GenericRead<Apple>().genericread(apples);

        Friut friut2=new CovRead<Friut>().genericread(apples);

    }

}
