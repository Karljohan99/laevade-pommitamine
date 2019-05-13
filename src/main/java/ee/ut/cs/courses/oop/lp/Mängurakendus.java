package ee.ut.cs.courses.oop.lp;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

import static java.lang.Double.min;

public class Mängurakendus extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage pealava) {

        Label sissejuhatus = new Label("Tere tulemast mängima laevade pommitamist!\n" +
                " Sinu ülesandeks on 60 pommiga põhja lasta kõik 10 laeva. \n" +
                "Laevade vahel on vähemalt üks tühi ruut.");
        sissejuhatus.setTextAlignment(TextAlignment.CENTER);
        sissejuhatus.setFont(new Font("Garamond", 24));
        sissejuhatus.setTextFill(Color.DARKCYAN);
        sissejuhatus.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        Mängunupp startNupp = new Mängunupp("Alusta");
        startNupp.setFont(new Font("Garamond", 24));
        VBox paneel = new VBox(sissejuhatus, startNupp);
        paneel.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));
        paneel.setAlignment(Pos.CENTER);
        paneel.setSpacing(50);
        Scene stseen = new Scene(paneel);
        startNupp.setOnAction(e -> stseen.setRoot(new Mäng()));
        stseen.rootProperty().addListener((o, enne, pärast) -> suurenda(pealava));
        pealava.heightProperty().addListener((o, enne, pärast) -> suurenda(pealava));
        pealava.widthProperty().addListener((o, enne, pärast) -> suurenda(pealava));
        pealava.setMinHeight(480);
        pealava.setMinWidth(640);
        pealava.setScene(stseen);
        pealava.setTitle("Laevade pommitamine");
        pealava.show();
    }

    /**
     * Pealava suuruse muutmine
     *
     * @param pealava Antud pealava
     */
    private static void suurenda(Stage pealava) {
        double scale = min(pealava.getHeight() / pealava.getMinHeight(),
                pealava.getWidth() / pealava.getMinWidth());
        pealava.getScene().getRoot().getTransforms().setAll(new Scale(scale, scale,
                pealava.getWidth() / 2, pealava.getHeight() / 2));
    }

}
