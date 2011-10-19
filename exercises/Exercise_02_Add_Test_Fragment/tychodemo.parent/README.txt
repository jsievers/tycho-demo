Exercise 2: Add a test fragment with a simple JUnit test
========================================================


As a starting point for your convenience, the result of step 1 "Create an RCP bundle" has been refactored:
  - Extracted common settings and reactor modules to a parent POM from which all modules will inherit

- Select "File > Switch Workspace > Other..." and choose folder Exercise_02_Add_Test_Fragment.
- Import the 2 projects from this folder into the new empty eclipse workspace using File > Import > Existing Maven Projects
- Right-click on tychodemo.parent > Run As > Maven install.
  The build should succeed and build 2 modules
   
   tychodemo.parent 
   tychodemo.bundle 

- Create a new fragment project "tychodemo.bundle.tests" with fragment host "tychodemo.bundle"
  Notes: - Make sure the new project root folder is located next to the existing modules
           (Uncheck "Use default location" in the project creation wizard and enter location if necessary)
         - As opposed to classic maven projects, tests are always in a separate module
           because otherwise we would pollute productive code with test-scoped dependencies in MANIFEST. 
           We use a fragment because: 
            1. otherwise we would have to export classes under test in MANIFEST
            2. we want to be able to test package-private members

- In the fragment project, create a new JUnit test case "tychodemo.SimpleTest"
  - choose "tychodemo.bundle.ApplicationWorkbenchAdvisor" as class under test
  - choose getInitialWindowPerspectiveId() as test method for which a stub will be created
  
- Run the test: Right-click > Run As > JUnit Plugin Test
  - expected result: the test should fail with "java.lang.AssertionError: Not yet implemented"
  - Note: make sure that both tychodemo.bundle as well as tychodemo.bundle.tests are active 
          in the "Plugins" tab of the launch configuration
          (Use "Launch with: Plug-ins selected below only")
  
- Add the fragment module to the reactor and run the build:
  - Add a pom.xml file with content:
	<?xml version="1.0" encoding="UTF-8"?>
	<project
		xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd"
		xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
		<modelVersion>4.0.0</modelVersion>
		<parent>
			<groupId>tychodemo</groupId>
			<artifactId>tychodemo.parent</artifactId>
			<version>1.0.0-SNAPSHOT</version>
			<relativePath>../tychodemo.parent/pom.xml</relativePath>
		</parent>
		<groupId>tychodemo</groupId>
		<artifactId>tychodemo.bundle.tests</artifactId>
		<version>1.0.0-SNAPSHOT</version>
		<packaging>eclipse-test-plugin</packaging>
	</project>  

   Note the packaging type "eclipse-test-plugin" which indicates this bundle or fragment contains tests.
   
   - Add the module to the parent pom.xml:
   
   		<module>../tychodemo.bundle.tests</module>
   - Right-click tychodemo.parent > Run As > Maven Install
     Expected result: build should fail because of the same test failure we just saw in eclipse 

-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running tychodemo.SimpleTest
Tests run: 1, Failures: 1, Errors: 0, Skipped: 0, Time elapsed: 0.016 sec <<< FAILURE!
testGetInitialWindowPerspectiveId(tychodemo.SimpleTest)  Time elapsed: 0 sec  <<< FAILURE!

   
- Implement the test:
  - assert that "tychodemo.bundle.perspective" is the return value of 
    tychodemo.bundle.ApplicationWorkbenchAdvisor.getInitialWindowPerspectiveId()
  - test should now succeed when executed in eclipse, 'mvn install' should also succeed
  
  







