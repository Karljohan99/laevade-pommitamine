package ee.ut.cs.courses.oop.lp;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.List;
import java.util.Scanner;

import static ee.ut.cs.courses.oop.lp.Mängunupp.ALGNE_RAAM;
import static ee.ut.cs.courses.oop.lp.Mängunupp.ALGNE_TAUST;

public class Mäng extends GridPane {

    private final Mängulaud mängulaud = new Mängulaud();

    public Mäng() {
        VBox laevadePaneel = new VBox();
        laevadePaneel.getChildren().addAll(this.getMängulaud().getLaevad());
        laevadePaneel.setAlignment(Pos.CENTER);
        laevadePaneel.setSpacing(6);
        Label pommideInfo = new Label("Pomme alles: " + this.pommideArv());
        pommideInfo.setFont(new Font("Futura", 20));
        this.getMängulaud().getManagedChildren().forEach(nupp -> {
            nupp.setMinHeight(36);
            nupp.setMinWidth(36);
            nupp.setOnAction(e -> {
                //this.getScene().setRoot(new Mängulõpp()); // testimiseks
                if (!nupp.onHävitatud()) {
                    nupp.hävita();
                    pommideInfo.setText("Pomme alles: " + this.pommideArv());
                    if (this.onLõppenud()) {
                        this.getScene().setRoot(new Mängulõpp());
                    }
                }
            });
        });
        this.add(pommideInfo, 0, 0);
        this.add(this.getMängulaud(), 0, 1);
        this.add(laevadePaneel, 1, 1);
        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
    }

    public Mängulaud getMängulaud() {
        return this.mängulaud;
    }

    public static void main(String[] args) {
        if (!List.of(args).contains("tekst")) {
            Mängurakendus.main(args);
            return;
        }

        Platform.startup(() -> {
        });

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
                            Mängupositsioon positsioon = mäng.getMängulaud().getPositsioon(x, y);
                            if (positsioon.onHävitatud()) {
                                System.out.println("Seda positsiooni oled juba pommitanud!");
                                continue;
                            } else {
                                positsioon.hävita();
                            }
                            System.out.println();
                        } catch (IllegalArgumentException erind) {
                            System.out.println(erind.getMessage());
                            continue;
                        } catch (IndexOutOfBoundsException erind) {
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
        return 60 - this.mängulaud.getManagedChildren().stream().filter(Mängupositsioon::onHävitatud).count();
    }

    public int skoor() {
        return (int) Math.round(Math.pow(2.5 * pommideArv(), 3));
    }


    @Override
    public String toString() {
        return this.getMängulaud().toString() + System.lineSeparator()
                + "Pomme alles: " + this.pommideArv() + System.lineSeparator();
    }

    class Mängulõpp extends VBox {

        public Mängulõpp() {
            this.setSpacing(8);
            this.setAlignment(Pos.CENTER);
            Label teade = new Label();
            teade.setFont(new Font("Futura", 36));
            Mängunupp startNupp = new Mängunupp("Mängi uuesti");
            startNupp.setDefaultButton(true);
            startNupp.setOnAction(e -> this.getScene().setRoot(new Mäng()));
            if (Mäng.this.onKaotatud()) {
                teade.setText("Sa kaotasid!");
                this.getChildren().addAll(teade, startNupp);
            } else {
                teade.setText("Sa võitsid!");
                Label skoor = new Label("Sinu skoor: " + Mäng.this.skoor());
                skoor.setFont(new Font("Futura", 24));
                Edetabel edetabel = new Edetabel();
                Mängunupp nimeNupp = new Mängunupp("OK");
                TextField nimeVäli = new TextField();
                nimeVäli.setBackground(ALGNE_TAUST);
                nimeVäli.setBorder(ALGNE_RAAM);
                Label nimi = new Label("Pane enda nimi edetabelisse: ");
                nimi.setFont(new Font("Futura", 20));
                HBox sisestusPaneel = new HBox();
                sisestusPaneel.setSpacing(10);
                sisestusPaneel.setAlignment(Pos.CENTER);
                sisestusPaneel.getChildren().addAll(nimi, nimeVäli, nimeNupp);
                this.getChildren().addAll(teade, skoor, edetabel, sisestusPaneel, startNupp);
            }

        }

    }

}
