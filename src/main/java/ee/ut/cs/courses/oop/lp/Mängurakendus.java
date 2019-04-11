package ee.ut.cs.courses.oop.lp;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Mängurakendus extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage pealava) {
        pealava.setHeight(480); // TODO: akna suurus parajaks
        pealava.setWidth(720);
        pealava.setResizable(false);
        pealava.setTitle("Laevade pommitamine");
        Label sissejuhatus = new Label("        Tere tulemast mängima laevade pommitamist!\n" +
                "Sinu ülesandeks on 60 pommiga põhja lasta kõik 10 laeva.\n" +
                "             Laevade vahel on vähemalt üks tühi ruut.");
        sissejuhatus.setFont(new Font("Futura", 24));
        Button startNupp = new Button("Alusta");
        //Button lõpp = new Button("Lõpp");
        //lõpp.setOnAction(e -> pealava.setScene(new Võidustseen()));
        startNupp.setDefaultButton(true);
        startNupp.setStyle("-fx-background-color: white;-fx-border-color: black; -fx-font-size: 3em; ");
        startNupp.setOnMouseEntered(e -> startNupp.setStyle("-fx-background-color: grey;-fx-border-color: black; -fx-font-size: 3em; "));
        startNupp.setOnMouseExited(e -> startNupp.setStyle("-fx-background-color: white;-fx-border-color: black; -fx-font-size: 3em; "));
        startNupp.setOnAction(e -> pealava.setScene(new Mängustseen()));
        VBox kast = new VBox(sissejuhatus, startNupp);
        kast.setAlignment(Pos.CENTER);
        kast.setSpacing(50);
        Scene stseen = new Scene(kast);
        pealava.setScene(stseen);
        pealava.show();
    }

}
