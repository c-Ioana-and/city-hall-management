package org.example;

import java.util.Date;
import java.util.LinkedList;
import java.util.ListIterator;

/*
 * Clasa care contine operatiile necesare pentru adaugarea
 * si stergerea elementelor dintr-o lista de cereri
 * ordonata dupa prioritatea si timpul creeari lor (adaugaElem2, stergeElem2)
 * sau doar dupa timpo creeari lor (adaugaElem, stergeElem)
 */

public class Lista {
    public static void adaugaElem2(Cerere cerere, LinkedList<Cerere> lista) {
        lista.add(cerere);
        lista.sort(new ComparatorPrio().thenComparing(new ComparatorTimp()));
    }

    public static void stergeElem2(Date data, LinkedList<Cerere> lista) {
        ListIterator<Cerere> iterator = lista.listIterator();
        while (iterator.hasNext()) {
            Cerere cerere = iterator.next();
            if (cerere.getData().equals(data)) {
                iterator.remove();
                System.out.println("Cererea a fost stearsa cu succes");
                return;
            }
        }
        lista.sort(new ComparatorPrio().thenComparing(new ComparatorTimp()));
    }

    public static void adaugaElem(Cerere cerere, LinkedList<Cerere> lista) {
        lista.add(cerere);
        lista.sort(new ComparatorTimp());
    }

    public static void stergeElem(Date data, LinkedList<Cerere> lista) {
        ListIterator<Cerere> iterator = lista.listIterator();
        while (iterator.hasNext()) {
            Cerere cerere = iterator.next();
            if (cerere.getData().equals(data)) {
                iterator.remove();
                System.out.println("Cererea a fost stearsa cu succes");
                return;
            }
        }
        lista.sort(new ComparatorTimp());
    }
}
