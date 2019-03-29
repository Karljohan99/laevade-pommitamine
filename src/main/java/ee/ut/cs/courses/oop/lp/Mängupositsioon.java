package ee.ut.cs.courses.oop.lp;

public class Mängupositsioon {

    private boolean hävitatud;
    private final int x;
    private final int y;

    public Mängupositsioon(int x, int y) {
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

    public boolean kattub(Mängupositsioon positsioon) {
        return positsioon != null && this.kattub(positsioon.getX(), positsioon.getY());
    }

    public boolean kattub(Mängupositsioon[] positsioonid) {
        for (Mängupositsioon positsioon : positsioonid) {
            if (this.kattub(positsioon)) {
                return true;
            }
        }
        return false;
    }

    public boolean onHävitatud() {
        return this.hävitatud;
    }

    @Override
    public String toString() {
        return "(" + this.getX() + ", " + this.getY() + ")";
    }

}