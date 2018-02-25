# AS_CodeTest_withGUI
This is the GUI version of AS_CoudeTest 

Please Download the project through the link below:

[Download](https://drive.google.com/open?id=1cH9Hmxy5FK9b3xLebkepjKlqB15pS05X)

The using guidance can be found in the download file.

Software Structure:

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
 
 ![alt text](https://github.com/adam-p/markdown-here/raw/master/src/common/images/icon48.png "Logo Title Text 1")
        