 # `Hello Servlet 등록 실행`
 
- ### [스프링 부트 스타터 페이지에서 스프링 프로젝트 생성](https://start.spring.io/)
  - 사용 환경(lntelliJ
  -  build.gradle 세팅
```
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

- 동작 확인
  - 기본 메인 클래스 실행( `ServletApplication.main()` )
  - localhost:8080 호출 ->  Whitelabel Error Page가 나오면 정상 동작

- ### 스프링 부트 서블릿 환경 구성
  - `@ServletComponentScan`<br>
    스프링 부트는 서블릿을 직접 등록해서 사용할 수 있도록 `@ServletComponentScan` 을 지원한다. 다음과 같이 추가하자.<br>

- ### hello.servlet.ServletApplication

```java
package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet",urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // soutm(단축키)
        // localhost:8080/hello 호출시 콘솔에 메세지 요청
        System.out.println("HelloServlet.service");

        // soutv(단축키)
        // 서블릿 HTTP 요청이 오면 WAS가 request / response 객체를 만들어 던져 준다.
        System.out.println("request = " + request);
        System.out.println("response = " + response);
        // 여러가지 WAS 서버들이 서블릿 표준스펙을 구현하는 것이 찍히는 것
        // request = org.apache.catalina.connector.RequestFacade@f95f8a4
        // response = org.apache.catalina.connector.ResponseFacade@40fdd60f

        //?username=kim( 쿼리 파라미터)
        String username = request.getParameter("username");
        //username 출력
        System.out.println("username = " + username);

        //응답 메세지 보내기 전 메세지 정보 세팅 (header 정보에 들어감)
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("hello " + username);

    }
}
```
- `WebServlet` : 서블릿 애노테이션
  - name: 서블릿 이름
  - urlPatterns: URL 매핑
- HTTP 요청을 통해 매핑된 URL이 호출되면 서블릿 컨테이너는 다음 메서드를 실행한다.
`protected void service(HttpServletRequest request, HttpServletResponse response)`

- 웹 브라우저 실행 
  - http://localhost:8080/hello?username=world
  - 결과 : hello world
- 콘솔 실행 결과
```
  HelloServlet.service
  request = org.apache.catalina.connector.RequestFacade@2e6593ad
  response = org.apache.catalina.connector.ResponseFacade@3bedb759
  username = world
```

- ### HTTP 요청 메시지 로그로 확인 하는 법
  - `appilcation.properties`
```properties
  logging.level.org.apache.coyote.http11=debug
```
  - 서버를 다시 재실행, 요청해보면 서버가 받은 HTTP 요청 메시지를 확인할 수 있다
```
Host: localhost:8080
Connection: keep-alive
Cache-Control: max-age=0
sec-ch-ua: "Chromium";v="112", "Google Chrome";v="112", "Not:A-Brand";v="99"
sec-ch-ua-mobile: ?0
sec-ch-ua-platform: "Windows"
Upgrade-Insecure-Requests: 1
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.0.0 Safari/537.36
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7
Sec-Fetch-Site: none
Sec-Fetch-Mode: navigate
Sec-Fetch-User: ?1
Sec-Fetch-Dest: document
Accept-Encoding: gzip, deflate, br
Accept-Language: ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7
Cookie: ch-veil-id=a35a06bb-0437-4c9f-833a-02e89c4a6e91

]
```

- ### 서블릿 컨테이너 동작 방식 설명
  - 내장 톰캣 서버 생성<br>
    <img width="600" alt="image" src="https://user-images.githubusercontent.com/100770651/233314798-db6982c1-fa68-45c6-8ff6-85ce7c8b9b2b.png"><br>
    - 스프링 부트를 실행하면 내장 톰캣 서버를 띄어줌 - 내부의 서블릿 컨테이너를 통해 서블릿을 생성 
    <img width="600" alt="image" src="https://user-images.githubusercontent.com/100770651/233315061-fa7e7a49-c617-4cc7-bb4a-2a8260042140.png"><br>
    <img width="600" alt="image" src="https://user-images.githubusercontent.com/100770651/233316061-eb0fbac1-9652-4c59-ba82-0d04f9af2100.png"><br>

