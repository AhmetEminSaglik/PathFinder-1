package aes_robot1;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class AES_Robot_1_English {

    public static String[][] LabirentAyarlama(int satir, int sutun) {
        Random random = new Random();
        String[][] Labirent_Yolu = new String[satir][sutun];          //Dizilere boş satır atar daha sonra | | yazabilmek için   
        for (int i = 0; i < satir; i++) {
            for (int j = 0; j < sutun; j++) {
                Labirent_Yolu[i][j] = " ";
            }
        }

        return Labirent_Yolu;
    }

    public static int GezmeyeBaslayalim(int satir, int sutun, String[][] Labirent) {
        Random random = new Random();
        String[][] Labirent_Yolu = new String[satir][sutun];

        Labirent_Yolu = Labirent;

        int j_baslangıc = random.nextInt(sutun);
        int referans_i = 0, referans_j = j_baslangıc;

        Labirent_Yolu[referans_i][j_baslangıc] = "☯";

        String[][] Referans = new String[satir][sutun];

        System.out.println("------------------------------------------------");
        System.out.println("----------------  THE MAZE  ----------------");
        System.out.println("------------------------------------------------");
        int cikis = random.nextInt(sutun);
        Labirent_Yolu[satir - 1][cikis] = "☺";

        for (int i = satir - 1; i >= 0; i--) {
            int satirlar_arasi_gecis = random.nextInt(sutun);
            for (int j = 0; j <= sutun - 1; j++) {
                if (i % 2 == 1 && j != satirlar_arasi_gecis) {
                    Labirent_Yolu[i][j] = "X";
                    System.out.print("|" + Labirent_Yolu[i][j] + "|");  //+i+","+j+   i ve j lerin yerlerdine numaraları gösterir
                    System.out.print("  ");
                } else {
                    System.out.print("|" + Labirent_Yolu[i][j] + "|");  //+i+","+j+   i ve j lerin yerlerdine numaraları gösterir
                    System.out.print("  ");
                }
            }
            System.out.println("");
        }

        System.out.println("------------------------------------------------");
        System.out.println("-----------  THE MAZE IS FINISHED -----------");
        System.out.println("------------------------------------------------");

        String Referansa_Dönüldü = "saga";
        int i = referans_i;
        int j = referans_j;          // i ve je baslangıc  noktasi
        int Saga_İlerlenen_Toplam_Birim = 0;
        int Sola_İlernen_Toplam_Birim = 0;
        while (true) {
            if (Labirent_Yolu[i][j].equals("☺")) {
                System.out.println("Our car has reached the exit");
                break;
            } else if (i < satir - 1 && Labirent_Yolu[i + 1][j].equals(" ")) {
                i += 2;
                referans_i = i;
                referans_j = j;
                Referansa_Dönüldü = "saga";//Once saga sonra sola ilerleyecegimiz icin buraya  bunu koydum
                System.out.println("Our car moved 2 lanes up");
                System.out.println("!!! Returned to the reference point  --- Position : [" + i + "," + j + "]--> Next direction is RIGHT side.");
            } else if (j == sutun - 1 && Referansa_Dönüldü.equals("saga")) {
                Referansa_Dönüldü = "sola";
                Sola_İlernen_Toplam_Birim += (j - referans_j);
                System.out.print("!!!   Maximum moved to the right, returning to the reference point to go left."
                        + "\nOur car moved " + (j - referans_j) + " units to the left and returned to the reference point.");
                j = referans_j;
                System.out.println("[" + i + "," + j + "]");
            } else if (j < sutun && Referansa_Dönüldü.equals("saga")) {
                j++;
                Saga_İlerlenen_Toplam_Birim++;
                System.out.println("Our car moved 1 lane to the right [" + i + "," + j + "]");
            } else if (j == 0) {
                System.out.println("!!!   Sol tarafa max. ilerleme kaydedildi. Referans noktasina dönülüyor ve saga tarafa gidilicek --> NOT : !!! SONSUZ DÖNGÜ OLABİLİR !!!");
                Saga_İlerlenen_Toplam_Birim += (referans_j - j);//j ile çıkarmaya gerek yok ama kafam karışmasın diye çıkardım
                j = referans_j;
                Referansa_Dönüldü = "saga";
            } else if (j >= 0 && Referansa_Dönüldü.equals("sola")) {
                j--;
                Sola_İlernen_Toplam_Birim++;
                System.out.println("Our car moved 1 lane to the left [" + i + "," + j + "]");
            }
        }
        System.out.println("Total units moved to the left : " + Sola_İlernen_Toplam_Birim);
        System.out.println("Total units moved to the right : " + Saga_İlerlenen_Toplam_Birim);
        return Sola_İlernen_Toplam_Birim + Saga_İlerlenen_Toplam_Birim;
    }

    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        int satir = 0;
        int sutun = 0;

        System.out.print("Enter the number of rows and columns separated by a space (enter at least 3,2):");
        if (scanner.hasNextInt()) {
            satir = scanner.nextInt();
        }
        if (scanner.hasNextInt()) {
            sutun = scanner.nextInt();
        }
        if (satir < 2) {
            satir = 2;
            System.out.println("Belirtilen degerden kucuk deger girdiginiz icin satir =" + satir + " alınmıştır");
        }
        if (sutun < 2) {
            sutun = 2;
            System.out.println("Belirtilen degerden kucuk deger girdiginiz icin sutun =" + sutun + " alınmıştır");
        }
        if (satir % 2 == 0) {
            satir++;
        }
        int Atılacak_Tur = 1;
        int Biten_Tur = 0;
        int En_Fazla_Adim = 0;
        int En_Az_Adim = Integer.MAX_VALUE;   //Başka türlü nasıl yazacağımı bilemedim
        int Adim_Sayisi = 0;
        while (Biten_Tur != Atılacak_Tur) {
            String[][] Labirent = LabirentAyarlama(satir, sutun);
            Adim_Sayisi = GezmeyeBaslayalim(satir, sutun, Labirent);
            if (Adim_Sayisi >= En_Fazla_Adim) {
                En_Fazla_Adim = Adim_Sayisi;
            } else if (Adim_Sayisi <= En_Az_Adim) {
                En_Az_Adim = Adim_Sayisi;
            }
            Biten_Tur++;
        }
    }
}
