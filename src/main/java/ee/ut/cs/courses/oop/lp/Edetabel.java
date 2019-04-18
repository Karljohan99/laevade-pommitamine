package ee.ut.cs.courses.oop.lp;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Map;
import java.util.TreeMap;

public class Edetabel extends VBox {

    private Map<Integer, String> skoorid = new TreeMap<>();

    public Edetabel() {
        for (int i = 0; i < 10; i++) {
            this.skoorid.put(100000 - 100 * i, "Test" + i);
        }
        this.skoorid.forEach((s, n) -> {
            Label nimi = new Label(n);
            Label skoor = new Label(s.toString());
            this.getChildren().add(new HBox(nimi, skoor));
        });
    }

}
