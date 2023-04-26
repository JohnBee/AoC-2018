package org.aoc.days.day9;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTapeTest {


    @org.junit.jupiter.api.Test
    void remove() {
        LinkedListTape<Integer> test = new LinkedListTape<>();
        assertEquals(test.getSize(), 0);
        test.add(0);
        assertEquals(test.getSize(), 1);
        assertEquals(test.getAtPointer().getValue(), 0);
        test.remove();
        assertEquals(test.getSize(), 0);
        assertNull(test.getAtPointer());
    }

    @org.junit.jupiter.api.Test
    void moveTape() {
        LinkedListTape<Integer> test = new LinkedListTape<>();
        test.add(0);
        assertEquals(test.getAtPointer().getValue(), 0);
        test.add(1);
        test.add(2);
        test.add(3);
        test.moveTape(1);
        assertEquals(test.getAtPointer().getValue(), 0);
        test.moveTape(1);
        assertEquals(test.getAtPointer().getValue(), 1);
        test.moveTape(1);
        assertEquals(test.getAtPointer().getValue(), 2);
        test.moveTape(-2);
        assertEquals(test.getAtPointer().getValue(), 0);
        test.moveTape(-1);
        assertEquals(test.getAtPointer().getValue(), 3);
        test.moveTape(-1);
        assertEquals(test.getAtPointer().getValue(), 2);
    }

    @Test
    void getStart() {
        LinkedListTape<Integer> test = new LinkedListTape<>();
        assertNull(test.getStart());
        test.add(0);
        assertEquals(test.getStart().getValue(), 0);
        test.add(1);
        assertEquals(test.getStart().getValue(), 0);
        test.moveTape(1);
        test.remove();
        test.remove();
        assertNull(test.getStart());
    }

    @Test
    void add() {
        LinkedListTape<Integer> test = new LinkedListTape<>();
        test.add(0);
        assertEquals(test.getAtPointer().getValue(), 0);
        test.add(1);
        assertEquals(test.getAtPointer().getValue(), 1);
        test.moveTape(-1);
        assertEquals(test.getAtPointer().getValue(), 0);
        test.add(2);
        assertEquals(test.getAtPointer().getValue(), 2);
        test.moveTape(-1);
        assertEquals(test.getAtPointer().getValue(), 0);

    }


    @Test
    void getAtPointer() {
        LinkedListTape<Integer> test = new LinkedListTape<>();
        assertNull(test.getAtPointer());
        test.add(0);
        assertEquals(test.getAtPointer().getValue(), 0);
        test.add(1);
        assertEquals(test.getAtPointer().getValue(), 1);
        test.moveTape(1);
        assertEquals(test.getAtPointer().getValue(), 0);
        test.remove();
        test.remove();
        assertNull(test.getAtPointer());
    }


    @Test
    void getSize() {
        LinkedListTape<Integer> test = new LinkedListTape<>();
        test.add(0);
        assertEquals(test.getSize(), 1);
        test.add(1);
        assertEquals(test.getSize(), 2);
        test.remove();
        assertEquals(test.getSize(), 1);
        test.remove();
        assertEquals(test.getSize(), 0);

    }
}