# How to download and setup Apache Maven (step-by-step)

+ **Step 1: Make sure Java has already been downloaded**
  + Download **Java-JDK** (the latest version), you can download it from [here](https://www.oracle.com/java/technologies/javase/jdk15-archive-downloads.html)
 
+ **Step 2: Create a user environment variable with following figures**
  + Name: **JAVA_HOME**
  + Value: the directory to the path of **Java-JDK** stored in your computer 

+ **Step 3: Create a system environment variable (Path variable) with following figures**
  + Variable: **Path**
  + Value: the directory path to the **bin** folder of the **Java-JDK** stored in your computer
 
+ **Step 4: Download Apache Maven**
  + Navigate to [here](https://maven.apache.org/download.cgi) to download Apache Maven

+ **Step 5: Create 2 user environment variables with following figures**
  + Name: **MAVEN_HOME** and **M2_HOME**
  + Value: the directory path to the **Apache Maven** version you just downloaded
 
+ **Step 6: Create a system environment variable (Path variable) with following figures**
  + Variable: **Path**
  + Value: the directory path to the **bin** folder of the **Apache Maven** version you just downloaded
 
+ **Step 7: Check whether you have successfully downloaded Maven**
  + Type command **mvn -version** to the command prompt 
  + The below picture indicates that Maven has been successfully installed

  ![maven success](https://user-images.githubusercontent.com/52565807/111071356-552a4c00-8508-11eb-8ad7-d8c574d86109.PNG)
