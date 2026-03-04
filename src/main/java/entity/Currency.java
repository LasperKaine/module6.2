package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "currency")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String abbreviation;
    private String name;

    @Column(name = "rate_to_usd")
    private double rateToUSD;

    // Required by JPA
    public Currency() {
    }

    public Currency(String abbreviation, String name, double rateToUSD) {
        this.abbreviation = abbreviation;
        this.name = name;
        this.rateToUSD = rateToUSD;
    }

    public int getId() { return id; }

    public String getAbbreviation() { return abbreviation; }

    public String getName() { return name; }

    public double getRateToUSD() { return rateToUSD; }

    @Override
    public String toString() {
        return abbreviation + " - " + name;
    }
}