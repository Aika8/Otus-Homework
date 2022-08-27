package ru.otus;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

public class AnnotationsTest {

    @Before
    public void setUp() {
        System.out.print("\n@Before. ");
        System.out.println("Экземпляр тестового класса: " + Integer.toHexString(hashCode()));
    }

    @Test
    void anyTest1() {
        System.out.print("@Test: anyTest1. ");
        System.out.println("Экземпляр тестового класса: " + Integer.toHexString(hashCode()));
    }

    @Test
    void anyTest2() {
        System.out.print("@Test: anyTest2. ");
        System.out.println("Экземпляр тестового класса: " + Integer.toHexString(hashCode()));
        throw new AssertionError("bad test, but do not stop");
    }

    @Test
    void anyTest3() {
        System.out.print("@Test: anyTest3. ");
        System.out.println("Экземпляр тестового класса: " + Integer.toHexString(hashCode()));
    }

    @After
    public void tearDown() {
        System.out.print("@After. ");
        System.out.println("Экземпляр тестового класса: " + Integer.toHexString(hashCode()));
    }

}
