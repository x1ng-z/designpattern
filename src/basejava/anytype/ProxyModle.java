package basejava.anytype;

import java.util.Date;

/**
 * @author zzx
 * @version 1.0
 * @date 2021/3/22 9:00
 */
interface TimeStamped{long getStamp();}
class TimeStampedImp implements TimeStamped{
    private  final long timestamp;

    public TimeStampedImp() {
        this.timestamp = System.nanoTime();
    }


    @Override
    public long getStamp() {
        return timestamp;
    }
}
public class ProxyModle {
    private TimeStamped timeStamped=new TimeStampedImp();
    public long getStamp(){
        return timeStamped.getStamp();
    }
}

interface SerialNumber{
    long getSerialnumber();
}

class SerialNumberImp implements SerialNumber{
    @Override
    public long getSerialnumber() {
        return 0;
    }
}



