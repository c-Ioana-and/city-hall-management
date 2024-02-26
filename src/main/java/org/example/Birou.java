package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

public class Birou <T extends Utilizator> {
    private LinkedList<Cerere> cereri;
    private LinkedList<Functionar> functionari;
    private String nume;
    private Class <T> tipClasa;

    public Birou(String nume, Class <T> tip) {
        this.nume = nume;
        this.cereri = new LinkedList<>();
        this.functionari = new LinkedList<>();
        tipClasa = tip;
    }

    public Class <T> getTipClasa() {
        return tipClasa;
    }

    public void adaugaCerere(Cerere cerere) {
        if (cerere.getUtilizator().getClass() == tipClasa)
            Lista.adaugaElem2(cerere, cereri);
    }

    public void adaugaFunctionar(Functionar functionar) {
        functionari.add(functionar);
    }

    public Functionar cautaFunctionar(String nume_functionar) {
        // cauta functionarul in coada
        for (Functionar f : functionari) {
            if (f.getNume().equals(nume_functionar)) {
                return f;
            }
        }
        return null;
    }

    public void stergeCerere(Cerere cerere) {
        if (cerere.getUtilizator().getClass() == tipClasa)
            Lista.stergeElem2(cerere.getData(), cereri);
    }

    public LinkedList<Cerere> getListaCereri() {
        return cereri;
    }

    public Queue<Functionar> getFunctionari() {
        return functionari;
    }

    public String getNume() {
        return nume;
    }

    public void afiseazaCereri(FileWriter file) throws IOException {
        file.write(nume + " - cereri in birou:\n");

        for (Cerere cerere : cereri) {
            file.write(cerere.getPrioritate() + " - " + cerere + "\n");
        }
    }
    public void retragereCerere (String data) {
        try {
            Date conv_data = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse(data);
            Lista.stergeElem2(conv_data, cereri);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
