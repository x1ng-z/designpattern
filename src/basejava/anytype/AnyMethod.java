package basejava.anytype;

import java.util.List;

/**
 * @author zzx
 * @version 1.0
 * @date 2021/3/11 11:01
 */
public class AnyMethod {

    public  <T extends Any> AnyType<T> getAnyType(T anyobj){
        AnyType<T>  anyType=new AnyType<T>(anyobj);
        return anyType;
    }

}
