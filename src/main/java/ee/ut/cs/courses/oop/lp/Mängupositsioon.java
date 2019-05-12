package ee.ut.cs.courses.oop.lp;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import static java.lang.Integer.compare;
import static java.util.Objects.hash;

public class Mängupositsioon extends Mängunupp implements Comparable<Mängupositsioon> {


    protected static final Border FOOKUSES_RAAM = new Border(new BorderStroke(Color.BLACK,
            BorderStrokeStyle.DASHED, CornerRadii.EMPTY, new BorderWidths(2, 2, 2, 2)));

    private static ObjectProperty<Mängupositsioon> eelmine = new SimpleObjectProperty<>();

    protected final BooleanProperty hävitatud = new SimpleBooleanProperty();
    protected final Sümbol sümbol;
    private final int x;
    private final int y;

    public Mängupositsioon(int x, int y) {
        this(x, y, Sümbol.O);
    }

    public Mängupositsioon(int x, int y, Sümbol sümbol) {
        this(x, y, sümbol, Character.toString(x + 65) + y);
    }

    public Mängupositsioon(int x, int y, Sümbol sümbol, String tekst) {
        super(tekst);
        this.sümbol = sümbol;
        this.hävitatud.addListener(v -> this.setBackground(this.sümbol.taust));
        this.x = x;
        this.y = y;
    }

    @Override
    protected Mängupositsioon clone() {
        Mängupositsioon uus = new Mängupositsioon(this.x, this.y, this.sümbol);
        this.borderProperty().bind(uus.borderProperty());
        this.hävitatud.bindBidirectional(uus.hävitatud);
        return uus;
    }

    @Override
    public final int compareTo(Mängupositsioon positsioon) {
        return compare(this.getX() + this.getY() * Mängulaud.SUURUS,
                positsioon.getX() + positsioon.getY() * Mängulaud.SUURUS);
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
        this.hävitatud.set(true);
        if (eelmine.isNotNull().getValue()) {
            eelmine.getValue().setBorder(ALGNE_RAAM);
        }
        eelmine.setValue(this);
        this.setFocusTraversable(false);
        this.setBorder(FOOKUSES_RAAM);
    }

    /**
     * Kontrollimine, kas positsioon on hävitatud
     *
     * @return true, kui positsioon on hävitatud, false, kui positsioon ei ole hävitatud
     */
    public boolean onHävitatud() {
        return this.hävitatud.get();
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
        return this.getX() >= 0 && this.getX() < Mängulaud.SUURUS && this.getY() >= 0 && this.getY() < Mängulaud.SUURUS;
    }

    @Override
    public String toString() {
        return this.onHävitatud() ? this.sümbol.toString() : "·";
    }

    public enum Sümbol {

        X("X", new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY))),
        O("o", new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        private final String sümbol;
        private final Background taust;

        Sümbol(String sümbol, Background taust) {
            this.sümbol = sümbol;
            this.taust = taust;
        }

        @Override
        public String toString() {
            return this.sümbol;
        }

    }

}
