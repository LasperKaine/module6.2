package dao;

import datasource.MariaDbJpaConnection;
import entity.Currency;
import jakarta.persistence.EntityManager;
import java.util.List;

public class CurrencyDao {

    public List<Currency> getAllCurrencies() {
        EntityManager em = MariaDbJpaConnection.getInstance();
        return em.createQuery("SELECT c FROM Currency c", Currency.class)
                .getResultList();
    }

    public double getExchangeRate(String abbreviation) {
        EntityManager em = MariaDbJpaConnection.getInstance();

        Currency currency = em.createQuery(
                        "SELECT c FROM Currency c WHERE c.abbreviation = :abbr",
                        Currency.class)
                .setParameter("abbr", abbreviation)
                .getSingleResult();

        return currency.getRateToUSD();
    }

    public void insertCurrency(Currency currency) {
        EntityManager em = MariaDbJpaConnection.getInstance();

        em.getTransaction().begin();
        em.persist(currency);
        em.getTransaction().commit();
    }
}