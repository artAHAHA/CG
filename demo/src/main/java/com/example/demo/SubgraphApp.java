package com.example.demo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubgraphApp extends Application {

    private SubgraphsWithMEdges subgraphFinder;
    private Canvas canvas;
    private TextArea resultArea;

    @Override
    public void start(Stage primaryStage) {
        List<int[]> edges = new ArrayList<>(Arrays.asList(
                new int[]{0, 5},
                new int[]{4, 3},
                new int[]{0, 1},
                new int[]{6, 4},
                new int[]{5, 4},
                new int[]{0, 2},
                new int[]{0, 6},
                new int[]{5, 3}
        ));

        subgraphFinder = new SubgraphsWithMEdges(edges);

        BorderPane root = new BorderPane();

        // Верхняя панель с вводом данных
        HBox topPanel = new HBox();
        topPanel.setSpacing(10);
        Label labelM = new Label("Количество рёбер в подграфе (M):");
        TextField textFieldM = new TextField();
        Button findButton = new Button("Найти подграфы");
        topPanel.getChildren().addAll(labelM, textFieldM, findButton);
        root.setTop(topPanel);

        // Область для рисования графа
        canvas = new Canvas(800, 600);
        root.setCenter(canvas);

        // Область для вывода результатов
        resultArea = new TextArea();
        resultArea.setEditable(false);
        root.setBottom(resultArea);

        findButton.setOnAction(event -> {
            int M = Integer.parseInt(textFieldM.getText());
            subgraphFinder.findSubgraphs(new ArrayList<>(), 0, M);
            displaySubgraphs(subgraphFinder.getSubgraphs(), M);
        });

        Scene scene = new Scene(root, 800, 700);
        primaryStage.setTitle("Подграфы с M рёбрами");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void displaySubgraphs(List<List<int[]>> subgraphs, int M) {
        resultArea.clear();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        int subgraphIndex = 1;
        for (List<int[]> subgraph : subgraphs) {
            resultArea.appendText("Подграф " + subgraphIndex + " с " + M + " рёбрами:\n");
            for (int[] edge : subgraph) {
                resultArea.appendText(Arrays.toString(edge) + "\n");
                drawEdge(gc, edge);
            }
            resultArea.appendText("\n");
            subgraphIndex++;
        }
    }

    private void drawEdge(GraphicsContext gc, int[] edge) {
        double radius = 10;
        double[] nodePositionsX = {50, 150, 250, 350, 450, 550, 650};
        double[] nodePositionsY = {300, 100, 500, 100, 500, 100, 300};

        gc.setFill(Color.BLACK);
        gc.fillOval(nodePositionsX[edge[0]] - radius / 2, nodePositionsY[edge[0]] - radius / 2, radius, radius);
        gc.fillOval(nodePositionsX[edge[1]] - radius / 2, nodePositionsY[edge[1]] - radius / 2, radius, radius);

        gc.setStroke(Color.BLUE);
        gc.strokeLine(nodePositionsX[edge[0]], nodePositionsY[edge[0]], nodePositionsX[edge[1]], nodePositionsY[edge[1]]);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
