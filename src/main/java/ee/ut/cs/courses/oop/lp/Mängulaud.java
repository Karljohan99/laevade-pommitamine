package ee.ut.cs.courses.oop.lp;

import java.util.concurrent.ThreadLocalRandom;

public class Mängulaud {

    public static final int SUURUS = 10;

    private final Mängulaev[] laevad = new Mängulaev[Mängulaud.SUURUS];

    public Mängulaud() {
        int[] suurused = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1};
        for (int i = 0; i < Mängulaud.SUURUS; i++) {
            int suurus = suurused[i];
            while (this.laevad[i] == null) {
                int x = ThreadLocalRandom.current().nextInt(Mängulaud.SUURUS);
                int y = ThreadLocalRandom.current().nextInt(Mängulaud.SUURUS);
                Mängulaev laev = new Mängulaev(x, y, suurus);
                if (!laev.asubMängulaual()) {
                    continue;
                }
                if (laev.kattub(this.laevad)) {
                    continue;
                }
                this.laevad[i] = laev;
            }
        }
    }


    public Mängulaev[] getLaevad() {
        return this.laevad;
    }

    public boolean tabatud(int x, int y){
        for(Mängulaev laev : this.getLaevad()){
            if (laev.onTabatud(x, y)) {
                return true;
            }
        }
        return false;
    }



    @Override
    public String toString() {
        String tähed = "  A B C D E F G H I J";
        StringBuilder sb = new StringBuilder();
        sb.append(tähed).append(System.lineSeparator());
        for (int y = 0; y < Mängulaud.SUURUS; y++) {
            sb.append(y);
            for (int x = 0; x < Mängulaud.SUURUS; x++) {
               if(tabatud(x, y)){
                   sb.append(" x");
               }else{
                   sb.append(" *");
               }
            }
            sb.append(" ").append(y).append(System.lineSeparator());
        }
        sb.append(tähed);
        return sb.toString();
    }

}
