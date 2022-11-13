import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class FraktalJulii extends Thread {
    final static int N = 4096;
    //stala okreslająca czy szereg manderglora w aktualnym punkcje będzie nieskończony
    final static int CUTOFF = 100;
    static int[][] set = new int[N][N];

    public static void main(String[] args) throws Exception {
//ustawienie stopera liczącego czas obliczeń
        long startTime = System.currentTimeMillis();
//ustawienie 4 wątków generujących fraktal w 4 ćwiartkach
        FraktalJulii thread0 = new FraktalJulii(0);
        FraktalJulii thread1 = new FraktalJulii(1);
        FraktalJulii thread2 = new FraktalJulii(2);
        FraktalJulii thread3 = new FraktalJulii(3);
        thread0.start();
        thread1.start();
        thread2.start();
        thread3.start();
//czekanie głównego programu na zakończenie 4 wątków roboczych
        thread0.join();
        thread1.join();
        thread2.join();
        thread3.join();

//zakończenie działania stopera i wyświetlenie czasu generowania fraktala
        long endTime = System.currentTimeMillis();
        System.out.println("Obliczenia zakończone w czasie " + (endTime - startTime) + " millisekund");
//ustawienie pliku graficznego, w którym zostanie wygenerowany fraktal
        BufferedImage img = new BufferedImage(N, N, BufferedImage.TYPE_INT_ARGB);
//wstawianie pixeli do pliku graficznego
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                double k = set[i][j];
                float level;
                if (k < CUTOFF) {
//pixel o wspolrzednych i,j należy do zbioru Manderbrota
                    level = (float) k / CUTOFF;
                } else {
//pixel o wspolrzednych i,j należy do zbioru Manderbrota
                    level = 0;
                }
//zapisywanie pixela (na zielono lub czarno)
                Color c = new Color(0, level, 0); // zielony
                img.setRGB(i, j, c.getRGB());
            }
        }
//zapis rysunku do pliku
        ImageIO.write(img, "PNG", new File("Julia1.png"));
    }

    int me;

    //konstruktor, który ustawie numeracje wątków
    public FraktalJulii(int me) {
        this.me = me;
    }

    //procedura wykonywana przez każdy z 4 wątków sprawdzająca czy dany punkt należy do zbioru Manderbrota
    public void run() {
        int begin = 0, end = 0;
        if (me == 0) {
            begin = 0;
            end = (N / 4) * 1;
        } else if (me == 1) {
            begin = (N / 4) * 1;
            end = (N / 4) * 2;
        } else if (me == 2) {
            begin = (N / 4) * 2;
            end = (N / 4) * 3;
        } else if (me == 3) {
            begin = (N / 4) * 3;
            end = N;
        }
        double cx = -0.73;
        double cy = 0.19;
        for (int i = begin; i < end; i++) {
            for (int j = 0; j < N; j++) {
                //przeskalowanie punktów cr i ci z dziedziny obrazka do
                //układu wspolrzednych kartezjanskich
                double cr = (4.0 * i - 2 * N) / N;
                double ci = (4.0 * j - 2 * N) / N;
                double zx = cr, zy = ci;
                int k = 0;
                while (zx * zx + zy * zy < 4 && k < CUTOFF) {
                    // z0 = p
                    // zn + 1 = zn ^ 2  + C
                    double xd = zx * zx - zy * zy + cx;
                    zy = zx * zy + zx * zy + cy;
                    zx = xd;
                    k++;
                }
                set[i][j] = k;
            }
        }
    }
}





