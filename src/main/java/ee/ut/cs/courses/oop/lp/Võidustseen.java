package ee.ut.cs.courses.oop.lp;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Võidustseen extends Scene {

    public Võidustseen() {
        super(new Pane());
        Pane juur = (Pane) this.getRoot();
        VBox kast = new VBox();
        HBox nimeväli = new HBox();
        kast.setSpacing(50);
        kast.setPrefWidth(720);
        kast.setPrefHeight(480);
        kast.setAlignment(Pos.CENTER);
        nimeväli.setAlignment(Pos.CENTER);
        Label võit = new Label("Sa võitsid");
        võit.setFont(new Font("Futura", 48));
        Button uuesti = new Button("Mängi uuesti!");
        uuesti.setStyle("-fx-background-color: white;-fx-border-color: black; -fx-font-size: 2em; ");
        TextField tekst = new TextField();
        Label nimi = new Label("Sisesta kasutajanimi: ");
        nimi.setFont(new Font("Futura", 20));
        Stage stage = (Stage) juur.getScene().getWindow();
        uuesti.setOnAction(e -> stage.setScene(new Mängustseen()));//TODO: nupp parandada
        uuesti.setOnMouseEntered(e -> uuesti.setStyle("-fx-background-color: grey;-fx-border-color: black; -fx-font-size: 2em; "));
        uuesti.setOnMouseExited(e -> uuesti.setStyle("-fx-background-color: white;-fx-border-color: black; -fx-font-size: 2em; "));
        nimeväli.getChildren().addAll(nimi, tekst);
        kast.getChildren().addAll(võit, nimeväli, uuesti);
        juur.getChildren().add(kast);
    }

}
