package ee.ut.cs.courses.oop.lp;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Mängunupp extends Button {

    protected static final Border ALGNE_RAAM = new Border(new BorderStroke(Color.BLACK,
            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
    protected static final Background ALGNE_TAUST = new Background(new BackgroundFill(Color.WHITE,
            CornerRadii.EMPTY, Insets.EMPTY));
    protected static final Background FOOKUSES_TAUST = new Background(new BackgroundFill(Color.LIGHTCYAN,
            CornerRadii.EMPTY, Insets.EMPTY));
    protected static final Background VAJUTATUD_TAUST = new Background(new BackgroundFill(Color.DARKCYAN,
            CornerRadii.EMPTY, Insets.EMPTY));

    /**
     * Mängunupu konstruktor
     *
     * @param tekst Nupul olev tekst
     */
    public Mängunupp(String tekst) {
        super(tekst);
        this.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> this.requestFocus());
        this.addEventHandler(MouseEvent.MOUSE_EXITED, e -> this.getParent().requestFocus());
        this.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            if (this.getBackground().equals(FOOKUSES_TAUST)) {
                this.setBackground(VAJUTATUD_TAUST);
            }

        });
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
        this.setBackground(ALGNE_TAUST);
        this.setBorder(ALGNE_RAAM);
        this.setFocusTraversable(tekst != null);
        this.setMinHeight(30);
        this.setMinWidth(30);
        this.setFont(new Font("Garamond", 12));
    }

}
