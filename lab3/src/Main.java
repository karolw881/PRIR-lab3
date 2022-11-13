import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class Main {


    public Main() {
    }

    public static void main(String[] args) {



        MonteCarlo m1 = new MonteCarlo(1, 100000, 1);
        MonteCarlo m2 = new MonteCarlo(1, 100000, 2);
        MonteCarlo m3 = new MonteCarlo(1, 100000, 3);
        MonteCarlo m4 = new MonteCarlo(1, 100000, 4);
        m1.start();
        m2.start();
        m3.start();
        m4.start();

        try {
            m1.join();
            m2.join();
            m3.join();
            m4.join();

        }catch (Exception e){}


        System.out.println("Pole kola metoda Monte Carlo ");
        System.out.println(m1.pole_kola_metoda_monte_carlo(m1.ile_w_kole) + m2.pole_kola_metoda_monte_carlo(m2.ile_w_kole) + m3.pole_kola_metoda_monte_carlo(m3.ile_w_kole)
                + m4.pole_kola_metoda_monte_carlo(m4.ile_w_kole));
        System.out.println("Pole kola ze zwyklego wzoru ");
        System.out.println(0.5 * 0.5 * 3.14);




        Negatyw n1 = new Negatyw(1);
        Negatyw n2 = new Negatyw(2);
        Negatyw n3 = new Negatyw(3);
        Negatyw n4 = new Negatyw(4);

        n1.start();
        n2.start();
        n3.start();
        n4.start();
        try {
            n1.join();
            n2.join();
            n3.join();
            n4.join();

        }catch (Exception e){}


       Negatyw.polacz_obrazki(n1.image , n2.image , n3.image , n4.image);





    }







}

