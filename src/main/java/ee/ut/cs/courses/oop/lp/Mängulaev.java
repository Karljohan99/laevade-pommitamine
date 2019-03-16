package ee.ut.cs.courses.oop.lp;

import java.util.concurrent.ThreadLocalRandom;

public class Mängulaev {

    private final Positsioon[] positsioonid;

    public Mängulaev(int x, int y, int suurus) {
        this.positsioonid = new Positsioon[suurus];
        int dx = ThreadLocalRandom.current().nextInt(2);
        int dy = dx == 0 ? 1 : 0;
        for (int i = 0; i < suurus; i++) {
            this.positsioonid[i] = new Positsioon(x, y);
            x += dx;
            y += dy;
        }
    }

    public boolean asubMängulaual() {
        for (Positsioon positsioon : this.getPositsioonid()) {
            if (positsioon.getX() >= Mängulaud.SUURUS || positsioon.getY() >= Mängulaud.SUURUS) {
                return false;
            }
        }
        return true;
    }

    public Positsioon[] getPositsioonid() {
        return this.positsioonid;
    }

    public boolean kattub(int x, int y) {
        for (Positsioon positsioon : this.getPositsioonid()) {
            if (positsioon.kattub(x, y)) {
                return true;
            }
        }
        return false;
    }

    public boolean kattub(Mängulaev laev) {
        if (laev != null) {
            for (Positsioon positsioon : this.getPositsioonid()) {
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
        for (Positsioon positsioon : this.getPositsioonid()) {
            if (!positsioon.onHävitatud()) {
                return false;
            }
        }
        return true;
    }

    public int suurus() {
        return this.getPositsioonid().length;
    }

    public static class Positsioon {

        private boolean hävitatud;
        private final int x;
        private final int y;

        public Positsioon(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        public void hävita() {
            this.hävitatud = true;
        }

        public boolean kattub(int x, int y) {
            return this.getX() == x && this.getY() == y;
        }

        public boolean kattub(Positsioon positsioon) {
            return positsioon != null && this.kattub(positsioon.getX(), positsioon.getY());
        }

        public boolean kattub(Positsioon[] positsioonid) {
            for (Positsioon positsioon : positsioonid) {
                if (this.kattub(positsioon)) {
                    return true;
                }
            }
            return false;
        }

        public boolean onHävitatud() {
            return this.hävitatud;
        }

    }

}
