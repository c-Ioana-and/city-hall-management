package org.example;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Cerere {
    private String text;
    private Date data;
    private int prioritate;
    private Utilizator utilizator;

    public Cerere (String text, Date data, int prioritate, Utilizator utilizator) {
        this.text = text;
        this.data = data;
        this.prioritate = prioritate;
        this.utilizator = utilizator;
    }

    public Date getData() {
        return data;
    }

    public int getPrioritate() {
        return prioritate;
    }

    public String getText() {
        return text;
    }

    public Utilizator getUtilizator() {
        return utilizator;
    }

    @Override
    public String toString() {
        SimpleDateFormat new_format = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        String data_formatata = new_format.format(data);
        return data_formatata + " - " + text;
    }

    public String toStringFunctionar() {
        // returneaza cererea in formatul cerut pentru fisierul unui functionar
        SimpleDateFormat new_format = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        String data_formatata = new_format.format(data);
        return data_formatata + " - " + getUtilizator().getNume();
    }
}
