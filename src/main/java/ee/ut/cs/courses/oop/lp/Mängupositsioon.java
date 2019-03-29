package ee.ut.cs.courses.oop.lp;

import static java.util.Objects.hash;

public class Mängupositsioon {

    private boolean hävitatud;
    private final int x;
    private final int y;

    public Mängupositsioon(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public final boolean equals(Object objekt) {
        if (this == objekt) return true;
        if (!(objekt instanceof Mängupositsioon)) return false;
        Mängupositsioon positsioon = (Mängupositsioon) objekt;
        return this.x == positsioon.x && this.y == positsioon.y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    @Override
    public final int hashCode() {
        return hash(x, y);
    }

    public void hävita() {
        this.hävitatud = true;
    }

    public boolean onHävitatud() {
        return this.hävitatud;
    }

    public boolean onLähedal(Mängupositsioon positsioon) {
        return this.getX() == positsioon.getX() && this.getY() == positsioon.getY()
                || this.getX() == positsioon.getX() && this.getY() == positsioon.getY() - 1
                || this.getX() == positsioon.getX() && this.getY() == positsioon.getY() + 1
                || this.getX() == positsioon.getX() - 1 && this.getY() == positsioon.getY()
                || this.getX() == positsioon.getX() - 1 && this.getY() == positsioon.getY() - 1
                || this.getX() == positsioon.getX() - 1 && this.getY() == positsioon.getY() + 1
                || this.getX() == positsioon.getX() + 1 && this.getY() == positsioon.getY()
                || this.getX() == positsioon.getX() + 1 && this.getY() == positsioon.getY() - 1
                || this.getX() == positsioon.getX() + 1 && this.getY() == positsioon.getY() + 1;
    }

    public boolean onMängulaual() {
        return this.getX() >= 0 && this.getX() < Mängulaud.SUURUS
                && this.getY() >= 0 && this.getY() < Mängulaud.SUURUS;
    }

    @Override
    public String toString() {
        return "(" + this.getX() + ", " + this.getY() + ")";
    }

}
