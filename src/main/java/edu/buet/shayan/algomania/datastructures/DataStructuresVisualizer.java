package edu.buet.shayan.algomania.datastructures;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.paint.CycleMethod;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.animation.*;
import javafx.util.Duration;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.Glow;
import java.util.*;

public class DataStructuresVisualizer extends Application {

    private Pane canvas;
    private ComboBox<String> structureSelector;
    private TextField inputField;
    private TextField indexField;
    private VBox controlPanel;
    private Label statusLabel;
    private Label infoLabel;
    private Button insertBtn, deleteBtn, searchBtn, clearBtn;
    private Button insertAtBtn, deleteAtBtn, peekBtn, sizeBtn;

    // Data structure implementations
    private VisualArray visualArray;
    private VisualSLL visualSLL;
    private VisualDLL visualDLL;
    private VisualStack visualStack;
    private VisualQueue visualQueue;
    private VisualHeap visualHeap;
    private VisualBST visualBST;

    private String currentStructure = "Array";

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #667eea 0%, #764ba2 100%);");

        // Initialize canvas with modern styling
        canvas = new Pane();
        canvas.setPrefSize(900, 650);
        canvas.setStyle("-fx-background-color: rgba(255, 255, 255, 0.95); " +
                "-fx-background-radius: 15; " +
                "-fx-border-radius: 15; " +
                "-fx-border-color: rgba(0, 0, 0, 0.1); " +
                "-fx-border-width: 2;");

        // Add drop shadow to canvas
        DropShadow canvasShadow = new DropShadow();
        canvasShadow.setRadius(15);
        canvasShadow.setOffsetX(5);
        canvasShadow.setOffsetY(5);
        canvasShadow.setColor(Color.rgb(0, 0, 0, 0.3));
        canvas.setEffect(canvasShadow);

        // Wrap canvas in a container with padding
        StackPane canvasContainer = new StackPane(canvas);
        canvasContainer.setPadding(new Insets(20));

        // Initialize control panel
        setupControlPanel();

        // Initialize data structures
        initializeDataStructures();

        root.setCenter(canvasContainer);
        ScrollPane scrollPane = new ScrollPane(controlPanel);
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setStyle("-fx-background-color: transparent;");

        root.setRight(scrollPane); // Instead of root.setRight(controlPanel);
//        root.setRight(controlPanel);

        Scene scene = new Scene(root, 1200, 650);
        primaryStage.setTitle("Advanced Data Structures Visualizer");
        primaryStage.setScene(scene);
//        primaryStage.setResizable(false);
        primaryStage.show();

        // Show initial structure
        showStructure(currentStructure);
        updateInfo();
    }

    private void setupControlPanel() {
        controlPanel = new VBox(15);
        controlPanel.setPadding(new Insets(25));
        controlPanel.setPrefWidth(350);
        controlPanel.setStyle("-fx-background-color: rgba(255, 255, 255, 0.95); " +
                "-fx-background-radius: 15; " +
                "-fx-border-radius: 15;");

        // Add drop shadow to control panel
        DropShadow panelShadow = new DropShadow();
        panelShadow.setRadius(15);
        panelShadow.setOffsetX(-5);
        panelShadow.setOffsetY(5);
        panelShadow.setColor(Color.rgb(0, 0, 0, 0.3));
        controlPanel.setEffect(panelShadow);

        // Title with gradient effect
        Label title = new Label("🔬 Data Structure Visualizer");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        title.setStyle("-fx-text-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, #667eea 0%, #764ba2 50%);");

        // Structure selector with improved styling
        Label selectorLabel = new Label("📊 Select Data Structure:");
        selectorLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 14));
        selectorLabel.setStyle("-fx-text-fill: #2c3e50;");

        structureSelector = new ComboBox<>();
        structureSelector.getItems().addAll("Array", "Singly Linked List", "Doubly Linked List",
                "Stack", "Queue", "Heap", "Binary Search Tree");
        structureSelector.setValue("Array");
        structureSelector.setPrefWidth(300);
        structureSelector.setStyle("-fx-background-color: white; " +
                "-fx-border-color: #3498db; " +
                "-fx-border-radius: 8; " +
                "-fx-background-radius: 8; " +
                "-fx-font-size: 12px;");
        structureSelector.setOnAction(e -> {
            currentStructure = structureSelector.getValue();
            showStructure(currentStructure);
            updateInfo();
            updateStatus("Switched to " + currentStructure);
        });

        // Input fields section
        Label inputLabel = new Label("🔢 Input Values:");
        inputLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 14));
        inputLabel.setStyle("-fx-text-fill: #2c3e50;");

        inputField = new TextField();
        inputField.setPromptText("Enter value (e.g., 42)");
        inputField.setPrefWidth(300);
        styleTextField(inputField);

        indexField = new TextField();
        indexField.setPromptText("Enter index (optional)");
        indexField.setPrefWidth(300);
        styleTextField(indexField);

        // Main operation buttons
        Label operationsLabel = new Label("⚡ Main Operations:");
        operationsLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 14));
        operationsLabel.setStyle("-fx-text-fill: #2c3e50;");

        insertBtn = createStyledButton("➕ Insert", "#27ae60");
        deleteBtn = createStyledButton("❌ Delete", "#e74c3c");
        searchBtn = createStyledButton("🔍 Search", "#3498db");
        clearBtn = createStyledButton("🗑️ Clear All", "#95a5a6");

        // Advanced operation buttons
        Label advancedLabel = new Label("🎯 Advanced Operations:");
        advancedLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 14));
        advancedLabel.setStyle("-fx-text-fill: #2c3e50;");

        insertAtBtn = createStyledButton("📍 Insert At Index", "#f39c12");
        deleteAtBtn = createStyledButton("🎯 Delete At Index", "#e67e22");
        peekBtn = createStyledButton("👁️ Peek/Top", "#9b59b6");
        sizeBtn = createStyledButton("📏 Size/Count", "#34495e");

        // Set button actions
        insertBtn.setOnAction(e -> performOperation("insert"));
        deleteBtn.setOnAction(e -> performOperation("delete"));
        searchBtn.setOnAction(e -> performOperation("search"));
        clearBtn.setOnAction(e -> performOperation("clear"));
        insertAtBtn.setOnAction(e -> performOperation("insertAt"));
        deleteAtBtn.setOnAction(e -> performOperation("deleteAt"));
        peekBtn.setOnAction(e -> performOperation("peek"));
        sizeBtn.setOnAction(e -> performOperation("size"));

        // Status and info labels
        statusLabel = new Label("✅ Ready to visualize data structures!");
        statusLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
        statusLabel.setStyle("-fx-text-fill: #27ae60; -fx-background-color: rgba(46, 204, 113, 0.1); " +
                "-fx-padding: 8; -fx-background-radius: 5;");
        statusLabel.setWrapText(true);

        infoLabel = new Label();
        infoLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 11));
        infoLabel.setStyle("-fx-text-fill: #7f8c8d; -fx-background-color: rgba(127, 140, 141, 0.1); " +
                "-fx-padding: 8; -fx-background-radius: 5;");
        infoLabel.setWrapText(true);

        // Add all components to control panel
        controlPanel.getChildren().addAll(
                title,
                new Separator(),
                selectorLabel, structureSelector,
                new Separator(),
                inputLabel, inputField, indexField,
                new Separator(),
                operationsLabel, insertBtn, deleteBtn, searchBtn, clearBtn,
                new Separator(),
                advancedLabel, insertAtBtn, deleteAtBtn, peekBtn, sizeBtn,
                new Separator(),
                statusLabel, infoLabel
        );
    }

    private void styleTextField(TextField field) {
        field.setStyle("-fx-background-color: white; " +
                "-fx-border-color: #bdc3c7; " +
                "-fx-border-radius: 8; " +
                "-fx-background-radius: 8; " +
                "-fx-padding: 8; " +
                "-fx-font-size: 12px;");

        // Add focus effect
        field.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (isNowFocused) {
                field.setStyle("-fx-background-color: white; " +
                        "-fx-border-color: #3498db; " +
                        "-fx-border-width: 2; " +
                        "-fx-border-radius: 8; " +
                        "-fx-background-radius: 8; " +
                        "-fx-padding: 8; " +
                        "-fx-font-size: 12px;");
            } else {
                field.setStyle("-fx-background-color: white; " +
                        "-fx-border-color: #bdc3c7; " +
                        "-fx-border-radius: 8; " +
                        "-fx-background-radius: 8; " +
                        "-fx-padding: 8; " +
                        "-fx-font-size: 12px;");
            }
        });
    }

    private Button createStyledButton(String text, String color) {
        Button button = new Button(text);
        button.setPrefWidth(300);
        button.setPrefHeight(40);
        button.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 12));

        String baseStyle = "-fx-background-color: " + color + "; " +
                "-fx-text-fill: white; " +
                "-fx-background-radius: 8; " +
                "-fx-border-radius: 8; " +
                "-fx-cursor: hand; " +
                "-fx-font-weight: bold;";

        String hoverStyle = "-fx-background-color: derive(" + color + ", -10%); " +
                "-fx-text-fill: white; " +
                "-fx-background-radius: 8; " +
                "-fx-border-radius: 8; " +
                "-fx-cursor: hand; " +
                "-fx-font-weight: bold; " +
                "-fx-scale-x: 1.02; " +
                "-fx-scale-y: 1.02;";

        button.setStyle(baseStyle);

        // Add hover effects
        button.setOnMouseEntered(e -> {
            button.setStyle(hoverStyle);
            ScaleTransition st = new ScaleTransition(Duration.millis(100), button);
            st.setToX(1.02);
            st.setToY(1.02);
            st.play();
        });

        button.setOnMouseExited(e -> {
            button.setStyle(baseStyle);
            ScaleTransition st = new ScaleTransition(Duration.millis(100), button);
            st.setToX(1.0);
            st.setToY(1.0);
            st.play();
        });

        // Add glow effect
        Glow glow = new Glow();
        glow.setLevel(0.3);
        button.setEffect(glow);

        return button;
    }

    private void initializeDataStructures() {
        visualArray = new VisualArray(canvas);
        visualSLL = new VisualSLL(canvas);
        visualDLL = new VisualDLL(canvas);
        visualStack = new VisualStack(canvas);
        visualQueue = new VisualQueue(canvas);
        visualHeap = new VisualHeap(canvas);
        visualBST = new VisualBST(canvas);
    }

    private void showStructure(String structure) {
        canvas.getChildren().clear();

        switch (structure) {
            case "Array":
                visualArray.display();
                break;
            case "Singly Linked List":
                visualSLL.display();
                break;
            case "Doubly Linked List":
                visualDLL.display();
                break;
            case "Stack":
                visualStack.display();
                break;
            case "Queue":
                visualQueue.display();
                break;
            case "Heap":
                visualHeap.display();
                break;
            case "Binary Search Tree":
                visualBST.display();
                break;
        }
    }

    private void performOperation(String operation) {
        String input = inputField.getText().trim();
        String indexInput = indexField.getText().trim();

        try {
            switch (currentStructure) {
                case "Array":
                    performArrayOperation(operation, input, indexInput);
                    break;
                case "Singly Linked List":
                    performSLLOperation(operation, input, indexInput);
                    break;
                case "Doubly Linked List":
                    performDLLOperation(operation, input, indexInput);
                    break;
                case "Stack":
                    performStackOperation(operation, input, indexInput);
                    break;
                case "Queue":
                    performQueueOperation(operation, input, indexInput);
                    break;
                case "Heap":
                    performHeapOperation(operation, input, indexInput);
                    break;
                case "Binary Search Tree":
                    performBSTOperation(operation, input, indexInput);
                    break;
            }
            inputField.clear();
            indexField.clear();
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter valid numbers only.", Alert.AlertType.ERROR);
            updateStatus("❌ Invalid input format");
        } catch (Exception e) {
            showAlert("Operation Error", e.getMessage(), Alert.AlertType.WARNING);
            updateStatus("❌ " + e.getMessage());
        }
    }

    private void performArrayOperation(String operation, String input, String indexInput) {
        switch (operation) {
            case "insert":
                if (!input.isEmpty()) {
                    visualArray.insert(Integer.parseInt(input));
                    updateStatus("✅ Inserted " + input + " into array");
                }
                break;
            case "insertAt":
                if (!input.isEmpty() && !indexInput.isEmpty()) {
                    visualArray.insertAt(Integer.parseInt(indexInput), Integer.parseInt(input));
                    updateStatus("✅ Inserted " + input + " at index " + indexInput);
                }
                break;
            case "delete":
                if (!input.isEmpty()) {
                    visualArray.delete(Integer.parseInt(input));
                    updateStatus("✅ Deleted " + input + " from array");
                } else {
                    visualArray.deleteLast();
                    updateStatus("✅ Deleted last element");
                }
                break;
            case "deleteAt":
                if (!indexInput.isEmpty()) {
                    int deleted = visualArray.deleteAt(Integer.parseInt(indexInput));
                    updateStatus("✅ Deleted " + deleted + " from index " + indexInput);
                }
                break;
            case "search":
                if (!input.isEmpty()) {
                    int index = visualArray.search(Integer.parseInt(input));
                    updateStatus("✅ Found " + input + " at index " + index);
                }
                break;
            case "size":
                int size = visualArray.size();
                updateStatus("📏 Array size: " + size);
                break;
            case "clear":
                visualArray.clear();
                updateStatus("✅ Array cleared");
                break;
            case "peek":
                if (VisualArray.size() != 0) {
                    size = VisualArray.size();
                    updateStatus("👁️ Last element: " + VisualArray.get(size - 1));
                } else {
                    updateStatus("❌ Array is empty");
                }
                break;
        }
    }

    private void performSLLOperation(String operation, String input, String indexInput) {
        switch (operation) {
            case "insert":
                if (!input.isEmpty()) {
                    visualSLL.insert(Integer.parseInt(input));
                    updateStatus("✅ Inserted " + input + " into linked list");
                }
                break;
            case "insertAt":
                if (!input.isEmpty() && !indexInput.isEmpty()) {
                    visualSLL.insertAt(Integer.parseInt(indexInput), Integer.parseInt(input));
                    updateStatus("✅ Inserted " + input + " at position " + indexInput);
                }
                break;
            case "delete":
                if (!input.isEmpty()) {
                    visualSLL.delete(Integer.parseInt(input));
                    updateStatus("✅ Deleted " + input + " from linked list");
                } else {
                    visualSLL.deleteFirst();
                    updateStatus("✅ Deleted first element");
                }
                break;
            case "deleteAt":
                if (!indexInput.isEmpty()) {
                    int deleted = visualSLL.deleteAt(Integer.parseInt(indexInput));
                    updateStatus("✅ Deleted " + deleted + " from position " + indexInput);
                }
                break;
            case "search":
                if (!input.isEmpty()) {
                    int position = visualSLL.search(Integer.parseInt(input));
                    updateStatus("✅ Found " + input + " at position " + position);
                }
                break;
            case "size":
                int size = visualSLL.size();
                updateStatus("📏 List size: " + size);
                break;
            case "clear":
                visualSLL.clear();
                updateStatus("✅ Linked list cleared");
                break;
            case "peek":
                size = VisualSLL.size();
                if (size != 0) {
                    updateStatus("👁️ First element: " + VisualSLL.get(0));
                } else {
                    updateStatus("❌ List is empty");
                }
                break;
        }
    }

    private void performDLLOperation(String operation, String input, String indexInput) {
        switch (operation) {
            case "insert":
                if (!input.isEmpty()) {
                    visualDLL.insert(Integer.parseInt(input));
                    updateStatus("✅ Inserted " + input + " into doubly linked list");
                }
                break;
            case "insertAt":
                if (!input.isEmpty() && !indexInput.isEmpty()) {
                    visualDLL.insertAt(Integer.parseInt(indexInput), Integer.parseInt(input));
                    updateStatus("✅ Inserted " + input + " at position " + indexInput);
                }
                break;
            case "delete":
                if (!input.isEmpty()) {
                    visualDLL.delete(Integer.parseInt(input));
                    updateStatus("✅ Deleted " + input + " from doubly linked list");
                } else {
                    visualDLL.deleteFirst();
                    updateStatus("✅ Deleted first element");
                }
                break;
            case "deleteAt":
                if (!indexInput.isEmpty()) {
                    int deleted = visualDLL.deleteAt(Integer.parseInt(indexInput));
                    updateStatus("✅ Deleted " + deleted + " from position " + indexInput);
                }
                break;
            case "search":
                if (!input.isEmpty()) {
                    int position = visualDLL.search(Integer.parseInt(input));
                    updateStatus("✅ Found " + input + " at position " + position);
                }
                break;
            case "size":
                int size = visualDLL.size();
                updateStatus("📏 List size: " + size);
                break;
            case "clear":
                visualDLL.clear();
                updateStatus("✅ Doubly linked list cleared");
                break;
            case "peek":
                size = VisualDLL.size();
                if (size != 0) {
                    updateStatus("👁️ First element: " + VisualDLL.get(0));
                } else {
                    updateStatus("❌ List is empty");
                }
                break;
        }
    }

    private void performStackOperation(String operation, String input, String indexInput) {
        switch (operation) {
            case "insert":
                if (!input.isEmpty()) {
                    visualStack.push(Integer.parseInt(input));
                    updateStatus("✅ Pushed " + input + " onto stack");
                }
                break;
            case "delete":
                int popped = visualStack.pop();
                updateStatus("✅ Popped " + popped + " from stack");
                break;
            case "peek":
                int top = visualStack.peek();
                updateStatus("👁️ Top element: " + top);
                break;
            case "search":
                if (!input.isEmpty()) {
                    int position = visualStack.search(Integer.parseInt(input));
                    updateStatus("✅ Found " + input + " at position " + position + " from top");
                }
                break;
            case "size":
                int size = visualStack.size();
                updateStatus("📏 Stack size: " + size);
                break;
            case "clear":
                visualStack.clear();
                updateStatus("✅ Stack cleared");
                break;
        }
    }

    private void performQueueOperation(String operation, String input, String indexInput) {
        switch (operation) {
            case "insert":
                if (!input.isEmpty()) {
                    visualQueue.enqueue(Integer.parseInt(input));
                    updateStatus("✅ Enqueued " + input + " to queue");
                }
                break;
            case "delete":
                int dequeued = visualQueue.dequeue();
                updateStatus("✅ Dequeued " + dequeued + " from queue");
                break;
            case "peek":
                int front = visualQueue.peek();
                updateStatus("👁️ Front element: " + front);
                break;
            case "search":
                if (!input.isEmpty()) {
                    int position = visualQueue.search(Integer.parseInt(input));
                    updateStatus("✅ Found " + input + " at position " + position);
                }
                break;
            case "size":
                int size = visualQueue.size();
                updateStatus("📏 Queue size: " + size);
                break;
            case "clear":
                visualQueue.clear();
                updateStatus("✅ Queue cleared");
                break;
        }
    }

    private void performHeapOperation(String operation, String input, String indexInput) {
        switch (operation) {
            case "insert":
                if (!input.isEmpty()) {
                    visualHeap.insert(Integer.parseInt(input));
                    updateStatus("✅ Inserted " + input + " into heap");
                }
                break;
            case "delete":
                int extracted = visualHeap.extractMax();
                updateStatus("✅ Extracted max: " + extracted);
                break;
            case "peek":
                int max = visualHeap.peekMax();
                updateStatus("👁️ Max element: " + max);
                break;
            case "search":
                if (!input.isEmpty()) {
                    boolean found = visualHeap.search(Integer.parseInt(input));
                    if (found) {
                        updateStatus("✅ Found " + input + " in heap");
                    } else {
                        updateStatus("❌ " + input + " not found in heap");
                    }
                }
                break;
            case "size":
                int size = visualHeap.size();
                updateStatus("📏 Heap size: " + size);
                break;
            case "clear":
                visualHeap.clear();
                updateStatus("✅ Heap cleared");
                break;
        }
    }

    private void performBSTOperation(String operation, String input, String indexInput) {
        switch (operation) {
            case "insert":
                if (!input.isEmpty()) {
                    visualBST.insert(Integer.parseInt(input));
                    updateStatus("✅ Inserted " + input + " into BST");
                }
                break;
            case "delete":
                if (!input.isEmpty()) {
                    visualBST.delete(Integer.parseInt(input));
                    updateStatus("✅ Deleted " + input + " from BST");
                }
                break;
            case "search":
                if (!input.isEmpty()) {
                    boolean found = visualBST.search(Integer.parseInt(input));
                    if (found) {
                        updateStatus("✅ Found " + input + " in BST");
                    } else {
                        updateStatus("❌ " + input + " not found in BST");
                    }
                }
                break;
            case "size":
                int size = visualBST.size();
                updateStatus("📏 BST size: " + size);
                break;
            case "peek":
                int[] minMax = visualBST.getMinMax();
                if (minMax != null) {
                    updateStatus("👁️ Min: " + minMax[0] + ", Max: " + minMax[1]);
                } else {
                    updateStatus("❌ BST is empty");
                }
                break;
            case "clear":
                visualBST.clear();
                updateStatus("✅ BST cleared");
                break;
        }
    }

    private void updateStatus(String message) {
        statusLabel.setText(message);

        // Add a fade-in animation for status updates
        FadeTransition ft = new FadeTransition(Duration.millis(300), statusLabel);
        ft.setFromValue(0.6);
        ft.setToValue(1.0);
        ft.play();
    }

    private void updateInfo() {
        String info = "";
        switch (currentStructure) {
            case "Array":
                info = "📊 Array: Fixed-size sequential collection. O(1) access, O(n) insertion/deletion.";
                break;
            case "Singly Linked List":
                info = "🔗 Singly Linked List: Dynamic size, sequential access. O(1) insertion at head, O(n) search.";
                break;
            case "Doubly Linked List":
                info = "🔗🔗 Doubly Linked List: Bidirectional traversal. O(1) insertion/deletion at both ends.";
                break;
            case "Stack":
                info = "📚 Stack: LIFO (Last In, First Out). O(1) push/pop operations. Used in recursion, undo operations.";
                break;
            case "Queue":
                info = "🚶‍♂️ Queue: FIFO (First In, First Out). O(1) enqueue/dequeue. Used in BFS, scheduling.";
                break;
            case "Heap":
                info = "🏔️ Max Heap: Complete binary tree. O(log n) insertion/extraction. Used in priority queues.";
                break;
            case "Binary Search Tree":
                info = "🌳 BST: Ordered binary tree. O(log n) average operations. Left < root < right property.";
                break;
        }
        infoLabel.setText(info);
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        // Style the alert
        alert.getDialogPane().setStyle("-fx-background-color: white; -fx-font-family: Arial;");

        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }

    // Enhanced Visual Array Implementation
    class VisualArray {
        private Pane canvas;
        private List<Integer> array;
        private List<Rectangle> rectangles;
        private List<Text> texts;
        private final int MAX_SIZE = 12;
        private final double RECT_WIDTH = 65;
        private final double RECT_HEIGHT = 45;
        private final double START_X = 70;
        private final double START_Y = 120;

        public VisualArray(Pane canvas) {
            this.canvas = canvas;
            this.array = new ArrayList<>();
            this.rectangles = new ArrayList<>();
            this.texts = new ArrayList<>();
        }

        public static int get(int index){
            return VisualArray.get(size() - 1);
        }

        public void display() {
            canvas.getChildren().clear();
            rectangles.clear();
            texts.clear();

            // Draw modern title with styling
            Text title = new Text(START_X, START_Y - 50, "📊 Array Visualization");
            title.setFont(Font.font("Arial", FontWeight.BOLD, 18));
            title.setFill(Color.web("#2c3e50"));
            canvas.getChildren().add(title);

            // Add description
            Text desc = new Text(START_X, START_Y - 25, "Fixed-size sequential collection with O(1) random access");
            desc.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
            desc.setFill(Color.web("#7f8c8d"));
            canvas.getChildren().add(desc);

            // Draw array elements
            for (int i = 0; i < array.size(); i++) {
                drawElement(i, array.get(i), false);
            }

            // Draw empty slots
            for (int i = array.size(); i < MAX_SIZE; i++) {
                drawEmptySlot(i);
            }

            // Draw indices with modern styling
            for (int i = 0; i < MAX_SIZE; i++) {
                Text indexText = new Text(START_X + i * (RECT_WIDTH + 8) + RECT_WIDTH / 2 - 8,
                        START_Y + RECT_HEIGHT + 25, String.valueOf(i));
                indexText.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 11));
                indexText.setFill(Color.web("#95a5a6"));
                canvas.getChildren().add(indexText);
            }

            // Add array info
            Text info = new Text(START_X, START_Y + RECT_HEIGHT + 50,
                    String.format("Size: %d/%d elements", array.size(), MAX_SIZE));
            info.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
            info.setFill(Color.web("#34495e"));
            canvas.getChildren().add(info);
        }

        private void drawElement(int index, int value, boolean highlight) {
            double x = START_X + index * (RECT_WIDTH + 8);
            double y = START_Y;

            // Create gradient rectangle
            Rectangle rect = new Rectangle(x, y, RECT_WIDTH, RECT_HEIGHT);
            rect.setFill(highlight ? Color.web("#3498db") : Color.web("#ecf0f1"));
            rect.setStroke(Color.web("#bdc3c7"));
            rect.setStrokeWidth(2);
            rect.setArcWidth(8);
            rect.setArcHeight(8);

            // Add shadow effect
            DropShadow shadow = new DropShadow();
            shadow.setRadius(5);
            shadow.setOffsetX(2);
            shadow.setOffsetY(2);
            shadow.setColor(Color.rgb(0, 0, 0, 0.2));
            rect.setEffect(shadow);

            Text text = new Text(x + RECT_WIDTH/2 - 8, y + RECT_HEIGHT/2 + 5, String.valueOf(value));
            text.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            text.setFill(highlight ? Color.WHITE : Color.web("#2c3e50"));

            rectangles.add(rect);
            texts.add(text);
            canvas.getChildren().addAll(rect, text);

            if (highlight) {
                // Add pulse animation for highlighted elements
                ScaleTransition pulse = new ScaleTransition(Duration.millis(500), rect);
                pulse.setFromX(1.0);
                pulse.setFromY(1.0);
                pulse.setToX(1.1);
                pulse.setToY(1.1);
                pulse.setCycleCount(2);
                pulse.setAutoReverse(true);
                pulse.play();
            }
        }

        private void drawEmptySlot(int index) {
            double x = START_X + index * (RECT_WIDTH + 8);
            double y = START_Y;

            Rectangle rect = new Rectangle(x, y, RECT_WIDTH, RECT_HEIGHT);
            rect.setFill(Color.TRANSPARENT);
            rect.setStroke(Color.web("#bdc3c7"));
            rect.setStrokeWidth(2);
            rect.getStrokeDashArray().addAll(5d, 5d);
            rect.setArcWidth(8);
            rect.setArcHeight(8);

            canvas.getChildren().add(rect);
        }

        public void insert(int value) {
            if (array.size() >= MAX_SIZE) {
                throw new RuntimeException("Array is full!");
            }
            array.add(value);
            display();
        }

        public void insertAt(int index, int value) {
            if (index < 0 || index > array.size()) {
                throw new RuntimeException("Invalid index!");
            }
            if (array.size() >= MAX_SIZE) {
                throw new RuntimeException("Array is full!");
            }
            array.add(index, value);
            display();
        }

        public void delete(int value) {
            boolean removed = array.remove(Integer.valueOf(value));
            if (!removed) {
                throw new RuntimeException("Value not found!");
            }
            display();
        }

        public void deleteLast() {
            if (array.isEmpty()) {
                throw new RuntimeException("Array is empty!");
            }
            array.remove(array.size() - 1);
            display();
        }

        public int deleteAt(int index) {
            if (index < 0 || index >= array.size()) {
                throw new RuntimeException("Invalid index!");
            }
            int value = array.remove(index);
            display();
            return value;
        }

        public int search(int value) {
            int index = array.indexOf(value);
            if (index == -1) {
                throw new RuntimeException("Value not found!");
            }
            // Highlight found element
            display();
            drawElement(index, value, true);
            return index;
        }

        public static int size() {
            return VisualArray.size();
        }

        public void clear() {
            array.clear();
            display();
        }
    }

    // Visual Singly Linked List Implementation
    static class VisualSLL {
        private Pane canvas;
        private static List<Integer> list;
        private final double NODE_SIZE = 50;
        private final double START_X = 80;
        private final double START_Y = 150;
        private final double NODE_SPACING = 120;

        public VisualSLL(Pane canvas) {
            this.canvas = canvas;
            this.list = new ArrayList<>();
        }

        public static int get(int index){
            return list.get(index);
        }

        public void display() {
            canvas.getChildren().clear();

            // Title
            Text title = new Text(START_X, START_Y - 50, "🔗 Singly Linked List");
            title.setFont(Font.font("Arial", FontWeight.BOLD, 18));
            title.setFill(Color.web("#2c3e50"));
            canvas.getChildren().add(title);

            Text desc = new Text(START_X, START_Y - 25, "Dynamic linear structure with sequential access");
            desc.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
            desc.setFill(Color.web("#7f8c8d"));
            canvas.getChildren().add(desc);

            if (list.isEmpty()) {
                Text empty = new Text(START_X, START_Y + 50, "List is empty");
                empty.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
                empty.setFill(Color.web("#95a5a6"));
                canvas.getChildren().add(empty);
                return;
            }

            for (int i = 0; i < list.size(); i++) {
                drawNode(i, list.get(i));
                if (i < list.size() - 1) {
                    drawArrow(i);
                }
            }

            // Add "NULL" at the end
            Text nullText = new Text(START_X + list.size() * NODE_SPACING + 20, START_Y + NODE_SIZE/2 + 5, "NULL");
            nullText.setFont(Font.font("Arial", FontWeight.BOLD, 12));
            nullText.setFill(Color.web("#e74c3c"));
            canvas.getChildren().add(nullText);
        }

        private void drawNode(int index, int value) {
            double x = START_X + index * NODE_SPACING;
            double y = START_Y;

            // Data part
            Rectangle dataRect = new Rectangle(x, y, NODE_SIZE, NODE_SIZE);
            dataRect.setFill(Color.web("#3498db"));
            dataRect.setStroke(Color.web("#2980b9"));
            dataRect.setStrokeWidth(2);
            dataRect.setArcWidth(8);
            dataRect.setArcHeight(8);

            Text dataText = new Text(x + NODE_SIZE/2 - 8, y + NODE_SIZE/2 + 5, String.valueOf(value));
            dataText.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            dataText.setFill(Color.WHITE);

            // Next pointer part
            Rectangle nextRect = new Rectangle(x + NODE_SIZE, y, 25, NODE_SIZE);
            nextRect.setFill(Color.web("#e74c3c"));
            nextRect.setStroke(Color.web("#c0392b"));
            nextRect.setStrokeWidth(2);
            nextRect.setArcWidth(8);
            nextRect.setArcHeight(8);

            canvas.getChildren().addAll(dataRect, dataText, nextRect);
        }

        private void drawArrow(int index) {
            double startX = START_X + index * NODE_SPACING + NODE_SIZE + 25;
            double startY = START_Y + NODE_SIZE/2;
            double endX = START_X + (index + 1) * NODE_SPACING;
            double endY = START_Y + NODE_SIZE/2;

            Line arrow = new Line(startX, startY, endX - 10, endY);
            arrow.setStroke(Color.web("#34495e"));
            arrow.setStrokeWidth(2);

            // Arrow head
            Line arrowHead1 = new Line(endX - 10, endY, endX - 15, endY - 5);
            Line arrowHead2 = new Line(endX - 10, endY, endX - 15, endY + 5);
            arrowHead1.setStroke(Color.web("#34495e"));
            arrowHead2.setStroke(Color.web("#34495e"));
            arrowHead1.setStrokeWidth(2);
            arrowHead2.setStrokeWidth(2);

            canvas.getChildren().addAll(arrow, arrowHead1, arrowHead2);
        }

        public void insert(int value) {
            list.add(0, value); // Insert at head
            display();
        }

        public void insertAt(int index, int value) {
            if (index < 0 || index > list.size()) {
                throw new RuntimeException("Invalid index!");
            }
            list.add(index, value);
            display();
        }

        public void delete(int value) {
            boolean removed = list.remove(Integer.valueOf(value));
            if (!removed) {
                throw new RuntimeException("Value not found!");
            }
            display();
        }

        public void deleteFirst() {
            if (list.isEmpty()) {
                throw new RuntimeException("List is empty!");
            }
            list.remove(0);
            display();
        }

        public int deleteAt(int index) {
            if (index < 0 || index >= list.size()) {
                throw new RuntimeException("Invalid index!");
            }
            int value = list.remove(index);
            display();
            return value;
        }

        public int search(int value) {
            int index = list.indexOf(value);
            if (index == -1) {
                throw new RuntimeException("Value not found!");
            }
            return index;
        }

        public static int size() {
            return list.size();
        }

        public void clear() {
            list.clear();
            display();
        }
    }

    // Visual Doubly Linked List Implementation
    class VisualDLL {
        private Pane canvas;
        private static List<Integer> list;
        private final double NODE_SIZE = 50;
        private final double START_X = 80;
        private final double START_Y = 150;
        private final double NODE_SPACING = 140;

        public VisualDLL(Pane canvas) {
            this.canvas = canvas;
            this.list = new ArrayList<>();
        }

        public static int get(int index){
            return list.get(index);
        }

        public void display() {
            canvas.getChildren().clear();

            // Title
            Text title = new Text(START_X, START_Y - 50, "🔗🔗 Doubly Linked List");
            title.setFont(Font.font("Arial", FontWeight.BOLD, 18));
            title.setFill(Color.web("#2c3e50"));
            canvas.getChildren().add(title);

            Text desc = new Text(START_X, START_Y - 25, "Bidirectional linked structure");
            desc.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
            desc.setFill(Color.web("#7f8c8d"));
            canvas.getChildren().add(desc);

            if (list.isEmpty()) {
                Text empty = new Text(START_X, START_Y + 50, "List is empty");
                empty.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
                empty.setFill(Color.web("#95a5a6"));
                canvas.getChildren().add(empty);
                return;
            }

            for (int i = 0; i < list.size(); i++) {
                drawDLLNode(i, list.get(i));
                if (i < list.size() - 1) {
                    drawBidirectionalArrow(i);
                }
            }
        }

        private void drawDLLNode(int index, int value) {
            double x = START_X + index * NODE_SPACING;
            double y = START_Y;

            // Previous pointer
            Rectangle prevRect = new Rectangle(x, y, 25, NODE_SIZE);
            prevRect.setFill(Color.web("#e74c3c"));
            prevRect.setStroke(Color.web("#c0392b"));
            prevRect.setStrokeWidth(2);
            prevRect.setArcWidth(8);
            prevRect.setArcHeight(8);

            // Data part
            Rectangle dataRect = new Rectangle(x + 25, y, NODE_SIZE, NODE_SIZE);
            dataRect.setFill(Color.web("#3498db"));
            dataRect.setStroke(Color.web("#2980b9"));
            dataRect.setStrokeWidth(2);
            dataRect.setArcWidth(8);
            dataRect.setArcHeight(8);

            Text dataText = new Text(x + 25 + NODE_SIZE/2 - 8, y + NODE_SIZE/2 + 5, String.valueOf(value));
            dataText.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            dataText.setFill(Color.WHITE);

            // Next pointer
            Rectangle nextRect = new Rectangle(x + 25 + NODE_SIZE, y, 25, NODE_SIZE);
            nextRect.setFill(Color.web("#e74c3c"));
            nextRect.setStroke(Color.web("#c0392b"));
            nextRect.setStrokeWidth(2);
            nextRect.setArcWidth(8);
            nextRect.setArcHeight(8);

            canvas.getChildren().addAll(prevRect, dataRect, dataText, nextRect);
        }

        private void drawBidirectionalArrow(int index) {
            double startX = START_X + index * NODE_SPACING + 100;
            double startY = START_Y + NODE_SIZE/2;
            double endX = START_X + (index + 1) * NODE_SPACING;
            double endY = START_Y + NODE_SIZE/2;

            // Forward arrow
            Line forwardArrow = new Line(startX, startY - 5, endX - 10, endY - 5);
            forwardArrow.setStroke(Color.web("#27ae60"));
            forwardArrow.setStrokeWidth(2);

            // Backward arrow
            Line backwardArrow = new Line(endX - 10, endY + 5, startX, startY + 5);
            backwardArrow.setStroke(Color.web("#e67e22"));
            backwardArrow.setStrokeWidth(2);

            canvas.getChildren().addAll(forwardArrow, backwardArrow);
        }

        public void insert(int value) {
            list.add(0, value);
            display();
        }

        public void insertAt(int index, int value) {
            if (index < 0 || index > list.size()) {
                throw new RuntimeException("Invalid index!");
            }
            list.add(index, value);
            display();
        }

        public void delete(int value) {
            boolean removed = list.remove(Integer.valueOf(value));
            if (!removed) {
                throw new RuntimeException("Value not found!");
            }
            display();
        }

        public void deleteFirst() {
            if (list.isEmpty()) {
                throw new RuntimeException("List is empty!");
            }
            list.remove(0);
            display();
        }

        public int deleteAt(int index) {
            if (index < 0 || index >= list.size()) {
                throw new RuntimeException("Invalid index!");
            }
            int value = list.remove(index);
            display();
            return value;
        }

        public int search(int value) {
            int index = list.indexOf(value);
            if (index == -1) {
                throw new RuntimeException("Value not found!");
            }
            return index;
        }

        public static int size() {
            return list.size();
        }

        public void clear() {
            list.clear();
            display();
        }
    }

    // Visual Stack Implementation
    class VisualStack {
        private Pane canvas;
        private Stack<Integer> stack;
        private final double RECT_WIDTH = 80;
        private final double RECT_HEIGHT = 40;
        private final double START_X = 200;
        private final double START_Y = 500;

        public VisualStack(Pane canvas) {
            this.canvas = canvas;
            this.stack = new Stack<>();
        }

        public void display() {
            canvas.getChildren().clear();

            // Title
            Text title = new Text(START_X - 50, 80, "📚 Stack (LIFO)");
            title.setFont(Font.font("Arial", FontWeight.BOLD, 18));
            title.setFill(Color.web("#2c3e50"));
            canvas.getChildren().add(title);

            Text desc = new Text(START_X - 50, 105, "Last In, First Out structure");
            desc.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
            desc.setFill(Color.web("#7f8c8d"));
            canvas.getChildren().add(desc);

            // Draw stack elements from bottom to top
            for (int i = 0; i < stack.size(); i++) {
                drawStackElement(i, stack.get(i), i == stack.size() - 1);
            }

            // Draw "TOP" pointer
            if (!stack.isEmpty()) {
                Text topLabel = new Text(START_X + RECT_WIDTH + 20, START_Y - (stack.size() - 1) * (RECT_HEIGHT + 5) + RECT_HEIGHT/2 + 5, "← TOP");
                topLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
                topLabel.setFill(Color.web("#e74c3c"));
                canvas.getChildren().add(topLabel);
            }

            // Draw stack base
            Rectangle base = new Rectangle(START_X - 10, START_Y + RECT_HEIGHT, RECT_WIDTH + 20, 10);
            base.setFill(Color.web("#34495e"));
            canvas.getChildren().add(base);
        }

        private void drawStackElement(int index, int value, boolean isTop) {
            double x = START_X;
            double y = START_Y - index * (RECT_HEIGHT + 5);

            Rectangle rect = new Rectangle(x, y, RECT_WIDTH, RECT_HEIGHT);
            rect.setFill(isTop ? Color.web("#e74c3c") : Color.web("#3498db"));
            rect.setStroke(Color.web("#2c3e50"));
            rect.setStrokeWidth(2);
            rect.setArcWidth(5);
            rect.setArcHeight(5);

            Text text = new Text(x + RECT_WIDTH/2 - 8, y + RECT_HEIGHT/2 + 5, String.valueOf(value));
            text.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            text.setFill(Color.WHITE);

            canvas.getChildren().addAll(rect, text);
        }

        public void push(int value) {
            stack.push(value);
            display();
        }

        public int pop() {
            if (stack.isEmpty()) {
                throw new RuntimeException("Stack is empty!");
            }
            int value = stack.pop();
            display();
            return value;
        }

        public int peek() {
            if (stack.isEmpty()) {
                throw new RuntimeException("Stack is empty!");
            }
            return stack.peek();
        }

        public int search(int value) {
            int position = stack.search(value);
            if (position == -1) {
                throw new RuntimeException("Value not found!");
            }
            return position;
        }

        public int size() {
            return stack.size();
        }

        public void clear() {
            stack.clear();
            display();
        }
    }

    // Visual Queue Implementation
    class VisualQueue {
        private Pane canvas;
        private Queue<Integer> queue;
        private final double RECT_WIDTH = 60;
        private final double RECT_HEIGHT = 40;
        private final double START_X = 80;
        private final double START_Y = 200;

        public VisualQueue(Pane canvas) {
            this.canvas = canvas;
            this.queue = new LinkedList<>();
        }

        public void display() {
            canvas.getChildren().clear();

            // Title
            Text title = new Text(START_X, START_Y - 50, "🚶‍♂️ Queue (FIFO)");
            title.setFont(Font.font("Arial", FontWeight.BOLD, 18));
            title.setFill(Color.web("#2c3e50"));
            canvas.getChildren().add(title);

            Text desc = new Text(START_X, START_Y - 25, "First In, First Out structure");
            desc.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
            desc.setFill(Color.web("#7f8c8d"));
            canvas.getChildren().add(desc);

            if (queue.isEmpty()) {
                Text empty = new Text(START_X, START_Y + 50, "Queue is empty");
                empty.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
                empty.setFill(Color.web("#95a5a6"));
                canvas.getChildren().add(empty);
                return;
            }

            Integer[] queueArray = queue.toArray(new Integer[0]);
            for (int i = 0; i < queueArray.length; i++) {
                drawQueueElement(i, queueArray[i], i == 0, i == queueArray.length - 1);
            }

            // Draw FRONT and REAR labels
            if (!queue.isEmpty()) {
                if (queueArray.length == 1) {
                    // Single element case - stack labels vertically
                    Text frontLabel = new Text(START_X, START_Y - 25, "FRONT");
                    frontLabel.setFont(Font.font("Arial", FontWeight.BOLD, 10));
                    frontLabel.setFill(Color.web("#27ae60"));
                    canvas.getChildren().add(frontLabel);

                    Text rearLabel = new Text(START_X, START_Y - 10, "REAR");
                    rearLabel.setFont(Font.font("Arial", FontWeight.BOLD, 10));
                    rearLabel.setFill(Color.web("#e74c3c"));
                    canvas.getChildren().add(rearLabel);
                } else {
                    // Multiple elements - place normally
                    Text frontLabel = new Text(START_X, START_Y - 15, "FRONT");
                    frontLabel.setFont(Font.font("Arial", FontWeight.BOLD, 10));
                    frontLabel.setFill(Color.web("#27ae60"));
                    canvas.getChildren().add(frontLabel);

                    Text rearLabel = new Text(START_X + (queueArray.length - 1) * (RECT_WIDTH + 10), START_Y - 15, "REAR");
                    rearLabel.setFont(Font.font("Arial", FontWeight.BOLD, 10));
                    rearLabel.setFill(Color.web("#e74c3c"));
                    canvas.getChildren().add(rearLabel);
                }
            }
        }

        private void drawQueueElement(int index, int value, boolean isFront, boolean isRear) {
            double x = START_X + index * (RECT_WIDTH + 10);
            double y = START_Y;

            Rectangle rect = new Rectangle(x, y, RECT_WIDTH, RECT_HEIGHT);
            if (isFront) {
                rect.setFill(Color.web("#27ae60"));
            } else if (isRear) {
                rect.setFill(Color.web("#e74c3c"));
            } else {
                rect.setFill(Color.web("#3498db"));
            }
            rect.setStroke(Color.web("#2c3e50"));
            rect.setStrokeWidth(2);
            rect.setArcWidth(5);
            rect.setArcHeight(5);

            Text text = new Text(x + RECT_WIDTH/2 - 8, y + RECT_HEIGHT/2 + 5, String.valueOf(value));
            text.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            text.setFill(Color.WHITE);

            canvas.getChildren().addAll(rect, text);
        }

        public void enqueue(int value) {
            queue.offer(value);
            display();
        }

        public int dequeue() {
            if (queue.isEmpty()) {
                throw new RuntimeException("Queue is empty!");
            }
            int value = queue.poll();
            display();
            return value;
        }

        public int peek() {
            if (queue.isEmpty()) {
                throw new RuntimeException("Queue is empty!");
            }
            return queue.peek();
        }

        public int search(int value) {
            Integer[] queueArray = queue.toArray(new Integer[0]);
            for (int i = 0; i < queueArray.length; i++) {
                if (queueArray[i].equals(value)) {
                    return i;
                }
            }
            throw new RuntimeException("Value not found!");
        }

        public int size() {
            return queue.size();
        }

        public void clear() {
            queue.clear();
            display();
        }
    }

    // Visual Heap Implementation (Max Heap)
    class VisualHeap {
        private Pane canvas;
        private List<Integer> heap;
        private final double NODE_RADIUS = 25;
        private final double START_X = 400;
        private final double START_Y = 150;
        private final double LEVEL_HEIGHT = 80;

        public VisualHeap(Pane canvas) {
            this.canvas = canvas;
            this.heap = new ArrayList<>();
        }

        public void display() {
            canvas.getChildren().clear();

            // Title
            Text title = new Text(START_X - 100, 80, "🏔️ Max Heap");
            title.setFont(Font.font("Arial", FontWeight.BOLD, 18));
            title.setFill(Color.web("#2c3e50"));
            canvas.getChildren().add(title);

            Text desc = new Text(START_X - 100, 105, "Complete binary tree with max heap property");
            desc.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
            desc.setFill(Color.web("#7f8c8d"));
            canvas.getChildren().add(desc);

            if (heap.isEmpty()) {
                Text empty = new Text(START_X - 50, START_Y + 50, "Heap is empty");
                empty.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
                empty.setFill(Color.web("#95a5a6"));
                canvas.getChildren().add(empty);
                return;
            }

            // Draw edges first
            for (int i = 0; i < heap.size(); i++) {
                int leftChild = 2 * i + 1;
                int rightChild = 2 * i + 2;

                if (leftChild < heap.size()) {
                    drawHeapEdge(i, leftChild);
                }
                if (rightChild < heap.size()) {
                    drawHeapEdge(i, rightChild);
                }
            }

            // Draw nodes
            for (int i = 0; i < heap.size(); i++) {
                drawHeapNode(i, heap.get(i), i == 0);
            }
        }

        private void drawHeapNode(int index, int value, boolean isRoot) {
            int level = (int) (Math.log(index + 1) / Math.log(2));
            int positionInLevel = index - (int) (Math.pow(2, level) - 1);
            int nodesInLevel = (int) Math.pow(2, level);

            double x = START_X + (positionInLevel - nodesInLevel / 2.0 + 0.5) * 100;
            double y = START_Y + level * LEVEL_HEIGHT;

            Circle circle = new Circle(x, y, NODE_RADIUS);
            circle.setFill(isRoot ? Color.web("#e74c3c") : Color.web("#3498db"));
            circle.setStroke(Color.web("#2c3e50"));
            circle.setStrokeWidth(2);

            Text text = new Text(x - 8, y + 5, String.valueOf(value));
            text.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            text.setFill(Color.WHITE);

            canvas.getChildren().addAll(circle, text);
        }

        private void drawHeapEdge(int parent, int child) {
            int parentLevel = (int) (Math.log(parent + 1) / Math.log(2));
            int parentPosInLevel = parent - (int) (Math.pow(2, parentLevel) - 1);
            int parentNodesInLevel = (int) Math.pow(2, parentLevel);

            int childLevel = (int) (Math.log(child + 1) / Math.log(2));
            int childPosInLevel = child - (int) (Math.pow(2, childLevel) - 1);
            int childNodesInLevel = (int) Math.pow(2, childLevel);

            double parentX = START_X + (parentPosInLevel - parentNodesInLevel / 2.0 + 0.5) * 100;
            double parentY = START_Y + parentLevel * LEVEL_HEIGHT;

            double childX = START_X + (childPosInLevel - childNodesInLevel / 2.0 + 0.5) * 100;
            double childY = START_Y + childLevel * LEVEL_HEIGHT;

            Line line = new Line(parentX, parentY + NODE_RADIUS, childX, childY - NODE_RADIUS);
            line.setStroke(Color.web("#34495e"));
            line.setStrokeWidth(2);

            canvas.getChildren().add(line);
        }

        public void insert(int value) {
            heap.add(value);
            heapifyUp(heap.size() - 1);
            display();
        }

        public int extractMax() {
            if (heap.isEmpty()) {
                throw new RuntimeException("Heap is empty!");
            }
            int max = heap.get(0);
            heap.set(0, heap.get(heap.size() - 1));
            heap.remove(heap.size() - 1);
            if (!heap.isEmpty()) {
                heapifyDown(0);
            }
            display();
            return max;
        }

        public int peekMax() {
            if (heap.isEmpty()) {
                throw new RuntimeException("Heap is empty!");
            }
            return heap.get(0);
        }

        public boolean search(int value) {
            return heap.contains(value);
        }

        public int size() {
            return heap.size();
        }

        public void clear() {
            heap.clear();
            display();
        }

        private void heapifyUp(int index) {
            while (index > 0) {
                int parentIndex = (index - 1) / 2;
                if (heap.get(index) <= heap.get(parentIndex)) {
                    break;
                }
                Collections.swap(heap, index, parentIndex);
                index = parentIndex;
            }
        }

        private void heapifyDown(int index) {
            while (true) {
                int leftChild = 2 * index + 1;
                int rightChild = 2 * index + 2;
                int largest = index;

                if (leftChild < heap.size() && heap.get(leftChild) > heap.get(largest)) {
                    largest = leftChild;
                }
                if (rightChild < heap.size() && heap.get(rightChild) > heap.get(largest)) {
                    largest = rightChild;
                }
                if (largest == index) {
                    break;
                }
                Collections.swap(heap, index, largest);
                index = largest;
            }
        }
    }

    // Visual Binary Search Tree Implementation
    class VisualBST {
        private Pane canvas;
        private TreeNode root;
        private final double NODE_RADIUS = 25;
        private final double START_X = 400;
        private final double START_Y = 150;
        private final double LEVEL_HEIGHT = 80;
        private final double HORIZONTAL_SPACING = 60;

        private class TreeNode {
            int val;
            TreeNode left, right;

            TreeNode(int val) {
                this.val = val;
            }
        }

        public VisualBST(Pane canvas) {
            this.canvas = canvas;
            this.root = null;
        }

        public void display() {
            canvas.getChildren().clear();

            // Title
            Text title = new Text(START_X - 100, 80, "🌳 Binary Search Tree");
            title.setFont(Font.font("Arial", FontWeight.BOLD, 18));
            title.setFill(Color.web("#2c3e50"));
            canvas.getChildren().add(title);

            Text desc = new Text(START_X - 100, 105, "Left < Root < Right property maintained");
            desc.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
            desc.setFill(Color.web("#7f8c8d"));
            canvas.getChildren().add(desc);

            if (root == null) {
                Text empty = new Text(START_X - 50, START_Y + 50, "BST is empty");
                empty.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
                empty.setFill(Color.web("#95a5a6"));
                canvas.getChildren().add(empty);
                return;
            }

            // Draw the tree
            drawBSTRecursive(root, START_X, START_Y, HORIZONTAL_SPACING * 2, 0);
        }

        private void drawBSTRecursive(TreeNode node, double x, double y, double horizontalGap, int level) {
            if (node == null) return;

            // Draw edges to children first
            if (node.left != null) {
                double leftX = x - horizontalGap;
                double leftY = y + LEVEL_HEIGHT;
                Line leftEdge = new Line(x - NODE_RADIUS * 0.7, y + NODE_RADIUS * 0.7,
                        leftX + NODE_RADIUS * 0.7, leftY - NODE_RADIUS * 0.7);
                leftEdge.setStroke(Color.web("#34495e"));
                leftEdge.setStrokeWidth(2);
                canvas.getChildren().add(leftEdge);
            }

            if (node.right != null) {
                double rightX = x + horizontalGap;
                double rightY = y + LEVEL_HEIGHT;
                Line rightEdge = new Line(x + NODE_RADIUS * 0.7, y + NODE_RADIUS * 0.7,
                        rightX - NODE_RADIUS * 0.7, rightY - NODE_RADIUS * 0.7);
                rightEdge.setStroke(Color.web("#34495e"));
                rightEdge.setStrokeWidth(2);
                canvas.getChildren().add(rightEdge);
            }

            // Draw current node
            Circle circle = new Circle(x, y, NODE_RADIUS);
            circle.setFill(level == 0 ? Color.web("#e74c3c") : Color.web("#3498db"));
            circle.setStroke(Color.web("#2c3e50"));
            circle.setStrokeWidth(2);

            Text text = new Text(x - 8, y + 5, String.valueOf(node.val));
            text.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            text.setFill(Color.WHITE);

            canvas.getChildren().addAll(circle, text);

            // Recursively draw children
            double nextGap = horizontalGap * 0.6;
            if (node.left != null) {
                drawBSTRecursive(node.left, x - horizontalGap, y + LEVEL_HEIGHT, nextGap, level + 1);
            }
            if (node.right != null) {
                drawBSTRecursive(node.right, x + horizontalGap, y + LEVEL_HEIGHT, nextGap, level + 1);
            }
        }

        public void insert(int value) {
            root = insertRecursive(root, value);
            display();
        }

        private TreeNode insertRecursive(TreeNode node, int value) {
            if (node == null) {
                return new TreeNode(value);
            }

            if (value < node.val) {
                node.left = insertRecursive(node.left, value);
            } else if (value > node.val) {
                node.right = insertRecursive(node.right, value);
            }
            // Duplicate values are ignored

            return node;
        }

        public void delete(int value) {
            if (root == null) {
                throw new RuntimeException("BST is empty!");
            }
            TreeNode temp = root;
            root = deleteRecursive(root, value);
            if (temp == root && (root == null || !searchRecursive(root, value))) {
                // Value was not found and nothing was deleted
                throw new RuntimeException("Value not found!");
            }
            display();
        }

        private TreeNode deleteRecursive(TreeNode node, int value) {
            if (node == null) {
                return null;
            }

            if (value < node.val) {
                node.left = deleteRecursive(node.left, value);
            } else if (value > node.val) {
                node.right = deleteRecursive(node.right, value);
            } else {
                // Node to be deleted found
                if (node.left == null) {
                    return node.right;
                } else if (node.right == null) {
                    return node.left;
                }

                // Node with two children
                TreeNode minNode = findMin(node.right);
                node.val = minNode.val;
                node.right = deleteRecursive(node.right, minNode.val);
            }

            return node;
        }

        private TreeNode findMin(TreeNode node) {
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }

        public boolean search(int value) {
            return searchRecursive(root, value);
        }

        private boolean searchRecursive(TreeNode node, int value) {
            if (node == null) {
                return false;
            }

            if (value == node.val) {
                return true;
            } else if (value < node.val) {
                return searchRecursive(node.left, value);
            } else {
                return searchRecursive(node.right, value);
            }
        }

        public int size() {
            return sizeRecursive(root);
        }

        private int sizeRecursive(TreeNode node) {
            if (node == null) {
                return 0;
            }
            return 1 + sizeRecursive(node.left) + sizeRecursive(node.right);
        }

        public int[] getMinMax() {
            if (root == null) {
                return null;
            }
            int min = findMinValue(root);
            int max = findMaxValue(root);
            return new int[]{min, max};
        }

        private int findMinValue(TreeNode node) {
            while (node.left != null) {
                node = node.left;
            }
            return node.val;
        }

        private int findMaxValue(TreeNode node) {
            while (node.right != null) {
                node = node.right;
            }
            return node.val;
        }

        public void clear() {
            root = null;
            display();
        }
    }
}
