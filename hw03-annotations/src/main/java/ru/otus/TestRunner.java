package ru.otus;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;
import ru.otus.utils.ReflectionHelper;
import ru.otus.utils.Summary;

import java.lang.reflect.Method;

public class TestRunner {
    public static void main(String[] args) throws ClassNotFoundException {
        if (args.length > 0) {
            for (String className : args) {
                Class<?> clazz = Class.forName("ru.otus." + className);
                Method[] beforeAndAfter = getMethodsAnnotatedWithBeforeAndAfter(clazz);
                Summary summary = invokeTriple(clazz, beforeAndAfter);
                System.out.println(summary);
            }
        } else {
            System.out.println("no input parameters");
        }
    }

    private static Method[] getMethodsAnnotatedWithBeforeAndAfter(final Class<?> clazz) {
        final Method[] methods = new Method[2];

        for (final Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Before.class)) {
                methods[0] = method;
            }
            if (method.isAnnotationPresent(After.class)) {
                methods[1] = method;
            }
        }
        return methods;
    }

    private static Summary invokeTriple(final Class<?> clazz, Method[] beforeAndAfter) {
        Summary summary = new Summary(clazz.getName());
        for (final Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Test.class)) {
                summary.testIncrease();
                Object instance = ReflectionHelper.instantiate(clazz);
                if (beforeAndAfter[0] != null) ReflectionHelper.callMethod(instance, beforeAndAfter[0].getName());
                checkTestAndInvoke(summary, method, instance);
                if (beforeAndAfter[1] != null) ReflectionHelper.callMethod(instance, beforeAndAfter[1].getName());
            }
        }
        return summary;
    }

    private static void checkTestAndInvoke(Summary summary, Method method, Object instance) {
        try {
            ReflectionHelper.callMethod(instance, method.getName());
            summary.successIncrease();
        } catch (RuntimeException e) {
            if (e.getCause().getCause() != null && e.getCause().getCause().getClass().equals(AssertionError.class)) {
                summary.failureIncrease();
            }
        }
    }
}