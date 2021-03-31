package basejava.anytype;

import java.lang.annotation.*;

/**
 * @author zzx
 * @version 1.0
 * @date 2021/3/11 11:28
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Inherited
public @interface AnyTag2 {
    public int value2() default 2;
}
