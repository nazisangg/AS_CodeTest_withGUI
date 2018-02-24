package sample.Model;

import com.sun.javafx.tools.ant.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TableData_Course {
    private final StringProperty courseName;

    private final IntegerProperty id;

    private CourseModel courseModel;


    public TableData_Course(String courseName, int id, CourseModel courseModel){
        this.courseName = new SimpleStringProperty(courseName);
        this.id = new SimpleIntegerProperty(id);
        this.courseModel = courseModel;


    }

    public String getCourseName() {
        return courseName.get();
    }

    public StringProperty courseNameProperty() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName.set(courseName);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public CourseModel getCourseModel() {
        return courseModel;
    }

    public void setCourseModel(CourseModel courseModel) {
        this.courseModel = courseModel;
    }
}
