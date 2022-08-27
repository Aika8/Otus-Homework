package ru.otus;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;
import ru.otus.utils.BeforeAndAfter;
import ru.otus.utils.ReflectionHelper;
import ru.otus.utils.Summary;

import java.lang.reflect.Method;

public class TestRunner {
    public static void main(String[] args) throws ClassNotFoundException {
        if (args.length > 0) {
            for (String className : args) {
                Class<?> clazz = Class.forName(className);
                BeforeAndAfter beforeAndAfter = getMethodsAnnotatedWithBeforeAndAfter(clazz);
                Summary summary = invokeTriple(clazz, beforeAndAfter);
                System.out.println(summary);
            }
        } else {
            System.out.println("no input parameters");
        }
    }

    private static BeforeAndAfter getMethodsAnnotatedWithBeforeAndAfter(final Class<?> clazz) {
        BeforeAndAfter beforeAndAfter = new BeforeAndAfter();

        for (final Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Before.class)) {
                beforeAndAfter.setBefore(method);
            }
            if (method.isAnnotationPresent(After.class)) {
                beforeAndAfter.setAfter(method);
            }
        }
        return beforeAndAfter;
    }

    private static Summary invokeTriple(final Class<?> clazz, BeforeAndAfter beforeAndAfter) {
        Summary summary = new Summary(clazz.getName());
        for (final Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Test.class)) {
                Object instance = ReflectionHelper.instantiate(clazz);
                try {
                    if (beforeAndAfter.getBefore() != null)
                        ReflectionHelper.callMethod(instance, beforeAndAfter.getBefore().getName());
                    summary.testIncrease();
                    checkTestAndInvoke(summary, method, instance);
                } finally {
                    if (beforeAndAfter.getAfter() != null)
                        ReflectionHelper.callMethod(instance, beforeAndAfter.getAfter().getName());
                }
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
            } else {
                throw e;
            }
        }
    }
}