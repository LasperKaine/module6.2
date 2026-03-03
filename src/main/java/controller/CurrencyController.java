package controller;

import dao.CurrencyDao;
import entity.Currency;
import view.CurrencyView;

import java.sql.SQLException;
import java.util.List;

public class CurrencyController {

    private final CurrencyDao dao;
    private final CurrencyView view;

    public CurrencyController(CurrencyView view) {
        this.dao = new CurrencyDao();
        this.view = view;

        initialize();
    }

    private void loadCurrencies() {
        view.getFromBox().getItems().clear();
        view.getToBox().getItems().clear();

        try {
            List<Currency> currencies = dao.getAllCurrencies();
            view.getFromBox().getItems().addAll(currencies);
            view.getToBox().getItems().addAll(currencies);
            view.getMessageLabel().setText("");
        } catch (SQLException e) {
            view.getMessageLabel().setText("Error: Could not connect to the database.");
        }
    }

    private void initialize() {
        loadCurrencies();

        view.getAmountField().textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.matches("\\d*(\\.\\d*)?")) {
                view.getAmountField().setText(oldVal);
            }
        });

        view.getConvertButton().setOnAction(e -> convert());
    }

    private void convert() {
        try {
            if (view.getAmountField().getText().isEmpty()) {
                throw new IllegalArgumentException("Please enter an amount.");
            }

            double amount = Double.parseDouble(view.getAmountField().getText());

            Currency from = view.getFromBox().getValue();
            Currency to = view.getToBox().getValue();

            if (from == null || to == null) {
                throw new IllegalArgumentException("Please select both currencies.");
            }

            double fromRate = dao.getExchangeRate(from.getAbbreviation());
            double toRate = dao.getExchangeRate(to.getAbbreviation());

            double amountInUSD = amount / fromRate;
            double result = amountInUSD * toRate;

            view.getResultField().setText(String.format("%.2f", result));
            view.getMessageLabel().setText("");

        } catch (SQLException e) {
            view.getMessageLabel().setText("Error: Could not connect to the database.");
        } catch (IllegalArgumentException e) {
            view.getMessageLabel().setText("Error: " + e.getMessage());
        }
    }
}