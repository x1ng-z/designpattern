package basejava.anytype;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzx
 * @version 1.0
 * @date 2021/3/21 1:06
 */


public class UnboundWildcards {
    static List list1;
    static List<?> list2;
    static List<? extends Object> list3;

    static void assigned1(List list) {
        list1 = list;
        list2 = list;
        list3 = list;
    }

    static void assigned2(List<?> list) {
        list1 = list;
        list2 = list;
        list3 = list;
    }

    static void assigned3(List<? extends Object> list) {
        list1 = list;
        list2 = list;
        list3 = list;
    }

    static void rawArgs(Holder holder, Object arg) {
        holder.setValue(arg);
//        holder.setValue(new WildCards());
        holder.getValue();
    }

    static void unboundedArg(Holder<?> holder, Object arg) {
        //holder.setValue(arg);//
//         Apple apple=new Object();
//        List<Friut> aa=new ArrayList<Apple>();
        Object o = holder.getValue();

    }

    static <T> T exact1(Holder<T> holder) {
        T t = holder.getValue();
//        System.out.println(t.getClass().getSimpleName());
        return t;
    }

    static <T> T exact2(Holder<T> holder, T arg) {
        holder.setValue(arg);
        T t = holder.getValue();
        return t;
    }

    static <T> T wildsubtype(Holder<? extends T> holder, T arg) {

//        holder.setValue(arg);

        T t=holder.getValue();
        return t;
    }

    static <T> Object wildsuptype(Holder<? super T> holder, T arg) {

        holder.setValue(arg);

        Object t=holder.getValue();
        return t;
    }

    static <T> void f1(Holder<T> holder){
        System.out.println(holder.getValue().getClass().getSimpleName());;
//        f2(holder);
    }

    static <T> void f2(Holder<?> holder){
        f1(holder);
    }


    static <T> void ex1_1(Holder<List<?>> holder){
        List<?> list=holder.getValue();
        holder.setValue(new ArrayList<>());

//        list.add(1L);
        Object o=list.get(0);
    }

    static <T> void ex1_2(List<Holder<?>> list){
//        List<Holder> a=list;
        for(Holder<?> j:list){

        }
        list.add(new Holder<Apple>(new Apple()));

        Holder<?> holder=list.get(0);
    }



    public static void main(String[] args) {
        assigned1(new ArrayList());
        assigned2(new ArrayList());
        assigned3(new ArrayList());

        Holder raw=new Holder(1L);
        Holder<Long> qualfied=new Holder<Long>(1L);
        Holder<? extends Long> bound=new Holder<Long>(new Long(1L));
        Holder<?> unbound=new Holder<Long>(1L);
        rawArgs(bound,1L);
        rawArgs(raw,1L);
        rawArgs(unbound,1L);

        unboundedArg(raw,1L);
        unboundedArg(bound,1L);
        unboundedArg(unbound,1L);

        Object o =exact1(raw);
        Long aLong=exact1(qualfied);
        Object exact1=exact1(unbound);
        Long aLong1=exact1(bound);


        Long lag=1L;
        Long aLong2=exact2(raw,lag);
        Long aLong3=exact2(qualfied,lag);
//        exact2(unbound,lag);// error  need set exact type
//        exact2(bound,lag);//error

        wildsubtype(raw,lag);

        wildsubtype(qualfied,lag);

        Object a=wildsubtype(unbound,lag);
        Long aLong4=wildsubtype(bound,lag);


        wildsuptype(raw,lag);

        wildsuptype(qualfied,lag);

        wildsubtype(unbound,lag);

        wildsubtype(bound,lag);


        Holder rawholder=new Holder<Integer>(1);

        f1(rawholder);
        f2(rawholder);

        Holder<?> unboundholder=new Holder<Integer>(1);

        f1(unboundholder);
        f2(unboundholder);

        Holder protypeholder=new Holder(1);

        f1(protypeholder);
        f2(protypeholder);



    }
}
