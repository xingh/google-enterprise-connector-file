## Introduction ##

This document walks you through the steps to manually install a Google Search Application Connector for FileNet with FileNet P8 4.5.1. The instructions mentioned here are applicable to connector version 2.x and above.

Google strongly recommends that you use the installer for installing the connector. Though, you may want to do it manually if,

You have built and installed a customized connector manager or a customized version of the connector
You want to deploy the connector on an existing Tomcat installation
You are installing a patch release that is not packaged with an installer.


## Pre-requisites: ##

• Apache Tomcat 6. You can download it from it from http://tomcat.apache.org/download-  60.cgi.

• JRE1.5 or above. Refer to http://java.sun.com/javase/downloads/index_jdk5.jsp for downloads.

• Make sure that Jace.jar is present.

• Make sure that jaas.conf.WSI is present.

• Make sure that  these files are also available :

> • activation.jar
> • log4j.jar
> • javaapi.jar
> • stax-api.jar
> • xlxpScanner.jar
> • xlxpScannerUtils.jar

## Steps to Install: ##

To install the connector manually, follow the instructions given below:

  1. On the Tomcat host, shut down Tomcat if it is running.

> 2. Go to $CATALINA\_HOME\bin directory.
> a. For Windows Users,
> Edit the following files:
> > • setclasspath.bat

```
       Add the following lines in the start of the file:
       set JRE_HOME=<JRE_HOME>
       set PATH=%PATH%;

      Add the following lines just before “rem Set standard command for invoking Java”:
       rem Google Enterpise Connector Logging
       set CONNECTOR_LOGGING= %CATALINA_HOME%\webapps\connector-manager\WEB-INF\lib\connector-logging.jar
       if not exist "%CONNECTOR_LOGGING%" goto noConnectorLogging
       set CLASSPATH=%CLASSPATH%;%CONNECTOR_LOGGING%
       :noConnectorLogging
```


> b.	For Linux Users,
> Edit setclasspath.sh

```
      Put the following lines in the start of the file:
       export JRE_HOME=<JRE_HOME>
       export PATH="$PATH":"$JRE_HOME"/bin

      Add the following lines just before “# OSX hack to CLASSPATH”:
       # Google Enterpise Connector Logging
       CONNECTOR_LOGGING=”$CATALINA_HOME”/webapps/connector-manager/WEB-INF/lib/connector-logging.jar
       if [ -f "$CONNECTOR_LOGGING ]; then
        CLASSPATH=”$CLASSPATH”:”$CONNECTOR_LOGGING”
       fi
```
Please make sure that all the shell scripts (with “.sh” as extension) have execute permissions.

3. If you do not have the connector manager installed, follow these steps to get the same:

> a. Start a web browser and navigate to http://code.google.com/p/google-enterprise-connector-manager/downloads/list

> b. Download the correct binary distribution compressed file for your platform

> c. Unzip or untar the compressed file

> d. copy the connector-manager.war to $CATALINA\_HOME/webapps directory

> e. Start Tomcat so that the connector manager gets deployed. To confirm that the connector manager has been properly deployed under Tomcat confirm that a directory with the name connector-manager has been created under $CATALINA\_HOME/webapps directory.

Also you can check for http://localhost:8080/connector-manager/testConnectivity which will displays the connectivity status or http://localhost:8080/connector-manager/startUp which will display a message indicating the successful deployment of Connector Manager

> f. Shut down the Tomcat to start with the further steps.


4. Start a web browser and navigate to the download site on http://code.google.com/p/google-enterprise-connector-file/downloads/list

5. Download the correct FileNet connector version (connector-file-`<version>`.zip).

6. Unzip the compressed file.

7. Copy the connector-file.jar file from the root directory and json.jar from the lib directory to the $CATALINA\_HOME/webapps/connector-manager/WEB-INF/lib directory.

8. Do the following changes in $CATALINA\_HOME/bin/catalina.bat (catalina.sh on Linux):
> o Find the following line:
```
      set JAVA_OPTS=%JAVA_OPTS% -Djava.util.logging.manager=org.apache.juli.ClassLoaderLogManager -Djava.util.logging.config.file="%CATALINA_BASE%\conf\logging.properties"
```

> o Change it to the following:
```
      set JAVA_OPTS=%JAVA_OPTS% -Djava.util.logging.manager=java.util.logging.LogManager -Djava.util.logging.config.file=”%CATALINA_BASE%\webapps\connector-manager\WEB-INF\classes\logging.properties" 
```

For Linux use $CATALINA\_BASE

9. Copy all the 7 jars (i.e. Jace.jar,activation.jar, log4j.jar, javaapi.jar, stax-api.jar ,xlxpScanner.jar, xlxpScannerUtils.jar) from mentioned from the pre-requisite section to CONNECTOR\_HOME\Tomcat\webapps\connector-manager\WEB-INF\lib

10. In the $CATALINA\_HOME/webapps/connector-manager/WEB-INF folder, create a directory or folder called classes.

11. Copy the logging.properties file from the /Config folder to the /classes folder.

12. Copy the jaas.conf.WSI from FileNet Installation (default path:Filenet/Content Engine/config/samples) file to the /classes folder.

13. Open the logging.properties file in a text editor. Set the value of java.util.logging.FileHandler.pattern equal to the absolute path of the log file.
> java.util.logging.FileHandler.pattern=<value of $CATALINA\_HOME>/logs/google-connectors%g.log

14. Start the Tomcat server.

15. To confirm whether the Tomcat server has started correctly and the connector is installed, navigate to the $CATALINA\_HOME/webapps/connector-manager/WEB-INF/connectors directory, and verify that the $CATALINA\_HOME/webapps/connector-manager/WEB-INF/connectors/Filenet\_P8 directory exists.