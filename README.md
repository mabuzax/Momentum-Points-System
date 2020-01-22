# Momentum-Points-System
A momentum shop point system for customers to buy products.

The main aim of this project is to test the API for the system, allowing the user to search for products and/or customers, as well as buy products using the customer's available points.

On startup the system loads a few dummy products for testing purposes.

The System is uses Basic SpringSecurity, and requires a username and password to make calls to the APIs.

As this is just a test system, the username and password is "user" and "password" respectively.


## Installation

### Required: MongoDB Installation
This application uses MongoDB as a repository.

If you do not yet have MongoDB running in your server (local or otherwise), download and follow the steps to setup MongoDB in your machine as outlined in: https://www.mongodb.com/

### Clone the Application
Note: Everytime when running the project for the first time, it may need to download some sources.
You may download/clone the project and sources to your local machine and run it as you would any springboot application.

Note: To build from source, you need the following installed
* [Java 7+](http://java.oracle.com/)
* [Apache maven](http://maven.apache.org/)

### Download the Springboot Jar
* To deploy/run a compiled and working version of the application, download the packaged Jar file in the bin folder. 

* In your commandline execute **java -jar MomentumPointsAPI-0.0.1-SNAPSHOT.jar** to get the server up and running.

* You are now ready to test your API by hitting the endpoints.  See Developer API Documentation for available endpoints.

## Developer API Documentation
- The API is documented using Swagger and can be accessed under **/swagger-ui.html** of yourserver e.g. **localhost:8080/swagger-ui.html**

- Using the API documentation, devs will find all the syntax as well as return codes to expect  when calling the API. 

- Sample CURL code(s) are also included in the API Documentation to assist devs with their Intergration testing.
  
## Some CURLs

* List Customers::  curl -u user GET "http://localhost:8081/momentum/api/v1/customer/list" -H "accept: */*"
* List Products::  curl -u user GET "http://localhost:8081/momentum/api/v1/product/list" -H "accept: */*"

 **The Developer API documentation** contains all the curls devs can use for api testing purposes.
