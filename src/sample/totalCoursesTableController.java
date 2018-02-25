package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import sample.Main;
import sample.Model.CourseModel;
import sample.Model.TableData_Course;
import javafx.scene.control.TableView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class totalCoursesTableController {

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

    public totalCoursesTableController(){

    }
/*
* Done Map Part
* */
    @FXML
    private TableView<TableData_Course> courseDoneTable;

    @FXML
    private TableColumn<TableData_Course, String> doneTableCourseName;

    @FXML ObservableList<TableData_Course> doneData = FXCollections.observableArrayList();


    @FXML
    private void initialize() {
        // Initialize the table with the two columns.
        //System.out.println("init");
        courseNameColumn.setCellValueFactory(cellData -> cellData.getValue().courseNameProperty());
        doneTableCourseName.setCellValueFactory(cellData -> cellData.getValue().courseNameProperty());
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());

        courseTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if(newValue!=null){
                        this.courseSelected = newValue;
                    }
                });
        addButton.setOnAction((ActionEvent e) -> {
            //System.out.println(courseSelected.getCourseModel().getCourseName());
            if(!(courseDone.contains(this.courseSelected.getCourseModel()))) {
                this.courseDone.add(this.courseSelected.getCourseModel());
                doneData.add(new TableData_Course(courseSelected.getCourseName(), courseSelected.getId(), courseSelected.getCourseModel()));
                //System.out.println(doneData.size());
                courseDoneTable.setItems(doneData);
            }
        }
        );

        clearButton.setOnAction((ActionEvent e) -> this.resetTable());

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
* add button part
*
* */
    private List<CourseModel> courseDone = new ArrayList<>();

    private HashMap<String, CourseModel> courseHashMap;

    @FXML
    private Button addButton = new Button();

    public void setCourseHashMap(HashMap<String, CourseModel> courseHashMap){
        this.courseHashMap = courseHashMap;
    }


/*
* Clear button
* */
    @FXML
    private Button clearButton = new Button();

    public void resetTable(){
        this.courseDone = new ArrayList<>();
        this.doneData = FXCollections.observableArrayList();
        this.text = "";
        this.setValues();
        courseDoneTable.setItems(doneData);
    }

/*
* Result label
* */
    @FXML
    private Label resultLabel = new Label();

    private List<CourseModel> totalCourses = new ArrayList<>();

    private String text;

    public void setValues(){
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

    public List<CourseModel> getAllCourses(){
        Collection<CourseModel> courseArray = this.courseHashMap.values();
        List<CourseModel> courseList = new ArrayList<>();
        courseList.addAll(courseArray);
        return courseList;
    }

    public void setTotalCourses(List<CourseModel> totalCourses){
        this.totalCourses = totalCourses;

    }



/*
* Submit button
* */

    @FXML
    private Button submitButton= new Button();

    public void submitButton_handleclick() throws Exception {
        this.setText();
        this.setValues();
        courseTableView.getSelectionModel().selectedItemProperty().removeListener(
                (observable, oldValue, newValue) -> {
                    if(newValue!=null){
                        this.courseSelected = newValue;
                    }
                });
    }




}
