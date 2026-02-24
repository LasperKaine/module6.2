package controller;

import view.CurrencyView;
import model.Currency;
import model.CurrencyModel;

public class CurrencyController {

    private final CurrencyModel model;
    private final CurrencyView view;

    public CurrencyController(CurrencyModel model, CurrencyView view) {
        this.model = model;
        this.view = view;

        createCurrencies();
        initialize();
    }

    private void createCurrencies() {
        model.addCurrency(new Currency("USD", "US Dollar", 1.0));
        model.addCurrency(new Currency("EUR", "Euro", 0.85));
        model.addCurrency(new Currency("GBP", "British Pound", 0.74));
        model.addCurrency(new Currency("JPY", "Japanese Yen", 156.08));
    }

    private void initialize() {

        view.getFromBox().getItems().addAll(model.getCurrencies());
        view.getToBox().getItems().addAll(model.getCurrencies());

        // Prevent letters in amount field
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

            // Convert via USD
            double amountInUSD = amount / from.getRateToUSD();
            double result = amountInUSD * to.getRateToUSD();

            view.getResultField().setText(String.format("%.2f", result));
            view.getMessageLabel().setText("");

        } catch (Exception ex) {
            view.getMessageLabel().setText("Error: " + ex.getMessage());
        }
    }
}