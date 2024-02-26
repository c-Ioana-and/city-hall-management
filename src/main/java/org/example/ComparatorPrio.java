package org.example;
import java.util.Comparator;

public class ComparatorPrio implements Comparator<Cerere> {
    @Override
    public int compare(Cerere o1, Cerere o2) {
        return o2.getPrioritate() - o1.getPrioritate();
    }
}

