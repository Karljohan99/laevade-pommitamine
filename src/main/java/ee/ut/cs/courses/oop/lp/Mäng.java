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
        System.out.println("Sinu ülesandeks on 30 käigu jooksu põhja lasta kõik 10 laeva. ");
        System.out.println("Laevade vahel on vähemalt üks tühi ruut.");
        System.out.println();

        Scanner käsurida = new Scanner(System.in);

        while (true) {
            System.out.println("Alustame uut mängu? (Jah/ei)");
            if (käsurida.nextLine().toLowerCase().startsWith("e")) {
                break;
            }

            Mäng mäng = new Mäng();

            while (!mäng.onLõppenud()) {
                long hävitatudLaevadeArv = mäng.getMängulaud().getLaevad().stream()
                        .filter(Mängulaev::onHävitatud)
                        .count();
                System.out.println("Käike jäänud: " + mäng.getKäike());
                System.out.println("Laevu hävitatud: " + hävitatudLaevadeArv + "/10");
                System.out.println(mäng.getMängulaud());
                while (true) {
                    System.out.println("Mida soovid pommitada? (A-J0-9)");
                    try {
                        String vastus = käsurida.nextLine().toUpperCase();
                        int x = vastus.codePointAt(0) - 65;
                        int y = vastus.codePointAt(1) - 48;
                        Mängupositsioon positsioon = new Mängupositsioon(x, y);
                        if (!positsioon.onMängulaual()) {
                            System.out.println("Sisestatd positsioon ei asu mängulaual");
                            continue;
                        }
                        if (mäng.getMängulaud().getPommitatud().contains(positsioon)) {
                            System.out.println("Seda positsiooni oled juba pommitanud!");
                            continue;
                        }
                        mäng.pommita(positsioon);
                        if (mäng.getMängulaud().getLaevad().stream().anyMatch(laev -> laev.kattub(positsioon))) {
                            if (mäng.getMängulaud().getLaevad().stream()
                                    .filter(Mängulaev::onHävitatud)
                                    .count() > hävitatudLaevadeArv) {
                                System.out.println("Pihtas-põhjas!");
                            } else {
                                System.out.println("Pihtas!");
                            }
                        } else {
                            System.out.println("Möödas!");
                        }
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

    /**
     * Kontrollimine, kas mäng on kaotatud
     *
     * @return true, kui käigud on otsas, false, kui käigud ei ole otsas
     */
    public boolean onKaotatud() {
        return this.getKäike() <= 0;
    }

    /**
     * Kontrollimine, kas mäng on lõppenud
     * @return true, kui mäng on kaotatud või võidetud, false, kui mäng käib
     */
    public boolean onLõppenud() {
        return this.onKaotatud() || this.onVõidetud();
    }

    /**
     * Kontrollimine, kas mäng on võidetud
     * @return true, kui kõik laevad on hävitatud, false, kui mõni laev on veel alles
     */
    public boolean onVõidetud() {
        for (var laev : this.getMängulaud().getLaevad()) {
            if (!laev.onHävitatud()) {
                return false;
            }
        }
        return !this.onKaotatud();
    }

    /**
     * Kasutaja poolt sisestatud positsiooni pommitamine ja käikude arvu vähendamine ühe võrra, kui lasi mööda
     * @param positsioon Kasutaja poolt sisestatud positsioon
     */
    public void pommita(Mängupositsioon positsioon) {
        if (!this.getMängulaud().getLaevad().stream().anyMatch(laev -> laev.kattub(positsioon))) {
            this.käike--;
        }
        this.getMängulaud().hävita(positsioon);
    }

}