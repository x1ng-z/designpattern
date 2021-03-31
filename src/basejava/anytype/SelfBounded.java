package basejava.anytype;

/**
 * @author zzx
 * @version 1.0
 * @date 2021/3/21 20:34
 */


class AA{}
class NN{}
class AAA extends AA{}

class InnerBound<T,V extends T>{
    T t;
    V v;

    public InnerBound(T t, V v) {
        this.t = t;
        this.v = v;
    }

    public static void main(String[] args) {
    }
}

interface A1{}
interface A2{}
interface A3 extends A1,A2{

}

class GenericType<T>{}

class CuriouslyRecurringGeneric extends GenericType<CuriouslyRecurringGeneric>{

}

class BasicHolder<T>{
    T el;
    void setEl(T el){
        this.el=el;
    }
    T get(){
        return el;
    }
    void f(){
        System.out.println(el.getClass().getSimpleName());
    }
}

class Subtype extends BasicHolder<Subtype>{

}

//use self bound

class A extends SelfBounded<A>{

}
class B extends SelfBounded<A/*B*/>{

}

class C extends SelfBounded<C>{
    C setAndget(C c){
        set(c);
       return get();
    }
}
class D{}

class F extends SelfBounded{
    SelfBounded setAndget(F f){
        set(f);

        return get();

    }
}


public class SelfBounded<T extends SelfBounded<T>> {

    T el;
    SelfBounded<T> set(T el){
        this.el=el;
        return this;
    }

    T get(){
        return el;
    }





    public static void main(String[] args) {
        Subtype s1=new Subtype(),s2=new Subtype();

        s1.setEl(s1);
        s1.get();
        s1.f();
    }
}
