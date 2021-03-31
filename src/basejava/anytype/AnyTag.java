package basejava.anytype;

import java.lang.annotation.*;

/**
 * @author zzx
 * @version 1.0
 * @date 2021/3/11 10:23
 */
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface AnyTag  {
    public int value() default 11;
    public AnyEnum ienum() default AnyEnum.One;
    public AnyTag2 haha() default @AnyTag2();
}
