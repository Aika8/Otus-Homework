package ru.otus.utils;

public class Summary {
    private int failureCount;
    private int successCount;
    private int countAll;
    private String className;

    public Summary(String className) {
        this.className = className;
    }

    public void successIncrease() {
        successCount++;
    }

    public void failureIncrease() {
        failureCount++;
    }

    public void testIncrease() {
        countAll++;
    }

    @Override
    public String toString() {
        return "Summary{" +
                "failureCount=" + failureCount +
                ", successCount=" + successCount +
                ", countAll=" + countAll +
                ", className='" + className + '\'' +
                '}';
    }
}
