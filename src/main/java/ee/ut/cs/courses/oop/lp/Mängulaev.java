package ee.ut.cs.courses.oop.lp;

public class Mängulaev {

    private final Positsioon[] positsioonid;

    public Mängulaev(Positsioon[] positsioonid) {
        this.positsioonid = positsioonid;

    }

    public boolean onHävitatud() {
        for (Positsioon positsioon : positsioonid) {
            if (!positsioon.onHävitatud()) {
                return false;
            }
        }
        return true;
    }

    public Positsioon[] getPositsioonid() {
        return this.positsioonid;
    }

    public static class Positsioon {

        private final int x;
        private final int y;
        private boolean hävitatud;

        public Positsioon(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean onHävitatud() {
            return this.hävitatud;
        }

        public void hävita() {
            this.hävitatud = true;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

    }

}
