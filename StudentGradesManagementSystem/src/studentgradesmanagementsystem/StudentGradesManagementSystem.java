package studentgradesmanagementsystem;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import studentgradesmanagementsystem.model.ArrayBag;
import studentgradesmanagementsystem.model.Student;

public class StudentGradesManagementSystem extends Application {

    private String[] ids = new String[100];
    private String[] names = new String[100];
    private double[] grades = new double[100];

    private int count = 0;
    private ArrayBag<Double> gradeBag
            = new ArrayBag<>(100);
    private ObservableList<Student> students
            = FXCollections.observableArrayList();

    @Override
    public void start(Stage stage) {

        Label lblId = new Label("Student ID");
        final TextField txtId = new TextField();

        Label lblName = new Label("Student Name");
        final TextField txtName = new TextField();

        Label lblGrade = new Label("Grade");
        final TextField txtGrade = new TextField();

        Button btnAdd = new Button("Add");
        Button btnSearch = new Button("Search");
        Button btnRemove = new Button("Remove");
        Button btnClear = new Button("Clear");
        Button btnFrequency = new Button("Frequency");
        Button btnContains = new Button("Contains");

        Label lblTotal = new Label("Total Students: 0");
        Label lblAverage = new Label("Average Grade: 0");
        Label lblHighest = new Label("Highest Grade: 0");
        Label lblLowest = new Label("Lowest Grade: 0");
        Label lblFail = new Label("Fail: 0");
        Label lblPass = new Label("Pass: 0");
        Label lblGood = new Label("Good: 0");
        Label lblVeryGood = new Label("Very Good: 0");
        Label lblExcellent = new Label("Excellent: 0");
        PieChart pieChart = new PieChart();
        pieChart.setPrefSize(180, 180);

        btnAdd.setOnAction(e -> {

            String id = txtId.getText();
            String name = txtName.getText();
            if (txtId.getText().isEmpty()
                    || txtName.getText().isEmpty()
                    || txtGrade.getText().isEmpty()) {

                System.out.println("Fill all fields");

                return;
            }
            double grade = Double.parseDouble(txtGrade.getText());
            if (grade < 0 || grade > 100) {

                System.out.println("Grade must be between 0 and 100");

                return;
            }
            for (int i = 0; i < count; i++) {

                if (ids[i].equals(id)) {

                    System.out.println("Duplicate ID");

                    return;
                }
            }

            ids[count] = id;
            names[count] = name;
            grades[count] = grade;
            gradeBag.add(grade);

            count++;
            students.add(
                    new Student(id, name, grade)
            );

            double sum = 0;
            double highest = grades[0];
            double lowest = grades[0];

            for (int i = 0; i < count; i++) {

                sum += grades[i];

                if (grades[i] > highest) {
                    highest = grades[i];
                }

                if (grades[i] < lowest) {
                    lowest = grades[i];
                }
            }

            double average = sum / count;
            int fail = 0;
            int pass = 0;
            int good = 0;
            int veryGood = 0;
            int excellent = 0;

            for (int i = 0; i < count; i++) {

                if (grades[i] < 60) {
                    fail++;
                } else if (grades[i] < 70) {
                    pass++;
                } else if (grades[i] < 80) {
                    good++;
                } else if (grades[i] < 90) {
                    veryGood++;
                } else {
                    excellent++;
                }
            }

            lblTotal.setText("Total Students: " + count);

            lblAverage.setText("Average Grade: " + average);

            lblHighest.setText("Highest Grade: " + highest);

            lblLowest.setText("Lowest Grade: " + lowest);
            lblFail.setText("Fail: " + fail);

            lblPass.setText("Pass: " + pass);

            lblGood.setText("Good: " + good);

            lblVeryGood.setText("Very Good: " + veryGood);

            lblExcellent.setText("Excellent: " + excellent);
            pieChart.getData().clear();

            pieChart.getData().add(
                    new PieChart.Data("Fail", fail));

            pieChart.getData().add(
                    new PieChart.Data("Pass", pass));

            pieChart.getData().add(
                    new PieChart.Data("Good", good));

            pieChart.getData().add(
                    new PieChart.Data("Very Good", veryGood));

            pieChart.getData().add(
                    new PieChart.Data("Excellent", excellent));

            txtId.clear();
            txtName.clear();
            txtGrade.clear();

            System.out.println("Student Added");
        });
        btnFrequency.setOnAction(e -> {

            double grade
                    = Double.parseDouble(
                            txtGrade.getText()
                    );

            int freq
                    = gradeBag.frequencyOf(grade);

            System.out.println(
                    "Frequency = " + freq
            );
        });
        btnContains.setOnAction(e -> {

            double grade
                    = Double.parseDouble(
                            txtGrade.getText()
                    );

            if (gradeBag.contains(grade)) {
                System.out.println("Grade Exists");
            } else {
                System.out.println("Grade Not Found");
            }
        });
        btnSearch.setOnAction(e -> {

            String searchId = txtId.getText();

            for (int i = 0; i < count; i++) {

                if (ids[i].equals(searchId)) {

                    txtName.setText(names[i]);
                    txtGrade.setText(
                            String.valueOf(grades[i])
                    );

                    return;
                }
            }

            System.out.println("Student Not Found");
        });
        btnRemove.setOnAction(e -> {

            String id = txtId.getText();

            for (int i = 0; i < count; i++) {

                if (ids[i].equals(id)) {

                    ids[i] = ids[count - 1];
                    names[i] = names[count - 1];
                    grades[i] = grades[count - 1];

                    count--;

                    students.clear();

                    for (int j = 0; j < count; j++) {

                        students.add(
                                new Student(
                                        ids[j],
                                        names[j],
                                        grades[j]
                                )
                        );
                    }

                    txtId.clear();
                    txtName.clear();
                    txtGrade.clear();

                    System.out.println("Student Removed");

                    return;
                }
            }

            System.out.println("Student Not Found");
        });
        btnClear.setOnAction(e -> {

            txtId.clear();
            txtName.clear();
            txtGrade.clear();

        });

        VBox root = new VBox(10);

        root.getChildren().addAll(
                lblId, txtId,
                lblName, txtName,
                lblGrade, txtGrade,
                btnAdd,
                btnSearch,
                btnRemove,
                btnClear,
                btnFrequency,
                btnContains,
                lblTotal,
                lblAverage,
                lblHighest,
                lblLowest,
                lblFail,
                lblPass,
                lblGood,
                lblVeryGood,
                lblExcellent
        //pieChart

        );
        TableView<Student> table = new TableView<>();

        table.setPrefHeight(250);
        table.setPrefWidth(700);

        TableColumn<Student, String> colId
                = new TableColumn<>("ID");

        TableColumn<Student, String> colName
                = new TableColumn<>("Name");

        TableColumn<Student, Double> colGrade
                = new TableColumn<>("Grade");
        colId.setCellValueFactory(
                new PropertyValueFactory<>("id"));

        colName.setCellValueFactory(
                new PropertyValueFactory<>("name"));

        colGrade.setCellValueFactory(
                new PropertyValueFactory<>("grade"));

        table.getColumns().addAll(
                colId,
                colName,
                colGrade
        );
        table.setItems(students);

        root.getChildren().add(table);
        root.getChildren().add(pieChart);

        Scene scene = new Scene(root, 1200, 1000);

        stage.setTitle("Student Grades Management System");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
