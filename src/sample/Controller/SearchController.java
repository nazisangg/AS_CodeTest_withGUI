package sample.Controller;

import sample.Model.CourseModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class SearchController {
    private List<CourseModel> coursesDone;
    private List<CourseModel> targetCourses;
    private List<CourseModel> preRequisiteList = new ArrayList<>();
    private List<CourseModel> totalCourses;

    public SearchController(List<CourseModel> coursesDone, List<CourseModel> targetCourses, List<CourseModel> totalCourses){
        this.coursesDone = coursesDone;
        this.targetCourses = targetCourses;
        this.totalCourses = totalCourses;
    }


/*
* Search the topology and find all possiable courses
* */

    // Return true if all preRequite is done
    public boolean ifCourseCanBeEnroll(CourseModel course){
        boolean enrollJudge = true;
        List<CourseModel> preRequisites = course.getPrerequisite();
        for(CourseModel preRequisite:preRequisites){
            if(!this.coursesDone.contains(preRequisite)){
                enrollJudge = false;
                break;
            }
        }
        return enrollJudge;
    }

    // If list A contains element which is also contained by list B, delete it from list A
    public List deleteFromList(List list_from, List list_delete){
        Iterator iterator = list_from.iterator();

        while (iterator.hasNext()){
            Object object_from = iterator.next();
            for (Object object_delete: list_delete){
                if(object_from == object_delete){
                    iterator.remove();
                }
            }
        }
        return list_from;
    }

    // find all possiable courses and return them with a hashmap format
    public HashMap<Integer,List<CourseModel>> findAllCourseOrder(){
        HashMap<Integer,List<CourseModel>> orderedCourseList = new HashMap<>();
        List<CourseModel> undoneCourses = deleteFromList(this.totalCourses, this.coursesDone);
        int index = 0;
        while (!undoneCourses.isEmpty()){
            List<CourseModel> coursesCanBeEnrolled = courseSearchLoop(undoneCourses);

            // sometimes some courses can never be enrolled, break the loop then.
            if(coursesCanBeEnrolled.size() == 0){
                break;
            }
            orderedCourseList.put(index,coursesCanBeEnrolled);
            index++;
            this.coursesDone.addAll(coursesCanBeEnrolled);
            undoneCourses = deleteFromList(undoneCourses, this.coursesDone);
        }
        return orderedCourseList;
    }

    // for all course have not been learned, record all that can be enrolled.
    public List<CourseModel> courseSearchLoop(List<CourseModel> UndoneCourses){
        List<CourseModel> temporaryList = new ArrayList<>();
        for(CourseModel course: UndoneCourses){
            if(ifCourseCanBeEnroll(course)){
                temporaryList.add(course);
            }
        }
        return temporaryList;
    }

    public void setCoursesDone(List<CourseModel> coursesDone) {
        this.coursesDone = coursesDone;
    }

    public void setTotalCourses(List<CourseModel> totalCourses) {
        this.totalCourses = totalCourses;
    }
}
