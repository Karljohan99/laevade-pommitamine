package ee.ut.cs.courses.oop.lp;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Mängulaud {

    public static final int SUURUS = 10;

    private final Collection<Mängulaev> laevad = new ArrayList<>();

    public Mängulaud() {
        int[] suurused = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1};
        for (int i = 0; i < suurused.length; i++) {
            int suurus = suurused[i];
            while (this.laevad.size() == i) {
                int x = ThreadLocalRandom.current().nextInt(Mängulaud.SUURUS);
                int y = ThreadLocalRandom.current().nextInt(Mängulaud.SUURUS);
                Mängulaev laev = new Mängulaev(x, y, suurus);
                if (!laev.onMängulaual()) {
                    continue;
                }
                if (this.laevad.stream().anyMatch(laev::kattub)) {
                    continue;
                }
                this.laevad.add(laev);
            }
        }
    }


    public Collection<Mängulaev> getLaevad() {
        return this.laevad;
    }

    public void hävita(Mängupositsioon positsioon) {
        //TODO: salvestada positsioon
        this.getLaevad().forEach(laev -> laev.hävita(positsioon));
    }

    @Override
    public String toString() {
        String tähed = "  A B C D E F G H I J";
        StringBuilder sb = new StringBuilder();
        sb.append(tähed).append(System.lineSeparator());
        for (int y = 0; y < Mängulaud.SUURUS; y++) {
            sb.append(y);
            for (int x = 0; x < Mängulaud.SUURUS; x++) {
                Mängupositsioon positsioon = new Mängupositsioon(x, y);
                if (this.getLaevad().stream().anyMatch(laev -> laev.onHävitatud(positsioon))) {
                    sb.append(" x");
                } else {
                    sb.append(" *");
                }
            }
            sb.append(" ").append(y).append(System.lineSeparator());
        }
        sb.append(tähed);
        return sb.toString();
    }

}
