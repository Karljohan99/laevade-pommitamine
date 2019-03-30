package ee.ut.cs.courses.oop.lp;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Mängulaev {

    private final Set<Mängupositsioon> positsioonid = new HashSet<>();

    /**
     * Genereeritakse antud positsioon juurde ülejäänud positsioonid nii, et laev vastaks antud suurusele
     *
     * @param x      Mängulaua x koordinaat
     * @param y      Mängulaua y koordinaat
     * @param suurus Mängulaeva suurus
     */
    public Mängulaev(int x, int y, int suurus) {
        if (ThreadLocalRandom.current().nextBoolean()) {
            for (int i = 0; i < suurus; i++, x++) {
                this.positsioonid.add(new Mängupositsioon(x, y));
            }
        } else {
            for (int i = 0; i < suurus; i++, y++) {
                this.positsioonid.add(new Mängupositsioon(x, y));
            }
        }
    }

    /**
     * Antud positsioonil hävitamine
     * @param positsioon Positsioon, mis hävitatakse
     */
    public void hävita(Mängupositsioon positsioon) {
        this.positsioonid.stream().filter(positsioon::equals).forEach(Mängupositsioon::hävita);
    }

    /**
     * Kontrollimine, kas antud laev kattub juba olemasoleva laevaga
     * @param laev Laev, mille kattumist kontrollitakse
     * @return true, kui antud laev kattub mingi teise laevaga, false, kui antud laev ei kattu mingi teise laevaga
     */
    public boolean kattub(Mängulaev laev) {
        return this.positsioonid.stream().anyMatch(laev::kattub);
    }

    /**
     * Kontrollimine, kas antud positsoon kattub juba olemasoleva laeva positsiooniga
     * @param positsioon Positsioon, mille kattumist kontrollitakse
     * @return true, kui positsioon kattub varasematega, false, kui positsioon ei kattu varasematega
     */
    public boolean kattub(Mängupositsioon positsioon) {
        return this.positsioonid.contains(positsioon);
    }

    /**
     * Kontrollimine, kas laev on hävitatud
     * @return true, kui laev on hävitatud, false, kui laev ei ole hävitatud
     */
    public boolean onHävitatud() {
        return this.positsioonid.stream().allMatch(Mängupositsioon::onHävitatud);
    }

    /**
     * Kontrollimine, kas laev on hävitatud antud positsioonil
     * @param positsioon Positsioon, kus kontrollitakse, kas laev on hävitatud
     * @return true, kui laev on hävitatud, false, kui laev ei ole hävitatud
     */
    public boolean onHävitatud(Mängupositsioon positsioon) {
        return this.positsioonid.stream().filter(Mängupositsioon::onHävitatud).anyMatch(positsioon::equals);
    }

    /**
     * Kontrollimine, kas antud laev on lähedal mingile teisele laevale
     * @param laev Laev, mille lähedust teistele laevadele kontrollitakse
     * @return true, kui antud laev on lähedal mingi teise laevaga, false, kui antud laev ei ole lähedal mingi teise laevaga
     */
    public boolean onLähedal(Mängulaev laev) {
        return this.positsioonid.stream().anyMatch(laev::onLähedal);
    }

    /**
     * Kontrollimine, kas antud positsoon on lähedal juba olemasoleva laeva positsiooniga
     * @param positsioon Positsioon, mille lähedust kontrollitakse
     * @return true, kui positsioon on lähedal varasematele, false, kui positsioon ei ole lähedal varasematele
     */
    public boolean onLähedal(Mängupositsioon positsioon) {
        return this.positsioonid.stream().anyMatch(positsioon::onLähedal);
    }

    /**
     * Kontrollimine, kas kõik laevad on mängulaual
     * @return true, kui kõik laevad on mängulaual, false, kui kõik laevad ei ole mängulaual
     */
    public boolean onMängulaual() {
        return this.positsioonid.stream().allMatch(Mängupositsioon::onMängulaual);
    }

    public int suurus() {
        return this.positsioonid.size();
    }

}
