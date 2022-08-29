# Black Duck & FOSSLight Hub integration CLI

### Introduction
  * A tool to sync the Black Duck's BOM information to the FOSSLight Hub's Project.
  * Black Duck: https://www.synopsys.com/software-integrity/security-testing/software-composition-analysis.html
  * FOSSLight Hub: https://fosslight.org/fosslight/

### Tested build/execution environment
  * JDK 11: [java-11-openjdk-11.0.15.9-1.windows.ojdkbuild.x86_64.zip(sha256)](https://github.com/ojdkbuild/ojdkbuild/releases/download/java-11-openjdk -11.0.15.9-1/java-11-openjdk-11.0.15.9-1.windows.ojdkbuild.x86_64.zip)
  * Spring Tools [4.15.3] (https://spring.io/tools)
  * Black Duck: [ v2022.4.1 ] (https://github.com/blackducksoftware/hub/releases/tag/v2022.4.1)
  * FOSSLight Hub: [ v1.3.6 ] (https://github.com/fosslight/fosslight/releases/tag/v1.3.6)

### How to Build
* Using Spring Tools (STS): https://spring.io/tools
* Source import: File >> Open Projects From Filesystem >> Import source: Select the downloaded source root
* Select the project, right-click >> Run AS >> Click Maven build >> Enter 'package' in Goals and click Run
* After the build is completed, create BlackDuck_FOSSLight_Sync-x.x.jar in the target directory and check the build success message

### Black Duck and FOSSLight Hub integration scenario
  * Create a project or check an existing project in FOSSLight Hub.
  * Check the FOSSLight Project ID of the project. (ID column value in the project list table)
  * Check the project name and version for linking in Black Duck
  * Run CLI command with entering the values checked in Black Duck and FOSSLight as parameters
  * After completion of execution, check the success/failure status in the terminal log
  
### How to run CLI (Terminal)
```
$ java -jar BlackDuck_FOSSLight_Sync-x.x.jar <Black Duck Information> <FOSSLight Information>

e.g)
$ java -jar BlackDuck_FOSSLight_sync-1.0.jar --bdurl https://your_blackduck_url.com --bdtoken MGQ0NjJmZmMtYjk2My00NTI2LWIwNTgtNzAyYzIxMTA3MzVkOjJhMjllODAxLTg4ZGItNGViOXXXXXXXXXXx --bdprojectname my_fosslight --bdprojectversion windows_01 --fltprotocol http --fltaddress your_fosslight_url.com:port --flttoken fosslightTockeneyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiJhZG1pbiIsImVtYWlsIjoiYWRtaW5AZm9zc2xpZ2 - -fltprojectid fosslightprojectid
```

### Parameter Description

```
Black Duck Server Information
 --bdurl: Black Duck url (ex. https://your_blackduck_url.com)
 --bdtoken: Black Duck Token Key

Black Duck Project Information
 --bdprojectname: Black Duck Project Name
 --bdprojectversion: Black Duck Project Version

FOSSLight Server Information
 --fltprotocol: FOSSLight Web Interface Protocol (ex. http)
 --fltaddress: FOSSLight Address (ex. your_fosslight_url.com:8080)
 --flttoken: FOSSLight Token

FOSSLight Project Information
 --fltprojectid: FOSSLight Project Id
 
FOSSLight Project Information (Option)
 --fltprojectname: FOSSLight Project Name
 --fltprojectversion: FOSSLight Project Version
 --fltostype: FOSSLight project OS type
              (default: Linux, Windows, MacOS, etc.)
```
