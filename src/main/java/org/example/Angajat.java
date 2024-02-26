package org.example;

public class Angajat extends Utilizator {
    private String Companie;
    public Angajat (String nume, String Companie) {
        this.setNume(nume);
        this.Companie = Companie;
    }
    public void setCompanie (String Companie) {
        this.Companie = Companie;
    }
    public String getCompanie () {
        return Companie;
    }

}
