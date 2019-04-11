package ee.ut.cs.courses.oop.lp;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Kaotusstseen extends Scene {

    public Kaotusstseen() {
        super(new Pane());
        Pane juur = (Pane) this.getRoot();
        VBox kast = new VBox();
        kast.setSpacing(50);
        kast.setPrefWidth(720);
        kast.setPrefHeight(480);
        kast.setAlignment(Pos.CENTER);
        Label kaotus = new Label("Sa kaotasid!");
        kaotus.setFont(new Font("Futura", 48));
        Button uuesti = new Button("Mängi uuesti!");
        uuesti.setStyle("-fx-background-color: white;-fx-border-color: black; -fx-font-size: 2em; ");
        Stage stage = (Stage) juur.getScene().getWindow();
        uuesti.setOnAction(e -> stage.setScene(new Mängustseen()));//TODO: nupp parandada
        uuesti.setOnMouseEntered(e -> uuesti.setStyle("-fx-background-color: grey;-fx-border-color: black; -fx-font-size: 2em; "));
        uuesti.setOnMouseExited(e -> uuesti.setStyle("-fx-background-color: white;-fx-border-color: black; -fx-font-size: 2em; "));
        kast.getChildren().addAll(kaotus, uuesti);
        juur.getChildren().add(kast);

    }
}
