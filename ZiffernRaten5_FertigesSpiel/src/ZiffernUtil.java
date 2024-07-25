public class ZiffernUtil {
    /**
     * Der String wird überprüft, ob er numerisch und positiv ist. Auch auf ein "+"-Zeichen am Anfang wird geprüft.
     * @param str der zu prüfende String
     * @return false, wenn der String nicht numerisch oder eine negative Zahl ist oder mit einem Plus-Zeichen startet.
     */
    public static boolean checkIsNumericAndPositive(String str) {
        try {
            Integer zahl = Integer.parseInt(str);
            return zahl >= 0 && !str.startsWith("+");
        } catch (NumberFormatException exc) {
            return false;
        }
    }

    /**
     * Erstellen einer Ziffer per Zufall.
     *
     * @param anzahlRandom Anzahl der möglichen Zufalls-Ziffern (10 => Ziffern 0 bis 9; 2 => Ziffern 0 bis 1)
     * @return die erstellte Ziffer
     */
    public static int createRandomNumber(int anzahlRandom) {

        // Math.random() liefert eine Gleitkommazahl zwischen 0.0 und 0.9999...
        // Das Casten der Gleitkommazahl zu einem int liefert die nächstkleinere ganze Zahl
        // Beispiel: Math.random liefert 0.7587 => 0.7587 * 10 = 7.587 => (int) 7.587 = 7
        return (int) (Math.random() * anzahlRandom);
    }
}
