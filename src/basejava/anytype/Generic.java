package basejava.anytype;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzx
 * @version 1.0
 * @date 2021/3/20 9:54
 */

class Friut {
}

class Apple extends Friut {
}

class RedApple extends Apple {
}

class Jonathan extends Apple {
}

class Orange extends Friut {
}

class Holder<T> {
    private T value;

    public Holder(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        return o.equals(value);
    }

    public void add(List<? super T> list, T friut) {
        list.add(friut);
    }
}

public class Generic {

    public static <T> void writewildcard(T object, List<? super T> list) {
        System.out.println(object.getClass());
        list.add(object);
    }

    public static <T> void writeexact(T o, List<T> list) {
        list.add(o);
    }

//    public static <T>void writeexact(List<T> o,List<T> list){
////        list.add(o);
//    }

    public static void main(String[] args) {

        Friut[] friuts = new Apple[10];

        friuts[0] = new Apple();
        friuts[1] = new RedApple();

        List<? extends Friut> friutList = new ArrayList<Apple>();

        List<? super Friut> superfruitlist = new ArrayList<>();

        List<? super Apple> superfruitlist2 = new ArrayList<Friut>();
        superfruitlist.add(new Apple());
//        superfruitlist.add();
        Object friut = superfruitlist.get(0);

        Number[] numbers = new Integer[100];
        numbers[0] = new Integer(1);

//        List<Number> numberList=new ArrayList<Integer>();

        Holder<? extends Friut> generic = new Holder<Apple>(new Apple());
        boolean eqresult = generic.equals(new Apple());
        //generic.setValue(new Apple()); error
        System.out.println(eqresult);
        List<Friut> friuts1 = new ArrayList<Friut>();
        List<? super Friut> gfriuts1 = new ArrayList<>();
        List<Apple> apples = new ArrayList<Apple>();

//        T object,List<? super T> list
        List<? super Apple> test = gfriuts1;//parent=object,so it's parent of Apple class
/***       gfriuts1=Test;error**/
        writewildcard(new Apple(), gfriuts1);

        writewildcard(new Apple(), apples);

/***       writewildcard(new Friut(),apples);error*/

        writeexact(new Apple(), new ArrayList<>());
        writeexact(new Apple(), new ArrayList<Friut>());
/***       writeexact(new Friut(),apples);,error***/
        List<Friut> list = null;
//        list.add(new Friut());
//        list.add(new Apple());

    }

}
