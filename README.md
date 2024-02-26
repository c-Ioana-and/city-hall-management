# Tema 2

## Implementare

În cadrul acestei teme, pe lânga clasa principală ManagementPrimărie, am creat mai multe clase:
- _Parser_, care se ocupă cu preluarea comenzilor citite din fișierele de input. Toate metodele din această clasă corespund unei comenzi. Am ales să o construiesc ca o subclasă a ManagementPrimărie pentru a avea acces la colecțiile din cadrul primăriei;
- _Cerere_, care reține textul aferent unei cereri, data la care a fost depusă, prioritatea ei și utilizatorul care a depus cererea. Această clasă dispune de doua metode pentru afișare (output-ul diferă în funcție de contextul din care este cerută afișarea cererilor);
- _CerereEnum_, care conține enumerările necesare pentru diferitele tipuri de cereri;
- _Lista_, care conține metodele necesare pentru adaugarea și ștergerea/retragerea unei cereri din listele din clasele Utilizator și Birou. Am ales să creez aceste metode pentru ca sortarea cererilor în liste diferă de context (utilizator - după dată /birou - după dată și prioritate);
- _ComparatorPrio_ și _ComparatorTimp_, subclase de tip Comparator, care conțin metodele necesare pentru sortarea listelor;
- _Utilizator_, care reține numele utilizatorului și două colecții de tipul LinkedList<Cerere>: Asteapta, care reține cererile în așteptare, și Finalizare, pentru cele deja preluate de către un funcționar.
- _Angajat, Elev, Pensionar, Persoana, EntitateJuridica_, subclase de tip Utilizator. Angajat reține numele companiei din care provine utilizatorul, iar EntitateJuridică conține numele reprezentantului;
- _Birou_, clasă generică care reține două colecții de tip LinkedList pentru a reține cererile și funționarii care corespund acelui birou, numele biroului și clasa din care trebuie să aparține fiecare user. Am ales să numesc biroul în funcție tipul cetățenilor care pot depune cereri la acel birou; 
- _Functionar_, clasă generică care reține numele funcționarului, referința către biroul căreia îi corespunde și clasa căruia trebuie să aparțină utilizatorii ai căror cerere este rezolvată de funcționarul curent.
