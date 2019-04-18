package ee.ut.cs.courses.oop.lp;

import javafx.scene.layout.TilePane;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static java.util.stream.Collectors.joining;

public class Mängulaev extends TilePane {

    public Mängulaev(int suurus) {
        int x = ThreadLocalRandom.current().nextInt(Mängulaud.SUURUS);
        int y = ThreadLocalRandom.current().nextInt(Mängulaud.SUURUS);
        if (ThreadLocalRandom.current().nextBoolean()) {
            // Horisontaalne
            for (int i = 0; i < suurus; i++, x++) {
                this.getChildren().add(new Mängupositsioon(x, y, Mängupositsioon.Sümbol.X, null));
            }
        } else {
            // Vertikaalne
            for (int i = 0; i < suurus; i++, y++) {
                this.getChildren().add(new Mängupositsioon(x, y, Mängupositsioon.Sümbol.X, null));
            }
        }
    }

    @Override
    protected List<Mängupositsioon> getManagedChildren() {
        return super.getManagedChildren();
    }

    /**
     * Kontrollimine, kas laev on hävitatud
     *
     * @return true, kui laev on hävitatud, false, kui laev ei ole hävitatud
     */
    public boolean onHävitatud() {
        return this.getManagedChildren().stream().allMatch(Mängupositsioon::onHävitatud);
    }

    /**
     * Kontrollimine, kas antud laev on lähedal sellele laevale
     *
     * @param laev Laev, mille lähedust kontrollitakse
     * @return true, kui see laev on lähedal teisele laevale, false, kui see laev ei ole lähedal teisele laevale
     */
    public boolean onLähedal(Mängulaev laev) {
        return this.getManagedChildren().stream().anyMatch(laev::onLähedal);
    }

    /**
     * Kontrollimine, kas antud positsoon on lähedal sellele laevale
     *
     * @param positsioon Positsioon, mille lähedust kontrollitakse
     * @return true, kui positsioon on lähedal, false, kui positsioon ei ole lähedal
     */
    public boolean onLähedal(Mängupositsioon positsioon) {
        return this.getManagedChildren().stream().anyMatch(positsioon::onLähedal);
    }

    /**
     * Kontrollimine, kas see laev on mängulaual
     *
     * @return true, kui see laev on mängulaual, false, kui see laev ei ole mängulaual
     */
    public boolean onMängulaual() {
        return this.getManagedChildren().stream().allMatch(Mängupositsioon::onMängulaual);
    }

    @Override
    public String toString() {
        return this.getManagedChildren().stream().map(Mängupositsioon::toString).collect(joining(" "));
    }

}
