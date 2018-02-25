package sample.Controller;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
import sample.Model.CourseModel;
import sample.UI.Main;


public class CSVController {

/*
* URL Relevant for jar purpose
* */

    final String COURSEFILENAME = "/courses.csv";
    final String PREREQUISITEFILENAME = "/prerequisites.csv";


    public CSVReader getReader(String CSVName) throws Exception{
        CSVReader csvReader = new CSVReader(new FileReader(CSVName), ',');
        return csvReader;
    }

    // find the jar location and return the url of csv files
    public String getPath(String source) throws URISyntaxException {
        CodeSource codeSource = Main.class.getProtectionDomain().getCodeSource();
        File jarFile = new File(codeSource.getLocation().toURI().getPath());
        String jarDir = jarFile.getParentFile().getPath();
        return (jarDir + source);
    }

    private CSVReader csvReader_Course = getReader(getPath(COURSEFILENAME));

    private CSVReader csvReader_PreRequisite= getReader(getPath(PREREQUISITEFILENAME));

    private HashMap<String, CourseModel> courses_Hash = new HashMap();

    public CSVController() throws Exception {

    }



/*
* Build the reflection between courses and preRequisite
* */

    public void buildCourses() throws IOException {
        int i = 0;
        int j = 0;
        String[] newline;
        // Build Hashmap with id and courseModel.
        while ((newline = this.csvReader_Course.readNext())!= null){
            if (i>0){
                String key = newline[0];
                String courseName = newline[1];
                CourseModel course = new CourseModel(Integer.parseInt(key),courseName);
                this.courses_Hash.put(key, course);
            }
            i++;
        }

        // Map preRequisite with courses
        while ((newline = this.csvReader_PreRequisite.readNext())!= null){
            if (j>0){
                String courseKey = newline[0];
                String preRequisiteKey = newline[1];
                CourseModel course = this.courses_Hash.get(courseKey);
                CourseModel preRequisiteCourseName = this.courses_Hash.get(preRequisiteKey);
                course.addElementToPreRequisite(preRequisiteCourseName);
            }
            j++;
        }
    }

/*
* Getter and Setters
* */

    public HashMap<String, CourseModel> getCourses_Hash() {
        return courses_Hash;
    }


    // Get the List of all courses from Hashmap
    public List<CourseModel> getAllCourses(){
        Collection<CourseModel> courseArray = this.courses_Hash.values();
        List<CourseModel> courseList = new ArrayList<>();
        courseList.addAll(courseArray);
        return courseList;
    }

    public CSVReader getCsvReader_Course() {
        return csvReader_Course;
    }

    public void setCsvReader_Course(CSVReader csvReader_Course) {
        this.csvReader_Course = csvReader_Course;
    }

    public CSVReader getCsvReader_PreRequisite() {
        return csvReader_PreRequisite;
    }

    public void setCsvReader_PreRequisite(CSVReader csvReader_PreRequisite) {
        this.csvReader_PreRequisite = csvReader_PreRequisite;
    }
}
