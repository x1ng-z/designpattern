package basejava.anytype;

/**
 * @author zzx
 * @version 1.0
 * @date 2021/3/22 9:07
 */

class Basic{
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

class TimeStamped1 extends DecorateModle{

    private final long timestamp;
    public TimeStamped1(Basic basic) {
        super(basic);
        timestamp=System.nanoTime();
    }
    public long getTimestamp(){
        return timestamp;
    }

}

class SerialNumbered extends DecorateModle{
    private static long counter=-1;
    private final long serialnumber =counter++;

    public SerialNumbered(Basic basic) {
        super(basic);
    }

    public long getSerialnumber(){
        return serialnumber;
    }
}


public class DecorateModle extends Basic {
    private Basic basic;

    public DecorateModle(Basic basic) {
        this.basic = basic;
    }

    @Override
    public String getValue() {
        return basic.getValue();
    }

    @Override
    public void setValue(String value) {
        basic.setValue(value);
    }


    public static void main(String[] args) {
        TimeStamped1 timeStamped1=new TimeStamped1(new Basic());
        timeStamped1.getTimestamp();
        SerialNumbered serialNumbered=new SerialNumbered(new TimeStamped1(new Basic()));

        serialNumbered.getSerialnumber();
        serialNumbered.getValue();

    }
}




