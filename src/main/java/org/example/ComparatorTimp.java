package org.example;

import java.util.Comparator;

public class ComparatorTimp implements Comparator<Cerere> {
    @Override
    public int compare(Cerere o1, Cerere o2) {
        return o1.getData().compareTo(o2.getData());
    }
}
