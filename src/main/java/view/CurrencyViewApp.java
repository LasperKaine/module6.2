package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.CurrencyModel;

public class CurrencyViewApp extends Application {

    @Override
    public void start(Stage stage) {
        CurrencyModel model = new CurrencyModel();
        CurrencyView view = new CurrencyView();
        new controller.CurrencyController(model, view);

        Scene scene = new Scene(view, 520, 380);
        // Load CSS from resources
        String css = getClass().getResource("/styles.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setTitle("Currency Converter");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
