public class Mrowisko  {
    static int MROWISKO =1;
    static int RUSZAJ =2;
    static int RUSZANIEWDROGEMROWKI =3;
    static int KONIEC_PRZEJSCIA =4;
    static int SMIERC =5;
    int ilosc_sciezek;
    int ilosc_zajetych_sciezek;
    int ilosc_mrowek;

    Mrowisko(int ilosc_pasow,int ilosc_samolotow){
        this.ilosc_sciezek =ilosc_pasow;
        this.ilosc_mrowek =ilosc_samolotow;
        this.ilosc_zajetych_sciezek =0;
    }
    synchronized int start(int numer){
        ilosc_zajetych_sciezek--;
        System.out.println("Mrowka rusza :) "+numer);
        return RUSZAJ;
    }
    synchronized int laduj(){
        try{
            Thread.currentThread().sleep(1000);//sleep for 1000 ms
        }
        catch(Exception ie){
        }
        if(ilosc_zajetych_sciezek < ilosc_sciezek){
            ilosc_zajetych_sciezek++;
            System.out.println(" $$Ochrona przepuszcza walcz - mrowke do Mrowiska "+ ilosc_zajetych_sciezek);
            return MROWISKO;
        }
        else
        {return KONIEC_PRZEJSCIA;}
    }
    synchronized void zmniejsz(){
        ilosc_mrowek--;
        System.out.println("Umarlam");
        if(ilosc_mrowek == ilosc_sciezek) System.out.println("ILOSC Mrowek taka sama jak sciezek");
    }

}
