import java.util.concurrent.Semaphore ;
public class FilozofWykladNiesymetrycznesieganie extends Thread {
    static int MAX = 0;
    //static Semaphore [] widelec = new Semaphore [MAX] ;
    int mojNum;
    public FilozofWykladNiesymetrycznesieganie  ( int nr , int ilu ) {
        mojNum=nr ;  MAX = ilu ;
    }
    public void run ( ) {
        while ( true ) {
// myslenie
            System.out.println ( "Mysle ¦ " + mojNum) ;
            try {
                Thread.sleep ( ( long ) (5000 * Math.random( ) ) ) ;
            } catch ( InterruptedException e ) {
            }
            if (mojNum == 0) {
                System.out.println("Filozof Podnosi prawy widelec " + mojNum);
                Glowna.widelec [ (mojNum+1)%MAX].acquireUninterruptibly ( ) ;
                System.out.println("Filozof Podnosi lewy  widelec " + mojNum);
                Glowna.widelec [mojNum].acquireUninterruptibly ( ) ;
            } else {
                System.out.println("Filozof Podnosi lewy widelec " + mojNum);
                Glowna.widelec [mojNum].acquireUninterruptibly ( ) ;
                System.out.println("Filozof Podnosi prawy widelec " + mojNum);
                Glowna.widelec [ (mojNum+1)%MAX].acquireUninterruptibly ( ) ;
            }
// jedzenie
            System.out.println ( "Zaczyna jesc "+mojNum) ;
            try {
                Thread.sleep ( ( long ) (3000 * Math.random( ) ) ) ;
            } catch ( InterruptedException e ) {
            }
            System.out.println ( "Konczy jesc "+mojNum) ;
            System.out.println("Filozof odklada  lewy widelec " + mojNum);
            Glowna.widelec [mojNum].release ( ) ;
            System.out.println("Filozof odklada prawy  widelec " + mojNum);
           Glowna.widelec [ (mojNum+1)%MAX].release ( ) ;
        }
    }

}