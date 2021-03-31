package basejava.anytype;

import java.util.Arrays;

/**
 * @author zzx
 * @version 1.0
 * @date 2021/3/11 10:47
 */
public class Test {
    public static void main(String[] args) {

        System.out.println(Arrays.toString(AnyType.class.getTypeParameters()));
        System.out.println(AnyType.class.getComponentType());
        System.out.println(AnyType.class.getTypeName());
        AnyEnum anyEnum=AnyEnum.valueOf("One");

        AnyType<Any> anyType=new AnyMethod().getAnyType(new Any());
        System.out.println(Any.class.isAssignableFrom(Object.class));

    }
}
