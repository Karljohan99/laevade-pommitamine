package ee.ut.cs.courses.oop.lp;

public class Mängulaev {

    private final Positsioon[] positsioonid;

    public Mängulaev(Positsioon[] positsioonid) {
        this.positsioonid = positsioonid;
    }

    public Positsioon[] getPositsioonid() {
        return this.positsioonid;
    }

    public static class Positsioon {

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

    }

}
