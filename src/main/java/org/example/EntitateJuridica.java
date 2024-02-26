package org.example;

public class EntitateJuridica extends Utilizator {
    String nume_reprezentant;
    public EntitateJuridica (String nume, String nume_reprezentant) {
        this.setNume(nume);
        this.nume_reprezentant = nume_reprezentant;
    }

    public void setNumeReprezentant (String nume_reprezentant) {
        this.nume_reprezentant = nume_reprezentant;
    }
    public String getNumeReprezentant () {
        return nume_reprezentant;
    }
}
