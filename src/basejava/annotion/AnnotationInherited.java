package basejava.annotion;

import java.lang.annotation.*;
import java.util.Arrays;

/**
 * @author zzx
 * @version 1.0
 * @date 2021/3/11 12:46
 */

public class AnnotationInherited {
    @Target(value = ElementType.TYPE)
    @Retention(value = RetentionPolicy.RUNTIME)
    @Inherited
            // 声明注解具有继承性
    @interface AInherited {
        String value() default "";
    }

    @Target(value = ElementType.TYPE)
    @Retention(value = RetentionPolicy.RUNTIME)
    @Inherited // 声明注解具有继承性
    @interface BInherited {
        String value() default "";
    }

    @Target(value = ElementType.TYPE)
    @Retention(value = RetentionPolicy.RUNTIME)
            // 未声明注解具有继承性
    @interface CInherited {
        String value() default "";
    }

    @Target(value = ElementType.TYPE)
    @Retention(value = RetentionPolicy.RUNTIME)
    @Inherited // 声明注解具有继承性
    @interface DInherited {
        String value() default "";
    }

    @DInherited("父父类的AInherited")
    interface SsuperInterface{

    }

    @AInherited("父类的AInherited")
    @BInherited("父类的BInherited")
    @CInherited("父类的CInherited")
    class SuperClass implements SsuperInterface {
    }

    @BInherited("子类的BInherited")
    class ChildClass extends SuperClass {
    }

    public static void main(String[] args) {
        Annotation[] annotations = ChildClass.class.getAnnotations();
        System.out.println(Arrays.toString(annotations));
        // output: [@annotations.InheritedTest1$AInherited(value=父类的AInherited), @annotations.InheritedTest1$BInherited(value=子类的BInherited)]
    }
}
