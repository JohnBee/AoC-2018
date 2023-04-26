package org.aoc.days.day9;

import static java.lang.Math.abs;

public class LinkedListTape<T> {
    private Node<T> start = null;
    private int size = 0;
    private Node<T> nPointer = null;

    public Node<T> getStart() {
        return start;
    }

    public void add(T newValue) {
        Node<T> item = new Node<T>(newValue);
        if (this.nPointer != null) {
            Node<T> prev = nPointer;
            Node<T> next = nPointer.next;
            item.prev = prev;
            item.next = next;
            if (prev != null) {
                prev.next = item;
            } else {
                this.start = item;
            }
            next.prev = item;
        } else {
            start = item;

            item.next = item;
            item.prev = item;
        }
        this.nPointer = item;
        size++;
    }

    public void remove() { // remove the items the nPointer is currently at
        assert nPointer != null;
        Node<T> prevNode = nPointer.prev;
        Node<T> nextNode = nPointer.next;
        if (prevNode != null) {
            prevNode.next = nextNode;
        }
        if (nextNode != null) {
            nextNode.prev = prevNode;
        }

        if(this.getStart() == nPointer){
            this.start = nextNode;
        }
        nPointer = nextNode;
        size--;
        if(size == 0){
            this.start = null;
            this.nPointer.next = null;
            this.nPointer.prev = null;
            this.nPointer = null;
        }
    }

    public Node<T> getAtPointer() {
        return nPointer;
    }

    public void moveTape(int dirMove) { // can be negative to move back
        int vector = abs(dirMove);
        if (nPointer == null) { // nothing to do
            return;
        }
        if (dirMove < 0) {
            for (int i = 0; i < vector; i++) {
                if (nPointer.prev != null) {
                    nPointer = nPointer.prev;
                }

            }
        } else {
            for (int i = 0; i < vector; i++) {
                if (nPointer.next != null) {
                    nPointer = nPointer.next;
                }
            }
        }

    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        Node<T> ptr = this.start;
        while (ptr != null) {
            out.append(ptr.value).append(" ");
            ptr = ptr.next;
            if (ptr == start) {
                break;
            }
        }
        return out.toString();
    }

    public static class Node<T> {
        private T value;
        private Node<T> next = null;
        private Node<T> prev = null;

        public Node(T value, Node<T> next, Node<T> prev) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }

        public Node(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public Node<T> getPrev() {
            return prev;
        }

        public void setPrev(Node<T> prev) {
            this.prev = prev;
        }

    }
}
