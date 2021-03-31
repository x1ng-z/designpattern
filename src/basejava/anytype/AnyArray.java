package basejava.anytype;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author zzx
 * @version 1.0
 * @date 2021/3/19 13:41
 */
public class AnyArray<T> {
    static final int SIZE = 100;
    static AnyType<String>[] gia;
    private T[] array;
    private T[] otherarray;
    private int size;

    public AnyArray(int size) {
        this.size=size;
        this.array = (T[]) new Object[size];
    }

    public AnyArray(Class<T> type,int size){
        otherarray=(T[])Array.newInstance(type,size);
    }

    public void put(int index, T object) {
        array[index] = object;
    }

    public T get(int index) {
        return array[index];
    }

    @SuppressWarnings("unchecked")
    public T[] rep(){
        return array;
    }


    public static void main(String[] args) {
        gia = new AnyType[SIZE];

        gia[0] = new AnyType<String>();
        AnyArray<Integer> anyArray=new AnyArray<Integer>(10);
        //Integer[] aa =anyArray.rep();//error
        Object[] aa1 =anyArray.rep();
    }
}
