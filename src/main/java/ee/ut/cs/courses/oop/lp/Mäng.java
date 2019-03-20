package ee.ut.cs.courses.oop.lp;

import java.util.Scanner;

public class Mäng {

    private int käike = 30;
    private final Mängulaud mängulaud = new Mängulaud();

    public int getKäike() {
        return this.käike;
    }

    public Mängulaud getMängulaud() {
        return this.mängulaud;
    }

    public static void main(String[] args) {

        System.out.println("Tere tulemast mängima laevade pommitamist!");

        Scanner käsurida = new Scanner(System.in);

        while (true) {
            System.out.println("Alustame uut mängu? (Jah/ei)");
            if (käsurida.nextLine().toLowerCase().startsWith("e")) {
                break;
            }

            Mäng mäng = new Mäng();

            while (!mäng.onLõppenud()) {
                System.out.println(mäng.getMängulaud());
                while (true) {

                    System.out.println("Mida soovid pommitada? (A-J0-9)");
                    try {
                        String vastus = käsurida.nextLine().strip();
                        int x = vastus.codePointAt(0) - 65;
                        int y = vastus.codePointAt(1) - 48;
                        mäng.pommita(x, y);
                    } catch (IndexOutOfBoundsException e) {
                        continue;
                    }
                    break;
                }
            }

            if (mäng.onVõidetud()) {
                System.out.println("Sa võitsid!");
            } else {
                System.out.println("Sa kaotasid!");
            }

        }
    }

    public boolean onKaotatud() {
        return this.getKäike() <= 0;
    }

    public boolean onLõppenud() {
        return this.onKaotatud() || this.onVõidetud();
    }

    public boolean onVõidetud() {
        for (var laev : this.getMängulaud().getLaevad()) {
            if (!laev.onHävitatud()) {
                return false;
            }
        }
        return !this.onKaotatud();
    }


    public void pommita(int x, int y) {
        this.käike--;
        for (var laev : this.getMängulaud().getLaevad()) {
            for (var positsioon : laev.getPositsioonid()) {
                if (positsioon.kattub(x, y)) {
                    positsioon.hävita();
                    break;
                }
            }
        }
    }
}