package view;

import entity.Currency;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class CurrencyView extends VBox {

    private final TextField amountField = new TextField();
    private final TextField resultField = new TextField();
    private final ComboBox<Currency> fromBox = new ComboBox<>();
    private final ComboBox<Currency> toBox = new ComboBox<>();
    private final Button convertButton = new Button("Convert");
    private final Label messageLabel = new Label();
    private final Button addCurrencyButton = new Button("Add Currency");

    public CurrencyView() {
        getStyleClass().add("currency-view");
        setSpacing(15);
        setPadding(new Insets(20));

        buildUI();
        applyStyleIds();
    }

    private void buildUI() {
        Label instructions = new Label(
                "Enter amount → choose source currency → choose target currency → press Convert."
        );
        instructions.getStyleClass().add("instructions");

        resultField.setEditable(false);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.getStyleClass().add("currency-grid");

        grid.add(new Label("Amount:"), 0, 0);
        grid.add(amountField, 1, 0);

        grid.add(new Label("From Currency:"), 0, 1);
        grid.add(fromBox, 1, 1);

        grid.add(new Label("To Currency:"), 0, 2);
        grid.add(toBox, 1, 2);

        grid.add(new Label("Result:"), 0, 3);
        grid.add(resultField, 1, 3);

        grid.add(convertButton, 1, 4);

        grid.add(addCurrencyButton, 1, 5);

        getChildren().addAll(instructions, grid, messageLabel);
    }

    private void applyStyleIds() {
        amountField.setId("amount-field");
        resultField.setId("result-field");
        fromBox.setId("from-box");
        toBox.setId("to-box");
        convertButton.getStyleClass().add("primary-button");
        messageLabel.getStyleClass().add("message-label");
        amountField.setPromptText("0.00");
        resultField.setPromptText("--");
    }

    public TextField getAmountField() { return amountField; }
    public TextField getResultField() { return resultField; }
    public ComboBox<Currency> getFromBox() { return fromBox; }
    public ComboBox<Currency> getToBox() { return toBox; }
    public Button getConvertButton() { return convertButton; }
    public Label getMessageLabel() { return messageLabel; }
    public Button getAddCurrencyButton() { return addCurrencyButton; }

    public static void launch(Class<?> ignored, String... args) {
        Application.launch(CurrencyViewApp.class, args);
    }

    public static void launch(String... args) {
        Application.launch(CurrencyViewApp.class, args);
    }
}