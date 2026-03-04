package controller;

import dao.CurrencyDao;
import entity.Currency;
import view.CurrencyView;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;

import java.util.List;

public class CurrencyController {

    private final CurrencyDao dao;
    private final CurrencyView view;

    public CurrencyController(CurrencyView view) {
        this.dao = new CurrencyDao();
        this.view = view;
        initialize();
    }

    private void initialize() {
        loadCurrencies();

        view.getAmountField().textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.matches("\\d*(\\.\\d*)?")) {
                view.getAmountField().setText(oldVal);
            }
        });

        view.getConvertButton().setOnAction(e -> convert());
        view.getAddCurrencyButton().setOnAction(e -> openAddCurrencyWindow());
    }

    private void convert() {
        try {
            if (view.getAmountField().getText().isEmpty()) {
                throw new IllegalArgumentException("Enter amount.");
            }

            double amount = Double.parseDouble(view.getAmountField().getText());
            Currency from = view.getFromBox().getValue();
            Currency to = view.getToBox().getValue();

            if (from == null || to == null) {
                throw new IllegalArgumentException("Select both currencies.");
            }

            double result = amount / dao.getExchangeRate(from.getAbbreviation())
                    * dao.getExchangeRate(to.getAbbreviation());

            view.getResultField().setText(String.format("%.2f", result));
            view.getMessageLabel().setText("");

        } catch (IllegalArgumentException e) {
            view.getMessageLabel().setText("Error: " + e.getMessage());
        } catch (Exception e) {
            view.getMessageLabel().setText("Database error.");
        }
    }

    private void openAddCurrencyWindow() {
        Stage stage = new Stage();
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(15));

        TextField abbr = new TextField();
        abbr.setPromptText("Abbreviation");
        TextField name = new TextField();
        name.setPromptText("Name");
        TextField rate = new TextField();
        rate.setPromptText("Rate to USD");
        Button save = new Button("Save");

        layout.getChildren().addAll(abbr, name, rate, save);

        save.setOnAction(e -> {
            try {
                dao.insertCurrency(new Currency(
                        abbr.getText(), name.getText(), Double.parseDouble(rate.getText())
                ));
                stage.close();
                loadCurrencies();
            } catch (Exception ex) {
                view.getMessageLabel().setText("Error inserting currency.");
            }
        });

        stage.setScene(new Scene(layout, 300, 200));
        stage.showAndWait();
    }

    private void loadCurrencies() {
        view.getFromBox().getItems().clear();
        view.getToBox().getItems().clear();
        try {
            List<Currency> list = dao.getAllCurrencies();
            view.getFromBox().getItems().addAll(list);
            view.getToBox().getItems().addAll(list);
        } catch (Exception e) {
            view.getMessageLabel().setText("Could not load currencies.");
        }
    }
}