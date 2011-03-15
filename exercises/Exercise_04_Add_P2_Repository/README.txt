Exercise 4: Add a p2 repository
===============================

- [optional if you want to catch up from exercise 3 or reset your workspace]:
  Import all 5 projects from the zip into an empty eclipse workspace using File > Import > Existing Maven Projects
- Right-click on tychodemo.aggregator > Run As > Maven package.
  The build should succeed and build 5 modules
   
   tychodemo.parent 
   tychodemo.bundle 
   tychodemo.bundle.tests
   tychodemo.feature
   tychodemo.aggregator 

- Create a new update site project "tychodemo.repository"
  Note: Make sure the new project root folder is located next to the existing module folders
        (Uncheck "Use default location" in the project creation wizard and enter location if necessary)
        
- In site.xml, create a new category "tychodemo.category" 
  and add the feature "tychodemo.feature" to it
- Rename site.xml to category.xml
- Add a pom.xml file in the update site project root with contents:

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
    <artifactId>tychodemo.repository</artifactId>
    <version>1.0.0-SNAPSHOT</version>
    <packaging>eclipse-repository</packaging>
  </project>
  
  Note the packaging type "eclipse-repository".
  
- Add the repository module to tychodemo.aggregator POM:

    <module>tychodemo.repository</module>

- Right-click tychodemo.aggregator > Run As > Maven install
  Expected result: SUCCESSFUL build with tychodemo.repository module.
  A p2 repository in tychodemo.repository/target/repository/ and
  the zipped repository in tychodemo.repository/target/tychodemo.repository.zip
  
- Test the p2 repository just created:
  Help > Install New Software > Add... > Local... 
  > Choose your local  tychodemo.repository/target/repository/ folder.
  
  Now the demo category and feature created in the previous step should show up in the
  p2 installer UI.

  