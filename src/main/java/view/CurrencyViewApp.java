package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CurrencyViewApp extends Application {

    @Override
    public void start(Stage stage) {
        CurrencyView view = new CurrencyView();
        new controller.CurrencyController(view);

        Scene scene = new Scene(view, 520, 380);
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
