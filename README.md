# Hotel management Java project

## Maven JavaFX build & run
1. Go to Javafx client's folder `cd hotelClient`
2. Build package by using command `maven package`
3. Run ".jar" file by `java --module-path <your-javafx-sdk-path> --add-modules javafx.controls,javafx.fxml,mysql.connector -jar target\hotelmanagement-1.0-SNAPSHOT.jar`
  + Download JavaFX SDK from [here](https://gluonhq.com/products/javafx/).
  + Place JavaFX SDK as your favourite (note it for later usage).
  + Add your JavaFX SDK path to the command. Ex: `java --module-path E:\javafx-sdk-15.0.1\lib --add-modules javafx.controls,javafx.fxml,mysql.connector -jar target\hotelmanagement-1.0-SNAPSHOT.jar`.
4. Run JavaFX debug: `maven javafx:run`.
## Maven Tomcat deploy
1. Go to server's folder: `cd hotelServer`.
2. Deloy with cargo plugin by: `mvn cargo:deploy`.
3. Run Tomcat's server: `C:\Program Files\Apache Software Foundation\Tomcat 9.0\bin\startup.bat`.
4. __Further information:__ [click here](https://www.baeldung.com/tomcat-deploy-war)

## Hotel management API
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
  

 
