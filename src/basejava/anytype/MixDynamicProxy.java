package basejava.anytype;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zzx
 * @version 1.0
 * @date 2021/3/22 14:59
 */

class TwoTuple<T,V>{
    final T first;
    final V second;
    public TwoTuple(T first, V second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return "("+first+", "+second+")";
    }
}

class MixinProxy implements InvocationHandler{
    Map<String,Object> delegatesByMethod;
    public MixinProxy(TwoTuple<Object,Class<?>>...pairs){
        delegatesByMethod=new HashMap<String,Object>();
        for(TwoTuple<Object,Class<?>> pair:pairs){
            for(Method method:pair.second.getMethods()){
                String methodName=method.getName();
                if(!delegatesByMethod.containsKey(methodName)){
                    delegatesByMethod.put(methodName,pair.first);
                }
            }

        }

    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName=method.getName();
        Object o=delegatesByMethod.get(methodName);
        return method.invoke(o,args);
    }

    public static Object newInstance(TwoTuple...pairs) {
        Class[] interfaces=new Class[pairs.length];
        for(int index=0;index<pairs.length;index++){
            interfaces[index]=(Class)pairs[index].second;
        }
        ClassLoader loader=pairs[0].first.getClass().getClassLoader();
        return Proxy.newProxyInstance(loader,interfaces,new MixinProxy(pairs));
    }
}


public class MixDynamicProxy {

    public static void main(String[] args) {
        Object o=MixinProxy.newInstance(new TwoTuple(new SerialNumberImp(), SerialNumber.class),
                new TwoTuple(new TimeStampedImp(),TimeStamped.class));

        TimeStamped basic=(TimeStamped) o;
        basic.getStamp();

        TimeStamped timeStampedImp=(TimeStamped) o;

        timeStampedImp.getStamp();

    }
}
