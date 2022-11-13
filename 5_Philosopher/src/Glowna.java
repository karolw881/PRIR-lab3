import java.awt.*;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Glowna {
    static  Semaphore[] widelec;
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";


    static Boolean czy_w_zakresie(int x){
        if( x >=2  && x <= 100){
            return true ;
        }
        return false ;
    }

    public static void main(String[] args) {

        int jeden = 1, dwa = 2, trzy = 3, cztery = 4, ilu = 99;
        System.out.println("Ktory wariant ?  (cyfra od 1 d 4 ) ");
        Scanner scanner = new Scanner(System.in);
        int ktory_wybrac = scanner.nextInt();



        if (ktory_wybrac == 1) {
            System.out.println(ANSI_YELLOW + "Wybrales wariant z warunkiem bezpieczenstwa ale mozliwym zaglodzeniem filozofa  " + ANSI_RESET);
            System.out.println("Ilu Filozofow potrzeba do myslenia ? : ) ");

            boolean czy;
            try {
                ilu = scanner.nextInt();
                czy = czy_w_zakresie(ilu);
               while(czy == false){
                   System.out.println("Liczba w zakresie od 2 do 100 !!!!!!!");
                   ilu = scanner.nextInt();
                   czy = czy_w_zakresie(ilu);
               }


            } catch (Exception e) {
                System.out.println("Nie wpisales cyfry ");

            }

         widelec = new Semaphore[ilu];
            //System.out.println(ilu);
            for (int i = 0; i < ilu; i++) {
                widelec[i] = new Semaphore(1);
            }
            for (int i = 0; i < ilu; i++) {
                new Filozof(i , ilu).start();
            }
        } else if (ktory_wybrac == 2) {
            System.out.println(ANSI_CYAN + "Wybrales wariant z warunkiem bezpieczenstwa   " +  ANSI_RESET);
            System.out.println("Ilu Filozofow potrzeba do myslenia ? : ) ");
            boolean czy;
            try {
                ilu = scanner.nextInt();
                czy = czy_w_zakresie(ilu);
                while(czy == false){
                    System.out.println("Liczba w zakresie od 2 do 100 !!!!!!!");
                    ilu = scanner.nextInt();
                    czy = czy_w_zakresie(ilu);
                }


            } catch (Exception e) {
                System.out.println("Nie wpisales cyfry ");

            }

         widelec = new Semaphore[ilu];
            for (int i = 0; i < ilu; i++) {
                widelec[i] = new Semaphore(1);
            }
            for (int i = 0; i < ilu; i++) {
                new FilozofWykladNiesymetrycznesieganie(i , ilu).start();
            }
        } else if (ktory_wybrac == 3) {
            System.out.println(ANSI_PURPLE_BACKGROUND + "Wybrales opcje z rzutem moneta " + ANSI_RESET);
            System.out.println("Ilu Filozofow potrzeba do myslenia ? : ) ");
            boolean czy;
            try {
                ilu = scanner.nextInt();
                czy = czy_w_zakresie(ilu);
                while(czy == false){
                    System.out.println("Liczba w zakresie od 2 do 100 !!!!!!!");
                    ilu = scanner.nextInt();
                    czy = czy_w_zakresie(ilu);
                }


            } catch (Exception e) {
                System.out.println("Nie wpisales cyfry ");

            }
            widelec = new Semaphore[ilu];
            for (int i = 0; i < ilu; i++) {
                widelec[i] = new Semaphore(1);
            }
            for (int i = 0; i < ilu; i++) {
                new FilozofRzutMoneta(i , ilu).start();
            }
        } else if (ktory_wybrac == 4) {
            System.out.println(ANSI_CYAN +"Wybrales wersje Problem ucztujących filozofów z niesymetrycznym\n" +
                    "sięganiem po widelce jako moja  modernizaccje " +  ANSI_RESET );
            System.out.println("Ilu Filozofow potrzeba do myslenia ?  W tym wariancie podaj n + 1 : ) ");
            boolean czy;
            try {
                ilu = scanner.nextInt();
                czy = czy_w_zakresie(ilu);
                while(czy == false){
                    System.out.println("Liczba w zakresie od 2 do 100 !!!!!!!");
                    ilu = scanner.nextInt();
                    czy = czy_w_zakresie(ilu);
                }


            } catch (Exception e) {
                System.out.println("Nie wpisales cyfry ");

            }

            widelec = new Semaphore[ilu];
            for (int i = 0; i < ilu; i++) {
                widelec[i] = new Semaphore(1);
            }
            for (int i = 0; i < ilu; i++) {
                Semaphore lewy_widelec = widelec[i + 1];
                Semaphore pray_widelec = widelec[(i + 1) % ilu];

                if (i == ilu - 1) {
                    new FilozofNiesymetryczneSieganie(i, pray_widelec, lewy_widelec , ilu ).start();
                } else {
                    new FilozofNiesymetryczneSieganie(i, lewy_widelec, pray_widelec , ilu ).start();
                }

            }


        } else {
            System.out.println("Wybrales nie odpowiednia cyfre :( aplikacja zakonczy dzialanie ");

        }


    }
}
