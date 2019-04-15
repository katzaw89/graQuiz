package GraQuiz;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws IOException {

//      File plik = new File("src/main/resources/Quiz/Animals.txt");
        File folder = new File("src/main/resources/Quiz");
        File[] files = folder.listFiles();

        try {
            List<ZadanieQuizowe> pytania = wyborKategoriiPytan(files);
            losowaniePytanQuizu(pytania);

        } catch (Exception e) {
            e.getMessage();
        }
//   wczytajListePytan(scanner); //wczytuje metode z pytaniami z 1 kategorii


    }


    private static List<ZadanieQuizowe> wyborKategoriiPytan(File[] files) throws FileNotFoundException {
        System.out.println("Wybierz  nr kategorii : ");
        for (int i = 0; i < files.length; i++) {
            System.out.println((i + 1) + ". " + files[i].getName().replace(".txt", "").replace("_", " "));
        }
//        Gracz wybiera nr kategorii
        int nrKategorii = new Scanner(System.in).nextInt();
        Scanner wczytajKategorie = new Scanner(files[nrKategorii - 1]);
        System.out.println();
        System.out.println("Wybraleś kategorię: " + files[nrKategorii - 1].getName().replace(".txt", "").replace("_", " "));


        //      Miesza odpowiedzi i losuje 10 kolejnych
        return wczytajListePytan(wczytajKategorie);
    }

    private static void losowaniePytanQuizu(List<ZadanieQuizowe> pytania) {
        Collections.shuffle(pytania); // miesza pytania

        int uzyskanePunkty = 0;

        for (int i = 0; i < 10; i++) {
            ArrayList<String> odpowiedziKopia = new ArrayList<>(pytania.get(i).odpowiedzi);

            System.out.println();
            System.out.println((i + 1) + ". " + pytania.get(i).pytania); //losowe pyt
            Collections.shuffle(odpowiedziKopia);

            char x = 97;

            for (String listaOdp : odpowiedziKopia) {
                System.out.println(x + ". " + listaOdp);
                x++;
            }

            System.out.println();
            System.out.println("Podaj porawną odpowiedź: ");
            String wybranaOdpowiedź = new Scanner(System.in).nextLine();
            char literaUzytownika = wybranaOdpowiedź.charAt(0);
            String odpowiedzUzytkownika = odpowiedziKopia.get(literaUzytownika - 97);
            System.out.println("Wybrales " + odpowiedzUzytkownika);

            if (odpowiedzUzytkownika.equals(pytania.get(i).odpowiedzi.get(0))) {
                uzyskanePunkty++;
                System.out.println("Poprawna odpowiedź !! \nZdobywasz 1 punkt. \nTwój wynik to " + uzyskanePunkty + " pkt");
            } else {
                System.out.println("Zła odpowiedź. \nZdobywasz 0 punktow. \nTwój wynik to " + uzyskanePunkty + " pkt");
                System.out.println("Poprawna odpowiedź to " + pytania.get(i).odpowiedzi.get(0));
            }


        }
    }


    private static List<ZadanieQuizowe> wczytajListePytan(Scanner scanner) {
        ArrayList<ZadanieQuizowe> list = new ArrayList<>();
        while (scanner.hasNextLine()) {//hasNextLine zczytuje cały plik i sprawdza czy jest jeszcze coś napisane
            String pytanie = scanner.nextLine();
            //System.out.println("Pytanie : " + pytanie);  //wypisuje tresc pytania
            String ileOdpowiedzi = scanner.nextLine();
            int ileOdp = Integer.parseInt(ileOdpowiedzi);
            List<String> mozliweOdpowiedzi = new ArrayList<>();

            for (int i = 0; i < ileOdp; i++) {
                String jednaOdpowiedz = scanner.nextLine();
                mozliweOdpowiedzi.add(jednaOdpowiedz);
                //System.out.println("Odpowiedź : " + jednaOdpowiedz); // wypisuje tresc odpowiedzi
            }

            ZadanieQuizowe zadanieQuizowe = new ZadanieQuizowe();
            zadanieQuizowe.pytania = pytanie;
            zadanieQuizowe.odpowiedzi = mozliweOdpowiedzi;
            list.add(zadanieQuizowe);
        }
        return list;
    }
}


