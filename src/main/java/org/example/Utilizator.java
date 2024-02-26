package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

class exceptie extends RuntimeException {
    public exceptie(String message) {
        super(message);
    }
}

public abstract class Utilizator {
    private String nume;
    LinkedList<Cerere> Asteapta = new LinkedList<>();
    LinkedList<Cerere> Finalizate = new LinkedList<>();

    public void setNume (String nume) {
        this.nume = nume;
    }
    public String getNume () {
        return nume;
    }

    public String textCerere (CerereEnum tip_cerere, FileWriter out) {
        if (this instanceof Persoana) {
            try {
                if (tip_cerere != CerereEnum.BULETIN && tip_cerere != CerereEnum.CARNET_SOFER)
                    throw new exceptie("Utilizatorul de tip persoana nu poate inainta o cerere de tip " + tip_cerere + "\n");

                else return "Subsemnatul " + this.getNume() + ", va rog sa-mi aprobati urmatoarea solicitare: "
                        + tip_cerere;
            } catch (exceptie e) {
                try {
                    out.write(e.getMessage());
                    return null;
                } catch (IOException e1) {}
            }
        }
        if (this instanceof Angajat) {
            try {
                if (tip_cerere != CerereEnum.BULETIN && tip_cerere != CerereEnum.CARNET_SOFER && tip_cerere != CerereEnum.VENIT) {
                    throw new exceptie("Utilizatorul de tip angajat nu poate inainta o cerere de tip " + tip_cerere + "\n");
                }
                return "Subsemnatul " + this.getNume() + ", angajat la compania " + ((Angajat) this).getCompanie()
                        + ", va rog sa-mi aprobati urmatoarea solicitare: " + tip_cerere;
            } catch (exceptie e) {
                try {
                    out.write(e.getMessage());
                    return null;
                } catch (IOException e1) {}
            }
        }
        if (this instanceof Elev) {
            try {
                if (tip_cerere != CerereEnum.BULETIN && tip_cerere != CerereEnum.CARNET_ELEV)
                    throw new exceptie("Utilizatorul de tip elev nu poate inainta o cerere de tip " + tip_cerere + "\n");

                else return "Subsemnatul " + this.getNume() + ", elev la scoala " + ((Elev) this).getScoala()
                        + ", va rog sa-mi aprobati urmatoarea solicitare: " + tip_cerere;
            } catch (exceptie e) {
                try {
                    out.write(e.getMessage());
                    return null;
                } catch (IOException e1) {}
            }
        }
        if (this instanceof Pensionar) {
            try {
                if (tip_cerere != CerereEnum.BULETIN && tip_cerere != CerereEnum.CARNET_SOFER && tip_cerere != CerereEnum.PENSIE) {
                    throw new exceptie("Utilizatorul de tip pensionar nu poate inainta o cerere de tip " + tip_cerere + "\n");
                } else return "Subsemnatul " + this.getNume() + ", va rog sa-mi aprobati urmatoarea solicitare: "
                        + tip_cerere;
            } catch (exceptie e) {
                try {
                    out.write(e.getMessage());
                    return null;
                } catch (IOException e1) {}
            }
        }
        if (this instanceof EntitateJuridica) {
            try {
                if (tip_cerere != CerereEnum.ACT_CONSTITUTIV && tip_cerere != CerereEnum.AUTORIZATIE) {
                    throw new exceptie("Utilizatorul de tip entitate juridica nu poate inainta o cerere de tip " + tip_cerere + "\n");
                } else return "Subsemnatul " + ((EntitateJuridica) this).getNumeReprezentant() +
                        ", reprezentant legal al companiei " + this.getNume() +
                        ", va rog sa-mi aprobati urmatoarea solicitare: " + tip_cerere;
            } catch (exceptie e) {
                try {
                    out.write(e.getMessage());
                    return null;
                } catch (IOException e1) {}
            }
        }
        return null;
    }

    public void adaugaCerereAsteptare(Cerere cerere) {
        Lista.adaugaElem(cerere, Asteapta);
    }


    public void retragereCerere (String data) {
        try {
            Date conv_data = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse(data);
            Lista.stergeElem(conv_data, Asteapta);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void afisCereriAsteptare (FileWriter file) throws IOException {
        file.write(nume + " - cereri in asteptare:\n");

        for (Cerere cerere : Asteapta) {
            file.write(cerere + "\n");
        }
    }

    public void afisCereriFinalizate (FileWriter file) throws IOException {
        file.write(nume + " - cereri in finalizate:\n");

        for (Cerere cerere : Finalizate) {
            file.write(cerere + "\n");
        }
    }

    public static Utilizator cautaUtilizator (String nume) {
        for (Utilizator utilizator : ManagementPrimarie.utilizatori) {
            if (utilizator.getNume().equals(nume)) {
                return utilizator;
            }
        }
        return null;
    }

    public void rezolvaCerere (Cerere cerere) {
        Lista.stergeElem(cerere.getData(), Asteapta);
        Lista.adaugaElem(cerere, Finalizate);
    }
}
