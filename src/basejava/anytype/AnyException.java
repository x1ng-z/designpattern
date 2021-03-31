package basejava.anytype;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzx
 * @version 1.0
 * @date 2021/3/21 21:32
 */


interface Processor<T, E extends Exception> {
    void process(List<T> result) throws E;
}

class ProcessRunner<T, E extends Exception> extends ArrayList<Processor<T, E>> {
    List<T> processAll() throws E {
        List<T> collects = new ArrayList<>();
        for (Processor<T, E> processor : this) {
            processor.process(collects);
        }
        return collects;
    }
}

class Failure1 extends Exception{}

class StringProcessor implements Processor<String,Failure1>{
    @Override
    public void process(List<String> result) throws Failure1 {
        int count=3;
        if(count-->0){
            result.add("a1");
        }else {
            throw new Failure1();
        }
    }
}



public class AnyException {

    public static void main(String[] args) {
        ProcessRunner<String,Failure1> processors= new ProcessRunner<String,Failure1>();
        try {
            processors.processAll();
        } catch (Failure1 failure1) {
            failure1.printStackTrace();
        }
    }

}
