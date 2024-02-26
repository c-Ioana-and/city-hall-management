package org.example;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Parser extends ManagementPrimarie {

    public void adaugaUtilizator (String[] words) {

        String functie = words[1].substring(1);
        String nume = words[2].substring(1);
        String numeInstitutie = "";
        if (!"persoana".equals(functie) && !"pensionar".equals(functie))
            numeInstitutie = words[3].substring(1);

        Utilizator user;
        switch (functie) {
            case "angajat" :
                user = new Angajat(nume, numeInstitutie);
                break;
            case "elev" :
                user = new Elev(nume, numeInstitutie);
                break;
            case "pensionar" :
                user = new Pensionar(nume);
                break;
            case "entitate juridica" :
                user = new EntitateJuridica(nume, numeInstitutie);
                break;
            default : user = new Persoana(nume);
        }

        // adaugare user in lista utilizatori
        if (utilizatori == null)
            utilizatori = new ArrayList<>();
        utilizatori.add(user);
    }

    public void adaugaFunctionar (String[] words) {
        String nume_birou = words[1].substring(1);
        String nume = words[2].substring(1);

        switch (nume_birou) {
            case "elev" :
                new Functionar(nume, birouElevi);
                break;
            case "angajat" :
                new Functionar(nume, birouAngajati);
                break;
            case "pensionar" :
                new Functionar(nume, birouPensionari);
                break;
            case "persoana" :
                new Functionar(nume, birouPersoane);
                break;
            case "entitate juridica" : new Functionar(nume, birouEntitatiJuridice);
        }
    }

    public void adaugaCerere (String[] words, FileWriter out) throws ParseException {
        Utilizator user = Utilizator.cautaUtilizator(words[1].substring(1));
        if (user == null) {
            // imposibil
            return;
        }

        CerereEnum nrCerere;

        switch(words[2].substring(1)) {
            case "inlocuire buletin" :
                nrCerere = CerereEnum.BULETIN;
                break;
            case "inlocuire carnet de sofer" :
                nrCerere = CerereEnum.CARNET_SOFER;
                break;
            case "inlocuire carnet de elev" :
                nrCerere = CerereEnum.CARNET_ELEV;
                break;
            case "creare act constitutiv" :
                nrCerere = CerereEnum.ACT_CONSTITUTIV;
                break;
            case "reinnoire autorizatie" :
                nrCerere = CerereEnum.AUTORIZATIE;
                break;
            case "inregistrare venit salarial" :
                nrCerere = CerereEnum.VENIT;
                break;
            default : nrCerere = CerereEnum.PENSIE;
        }

        String text = user.textCerere(nrCerere, out);
        if (text == null) {
            // se anuleaza cererea
            return;
        }

        int prioritate = Integer.parseInt(words[4].split(" ")[1]);
        SimpleDateFormat data_formatata = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        Date data = data_formatata.parse(words[3]);
        Cerere cerere = new Cerere(text, data, prioritate, user);

        switch (user.getClass().getSimpleName()) {
            case "Angajat" :
                birouAngajati.adaugaCerere(cerere);
                break;
            case "Elev" : birouElevi.adaugaCerere(cerere);
                break;
            case "Pensionar" :
                birouPensionari.adaugaCerere(cerere);
                break;
            case "EntitateJuridica" :
                birouEntitatiJuridice.adaugaCerere(cerere);
                break;
            default : birouPersoane.adaugaCerere(cerere);
        }
        user.adaugaCerereAsteptare(cerere);
    }

    public void retragereCerere (String[] words) {
        Utilizator user = Utilizator.cautaUtilizator(words[1].substring(1));
        if (user == null) {
            // imposibil
            return;
        }

        user.retragereCerere(words[2]);
        if (user instanceof Angajat)
            birouAngajati.retragereCerere(words[2]);
        else if (user instanceof Elev)
            birouElevi.retragereCerere(words[2]);
        else if (user instanceof Pensionar)
            birouPensionari.retragereCerere(words[2]);
        else if (user instanceof EntitateJuridica)
            birouEntitatiJuridice.retragereCerere(words[2]);
        else
            birouPersoane.retragereCerere(words[2]);
    }

    public void afiseazaCereriAsteptare (String[] words, FileWriter file) throws IOException {
        Utilizator user = Utilizator.cautaUtilizator(words[1].substring(1));
        if (user == null) {
            System.out.println("Utilizatorul nu exista");
            return;
        }
        user.afisCereriAsteptare(file);
    }

    public void afiseazaCereriFinalizate (String[] words, FileWriter file) throws IOException {
        Utilizator user = Utilizator.cautaUtilizator(words[1].substring(1));
        if (user == null) {
            System.out.println("Utilizatorul nu exista");
            return;
        }
        user.afisCereriFinalizate(file);
    }

    public void rezolvaCerere (String[] words) throws IOException {
        String birou = words[1].substring(1);
        Birou birou_curent;
        switch (birou) {
            case "elev" :
                birou_curent = birouElevi;
                break;
            case "angajat" :
                birou_curent = birouAngajati;
                break;
            case "pensionar" :
                birou_curent = birouPensionari;
                break;
            case "persoana" :
                birou_curent = birouPersoane;
                break;
            default : birou_curent = birouEntitatiJuridice;
        }

        Functionar functionar = birou_curent.cautaFunctionar(words[2].substring(1));
        if (functionar == null) {
            System.out.println("Functionarul nu exista");
            return;
        }
        functionar.rezolvaCerere();
    }

    public void afiseazaCereriBirou (String[] words, FileWriter file) throws IOException {
        String birou = words[1].substring(1);
        Birou birou_curent;
        switch (birou) {
            case "elev" :
                birou_curent = birouElevi;
                break;
            case "angajat" :
                birou_curent = birouAngajati;
                break;
            case "pensionar" :
                birou_curent = birouPensionari;
                break;
            case "persoana" :
                birou_curent = birouPersoane;
                break;
            default : birou_curent =  birouEntitatiJuridice;
        }

        birou_curent.afiseazaCereri(file);
    }
}
