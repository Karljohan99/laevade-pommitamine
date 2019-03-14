package ee.ut.cs.courses.oop.lp;

public class Mängulaud {

    @Override
    public String toString() {
        String tähed = "  A B C D E F G H I J";
        StringBuilder sb = new StringBuilder();
        sb.append(tähed).append(System.lineSeparator());
        for (int y = 0; y < 10; y++) {
            sb.append(y);
            for (int x = 0; x < 10; x++) {
                sb.append(" *");
            }
            sb.append(" ").append(y).append(System.lineSeparator());
        }
        sb.append(tähed);
        return sb.toString();
    }

}
