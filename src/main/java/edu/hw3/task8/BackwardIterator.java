package edu.hw3.task8;

import java.util.Iterator;
import java.util.List;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class BackwardIterator implements Iterator {

    private final List list;
    private int pointer;

    public BackwardIterator(List list) {
        this.list = list;
        this.pointer = list.size() - 1;
    }

    @Override
    public boolean hasNext() {
        log.info("check if has next");
        return pointer >= 0;
    }

    @Override
    public Object next() {
        log.info("get {} value", pointer);
        return list.get(pointer--);
    }

}
