package org.example;

public enum CerereEnum {
    BULETIN {
        @Override
        public String toString() {
            return "inlocuire buletin";
        }
    },
    VENIT {
        @Override
        public String toString() {
            return "inregistrare venit salarial";
        }
    },
    CARNET_SOFER {
        @Override
        public String toString() {
            return "inlocuire carnet de sofer";
        }
    },
    CARNET_ELEV {
        @Override
        public String toString() {
            return "inlocuire carnet de elev";
        }
    },
    ACT_CONSTITUTIV {
        @Override
        public String toString() {
            return "creare act constitutiv";
        }
    },
    AUTORIZATIE {
        @Override
        public String toString() {
            return "reinnoire autorizatie";
        }
    },
    PENSIE {
        @Override
        public String toString() {
            return "inregistrare cupoane de pensie";
        }
    }
}
