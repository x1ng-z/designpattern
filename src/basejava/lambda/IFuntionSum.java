package basejava.lambda;

/**
 * @author zzx
 * @version 1.0
 * @date 2021/4/1 10:17
 */

import java.util.List;

@FunctionalInterface
public interface IFuntionSum<T extends Number> {
    T sum(List<T> numbers);      // 抽象方法
//    T min(List<T> numbers);
}

@FunctionalInterface
interface IFunctionMulti<T extends Number> {
    void multi(List<T> numbers); // 抽象方法


    //    boolean equals(Object obj);  // Object中的方法
    default boolean multi2(Object obj) {
//        List<?> list=null;
//        list.forEach();

        return false;

    }
}

//@FunctionalInterface
//public interface IFunctionMulti2<T extends Number> extends IFuntionSum<T> {
//    void multi(List<T> numbers);
//
//    @Override
//    boolean equals(Object obj);
//}
// IFunctionMulti 接口继承了 IFuntionSum 接口，此时 IFunctionMulti 包含了2个抽象方法


