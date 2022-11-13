import java.util.Random;

public class MonteCarlo extends Thread {

    private double  pole_kwadratu_opisanego , r ;
    private int ilosc_losowanych_punktow ;
    private Random random ;
    public double ile_w_kole = 0 ;
    public int ilosc_watkow ;
    public double dlugosc_boku_kwadratu_opisanego ;

    MonteCarlo(double dlugosc_boku_kwadratu_opisanego , int ilosc_losowanych_punktow , int ilosc_watkow){
        this.pole_kwadratu_opisanego = dlugosc_boku_kwadratu_opisanego * dlugosc_boku_kwadratu_opisanego ;
        this.r = dlugosc_boku_kwadratu_opisanego * 0.5   ;
        this.ilosc_losowanych_punktow = ilosc_losowanych_punktow ;
        this.ilosc_watkow = ilosc_watkow ;
        this.dlugosc_boku_kwadratu_opisanego = dlugosc_boku_kwadratu_opisanego ;
        random = new Random();
    }

    public double pole_kola_metoda_monte_carlo(double ile_punktow_w_kole_wylosowanych){
        return    pole_kwadratu_opisanego *  (ile_punktow_w_kole_wylosowanych   /  ilosc_losowanych_punktow ) ;
    }

    public void run() {
        double begin = 0, end = 0 , x , y ;
        if (ilosc_watkow == 1) {
            begin = (dlugosc_boku_kwadratu_opisanego / 2) * 1;
            end = dlugosc_boku_kwadratu_opisanego ;
        }
        else if (ilosc_watkow== 2) {
            begin = dlugosc_boku_kwadratu_opisanego / 2  ;
            end = (dlugosc_boku_kwadratu_opisanego  ) ;
        }
        else if (ilosc_watkow == 3) {
            begin = (dlugosc_boku_kwadratu_opisanego  / 2) ;
            end = dlugosc_boku_kwadratu_opisanego ;
        }
        else if (ilosc_watkow == 4) {
            begin = 0;
            end = (dlugosc_boku_kwadratu_opisanego  / 2) ;
        }

        for (int j =0; j < ilosc_losowanych_punktow ; j++) {
                x = random.nextDouble(begin, end);
                y = random.nextDouble(begin , end);
                if (Math.pow(x, 2) + Math.pow(y, 2) < Math.pow(r, 2)) ile_w_kole++;
        }
    }
}


