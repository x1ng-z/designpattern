package basejava.classinf;

import java.time.Instant;
import java.util.Random;

/**
 * @author zzx
 * @version 1.0
 * @date 2021/3/11 16:59
 */
public class Load {

    public static void main(String[] args) {
         Class  initClass=Init.class;
        System.out.println(Init.sf2);;
        System.out.println(Init1.sf);;
        Instant instant;
        Integer integer;
        Number number;
    }

}

class Init{
    static final int sf=47;
    static final int sf2=new Random(2).nextInt();
    static {
        System.out.println("Init class");
    }
}


class Init1{
    static  int sf=147;
//    static final int sf2=new Random(2).nextInt();
    static {
        System.out.println("Init1 class");
    }
}


class Init3{
    static  int sf=247;
//    static final int sf2=new Random(2).nextInt();
    static {
        System.out.println("Init class");
    }
}
