package sample.UI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import sample.UI.Main;
import sample.Model.CourseModel;
import sample.Model.TableData_Course;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class totalCoursesTableController {


/*
* Annotate the bean of javaFX and connect to course.fxml
* */

/*
* Objects points to element in "All Courses" Table.
* */
    @FXML
    private TableView<TableData_Course> courseTableView;

    @FXML
    private TableColumn<TableData_Course, String> courseNameColumn;

    @FXML
    private TableColumn<TableData_Course, Integer> idColumn;

    @FXML
    private Label courseNameLabel;

    @FXML
    Label idLabel;

    private Main mainApp;

    private TableData_Course courseSelected;

    @FXML
    private ObservableList<TableData_Course> data;

    public totalCoursesTableController() {

    }

/*
* Objects points to element in "Courses Done" Table.
* */
    @FXML
    private TableView<TableData_Course> courseDoneTable;

    @FXML
    private TableColumn<TableData_Course, String> doneTableCourseName;

    @FXML
    ObservableList<TableData_Course> doneData = FXCollections.observableArrayList();

/*
* Add all controllers to the GUI while init
* */
    @FXML
    private void initialize() {
        // Initialize the table with the two columns.
        courseNameColumn.setCellValueFactory(cellData -> cellData.getValue().courseNameProperty());
        doneTableCourseName.setCellValueFactory(cellData -> cellData.getValue().courseNameProperty());
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());

        // Add listener to "All Courses" Table column when they are selected
        courseTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        this.courseSelected = newValue;
                    }
                });

        // Add event handler to add button
        addButton.setOnAction((ActionEvent e) -> {

                        if (!(courseDone.contains(this.courseSelected.getCourseModel()))) {
                            this.courseDone.add(this.courseSelected.getCourseModel());
                            doneData.add(new TableData_Course(courseSelected.getCourseName(), courseSelected.getId(), courseSelected.getCourseModel()));
                            courseDoneTable.setItems(doneData);
                        }

                }
        );


        // Add event handler to clear button
        clearButton.setOnAction((ActionEvent e) -> this.resetTable());

        // Add event handler to submit button
        submitButton.setOnAction((ActionEvent e) -> {
            try {
                this.submitButton_handleclick();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

    }


    public void setMainApp(Main main) throws Exception {
        this.mainApp = main;

        // Add observable list data to the table
        courseTableView.setItems(mainApp.getTotalCourse());
    }

    /*
    * add button Controller
    *
    * */
    private List<CourseModel> courseDone = new ArrayList<>();

    private HashMap<String, CourseModel> courseHashMap;

    @FXML
    private Button addButton = new Button();

    public void setCourseHashMap(HashMap<String, CourseModel> courseHashMap) {
        this.courseHashMap = courseHashMap;
    }


    /*
    * Clear button controller
    * */
    @FXML
    private Button clearButton = new Button();

    public void resetTable() {
        this.courseDone = new ArrayList<>();
        this.doneData = FXCollections.observableArrayList();
        this.text = "";
        this.setValues();
        courseDoneTable.setItems(doneData);
        courseTableView.getSelectionModel().selectedItemProperty().removeListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        this.courseSelected = newValue;
                    }
                });
    }

    /*
    * Result label controller
    * */
    @FXML
    private Label resultLabel = new Label();

    private List<CourseModel> totalCourses = new ArrayList<>();

    private String text;

    public void setValues() {
        resultLabel.setText(text);

    }

    public void setText() {
        //System.out.println("hehehe"+this.totalCourses.size());
        List<CourseModel> courses = this.getAllCourses();
        try {
            this.text = this.mainApp.getText(courseDone, courses);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<CourseModel> getAllCourses() {
        Collection<CourseModel> courseArray = this.courseHashMap.values();
        List<CourseModel> courseList = new ArrayList<>();
        courseList.addAll(courseArray);
        return courseList;
    }

    public void setTotalCourses(List<CourseModel> totalCourses) {
        this.totalCourses = totalCourses;

    }



/*
* Submit button controller
* */

    @FXML
    private Button submitButton = new Button();

    public void submitButton_handleclick() throws Exception {
        this.setText();
        this.setValues();
        courseTableView.getSelectionModel().selectedItemProperty().removeListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        this.courseSelected = newValue;
                    }
                });
    }
}





