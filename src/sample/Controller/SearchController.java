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
    // if the preRequiste has not been searched, add it into targetCourse
    public void updateTargetCourses(CourseModel course){
        List<CourseModel> preRequisites = course.getPrerequisite();
        for(CourseModel preRequisite:preRequisites){
            if(!this.preRequisiteList.contains(preRequisite)){
                this.targetCourses.add(preRequisite);
            }
        }
    }

    public void updatePreRequisiteList(CourseModel course){
        if(!this.preRequisiteList.contains(course)){
            this.preRequisiteList.add(course);
        }
    }
    // Function Block: Find all possiable courses can be enrolled by providing:
// All courses; Done courses.
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

    public HashMap<Integer,List<CourseModel>> findAllCourseOrder(){
        HashMap<Integer,List<CourseModel>> orderedCourseList = new HashMap<>();
        List<CourseModel> undoneCourses = deleteFromList(this.totalCourses, this.coursesDone);
        int index = 0;
        while (!undoneCourses.isEmpty()){
            List<CourseModel> coursesCanBeEnrolled = courseSearchLoop(undoneCourses);
            orderedCourseList.put(index,coursesCanBeEnrolled);
            index++;
            this.coursesDone.addAll(coursesCanBeEnrolled);
            undoneCourses = deleteFromList(undoneCourses, this.coursesDone);
        }
        return orderedCourseList;
    }

    public List<CourseModel> courseSearchLoop(List<CourseModel> UndoneCourses){
        List<CourseModel> temporaryList = new ArrayList<>();
        for(CourseModel course: UndoneCourses){
            if(ifCourseCanBeEnroll(course)){
                temporaryList.add(course);
            }
        }
        return temporaryList;
    }

}
