package org.example;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ManagementPrimarie {
    static List<Utilizator> utilizatori;
    static Birou<Angajat> birouAngajati;
    static Birou<Elev> birouElevi;
    static Birou<Persoana> birouPersoane;
    static Birou<Pensionar> birouPensionari;
    static Birou<EntitateJuridica> birouEntitatiJuridice;

    public ManagementPrimarie () {
        utilizatori = new ArrayList<>();
        birouAngajati = new Birou<>("angajat", Angajat.class);
        birouElevi = new Birou<>("elev", Elev.class);
        birouPersoane = new Birou<>("persoana", Persoana.class);
        birouPensionari = new Birou<>("pensionar", Pensionar.class);
        birouEntitatiJuridice = new Birou<>("entitate juridica", EntitateJuridica.class);
    }

    public static void main(String[] args) throws IOException, ParseException {
        new ManagementPrimarie();

        // sterg fisierele temporare de la rularea trecuta
        File[] files = new File("src/main/resources/output").listFiles();
        for (File file : files) {
            if (file.isFile() && file.getName().contains("functionar_")) {
                file.delete();
            }
        }

        File inputFile = new File("src/main/resources/input/" + args[0]);
        File outputFile = new File("src/main/resources/output/" + args[0]);
        FileWriter fileWriter = new FileWriter(outputFile);
        Parser parser = new Parser();

        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        String line;
        while ((line = br.readLine()) != null) {
            String[] words = line.split(";");
            if (words[0].equals("adauga_utilizator"))
                parser.adaugaUtilizator(words);
            if (words[0].equals("adauga_functionar"))
                parser.adaugaFunctionar(words);
            if (words[0].equals("cerere_noua"))
                parser.adaugaCerere(words, fileWriter);
            if (words[0].equals("afiseaza_cereri_in_asteptare"))
                parser.afiseazaCereriAsteptare(words, fileWriter);
            if (words[0].equals("afiseaza_cereri_finalizate"))
                parser.afiseazaCereriFinalizate(words, fileWriter);
            if (words[0].equals("retrage_cerere"))
                parser.retragereCerere(words);
            if (words[0].equals("rezolva_cerere"))
                parser.rezolvaCerere(words);
            if (words[0].equals("afiseaza_cereri"))
                parser.afiseazaCereriBirou(words, fileWriter);
        }
        fileWriter.close();
    }
}