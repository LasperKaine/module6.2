package model;

import java.util.ArrayList;
import java.util.List;

public class CurrencyModel {

    private final List<Currency> currencies = new ArrayList<>();

    public void addCurrency(Currency currency) {
        currencies.add(currency);
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }
}