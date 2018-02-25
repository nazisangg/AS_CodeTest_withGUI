# AS_CodeTest_withGUI
This is the GUI version of AS_CoudeTest 

Please Download the project through the link below:

[Download](https://drive.google.com/file/d/1IAp6a503b_fGIrrQc8NqNv25nBBNlNEc/view?usp=sharing)

The using guidance can be found in the download file.

Please run the code with command as below

```
cd AS_CodeTest

java -jar AS_JavaFx.jar

or

sh work.sh

```


## Basic Tasks
This is the basic function which meet the test requirement.

#### CSV Reader
CSV reader is build base on "opencsv" library

#### Mapping relationship between courses and preRequisite

By reading messages from two csv files, we can obtain relationshipas below:

"courses can be found by id and preReuisites use id to define preReuisites relationship"

That is a typical Hashmap format with "Key" equals to id and "Value" equals to course

So a Object named "CourseModel" is build which contains information as below:

 - String courseName
 
    - the name of course
 - List< CourseModel > preRequisite
 
    - Contain all preRequisite of the course
    
    - The reason why I didn't use parent and child class relation between Course and preRequisite is that they contains same information and methods
 
The HashMap is used to map the preRequisite to their courses, the pseudo code is shown below:

```pseudo
CSVReader(courses.csv)
    new hashmap(String, CourseModel)
    for each line:
        key = CSVReader.id
        value = Course(CSVReader.title, preRequisite = [])
        hashmap.add(key, value)
    return hashmap
    
CSVReader(preRequisite.csv, hashmap)
     for each line:
        coursekey = CSVReader.course
        preRequitekey = CSVReader.preRequisite
        course = hashmap.get(coursekey)
        preRequisite = hashmap.gey(preRequitekey)
        // add preRequisite to the list in CourseModel
        course.preRequisiteList.add(preRequisite)
        
```

#### Search possiable courses for students

From the previous section, we can obtain objects like below:
- List< CourseModel > totalCourses
    - this entity contains the preRequisite relationship.

This relation can be regared as a Topology graph

By providing input as below:

- List< CourseModel > CourseDone
    - this entity shows how many courses has been done
    - base on that we need to give student a study plan
    
    
The algorithm pseudo code is shown below:

```pseudo
List totalList
list DoneList

// Those are the nodes we need to search in the Topology graph
List targetList = totallist.removeAll(donelist)

// This is for the recording of learning orders
index = 0

// Searching the Topology until all courses have been learned
while(targetlist!=null):
    for(course:targetlist):
        // This is a list to record which course can be learn in this loop
        list temperoray = []
        if(canBeEnroll(course)){
			temperoray.add(course)
	}
    // Record courses that can be enrolled
    hashmap.add(index,temperoray)
    // Assume those courses is alread done and conitues search
    DoneList.addAll(temperoray)
    // Remove all done courses from  
    targetlist.removeAll(Donelist)

canBeEnrolled(course, Donelist):
    boolean judge = true
    list prerequisites = course.getPrerequisite
    for(prerequisite:prerequisites):
        if(!Donelist.contains(prerequisite)):
            judge = false
            break;
            
    return judge
    

```


### Software Structure:

- Controller package
    - CSVController
        - read csv files and build the relation map between courses
        - dependency : opencsv
        - logic can befound in [AS_CodingTest_Web](https://github.com/nazisangg/AS_CodeTest)
    - SearchController
        - find all possible courses and study plan
        - Algorithms can befound in [AS_CodingTest_Web](https://github.com/nazisangg/AS_CodeTest)

- Model package
    - CourseModel
        - save the information of courses including id, courseName and preRequisite
        - multiple courseModel become a topology map
    - TableData_Course
        - This is the model for javaFX to output the value in CourseModel
        - Dependency: JavaFX
- Resource package
    - Save static files
    
- Test package
    - Test files
    - Dependency: Junit
    - [AS_CodingTest_Web_Test](https://github.com/nazisangg/AS_CodeTest/tree/master/src/test/java/Yinong/AlexSolution)
    
- UI package
    - Main
        - JavaFx View relevant including main function 
    - ViewController
        - Connect the Fx GUI and Java entity.
        - Control function of buttons and tables.
 
 #### App 
 
 ![alt text](https://github.com/nazisangg/AS_CodeTest_withGUI/blob/master/src/sample/Resource/NmTvPPKQMi.gif)
        
