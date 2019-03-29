package ee.ut.cs.courses.oop.lp;

import java.util.concurrent.ThreadLocalRandom;

public class Mängulaev {

    private final Mängupositsioon[] positsioonid;

    public Mängulaev(int x, int y, int suurus) {
        this.positsioonid = new Mängupositsioon[suurus];
        int dx = ThreadLocalRandom.current().nextInt(2);
        int dy = dx == 0 ? 1 : 0;
        for (int i = 0; i < suurus; i++) {
            this.positsioonid[i] = new Mängupositsioon(x, y);
            x += dx;
            y += dy;
        }
    }

    public boolean asubMängulaual() {
        for (Mängupositsioon positsioon : this.getPositsioonid()) {
            if (positsioon.getX() >= Mängulaud.SUURUS || positsioon.getY() >= Mängulaud.SUURUS) {
                return false;
            }
        }
        return true;
    }

    public Mängupositsioon[] getPositsioonid() {
        return this.positsioonid;
    }

    public boolean kattub(int x, int y) {
        for (Mängupositsioon positsioon : this.getPositsioonid()) {
            if (positsioon.kattub(x, y)) {
                return true;
            }
        }
        return false;
    }

    public boolean kattub(Mängulaev laev) {
        if (laev != null) {
            for (Mängupositsioon positsioon : this.getPositsioonid()) {
                if (positsioon.kattub(laev.getPositsioonid())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean kattub(Mängulaev[] laevad) {
        for (Mängulaev laev : laevad) {
            if (this.kattub(laev)) {
                return true;
            }
        }
        return false;
    }

    public boolean onHävitatud() {
        for (Mängupositsioon positsioon : this.getPositsioonid()) {
            if (!positsioon.onHävitatud()) {
                return false;
            }
        }
        return true;
    }

    public boolean onTabatud(int x, int y) {
        for (Mängupositsioon positsioon : this.getPositsioonid()) {
            if (positsioon.kattub(x, y) && positsioon.onHävitatud()) {
                return true;
            }
        }
        return false;
    }

    public int suurus() {
        return this.getPositsioonid().length;
    }

}
