package ee.ut.cs.courses.oop.lp;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class Mängunupp extends Button {

    protected static final Border ALGNE_RAAM = new Border(new BorderStroke(Color.BLACK,
            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
    protected static final Background ALGNE_TAUST = new Background(new BackgroundFill(Color.WHITE,
            CornerRadii.EMPTY, Insets.EMPTY));
    protected static final Background FOOKUSES_TAUST = new Background(new BackgroundFill(Color.LIGHTGRAY,
            CornerRadii.EMPTY, Insets.EMPTY));

    public Mängunupp(String tekst) {
        super(tekst);
        this.setBackground(ALGNE_TAUST);
        this.setBorder(ALGNE_RAAM);
        this.setDisable(tekst == null);
        this.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> this.requestFocus());
        this.focusedProperty().addListener((parameeter, enne, pärast) -> {
            if (this.isFocusTraversable()) {
                if (pärast) {
                    this.setDefaultButton(true);
                    this.setBackground(FOOKUSES_TAUST);
                } else {
                    this.setBackground(ALGNE_TAUST);
                }
            }
        });
    }

}
