public class Main {
    static int ilosc_mrowek = 60;
    static int ilosc_sciezek = 7;
    static Mrowisko mrowisko;
    public static void main(String[] args) {
         mrowisko =new Mrowisko(ilosc_sciezek, ilosc_mrowek);
         for(int i = 0; i< ilosc_mrowek; i++)
            new Mrowka(i,600,mrowisko).start();
    }

}
