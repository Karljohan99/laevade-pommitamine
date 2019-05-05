package ee.ut.cs.courses.oop.lp;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.Map;
import java.util.TreeMap;

public class Edetabel extends VBox {


    private Map<Integer, String> skoorid = new TreeMap<>();

    public Edetabel() {
        this.setMaxWidth(360);
        Label tekst = new Label("Edetabeli TOP10:");
        tekst.setFont(new Font("Futura", 18));
        HBox tekstikast = new HBox(tekst);
        tekstikast.setAlignment(Pos.CENTER);
        this.getChildren().add(tekstikast);
        for (int i = 0; i < 10; i++) {
            this.skoorid.put(100000 - 100 * i, "Test " + i);
        }
        this.skoorid.forEach((s, n) -> {
            Label nimi = new Label(n);
            nimi.setFont(new Font("Futura", 14));
            Label skoor = new Label(s.toString());
            skoor.setFont(new Font("Futura", 14));
            HBox kast = new HBox(nimi, skoor);
            kast.setAlignment(Pos.CENTER);
            this.getChildren().add(kast);
        });
    }

}
