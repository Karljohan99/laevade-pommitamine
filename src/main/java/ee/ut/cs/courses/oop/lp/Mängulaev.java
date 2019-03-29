package ee.ut.cs.courses.oop.lp;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Mängulaev {

    private final Set<Mängupositsioon> positsioonid = new HashSet<>();

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

    public void hävita(Mängupositsioon positsioon) {
        this.positsioonid.stream().filter(positsioon::equals).forEach(Mängupositsioon::hävita);
    }

    public boolean kattub(Mängulaev laev) {
        return this.positsioonid.stream().anyMatch(laev::kattub);
    }

    public boolean kattub(Mängupositsioon positsioon) {
        return this.positsioonid.contains(positsioon);
    }

    public boolean onHävitatud() {
        return this.positsioonid.stream().allMatch(Mängupositsioon::onHävitatud);
    }

    public boolean onHävitatud(Mängupositsioon positsioon) {
        return this.positsioonid.stream().filter(Mängupositsioon::onHävitatud).anyMatch(positsioon::equals);
    }

    public boolean onLähedal(Mängulaev laev) {
        return this.positsioonid.stream().anyMatch(laev::onLähedal);
    }

    public boolean onLähedal(Mängupositsioon positsioon) {
        return this.positsioonid.stream().anyMatch(positsioon::onLähedal);
    }

    public boolean onMängulaual() {
        return this.positsioonid.stream().allMatch(Mängupositsioon::onMängulaual);
    }

    public int suurus() {
        return this.positsioonid.size();
    }

}
