package edu.hw3.task7;

import java.util.TreeMap;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TreeMapCreator {

    public <T extends Comparable<T>, E> TreeMap<T, E> getExtendedTreeMap() {
        log.info("create extended treeMap");
        TreeMap<T, E> treeMap = new TreeMap<>((firstObject, secondObject) -> {
            if (firstObject == null && secondObject == null) {
                return 0;
            }
            if (firstObject == null) {
                return -1;
            }
            if (secondObject == null) {
                return 1;
            }
            return firstObject.compareTo(secondObject);
        });
        log.info("finish creating extended treeMap");
        return treeMap;
    }

}
