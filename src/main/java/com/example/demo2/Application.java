package com.example.demo2;

import com.example.demo2.algorithms.BresenhamAlgorithm;
import com.example.demo2.algorithms.DDA_Algorithm;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage primaryStage) {

        Canvas canvas = new Canvas(500, 500);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        clearCanvas(gc);

        TextField x1Field = new TextField();
        TextField y1Field = new TextField();
        TextField x2Field = new TextField();
        TextField y2Field = new TextField();

        Button drawDDABButton = new Button("DDA");
        Button drawBresenhamBButton = new Button("Брезехем");
        Button clearButton = new Button("Очистить холст");


        GridPane inputGrid = new GridPane();
        inputGrid.setPadding(new Insets(10));
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);

        inputGrid.add(new Label("X1:"), 0, 0);
        inputGrid.add(x1Field, 1, 0);
        inputGrid.add(new Label("Y1:"), 2, 0);
        inputGrid.add(y1Field, 3, 0);
        inputGrid.add(new Label("X2:"), 0, 1);
        inputGrid.add(x2Field, 1, 1);
        inputGrid.add(new Label("Y2:"), 2, 1);
        inputGrid.add(y2Field, 3, 1);

        inputGrid.add(drawDDABButton, 1, 2, 2, 1);
        inputGrid.add(drawBresenhamBButton, 3, 2, 2, 1);
        inputGrid.add(clearButton, 5, 2, 2, 1);

        drawDDABButton.setOnAction(e -> {
            try {

                int x1 = Integer.parseInt(x1Field.getText());
                int y1 = Integer.parseInt(y1Field.getText());
                int x2 = Integer.parseInt(x2Field.getText());
                int y2 = Integer.parseInt(y2Field.getText());

                DDA_Algorithm.drawLine(gc, x1, y1, x2, y2);

            } catch (NumberFormatException ex) {
                System.out.println("Неверный формат координат. Введите целые числа.");
            }
        });

        drawBresenhamBButton.setOnAction(e -> {
            try{
                int x1 = Integer.parseInt(x1Field.getText());
                int y1 = Integer.parseInt(y1Field.getText());
                int x2 = Integer.parseInt(x2Field.getText());
                int y2 = Integer.parseInt(y2Field.getText());

                BresenhamAlgorithm.drawLine(gc, x1, y1, x2, y2);
            } catch (NumberFormatException ex) {
                System.out.println("Неверный формат координат. Введите целые числа.");
            }
        });

        clearButton.setOnAction(e -> clearCanvas(gc));

        VBox root = new VBox();
        root.getChildren().addAll(canvas, inputGrid);

        Scene scene = new Scene(root, 500, 600);

        primaryStage.setTitle("DDA Line Drawing Algorithm with User Input and Clear");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void clearCanvas(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 500, 500);
        gc.setStroke(Color.BLACK);
    }
}