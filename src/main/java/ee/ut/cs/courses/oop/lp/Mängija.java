package ee.ut.cs.courses.oop.lp;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import static java.util.Comparator.comparingInt;
import static java.util.Objects.hash;

public class Mängija implements Comparable<Mängija> {

    private final StringProperty nimi;
    private final IntegerProperty skoor;

    /**
     * Mängija konstruktor
     *
     * @param nimi  Mängija nimi
     * @param skoor Mängija skoor
     */
    public Mängija(String nimi, int skoor) {
        this.nimi = new SimpleStringProperty(this, "nimi", nimi);
        this.skoor = new SimpleIntegerProperty(this, "skoor", skoor);
    }

    @Override
    public int compareTo(Mängija mängija) {
        return comparingInt(Mängija::getSkoor).reversed().thenComparing(Mängija::getNimi).compare(this, mängija);
    }

    @Override
    public final boolean equals(Object objekt) {
        if (this == objekt) return true;
        if (!(objekt instanceof Mängija)) return false;
        return this.nimi.get().toLowerCase().equals(((Mängija) objekt).nimi.get().toLowerCase());
    }

    public String getNimi() {
        return this.nimi.get();
    }

    public void setNimi(String nimi) {
        this.nimi.set(nimi);
    }

    public int getSkoor() {
        return this.skoor.get();
    }

    public void setSkoor(int skoor) {
        this.skoor.set(skoor);
    }

    @Override
    public final int hashCode() {
        return hash(this.nimi.get().toLowerCase());
    }

    public StringProperty nimiProperty() {
        return this.nimi;
    }

    public IntegerProperty skoorProperty() {
        return this.skoor;
    }

    @Override
    public String toString() {
        return this.getSkoor() + "\t" + this.getNimi();
    }

}
