 # `Hello Servlet 등록 실행`
 
- ### [스프링 부트 스타터 페이지에서 스프링 프로젝트 생성](https://start.spring.io/)
  - 사용 환경(lntelliJ
  -  build.gradle
```java
plugins {
        id 'java'
        id 'war'
        id 'org.springframework.boot' version '2.7.8'
        id 'io.spring.dependency-management' version '1.0.15.RELEASE'
        }

        group = 'hello'
        version = '0.0.1-SNAPSHOT'
        sourceCompatibility = '11'

        configurations {
        compileOnly {
        extendsFrom annotationProcessor
        }
        }

        repositories {
        mavenCentral()
        }

        dependencies {
        implementation 'org.springframework.boot:spring-boot-starter-web'
        compileOnly 'org.projectlombok:lombok'
        annotationProcessor 'org.projectlombok:lombok'
        providedRuntime 'org.springframework.boot:spring-boot-starter-tomcat'
        testImplementation 'org.springframework.boot:spring-boot-starter-test'
        }

        tasks.named('test') {
        useJUnitPlatform()
        }

```
