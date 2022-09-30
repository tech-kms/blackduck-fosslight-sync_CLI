<p align='right'>
  <a href="https://github.com/tech-kms/blackduck-fosslight-sync_CLI/blob/main/docs/README_eng.md">[ENGLISH]</a>
</p>

# Black Duck & FOSSLight Hub 연동 CLI

### 도구설명
  * Black Duck의 BOM정보를 FOSSLight Hub의 Project에 업데이트하기 위한 도구입니다.
  * Black Duck : https://www.synopsys.com/software-integrity/security-testing/software-composition-analysis.html
  * FOSSLight Hub : https://fosslight.org/fosslight/

### 테스트된 빌드 / 실행 환경
  * JDK 11 : [java-11-openjdk-11.0.15.9-1.windows.ojdkbuild.x86_64.zip (sha256)](https://github.com/ojdkbuild/ojdkbuild/releases/download/java-11-openjdk-11.0.15.9-1/java-11-openjdk-11.0.15.9-1.windows.ojdkbuild.x86_64.zip)
  * Spring Tools [4.15.3](https://spring.io/tools)
  * Black Duck : [ v2022.4.1 ](https://github.com/blackducksoftware/hub/releases/tag/v2022.4.1)
  * FOSSLight Hub : [ v1.3.6 ](https://github.com/fosslight/fosslight/releases/tag/v1.3.6)

### Build 방법(STS)
* Spring Tools (STS) 사용 : https://spring.io/tools
* 소스 import : File >> Open Projects From Filesystem >> Import source: 에서 다운로드 받은 소스 루트 선택
* 해당프로젝트선택 오른쪽 마우스 >> Run AS >> Maven build 클릭 >> Goals 에  'package' 입력 하고 Run 클릭
* 빌드 완료 후 target 디렉토리에 BlackDuck_FOSSLight_Sync-x.x.jar 생성 및 BUILD SUCESS 메세지 확인

### Black Duck과 FOSSLight Hub 연동 시나리오
  * FOSSLight Hub에서 프로젝트를 생성 혹은 기존 프로젝트를 확인 한다.
  * 해당 프로젝트의 FOSSLight Project ID를 확인 한다. (Project 리스트 표에서 ID 칼럼값)
  * Black Duck에서 연동을 위한 Project 이름 및 버전을 확인 한다
  * CLI command로 Black Duck 및 FOSSLight에서 확인한 값들을 파라미터로 입력하여 실행한다
  * 실행 완료 후 터미널 로그에서 성공 / 실패 유무를 확인한다
  
### CLI 실행 방법(Terminal)
```
$ java -jar BlackDuck_FOSSLight_Sync-x.x.jar <Black Duck Information> <FOSSLight Information>

e.g)
$ java -jar BlackDuck_FOSSLight_sync-1.0.jar --bdurl https://your_blackduck_url.com --bdtoken MGQ0NjJmZmMtYjk2My00NTI2LWIwNTgtNzAyYzIxMTA3MzVkOjJhMjllODAxLTg4ZGItNGViOXXXXXXXXXXx --bdprojectname my_fosslight --bdprojectversion windows_01 --fltprotocol http --fltaddress your_fosslight_url.com:port --flttoken fosslightTockeneyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiJhZG1pbiIsImVtYWlsIjoiYWRtaW5AZm9zc2xpZ2 --fltprojectid fosslightprojectid
```

### 파라미터 설명

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
