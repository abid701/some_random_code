public class ArraysUtil {

    /**
     * Prüfen, ob die übergebene Ziffer im Array ist.
     *
     * @param digit zu prüfende Ziffer
     * @param arr auszugebendes Array
     * @return true, wenn die Ziffer im Array vorhanden ist, sonst false
     */
    public static boolean checkIsInArray(int digit, int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (digit == arr[i]) {
                return true;
            }
        }
        return false;
    }

    /**
     * Ausgeben eines Arrays.
     *
     * @param arr auszugebendes Array
     * @param zeilenende wenn true, wird ein Zeilenumbruch geschrieben
     */
    public static void ausgeben(int[] arr, boolean zeilenende) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
        if (zeilenende) {
            System.out.println();
        }
    }
}
