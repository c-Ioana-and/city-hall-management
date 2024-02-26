package org.example;

public class Elev extends Utilizator {
    private String Scoala;
    public Elev (String nume, String Scoala) {
        this.setNume(nume);
        this.Scoala = Scoala;
    }
    public void setScoala (String Scoala) {
        this.Scoala = Scoala;
    }
    public String getScoala () {
        return Scoala;
    }
}
