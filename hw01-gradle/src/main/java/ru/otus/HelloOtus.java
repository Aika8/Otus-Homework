package ru.otus;

import com.google.common.base.CharMatcher;


/**
 *
 * To start the application:
 * ./gradlew build
 * java -jar ./hw01-gradle/build/libs/gradleHW1-0.1.jar
 *
 * To unzip the jar:
 * unzip -l hw01-gradle.jar
 * unzip -l gradleHW1-0.1.jar
 *
 */
public class HelloOtus {
    public static void main(String... args) {
        String[] stringsToCheck = new String[] {"HelloWorld", "Hello World", " "};
        for (String s : stringsToCheck) {
            System.out.println(CharMatcher.breakingWhitespace().matchesAnyOf(s));
        }
    }
}
