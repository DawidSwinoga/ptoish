package pl.dmcs.proxy;

import lombok.RequiredArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Dawid on 07.12.2018 at 12:19.
 */

@RequiredArgsConstructor
public class DynamicProxy implements InvocationHandler {
    private final Object target;
    private final String methodName;

    @SuppressWarnings("unchecked")
    public static <T extends Testable, E> E createProxy(T target, String methodName, Class<E> impl) {
        DynamicProxy myProxy = new DynamicProxy(target, methodName);
        ClassLoader loader = target.getClass().getClassLoader();
        return (E)Proxy.newProxyInstance(loader, new Class[]{impl}, myProxy);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        try {
            System.out.println("Proxy object:" + proxy.getClass().getCanonicalName() + " method: " + method.getName());
            Method targetMethod = target.getClass()
                    .getMethod(methodName);
            return targetMethod.invoke(target);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}

