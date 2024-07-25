import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Spiel zum Raten einer Ziffer.
 * Der Computer "denkt" sich eine Zufallszahl aus mehreren Ziffern aus.
 * Der Benutzer muss diese Zufallszahl erraten.
 * Programmier-Schritt 5a:
 *   Die Anzahl der zu ratenden Ziffern ist variabel.
 * Programmier-Schritt 5b:
 *   Eingabe aller Ziffern in einer Zeile und Einlesen als String.
 *   Prüfung des eingegebenen Strings auf numerisch, positiv und korrekte Länge.
 *   Try/Catch(Exception exc) beim Casten in einen Integer (für Test auf numerisch).
 *   Wenn korrekte Eingabe, zerlegen des Strings und speichern in int-Array.
 * Programmier-Schritt 5c:
 *   Ausgabe des Ergebnisses in eigene Methode ausgelagert.
 *   Methode ausgeben() erhält boolean zeilenumbruch als 2. Paramter.
 * Programmier-Schritt 5d:
 *   Abbruchmöglichkeit des Spiels mit 'quit' und Anzeige der korrekten Lösung.
 *   Equals-Methode zum String-Vergleich.
 * Programmier-Schritt 5e:
 *   Möglichkeit, Doppelte zu verhindern. Nur bei bis zu 9 Ziffern möglich!
 * Programmier-Schritt 5f:
 *   Auslagern von Utility-Methoden in Utility-Klassen.
 */
public class ZiffernRaten5 {

    // Konstante für die Anzahl der möglichen Zufalls-Ziffern:
    // 2 => Ziffern 0 und 1 sind möglich
    // 10 => Ziffern zwischen 0 und 9 sind möglich
    private final int ANZAHL_RANDOM = 10;

    // Konstante für die einzugebenden Zeichen für den Abbruch des Spiels
    private final String ABBRUCH = "quit";

    // Anzahl der zu ratenden Ziffern
    private int anzahlZiffern;

    // sollen doppelte Ziffern verhindert werden?
    private boolean doppelteZiffernVerhindern = false;

    /**
     * Methode zur Steuerung des Spiel-Ablaufs.
     */
    public void run() {

        spielStarten();

        // Mehrmals spielen zu den gleichen Bedingungen ermöglichen
        while(true) {

            // Array anlegen und mit Zufallszahlen füllen
            int[] zufallszahlen = createRandomNumbers();
            //ArraysUtil.ausgeben(zufallszahlen, true);

            // Array anlegen für die vom Benutzer eingegebenen Zahlen
            int[] userInput = new int[anzahlZiffern];

            // Schalter für gewonnen
            boolean gewonnen = false;

            // Zähler für Anzahl der Rateversuche
            int versuche = 0;

            // Schleife ausführen, bis der Benutzer gewonnen hat
            while (!gewonnen) {

                // Vom Benutzer die Eingabe der Ziffer anfordern
                if (!readUserNumbers(userInput)) {
                    // Abbruch des Spiels
                    System.out.println("Das Spiel wurde abgebrochen! Dies war die zu ratende Zahl:");
                    ArraysUtil.ausgeben(zufallszahlen, true);
                    return;
                }

                // eingegebene Ziffern ausgeben
                //ArraysUtil.ausgeben(userInput);

                // Die Anzahl der Rateversuche inkrementieren
                versuche++;

                // Die vom Benutzer angegebenen Ziffern mit der Lösung vergleichen
                boolean correct = compareArrays(zufallszahlen, userInput);
                // Prüfen, ob die eingebenen Ziffern den Zufalls-Ziffern entsprechen
                if (correct) {
                    System.out.println("DU HAST GEWONNEN!");
                    if (versuche == 1) {
                        System.out.println("Du hast nur einen Versuch gebraucht! Du bist super!");
                    } else {
                        System.out.println("Du hast " + versuche + " Versuche gebraucht!");
                        System.out.println("--------------------------------------------");
                    }
                    gewonnen = true;
                }
            }
        }
    }

    /**
     * Die Start-Parameter für das Spiel werden gesetzt.
     */
    private void spielStarten() {

        // Ausgabe der Aufforderung zum Spiel
        System.out.println("Hallo, spiel mit mir! In diesem Spiel kannst du Ziffern erraten.");

        // Nachfrage, wieviele Ziffern geraten werden sollen
        anzahlZiffernSetzen();

        // Nachfrage, ob doppelte Ziffern verhindert werden sollen
        if (anzahlZiffern < 10) {
            doppelteZiffernVerhindernSetzen();
        }

        // Tipp für Abbruch des Spiels
        System.out.println("Tipp: Gib statt den Ziffern '" + ABBRUCH + "' ein, wenn du das Spiel abbrechen und die zu ratende Ziffer angezeigt bekommen möchtest.");
    }

    /**
     * Die Anzahl der zu ratenden Ziffern wird vom Benutzer abgefragt.
     */
    private void anzahlZiffernSetzen() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wieviele Ziffern möchtest du raten?");

        boolean correct = false;
        while(!correct) {
            try {
                anzahlZiffern = scanner.nextInt();
                correct = true;
            } catch (InputMismatchException exc) {
                String s = scanner.next();
                System.out.println(s + " ist keine Ziffer!!!");
            }
        }
    }

    /**
     * Der Benutzer wird gefragt, ob doppelte Ziffern verhindert werden sollen.
     *
     */
    private void doppelteZiffernVerhindernSetzen() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Sollen doppelte Ziffern verhindert werden? Gib bitte 'ja' oder 'nein' ein.");
        String eingabe = scanner.next();
        this.doppelteZiffernVerhindern = eingabe.equals("ja");
    }

    /**
     * Füllen eines Arrays mit vom Benutzer eingegebenen Ziffern.
     *
     * @param arr zu füllendes Array
     * @return false, wenn das Spiel abgebrochen wird, sonst false
     */
    private boolean readUserNumbers(int[] arr) {

        Scanner scanner = new Scanner(System.in);
        boolean correct = false;

        while (!correct) {
            System.out.println("Bitte gebe die " + anzahlZiffern + " Ziffern ein!");
            String eingabe = scanner.next();
            if (eingabe.equals(ABBRUCH)) {
                return false;
            }
            correct = zerlegeZiffernString(eingabe, arr);
        }
        return true;
    }

    /**
     * Zerlegt den String in einzelne Ziffern und liefert ein Array mit diesen Ziffern zurück.
     * Der String wird hier geprüft auf numerisch, positiv und die richtige Länge.
     *
     * @param ziffernString zu zerlegender String
     * @return false, wenn der String nicht korrekt ist, sonst true.
     */
    private boolean zerlegeZiffernString(String ziffernString, int[] arr) {

        if (ziffernString.length() != arr.length) {
            System.out.println("Diese Ziffernfolge hat nicht die richtige Länge!");
            return false;
        } else if (! ZiffernUtil.checkIsNumericAndPositive(ziffernString)) {
            System.out.println("Die Eingabe ist keine ganze Zahl oder hat ein Plus- oder Minus-Zeichen!");
            return false;
        }
        for(int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(ziffernString.substring(i, i+1));
        }
        return true;
    }

    /**
     * Zwei Arrays werden miteinander verglichen.
     * Dabei wird ausgegeben, wieviele Ziffern korrekt sind
     * und wieviele korrekte Ziffern an der richtigen Stelle stehen.
     * Zwischenschritt 4c: Ermittlung, wieviele Ziffern richtig sind.
     *
     * @param arrLoesung Das Array mit der Lösung
     * @param arrUser Das Array mit den vom Benutzer eingegebenen Ziffern
     * @return liefert true zurück, wenn alle Ziffern richtig sind und an der richtigen Stelle stehen
     */
    public boolean compareArrays(int[] arrLoesung, int[] arrUser) {

        if (arrLoesung.length != arrUser.length) {
            System.out.println("FEHLER: Die Länge der Arrays stimmt nicht überein!");
            return false;
        }

        int correctDigits = 0;
        int correctPlaces = 0;

        for (int i = 0; i < anzahlZiffern; i++) {
            if (ArraysUtil.checkIsInArray(arrUser[i], arrLoesung)) {
                correctDigits++;
                if (arrLoesung[i] == arrUser[i]) {
                    correctPlaces++;
                }
            }
        }

        // Das Ergebnis ausgeben
        ausgabeErgebnis(correctDigits, correctPlaces, arrUser);

        // Prüfen, ob alle Ziffern korrekt sind und an der richtigen Stelle stehen
        return correctPlaces == anzahlZiffern;
    }

    /**
     * Das Ergebnis der Prüfung der eingegebenen Ziffern wird ausgegeben.
     * @param correctDigits Anzahl der korrekten Ziffern
     * @param correctPlaces Anzahl der Ziffern am richtigen Platz
     * @param arrUser die vom Benutzer eingegebene Zahl
     */
    private void ausgabeErgebnis(int correctDigits, int correctPlaces, int[] arrUser) {

        StringBuilder sb = new StringBuilder();
        ArraysUtil.ausgeben(arrUser, false);
        sb.append(" - ");
        switch (correctDigits) {
            case 0:
                sb.append("Keine Ziffer ist richtig. ");
                break;
            case 1:
                sb.append("Eine Ziffer ist richtig. ");
                break;
            default:
                sb.append(correctDigits + " Ziffern sind richtig. ");
                break;
        }
        switch (correctPlaces) {
            case 0:
                sb.append("Keine Ziffer steht am richtigen Platz.");
                break;
            case 1:
                sb.append("Eine Ziffer steht am richtigen Platz.");
                break;
            default:
                sb.append(correctPlaces + " Ziffern stehen am richtigen Platz.");
                break;
        }

        System.out.println(sb.toString());
    }

    /**
     * Erstellen von Ziffern per Zufall.
     *
     * @return Array mit den erstellten Ziffern
     */
    private int[] createRandomNumbers() {

        // Array mit den Zufallszahlen erstellen
        int[] zufallszahlen = new int[anzahlZiffern];
        // Alle Zahlen im Array auf den Wert -1 setzen (da sonst der Wert 0 immer als bereits vorhanden im Array gilt)
        for(int i = 0; i < zufallszahlen.length; i++) {
            zufallszahlen[i] = -1;
        }

        for (int i = 0; i < zufallszahlen.length; i++) {
            int ziffer = ZiffernUtil.createRandomNumber(ANZAHL_RANDOM);
            // wenn keine doppelten Ziffern erlaubt sind, prüfen, ob die Ziffer bereits im Array ist.
            // wenn ja, nochmal eine Zufallsziffer erstellen
            if (this.doppelteZiffernVerhindern) {
                while (ArraysUtil.checkIsInArray(ziffer, zufallszahlen)) {
                    ziffer = ZiffernUtil.createRandomNumber(ANZAHL_RANDOM);
                }
            }
            zufallszahlen[i] = ziffer;
        }

        // Array zurückliefern
        return zufallszahlen;
    }

}


