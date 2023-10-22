package edu.hw3.task8;

import java.util.Iterator;
import java.util.List;

public class BackwardIterator implements Iterator {

    private final List list;
    private int pointer;

    public BackwardIterator(List list) {
        this.list = list;
        this.pointer = list.size() - 1;
    }

    @Override
    public boolean hasNext() {
        return pointer >= 0;
    }

    @Override
    public Object next() {
        return list.get(pointer--);
    }

}
