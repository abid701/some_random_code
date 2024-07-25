public class main2 {
    public static void main(String[] args) {
        int [] arr = {55, 69, 40, 90, 80, 73, 50, 79};
        int ziel = 55;
        ausgebenAnzahlBestanden(arr, 50);
    }

    public static void berechneZahlen (int zahl1, int zahl2) {
        if (zahl1 < zahl2){
            zahl1++;
        }
        else {
            zahl2++;
        }
        while (zahl1 < 10){
            zahl1 += 2;
            if(zahl2 > zahl1){
                zahl2--;
            }
        }
        System.out.println("1.Zahl: " + zahl1 + ", 2.Zahl: " + zahl2);

    }

    public static void ausgebenAnzahlBestanden(int[] arr, int ziel){
        int bestandeneLeute = 0;
        for (int i = 0; i < arr.length; i++){
            if (arr[i] >= ziel){
                bestandeneLeute++;
            }
        }
        System.out.println(bestandeneLeute + " Teilnehmende haben bestanden!");
    }
}
