package basejava.anytype;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zzx
 * @version 1.0
 * @date 2021/3/15 16:12
 */
public class New {
    public static <K, V> Map<K, V> map() {

        return new HashMap<K,V>();
    }

    public static void main(String[] args) {
        New.<String,Integer>map();

    }
}