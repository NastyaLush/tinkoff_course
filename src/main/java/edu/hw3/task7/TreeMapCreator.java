package edu.hw3.task7;

import java.util.TreeMap;

public class TreeMapCreator {

    public <T extends Comparable<T>, E> TreeMap<T, E> getExtendedTreeMap() {
        TreeMap<T, E> treeMap = new TreeMap<>((o1, o2) -> {
            if (o1 == null && o2 == null) {
                return 0;
            }
            if (o1 == null) {
                return -1;
            }
            if (o2 == null) {
                return 1;
            }
            return o1.compareTo(o2);
        });
        return treeMap;
    }

}
