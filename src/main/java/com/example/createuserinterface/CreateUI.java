package com.example.createuserinterface;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class CreateUI extends Application {
    private final TextArea textArea = new TextArea();
    private final Random random = new Random();

    @Override
    public void start(Stage primaryStage) {
        MenuBar menuBar = new MenuBar();

        // Create a menu
        Menu menu = new Menu("Menu");

        // Create and add menu items
        MenuItem dateItem = new MenuItem("Show Date/Time");
        dateItem.setOnAction(e -> showDateTime());
        menu.getItems().add(dateItem);

        MenuItem saveItem = new MenuItem("Save to File");
        saveItem.setOnAction(e -> saveToFile());
        menu.getItems().add(saveItem);

        MenuItem colorItem = new MenuItem("Change Background Color");
        colorItem.setOnAction(e -> changeBackgroundColor());
        menu.getItems().add(colorItem);

        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction(e -> System.exit(0));
        menu.getItems().add(exitItem);

        // Add menu to menu bar
        menuBar.getMenus().add(menu);

        // Layout setup
        BorderPane root = new BorderPane();
        root.setTop(new VBox(menuBar));
        root.setCenter(textArea);

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("JavaFX App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy HH:mm:ss");
        textArea.appendText("Date and Time: " + now.format(formatter) + "\n");
    }

    private void saveToFile() {
        try (PrintWriter out = new PrintWriter(Files.newBufferedWriter(Paths.get("log.txt")))) {
            out.println(textArea.getText());
        } catch (IOException e) {
            e.printStackTrace();
            textArea.appendText("Couldn't Write to File.\n");
        }
    }

    private void changeBackgroundColor() {
        // Generate a new green hue between 85 and 140 degrees each time this method is called
        float newGreenHue = 85 + random.nextFloat() * (140 - 85);
        textArea.setStyle(String.format("-fx-control-inner-background: hsb(%f, 50%%, 50%%);", newGreenHue));
    }

    public static void main(String[] args) {
        launch(args);
    }
}