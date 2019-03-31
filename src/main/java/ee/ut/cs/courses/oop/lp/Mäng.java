package ee.ut.cs.courses.oop.lp;

import java.util.Scanner;

public class Mäng {

    private int pommideArv = 60;
    private final Mängulaud mängulaud = new Mängulaud();

    public Mängulaud getMängulaud() {
        return this.mängulaud;
    }

    public int getPommideArv() {
        return this.pommideArv;
    }

    public static void main(String[] args) {

        System.out.println("Tere tulemast mängima laevade pommitamist!");
        System.out.println("Sinu ülesandeks on 60 pommiga põhja lasta kõik 10 laeva.");
        System.out.println("Laevade vahel on vähemalt üks tühi ruut.");

        Scanner käsurida = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("Alustame uut mängu? (Jah/ei)");
            if (käsurida.nextLine().toLowerCase().startsWith("e")) {
                break;
            }
            Mäng mäng = new Mäng();
            System.out.println(mäng.getMängulaud());
            while (!mäng.onLõppenud()) {
                long hävitatudLaevadeArv = mäng.getMängulaud().getLaevad().stream()
                        .filter(Mängulaev::onHävitatud)
                        .count();
                System.out.println("Pomme alles: " + mäng.getPommideArv());
                System.out.println("Laevu hävitatud: " + hävitatudLaevadeArv + "/10");
                while (true) {
                    System.out.println("Mida soovid pommitada? (A-J ja 0-9)");
                    try {
                        String vastus = käsurida.nextLine().toUpperCase();
                        int x = vastus.codePointAt(0) - 65; // tähed algavad kohalt 65
                        int y = vastus.codePointAt(1) - 48; // numbrid algavad kohalt 48
                        Mängupositsioon positsioon = new Mängupositsioon(x, y);
                        if (!positsioon.onMängulaual()) {
                            System.out.println("Sisestatud positsioon ei asu mängulaual!");
                            continue;
                        }
                        if (mäng.getMängulaud().onHävitatud(positsioon)) {
                            System.out.println("Seda positsiooni oled juba pommitanud!");
                            continue;
                        }
                        mäng.pommita(positsioon);
                        System.out.println(mäng.getMängulaud());
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
                        System.out.println();
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Sisesta mängulaua koordinaat, näiteks C4");
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
     * @return true, kui pommid on otsas, false, kui pommid ei ole otsas
     */
    public boolean onKaotatud() {
        return this.getPommideArv() <= 0;
    }

    /**
     * Kontrollimine, kas mäng on lõppenud
     *
     * @return true, kui mäng on kaotatud või võidetud, false, kui mäng käib
     */
    public boolean onLõppenud() {
        return this.onKaotatud() || this.onVõidetud();
    }

    /**
     * Kontrollimine, kas mäng on võidetud
     *
     * @return true, kui kõik laevad on hävitatud, false, kui mõni laev on veel alles
     */
    public boolean onVõidetud() {
        return this.getMängulaud().getLaevad().stream().allMatch(Mängulaev::onHävitatud) && !this.onKaotatud();
    }

    /**
     * Kasutaja poolt sisestatud positsiooni pommitamine ja pommide vähendamine ühe võrra
     *
     * @param positsioon Kasutaja poolt sisestatud positsioon
     */
    public void pommita(Mängupositsioon positsioon) {
        this.pommideArv--;
        this.getMängulaud().hävita(positsioon);
    }

}