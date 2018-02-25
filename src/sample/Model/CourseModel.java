package sample.Model;

import java.util.ArrayList;
import java.util.List;

/*
* This model contains information of courses including id, course name and prerequisite.
* */
public class CourseModel {

    private int id;
    private String courseName;
    private List<CourseModel> prerequisite = new ArrayList();

    public CourseModel(int id, String courseName) {
        this.id = id;
        this.courseName = courseName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void addElementToPreRequisite(CourseModel prerequisite) {
        this.prerequisite.add(prerequisite);
    }

    public List<CourseModel> getPrerequisite() {
        return prerequisite;
    }
}
