
    import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

    public class Negatyw extends Thread  {
        BufferedImage image ;
        int width;
        int height;
        int watek;
        File input ;
        File input_temp ;
        int begin_x = 0 , end_x  = 0, begin_y = 0 , end_y = 0 ;






        public Negatyw (int watek) {
            this.watek = watek ;
            try {
//odczyt obrazu z pliku

                input = new File("final.jpg");
                image = ImageIO.read(input);
                width = image.getWidth();
                height = image.getHeight();

            }catch (Exception e){

            }

        }


        public void run() {
            try {

                if (watek == 1) {
                    begin_x = 0;
                    end_x = width / 2;
                    begin_y = 0;
                    end_y = height / 2;

                }
                if (watek == 2) {
                    begin_x = width / 2;
                    end_x = width;
                    begin_y = 0;
                    end_y = height / 2;

                }

                if (watek == 3) {
                    begin_x = 0;
                    end_x = width / 2;
                    begin_y = height / 2;
                    end_y = height;
                }

                if (watek == 4) {
                    begin_x = width / 2;
                    end_x = width;
                    begin_y = height / 2;
                    end_y = height;
                }


                for (int i = begin_x; i < end_x - 1; i++) {
                    for (int j = begin_y; j < end_y - 1; j++) {

                        //odczyt składowych koloru RGB
                        Color c = new Color(image.getRGB(j, i));
                        int red = (int) (c.getRed());
                        int green = (int) (c.getGreen());
                        int blue = (int) (c.getBlue());

                        int final_red, final_green, final_blue;

                        //negatyw
                        final_red = 255 - red;
                        final_green = 255 - green;
                        final_blue = 255 - blue;
                        Color newColor = new Color(final_red, final_green, final_blue);
                        image.setRGB(j,i,newColor.getRGB());
                    } //koniec dwóch pętli po kolumnach i wierszach obrazu

                }


            } catch (Exception e) {
            }

        }




        static public void polacz_obrazki(BufferedImage image1 , BufferedImage image2 , BufferedImage image3 , BufferedImage image4  ){
            BufferedImage zwracany = new BufferedImage(image1.getHeight(), image1.getHeight(),BufferedImage.TYPE_INT_RGB);
            for (int i = 0  ;  i < image1.getHeight() ; i++){
                for (int j = 0  ;  j < image1.getHeight() ; j++){
                    if ( i < image1.getWidth() /  2 && j < image1.getHeight() / 2 ){
                        zwracany.setRGB(i , j , image1.getRGB(i , j));
                    }
                    if ( i > image1.getWidth() /  2 && j < image1.getHeight() / 2 ){
                        zwracany.setRGB(i , j , image3.getRGB(i , j));
                    }
                    if ( i > image1.getWidth() /  2 && j > image1.getHeight() / 2 ){
                        zwracany.setRGB(i , j , image4.getRGB(i , j));
                    }
                    if ( i < image1.getWidth() /  2 && j > image1.getHeight() / 2 ){
                        zwracany.setRGB(i , j , image2.getRGB(i , j));
                    }


                }
            }
            try {
                File ouptut = new File("negatywxxx.jpg");
                ImageIO.write(zwracany, "jpg", ouptut);
            }catch (Exception e){}
        }

    }
