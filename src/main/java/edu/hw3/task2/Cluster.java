package edu.hw3.task2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Cluster {

    public ArrayList<String> clusterize(String inputString) {
        log.info("begin clusterizing {}", inputString);
        Deque<Character> stack = new ArrayDeque<>();
        ArrayList<Character> cluster = new ArrayList<>();
        ArrayList<String> clusters = new ArrayList<>();
        for (char ch : inputString.toCharArray()) {
            if (ch == '(') {
                stack.addFirst(ch);
                cluster.add(ch);
            } else {
                if (stack.isEmpty() || stack.getFirst() != '(') {
                    throw new IllegalArgumentException();
                } else {
                    stack.removeFirst();
                    cluster.add(ch);
                    if (stack.isEmpty()) {
                        clusters.add(cluster.stream()
                                            .map(Object::toString)
                                            .collect(Collectors.joining("")));
                        cluster.clear();
                    }
                }
            }
        }
        if (!cluster.isEmpty()) {
            log.error("cluster is empty, input is incorrect");
            throw new IllegalArgumentException();
        }
        log.info("finish clusterizing");
        return clusters;
    }

}
