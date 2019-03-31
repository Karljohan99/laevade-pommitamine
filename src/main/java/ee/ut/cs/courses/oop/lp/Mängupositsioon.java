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

    /**
     * Positsiooni hävitamine
     */
    public void hävita() {
        this.hävitatud = true;
    }

    /**
     * Kontrollimine, kas positsioon on hävitatud
     *
     * @return true, kui positsioon on hävitatud, false, kui positsioon ei ole hävitatud
     */
    public boolean onHävitatud() {
        return this.hävitatud;
    }

    /**
     * Kontrollimine, kas antud positsoon on lähedal sellele positsioonile
     *
     * @param positsioon Positsioon, mille lähedust kontrollitakse
     * @return true, kui positsioon on lähedal, false, kui positsioon ei ole lähedal
     */
    public boolean onLähedal(Mängupositsioon positsioon) {
        return this.getX() == positsioon.getX() && this.getY() == positsioon.getY() // sama
                || this.getX() == positsioon.getX() && this.getY() == positsioon.getY() - 1 // üleval
                || this.getX() == positsioon.getX() && this.getY() == positsioon.getY() + 1 // all
                || this.getX() == positsioon.getX() - 1 && this.getY() == positsioon.getY() // vasakul
                || this.getX() == positsioon.getX() - 1 && this.getY() == positsioon.getY() - 1 // vasakul üleval
                || this.getX() == positsioon.getX() - 1 && this.getY() == positsioon.getY() + 1 // vasakul all
                || this.getX() == positsioon.getX() + 1 && this.getY() == positsioon.getY() // paremal
                || this.getX() == positsioon.getX() + 1 && this.getY() == positsioon.getY() - 1 // paremal üleval
                || this.getX() == positsioon.getX() + 1 && this.getY() == positsioon.getY() + 1; // paremal all
    }

    /**
     * Kontrollimine, kas see positsioon on mängulaual
     *
     * @return true, kui on mängulaual, false, kui ei ole mängulaual
     */
    public boolean onMängulaual() {
        return this.getX() >= 0 && this.getX() < Mängulaud.SUURUS
                && this.getY() >= 0 && this.getY() < Mängulaud.SUURUS;
    }

    @Override
    public String toString() {
        return "(" + this.getX() + ", " + this.getY() + ")";
    }

}
