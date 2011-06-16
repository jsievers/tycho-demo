Exercise 5: Add a product
=========================


- [optional if you want to catch up from exercise 4 or reset your workspace]:
  Import all 6 projects from this folder into an empty eclipse workspace using File > Import > Existing Maven Projects
- Right-click on tychodemo.aggregator > Run As > Maven install.
  The build should succeed and build 6 modules
   
   tychodemo.parent 
   tychodemo.bundle 
   tychodemo.bundle.tests
   tychodemo.feature
   tychodemo.repository
   tychodemo.aggregator 
   
- As a preparation, make sure the "Eclipse Application" launch configuration from Exercise 1 is now a feature-based launch config: 
  (this is a workaround for PDE bug https://bugs.eclipse.org/bugs/show_bug.cgi?id=340350 )
  - Run > Run Configurations > Eclipse Application
  - Choose "Run a product" and select "tychodemo.bundle.product"
  - IMPORTANT: In the "Plug-ins" tab, under "Launch with:", select "features selected below" and check tychodemo.feature only
  - Run it to see if the launch configuration works
   
- In the tychodemo.repository project root folder, create a new .product file:
  - File > New >  Product Configuration
  - File name: "tychodemo.product"
  - Choose "Use a launch configuration" and select the launch configuration from the previous step
    Note: this will make sure the bundle start levels of the product are configured correctly. 
    The start levels in the .product file in this example should be:
    
    <configurations>
      <plugin id="org.eclipse.core.runtime" autoStart="true" startLevel="0" />
      <plugin id="org.eclipse.equinox.common" autoStart="true" startLevel="2" />
      <plugin id="org.eclipse.osgi" autoStart="true" startLevel="-1" />
    </configurations>
    
  - In the "Overview" tab:
    - enter product ID "tychodemo.product"
    - "The product configuration is based on": select "features"
    - Under "Product:", select "tychodemo.bundle.product"
    - Under "Application:", select "tychodemo.bundle.application"
    
  - In the "Dependencies" tab:
    - add feature "tychodemo.feature" :

      <feature id="tychodemo.feature" version="1.0.0.qualifier"/>
      
  Note the product is being built as part of the existing packaging type "eclipse-repository".
  This is not intuitive and there will be a dedicated packaging type for p2-updatable 
  eclipse products in tycho 0.12.0.

- Right-click on tychodemo.aggregator > Run As > Maven install.
  Expected result: SUCCESSFUL build.
  In tychodemo.repository/target/repository, we have published the product metadata.
  E.g. you should now find the zipped eclipse launcher binaries in
  tychodemo.repository/target/repository/binary/
  
- Install the product:
  Add the following snippet to tychodemo.repository/pom.xml:
  
  <build>
    <plugins>
      <plugin>
        <groupId>org.eclipse.tycho</groupId>
        <artifactId>tycho-p2-director-plugin</artifactId>
        <version>${tycho-version}</version>
        <executions>
          <execution>
            <!-- (optional) install the product for all configured os/ws/arch environments using p2 director -->
            <id>materialize-products</id>
            <goals>
              <goal>materialize-products</goal>
            </goals>
          </execution>
        </executions>
      </plugin>
    </plugins>
  </build>  
  
- To work around an issue with the SWT fragments in a multi-platform build,
  add the SWT fragment of your build platform to tychodemo.feature/feature.xml.
  E.g. for win32/win32/x86:

   <plugin
         id="org.eclipse.swt.win32.win32.x86"
         os="win32"
         ws="win32"
         arch="x86"
         download-size="0"
         install-size="0"
         version="0.0.0"
         fragment="true"
         unpack="false"/>

  - Make sure to specify the correct os/ws/arch attributes  

- Run 'mvn install' on the aggregator project.
  Expected result: After successful build, you should find an installed product under
  tychodemo.repository/target/products/tychodemo.product/<os>/<ws>/<arch>/
  
- Run the product executable.
  Expected result: The "Tycho Demo RCP" screen should show up. 

=========================

- Additional bonus steps:

- Create a product zip:
  - Add the following snippet to the executions section of tycho-p2-director-plugin in 
    tychodemo.repository/pom.xml:
  
          <execution>
            <!-- (optional) create product zips (one per os/ws/arch) -->
            <id>archive-products</id>
            <goals>
              <goal>archive-products</goal>
            </goals>
          </execution>
          
  - Expected result: after successful build, you should find a product zip under 
    tychodemo.repository/target/products/
   
- Configure the product executable name:
  ( CAVEAT: this currently does not work for MacOSX, see http://bugs.eclipse.org/313997 )
  Choose a name, e.g. "tychoDemoRCP" in the "Launching" tab of the product editor
  
- Configure the product root folder:
  Add the following snippet to the tycho-p2-director plugin section:
  
        <!-- (optional) customize the root folder name of the product zip -->
        <configuration>
          <products>
            <product>
              <id>tychodemo.product</id>
              <rootFolder>myRCP</rootFolder>
            </product>
          </products>
        </configuration>

- Configure the splash screen:
  On the "Splash" tab of the product editor, configure "Plugin:" "tychodemo.bundle"
  
- Customize executable icon: 
  - Copy tychodemo.bundle/icons/ to tychodemo.repository/icons/
  - On the "Launching" tab of the product editor, choose e.g. for win32:
    "Use single .ico:" -> "../../../icons/alt_launcher.ico"
    
- Build the product for multiple platforms:
  - Configure the target platform environments in tychodemo.parent/pom.xml
    E.g. for two platforms linux/gtk/x86_64 and win32/win32/x86:
    
      <plugin>
        <groupId>org.eclipse.tycho</groupId>
        <artifactId>target-platform-configuration</artifactId>
        <version>${tycho-version}</version>
        <configuration>
          <!-- configure the p2 target environments for multi-platform build -->
          <environments>
            <environment>
              <os>linux</os>
              <ws>gtk</ws>
              <arch>x86_64</arch>
            </environment>
            <environment>
              <os>win32</os>
              <ws>win32</ws>
              <arch>x86</arch>
            </environment>
          </environments>
        </configuration>
      </plugin>    

  - CAVEAT: Add the SWT fragment for linux/gtk/x86_64 to tychodemo.feature/feature.xml (see above)
      
- Run 'mvn install' on the aggregator project.
  Expected result: After successful build, you should now find two installed products under
  tychodemo.repository/target/products/tychodemo.product/ :
  One for linux/gtk/x86_64 and one for win32/win32/x86.
    
 
 





   


