package sample.UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.Controller.CSVController;
import sample.Controller.SearchController;
import sample.Model.CourseModel;
import sample.Model.TableData_Course;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main extends Application {

    private List<CourseModel> totalCourse;

    private List<CourseModel> totalCourse_copy;

    private CSVController csvController;

    private Stage primaryStage;

    private ObservableList<TableData_Course> courseData = FXCollections.observableArrayList();

    private SearchController searchController;

    private HashMap<String,CourseModel> courseModelHashMap;

    private totalCoursesTableController controller = new totalCoursesTableController();


    public void setUp() throws Exception{
        csvController = new CSVController();
        csvController.buildCourses();
        this.totalCourse = csvController.getAllCourses();
        System.out.println("lalal"+ csvController.getAllCourses().size());
        List<CourseModel> tagetList = new ArrayList<>();
        List<CourseModel> DoneCourses = new ArrayList<>();
        this.searchController = new SearchController(DoneCourses,tagetList,totalCourse);
        this.courseModelHashMap = csvController.getCourses_Hash();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        System.out.println("start");
        this.primaryStage = primaryStage;
        primaryStage.setTitle("AlexSolution App");
        initRootLayout();
    }

    public ObservableList<TableData_Course> getTotalCourse() throws Exception{

        for(CourseModel course:totalCourse){
            TableData_Course value = new TableData_Course(course.getCourseName(),course.getId(),course);
            courseData.add(value);

        }
        return this.courseData;
    }

    public void initRootLayout() throws Exception {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("course.fxml"));
            // Show the scene containing the root layout.
            AnchorPane personOverview = (AnchorPane) loader.load();
            Scene scene = new Scene(personOverview);
            // Set person overview into the center of root layout.

            // Give the controller access to the main app.
            totalCoursesTableController controller = loader.getController();
            this.setUp();
            controller.setCourseHashMap(courseModelHashMap);
            System.out.println("here"+totalCourse.size());
            controller.setTotalCourses(this.totalCourse);
            controller.setMainApp(this);
            //controller.setText();
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getText(List<CourseModel> DoneCourses, List<CourseModel> allCourses) throws Exception {
        String text = "";
        System.out.println(allCourses.size());
        searchController.setCoursesDone(DoneCourses);
        searchController.setTotalCourses(allCourses);
        HashMap<Integer,List<CourseModel>> courseOrder = searchController.findAllCourseOrder();
        System.out.println("here3");
        for(Integer i = 0; i < courseOrder.size(); i++ ){
            for(CourseModel course: courseOrder.get(i)){
                text = text + "Level " + i + " " + course.getCourseName() + "\n";
            }
        }
        return text;

    }





    public static void main(String[] args) {


        launch(args);
    }
}
