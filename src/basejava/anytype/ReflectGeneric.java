package basejava.anytype;

import sun.plugin.javascript.navig.Array;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author zzx
 * @version 1.0
 * @date 2021/3/23 1:08
 */

class Apply {
    static <T, S extends Iterable<? extends T>> void apply(S seq, Method f, Object... args) {
        for (T s : seq) {
            try {
                f.invoke(s, args);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}

class Shape {
    public void rotate() {
        System.out.println("rotate");
    }

    public void resize() {
        System.out.println("resize");
    }
}

class Square extends Shape {
}

class FilledList<T> extends ArrayList<T> {
    Class tClass = this.getClass();

    public FilledList(Class<? extends T> c, int size) {
        for (int index = 0; index < size; index++) {
            try {
                add(c.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}

interface Addable<T> {
    void add(T t);
}

class Fill2 {
    public static <T> void fill(Addable<T> addable, Class<? extends T> clazzToken, int size) {
        try {
            while (size-- > 0) {
                addable.add(clazzToken.newInstance());
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static <T> void fill(Addable<T> addable, Generator<T> generator, int size) {
        while (size-- > 0) {
            addable.add(generator.next());
        }
    }


}

class AddableCollectAdapter<T> implements Addable<T>{
    public AddableCollectAdapter(Collection<T> collection) {
        this.collection = collection;
    }

    private Collection<T> collection;

    @Override
    public void add(T t) {
        collection.add(t);
    }
}

class Adapter{
    public static <T> Addable<T> collectAdapter(Collection<T> c){
        return new AddableCollectAdapter<T>(c);
    }
}

public class ReflectGeneric {
}
