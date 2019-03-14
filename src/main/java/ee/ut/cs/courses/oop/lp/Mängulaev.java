package ee.ut.cs.courses.oop.lp;

public class Mängulaev {

    private final Positsioon[] positsioonid;

    public Mängulaev(Positsioon[] positsioonid) {
        this.positsioonid = positsioonid;

    }

    public Positsioon[] getPositsioonid() {
        return this.positsioonid;
    }

    public boolean onHävitatud() {
        for (Positsioon positsioon : this.getPositsioonid()) {
            if (!positsioon.onHävitatud()) {
                return false;
            }
        }
        return true;
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

        public boolean onHävitatud() {
            return this.hävitatud;
        }

    }

}
