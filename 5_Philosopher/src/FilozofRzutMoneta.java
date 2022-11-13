import java.util.Random;
import java.util.concurrent.Semaphore ;
public class FilozofRzutMoneta extends Thread {
    static int MAX;
  //  static Semaphore [] widelec = new Semaphore [MAX] ;
    int mojNum;
    Random losuj ;
    public  FilozofRzutMoneta ( int nr , int ilu ) {
        MAX = ilu ;
        mojNum=nr ;
        losuj = new Random(mojNum) ;
    }
    public void run ( ) {
        while ( true ) {
// myslenie
            System.out.println ( "Mysle ¦ " + mojNum) ;
            try {
                Thread.sleep ( ( long ) (5000 * Math.random( ) ) ) ;
            } catch ( InterruptedException e ) {
            }
            int strona = losuj.nextInt ( 2 ) ;

            boolean podnioslDwaWidelce = false ;

            do {
                if ( strona == 0) {
                    System.out.println("wylosowano po rzucie moneta orla : " + "Dla filozofa o numerze " + mojNum );
                    System.out.println("Podniosl lewy widelec filozof : " + mojNum);
                    Glowna.widelec [mojNum].acquireUninterruptibly ( ) ;
                    try {
                        Thread.sleep(((int) (Math.random() * 600)));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if( ! ( Glowna.widelec [ (mojNum+1)%MAX].tryAcquire ( ) ) ) {
                        Glowna.widelec[mojNum].release ( ) ;
                        System.out.println("Odlozyl lewy widelec filozof o numerze " + mojNum);
                        try {
                            Thread.sleep(((int) (Math.random() * 600)));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("Podniosl oba  widelce filozof : " + mojNum);
                        podnioslDwaWidelce = true ;
                        try {
                            Thread.sleep(((int) (Math.random() * 600)));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    System.out.println("wylosowano po rzucie moneta reszke : " + "Dla filozofa o numerze " + mojNum);
                    System.out.println("Podniosl prawy widelec filozof : " + mojNum);
                    Glowna.widelec[(mojNum+1)%MAX].acquireUninterruptibly ( ) ;
                    if ( ! (Glowna.widelec[mojNum].tryAcquire ( ) ) ) {
                        Glowna.widelec[(mojNum+1)%MAX].release ( ) ;
                        System.out.println("ODlozyl prawy widelec :  " + mojNum);
                        try {
                            Thread.sleep(((int) (Math.random() * 600)));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("Podniosl oba  widelce filozof : " + mojNum);
                        podnioslDwaWidelce = true ;
                        try {
                            Thread.sleep(((int) (Math.random() * 600)));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } while ( podnioslDwaWidelce == false ) ;
            System.out.println ( "Zaczyna jesc "+mojNum) ;
            try {
                Thread.sleep(((int) (Math.random() * 600)));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }



            try {
                Thread.sleep ( ( long ) (3000 * Math.random( ) ) ) ;
            } catch ( InterruptedException e ) {
            }

            System.out.println ( "Konczy jesc "+mojNum) ;
            Glowna.widelec [mojNum].release ( ) ;
            System.out.println("Odlozyl lewy widelec filozof o numerze : " + mojNum);
            Glowna.widelec [ (mojNum+1)%MAX].release ( ) ;
            System.out.println("Odlozyl prawy widelec filozof o numerze " + mojNum);
        }
    }
 }