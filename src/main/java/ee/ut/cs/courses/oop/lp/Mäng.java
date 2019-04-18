package ee.ut.cs.courses.oop.lp;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.awt.*;
import java.util.Scanner;

public class Mäng extends Pane {

    private final Mängulaud mängulaud = new Mängulaud();

    public Mäng() {


        VBox laevadePaneel = new VBox();
        laevadePaneel.setSpacing(6);
        Label pommid = new Label("Pomme alles: " + this.pommideArv());
        pommid.setFont(new Font("Futura", 20));

        for (Mängulaev laev : this.getMängulaud().getLaevad()) {
            for (Mängupositsioon nupp : laev.getManagedChildren()) {
                nupp.setPrefWidth(30);
                nupp.setPrefHeight(30);
            }
            laevadePaneel.getChildren().add(laev);
        }


        this.getMängulaud().getPositsioonid().forEach(nupp -> {
            nupp.setPrefWidth(36);
            nupp.setPrefHeight(36);

            nupp.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
                // this.getScene().setRoot(new Mängulõpp()); // testimiseks
                if (!nupp.onHävitatud()) {
                    nupp.hävita();
                    pommid.setText("Pomme alles: " + this.pommideArv());
                    if (this.onLõppenud()) {
                        this.getScene().setRoot(new Mängulõpp());
                    }
                }
            });

        });


        this.getMängulaud().setLayoutX(60);
        this.getMängulaud().setLayoutY(30);
        laevadePaneel.setLayoutX(480);
        laevadePaneel.setLayoutY(30);
        this.getChildren().addAll(pommid, this.getMängulaud(), laevadePaneel);
    }

    public Mängulaud getMängulaud() {
        return this.mängulaud;
    }

    public int hävitatudLaevadeArv() {
        return (int) this.getMängulaud().getLaevad().stream()
                .filter(Mängulaev::onHävitatud)
                .count();
    }

    public static void main(String[] args) {
        if (!GraphicsEnvironment.isHeadless()) {
            Mängurakendus.main(args); // Kui on võimalik, kasutame graafilist kasutajaliidest
            return;
        } else {
            Platform.startup(() -> {
            });
        }
        System.out.println("Tere tulemast mängima laevade pommitamist!");
        System.out.println("Sinu ülesandeks on 60 pommiga põhja lasta kõik 10 laeva.");
        System.out.println("Laevade vahel on vähemalt üks tühi ruut.");
        try (Scanner käsurida = new Scanner(System.in)) {
            while (true) {
                System.out.println();
                System.out.println("Alustame uut mängu? (Jah/ei)");
                if (käsurida.nextLine().toLowerCase().startsWith("e")) {
                    break;
                }
                Mäng mäng = new Mäng();
                while (!mäng.onLõppenud()) {
                    System.out.println(mäng);
                    while (true) {
                        System.out.println("Mida soovid pommitada? (A-J ja 0-9)");
                        try {
                            String vastus = käsurida.nextLine().toUpperCase();
                            int x = vastus.codePointAt(0) - 65; // tähed algavad kohalt 65
                            int y = vastus.codePointAt(1) - 48; // numbrid algavad kohalt 48
                            if (!Mängupositsioon.onMängulaual(x, y)) {
                                System.out.println("Sisestatud positsioon ei asu mängulaual!");
                                continue;
                            }
                            Mängupositsioon positsioon = mäng.getMängulaud().getManagedChildren().get(x + y * Mängulaud.SUURUS);
                            if (positsioon.onHävitatud()) {
                                System.out.println("Seda positsiooni oled juba pommitanud!");
                                continue;
                            } else {
                                positsioon.hävita();
                            }
                            System.out.println();
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Sisesta mängulaua koordinaat, näiteks C4");
                            continue;
                        }
                        break;
                    }
                }
                System.out.println(mäng);
                if (mäng.onVõidetud()) {
                    System.out.println("Sa võitsid!");
                } else {
                    System.out.println("Sa kaotasid!");
                }
            }
        }
    }

    /**
     * Kontrollimine, kas mäng on kaotatud
     *
     * @return true, kui pommid on otsas, false, kui pommid ei ole otsas
     */
    public boolean onKaotatud() {
        return this.pommideArv() <= 0;
    }

    /**
     * Kontrollimine, kas mäng on lõppenud
     *
     * @return true, kui mäng on kaotatud või võidetud, false, kui mäng käib
     */
    public boolean onLõppenud() {
        return this.onKaotatud() || this.onVõidetud();
    }

    /**
     * Kontrollimine, kas mäng on võidetud
     *
     * @return true, kui kõik laevad on hävitatud, false, kui mõni laev on veel alles
     */
    public boolean onVõidetud() {
        return this.getMängulaud().getLaevad().stream().allMatch(Mängulaev::onHävitatud) && !this.onKaotatud();
    }

    public long pommideArv() {
        return 60 - this.mängulaud.getPositsioonid().stream().filter(Mängupositsioon::onHävitatud).count();
    }

    public int skoor() {
        return 0; // TODO: matemaatika
    }

    @Override
    public String toString() {
        return this.getMängulaud().toString() + System.lineSeparator()
                + "Pomme alles: " + this.pommideArv() + System.lineSeparator();
    }

    class Mängulõpp extends VBox {
        public Mängulõpp() {
            HBox nimeväli = new HBox();
            this.setSpacing(50);
            this.setPrefWidth(720);
            this.setPrefHeight(480);
            this.setAlignment(Pos.CENTER);
            nimeväli.setAlignment(Pos.CENTER);
            Label võit = new Label("Sa võitsid");
            if (Mäng.this.onKaotatud()) {
                võit.setText("Sa kaotasid");
            }
            võit.setFont(new Font("Futura", 48));
            javafx.scene.control.Button uuesti = new javafx.scene.control.Button("Mängi uuesti!");
            uuesti.setStyle("-fx-background-color: white;-fx-border-color: black; -fx-font-size: 2em; ");
            javafx.scene.control.TextField tekst = new TextField();
            Label nimi = new Label("Sisesta kasutajanimi: ");
            nimi.setFont(new Font("Futura", 20));
            uuesti.setOnMouseEntered(e -> uuesti.setStyle("-fx-background-color: grey;-fx-border-color: black; -fx-font-size: 2em; "));
            uuesti.setOnMouseExited(e -> uuesti.setStyle("-fx-background-color: white;-fx-border-color: black; -fx-font-size: 2em; "));
            uuesti.setOnAction(e -> this.getScene().setRoot(new Mäng()));
            nimeväli.getChildren().addAll(nimi, tekst);
            Edetabel edetabel = new Edetabel();
            this.getChildren().addAll(võit, edetabel, uuesti);
        }
    }

}
