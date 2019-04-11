package ee.ut.cs.courses.oop.lp;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Mängurakendus extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage pealava) {
        pealava.setHeight(480); // TODO: akna suurus parajaks
        pealava.setWidth(600);
        pealava.setResizable(false);
        pealava.setTitle("Laevade pommitamine");
        Label sissejuhatus = new Label("Tere tulemast mängima laevade pommitamist!\n" +
                "Sinu ülesandeks on 60 pommiga põhja lasta kõik 10 laeva.\n" +
                "Laevade vahel on vähemalt üks tühi ruut.");
        Button startNupp = new Button("Alusta");
        startNupp.setDefaultButton(true);
        startNupp.setOnAction(e -> pealava.setScene(new Mängustseen()));
        VBox kast = new VBox(sissejuhatus, startNupp);
        kast.setAlignment(Pos.CENTER);
        kast.setSpacing(50);
        pealava.setScene(new Scene(kast));
        pealava.show();
    }

}
