package ee.ut.cs.courses.oop.lp;

import javafx.scene.layout.GridPane;
import java.util.ArrayList;
import java.util.List;

import static java.util.Comparator.naturalOrder;

public class Mängulaud extends GridPane {

    public static final int SUURUS = 10;

    private final List<Mängulaev> laevad = new ArrayList<>();

    /**
     * Genereeritakse 10 etteantud suurustega laeva juhuslikele positsioonidele
     */
    public Mängulaud() {
        int[] suurused = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1};
        for (int i = 0; i < suurused.length; i++) {
            int suurus = suurused[i];
            while (this.laevad.size() == i) {
                Mängulaev laev = new Mängulaev(suurus);
                if (laev.onMängulaual() && this.laevad.stream().noneMatch(laev::onLähedal)) {
                    laev.getManagedChildren().stream()
                            .map(Mängupositsioon::clone)
                            .forEach(this.getManagedChildren()::add);
                    this.laevad.add(laev);
                }
            }
        }
        for (int y = 0; y < Mängulaud.SUURUS; y++) {
            for (int x = 0; x < Mängulaud.SUURUS; x++) {
                Mängupositsioon positsioon = new Mängupositsioon(x, y);
                if (!this.getManagedChildren().contains(positsioon)) {
                    this.getManagedChildren().add(positsioon);
                }
            }
        }
        this.getManagedChildren().sort(naturalOrder());
        this.getManagedChildren().forEach(positsioon -> this.add(positsioon, positsioon.getX(), positsioon.getY()));
    }

    @Override
    protected List<Mängupositsioon> getManagedChildren() {
        return super.getManagedChildren();
    }

    public List<Mängulaev> getLaevad() {
        return this.laevad;
    }

    public int getSUURUS() {
        return SUURUS;
    }

    public Mängupositsioon getPositsioon(int x, int y) {
        if (x < 0 || x >= Mängulaud.SUURUS || y < 0 || y >= Mängulaud.SUURUS) {
            throw new IllegalArgumentException("Sisestatud positsioon ei asu mängulaual!");
        }
        return this.getManagedChildren().get(x + y * Mängulaud.SUURUS);
    }

    @Override
    public String toString() {
        String tähed = "  A B C D E F G H I J";
        StringBuilder sb = new StringBuilder();
        sb.append(tähed).append(System.lineSeparator());
        for (int y = 0; y < Mängulaud.SUURUS; y++) {
            sb.append(y);
            for (int x = 0; x < Mängulaud.SUURUS; x++) {
                sb.append(" ").append(this.getPositsioon(x, y));
            }
            sb.append(" ").append(y).append("    ").append(this.getLaevad().get(y)).append(System.lineSeparator());
        }
        sb.append(tähed).append(System.lineSeparator());
        return sb.toString();
    }

}
