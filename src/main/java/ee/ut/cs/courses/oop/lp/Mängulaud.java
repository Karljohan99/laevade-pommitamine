package ee.ut.cs.courses.oop.lp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Mängulaud {

    public static final int SUURUS = 10;

    private final List<Mängulaev> laevad = new ArrayList<>();
    private final Set<Mängupositsioon> pommitatudPositsioonid = new HashSet<>();

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
                if (laev.onMängulaual() && this.laevad.stream().noneMatch(laev::onLähedal)) {
                    this.laevad.add(laev);
                }
            }
        }
    }

    public List<Mängulaev> getLaevad() {
        return this.laevad;
    }

    /**
     * Antud positsioonil laevade hävitamine ja positsiooni meelde jätimine
     *
     * @param positsioon Positsioon, mis hävitatakse
     */
    public void hävita(Mängupositsioon positsioon) {
        this.pommitatudPositsioonid.add(positsioon);
        this.getLaevad().forEach(laev -> laev.hävita(positsioon));
    }

    /**
     * Kontrollimine, kas positsioon on juba hävitatud
     *
     * @param positsioon Positsioon, mida kontrollitakse
     * @return true, kui positsioon on varem hävitatud, false, kui positsioon ei ole varem hävitatud
     */
    public boolean onHävitatud(Mängupositsioon positsioon) {
        return this.pommitatudPositsioonid.contains(positsioon);
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
                    sb.append(" X");
                } else if (this.pommitatudPositsioonid.contains(positsioon)) {
                    sb.append(" o");
                } else {
                    sb.append(" ·");
                }
            }
            sb.append(" ").append(y).append(System.lineSeparator());
        }
        sb.append(tähed).append(System.lineSeparator());
        return sb.toString();
    }

}
