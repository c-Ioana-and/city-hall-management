package org.example;

import java.io.*;

public class Functionar <T extends Utilizator> {
    private String nume;
    private Birou<T> birou;
    private Class <T> tipClasa;

    public Functionar(String nume, Birou<T> birou) {
        this.nume = nume;
        this.birou = birou;
        birou.adaugaFunctionar(this);
        tipClasa = birou.getTipClasa();
    }

    public String getNume() {
        return nume;
    }

    public Birou getBirou() {
        return birou;
    }

    public void rezolvaCerere() throws IOException {
        String file_name = "functionar_" + nume + ".txt";
        // rezolva cererea
        Cerere cerere = birou.getListaCereri().getFirst();

        if (cerere.getUtilizator().getClass() == tipClasa) {
            cerere.getUtilizator().rezolvaCerere(cerere);

            FileWriter fw = new FileWriter("src/main/resources/output/" + file_name, true);
            fw.write(cerere.toStringFunctionar() + "\n");
            fw.close();

            // sterg cererea din lista din cadrul biroului
            birou.stergeCerere(cerere);
        }
        else System.out.println("Nu se poate rezolva cererea!");
    }
}
