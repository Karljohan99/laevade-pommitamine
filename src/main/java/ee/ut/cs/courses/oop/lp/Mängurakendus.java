package ee.ut.cs.courses.oop.lp;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
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
        Label sissejuhatus = new Label("Tere tulemast mängima laevade pommitamist!\n" +
                "Sinu ülesandeks on 60 pommiga põhja lasta kõik 10 laeva.\n" +
                "Laevade vahel on vähemalt üks tühi ruut.");
        sissejuhatus.setTextAlignment(TextAlignment.CENTER);
        sissejuhatus.setFont(new Font("Futura", 24));
        Button startNupp = new Button("Alusta");
        VBox sissejuhatusePaneel = new VBox(sissejuhatus, startNupp);
        sissejuhatusePaneel.setAlignment(Pos.CENTER);
        sissejuhatusePaneel.setSpacing(50);
        Scene stseen = new Scene(sissejuhatusePaneel);
        startNupp.setDefaultButton(true);
        startNupp.setStyle("-fx-background-color: white;-fx-border-color: black; -fx-font-size: 3em; ");
        startNupp.setOnMouseEntered(e -> startNupp.setStyle("-fx-background-color: grey;-fx-border-color: black; -fx-font-size: 3em; "));
        startNupp.setOnMouseExited(e -> startNupp.setStyle("-fx-background-color: white;-fx-border-color: black; -fx-font-size: 3em; "));
        startNupp.setOnAction(e -> stseen.setRoot(new Mäng()));
        pealava.setScene(stseen);
        pealava.show();
    }

}
