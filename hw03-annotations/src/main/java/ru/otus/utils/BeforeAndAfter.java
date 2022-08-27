package ru.otus.utils;

import java.lang.reflect.Method;

public class BeforeAndAfter {
    private Method before;
    private Method after;

    public Method getBefore() {
        return before;
    }

    public void setBefore(Method before) {
        this.before = before;
    }

    public Method getAfter() {
        return after;
    }

    public void setAfter(Method after) {
        this.after = after;
    }
}
