package basejava.classinf;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author zzx
 * @version 1.0
 * @date 2021/3/12 11:10
 */

class DynamicProxyHandler implements InvocationHandler{
    private Object proxied;

    public DynamicProxyHandler(Object proxied) {
        this.proxied = proxied;//被代理类
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy:"+proxy.getClass());//代理类
       return method.invoke(proxied,args);
    }
}

interface Job{
    void work();
}


public class DynamicProxy implements Job{
    @Override
    public void work(){
        System.out.println(this+"work");
    }

    public static void main(String[] args) {
        DynamicProxy dynamicProxy=new DynamicProxy();
        DynamicProxyHandler dynamicProxyHandler=new DynamicProxyHandler(dynamicProxy);
        Job proxyInstance=(Job)Proxy.newProxyInstance(dynamicProxyHandler.getClass().getClassLoader(),dynamicProxy.getClass().getInterfaces(),dynamicProxyHandler);

        proxyInstance.work();
    }
}





