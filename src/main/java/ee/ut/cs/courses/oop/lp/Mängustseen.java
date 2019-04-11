package ee.ut.cs.courses.oop.lp;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Mängustseen extends Scene {

    private final Mäng mäng = new Mäng();

    public Mängustseen() {
        super(new Pane());
        Pane juur = (Pane) this.getRoot();
        GridPane grid = new GridPane();
        Button[] nupud = new Button[100];
        GridPane laevade_grid = new GridPane();
        laevade_grid.setVgap(6);
        long hävitatudLaevadeArv = mäng.getMängulaud().getLaevad().stream()
                .filter(Mängulaev::onHävitatud)
                .count();
        Label sõnum = new Label();
        Label pommid = new Label("Pomme alles: " + mäng.getPommideArv());
        Label laevad = new Label("Laevu hävitatud: " + hävitatudLaevadeArv + "/10");
        sõnum.setFont(new Font("Futura", 20));
        pommid.setFont(new Font("Futura", 20));
        laevad.setFont(new Font("Futura", 20));


        int arv3 = 0;
        int arv2 = 0;
        int arv1 = 0;
        for (Mängulaev laev : mäng.getMängulaud().getLaevad()) {
            int suurus = laev.mängulaevaSuurus(laev);
            if (suurus == 4) {
                for (Mängupositsioon positsioon : laev.getPositsioonid()) {
                    Button nupp = new Button();
                    nupp.setPrefWidth(30);
                    nupp.setPrefHeight(30);
                    nupp.setDisable(true);
                    nupp.setStyle("-fx-background-color: white;-fx-border-color: black;");
                    laevade_grid.addRow(0, nupp);
                }
            }
        }
        for (Mängulaev laev : mäng.getMängulaud().getLaevad()) {
            int suurus = laev.mängulaevaSuurus(laev);
            if (suurus == 3) {
                for (Mängupositsioon positsioon : laev.getPositsioonid()) {
                    Button nupp = new Button();
                    nupp.setPrefWidth(30);
                    nupp.setPrefHeight(30);
                    nupp.setDisable(true);
                    nupp.setStyle("-fx-background-color: white;-fx-border-color: black;");
                    laevade_grid.addRow(1 + arv3, nupp);
                }
                arv3++;
            }
        }
        for (Mängulaev laev : mäng.getMängulaud().getLaevad()) {
            int suurus = laev.mängulaevaSuurus(laev);
            if (suurus == 2) {
                for (Mängupositsioon positsioon : laev.getPositsioonid()) {
                    Button nupp = new Button();
                    nupp.setPrefWidth(30);
                    nupp.setPrefHeight(30);
                    nupp.setDisable(true);
                    nupp.setStyle("-fx-background-color: white;-fx-border-color: black;");
                    laevade_grid.addRow(1 + arv3 + arv2, nupp);
                }
                arv2++;
            }
        }
        for (Mängulaev laev : mäng.getMängulaud().getLaevad()) {
            int suurus = laev.mängulaevaSuurus(laev);
            if (suurus == 1) {
                for (Mängupositsioon positsioon : laev.getPositsioonid()) {
                    Button nupp = new Button();
                    nupp.setPrefWidth(30);
                    nupp.setPrefHeight(30);
                    nupp.setDisable(true);
                    nupp.setStyle("-fx-background-color: white;-fx-border-color: black;");
                    laevade_grid.addRow(1 + arv3 + arv2 + arv1, nupp);
                }
                arv1++;
            }
        }

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
                                Stage stage = (Stage) juur.getScene().getWindow();
                                stage.setScene(new Võidustseen());
                                //nuppud_kinni(nupud);
                            }
                            if (mäng.onKaotatud()) {
                                Stage stage = (Stage) juur.getScene().getWindow();
                                stage.setScene(new Kaotusstseen());
                                //nuppud_kinni(nupud);
                            }
                        }
                    }
                });
                nupud[i * 10 + j].addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if (nupud[m * 10 + n].getStyle().strip().equals("-fx-background-color: white;-fx-border-color: black;")) {
                            nupud[m * 10 + n].setStyle("-fx-background-color: grey;-fx-border-color: black; ");
                        }
                    }
                });

                nupud[i * 10 + j].addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if (nupud[m * 10 + n].getStyle().strip().equals("-fx-background-color: grey;-fx-border-color: black;")) {
                            nupud[m * 10 + n].setStyle("-fx-background-color: white;-fx-border-color: black; ");
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
        laevade_grid.setLayoutX(480);
        laevade_grid.setLayoutY(30);
        juur.getChildren().addAll(top_kast, grid, sõnum, laevade_grid);
    }

    public void nuppud_kinni(Button[] nuppud) {
        for (Button nupp : nuppud) {
            nupp.setDisable(true);
        }
    }

}
