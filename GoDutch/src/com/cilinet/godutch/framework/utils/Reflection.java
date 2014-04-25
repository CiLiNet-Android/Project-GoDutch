package com.cilinet.godutch.framework.utils;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Reflection {
	
	public static Object getProperty(Object owner, String fieldName) throws Exception {
		Class ownerClass = owner.getClass();

		Field field = ownerClass.getField(fieldName);

		Object property = field.get(owner);

		return property;
	}

	public static Object getStaticProperty(String className, String fieldName)
			throws Exception {
		Class ownerClass = Class.forName(className);

		Field field = ownerClass.getField(fieldName);

		Object property = field.get(ownerClass);

		return property;
	}

	
	public static Object invokeMethod(Object owner, String methodName, Object[] args)
			throws Exception {

		Class ownerClass = owner.getClass();

		Class[] argsClass = new Class[args.length];

		for (int i = 0, j = args.length; i < j; i++) {
			argsClass[i] = args[i].getClass();
		}

		Method method = ownerClass.getMethod(methodName, argsClass);

		return method.invoke(owner, args);
	}

	public static Object invokeStaticMethod(String className, String methodName,
			Object[] args) throws Exception {
		Class ownerClass = Class.forName(className);

		Class[] argsClass = new Class[args.length];

		for (int i = 0, j = args.length; i < j; i++) {
			argsClass[i] = args[i].getClass();
		}

		Method method = ownerClass.getMethod(methodName, argsClass);

		return method.invoke(null, args);
	}

	
	public static Object newInstance(String className, Object[] args, Class[] argsType)
			throws NoSuchMethodException, SecurityException,
			ClassNotFoundException, InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		Class newoneClass = Class.forName(className);

		if (args == null) {
			return newoneClass.newInstance();

		} else {
			// Class[] argsClass = new Class[args.length];
			//
			// for (int i = 0, j = args.length; i < j; i++) {
			// argsClass[i] = args[i].getClass();
			// }
			//
			// Constructor cons = newoneClass.getConstructor(argsClass);
			Constructor cons = newoneClass.getConstructor(argsType);

			return cons.newInstance(args);
		}

	}

	
	public static boolean isInstance(Object obj, Class cls) {
		return cls.isInstance(obj);
	}


	public static Object getByArray(Object array, int index) {
		return Array.get(array, index);
	}

	public static Class<?> GetClassListByPackage(String pPackage) {
		Package _Package = Package.getPackage(pPackage);
		Class<?> _List = _Package.getClass();

		return _List;
	}
}
