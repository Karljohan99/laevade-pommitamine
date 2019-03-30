package ee.ut.cs.courses.oop.lp;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Mängulaud {

    public static final int SUURUS = 10;

    private final Collection<Mängulaev> laevad = new ArrayList<>();

    /**
     * Genereeritakse 10 etteantud suurustega laeva juhuslikele positsioonidele
     */
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
                if (this.laevad.stream().anyMatch(laev::onLähedal)) {
                    continue;
                }
                this.laevad.add(laev);
            }
        }
    }

    private final Set<Mängupositsioon> pommitatud = new HashSet<>();

    public Set<Mängupositsioon> getPommitatud() {
        return this.pommitatud;
    }


    public Collection<Mängulaev> getLaevad() {
        return this.laevad;
    }

    /**
     * Antud positsioonil hävitamine ja positsiooni meelde jätimine
     *
     * @param positsioon Positsioon, mis hävitatakse
     */
    public void hävita(Mängupositsioon positsioon) {
        pommitatud.add(positsioon);
        this.getLaevad().forEach(laev -> laev.hävita(positsioon));
    }

    /**
     * Mängulaua genereerimine
     * @return Mängulaud
     */
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
                } else if (pommitatud.contains(positsioon)) {
                    sb.append(" O");
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
