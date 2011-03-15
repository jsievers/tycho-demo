Exercise 3: Add a feature
=========================


- Import all 4 projects from the zip into an empty eclipse workspace using File > Import > Existing Maven Projects
- Right-click on tychodemo.aggregator > Run As > Maven package.
  The build should succeed and build 4 modules
   
   tychodemo.parent 
   tychodemo.bundle 
   tychodemo.bundle.tests
   tychodemo.aggregator 

- Create a new feature project "tychodemo.feature" and add "tychodemo.bundle" as included plugin

- Add a file pom.xml to the feature project root with contents:

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
		<artifactId>tychodemo.feature</artifactId>
		<version>1.0.0-SNAPSHOT</version>
		<packaging>eclipse-feature</packaging>
	</project>

  Note packaging type "eclipse-feature". 
  Also note POM version == feature version and POM artifactId == feature id.

- Add the feature module to the aggregator pom:
		<module>tychodemo.feature</module>

- tychodemo.aggregator > Run As > Maven Install

  Expected result: SUCCESSFUL build with 5 modules, including tychodemo.feature