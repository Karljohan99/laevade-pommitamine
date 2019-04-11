package ee.ut.cs.courses.oop.lp;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class Mängustseen extends Scene {

    private final Mäng mäng = new Mäng();

    public Mängustseen() {
        super(new Pane());
        Pane juur = (Pane) this.getRoot();
        GridPane grid = new GridPane();
        Button[] nupud = new Button[100];
        long hävitatudLaevadeArv = mäng.getMängulaud().getLaevad().stream()
                .filter(Mängulaev::onHävitatud)
                .count();
        Label sõnum = new Label();
        Label pommid = new Label("Pomme alles: " + mäng.getPommideArv());
        Label laevad = new Label("Laevu hävitatud: " + hävitatudLaevadeArv + "/10");
        Label võit = new Label();
        Label kaotus = new Label();
        Button uuesti = new Button("Mängi uuesti!");
        uuesti.setVisible(false);
        sõnum.setFont(new Font("Arial", 20));
        pommid.setFont(new Font("Arial", 20));
        laevad.setFont(new Font("Arial", 20));
        võit.setFont(new Font("Arial", 48));
        kaotus.setFont(new Font("Arial", 48));
        uuesti.setStyle("-fx-font-size: 2em; ");
        for (int i = 0; i < 10; i++) {
            final int m = i;
            for (int j = 0; j < 10; j++) {
                final int n = j;
                String tekst = (char) (i + 65) + "" + j;
                nupud[i * 10 + j] = new Button(tekst);
                nupud[i * 10 + j].setStyle("-fx-background-color: white;-fx-border-color: black;");
                nupud[i * 10 + j].setPrefWidth(36);
                nupud[i * 10 + j].setPrefHeight(36);
                nupud[i * 10 + j].addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent me) {
                        Mängupositsioon positsioon = new Mängupositsioon(m, n);
                        if (mäng.getMängulaud().onHävitatud(positsioon)) {
                            sõnum.setText("Seda positsiooni oled juba pommitanud!");
                        } else {
                            mäng.pommita(positsioon);
                            pommid.setText("Pomme alles: " + mäng.getPommideArv());
                            long hävitatudLaevadeArv = mäng.getMängulaud().getLaevad().stream()
                                    .filter(Mängulaev::onHävitatud)
                                    .count();
                            laevad.setText("Laevu hävitatud: " + hävitatudLaevadeArv + "/10");
                            if (mäng.getMängulaud().getLaevad().stream().anyMatch(laev -> laev.kattub(positsioon))) {
                                nupud[10 * m + n].setStyle("-fx-background-color: red; -fx-border-color: black;");
                                if (mäng.getMängulaud().getLaevad().stream().filter(Mängulaev::onHävitatud).count() > hävitatudLaevadeArv) {
                                    sõnum.setText("Pihtas-põhjas!");
                                } else {
                                    sõnum.setText("Pihtas!");
                                }
                            } else {
                                nupud[10 * m + n].setStyle("-fx-background-color: blue; -fx-border-color: black;");
                                sõnum.setText("Möödas!");
                            }
                            if (mäng.onVõidetud()) {
                                võit.setText("Sa võitsid!");
                                uuesti.setVisible(true);
                                nuppud_kinni(nupud);
                            }
                            if (mäng.onKaotatud()) {
                                kaotus.setText("Sa kaotasid!");
                                uuesti.setVisible(true);
                                nuppud_kinni(nupud);
                            }
                        }
                    }
                });
                grid.add(nupud[i * 10 + j], i, j, 1, 1);

            }
        }


        HBox top_kast = new HBox(20);
        top_kast.getChildren().addAll(pommid, laevad);
        top_kast.setLayoutX(60);
        grid.setLayoutX(60);
        grid.setLayoutY(30);
        sõnum.setLayoutX(60);
        sõnum.setLayoutY(400);
        võit.setLayoutX(130);
        võit.setLayoutY(120);
        kaotus.setLayoutX(110);
        kaotus.setLayoutY(120);
        uuesti.setLayoutX(160);
        uuesti.setLayoutY(200);
        juur.getChildren().addAll(top_kast, grid, sõnum, võit, kaotus, uuesti);
    }

    public void nuppud_kinni(Button[] nuppud) {
        for (Button nupp : nuppud) {
            nupp.setDisable(true);
        }
    }

}
