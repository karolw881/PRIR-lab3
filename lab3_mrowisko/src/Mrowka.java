import java.util.Random;

public class Mrowka extends Thread{
    //definicja stanˇw samolotu
    static int MROWISKO = 1;
    static int START = 2;
    static int RUCH = 3;
    static int Etap_DROGI = 4;
    static int SMIERC = 5;
    static int REGENERACJA = 25;
    static int HP = 600;
    static boolean wygrana_z_potworem  ;
    //zmienne pomocnicze
    int numer;
    int energia_mrowki;
    int stan;
    Mrowisko m;
    Random rand;

    public Mrowka(int numer, int energy, Mrowisko m) {
        this.numer = numer;
        this.energia_mrowki = energy;
        this.stan = RUCH;
        this.m = m;
        rand = new Random();
    }

    public void run() {
        while (true) {
            if (stan == MROWISKO) {
                if (rand.nextInt(2) == 1) {
                    stan = START;
                    energia_mrowki = REGENERACJA;
                    System.out.println("Krolowo czy moge wyruszyc w trase ? " + numer);
                    stan = m.start(numer);
                    try {
                        Thread.sleep ( ( long ) (7000 * Math.random( ) ) ) ;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Dobrze poczekam jeszcze na zgode");
                }
            } else if (stan == START) {
                System.out.println("Mrowka wyruszyla o indeksie  " + numer);
                stan = RUCH;
                try {
                    Thread.sleep ( ( long ) (7000 * Math.random( ) ) ) ;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (stan == RUCH) {
                energia_mrowki -= rand.nextInt(500);
                if (energia_mrowki <= HP) {
                    stan = Etap_DROGI;
                } else try {
                    sleep(rand.nextInt(1000));
                } catch (Exception e) {
                }
            } else if (stan == Etap_DROGI) {
                System.out.println(" @@Królu Czy moge wyruszyc w niebezpiczna trase? " + numer + " ilosc energii " + energia_mrowki);
                stan = m.laduj();
                if (stan == Etap_DROGI) {
                    Random randint = new Random();
                    int czy_wygrala_z_bossem = randint.nextInt(0,6);
                    if (czy_wygrala_z_bossem < 3 || czy_wygrala_z_bossem >=0){
                        energia_mrowki += rand.nextInt(200);
                        System.out.println("WALCZ MROWKA WYGRYWAZ BOSSEM " + " MROWKA NUMER "+ numer  + "ENERGIA " + energia_mrowki);
                    }else{
                        energia_mrowki -= rand.nextInt(200);
                        System.out.println("WALCZ MROWKA PRZEGRYWA Z BOSSEM " + " MROWKA NUMER "+ numer  + "ENERGIA " + energia_mrowki);

                    }


                    energia_mrowki -= rand.nextInt(800);
                    System.out.println("Resztki sil  " + energia_mrowki);
                    if (energia_mrowki <= 0) stan = SMIERC;
                }
            } else if (stan == SMIERC) {
                System.out.println("Smierc mrowki z przemeczenia o indeksie " + numer);
                m.zmniejsz();
            }
        }
    }
}
