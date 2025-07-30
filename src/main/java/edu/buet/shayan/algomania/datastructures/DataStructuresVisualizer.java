////package edu.buet.shayan.datastructuresvisualizer;
////
////import javafx.application.Application;
////import javafx.geometry.Insets;
////import javafx.geometry.Pos;
////import javafx.scene.Scene;
////import javafx.scene.control.*;
////import javafx.scene.layout.*;
////import javafx.scene.paint.Color;
////import javafx.scene.shape.Circle;
////import javafx.scene.shape.Line;
////import javafx.scene.shape.Rectangle;
////import javafx.scene.text.Font;
////import javafx.scene.text.Text;
////import javafx.stage.Stage;
////import javafx.animation.TranslateTransition;
////import javafx.animation.FadeTransition;
////import javafx.animation.ScaleTransition;
////import javafx.animation.ParallelTransition;
////import javafx.util.Duration;
////import java.util.*;
////
////public class DataStructuresVisualizer extends Application {
////
////    private Pane canvas;
////    private ComboBox<String> structureSelector;
////    private TextField inputField;
////    private VBox controlPanel;
////
////    // Data structure implementations
////    private VisualArray visualArray;
////    private VisualSLL visualSLL;
////    private VisualDLL visualDLL;
////    private VisualStack visualStack;
////    private VisualQueue visualQueue;
////    private VisualHeap visualHeap;
////    private VisualBST visualBST;
////
////    private String currentStructure = "Array";
////
////    @Override
////    public void start(Stage primaryStage) {
////        BorderPane root = new BorderPane();
////
////        // Initialize canvas
////        canvas = new Pane();
////        canvas.setPrefSize(800, 600);
////        canvas.setStyle("-fx-background-color: #f0f0f0; -fx-border-color: #ccc;");
////
////        // Initialize control panel
////        setupControlPanel();
////
////        // Initialize data structures
////        initializeDataStructures();
////
////        root.setCenter(canvas);
////        root.setRight(controlPanel);
////
////        Scene scene = new Scene(root, 1200, 700);
////        primaryStage.setTitle("Data Structures Visualizer");
////        primaryStage.setScene(scene);
////        primaryStage.show();
////
////        // Show initial structure
////        showStructure(currentStructure);
////    }
////
////    private void setupControlPanel() {
////        controlPanel = new VBox(10);
////        controlPanel.setPadding(new Insets(20));
////        controlPanel.setPrefWidth(300);
////        controlPanel.setStyle("-fx-background-color: white; -fx-border-color: #ccc;");
////
////        Label title = new Label("Data Structure Visualizer");
////        title.setFont(Font.font(16));
////        title.setStyle("-fx-font-weight: bold;");
////
////        // Structure selector
////        structureSelector = new ComboBox<>();
////        structureSelector.getItems().addAll("Array", "Singly Linked List", "Doubly Linked List",
////                "Stack", "Queue", "Heap", "Binary Search Tree");
////        structureSelector.setValue("Array");
////        structureSelector.setOnAction(e -> {
////            currentStructure = structureSelector.getValue();
////            showStructure(currentStructure);
////        });
////
////        // Input field
////        inputField = new TextField();
////        inputField.setPromptText("Enter value");
////
////        // Buttons
////        Button insertBtn = new Button("Insert");
////        Button deleteBtn = new Button("Delete");
////        Button searchBtn = new Button("Search");
////        Button clearBtn = new Button("Clear");
////
////        insertBtn.setOnAction(e -> performOperation("insert"));
////        deleteBtn.setOnAction(e -> performOperation("delete"));
////        searchBtn.setOnAction(e -> performOperation("search"));
////        clearBtn.setOnAction(e -> performOperation("clear"));
////
////        // Make buttons full width
////        insertBtn.setMaxWidth(Double.MAX_VALUE);
////        deleteBtn.setMaxWidth(Double.MAX_VALUE);
////        searchBtn.setMaxWidth(Double.MAX_VALUE);
////        clearBtn.setMaxWidth(Double.MAX_VALUE);
////
////        controlPanel.getChildren().addAll(title, new Separator(),
////                new Label("Select Structure:"), structureSelector,
////                new Label("Input:"), inputField,
////                insertBtn, deleteBtn, searchBtn, clearBtn);
////    }
////
////    private void initializeDataStructures() {
////        visualArray = new VisualArray(canvas);
////        visualSLL = new VisualSLL(canvas);
////        visualDLL = new VisualDLL(canvas);
////        visualStack = new VisualStack(canvas);
////        visualQueue = new VisualQueue(canvas);
////        visualHeap = new VisualHeap(canvas);
////        visualBST = new VisualBST(canvas);
////    }
////
////    private void showStructure(String structure) {
////        canvas.getChildren().clear();
////
////        switch (structure) {
////            case "Array":
////                visualArray.display();
////                break;
////            case "Singly Linked List":
////                visualSLL.display();
////                break;
////            case "Doubly Linked List":
////                visualDLL.display();
////                break;
////            case "Stack":
////                visualStack.display();
////                break;
////            case "Queue":
////                visualQueue.display();
////                break;
////            case "Heap":
////                visualHeap.display();
////                break;
////            case "Binary Search Tree":
////                visualBST.display();
////                break;
////        }
////    }
////
////    private void performOperation(String operation) {
////        String input = inputField.getText().trim();
////
////        try {
////            switch (currentStructure) {
////                case "Array":
////                    performArrayOperation(operation, input);
////                    break;
////                case "Singly Linked List":
////                    performSLLOperation(operation, input);
////                    break;
////                case "Doubly Linked List":
////                    performDLLOperation(operation, input);
////                    break;
////                case "Stack":
////                    performStackOperation(operation, input);
////                    break;
////                case "Queue":
////                    performQueueOperation(operation, input);
////                    break;
////                case "Heap":
////                    performHeapOperation(operation, input);
////                    break;
////                case "Binary Search Tree":
////                    performBSTOperation(operation, input);
////                    break;
////            }
////            inputField.clear();
////        } catch (NumberFormatException e) {
////            showAlert("Invalid input. Please enter a valid number.");
////        } catch (Exception e) {
////            showAlert("Error: " + e.getMessage());
////        }
////    }
////
////    private void performArrayOperation(String operation, String input) {
////        switch (operation) {
////            case "insert":
////                if (!input.isEmpty()) {
////                    visualArray.insert(Integer.parseInt(input));
////                }
////                break;
////            case "delete":
////                if (!input.isEmpty()) {
////                    visualArray.delete(Integer.parseInt(input));
////                } else {
////                    visualArray.deleteLast();
////                }
////                break;
////            case "search":
////                if (!input.isEmpty()) {
////                    visualArray.search(Integer.parseInt(input));
////                }
////                break;
////            case "clear":
////                visualArray.clear();
////                break;
////        }
////    }
////
////    private void performSLLOperation(String operation, String input) {
////        switch (operation) {
////            case "insert":
////                if (!input.isEmpty()) {
////                    visualSLL.insert(Integer.parseInt(input));
////                }
////                break;
////            case "delete":
////                if (!input.isEmpty()) {
////                    visualSLL.delete(Integer.parseInt(input));
////                } else {
////                    visualSLL.deleteFirst();
////                }
////                break;
////            case "search":
////                if (!input.isEmpty()) {
////                    visualSLL.search(Integer.parseInt(input));
////                }
////                break;
////            case "clear":
////                visualSLL.clear();
////                break;
////        }
////    }
////
////    private void performDLLOperation(String operation, String input) {
////        switch (operation) {
////            case "insert":
////                if (!input.isEmpty()) {
////                    visualDLL.insert(Integer.parseInt(input));
////                }
////                break;
////            case "delete":
////                if (!input.isEmpty()) {
////                    visualDLL.delete(Integer.parseInt(input));
////                } else {
////                    visualDLL.deleteFirst();
////                }
////                break;
////            case "search":
////                if (!input.isEmpty()) {
////                    visualDLL.search(Integer.parseInt(input));
////                }
////                break;
////            case "clear":
////                visualDLL.clear();
////                break;
////        }
////    }
////
////    private void performStackOperation(String operation, String input) {
////        switch (operation) {
////            case "insert":
////                if (!input.isEmpty()) {
////                    visualStack.push(Integer.parseInt(input));
////                }
////                break;
////            case "delete":
////                visualStack.pop();
////                break;
////            case "search":
////                if (!input.isEmpty()) {
////                    visualStack.search(Integer.parseInt(input));
////                }
////                break;
////            case "clear":
////                visualStack.clear();
////                break;
////        }
////    }
////
////    private void performQueueOperation(String operation, String input) {
////        switch (operation) {
////            case "insert":
////                if (!input.isEmpty()) {
////                    visualQueue.enqueue(Integer.parseInt(input));
////                }
////                break;
////            case "delete":
////                visualQueue.dequeue();
////                break;
////            case "search":
////                if (!input.isEmpty()) {
////                    visualQueue.search(Integer.parseInt(input));
////                }
////                break;
////            case "clear":
////                visualQueue.clear();
////                break;
////        }
////    }
////
////    private void performHeapOperation(String operation, String input) {
////        switch (operation) {
////            case "insert":
////                if (!input.isEmpty()) {
////                    visualHeap.insert(Integer.parseInt(input));
////                }
////                break;
////            case "delete":
////                visualHeap.extractMax();
////                break;
////            case "search":
////                if (!input.isEmpty()) {
////                    visualHeap.search(Integer.parseInt(input));
////                }
////                break;
////            case "clear":
////                visualHeap.clear();
////                break;
////        }
////    }
////
////    private void performBSTOperation(String operation, String input) {
////        switch (operation) {
////            case "insert":
////                if (!input.isEmpty()) {
////                    visualBST.insert(Integer.parseInt(input));
////                }
////                break;
////            case "delete":
////                if (!input.isEmpty()) {
////                    visualBST.delete(Integer.parseInt(input));
////                }
////                break;
////            case "search":
////                if (!input.isEmpty()) {
////                    visualBST.search(Integer.parseInt(input));
////                }
////                break;
////            case "clear":
////                visualBST.clear();
////                break;
////        }
////    }
////
////    private void showAlert(String message) {
////        Alert alert = new Alert(Alert.AlertType.WARNING);
////        alert.setTitle("Warning");
////        alert.setHeaderText(null);
////        alert.setContentText(message);
////        alert.showAndWait();
////    }
////
////    public static void main(String[] args) {
////        launch(args);
////    }
////}
////
////// Visual Array Implementation
////class VisualArray {
////    private Pane canvas;
////    private List<Integer> array;
////    private List<Rectangle> rectangles;
////    private List<Text> texts;
////    private final int MAX_SIZE = 10;
////    private final double RECT_WIDTH = 60;
////    private final double RECT_HEIGHT = 40;
////    private final double START_X = 50;
////    private final double START_Y = 100;
////
////    public VisualArray(Pane canvas) {
////        this.canvas = canvas;
////        this.array = new ArrayList<>();
////        this.rectangles = new ArrayList<>();
////        this.texts = new ArrayList<>();
////    }
////
////    public void display() {
////        canvas.getChildren().clear();
////        rectangles.clear();
////        texts.clear();
////
////        // Draw title
////        Text title = new Text(START_X, START_Y - 30, "Array Visualization");
////        title.setFont(Font.font(16));
////        canvas.getChildren().add(title);
////
////        // Draw array elements
////        for (int i = 0; i < array.size(); i++) {
////            drawElement(i, array.get(i));
////        }
////
////        // Draw empty slots
////        for (int i = array.size(); i < MAX_SIZE; i++) {
////            drawEmptySlot(i);
////        }
////
////        // Draw indices
////        for (int i = 0; i < MAX_SIZE; i++) {
////            Text indexText = new Text(START_X + i * (RECT_WIDTH + 5) + RECT_WIDTH/2 - 5,
////                    START_Y + RECT_HEIGHT + 20, String.valueOf(i));
////            indexText.setFont(Font.font(12));
////            canvas.getChildren().add(indexText);
////        }
////    }
////
////    private void drawElement(int index, int value) {
////        double x = START_X + index * (RECT_WIDTH + 5);
////        double y = START_Y;
////
////        Rectangle rect = new Rectangle(x, y, RECT_WIDTH, RECT_HEIGHT);
////        rect.setFill(Color.LIGHTBLUE);
////        rect.setStroke(Color.BLACK);
////        rectangles.add(rect);
////
////        Text text = new Text(x + RECT_WIDTH/2 - 5, y + RECT_HEIGHT/2 + 5, String.valueOf(value));
////        text.setFont(Font.font(14));
////        texts.add(text);
////
////        canvas.getChildren().addAll(rect, text);
////    }
////
////    private void drawEmptySlot(int index) {
////        double x = START_X + index * (RECT_WIDTH + 5);
////        double y = START_Y;
////
////        Rectangle rect = new Rectangle(x, y, RECT_WIDTH, RECT_HEIGHT);
////        rect.setFill(Color.WHITE);
////        rect.setStroke(Color.GRAY);
////        rect.getStrokeDashArray().addAll(5d, 5d);
////
////        canvas.getChildren().add(rect);
////    }
////
////    public void insert(int value) {
////        if (array.size() >= MAX_SIZE) {
////            throw new RuntimeException("Array is full!");
////        }
////        array.add(value);
////        display();
////        highlightElement(array.size() - 1, Color.LIGHTGREEN);
////    }
////
////    public void delete(int value) {
////        int index = array.indexOf(value);
////        if (index != -1) {
////            highlightElement(index, Color.LIGHTCORAL);
////            array.remove(index);
////
////            // Animate removal
////            TranslateTransition tt = new TranslateTransition(Duration.millis(300));
////            tt.setOnFinished(e -> display());
////            tt.play();
////        } else {
////            throw new RuntimeException("Element not found!");
////        }
////    }
////
////    public void deleteLast() {
////        if (array.isEmpty()) {
////            throw new RuntimeException("Array is empty!");
////        }
////        highlightElement(array.size() - 1, Color.LIGHTCORAL);
////        array.remove(array.size() - 1);
////
////        TranslateTransition tt = new TranslateTransition(Duration.millis(300));
////        tt.setOnFinished(e -> display());
////        tt.play();
////    }
////
////    public void search(int value) {
////        for (int i = 0; i < array.size(); i++) {
////            if (array.get(i) == value) {
////                highlightElement(i, Color.YELLOW);
////                return;
////            }
////        }
////        throw new RuntimeException("Element not found!");
////    }
////
////    public void clear() {
////        array.clear();
////        display();
////    }
////
////    private void highlightElement(int index, Color color) {
////        if (index < rectangles.size()) {
////            rectangles.get(index).setFill(color);
////
////            ScaleTransition st = new ScaleTransition(Duration.millis(200), rectangles.get(index));
////            st.setFromX(1.0);
////            st.setFromY(1.0);
////            st.setToX(1.2);
////            st.setToY(1.2);
////            st.setAutoReverse(true);
////            st.setCycleCount(2);
////            st.play();
////        }
////    }
////}
////
////// Visual Singly Linked List Implementation
////class VisualSLL {
////    private Pane canvas;
////    private Node head;
////    private final double NODE_RADIUS = 25;
////    private final double START_X = 50;
////    private final double START_Y = 150;
////    private final double NODE_SPACING = 120;
////
////    class Node {
////        int data;
////        Node next;
////        Circle circle;
////        Text text;
////        Line arrow;
////
////        Node(int data) {
////            this.data = data;
////            this.next = null;
////        }
////    }
////
////    public VisualSLL(Pane canvas) {
////        this.canvas = canvas;
////        this.head = null;
////    }
////
////    public void display() {
////        canvas.getChildren().clear();
////
////        Text title = new Text(START_X, START_Y - 50, "Singly Linked List Visualization");
////        title.setFont(Font.font(16));
////        canvas.getChildren().add(title);
////
////        if (head == null) {
////            Text emptyText = new Text(START_X, START_Y, "List is empty");
////            canvas.getChildren().add(emptyText);
////            return;
////        }
////
////        Node current = head;
////        int position = 0;
////
////        while (current != null) {
////            drawNode(current, position);
////            current = current.next;
////            position++;
////        }
////    }
////
////    private void drawNode(Node node, int position) {
////        double x = START_X + position * NODE_SPACING;
////        double y = START_Y;
////
////        // Draw circle
////        Circle circle = new Circle(x, y, NODE_RADIUS);
////        circle.setFill(Color.LIGHTBLUE);
////        circle.setStroke(Color.BLACK);
////        node.circle = circle;
////
////        // Draw text
////        Text text = new Text(x - 5, y + 5, String.valueOf(node.data));
////        text.setFont(Font.font(14));
////        node.text = text;
////
////        canvas.getChildren().addAll(circle, text);
////
////        // Draw arrow to next node
////        if (node.next != null) {
////            Line arrow = new Line(x + NODE_RADIUS, y, x + NODE_SPACING - NODE_RADIUS, y);
////            arrow.setStroke(Color.BLACK);
////            arrow.setStrokeWidth(2);
////            node.arrow = arrow;
////
////            // Arrow head
////            Line arrowHead1 = new Line(x + NODE_SPACING - NODE_RADIUS - 10, y - 5,
////                    x + NODE_SPACING - NODE_RADIUS, y);
////            Line arrowHead2 = new Line(x + NODE_SPACING - NODE_RADIUS - 10, y + 5,
////                    x + NODE_SPACING - NODE_RADIUS, y);
////
////            canvas.getChildren().addAll(arrow, arrowHead1, arrowHead2);
////        }
////    }
////
////    public void insert(int data) {
////        Node newNode = new Node(data);
////        if (head == null) {
////            head = newNode;
////        } else {
////            Node current = head;
////            while (current.next != null) {
////                current = current.next;
////            }
////            current.next = newNode;
////        }
////        display();
////        highlightLastNode();
////    }
////
////    public void delete(int data) {
////        if (head == null) {
////            throw new RuntimeException("List is empty!");
////        }
////
////        if (head.data == data) {
////            head = head.next;
////            display();
////            return;
////        }
////
////        Node current = head;
////        while (current.next != null && current.next.data != data) {
////            current = current.next;
////        }
////
////        if (current.next == null) {
////            throw new RuntimeException("Element not found!");
////        }
////
////        current.next = current.next.next;
////        display();
////    }
////
////    public void deleteFirst() {
////        if (head == null) {
////            throw new RuntimeException("List is empty!");
////        }
////        head = head.next;
////        display();
////    }
////
////    public void search(int data) {
////        Node current = head;
////        int position = 0;
////
////        while (current != null) {
////            if (current.data == data) {
////                display();
////                highlightNode(position);
////                return;
////            }
////            current = current.next;
////            position++;
////        }
////        throw new RuntimeException("Element not found!");
////    }
////
////    public void clear() {
////        head = null;
////        display();
////    }
////
////    private void highlightLastNode() {
////        Node current = head;
////        int position = 0;
////
////        while (current.next != null) {
////            current = current.next;
////            position++;
////        }
////
////        highlightNode(position);
////    }
////
////    private void highlightNode(int position) {
////        double x = START_X + position * NODE_SPACING;
////        double y = START_Y;
////
////        Circle highlight = new Circle(x, y, NODE_RADIUS);
////        highlight.setFill(Color.YELLOW);
////        highlight.setStroke(Color.ORANGE);
////        highlight.setStrokeWidth(3);
////
////        canvas.getChildren().add(highlight);
////
////        ScaleTransition st = new ScaleTransition(Duration.millis(500), highlight);
////        st.setFromX(1.0);
////        st.setFromY(1.0);
////        st.setToX(1.3);
////        st.setToY(1.3);
////        st.setAutoReverse(true);
////        st.setCycleCount(2);
////        st.setOnFinished(e -> canvas.getChildren().remove(highlight));
////        st.play();
////    }
////}
////
////// Visual Doubly Linked List Implementation
////class VisualDLL {
////    private Pane canvas;
////    private DNode head;
////    private final double NODE_RADIUS = 25;
////    private final double START_X = 50;
////    private final double START_Y = 200;
////    private final double NODE_SPACING = 140;
////
////    class DNode {
////        int data;
////        DNode next;
////        DNode prev;
////        Circle circle;
////        Text text;
////        Line forwardArrow;
////        Line backwardArrow;
////
////        DNode(int data) {
////            this.data = data;
////            this.next = null;
////            this.prev = null;
////        }
////    }
////
////    public VisualDLL(Pane canvas) {
////        this.canvas = canvas;
////        this.head = null;
////    }
////
////    public void display() {
////        canvas.getChildren().clear();
////
////        Text title = new Text(START_X, START_Y - 50, "Doubly Linked List Visualization");
////        title.setFont(Font.font(16));
////        canvas.getChildren().add(title);
////
////        if (head == null) {
////            Text emptyText = new Text(START_X, START_Y, "List is empty");
////            canvas.getChildren().add(emptyText);
////            return;
////        }
////
////        DNode current = head;
////        int position = 0;
////
////        while (current != null) {
////            drawDNode(current, position);
////            current = current.next;
////            position++;
////        }
////    }
////
////    private void drawDNode(DNode node, int position) {
////        double x = START_X + position * NODE_SPACING;
////        double y = START_Y;
////
////        // Draw circle
////        Circle circle = new Circle(x, y, NODE_RADIUS);
////        circle.setFill(Color.LIGHTGREEN);
////        circle.setStroke(Color.BLACK);
////        node.circle = circle;
////
////        // Draw text
////        Text text = new Text(x - 5, y + 5, String.valueOf(node.data));
////        text.setFont(Font.font(14));
////        node.text = text;
////
////        canvas.getChildren().addAll(circle, text);
////
////        // Draw forward arrow
////        if (node.next != null) {
////            Line forwardArrow = new Line(x + NODE_RADIUS, y - 5,
////                    x + NODE_SPACING - NODE_RADIUS, y - 5);
////            forwardArrow.setStroke(Color.BLUE);
////            forwardArrow.setStrokeWidth(2);
////
////            // Forward arrow head
////            Line fArrowHead1 = new Line(x + NODE_SPACING - NODE_RADIUS - 10, y - 10,
////                    x + NODE_SPACING - NODE_RADIUS, y - 5);
////            Line fArrowHead2 = new Line(x + NODE_SPACING - NODE_RADIUS - 10, y,
////                    x + NODE_SPACING - NODE_RADIUS, y - 5);
////            fArrowHead1.setStroke(Color.BLUE);
////            fArrowHead2.setStroke(Color.BLUE);
////
////            canvas.getChildren().addAll(forwardArrow, fArrowHead1, fArrowHead2);
////        }
////
////        // Draw backward arrow
////        if (node.prev != null) {
////            Line backwardArrow = new Line(x - NODE_RADIUS, y + 5,
////                    x - NODE_SPACING + NODE_RADIUS, y + 5);
////            backwardArrow.setStroke(Color.RED);
////            backwardArrow.setStrokeWidth(2);
////
////            // Backward arrow head
////            Line bArrowHead1 = new Line(x - NODE_SPACING + NODE_RADIUS + 10, y,
////                    x - NODE_SPACING + NODE_RADIUS, y + 5);
////            Line bArrowHead2 = new Line(x - NODE_SPACING + NODE_RADIUS + 10, y + 10,
////                    x - NODE_SPACING + NODE_RADIUS, y + 5);
////            bArrowHead1.setStroke(Color.RED);
////            bArrowHead2.setStroke(Color.RED);
////
////            canvas.getChildren().addAll(backwardArrow, bArrowHead1, bArrowHead2);
////        }
////    }
////
////    public void insert(int data) {
////        DNode newNode = new DNode(data);
////        if (head == null) {
////            head = newNode;
////        } else {
////            DNode current = head;
////            while (current.next != null) {
////                current = current.next;
////            }
////            current.next = newNode;
////            newNode.prev = current;
////        }
////        display();
////    }
////
////    public void delete(int data) {
////        if (head == null) {
////            throw new RuntimeException("List is empty!");
////        }
////
////        DNode current = head;
////        while (current != null && current.data != data) {
////            current = current.next;
////        }
////
////        if (current == null) {
////            throw new RuntimeException("Element not found!");
////        }
////
////        if (current.prev != null) {
////            current.prev.next = current.next;
////        } else {
////            head = current.next;
////        }
////
////        if (current.next != null) {
////            current.next.prev = current.prev;
////        }
////
////        display();
////    }
////
////    public void deleteFirst() {
////        if (head == null) {
////            throw new RuntimeException("List is empty!");
////        }
////        head = head.next;
////        if (head != null) {
////            head.prev = null;
////        }
////        display();
////    }
////
////    public void search(int data) {
////        DNode current = head;
////        int position = 0;
////
////        while (current != null) {
////            if (current.data == data) {
////                display();
////                highlightNode(position);
////                return;
////            }
////            current = current.next;
////            position++;
////        }
////        throw new RuntimeException("Element not found!");
////    }
////
////    public void clear() {
////        head = null;
////        display();
////    }
////
////    private void highlightNode(int position) {
////        double x = START_X + position * NODE_SPACING;
////        double y = START_Y;
////
////        Circle highlight = new Circle(x, y, NODE_RADIUS);
////        highlight.setFill(Color.YELLOW);
////        highlight.setStroke(Color.ORANGE);
////        highlight.setStrokeWidth(3);
////
////        canvas.getChildren().add(highlight);
////
////        FadeTransition ft = new FadeTransition(Duration.millis(1000), highlight);
////        ft.setFromValue(1.0);
////        ft.setToValue(0.0);
////        ft.setAutoReverse(true);
////        ft.setCycleCount(4);
////        ft.setOnFinished(e -> canvas.getChildren().remove(highlight));
////        ft.play();
////    }
////}
////
////// Visual Stack Implementation
////class VisualStack {
////    private Pane canvas;
////    private List<Integer> stack;
////    private final double RECT_WIDTH = 80;
////    private final double RECT_HEIGHT = 40;
////    private final double START_X = 150;
////    private final double START_Y = 500;
////
////    public VisualStack(Pane canvas) {
////        this.canvas = canvas;
////        this.stack = new ArrayList<>();
////    }
////
////    public void display() {
////        canvas.getChildren().clear();
////
////        Text title = new Text(START_X, START_Y - stack.size() * (RECT_HEIGHT + 5) - 50,
////                "Stack Visualization (LIFO)");
////        title.setFont(Font.font(16));
////        canvas.getChildren().add(title);
////
////        // Draw base
////        Rectangle base = new Rectangle(START_X - 10, START_Y + 10, RECT_WIDTH + 20, 10);
////        base.setFill(Color.DARKGRAY);
////        canvas.getChildren().add(base);
////
////        // Draw stack elements from bottom to top
////        for (int i = 0; i < stack.size(); i++) {
////            double y = START_Y - i * (RECT_HEIGHT + 5);
////
////            Rectangle rect = new Rectangle(START_X, y - RECT_HEIGHT, RECT_WIDTH, RECT_HEIGHT);
////            rect.setFill(i == stack.size() - 1 ? Color.LIGHTCORAL : Color.LIGHTBLUE);
////            rect.setStroke(Color.BLACK);
////
////            Text text = new Text(START_X + RECT_WIDTH/2 - 5, y - RECT_HEIGHT/2 + 5,
////                    String.valueOf(stack.get(i)));
////            text.setFont(Font.font(14));
////
////            canvas.getChildren().addAll(rect, text);
////        }
////
////        // Draw "TOP" indicator
////        if (!stack.isEmpty()) {
////            Text topLabel = new Text(START_X + RECT_WIDTH + 20,
////                    START_Y - (stack.size() - 1) * (RECT_HEIGHT + 5) - RECT_HEIGHT/2 + 5,
////                    "← TOP");
////            topLabel.setFont(Font.font(12));
////            topLabel.setFill(Color.RED);
////            canvas.getChildren().add(topLabel);
////        }
////    }
////
////    public void push(int data) {
////        stack.add(data);
////        display();
////        animateNewElement();
////    }
////
////    public void pop() {
////        if (stack.isEmpty()) {
////            throw new RuntimeException("Stack is empty!");
////        }
////        stack.remove(stack.size() - 1);
////        display();
////    }
////
////    public void search(int data) {
////        for (int i = stack.size() - 1; i >= 0; i--) {
////            if (stack.get(i) == data) {
////                display();
////                highlightElement(i);
////                return;
////            }
////        }
////        throw new RuntimeException("Element not found!");
////    }
////
////    public void clear() {
////        stack.clear();
////        display();
////    }
////
////    private void animateNewElement() {
////        if (!stack.isEmpty()) {
////            int topIndex = stack.size() - 1;
////            double y = START_Y - topIndex * (RECT_HEIGHT + 5);
////
////            Rectangle rect = new Rectangle(START_X, y - RECT_HEIGHT, RECT_WIDTH, RECT_HEIGHT);
////            rect.setFill(Color.YELLOW);
////            rect.setStroke(Color.BLACK);
////            rect.setStrokeWidth(3);
////
////            canvas.getChildren().add(rect);
////
////            ScaleTransition st = new ScaleTransition(Duration.millis(300), rect);
////            st.setFromX(0.5);
////            st.setFromY(0.5);
////            st.setToX(1.0);
////            st.setToY(1.0);
////            st.setOnFinished(e -> {
////                canvas.getChildren().remove(rect);
////                display();
////            });
////            st.play();
////        }
////    }
////
////    private void highlightElement(int index) {
////        double y = START_Y - index * (RECT_HEIGHT + 5);
////
////        Rectangle highlight = new Rectangle(START_X, y - RECT_HEIGHT, RECT_WIDTH, RECT_HEIGHT);
////        highlight.setFill(Color.YELLOW);
////        highlight.setStroke(Color.ORANGE);
////        highlight.setStrokeWidth(3);
////
////        canvas.getChildren().add(highlight);
////
////        FadeTransition ft = new FadeTransition(Duration.millis(1000), highlight);
////        ft.setFromValue(1.0);
////        ft.setToValue(0.0);
////        ft.setAutoReverse(true);
////        ft.setCycleCount(4);
////        ft.setOnFinished(e -> canvas.getChildren().remove(highlight));
////        ft.play();
////    }
////}
////
////// Visual Queue Implementation
////class VisualQueue {
////    private Pane canvas;
////    private List<Integer> queue;
////    private final double RECT_WIDTH = 60;
////    private final double RECT_HEIGHT = 40;
////    private final double START_X = 50;
////    private final double START_Y = 200;
////
////    public VisualQueue(Pane canvas) {
////        this.canvas = canvas;
////        this.queue = new ArrayList<>();
////    }
////
////    public void display() {
////        canvas.getChildren().clear();
////
////        Text title = new Text(START_X, START_Y - 50, "Queue Visualization (FIFO)");
////        title.setFont(Font.font(16));
////        canvas.getChildren().add(title);
////
////        if (queue.isEmpty()) {
////            Text emptyText = new Text(START_X, START_Y, "Queue is empty");
////            canvas.getChildren().add(emptyText);
////            return;
////        }
////
////        // Draw queue elements
////        for (int i = 0; i < queue.size(); i++) {
////            double x = START_X + i * (RECT_WIDTH + 5);
////
////            Rectangle rect = new Rectangle(x, START_Y, RECT_WIDTH, RECT_HEIGHT);
////            rect.setFill(Color.LIGHTCYAN);
////            rect.setStroke(Color.BLACK);
////
////            Text text = new Text(x + RECT_WIDTH/2 - 5, START_Y + RECT_HEIGHT/2 + 5,
////                    String.valueOf(queue.get(i)));
////            text.setFont(Font.font(14));
////
////            canvas.getChildren().addAll(rect, text);
////        }
////
////        // Draw FRONT and REAR indicators
////        if (!queue.isEmpty()) {
////            Text frontLabel = new Text(START_X, START_Y - 20, "FRONT");
////            frontLabel.setFont(Font.font(12));
////            frontLabel.setFill(Color.BLUE);
////
////            double rearX = START_X + (queue.size() - 1) * (RECT_WIDTH + 5);
////            Text rearLabel = new Text(rearX, START_Y - 20, "REAR");
////            rearLabel.setFont(Font.font(12));
////            rearLabel.setFill(Color.RED);
////
////            canvas.getChildren().addAll(frontLabel, rearLabel);
////        }
////    }
////
////    public void enqueue(int data) {
////        queue.add(data);
////        display();
////        animateNewElement();
////    }
////
////    public void dequeue() {
////        if (queue.isEmpty()) {
////            throw new RuntimeException("Queue is empty!");
////        }
////        queue.remove(0);
////        display();
////    }
////
////    public void search(int data) {
////        for (int i = 0; i < queue.size(); i++) {
////            if (queue.get(i) == data) {
////                display();
////                highlightElement(i);
////                return;
////            }
////        }
////        throw new RuntimeException("Element not found!");
////    }
////
////    public void clear() {
////        queue.clear();
////        display();
////    }
////
////    private void animateNewElement() {
////        if (!queue.isEmpty()) {
////            int rearIndex = queue.size() - 1;
////            double x = START_X + rearIndex * (RECT_WIDTH + 5);
////
////            Rectangle rect = new Rectangle(x, START_Y, RECT_WIDTH, RECT_HEIGHT);
////            rect.setFill(Color.YELLOW);
////            rect.setStroke(Color.BLACK);
////            rect.setStrokeWidth(3);
////
////            canvas.getChildren().add(rect);
////
////            TranslateTransition tt = new TranslateTransition(Duration.millis(300), rect);
////            tt.setFromY(-50);
////            tt.setToY(0);
////            tt.setOnFinished(e -> {
////                canvas.getChildren().remove(rect);
////                display();
////            });
////            tt.play();
////        }
////    }
////
////    private void highlightElement(int index) {
////        double x = START_X + index * (RECT_WIDTH + 5);
////
////        Rectangle highlight = new Rectangle(x, START_Y, RECT_WIDTH, RECT_HEIGHT);
////        highlight.setFill(Color.YELLOW);
////        highlight.setStroke(Color.ORANGE);
////        highlight.setStrokeWidth(3);
////
////        canvas.getChildren().add(highlight);
////
////        ScaleTransition st = new ScaleTransition(Duration.millis(500), highlight);
////        st.setFromX(1.0);
////        st.setFromY(1.0);
////        st.setToX(1.2);
////        st.setToY(1.2);
////        st.setAutoReverse(true);
////        st.setCycleCount(4);
////        st.setOnFinished(e -> canvas.getChildren().remove(highlight));
////        st.play();
////    }
////}
////
////// Visual Heap Implementation (Max Heap)
////class VisualHeap {
////    private Pane canvas;
////    private List<Integer> heap;
////    private final double NODE_RADIUS = 20;
////    private final double START_X = 400;
////    private final double START_Y = 100;
////    private final double LEVEL_HEIGHT = 80;
////
////    public VisualHeap(Pane canvas) {
////        this.canvas = canvas;
////        this.heap = new ArrayList<>();
////    }
////
////    public void display() {
////        canvas.getChildren().clear();
////
////        Text title = new Text(START_X - 100, START_Y - 30, "Max Heap Visualization");
////        title.setFont(Font.font(16));
////        canvas.getChildren().add(title);
////
////        if (heap.isEmpty()) {
////            Text emptyText = new Text(START_X, START_Y + 50, "Heap is empty");
////            canvas.getChildren().add(emptyText);
////            return;
////        }
////
////        // Draw heap nodes
////        for (int i = 0; i < heap.size(); i++) {
////            drawHeapNode(i);
////        }
////
////        // Draw edges
////        for (int i = 0; i < heap.size(); i++) {
////            int leftChild = 2 * i + 1;
////            int rightChild = 2 * i + 2;
////
////            if (leftChild < heap.size()) {
////                drawEdge(i, leftChild);
////            }
////            if (rightChild < heap.size()) {
////                drawEdge(i, rightChild);
////            }
////        }
////    }
////
////    private void drawHeapNode(int index) {
////        double[] pos = getNodePosition(index);
////        double x = pos[0];
////        double y = pos[1];
////
////        Circle circle = new Circle(x, y, NODE_RADIUS);
////        circle.setFill(Color.LIGHTGREEN);
////        circle.setStroke(Color.BLACK);
////
////        Text text = new Text(x - 5, y + 5, String.valueOf(heap.get(index)));
////        text.setFont(Font.font(12));
////
////        canvas.getChildren().addAll(circle, text);
////    }
////
////    private void drawEdge(int parent, int child) {
////        double[] parentPos = getNodePosition(parent);
////        double[] childPos = getNodePosition(child);
////
////        Line edge = new Line(parentPos[0], parentPos[1] + NODE_RADIUS,
////                childPos[0], childPos[1] - NODE_RADIUS);
////        edge.setStroke(Color.BLACK);
////
////        canvas.getChildren().add(edge);
////    }
////
////    private double[] getNodePosition(int index) {
////        int level = (int) (Math.log(index + 1) / Math.log(2));
////        int positionInLevel = index - (int) Math.pow(2, level) + 1;
////        int nodesInLevel = (int) Math.pow(2, level);
////
////        double levelWidth = 300;
////        double spacing = levelWidth / (nodesInLevel + 1);
////
////        double x = START_X - levelWidth/2 + spacing * (positionInLevel + 1);
////        double y = START_Y + level * LEVEL_HEIGHT;
////
////        return new double[]{x, y};
////    }
////
////    public void insert(int data) {
////        heap.add(data);
////        heapifyUp(heap.size() - 1);
////        display();
////        highlightNode(heap.size() - 1);
////    }
////
////    public void extractMax() {
////        if (heap.isEmpty()) {
////            throw new RuntimeException("Heap is empty!");
////        }
////
////        heap.set(0, heap.get(heap.size() - 1));
////        heap.remove(heap.size() - 1);
////
////        if (!heap.isEmpty()) {
////            heapifyDown(0);
////        }
////
////        display();
////    }
////
////    public void search(int data) {
////        for (int i = 0; i < heap.size(); i++) {
////            if (heap.get(i) == data) {
////                display();
////                highlightNode(i);
////                return;
////            }
////        }
////        throw new RuntimeException("Element not found!");
////    }
////
////    public void clear() {
////        heap.clear();
////        display();
////    }
////
////    private void heapifyUp(int index) {
////        if (index == 0) return;
////
////        int parentIndex = (index - 1) / 2;
////        if (heap.get(index) > heap.get(parentIndex)) {
////            Collections.swap(heap, index, parentIndex);
////            heapifyUp(parentIndex);
////        }
////    }
////
////    private void heapifyDown(int index) {
////        int leftChild = 2 * index + 1;
////        int rightChild = 2 * index + 2;
////        int largest = index;
////
////        if (leftChild < heap.size() && heap.get(leftChild) > heap.get(largest)) {
////            largest = leftChild;
////        }
////
////        if (rightChild < heap.size() && heap.get(rightChild) > heap.get(largest)) {
////            largest = rightChild;
////        }
////
////        if (largest != index) {
////            Collections.swap(heap, index, largest);
////            heapifyDown(largest);
////        }
////    }
////
////    private void highlightNode(int index) {
////        double[] pos = getNodePosition(index);
////        double x = pos[0];
////        double y = pos[1];
////
////        Circle highlight = new Circle(x, y, NODE_RADIUS);
////        highlight.setFill(Color.YELLOW);
////        highlight.setStroke(Color.ORANGE);
////        highlight.setStrokeWidth(3);
////
////        canvas.getChildren().add(highlight);
////
////        ScaleTransition st = new ScaleTransition(Duration.millis(500), highlight);
////        st.setFromX(1.0);
////        st.setFromY(1.0);
////        st.setToX(1.3);
////        st.setToY(1.3);
////        st.setAutoReverse(true);
////        st.setCycleCount(4);
////        st.setOnFinished(e -> canvas.getChildren().remove(highlight));
////        st.play();
////    }
////}
////
////// Visual Binary Search Tree Implementation
////class VisualBST {
////    private Pane canvas;
////    private TreeNode root;
////    private final double NODE_RADIUS = 20;
////    private final double START_X = 400;
////    private final double START_Y = 100;
////    private final double LEVEL_HEIGHT = 70;
////
////    class TreeNode {
////        int data;
////        TreeNode left;
////        TreeNode right;
////
////        TreeNode(int data) {
////            this.data = data;
////            this.left = null;
////            this.right = null;
////        }
////    }
////
////    public VisualBST(Pane canvas) {
////        this.canvas = canvas;
////        this.root = null;
////    }
////
////    public void display() {
////        canvas.getChildren().clear();
////
////        Text title = new Text(START_X - 100, START_Y - 30, "Binary Search Tree Visualization");
////        title.setFont(Font.font(16));
////        canvas.getChildren().add(title);
////
////        if (root == null) {
////            Text emptyText = new Text(START_X, START_Y + 50, "BST is empty");
////            canvas.getChildren().add(emptyText);
////            return;
////        }
////
////        drawTree(root, START_X, START_Y, 150, 0);
////    }
////
////    private void drawTree(TreeNode node, double x, double y, double xOffset, int level) {
////        if (node == null) return;
////
////        // Draw current node
////        Circle circle = new Circle(x, y, NODE_RADIUS);
////        circle.setFill(Color.LIGHTBLUE);
////        circle.setStroke(Color.BLACK);
////
////        Text text = new Text(x - 5, y + 5, String.valueOf(node.data));
////        text.setFont(Font.font(12));
////
////        canvas.getChildren().addAll(circle, text);
////
////        // Draw left subtree
////        if (node.left != null) {
////            double leftX = x - xOffset;
////            double leftY = y + LEVEL_HEIGHT;
////
////            Line edge = new Line(x - NODE_RADIUS * 0.7, y + NODE_RADIUS * 0.7,
////                    leftX + NODE_RADIUS * 0.7, leftY - NODE_RADIUS * 0.7);
////            edge.setStroke(Color.BLACK);
////            canvas.getChildren().add(edge);
////
////            drawTree(node.left, leftX, leftY, xOffset / 1.5, level + 1);
////        }
////
////        // Draw right subtree
////        if (node.right != null) {
////            double rightX = x + xOffset;
////            double rightY = y + LEVEL_HEIGHT;
////
////            Line edge = new Line(x + NODE_RADIUS * 0.7, y + NODE_RADIUS * 0.7,
////                    rightX - NODE_RADIUS * 0.7, rightY - NODE_RADIUS * 0.7);
////            edge.setStroke(Color.BLACK);
////            canvas.getChildren().add(edge);
////
////            drawTree(node.right, rightX, rightY, xOffset / 1.5, level + 1);
////        }
////    }
////
////    public void insert(int data) {
////        root = insertRec(root, data);
////        display();
////        // Highlight the newly inserted node
////        highlightPath(data);
////    }
////
////    private TreeNode insertRec(TreeNode root, int data) {
////        if (root == null) {
////            return new TreeNode(data);
////        }
////
////        if (data < root.data) {
////            root.left = insertRec(root.left, data);
////        } else if (data > root.data) {
////            root.right = insertRec(root.right, data);
////        }
////
////        return root;
////    }
////
////    public void delete(int data) {
////        if (root == null) {
////            throw new RuntimeException("BST is empty!");
////        }
////
////        root = deleteRec(root, data);
////        display();
////    }
////
////    private TreeNode deleteRec(TreeNode root, int data) {
////        if (root == null) {
////            throw new RuntimeException("Element not found!");
////        }
////
////        if (data < root.data) {
////            root.left = deleteRec(root.left, data);
////        } else if (data > root.data) {
////            root.right = deleteRec(root.right, data);
////        } else {
////            // Node to be deleted found
////            if (root.left == null) {
////                return root.right;
////            } else if (root.right == null) {
////                return root.left;
////            }
////
////            // Node with two children
////            root.data = minValue(root.right);
////            root.right = deleteRec(root.right, root.data);
////        }
////
////        return root;
////    }
////
////    private int minValue(TreeNode root) {
////        int minValue = root.data;
////        while (root.left != null) {
////            minValue = root.left.data;
////            root = root.left;
////        }
////        return minValue;
////    }
////
////    public void search(int data) {
////        if (searchRec(root, data)) {
////            display();
////            highlightPath(data);
////        } else {
////            throw new RuntimeException("Element not found!");
////        }
////    }
////
////    private boolean searchRec(TreeNode root, int data) {
////        if (root == null) {
////            return false;
////        }
////
////        if (data == root.data) {
////            return true;
////        }
////
////        return data < root.data ? searchRec(root.left, data) : searchRec(root.right, data);
////    }
////
////    public void clear() {
////        root = null;
////        display();
////    }
////
////    private void highlightPath(int data) {
////        highlightPathRec(root, data, START_X, START_Y, 150);
////    }
////
////    private boolean highlightPathRec(TreeNode node, int data, double x, double y, double xOffset) {
////        if (node == null) {
////            return false;
////        }
////
////        // Highlight current node
////        Circle highlight = new Circle(x, y, NODE_RADIUS);
////        highlight.setFill(Color.YELLOW);
////        highlight.setStroke(Color.ORANGE);
////        highlight.setStrokeWidth(3);
////        canvas.getChildren().add(highlight);
////
////        FadeTransition ft = new FadeTransition(Duration.millis(800), highlight);
////        ft.setFromValue(1.0);
////        ft.setToValue(0.0);
////        ft.setDelay(Duration.millis(200));
////        ft.setOnFinished(e -> canvas.getChildren().remove(highlight));
////        ft.play();
////
////        if (data == node.data) {
////            return true;
////        }
////
////        if (data < node.data) {
////            return highlightPathRec(node.left, data, x - xOffset, y + LEVEL_HEIGHT, xOffset / 1.5);
////        } else {
////            return highlightPathRec(node.right, data, x + xOffset, y + LEVEL_HEIGHT, xOffset / 1.5);
////        }
////    }
////}
//
//package edu.buet.shayan.datastructuresvisualizer;
//
//import javafx.application.Application;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.layout.*;
//import javafx.scene.paint.Color;
//import javafx.scene.paint.LinearGradient;
//import javafx.scene.paint.Stop;
//import javafx.scene.paint.CycleMethod;
//import javafx.scene.shape.Circle;
//import javafx.scene.shape.Line;
//import javafx.scene.shape.Rectangle;
//import javafx.scene.text.Font;
//import javafx.scene.text.FontWeight;
//import javafx.scene.text.Text;
//import javafx.stage.Stage;
//import javafx.animation.*;
//import javafx.util.Duration;
//import javafx.scene.effect.DropShadow;
//import javafx.scene.effect.InnerShadow;
//import javafx.scene.effect.Glow;
//import java.util.*;
//
//public class DataStructuresVisualizer extends Application {
//
//    private Pane canvas;
//    private ComboBox<String> structureSelector;
//    private TextField inputField;
//    private TextField indexField;
//    private VBox controlPanel;
//    private Label statusLabel;
//    private Label infoLabel;
//    private Button insertBtn, deleteBtn, searchBtn, clearBtn;
//    private Button insertAtBtn, deleteAtBtn, peekBtn, sizeBtn;
//
//    // Data structure implementations
//    private VisualArray visualArray;
//    private VisualSLL visualSLL;
//    private VisualDLL visualDLL;
//    private VisualStack visualStack;
//    private VisualQueue visualQueue;
//    private VisualHeap visualHeap;
//    private VisualBST visualBST;
//
//    private String currentStructure = "Array";
//
//    @Override
//    public void start(Stage primaryStage) {
//        BorderPane root = new BorderPane();
//        root.setStyle("-fx-background-color: linear-gradient(to bottom, #667eea 0%, #764ba2 100%);");
//
//        // Initialize canvas with modern styling
//        canvas = new Pane();
//        canvas.setPrefSize(900, 650);
//        canvas.setStyle("-fx-background-color: rgba(255, 255, 255, 0.95); " +
//                "-fx-background-radius: 15; " +
//                "-fx-border-radius: 15; " +
//                "-fx-border-color: rgba(0, 0, 0, 0.1); " +
//                "-fx-border-width: 2;");
//
//        // Add drop shadow to canvas
//        DropShadow canvasShadow = new DropShadow();
//        canvasShadow.setRadius(15);
//        canvasShadow.setOffsetX(5);
//        canvasShadow.setOffsetY(5);
//        canvasShadow.setColor(Color.rgb(0, 0, 0, 0.3));
//        canvas.setEffect(canvasShadow);
//
//        // Wrap canvas in a container with padding
//        StackPane canvasContainer = new StackPane(canvas);
//        canvasContainer.setPadding(new Insets(20));
//
//        // Initialize control panel
//        setupControlPanel();
//
//        // Initialize data structures
//        initializeDataStructures();
//
//        root.setCenter(canvasContainer);
//        root.setRight(controlPanel);
//
//        Scene scene = new Scene(root, 1350, 750);
//        primaryStage.setTitle("Advanced Data Structures Visualizer");
//        primaryStage.setScene(scene);
//        primaryStage.setResizable(false);
//        primaryStage.show();
//
//        // Show initial structure
//        showStructure(currentStructure);
//        updateInfo();
//    }
//
//    private void setupControlPanel() {
//        controlPanel = new VBox(15);
//        controlPanel.setPadding(new Insets(25));
//        controlPanel.setPrefWidth(350);
//        controlPanel.setStyle("-fx-background-color: rgba(255, 255, 255, 0.95); " +
//                "-fx-background-radius: 15; " +
//                "-fx-border-radius: 15;");
//
//        // Add drop shadow to control panel
//        DropShadow panelShadow = new DropShadow();
//        panelShadow.setRadius(15);
//        panelShadow.setOffsetX(-5);
//        panelShadow.setOffsetY(5);
//        panelShadow.setColor(Color.rgb(0, 0, 0, 0.3));
//        controlPanel.setEffect(panelShadow);
//
//        // Title with gradient effect
//        Label title = new Label("🔬 Data Structure Visualizer");
//        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
//        title.setStyle("-fx-text-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, #667eea 0%, #764ba2 50%);");
//
//        // Structure selector with improved styling
//        Label selectorLabel = new Label("📊 Select Data Structure:");
//        selectorLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 14));
//        selectorLabel.setStyle("-fx-text-fill: #2c3e50;");
//
//        structureSelector = new ComboBox<>();
//        structureSelector.getItems().addAll("Array", "Singly Linked List", "Doubly Linked List",
//                "Stack", "Queue", "Heap", "Binary Search Tree");
//        structureSelector.setValue("Array");
//        structureSelector.setPrefWidth(300);
//        structureSelector.setStyle("-fx-background-color: white; " +
//                "-fx-border-color: #3498db; " +
//                "-fx-border-radius: 8; " +
//                "-fx-background-radius: 8; " +
//                "-fx-font-size: 12px;");
//        structureSelector.setOnAction(e -> {
//            currentStructure = structureSelector.getValue();
//            showStructure(currentStructure);
//            updateInfo();
//            updateStatus("Switched to " + currentStructure);
//        });
//
//        // Input fields section
//        Label inputLabel = new Label("🔢 Input Values:");
//        inputLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 14));
//        inputLabel.setStyle("-fx-text-fill: #2c3e50;");
//
//        inputField = new TextField();
//        inputField.setPromptText("Enter value (e.g., 42)");
//        inputField.setPrefWidth(300);
//        styleTextField(inputField);
//
//        indexField = new TextField();
//        indexField.setPromptText("Enter index (optional)");
//        indexField.setPrefWidth(300);
//        styleTextField(indexField);
//
//        // Main operation buttons
//        Label operationsLabel = new Label("⚡ Main Operations:");
//        operationsLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 14));
//        operationsLabel.setStyle("-fx-text-fill: #2c3e50;");
//
//        insertBtn = createStyledButton("➕ Insert", "#27ae60");
//        deleteBtn = createStyledButton("❌ Delete", "#e74c3c");
//        searchBtn = createStyledButton("🔍 Search", "#3498db");
//        clearBtn = createStyledButton("🗑️ Clear All", "#95a5a6");
//
//        // Advanced operation buttons
//        Label advancedLabel = new Label("🎯 Advanced Operations:");
//        advancedLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 14));
//        advancedLabel.setStyle("-fx-text-fill: #2c3e50;");
//
//        insertAtBtn = createStyledButton("📍 Insert At Index", "#f39c12");
//        deleteAtBtn = createStyledButton("🎯 Delete At Index", "#e67e22");
//        peekBtn = createStyledButton("👁️ Peek/Top", "#9b59b6");
//        sizeBtn = createStyledButton("📏 Size/Count", "#34495e");
//
//        // Set button actions
//        insertBtn.setOnAction(e -> performOperation("insert"));
//        deleteBtn.setOnAction(e -> performOperation("delete"));
//        searchBtn.setOnAction(e -> performOperation("search"));
//        clearBtn.setOnAction(e -> performOperation("clear"));
//        insertAtBtn.setOnAction(e -> performOperation("insertAt"));
//        deleteAtBtn.setOnAction(e -> performOperation("deleteAt"));
//        peekBtn.setOnAction(e -> performOperation("peek"));
//        sizeBtn.setOnAction(e -> performOperation("size"));
//
//        // Status and info labels
//        statusLabel = new Label("✅ Ready to visualize data structures!");
//        statusLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
//        statusLabel.setStyle("-fx-text-fill: #27ae60; -fx-background-color: rgba(46, 204, 113, 0.1); " +
//                "-fx-padding: 8; -fx-background-radius: 5;");
//        statusLabel.setWrapText(true);
//
//        infoLabel = new Label();
//        infoLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 11));
//        infoLabel.setStyle("-fx-text-fill: #7f8c8d; -fx-background-color: rgba(127, 140, 141, 0.1); " +
//                "-fx-padding: 8; -fx-background-radius: 5;");
//        infoLabel.setWrapText(true);
//
//        // Add all components to control panel
//        controlPanel.getChildren().addAll(
//                title,
//                new Separator(),
//                selectorLabel, structureSelector,
//                new Separator(),
//                inputLabel, inputField, indexField,
//                new Separator(),
//                operationsLabel, insertBtn, deleteBtn, searchBtn, clearBtn,
//                new Separator(),
//                advancedLabel, insertAtBtn, deleteAtBtn, peekBtn, sizeBtn,
//                new Separator(),
//                statusLabel, infoLabel
//        );
//    }
//
//    private void styleTextField(TextField field) {
//        field.setStyle("-fx-background-color: white; " +
//                "-fx-border-color: #bdc3c7; " +
//                "-fx-border-radius: 8; " +
//                "-fx-background-radius: 8; " +
//                "-fx-padding: 8; " +
//                "-fx-font-size: 12px;");
//
//        // Add focus effect
//        field.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
//            if (isNowFocused) {
//                field.setStyle("-fx-background-color: white; " +
//                        "-fx-border-color: #3498db; " +
//                        "-fx-border-width: 2; " +
//                        "-fx-border-radius: 8; " +
//                        "-fx-background-radius: 8; " +
//                        "-fx-padding: 8; " +
//                        "-fx-font-size: 12px;");
//            } else {
//                field.setStyle("-fx-background-color: white; " +
//                        "-fx-border-color: #bdc3c7; " +
//                        "-fx-border-radius: 8; " +
//                        "-fx-background-radius: 8; " +
//                        "-fx-padding: 8; " +
//                        "-fx-font-size: 12px;");
//            }
//        });
//    }
//
//    private Button createStyledButton(String text, String color) {
//        Button button = new Button(text);
//        button.setPrefWidth(300);
//        button.setPrefHeight(40);
//        button.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 12));
//
//        String baseStyle = "-fx-background-color: " + color + "; " +
//                "-fx-text-fill: white; " +
//                "-fx-background-radius: 8; " +
//                "-fx-border-radius: 8; " +
//                "-fx-cursor: hand; " +
//                "-fx-font-weight: bold;";
//
//        String hoverStyle = "-fx-background-color: derive(" + color + ", -10%); " +
//                "-fx-text-fill: white; " +
//                "-fx-background-radius: 8; " +
//                "-fx-border-radius: 8; " +
//                "-fx-cursor: hand; " +
//                "-fx-font-weight: bold; " +
//                "-fx-scale-x: 1.02; " +
//                "-fx-scale-y: 1.02;";
//
//        button.setStyle(baseStyle);
//
//        // Add hover effects
//        button.setOnMouseEntered(e -> {
//            button.setStyle(hoverStyle);
//            ScaleTransition st = new ScaleTransition(Duration.millis(100), button);
//            st.setToX(1.02);
//            st.setToY(1.02);
//            st.play();
//        });
//
//        button.setOnMouseExited(e -> {
//            button.setStyle(baseStyle);
//            ScaleTransition st = new ScaleTransition(Duration.millis(100), button);
//            st.setToX(1.0);
//            st.setToY(1.0);
//            st.play();
//        });
//
//        // Add glow effect
//        Glow glow = new Glow();
//        glow.setLevel(0.3);
//        button.setEffect(glow);
//
//        return button;
//    }
//
//    private void initializeDataStructures() {
//        visualArray = new VisualArray(canvas);
//        visualSLL = new VisualSLL(canvas);
//        visualDLL = new VisualDLL(canvas);
//        visualStack = new VisualStack(canvas);
//        visualQueue = new VisualQueue(canvas);
//        visualHeap = new VisualHeap(canvas);
//        visualBST = new VisualBST(canvas);
//    }
//
//    private void showStructure(String structure) {
//        canvas.getChildren().clear();
//
//        switch (structure) {
//            case "Array":
//                visualArray.display();
//                break;
//            case "Singly Linked List":
//                visualSLL.display();
//                break;
//            case "Doubly Linked List":
//                visualDLL.display();
//                break;
//            case "Stack":
//                visualStack.display();
//                break;
//            case "Queue":
//                visualQueue.display();
//                break;
//            case "Heap":
//                visualHeap.display();
//                break;
//            case "Binary Search Tree":
//                visualBST.display();
//                break;
//        }
//    }
//
//    private void performOperation(String operation) {
//        String input = inputField.getText().trim();
//        String indexInput = indexField.getText().trim();
//
//        try {
//            switch (currentStructure) {
//                case "Array":
//                    performArrayOperation(operation, input, indexInput);
//                    break;
//                case "Singly Linked List":
//                    performSLLOperation(operation, input, indexInput);
//                    break;
//                case "Doubly Linked List":
//                    performDLLOperation(operation, input, indexInput);
//                    break;
//                case "Stack":
//                    performStackOperation(operation, input, indexInput);
//                    break;
//                case "Queue":
//                    performQueueOperation(operation, input, indexInput);
//                    break;
//                case "Heap":
//                    performHeapOperation(operation, input, indexInput);
//                    break;
//                case "Binary Search Tree":
//                    performBSTOperation(operation, input, indexInput);
//                    break;
//            }
//            inputField.clear();
//            indexField.clear();
//        } catch (NumberFormatException e) {
//            showAlert("Invalid Input", "Please enter valid numbers only.", Alert.AlertType.ERROR);
//            updateStatus("❌ Invalid input format");
//        } catch (Exception e) {
//            showAlert("Operation Error", e.getMessage(), Alert.AlertType.WARNING);
//            updateStatus("❌ " + e.getMessage());
//        }
//    }
//
//    private void performArrayOperation(String operation, String input, String indexInput) {
//        switch (operation) {
//            case "insert":
//                if (!input.isEmpty()) {
//                    visualArray.insert(Integer.parseInt(input));
//                    updateStatus("✅ Inserted " + input + " into array");
//                }
//                break;
//            case "insertAt":
//                if (!input.isEmpty() && !indexInput.isEmpty()) {
//                    visualArray.insertAt(Integer.parseInt(indexInput), Integer.parseInt(input));
//                    updateStatus("✅ Inserted " + input + " at index " + indexInput);
//                }
//                break;
//            case "delete":
//                if (!input.isEmpty()) {
//                    visualArray.delete(Integer.parseInt(input));
//                    updateStatus("✅ Deleted " + input + " from array");
//                } else {
//                    visualArray.deleteLast();
//                    updateStatus("✅ Deleted last element");
//                }
//                break;
//            case "deleteAt":
//                if (!indexInput.isEmpty()) {
//                    int deleted = visualArray.deleteAt(Integer.parseInt(indexInput));
//                    updateStatus("✅ Deleted " + deleted + " from index " + indexInput);
//                }
//                break;
//            case "search":
//                if (!input.isEmpty()) {
//                    int index = visualArray.search(Integer.parseInt(input));
//                    updateStatus("✅ Found " + input + " at index " + index);
//                }
//                break;
//            case "size":
//                int size = visualArray.size();
//                updateStatus("📏 Array size: " + size);
//                break;
//            case "clear":
//                visualArray.clear();
//                updateStatus("✅ Array cleared");
//                break;
//        }
//    }
//
//    private void performSLLOperation(String operation, String input, String indexInput) {
//        switch (operation) {
//            case "insert":
//                if (!input.isEmpty()) {
//                    visualSLL.insert(Integer.parseInt(input));
//                    updateStatus("✅ Inserted " + input + " into linked list");
//                }
//                break;
//            case "insertAt":
//                if (!input.isEmpty() && !indexInput.isEmpty()) {
//                    visualSLL.insertAt(Integer.parseInt(indexInput), Integer.parseInt(input));
//                    updateStatus("✅ Inserted " + input + " at position " + indexInput);
//                }
//                break;
//            case "delete":
//                if (!input.isEmpty()) {
//                    visualSLL.delete(Integer.parseInt(input));
//                    updateStatus("✅ Deleted " + input + " from linked list");
//                } else {
//                    visualSLL.deleteFirst();
//                    updateStatus("✅ Deleted first element");
//                }
//                break;
//            case "deleteAt":
//                if (!indexInput.isEmpty()) {
//                    int deleted = visualSLL.deleteAt(Integer.parseInt(indexInput));
//                    updateStatus("✅ Deleted " + deleted + " from position " + indexInput);
//                }
//                break;
//            case "search":
//                if (!input.isEmpty()) {
//                    int position = visualSLL.search(Integer.parseInt(input));
//                    updateStatus("✅ Found " + input + " at position " + position);
//                }
//                break;
//            case "size":
//                int size = visualSLL.size();
//                updateStatus("📏 List size: " + size);
//                break;
//            case "clear":
//                visualSLL.clear();
//                updateStatus("✅ Linked list cleared");
//                break;
//        }
//    }
//
//    private void performDLLOperation(String operation, String input, String indexInput) {
//        switch (operation) {
//            case "insert":
//                if (!input.isEmpty()) {
//                    visualDLL.insert(Integer.parseInt(input));
//                    updateStatus("✅ Inserted " + input + " into doubly linked list");
//                }
//                break;
//            case "insertAt":
//                if (!input.isEmpty() && !indexInput.isEmpty()) {
//                    visualDLL.insertAt(Integer.parseInt(indexInput), Integer.parseInt(input));
//                    updateStatus("✅ Inserted " + input + " at position " + indexInput);
//                }
//                break;
//            case "delete":
//                if (!input.isEmpty()) {
//                    visualDLL.delete(Integer.parseInt(input));
//                    updateStatus("✅ Deleted " + input + " from doubly linked list");
//                } else {
//                    visualDLL.deleteFirst();
//                    updateStatus("✅ Deleted first element");
//                }
//                break;
//            case "deleteAt":
//                if (!indexInput.isEmpty()) {
//                    int deleted = visualDLL.deleteAt(Integer.parseInt(indexInput));
//                    updateStatus("✅ Deleted " + deleted + " from position " + indexInput);
//                }
//                break;
//            case "search":
//                if (!input.isEmpty()) {
//                    int position = visualDLL.search(Integer.parseInt(input));
//                    updateStatus("✅ Found " + input + " at position " + position);
//                }
//                break;
//            case "size":
//                int size = visualDLL.size();
//                updateStatus("📏 List size: " + size);
//                break;
//            case "clear":
//                visualDLL.clear();
//                updateStatus("✅ Doubly linked list cleared");
//                break;
//        }
//    }
//
//    private void performStackOperation(String operation, String input, String indexInput) {
//        switch (operation) {
//            case "insert":
//                if (!input.isEmpty()) {
//                    visualStack.push(Integer.parseInt(input));
//                    updateStatus("✅ Pushed " + input + " onto stack");
//                }
//                break;
//            case "delete":
//                int popped = visualStack.pop();
//                updateStatus("✅ Popped " + popped + " from stack");
//                break;
//            case "peek":
//                int top = visualStack.peek();
//                updateStatus("👁️ Top element: " + top);
//                break;
//            case "search":
//                if (!input.isEmpty()) {
//                    int position = visualStack.search(Integer.parseInt(input));
//                    updateStatus("✅ Found " + input + " at position " + position + " from top");
//                }
//                break;
//            case "size":
//                int size = visualStack.size();
//                updateStatus("📏 Stack size: " + size);
//                break;
//            case "clear":
//                visualStack.clear();
//                updateStatus("✅ Stack cleared");
//                break;
//        }
//    }
//
//    private void performQueueOperation(String operation, String input, String indexInput) {
//        switch (operation) {
//            case "insert":
//                if (!input.isEmpty()) {
//                    visualQueue.enqueue(Integer.parseInt(input));
//                    updateStatus("✅ Enqueued " + input + " to queue");
//                }
//                break;
//            case "delete":
//                int dequeued = visualQueue.dequeue();
//                updateStatus("✅ Dequeued " + dequeued + " from queue");
//                break;
//            case "peek":
//                int front = visualQueue.peek();
//                updateStatus("👁️ Front element: " + front);
//                break;
//            case "search":
//                if (!input.isEmpty()) {
//                    int position = visualQueue.search(Integer.parseInt(input));
//                    updateStatus("✅ Found " + input + " at position " + position);
//                }
//                break;
//            case "size":
//                int size = visualQueue.size();
//                updateStatus("📏 Queue size: " + size);
//                break;
//            case "clear":
//                visualQueue.clear();
//                updateStatus("✅ Queue cleared");
//                break;
//        }
//    }
//
//    private void performHeapOperation(String operation, String input, String indexInput) {
//        switch (operation) {
//            case "insert":
//                if (!input.isEmpty()) {
//                    visualHeap.insert(Integer.parseInt(input));
//                    updateStatus("✅ Inserted " + input + " into heap");
//                }
//                break;
//            case "delete":
//                int extracted = visualHeap.extractMax();
//                updateStatus("✅ Extracted max: " + extracted);
//                break;
//            case "peek":
//                int max = visualHeap.peekMax();
//                updateStatus("👁️ Max element: " + max);
//                break;
//            case "search":
//                if (!input.isEmpty()) {
//                    boolean found = visualHeap.search(Integer.parseInt(input));
//                    if (found) {
//                        updateStatus("✅ Found " + input + " in heap");
//                    } else {
//                        updateStatus("❌ " + input + " not found in heap");
//                    }
//                }
//                break;
//            case "size":
//                int size = visualHeap.size();
//                updateStatus("📏 Heap size: " + size);
//                break;
//            case "clear":
//                visualHeap.clear();
//                updateStatus("✅ Heap cleared");
//                break;
//        }
//    }
//
//    private void performBSTOperation(String operation, String input, String indexInput) {
//        switch (operation) {
//            case "insert":
//                if (!input.isEmpty()) {
//                    visualBST.insert(Integer.parseInt(input));
//                    updateStatus("✅ Inserted " + input + " into BST");
//                }
//                break;
//            case "delete":
//                if (!input.isEmpty()) {
//                    visualBST.delete(Integer.parseInt(input));
//                    updateStatus("✅ Deleted " + input + " from BST");
//                }
//                break;
//            case "search":
//                if (!input.isEmpty()) {
//                    boolean found = visualBST.search(Integer.parseInt(input));
//                    if (found) {
//                        updateStatus("✅ Found " + input + " in BST");
//                    } else {
//                        updateStatus("❌ " + input + " not found in BST");
//                    }
//                }
//                break;
//            case "size":
//                int size = visualBST.size();
//                updateStatus("📏 BST size: " + size);
//                break;
//            case "peek":
//                int[] minMax = visualBST.getMinMax();
//                if (minMax != null) {
//                    updateStatus("👁️ Min: " + minMax[0] + ", Max: " + minMax[1]);
//                } else {
//                    updateStatus("❌ BST is empty");
//                }
//                break;
//            case "clear":
//                visualBST.clear();
//                updateStatus("✅ BST cleared");
//                break;
//        }
//    }
//
//    private void updateStatus(String message) {
//        statusLabel.setText(message);
//
//        // Add a fade-in animation for status updates
//        FadeTransition ft = new FadeTransition(Duration.millis(300), statusLabel);
//        ft.setFromValue(0.6);
//        ft.setToValue(1.0);
//        ft.play();
//    }
//
//    private void updateInfo() {
//        String info = "";
//        switch (currentStructure) {
//            case "Array":
//                info = "📊 Array: Fixed-size sequential collection. O(1) access, O(n) insertion/deletion.";
//                break;
//            case "Singly Linked List":
//                info = "🔗 Singly Linked List: Dynamic size, sequential access. O(1) insertion at head, O(n) search.";
//                break;
//            case "Doubly Linked List":
//                info = "🔗🔗 Doubly Linked List: Bidirectional traversal. O(1) insertion/deletion at both ends.";
//                break;
//            case "Stack":
//                info = "📚 Stack: LIFO (Last In, First Out). O(1) push/pop operations. Used in recursion, undo operations.";
//                break;
//            case "Queue":
//                info = "🚶‍♂️ Queue: FIFO (First In, First Out). O(1) enqueue/dequeue. Used in BFS, scheduling.";
//                break;
//            case "Heap":
//                info = "🏔️ Max Heap: Complete binary tree. O(log n) insertion/extraction. Used in priority queues.";
//                break;
//            case "Binary Search Tree":
//                info = "🌳 BST: Ordered binary tree. O(log n) average operations. Left < root < right property.";
//                break;
//        }
//        infoLabel.setText(info);
//    }
//
//    private void showAlert(String title, String message, Alert.AlertType type) {
//        Alert alert = new Alert(type);
//        alert.setTitle(title);
//        alert.setHeaderText(null);
//        alert.setContentText(message);
//
//        // Style the alert
//        alert.getDialogPane().setStyle("-fx-background-color: white; -fx-font-family: Arial;");
//
//        alert.showAndWait();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}
//
//// Enhanced Visual Array Implementation
//class VisualArray {
//    private Pane canvas;
//    private List<Integer> array;
//    private List<Rectangle> rectangles;
//    private List<Text> texts;
//    private final int MAX_SIZE = 12;
//    private final double RECT_WIDTH = 65;
//    private final double RECT_HEIGHT = 45;
//    private final double START_X = 70;
//    private final double START_Y = 120;
//
//    public VisualArray(Pane canvas) {
//        this.canvas = canvas;
//        this.array = new ArrayList<>();
//        this.rectangles = new ArrayList<>();
//        this.texts = new ArrayList<>();
//    }
//
//    public void display() {
//        canvas.getChildren().clear();
//        rectangles.clear();
//        texts.clear();
//
//        // Draw modern title with styling
//        Text title = new Text(START_X, START_Y - 50, "📊 Array Visualization");
//        title.setFont(Font.font("Arial", FontWeight.BOLD, 18));
//        title.setFill(Color.web("#2c3e50"));
//        canvas.getChildren().add(title);
//
//        // Add description
//        Text desc = new Text(START_X, START_Y - 25, "Fixed-size sequential collection with O(1) random access");
//        desc.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
//        desc.setFill(Color.web("#7f8c8d"));
//        canvas.getChildren().add(desc);
//
//        // Draw array elements
//        for (int i = 0; i < array.size(); i++) {
//            drawElement(i, array.get(i), false);
//        }
//
//        // Draw empty slots
//        for (int i = array.size(); i < MAX_SIZE; i++) {
//            drawEmptySlot(i);
//        }
//
//        // Draw indices with modern styling
//        for (int i = 0; i < MAX_SIZE; i++) {
//            Text indexText = new Text(START_X + i * (RECT_WIDTH + 8) + RECT_WIDTH/2 - 8,
//                    START_Y + RECT_HEIGHT + 25, String.valueOf(i));
//            indexText.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 11));
//            indexText.setFill(Color.web("#95a5a6"));
//            canvas.getChildren().add(indexText);
//        }
//
//        // Add array info
//        Text info = new Text(START_X, START_Y + RECT_HEIGHT + 50,
//                String.format("Size: %d/%d elements", array.size(), MAX_SIZE));
//        info.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
//        info.setFill(Color.web("#34495e"));
//        canvas.getChildren().add(info);
//    }
//
//    private void drawElement(int index, int value, boolean highlight) {
//        double x = START_X + index * (RECT_WIDTH + 8);
//        double y = START_Y;
//
//        Rectangle rect = new Rectangle(x, y, RECT_WIDTH, RECT_HEIGHT);
//
//        // Create gradient fill
//        LinearGradient gradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
//                new Stop(0, highlight ? Color.web("#f39c12") : Color.web("#3498db")),
//                new Stop(1, highlight ? Color.web("#e67e22") : Color.web("#2980b9")));
//
//        rect.setFill(gradient);
//        rect.setStroke(Color.web("#2c3e50"));
//        rect.setStrokeWidth(2);
//        rect.setArcWidth(8);
//        rect.setArcHeight(8);
//
//        // Add shadow effect
//        DropShadow shadow = new DropShadow();
//        shadow.setRadius(5);
//        shadow.setOffsetX(2);
//        shadow.setOffsetY(2);
//        shadow.setColor(Color.rgb(0, 0, 0, 0.3));
//        rect.setEffect(shadow);
//
//        rectangles.add(rect);
//
//        Text text = new Text(x + RECT_WIDTH/2 - 8, y + RECT_HEIGHT/2 + 6, String.valueOf(value));
//        text.setFont(Font.font("Arial", FontWeight.BOLD, 14));
//        text.setFill(Color.WHITE);
//        texts.add(text);
//
//        canvas.getChildren().addAll(rect, text);
//    }
//
//    private void drawEmptySlot(int index) {
//        double x = START_X + index * (RECT_WIDTH + 8);
//        double y = START_Y;
//
//        Rectangle rect = new Rectangle(x, y, RECT_WIDTH, RECT_HEIGHT);
//        rect.setFill(Color.TRANSPARENT);
//        rect.setStroke(Color.web("#bdc3c7"));
//        rect.setStrokeWidth(2);
//        rect.getStrokeDashArray().addAll(8d, 8d);
//        rect.setArcWidth(8);
//        rect.setArcHeight(8);
//
//        Text text = new Text(x + RECT_WIDTH/2 - 8, y + RECT_HEIGHT/2 + 6, "—");
//        text.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
//        text.setFill(Color.web("#bdc3c7"));
//
//        canvas.getChildren().addAll(rect, text);
//    }
//
//    public void insert(int value) {
//        if (array.size() >= MAX_SIZE) {
//            throw new RuntimeException("Array is full! Cannot insert more elements.");
//        }
//        array.add(value);
//        display();
//        animateInsertion(array.size() - 1);
//    }
//
//    public void insertAt(int index, int value) {
//        if (index < 0 || index > array.size()) {
//            throw new RuntimeException("Invalid index! Must be between 0 and " + array.size());
//        }
//        if (array.size() >= MAX_SIZE) {
//            throw new RuntimeException("Array is full! Cannot insert more elements.");
//        }
//        array.add(index, value);
//        display();
//        animateInsertion(index);
//    }
//
//    public void delete(int value) {
//        int index = array.indexOf(value);
//        if (index != -1) {
//            animateDeletion(index);
//            array.remove(index);
//            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500), e -> display()));
//            timeline.play();
//        } else {
//            throw new RuntimeException("Element " + value + " not found in array!");
//        }
//    }
//
//    public int deleteAt(int index) {
//        if (index < 0 || index >= array.size()) {
//            throw new RuntimeException("Invalid index! Must be between 0 and " + (array.size() - 1));
//        }
//        int value = array.get(index);
//        animateDeletion(index);
//        array.remove(index);
//        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500), e -> display()));
//        timeline.play();
//        return value;
//    }
//
//    public void deleteLast() {
//        if (array.isEmpty()) {
//            throw new RuntimeException("Array is empty! Cannot delete from empty array.");
//        }
//        animateDeletion(array.size() - 1);
//        array.remove(array.size() - 1);
//        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500), e -> display()));
//        timeline.play();
//    }
//
//    public int search(int value) {
//        for (int i = 0; i < array.size(); i++) {
//            if (array.get(i) == value) {
//                animateSearch(i);
//                return i;
//            }
//        }
//        throw new RuntimeException("Element " + value + " not found in array!");
//    }
//
//    public int size() {
//        return array.size();
//    }
//
//    public void clear() {
//        array.clear();
//        display();
//    }
//
//    private void animateInsertion(int index) {
//        if (index < rectangles.size()) {
//            Rectangle rect = rectangles.get(index);
//            Text text = texts.get(index);
//
//            ScaleTransition scaleRect = new ScaleTransition(Duration.millis(400), rect);
//            scaleRect.setFromX(0.1);
//            scaleRect.setFromY(0.1);
//            scaleRect.setToX(1.0);
//            scaleRect.setToY(1.0);
//
//            ScaleTransition scaleText = new ScaleTransition(Duration.millis(400), text);
//            scaleText.setFromX(0.1);
//            scaleText.setFromY(0.1);
//            scaleText.setToX(1.0);
//            scaleText.setToY(1.0);
//
//            ParallelTransition pt = new ParallelTransition(scaleRect, scaleText);
//            pt.play();
//        }
//    }
//
//    private void animateDeletion(int index) {
//        if (index < rectangles.size()) {
//            Rectangle rect = rectangles.get(index);
//            Text text = texts.get(index);
//
//            FadeTransition fadeRect = new FadeTransition(Duration.millis(300), rect);
//            fadeRect.setToValue(0);
//
//            FadeTransition fadeText = new FadeTransition(Duration.millis(300), text);
//            fadeText.setToValue(0);
//
//            ParallelTransition pt = new ParallelTransition(fadeRect, fadeText);
//            pt.play();
//        }
//    }
//
//    private void animateSearch(int index) {
//        display();
//        drawElement(index, array.get(index), true);
//
//        if (index < rectangles.size()) {
//            Rectangle highlight = rectangles.get(rectangles.size() - 1);
//
//            ScaleTransition st = new ScaleTransition(Duration.millis(600), highlight);
//            st.setFromX(1.0);
//            st.setFromY(1.0);
//            st.setToX(1.1);
//            st.setToY(1.1);
//            st.setAutoReverse(true);
//            st.setCycleCount(4);
//
//            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(2500), e -> display()));
//
//            SequentialTransition seq = new SequentialTransition(st, timeline);
//            seq.play();
//        }
//    }
//}
//
//// Enhanced Visual Singly Linked List Implementation
//class VisualSLL {
//    private Pane canvas;
//    private Node head;
//    private final double NODE_RADIUS = 30;
//    private final double START_X = 80;
//    private final double START_Y = 200;
//    private final double NODE_SPACING = 140;
//
//    class Node {
//        int data;
//        Node next;
//        Circle circle;
//        Text text;
//        Line arrow;
//
//        Node(int data) {
//            this.data = data;
//            this.next = null;
//        }
//    }
//
//    public VisualSLL(Pane canvas) {
//        this.canvas = canvas;
//        this.head = null;
//    }
//
//    public void display() {
//        canvas.getChildren().clear();
//
//        // Modern title
//        Text title = new Text(START_X, START_Y - 80, "🔗 Singly Linked List Visualization");
//        title.setFont(Font.font("Arial", FontWeight.BOLD, 18));
//        title.setFill(Color.web("#2c3e50"));
//        canvas.getChildren().add(title);
//
//        Text desc = new Text(START_X, START_Y - 55, "Dynamic linear data structure with sequential access");
//        desc.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
//        desc.setFill(Color.web("#7f8c8d"));
//        canvas.getChildren().add(desc);
//
//        if (head == null) {
//            Text emptyText = new Text(START_X, START_Y, "List is empty - Add some elements!");
//            emptyText.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
//            emptyText.setFill(Color.web("#95a5a6"));
//            canvas.getChildren().add(emptyText);
//            return;
//        }
//
//        Node current = head;
//        int position = 0;
//
//        while (current != null) {
//            drawNode(current, position, false);
//            current = current.next;
//            position++;
//        }
//
//        // Add HEAD indicator
//        Text headLabel = new Text(START_X - 30, START_Y - 45, "HEAD");
//        headLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
//        headLabel.setFill(Color.web("#e74c3c"));
//        canvas.getChildren().add(headLabel);
//
//        // Draw arrow to head
//        Line headArrow = new Line(START_X - 10, START_Y - 35, START_X - 10, START_Y - NODE_RADIUS - 5);
//        headArrow.setStroke(Color.web("#e74c3c"));
//        headArrow.setStrokeWidth(2);
//        canvas.getChildren().add(headArrow);
//
//        // Add size info
//        Text sizeInfo = new Text(START_X, START_Y + 80, "Size: " + size() + " nodes");
//        sizeInfo.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
//        sizeInfo.setFill(Color.web("#34495e"));
//        canvas.getChildren().add(sizeInfo);
//    }
//
//    private void drawNode(Node node, int position, boolean highlight) {
//        double x = START_X + position * NODE_SPACING;
//        double y = START_Y;
//
//        // Create gradient for node
//        LinearGradient gradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
//                new Stop(0, highlight ? Color.web("#f39c12") : Color.web("#3498db")),
//                new Stop(1, highlight ? Color.web("#e67e22") : Color.web("#2980b9")));
//
//        // Draw circle
//        Circle circle = new Circle(x, y, NODE_RADIUS);
//        circle.setFill(gradient);
//        circle.setStroke(Color.web("#2c3e50"));
//        circle.setStrokeWidth(3);
//
//        // Add shadow
//        DropShadow shadow = new DropShadow();
//        shadow.setRadius(8);
//        shadow.setOffsetX(3);
//        shadow.setOffsetY(3);
//        shadow.setColor(Color.rgb(0, 0, 0, 0.4));
//        circle.setEffect(shadow);
//
//        node.circle = circle;
//
//        // Draw text
//        Text text = new Text(x - 8, y + 6, String.valueOf(node.data));
//        text.setFont(Font.font("Arial", FontWeight.BOLD, 14));
//        text.setFill(Color.WHITE);
//        node.text = text;
//
//        canvas.getChildren().addAll(circle, text);
//
//        // Draw arrow to next node
//        if (node.next != null) {
//            double arrowStartX = x + NODE_RADIUS + 5;
//            double arrowEndX = x + NODE_SPACING - NODE_RADIUS - 5;
//
//            Line arrow = new Line(arrowStartX, y, arrowEndX, y);
//            arrow.setStroke(Color.web("#2c3e50"));
//            arrow.setStrokeWidth(3);
//            node.arrow = arrow;
//
//            // Arrow head
//            Line arrowHead1 = new Line(arrowEndX - 12, y - 6, arrowEndX, y);
//            Line arrowHead2 = new Line(arrowEndX - 12, y + 6, arrowEndX, y);
//            arrowHead1.setStroke(Color.web("#2c3e50"));
//            arrowHead2.setStroke(Color.web("#2c3e50"));
//            arrowHead1.setStrokeWidth(3);
//            arrowHead2.setStrokeWidth(3);
//
//            canvas.getChildren().addAll(arrow, arrowHead1, arrowHead2);
//        } else {
//            // Draw NULL indicator
//            Text nullText = new Text(x + NODE_RADIUS + 15, y + 5, "NULL");
//            nullText.setFont(Font.font("Arial", FontWeight.BOLD, 10));
//            nullText.setFill(Color.web("#e74c3c"));
//            canvas.getChildren().add(nullText);
//        }
//
//        // Add position label
//        Text posLabel = new Text(x - 8, y + NODE_RADIUS + 20, "pos: " + position);
//        posLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 10));
//        posLabel.setFill(Color.web("#95a5a6"));
//        canvas.getChildren().add(posLabel);
//    }
//
//    public void insert(int data) {
//        Node newNode = new Node(data);
//        if (head == null) {
//            head = newNode;
//        } else {
//            Node current = head;
//            while (current.next != null) {
//                current = current.next;
//            }
//            current.next = newNode;
//        }
//        display();
//        animateNewNode();
//    }
//
//    public void insertAt(int position, int data) {
//        if (position < 0) {
//            throw new RuntimeException("Position cannot be negative!");
//        }
//
//        Node newNode = new Node(data);
//
//        if (position == 0) {
//            newNode.next = head;
//            head = newNode;
//        } else {
//            Node current = head;
//            for (int i = 0; i < position - 1 && current != null; i++) {
//                current = current.next;
//            }
//            if (current == null) {
//                throw new RuntimeException("Position " + position + " is out of bounds!");
//            }
//            newNode.next = current.next;
//            current.next = newNode;
//        }
//        display();
//        animateNewNode();
//    }
//
//    public void delete(int data) {
//        if (head == null) {
//            throw new RuntimeException("List is empty! Cannot delete from empty list.");
//        }
//
//        if (head.data == data) {
//            head = head.next;
//            display();
//            return;
//        }
//
//        Node current = head;
//        while (current.next != null && current.next.data != data) {
//            current = current.next;
//        }
//
//        if (current.next == null) {
//            throw new RuntimeException("Element " + data + " not found in list!");
//        }
//
//        current.next = current.next.next;
//        display();
//    }
//
//    public int deleteAt(int position) {
//        if (head == null) {
//            throw new RuntimeException("List is empty! Cannot delete from empty list.");
//        }
//        if (position < 0) {
//            throw new RuntimeException("Position cannot be negative!");
//        }
//
//        if (position == 0) {
//            int data = head.data;
//            head = head.next;
//            display();
//            return data;
//        }
//
//        Node current = head;
//        for (int i = 0; i < position - 1 && current.next != null; i++) {
//            current = current.next;
//        }
//
//        if (current.next == null) {
//            throw new RuntimeException("Position " + position + " is out of bounds!");
//        }
//
//        int data = current.next.data;
//        current.next = current.next.next;
//        display();
//        return data;
//    }
//
//    public void deleteFirst() {
//        if (head == null) {
//            throw new RuntimeException("List is empty! Cannot delete from empty list.");
//        }
//        head = head.next;
//        display();
//    }
//
//    public int search(int data) {
//        Node current = head;
//        int position = 0;
//
//        while (current != null) {
//            if (current.data == data) {
//                display();
//                highlightNode(position);
//                return position;
//            }
//            current = current.next;
//            position++;
//        }
//        throw new RuntimeException("Element " + data + " not found in list!");
//    }
//
//    public int size() {
//        int count = 0;
//        Node current = head;
//        while (current != null) {
//            count++;
//            current = current.next;
//        }
//        return count;
//    }
//
//    public void clear() {
//        head = null;
//        display();
//    }
//
//    private void animateNewNode() {
//        Node current = head;
//        int position = 0;
//
//        // Find the last node
//        while (current != null && current.next != null) {
//            current = current.next;
//            position++;
//        }
//
//        if (current != null) {
//            highlightNode(position);
//        }
//    }
//
//    private void highlightNode(int position) {
//        double x = START_X + position * NODE_SPACING;
//        double y = START_Y;
//
//        Circle highlight = new Circle(x, y, NODE_RADIUS + 5);
//        highlight.setFill(Color.TRANSPARENT);
//        highlight.setStroke(Color.web("#f39c12"));
//        highlight.setStrokeWidth(4);
//
//        canvas.getChildren().add(highlight);
//
//        ScaleTransition st = new ScaleTransition(Duration.millis(800), highlight);
//        st.setFromX(0.8);
//        st.setFromY(0.8);
//        st.setToX(1.2);
//        st.setToY(1.2);
//        st.setAutoReverse(true);
//        st.setCycleCount(4);
//        st.setOnFinished(e -> canvas.getChildren().remove(highlight));
//        st.play();
//    }
//}
//
//// Enhanced Visual Doubly Linked List Implementation
//class VisualDLL {
//    private Pane canvas;
//    private DNode head;
//    private final double NODE_RADIUS = 30;
//    private final double START_X = 80;
//    private final double START_Y = 250;
//    private final double NODE_SPACING = 160;
//
//    class DNode {
//        int data;
//        DNode next;
//        DNode prev;
//        Circle circle;
//        Text text;
//
//        DNode(int data) {
//            this.data = data;
//            this.next = null;
//            this.prev = null;
//        }
//    }
//
//    public VisualDLL(Pane canvas) {
//        this.canvas = canvas;
//        this.head = null;
//    }
//
//    public void display() {
//        canvas.getChildren().clear();
//
//        Text title = new Text(START_X, START_Y - 80, "🔗🔗 Doubly Linked List Visualization");
//        title.setFont(Font.font("Arial", FontWeight.BOLD, 18));
//        title.setFill(Color.web("#2c3e50"));
//        canvas.getChildren().add(title);
//
//        Text desc = new Text(START_X, START_Y - 55, "Bidirectional linked structure with forward and backward pointers");
//        desc.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
//        desc.setFill(Color.web("#7f8c8d"));
//        canvas.getChildren().add(desc);
//
//        if (head == null) {
//            Text emptyText = new Text(START_X, START_Y, "List is empty - Add some elements!");
//            emptyText.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
//            emptyText.setFill(Color.web("#95a5a6"));
//            canvas.getChildren().add(emptyText);
//            return;
//        }
//
//        DNode current = head;
//        int position = 0;
//
//        while (current != null) {
//            drawDNode(current, position);
//            current = current.next;
//            position++;
//        }
//
//        // Add HEAD indicator
//        Text headLabel = new Text(START_X - 30, START_Y - 45, "HEAD");
//        headLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
//        headLabel.setFill(Color.web("#e74c3c"));
//        canvas.getChildren().add(headLabel);
//
//        // Add size info
//        Text sizeInfo = new Text(START_X, START_Y + 100, "Size: " + size() + " nodes");
//        sizeInfo.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
//        sizeInfo.setFill(Color.web("#34495e"));
//        canvas.getChildren().add(sizeInfo);
//    }
//
//    private void drawDNode(DNode node, int position) {
//        double x = START_X + position * NODE_SPACING;
//        double y = START_Y;
//
//        // Create gradient
//        LinearGradient gradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
//                new Stop(0, Color.web("#27ae60")),
//                new Stop(1, Color.web("#2ecc71")));
//
//        // Draw circle
//        Circle circle = new Circle(x, y, NODE_RADIUS);
//        circle.setFill(gradient);
//        circle.setStroke(Color.web("#2c3e50"));
//        circle.setStrokeWidth(3);
//
//        DropShadow shadow = new DropShadow();
//        shadow.setRadius(8);
//        shadow.setOffsetX(3);
//        shadow.setOffsetY(3);
//        shadow.setColor(Color.rgb(0, 0, 0, 0.4));
//        circle.setEffect(shadow);
//
//        node.circle = circle;
//
//        // Draw text
//        Text text = new Text(x - 8, y + 6, String.valueOf(node.data));
//        text.setFont(Font.font("Arial", FontWeight.BOLD, 14));
//        text.setFill(Color.WHITE);
//        node.text = text;
//
//        canvas.getChildren().addAll(circle, text);
//
//        // Draw forward arrow (next)
//        if (node.next != null) {
//            double arrowY = y - 8;
//            Line forwardArrow = new Line(x + NODE_RADIUS + 5, arrowY,
//                    x + NODE_SPACING - NODE_RADIUS - 5, arrowY);
//            forwardArrow.setStroke(Color.web("#3498db"));
//            forwardArrow.setStrokeWidth(3);
//
//            // Forward arrow head
//            double endX = x + NODE_SPACING - NODE_RADIUS - 5;
//            Line fArrowHead1 = new Line(endX - 12, arrowY - 6, endX, arrowY);
//            Line fArrowHead2 = new Line(endX - 12, arrowY + 6, endX, arrowY);
//            fArrowHead1.setStroke(Color.web("#3498db"));
//            fArrowHead2.setStroke(Color.web("#3498db"));
//            fArrowHead1.setStrokeWidth(3);
//            fArrowHead2.setStrokeWidth(3);
//
//            // Add "next" label
//            Text nextLabel = new Text(x + NODE_SPACING/2 - 10, arrowY - 10, "next");
//            nextLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 9));
//            nextLabel.setFill(Color.web("#3498db"));
//
//            canvas.getChildren().addAll(forwardArrow, fArrowHead1, fArrowHead2, nextLabel);
//        }
//
//        // Draw backward arrow (prev)
//        if (node.prev != null) {
//            double arrowY = y + 8;
//            Line backwardArrow = new Line(x - NODE_RADIUS - 5, arrowY,
//                    x - NODE_SPACING + NODE_RADIUS + 5, arrowY);
//            backwardArrow.setStroke(Color.web("#e74c3c"));
//            backwardArrow.setStrokeWidth(3);
//
//            // Backward arrow head
//            double startX = x - NODE_SPACING + NODE_RADIUS + 5;
//            Line bArrowHead1 = new Line(startX + 12, arrowY - 6, startX, arrowY);
//            Line bArrowHead2 = new Line(startX + 12, arrowY + 6, startX, arrowY);
//            bArrowHead1.setStroke(Color.web("#e74c3c"));
//            bArrowHead2.setStroke(Color.web("#e74c3c"));
//            bArrowHead1.setStrokeWidth(3);
//            bArrowHead2.setStrokeWidth(3);
//
//            // Add "prev" label
//            Text prevLabel = new Text(x - NODE_SPACING/2 - 10, arrowY + 20, "prev");
//            prevLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 9));
//            prevLabel.setFill(Color.web("#e74c3c"));
//
//            canvas.getChildren().addAll(backwardArrow, bArrowHead1, bArrowHead2, prevLabel);
//        }
//
//        // Add position label
//        Text posLabel = new Text(x - 8, y + NODE_RADIUS + 20, "pos: " + position);
//        posLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 10));
//        posLabel.setFill(Color.web("#95a5a6"));
//        canvas.getChildren().add(posLabel);
//    }
//
//    public void insert(int data) {
//        DNode newNode = new DNode(data);
//        if (head == null) {
//            head = newNode;
//        } else {
//            DNode current = head;
//            while (current.next != null) {
//                current = current.next;
//            }
//            current.next = newNode;
//            newNode.prev = current;
//        }
//        display();
//    }
//
//    public void insertAt(int position, int data) {
//        if (position < 0) {
//            throw new RuntimeException("Position cannot be negative!");
//        }
//
//        DNode newNode = new DNode(data);
//
//        if (position == 0 || head == null) {
//            newNode.next = head;
//            if (head != null) {
//                head.prev = newNode;
//            }
//            head = newNode;
//        } else {
//            DNode current = head;
//            for (int i = 0; i < position && current != null; i++) {
//                current = current.next;
//            }
//
//            if (current == null) {
//                // Insert at end
//                current = head;
//                while (current.next != null) {
//                    current = current.next;
//                }
//                current.next = newNode;
//                newNode.prev = current;
//            } else {
//                // Insert in middle
//                newNode.next = current;
//                newNode.prev = current.prev;
//                if (current.prev != null) {
//                    current.prev.next = newNode;
//                } else {
//                    head = newNode;
//                }
//                current.prev = newNode;
//            }
//        }
//        display();
//    }
//
//    public void delete(int data) {
//        if (head == null) {
//            throw new RuntimeException("List is empty! Cannot delete from empty list.");
//        }
//
//        DNode current = head;
//        while (current != null && current.data != data) {
//            current = current.next;
//        }
//
//        if (current == null) {
//            throw new RuntimeException("Element " + data + " not found in list!");
//        }
//
//        if (current.prev != null) {
//            current.prev.next = current.next;
//        } else {
//            head = current.next;
//        }
//
//        if (current.next != null) {
//            current.next.prev = current.prev;
//        }
//
//        display();
//    }
//
//    public int deleteAt(int position) {
//        if (head == null) {
//            throw new RuntimeException("List is empty! Cannot delete from empty list.");
//        }
//        if (position < 0) {
//            throw new RuntimeException("Position cannot be negative!");
//        }
//
//        DNode current = head;
//        for (int i = 0; i < position && current != null; i++) {
//            current = current.next;
//        }
//
//        if (current == null) {
//            throw new RuntimeException("Position " + position + " is out of bounds!");
//        }
//
//        int data = current.data;
//
//        if (current.prev != null) {
//            current.prev.next = current.next;
//        } else {
//            head = current.next;
//        }
//
//        if (current.next != null) {
//            current.next.prev = current.prev;
//        }
//
//        display();
//        return data;
//    }
//
//    public void deleteFirst() {
//        if (head == null) {
//            throw new RuntimeException("List is empty! Cannot delete from empty list.");
//        }
//        head = head.next;
//        if (head != null) {
//            head.prev = null;
//        }
//        display();
//    }
//
//    public int search(int data) {
//        DNode current = head;
//        int position = 0;
//
//        while (current != null) {
//            if (current.data == data) {
//                display();
//                highlightNode(position);
//                return position;
//            }
//            current = current.next;
//            position++;
//        }
//        throw new RuntimeException("Element " + data + " not found in list!");
//    }
//
//    public int size() {
//        int count = 0;
//        DNode current = head;
//        while (current != null) {
//            count++;
//            current = current.next;
//        }
//        return count;
//    }
//
//    public void clear() {
//        head = null;
//        display();
//    }
//
//    // Complete the highlightNode method in VisualDLL class
//    private void highlightNode(int position) {
//        double x = START_X + position * NODE_SPACING;
//        double y = START_Y;
//
//        Circle highlight = new Circle(x, y, NODE_RADIUS + 5);
//        highlight.setFill(Color.TRANSPARENT);
//        highlight.setStroke(Color.web("#f39c12"));
//        highlight.setStrokeWidth(4);
//
//        canvas.getChildren().add(highlight);
//
//        FadeTransition ft = new FadeTransition(Duration.millis(600), highlight);
//        ft.setFromValue(0.3);
//        ft.setToValue(1.0);
//        ft.setAutoReverse(true);
//        ft.setCycleCount(6);
//        ft.setOnFinished(e -> canvas.getChildren().remove(highlight));
//        ft.play();
//    }
//
//    // Enhanced Visual Stack Implementation
//    class VisualStack {
//        private Pane canvas;
//        private List<Integer> stack;
//        private final double RECT_WIDTH = 80;
//        private final double RECT_HEIGHT = 40;
//        private final double START_X = 350;
//        private final double START_Y = 500;
//
//        public VisualStack(Pane canvas) {
//            this.canvas = canvas;
//            this.stack = new ArrayList<>();
//        }
//
//        public void display() {
//            canvas.getChildren().clear();
//
//            Text title = new Text(START_X - 50, START_Y - 180, "📚 Stack Visualization (LIFO)");
//            title.setFont(Font.font("Arial", FontWeight.BOLD, 18));
//            title.setFill(Color.web("#2c3e50"));
//            canvas.getChildren().add(title);
//
//            Text desc = new Text(START_X - 50, START_Y - 155, "Last In, First Out - Elements added/removed from top");
//            desc.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
//            desc.setFill(Color.web("#7f8c8d"));
//            canvas.getChildren().add(desc);
//
//            if (stack.isEmpty()) {
//                Text emptyText = new Text(START_X, START_Y - 50, "Stack is empty");
//                emptyText.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
//                emptyText.setFill(Color.web("#95a5a6"));
//                canvas.getChildren().add(emptyText);
//            }
//
//            // Draw stack elements from bottom to top
//            for (int i = 0; i < stack.size(); i++) {
//                drawStackElement(i, stack.get(i), i == stack.size() - 1);
//            }
//
//            // Draw base
//            Rectangle base = new Rectangle(START_X - 10, START_Y + 10, RECT_WIDTH + 20, 10);
//            base.setFill(Color.web("#34495e"));
//            canvas.getChildren().add(base);
//
//            // Add TOP indicator for non-empty stack
//            if (!stack.isEmpty()) {
//                Text topLabel = new Text(START_X + RECT_WIDTH + 20,
//                        START_Y - (stack.size() - 1) * RECT_HEIGHT + 5, "← TOP");
//                topLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
//                topLabel.setFill(Color.web("#e74c3c"));
//                canvas.getChildren().add(topLabel);
//            }
//
//            // Add size info
//            Text sizeInfo = new Text(START_X - 50, START_Y + 40, "Size: " + stack.size() + " elements");
//            sizeInfo.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
//            sizeInfo.setFill(Color.web("#34495e"));
//            canvas.getChildren().add(sizeInfo);
//        }
//
//        private void drawStackElement(int index, int value, boolean isTop) {
//            double x = START_X;
//            double y = START_Y - index * RECT_HEIGHT;
//
//            Rectangle rect = new Rectangle(x, y, RECT_WIDTH, RECT_HEIGHT);
//
//            LinearGradient gradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
//                    new Stop(0, isTop ? Color.web("#e74c3c") : Color.web("#3498db")),
//                    new Stop(1, isTop ? Color.web("#c0392b") : Color.web("#2980b9")));
//
//            rect.setFill(gradient);
//            rect.setStroke(Color.web("#2c3e50"));
//            rect.setStrokeWidth(2);
//
//            DropShadow shadow = new DropShadow();
//            shadow.setRadius(5);
//            shadow.setOffsetX(2);
//            shadow.setOffsetY(2);
//            shadow.setColor(Color.rgb(0, 0, 0, 0.3));
//            rect.setEffect(shadow);
//
//            Text text = new Text(x + RECT_WIDTH/2 - 8, y + RECT_HEIGHT/2 + 6, String.valueOf(value));
//            text.setFont(Font.font("Arial", FontWeight.BOLD, 14));
//            text.setFill(Color.WHITE);
//
//            Text indexText = new Text(x - 25, y + RECT_HEIGHT/2 + 6, String.valueOf(index));
//            indexText.setFont(Font.font("Arial", FontWeight.NORMAL, 10));
//            indexText.setFill(Color.web("#95a5a6"));
//
//            canvas.getChildren().addAll(rect, text, indexText);
//        }
//
//        public void push(int value) {
//            stack.add(value);
//            display();
//            animatePush();
//        }
//
//        public int pop() {
//            if (stack.isEmpty()) {
//                throw new RuntimeException("Stack is empty! Cannot pop from empty stack.");
//            }
//            int value = stack.remove(stack.size() - 1);
//            display();
//            return value;
//        }
//
//        public int peek() {
//            if (stack.isEmpty()) {
//                throw new RuntimeException("Stack is empty! Cannot peek empty stack.");
//            }
//            return stack.get(stack.size() - 1);
//        }
//
//        public int search(int value) {
//            for (int i = stack.size() - 1; i >= 0; i--) {
//                if (stack.get(i) == value) {
//                    return stack.size() - i; // Position from top
//                }
//            }
//            throw new RuntimeException("Element " + value + " not found in stack!");
//        }
//
//        public int size() {
//            return stack.size();
//        }
//
//        public void clear() {
//            stack.clear();
//            display();
//        }
//
//        private void animatePush() {
//            if (!stack.isEmpty()) {
//                double x = START_X;
//                double y = START_Y - (stack.size() - 1) * RECT_HEIGHT;
//
//                Rectangle animRect = new Rectangle(x, y, RECT_WIDTH, RECT_HEIGHT);
//                animRect.setFill(Color.web("#f39c12"));
//                canvas.getChildren().add(animRect);
//
//                ScaleTransition st = new ScaleTransition(Duration.millis(300), animRect);
//                st.setFromX(0.1);
//                st.setFromY(0.1);
//                st.setToX(1.0);
//                st.setToY(1.0);
//                st.setOnFinished(e -> canvas.getChildren().remove(animRect));
//                st.play();
//            }
//        }
//    }
//
//    // Enhanced Visual Queue Implementation
//    class VisualQueue {
//        private Pane canvas;
//        private List<Integer> queue;
//        private final double RECT_WIDTH = 65;
//        private final double RECT_HEIGHT = 45;
//        private final double START_X = 70;
//        private final double START_Y = 300;
//
//        public VisualQueue(Pane canvas) {
//            this.canvas = canvas;
//            this.queue = new ArrayList<>();
//        }
//
//        public void display() {
//            canvas.getChildren().clear();
//
//            Text title = new Text(START_X, START_Y - 80, "🚶‍♂️ Queue Visualization (FIFO)");
//            title.setFont(Font.font("Arial", FontWeight.BOLD, 18));
//            title.setFill(Color.web("#2c3e50"));
//            canvas.getChildren().add(title);
//
//            Text desc = new Text(START_X, START_Y - 55, "First In, First Out - Elements added at rear, removed from front");
//            desc.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
//            desc.setFill(Color.web("#7f8c8d"));
//            canvas.getChildren().add(desc);
//
//            if (queue.isEmpty()) {
//                Text emptyText = new Text(START_X, START_Y, "Queue is empty");
//                emptyText.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
//                emptyText.setFill(Color.web("#95a5a6"));
//                canvas.getChildren().add(emptyText);
//                return;
//            }
//
//            // Draw queue elements
//            for (int i = 0; i < queue.size(); i++) {
//                drawQueueElement(i, queue.get(i), i == 0, i == queue.size() - 1);
//            }
//
//            // Add FRONT and REAR indicators
//            if (!queue.isEmpty()) {
//                Text frontLabel = new Text(START_X - 20, START_Y - 20, "FRONT");
//                frontLabel.setFont(Font.font("Arial", FontWeight.BOLD, 10));
//                frontLabel.setFill(Color.web("#e74c3c"));
//                canvas.getChildren().add(frontLabel);
//
//                Text rearLabel = new Text(START_X + (queue.size() - 1) * (RECT_WIDTH + 8) - 10,
//                        START_Y - 20, "REAR");
//                rearLabel.setFont(Font.font("Arial", FontWeight.BOLD, 10));
//                rearLabel.setFill(Color.web("#27ae60"));
//                canvas.getChildren().add(rearLabel);
//            }
//
//            // Add size info
//            Text sizeInfo = new Text(START_X, START_Y + 80, "Size: " + queue.size() + " elements");
//            sizeInfo.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
//            sizeInfo.setFill(Color.web("#34495e"));
//            canvas.getChildren().add(sizeInfo);
//        }
//
//        private void drawQueueElement(int index, int value, boolean isFront, boolean isRear) {
//            double x = START_X + index * (RECT_WIDTH + 8);
//            double y = START_Y;
//
//            Rectangle rect = new Rectangle(x, y, RECT_WIDTH, RECT_HEIGHT);
//
//            Color color1, color2;
//            if (isFront) {
//                color1 = Color.web("#e74c3c"); color2 = Color.web("#c0392b");
//            } else if (isRear) {
//                color1 = Color.web("#27ae60"); color2 = Color.web("#229954");
//            } else {
//                color1 = Color.web("#3498db"); color2 = Color.web("#2980b9");
//            }
//
//            LinearGradient gradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
//                    new Stop(0, color1), new Stop(1, color2));
//
//            rect.setFill(gradient);
//            rect.setStroke(Color.web("#2c3e50"));
//            rect.setStrokeWidth(2);
//            rect.setArcWidth(8);
//            rect.setArcHeight(8);
//
//            DropShadow shadow = new DropShadow();
//            shadow.setRadius(5);
//            shadow.setOffsetX(2);
//            shadow.setOffsetY(2);
//            shadow.setColor(Color.rgb(0, 0, 0, 0.3));
//            rect.setEffect(shadow);
//
//            Text text = new Text(x + RECT_WIDTH/2 - 8, y + RECT_HEIGHT/2 + 6, String.valueOf(value));
//            text.setFont(Font.font("Arial", FontWeight.BOLD, 14));
//            text.setFill(Color.WHITE);
//
//            canvas.getChildren().addAll(rect, text);
//        }
//
//        public void enqueue(int value) {
//            queue.add(value);
//            display();
//        }
//
//        public int dequeue() {
//            if (queue.isEmpty()) {
//                throw new RuntimeException("Queue is empty! Cannot dequeue from empty queue.");
//            }
//            int value = queue.remove(0);
//            display();
//            return value;
//        }
//
//        public int peek() {
//            if (queue.isEmpty()) {
//                throw new RuntimeException("Queue is empty! Cannot peek empty queue.");
//            }
//            return queue.get(0);
//        }
//
//        public int search(int value) {
//            for (int i = 0; i < queue.size(); i++) {
//                if (queue.get(i) == value) {
//                    return i;
//                }
//            }
//            throw new RuntimeException("Element " + value + " not found in queue!");
//        }
//
//        public int size() {
//            return queue.size();
//        }
//
//        public void clear() {
//            queue.clear();
//            display();
//        }
//    }
//
//    // Enhanced Visual Heap Implementation (Max Heap)
//    class VisualHeap {
//        private Pane canvas;
//        private List<Integer> heap;
//        private final double NODE_RADIUS = 25;
//        private final double START_X = 400;
//        private final double START_Y = 150;
//        private final double LEVEL_HEIGHT = 80;
//
//        public VisualHeap(Pane canvas) {
//            this.canvas = canvas;
//            this.heap = new ArrayList<>();
//        }
//
//        public void display() {
//            canvas.getChildren().clear();
//
//            Text title = new Text(START_X - 150, START_Y - 50, "🏔️ Max Heap Visualization");
//            title.setFont(Font.font("Arial", FontWeight.BOLD, 18));
//            title.setFill(Color.web("#2c3e50"));
//            canvas.getChildren().add(title);
//
//            Text desc = new Text(START_X - 150, START_Y - 25, "Complete binary tree with max heap property");
//            desc.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
//            desc.setFill(Color.web("#7f8c8d"));
//            canvas.getChildren().add(desc);
//
//            if (heap.isEmpty()) {
//                Text emptyText = new Text(START_X - 50, START_Y + 50, "Heap is empty");
//                emptyText.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
//                emptyText.setFill(Color.web("#95a5a6"));
//                canvas.getChildren().add(emptyText);
//                return;
//            }
//
//            // Draw heap nodes
//            for (int i = 0; i < heap.size(); i++) {
//                drawHeapNode(i, heap.get(i));
//            }
//
//            // Add size info
//            Text sizeInfo = new Text(START_X - 150, START_Y + 300, "Size: " + heap.size() + " elements");
//            sizeInfo.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
//            sizeInfo.setFill(Color.web("#34495e"));
//            canvas.getChildren().add(sizeInfo);
//        }
//
//        private void drawHeapNode(int index, int value) {
//            int level = (int) Math.floor(Math.log(index + 1) / Math.log(2));
//            int positionInLevel = index - (int) Math.pow(2, level) + 1;
//            int maxNodesInLevel = (int) Math.pow(2, level);
//
//            double x = START_X - (maxNodesInLevel - 1) * NODE_RADIUS + positionInLevel * 2 * NODE_RADIUS;
//            double y = START_Y + level * LEVEL_HEIGHT;
//
//            // Draw connection to parent
//            if (index > 0) {
//                int parentIndex = (index - 1) / 2;
//                int parentLevel = (int) Math.floor(Math.log(parentIndex + 1) / Math.log(2));
//                int parentPosInLevel = parentIndex - (int) Math.pow(2, parentLevel) + 1;
//                int parentMaxNodes = (int) Math.pow(2, parentLevel);
//
//                double parentX = START_X - (parentMaxNodes - 1) * NODE_RADIUS + parentPosInLevel * 2 * NODE_RADIUS;
//                double parentY = START_Y + parentLevel * LEVEL_HEIGHT;
//
//                Line connection = new Line(parentX, parentY + NODE_RADIUS, x, y - NODE_RADIUS);
//                connection.setStroke(Color.web("#34495e"));
//                connection.setStrokeWidth(2);
//                canvas.getChildren().add(connection);
//            }
//
//            Circle circle = new Circle(x, y, NODE_RADIUS);
//            LinearGradient gradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
//                    new Stop(0, Color.web("#9b59b6")),
//                    new Stop(1, Color.web("#8e44ad")));
//            circle.setFill(gradient);
//            circle.setStroke(Color.web("#2c3e50"));
//            circle.setStrokeWidth(2);
//
//            DropShadow shadow = new DropShadow();
//            shadow.setRadius(5);
//            shadow.setOffsetX(2);
//            shadow.setOffsetY(2);
//            shadow.setColor(Color.rgb(0, 0, 0, 0.3));
//            circle.setEffect(shadow);
//
//            Text text = new Text(x - 8, y + 6, String.valueOf(value));
//            text.setFont(Font.font("Arial", FontWeight.BOLD, 12));
//            text.setFill(Color.WHITE);
//
//            Text indexText = new Text(x - 8, y - NODE_RADIUS - 10, String.valueOf(index));
//            indexText.setFont(Font.font("Arial", FontWeight.NORMAL, 9));
//            indexText.setFill(Color.web("#95a5a6"));
//
//            canvas.getChildren().addAll(circle, text, indexText);
//        }
//
//        public void insert(int value) {
//            heap.add(value);
//            heapifyUp(heap.size() - 1);
//            display();
//        }
//
//        public int extractMax() {
//            if (heap.isEmpty()) {
//                throw new RuntimeException("Heap is empty! Cannot extract from empty heap.");
//            }
//            int max = heap.get(0);
//            heap.set(0, heap.get(heap.size() - 1));
//            heap.remove(heap.size() - 1);
//            if (!heap.isEmpty()) {
//                heapifyDown(0);
//            }
//            display();
//            return max;
//        }
//
//        public int peekMax() {
//            if (heap.isEmpty()) {
//                throw new RuntimeException("Heap is empty! Cannot peek empty heap.");
//            }
//            return heap.get(0);
//        }
//
//        public boolean search(int value) {
//            return heap.contains(value);
//        }
//
//        public int size() {
//            return heap.size();
//        }
//
//        public void clear() {
//            heap.clear();
//            display();
//        }
//
//        private void heapifyUp(int index) {
//            if (index > 0) {
//                int parentIndex = (index - 1) / 2;
//                if (heap.get(index) > heap.get(parentIndex)) {
//                    Collections.swap(heap, index, parentIndex);
//                    heapifyUp(parentIndex);
//                }
//            }
//        }
//
//        private void heapifyDown(int index) {
//            int leftChild = 2 * index + 1;
//            int rightChild = 2 * index + 2;
//            int largest = index;
//
//            if (leftChild < heap.size() && heap.get(leftChild) > heap.get(largest)) {
//                largest = leftChild;
//            }
//            if (rightChild < heap.size() && heap.get(rightChild) > heap.get(largest)) {
//                largest = rightChild;
//            }
//            if (largest != index) {
//                Collections.swap(heap, index, largest);
//                heapifyDown(largest);
//            }
//        }
//    }
//
//    // Enhanced Visual Binary Search Tree Implementation
//    class VisualBST {
//        private Pane canvas;
//        private TreeNode root;
//        private final double NODE_RADIUS = 25;
//        private final double START_X = 400;
//        private final double START_Y = 120;
//        private final double LEVEL_HEIGHT = 70;
//
//        class TreeNode {
//            int data;
//            TreeNode left;
//            TreeNode right;
//
//            TreeNode(int data) {
//                this.data = data;
//                this.left = null;
//                this.right = null;
//            }
//        }
//
//        public VisualBST(Pane canvas) {
//            this.canvas = canvas;
//            this.root = null;
//        }
//
//        public void display() {
//            canvas.getChildren().clear();
//
//            Text title = new Text(START_X - 150, START_Y - 50, "🌳 Binary Search Tree Visualization");
//            title.setFont(Font.font("Arial", FontWeight.BOLD, 18));
//            title.setFill(Color.web("#2c3e50"));
//            canvas.getChildren().add(title);
//
//            Text desc = new Text(START_X - 150, START_Y - 25, "Ordered binary tree: Left < Root < Right");
//            desc.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
//            desc.setFill(Color.web("#7f8c8d"));
//            canvas.getChildren().add(desc);
//
//            if (root == null) {
//                Text emptyText = new Text(START_X - 50, START_Y + 50, "BST is empty");
//                emptyText.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
//                emptyText.setFill(Color.web("#95a5a6"));
//                canvas.getChildren().add(emptyText);
//                return;
//            }
//
//            drawBSTNode(root, START_X, START_Y, 150, 0);
//
//            Text sizeInfo = new Text(START_X - 150, START_Y + 350, "Size: " + size() + " nodes");
//            sizeInfo.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
//            sizeInfo.setFill(Color.web("#34495e"));
//            canvas.getChildren().add(sizeInfo);
//        }
//
//        private void drawBSTNode(TreeNode node, double x, double y, double xOffset, int level) {
//            if (node == null) return;
//
//            // Draw connections first
//            if (node.left != null) {
//                double leftX = x - xOffset / Math.pow(2, level);
//                double leftY = y + LEVEL_HEIGHT;
//                Line leftLine = new Line(x - NODE_RADIUS/2, y + NODE_RADIUS/2,
//                        leftX + NODE_RADIUS/2, leftY - NODE_RADIUS/2);
//                leftLine.setStroke(Color.web("#34495e"));
//                leftLine.setStrokeWidth(2);
//                canvas.getChildren().add(leftLine);
//
//                drawBSTNode(node.left, leftX, leftY, xOffset, level + 1);
//            }
//
//            if (node.right != null) {
//                double rightX = x + xOffset / Math.pow(2, level);
//                double rightY = y + LEVEL_HEIGHT;
//                Line rightLine = new Line(x + NODE_RADIUS/2, y + NODE_RADIUS/2,
//                        rightX - NODE_RADIUS/2, rightY - NODE_RADIUS/2);
//                rightLine.setStroke(Color.web("#34495e"));
//                rightLine.setStrokeWidth(2);
//                canvas.getChildren().add(rightLine);
//
//                drawBSTNode(node.right, rightX, rightY, xOffset, level + 1);
//            }
//
//            // Draw node
//            Circle circle = new Circle(x, y, NODE_RADIUS);
//            LinearGradient gradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
//                    new Stop(0, Color.web("#e67e22")),
//                    new Stop(1, Color.web("#d35400")));
//            circle.setFill(gradient);
//            circle.setStroke(Color.web("#2c3e50"));
//            circle.setStrokeWidth(2);
//
//            DropShadow shadow = new DropShadow();
//            shadow.setRadius(5);
//            shadow.setOffsetX(2);
//            shadow.setOffsetY(2);
//            shadow.setColor(Color.rgb(0, 0, 0, 0.3));
//            circle.setEffect(shadow);
//
//            Text text = new Text(x - 8, y + 6, String.valueOf(node.data));
//            text.setFont(Font.font("Arial", FontWeight.BOLD, 12));
//            text.setFill(Color.WHITE);
//
//            canvas.getChildren().addAll(circle, text);
//        }
//
//        public void insert(int data) {
//            root = insertRec(root, data);
//            display();
//        }
//
//        private TreeNode insertRec(TreeNode root, int data) {
//            if (root == null) {
//                return new TreeNode(data);
//            }
//            if (data < root.data) {
//                root.left = insertRec(root.left, data);
//            } else if (data > root.data) {
//                root.right = insertRec(root.right, data);
//            }
//            return root;
//        }
//
//        public void delete(int data) {
//            root = deleteRec(root, data);
//            display();
//        }
//
//        private TreeNode deleteRec(TreeNode root, int data) {
//            if (root == null) {
//                throw new RuntimeException("Element " + data + " not found in BST!");
//            }
//
//            if (data < root.data) {
//                root.left = deleteRec(root.left, data);
//            } else if (data > root.data) {
//                root.right = deleteRec(root.right, data);
//            } else {
//                if (root.left == null) return root.right;
//                else if (root.right == null) return root.left;
//
//                root.data = minValue(root.right);
//                root.right = deleteRec(root.right, root.data);
//            }
//            return root;
//        }
//
//        private int minValue(TreeNode root) {
//            while (root.left != null) {
//                root = root.left;
//            }
//            return root.data;
//        }
//
//        public boolean search(int data) {
//            return searchRec(root, data);
//        }
//
//        private boolean searchRec(TreeNode root, int data) {
//            if (root == null) return false;
//            if (root.data == data) return true;
//            return data < root.data ? searchRec(root.left, data) : searchRec(root.right, data);
//        }
//
//        public int size() {
//            return sizeRec(root);
//        }
//
//        private int sizeRec(TreeNode root) {
//            if (root == null) return 0;
//            return 1 + sizeRec(root.left) + sizeRec(root.right);
//        }
//
//        public int[] getMinMax() {
//            if (root == null) return null;
//            return new int[]{findMin(root), findMax(root)};
//        }
//
//        private int findMin(TreeNode root) {
//            while (root.left != null) {
//                root = root.left;
//            }
//            return root.data;
//        }
//
//        private int findMax(TreeNode root) {
//            while (root.right != null) {
//                root = root.right;
//            }
//            return root.data;
//        }
//
//        public void clear() {
//            root = null;
//            display();
//        }
//    }

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