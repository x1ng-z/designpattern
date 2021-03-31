package basejava.anytype;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @author zzx
 * @version 1.0
 * @date 2021/3/11 10:14
 */
public class AnyType<T> {
    private T anytype;


    public AnyType(T anytype) {
        this.anytype = anytype;
        Field[] fields=anytype.getClass().getDeclaredFields();
        for(Field field:fields){

            field.setAccessible(true);
            if(field.isAnnotationPresent(AnyTag.class)){
                System.out.println(field.getAnnotation(AnyTag.class).ienum().name());
                System.out.println(field.getAnnotation(AnyTag.class).value());
                field.getAnnotation(AnyTag.class).haha().value2();
            }
            for(Annotation annotation:field.getAnnotations()){
                System.out.println(annotation.annotationType());
                if(annotation.annotationType().equals(AnyType.class)){

                }
            }
        }
    }

    public AnyType() {
    }

    public T getAnytype() {
        return anytype;
    }
}
