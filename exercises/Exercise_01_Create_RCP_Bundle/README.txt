Exercise 1: Create and build a hello world RCP plugin using tycho
=================================================================

- Prerequisites: 
  - eclipse classic 3.7M5 with m2eclipse and Tycho Project Configurators installed
  - access to an Eclipse Helios p2 repo (directly via http://download.eclipse.org/releases/helios/ or better, via local mirror)

- Create a new plugin project with name "tychodemo.bundle".
- Choose "Create a rich client application" and use the "Hello RCP" template to create a hello-world RCP bundle
- Enter window title "Tycho Demo RCP".
- Check "Add branding" (Note: we will need this later when building a product)

- Run the application to check if it works:
 - Create a new "Eclipse Application" launch configuration
 - Choose "Run a product" and select "tychodemo.bundle.product"
 - You should see a splash screen followed by an empty RCP window with title "Tycho Demo RCP".
   (see expected_rcp_screenshot.png)
 - Tip: to avoid starting lots of unneeded bundles:
       - go to the Plug-ins tab of the launch configuration
       - under "Launch with:", select "Plug-ins selected below only"
       - uncheck all plugins in the target platform and only check "tychodemo.bundle" from the workspace
       - press "Add Required Plug-ins"
   Now only the plugins (transitively) required by tychodemo.bundle will be started.
 
- Add a maven project file "pom.xml" in the root of the project with contents:

<?xml version="1.0" encoding="UTF-8"?>
<project
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd"
  xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
  <modelVersion>4.0.0</modelVersion>
  <groupId>tychodemo</groupId>
  <artifactId>tychodemo.bundle</artifactId>
  <version>1.0.0-SNAPSHOT</version>
  <packaging>eclipse-plugin</packaging>
</project>

- Note that MANIFEST/Bundle-SymbolicName == POM/artifactId and MANIFEST/<Bundle-Version>.qualifier == POM/<version>-SNAPSHOT

- To define the tycho version to be used, add this snippet into <project> :

  <properties>
    <tycho-version>0.12.0</tycho-version>
  </properties>

- To enable tycho, add this snippet into <project> :

  <build>
    <plugins>
      <plugin>
        <!-- enable tycho build extension -->
        <groupId>org.eclipse.tycho</groupId>
        <artifactId>tycho-maven-plugin</artifactId>
        <version>${tycho-version}</version>
        <extensions>true</extensions>
      </plugin>
    </plugins>
  </build>

- To build against the Helios p2 repository, add this snippet in <project>:

  <repositories>
    <!-- configure p2 repository to resolve against -->
    <repository>
      <id>helios</id>
      <layout>p2</layout>
      <url>http://localhost:8081/nexus/content/repositories/helios/</url>
    </repository>
  </repositories>

- Right-click the project > Run As > Maven package (or run 'mvn package' on the console)

- expected result is a SUCCESSFUL build with the bundle jar 
  tychodemo.bundle-1.0.0-SNAPSHOT.jar in the target/ folder of the project

