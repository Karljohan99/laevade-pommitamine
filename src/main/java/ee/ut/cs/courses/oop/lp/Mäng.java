package ee.ut.cs.courses.oop.lp;

import java.util.Scanner;

public class Mäng {

    private int käike = 30;

    private Mängulaud mängulaud = new Mängulaud();

    public Mängulaud getMängulaud() {
        return mängulaud;
    }

    public int getKäike() {
        return this.käike;
    }

    public boolean onKaotatud() {
        return this.getKäike() <= 0;
    }

    public boolean onLõppenud() {
        return this.onKaotatud() || this.onVõidetud();
    }

    public boolean onVõidetud() {
        return !this.onKaotatud() && false;
    }

    public static void main(String[] args) {

        System.out.println("Tere tulemast mängima laevade pommitamist!");

        Scanner käsurida = new Scanner(System.in);

        while (true) {

            System.out.println("Alustame uut mängu? (Jah/ei)");

            if (käsurida.nextLine().strip().toLowerCase().startsWith("e")) {
                break;
            }

            Mäng mäng = new Mäng();

            while (!mäng.onLõppenud()) {
                System.out.println(mäng.getMängulaud());

                käsurida.nextLine();
            }

            if (mäng.onVõidetud()) {
                System.out.println("Sa võitsid!");
            } else {
                System.out.println("Sa kaotasid!");
            }

        }

    }

}
