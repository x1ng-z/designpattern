package basejava.anytype;

/**
 * @author zzx
 * @version 1.0
 * @date 2021/3/21 21:03
 */

class Base{}
class Derived extends Base{}
interface OrdinaryGetter{
//    @Override
    Base get();
}

interface DerivedGetter extends OrdinaryGetter{
    @Override
    Derived get();
}


interface GenericGetter<T extends GenericGetter<T>>{
    T get();
}

interface Getter extends GenericGetter<Getter>{

}


class GenericSetter<T>{
    void set(T t){
        System.out.println("GenericSetter.set()");
    }
}

class DerivedGS extends GenericSetter<Base>{
    void set(Derived derived){
        System.out.println("DerivedGS.set()");
    }
}

public class ParamCovarant {

    void test(DerivedGetter derivedGetter){
        Base derived=derivedGetter.get();
    }

    void test(Getter getter){
        GenericGetter d= getter.get().get().get().get().get().get().get().get().get();

    }

    public static void main(String[] args) {
//        new ParamCovarant().test();

        DerivedGS dgs=new DerivedGS();
        dgs.set(new Derived());
        dgs.set(new Base());
//        java.util.Collections.c

    }
}
