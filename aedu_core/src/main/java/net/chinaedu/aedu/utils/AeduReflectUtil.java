package net.chinaedu.aedu.utils;

import java.lang.reflect.Method;

public class AeduReflectUtil {

	/**
	 * 调用方法
	 */
	public static Object invokeMethod(Object obj , String methodName, Object[] args) throws Exception {
		if(args == null){
			return obj.getClass().getMethod(methodName).invoke(obj);
		}
		Class<?>[] argTypes = new Class<?>[args.length];
		for (int i = 0; i < argTypes.length; i++){
            argTypes[i] = args[i].getClass();
        }
		Class<?> objType = obj.getClass();
		Method m = objType.getMethod(methodName, argTypes);
		return m.invoke(obj, args);
	}
}