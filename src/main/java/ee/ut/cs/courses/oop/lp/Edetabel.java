package ee.ut.cs.courses.oop.lp;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;

import static ee.ut.cs.courses.oop.lp.Mängunupp.ALGNE_TAUST;
import static java.lang.System.lineSeparator;
import static java.util.Comparator.naturalOrder;
import static java.util.stream.Collectors.joining;

public class Edetabel extends TableView<Mängija> {

    private static final String ANDMEFAILI_NIMI = "edetabel.bin";

    public Edetabel() {
        this.setBackground(ALGNE_TAUST);
        this.setColumnResizePolicy(CONSTRAINED_RESIZE_POLICY);
        this.setMaxSize(300, 200);
        this.setPlaceholder(new Label());
        TableColumn pealkiri = new TableColumn("Edetabel");
        pealkiri.setReorderable(false);
        this.getColumns().addAll(pealkiri);
        TableColumn skooriVeerg = new TableColumn("Skoor");
        skooriVeerg.setReorderable(false);
        skooriVeerg.setSortable(false);
        skooriVeerg.setCellValueFactory(new PropertyValueFactory<>("skoor"));
        TableColumn nimeVeerg = new TableColumn("Nimi");
        nimeVeerg.setReorderable(false);
        nimeVeerg.setSortable(false);
        nimeVeerg.setCellValueFactory(new PropertyValueFactory<>("nimi"));
        pealkiri.getColumns().addAll(skooriVeerg, nimeVeerg);
        try (DataInputStream dis = new DataInputStream(new FileInputStream(ANDMEFAILI_NIMI))) {
            for (int mängijateArv = dis.readInt(); mängijateArv > 0; mängijateArv--) {
                this.getItems().add(new Mängija(dis.readUTF(), dis.readInt()));
            }
        } catch (IOException e) {
            // Eirame viga
        }
    }

    public void lisa(Mängija mängija) {
        int indeks = this.getItems().indexOf(mängija);
        if (indeks != -1) {
            Mängija vana = this.getItems().get(indeks);
            if (mängija.compareTo(vana) > 0) {
                vana.setNimi(mängija.getNimi());
                mängija = vana;
            }
            this.getItems().set(indeks, mängija);
        } else {
            this.getItems().add(mängija);
        }
        this.getItems().sort(naturalOrder());
        indeks = this.getItems().indexOf(mängija);
        this.getSelectionModel().clearAndSelect(indeks);
        this.scrollTo(indeks);
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(ANDMEFAILI_NIMI))) {
            dos.writeInt(this.getItems().size());
            for (var m : this.getItems()) {
                dos.writeUTF(m.getNimi());
                dos.writeInt(m.getSkoor());
            }
        } catch (IOException e) {
            // Eirame viga
        }
    }

    @Override
    public String toString() {
        return this.getItems().stream().map(Mängija::toString).collect(joining(lineSeparator())) + lineSeparator();
    }

}
