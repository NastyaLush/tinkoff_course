package edu.hw3.task5;

import java.util.Comparator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SortContacts {

    public String[] parseContacts(String[] contacts, SortOrder order) {
        log.info("begin parse contacts");

        if (contacts == null || contacts.length == 0) {
            return new String[]{};
        }
        quickSort(0, contacts.length - 1, contacts, new NameComparatorDependsOnOrder(order));

        log.info("finish parse contacts");
        return contacts;
    }

    private void quickSort(int left, int right, String[] contacts, Comparator<String> comparator) {
        String pivot = contacts[(left + right) / 2];
        if (left < right) {
            int i = left;
            int j = right;
            while (i <= j) {
                while (comparator.compare(contacts[i], pivot) < 0) {
                    i++;
                }
                while (comparator.compare(contacts[j], pivot) > 0) {
                    j--;
                }
                if (i < j) {
                    swap(i, j, contacts);
                    i++;
                    j--;
                } else {
                    break;
                }
            }
            quickSort(left, j, contacts, comparator);
            quickSort(j + 1, right, contacts, comparator);
        }
    }

    private void swap(int first, int second, String[] contacts) {
        String temporary = contacts[first];
        contacts[first] = contacts[second];
        contacts[second] = temporary;
    }

    class NameComparatorDependsOnOrder implements Comparator<String> {

        private final SortOrder order;

        NameComparatorDependsOnOrder(SortOrder order) {
            this.order = order;
        }

        @Override
        public int compare(String firstObjectFullName, String secondObjectFullName) {
            String first = getName(firstObjectFullName);
            String second = getName(secondObjectFullName);
            if (order == SortOrder.ASC) {
                return first.compareTo(second);
            } else {
                return second.compareTo(first);
            }
        }

        private String getName(String str) {
            String[] splitedString = str.split(" ");
            if (splitedString.length == 2) {
                return splitedString[1];
            }
            if (splitedString.length != 1) {
                throw new IllegalArgumentException();
            }
            return str;
        }
    }

}
