package ee.ut.cs.courses.oop.lp;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Mängulaev {

    private final Set<Mängupositsioon> positsioonid = new HashSet<>();

    /**
     * Genereeritakse antud positsiooni juurde ülejäänud positsioonid nii, et laev vastaks antud suurusele
     *
     * @param x      Mängulaua x koordinaat
     * @param y      Mängulaua y koordinaat
     * @param suurus Mängulaeva suurus
     */
    public Mängulaev(int x, int y, int suurus) {
        if (ThreadLocalRandom.current().nextBoolean()) {
            for (int i = 0; i < suurus; i++, x++) {
                this.positsioonid.add(new Mängupositsioon(x, y)); // horisontaalne
            }
        } else {
            for (int i = 0; i < suurus; i++, y++) {
                this.positsioonid.add(new Mängupositsioon(x, y)); // vertikaalne
            }
        }
    }

    /**
     * Antud positsioonil hävitamine
     *
     * @param positsioon Positsioon, mis hävitatakse
     */
    public void hävita(Mängupositsioon positsioon) {
        this.positsioonid.stream().filter(positsioon::equals).forEach(Mängupositsioon::hävita);
    }

    /**
     * Kontrollimine, kas antud laev kattub selle laevaga
     *
     * @param laev Laev, mille kattumist kontrollitakse
     * @return true, kui antud laev kattub selle laevaga, false, kui antud laev ei kattu selle laevaga
     */
    public boolean kattub(Mängulaev laev) {
        return this.positsioonid.stream().anyMatch(laev::kattub);
    }

    /**
     * Kontrollimine, kas antud positsoon kattub selle laevaga
     *
     * @param positsioon Positsioon, mille kattumist kontrollitakse
     * @return true, kui positsioon kattub selle laevaga, false, kui positsioon ei kattu selle laevaga
     */
    public boolean kattub(Mängupositsioon positsioon) {
        return this.positsioonid.contains(positsioon);
    }

    /**
     * Kontrollimine, kas laev on hävitatud
     *
     * @return true, kui laev on hävitatud, false, kui laev ei ole hävitatud
     */
    public boolean onHävitatud() {
        return this.positsioonid.stream().allMatch(Mängupositsioon::onHävitatud);
    }

    /**
     * Kontrollimine, kas laev on hävitatud antud positsioonil
     *
     * @param positsioon Positsioon, kus kontrollitakse, kas laev on hävitatud
     * @return true, kui laev on hävitatud, false, kui laev ei ole hävitatud
     */
    public boolean onHävitatud(Mängupositsioon positsioon) {
        return this.positsioonid.stream().filter(Mängupositsioon::onHävitatud).anyMatch(positsioon::equals);
    }

    /**
     * Kontrollimine, kas antud laev on lähedal sellele laevale
     *
     * @param laev Laev, mille lähedust kontrollitakse
     * @return true, kui see laev on lähedal teisele laevale, false, kui see laev ei ole lähedal teisele laevale
     */
    public boolean onLähedal(Mängulaev laev) {
        return this.positsioonid.stream().anyMatch(laev::onLähedal);
    }

    /**
     * Kontrollimine, kas antud positsoon on lähedal sellele laevale
     *
     * @param positsioon Positsioon, mille lähedust kontrollitakse
     * @return true, kui positsioon on lähedal, false, kui positsioon ei ole lähedal
     */
    public boolean onLähedal(Mängupositsioon positsioon) {
        return this.positsioonid.stream().anyMatch(positsioon::onLähedal);
    }

    /**
     * Kontrollimine, kas see laev on mängulaual
     *
     * @return true, kui see laev on mängulaual, false, kui see laev ei ole mängulaual
     */
    public boolean onMängulaual() {
        return this.positsioonid.stream().allMatch(Mängupositsioon::onMängulaual);
    }

    public int suurus() {
        return this.positsioonid.size();
    }

}
