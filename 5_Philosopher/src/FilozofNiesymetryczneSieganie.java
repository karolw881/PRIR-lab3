import java.util.concurrent.Semaphore ;

public class FilozofNiesymetryczneSieganie extends Thread {

    // max = 6 - 1
    static int MAX= 0;
   /// static Semaphore [] widelec = new Semaphore [MAX] ;
    int mojNum;
    Semaphore lewy_widelec ;
    Semaphore prawy_widelec ;
    public FilozofNiesymetryczneSieganie ( int nr , Semaphore lewy_widelec , Semaphore prawy_widelec , int ilu ) {
        mojNum=nr ;
        this.lewy_widelec = lewy_widelec;
        this.prawy_widelec = prawy_widelec;
        this.MAX = ilu ;
    }
    public void run ( ) {
        while ( true ) {
// myslenie
            System.out.println ( "Mysle ¦ " + mojNum) ;
            try {
                Thread.sleep ( ( long ) (7000 * Math.random( ) ) ) ;
                synchronized (lewy_widelec) {
                    Glowna.widelec[mojNum].acquireUninterruptibly();
                    System.out.println("podniosl lewy widelec " + mojNum);
                    Thread.sleep(((int) (Math.random() * 600)));


                    synchronized (prawy_widelec) {
                        System.out.println("Podniosl prawy widelec " + mojNum);
                        Glowna.widelec[(mojNum + 1) % MAX].acquireUninterruptibly(); //przechwycenie P widelca

                        Thread.sleep(((int) (Math.random() * 600)));
                        System.out.println("Zaczyna jesc " + mojNum);
                    }
                    synchronized (prawy_widelec){

                        System.out.println("Odlozyl prawy widlece Koncze jesc " + mojNum);
                        Glowna.widelec[(mojNum + 1) % MAX].release(); //zwolnienie P widelca
                        Thread.sleep(((int) (Math.random() * 600)));
                    }
                }

                    System.out.println("Odlozyl lewy widelec zaczyna myslec " + mojNum);
                    Glowna.widelec[mojNum].release(); //zwolnienie L widelca
                Thread.sleep(((int) (Math.random() * 600)));

            } catch ( InterruptedException e ) {
            }

        }
    }




    }

