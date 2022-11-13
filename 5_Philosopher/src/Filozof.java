
import java.util.concurrent.Semaphore ;
public class Filozof extends Thread {
     int MAX;
    //static Semaphore [] widelec = new Semaphore [MAX] ;
    int mojNum;
    public Filozof ( int nr  , int max ) {
        mojNum=nr ;
        MAX = max;
    }
    public void run ( ) {
        while ( true ) {
// myslenie
            System.out.println ( "Mysle ¦ " + mojNum) ;
            try {
                Thread.sleep ( ( long ) (7000 * Math.random( ) ) ) ;
            } catch ( InterruptedException e ) {
            }
            Glowna.widelec [mojNum].acquireUninterruptibly ( ) ; //przechwycenie L widelca
            System.out.println("Filozof podnosi lewy widelec " +  mojNum);
            try {
                Thread.sleep ( ( long ) (7000 * Math.random( ) ) ) ;
            } catch ( InterruptedException e ) {
            }
            Glowna.widelec [ (mojNum+1)%MAX].acquireUninterruptibly ( ) ; //przechwycenie P widelca
            System.out.println("Filozof podnosi prawy widelec " +  mojNum);
            try {
                Thread.sleep ( ( long ) (7000 * Math.random( ) ) ) ;
            } catch ( InterruptedException e ) {
            }
// jedzenie
            System.out.println ( "Zaczyna jesc "+mojNum) ;
            try {
                Thread.sleep ( ( long ) (5000 * Math.random( ) ) ) ;
            } catch ( InterruptedException e ) {
            }
            System.out.println ( "Konczy jesc "+mojNum) ;
            Glowna.widelec [mojNum].release ( ) ; //zwolnienie L widelca
            System.out.println("Filozof zwalia lewy widelec " +  mojNum);
            try {
                Thread.sleep ( ( long ) (7000 * Math.random( ) ) ) ;
            } catch ( InterruptedException e ) {
            }
            Glowna.widelec[ (mojNum+1)%MAX].release ( ) ; //zwolnienie P widelca
            System.out.println("Filozof zwalia prawy widelec " +  mojNum);

        }
    }





}



/*
*
* 5pNapisz program w Javie, który połączy wszystkie programy dotyczące problemu myślących i
jedzących Filozofów z wykładu 3 w jednym kodzie. Program powinien w trakcie uruchamiania
posiadać następujące opcje: ( wybór jednego z 3 wariantów do uruchomienia, sterowanie
liczbą filozofów w symulacji od 2 do 100). 0.
*
*
*
*
* */