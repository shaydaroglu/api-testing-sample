# api-testing-sample
Sample API test automation framework with Java made by Sercan Haydaroglu.

# Concepts & Tools included
- Parallel Test Runs 
- TestNG Framework
- Slf4j Logger
- OkHttp and Retrofit2
- Common api interaction methods

# Requirements
In order to utilise this project you need to have the following installed locally:
  
      Maven 3
      Java 1.8
      Lombok Plugin
 
And please do not forget to enable annotation processing with the IDE.
In case of IntelliJ does not suggests to enabling annotation processing: 
 - open /.idea/compiler.xml file.
 - Insert following config inside \<component\> node
    
    ```    
   <annotationProcessing>
       <profile default="true" name="Default" enabled="true" />
   </annotationProcessing>
   ```
 

# Install & Run Tests

- Build the project
- Run command 
        "mvn surefire:test"