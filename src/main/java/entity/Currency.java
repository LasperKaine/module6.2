package entity;

public class Currency {

    private int id;
    private final String abbreviation;
    private final String name;
    private final double rateToUSD;

    public Currency(String abbreviation, String name, double rateToUSD) {
        this.abbreviation = abbreviation;
        this.name = name;
        this.rateToUSD = rateToUSD;
    }

    public Currency(int id, String abbreviation, String name, double rateToUSD) {
        this.id = id;
        this.abbreviation = abbreviation;
        this.name = name;
        this.rateToUSD = rateToUSD;
    }

    public int getId() {
        return id;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getName() {
        return name;
    }

    public double getRateToUSD() {
        return rateToUSD;
    }

    @Override
    public String toString() {
        return abbreviation + " - " + name;
    }
}
