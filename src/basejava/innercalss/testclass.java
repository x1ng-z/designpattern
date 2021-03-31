package basejava.innercalss;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zzx
 * @version 1.0
 * @date 2021/3/6 9:18
 */


public class testclass {

    private outerclass test(){
        return new outerclass(){
            @Override
            public inerclass newinerclass() {
                return super.newinerclass();
            }
        };
    }
    public static void main(String[] args) {

        Map<Integer,String> cons=new HashMap<>();
        cons.put(new Integer(11),"22");
        cons.put(new Integer(33),"bb");

        System.out.println(cons.get(new Integer(11)));
        Collection<String> ccc=cons.values();
        for(String c:ccc){
            if(c.equals(22)){
                ccc.remove(c);
                break;
            }
        }
        System.out.println(cons.toString());
         AtomicInteger reconnectcount = new AtomicInteger(0);

        reconnectcount.set(1);

        reconnectcount.decrementAndGet();
//        "*/19 * * * * ?";
        System.out.println(reconnectcount.get());;
        byte i = (byte) 0xffffffff;
        Integer aaaaaaa=((int)i)&0xffffffff;
        BigInteger bigInteger=BigInteger.valueOf(i);
        int kk=bigInteger.intValue();
        Byte b=aaaaaaa.byteValue();
        Long i1 = Byte.toUnsignedLong(i);
        int i2 = Byte.toUnsignedInt(i);
        int test1=((int)i);
        int aa1=test1&0xff;




        Map<String,String> testmap=new ConcurrentHashMap<>();
        testmap.put("1","2");
        System.out.println(testmap.toString());
        testmap.values().removeAll(Arrays.asList("1","2"));
        System.out.println(testmap.toString());
        outerclass.inerclass1 aa=new outerclass().new inerclass1();


    }
}
