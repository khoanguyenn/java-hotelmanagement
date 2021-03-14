# Hotel management Java project

## Introduction
For the purpose of solving the problems related to the managing documentations, a hotel management 
desktop application is made to help reducing the managing efforts of the hotel operators.

The hotel management Java application is established to digitalize a fraction of hotel management. 

This project includes 3 team-members: Nguyen Dang Khoa, 
Truong Hoang Phuong Nhu and Ha Xuan Huy, working in 4 months to complete the project.

Made to accomplish Java-OOP modular on semester 3 of VGU.
### Technology uses:
+ Desktop application:
  + Maven
  + JavaFX (with Scene Builder)
+ Back-end Server:
  + Maven
  + Tomcat 9
  + Hibernate (to connect with MySQL)
+ Database:
  + MySQL 9
## Installation guide
### 1. Setup SQL database
Run the `sqlConfig.sql` which could be found in the main directory to setup the SQL database
### 2. Required materials
There are some required materials need to be installed to run the desktop application.
  + **Maven**, is utilized to manage the required libraries to run the application.
  + **JavaFX SDK**, uses to run front-end application (desktop), you can download JavaFX SDK from [here](https://gluonhq.com/products/javafx/).
  + **Tomcat 9**, uses to deploy the back-end of application, you can download from [here](htpps://www.tomcat.apache.org).
  + **MySQL Server**, is the database of the application, if you don't have MySQL server, you can download from 
  [here](https://www.dev.mysql.com/downloads/mysql/).
  + Makes sure that you cloned this project on github.
## 3. Maven Tomcat deploy
1. Go to server's folder: `cd hotelServer`.
2. Deloy with cargo plugin by: `mvn cargo:deploy`.
3. Run Tomcat's server by running `startup.bat` from your Tomcat directory. Example: `C:\Program Files\Apache Software Foundation\Tomcat 9.0\bin\startup.bat`.
4. __Further information:__ [click here](https://www.baeldung.com/tomcat-deploy-war)
### 4. Maven JavaFX build & run
1. Go to Javafx client's folder `cd hotelClient`
2. Build package by using command `maven package`
3. Run ".jar" file by `java --module-path <your-javafx-sdk-path> --add-modules javafx.controls,javafx.fxml,mysql.connector -jar target\hotelmanagement-1.0-SNAPSHOT.jar`
  + Download JavaFX SDK from [here](https://gluonhq.com/products/javafx/).
  + Place JavaFX SDK as your favourite (note it for later usage).
  + Add your JavaFX SDK path to the command. Example: `java --module-path E:\javafx-sdk-15.0.1\lib --add-modules javafx.controls,javafx.fxml,mysql.connector -jar target\hotelmanagement-1.0-SNAPSHOT.jar`.
4. Finally, run JavaFX using the following command: `maven javafx:run`.

### 4. Extra information
#### Hotel management API
`GET:` /hotelmanagement/rooms
+ Get all rooms information.
+ Params: 
    + `get_available=[boolean]`
    + `get_count=[boolean]`
  
`GET:` /hotelmanagement/customers
  + Get all customers information
  
`GET:` /hotelmanagement/bookings
  + Get all bookings information
  + Params:
    + `customer_id=[integer]`, get all bookings of specific customer.
  
`POST:` | `PUT` /hotelmanagement/rooms
+ Create a new room.
+ Params:
  + `number=[String]`
  + `type=[String]`
  + `status=[String]`

`POST:` | `PUT` /hotelmanagement/customers
+ Create a new customer.
+ Params:
  + `firstName=[String]`
  + `lastName=[String]`
  + `gender=[String]`
  + `telephoneNumber=[String]`
  + `address=[String]`
  + `email=[String]`
  + `dob=[String]`
  
`POST:` | `PUT` /hotelmanagement/bookings
+ Create a new booking
+ Params:
  + `roomNumber=[String]`
  + `customerId=[String]`
  + `checkin=[String]`
  + `checkout=[String]`
  + `method=[String]`
  
`DELETE:` /hotelmanagement/rooms
+ Delete by room's number.
+ Params:
  + `number=[String]`
  
`DELETE:` /hotelmanagement/customers
+ Delete by customer's information.
+ Params:
  + `id=[String]`
  
`DELETE:` /hotelmanagement/bookings
+ Delete by booking's ID.
+ Params:
  + `id=[String]`
  

 
