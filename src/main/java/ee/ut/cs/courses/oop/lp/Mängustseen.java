package ee.ut.cs.courses.oop.lp;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

public class Mängustseen extends Scene {

    private final Mäng mäng = new Mäng();

    public Mängustseen() {
        super(new GridPane());
        GridPane juur = (GridPane) this.getRoot();
        // TODO: graafiline mängulaud
    }

}
