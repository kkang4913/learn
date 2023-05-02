# `HTTP 요청 데이터 -개요`

- ### HTTP 요청 메시지를 통해 클라이언트에서 서버로 데이터를 전달하는 방법을 알아보자.
- ### 주로 3가지 방법을 사용한다.
  - GET - 쿼리 파라미터
    - /url?<b>username=hello&age=20</b>
    - 메시지 바디 없이, URL의 쿼리 파라미터에 데이터를 포함해서 전달
    - 예) 검색, 필터, 페이징등에서 많이 사용하는 방식
  - POST - HTML Form
    - content-type:application/x-www-form-urlencoded
    - 메시지 바디에 쿼리 파라미터 형식으로 전달 username=hello&age=20
    - 예) 회원 가입, 상품 주문, HTML Form 사용
  - HTTP message body 에 데이터를 직접 담아서 요청
    - HTTP API에서 주로 사용, JSON,XML,TEXT
    - 데이터 형식은 주로 JSON 사용
    - POST, PUT, PATCH<br>
      <img width="600" alt="image" src="https://user-images.githubusercontent.com/100770651/233611803-6f94c8cd-ea31-4cc3-9347-a76284b5ecd5.png"><br>
<hr>

- ### GET 쿼리 파라미터
  - 전달하고 싶은 데이터
  `username=hello`
  `age=20`
  - 메시지 바디 없이, URL의 <b>쿼리 파라미터</b>를 사용해서 데이터를 전달
  - 예) 검색, 필터, 페이징 등에서 많이 사용하는 방식
  - 쿼리 파라미터는 URL에 다음과 같이 `?` 를 시작으로 보낼 수 있다. 추가 파라미터는 `&` 로 구분
  `http://localhost8080/request-param?username=hello&age=20`
  - 서버에서는 `HttpServletRequest`가 제공하는 메서드를 통해 쿼리 파라미터를 조회할 수 있다.
  - <b>쿼리 파라미터 조회 메서드</b>
```java
  String username = request.getParameter("username"); //단일 파라미터 조회
  String[] usernames = request.getParameterValues("username"); //복수 파라미터 조회  
```
  `Enumeration<String> parameterNames = request.getParameterNames(); //파라미터 이름들  모두 조회`<br>
  - 요즘 방식으로 변환해서 사용
```java
    request.getParameterNames().asIterator()
          .forEachRemaining(paramName -> System.out.println(paramName + "=" + request.getParameter(paramName)));
```
  - RequestParamServlet
```java
package hello.learn.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

/**
 * 1. 파라미터 전송 기능
 * http://localhost:8080/request-param?username=hello&age=20
 * <p>
 * 2. 동일한 파라미터 전송 가능
 * http://localhost:8080/request-param?username=hello&username=kim&age=20
 */
@WebServlet(name ="requestParamServlet",urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    System.out.println("RequestParamServlet.service");
    //전체 파라미터 조회하는 법
    System.out.println("[전체 파라미터 조회] - start");
    request.getParameterNames().asIterator()
            .forEachRemaining(paramName -> System.out.println(paramName + "=" + request.getParameter(paramName)));
    System.out.println("[전체 파라미터 조회] - end");
    System.out.println();

    //단일 파라미터 조회하는 법
    System.out.println("[단일 파라미터 조회]");
    String username = request.getParameter("username");
    String age = request.getParameter("age");

    System.out.println("username = " + username);
    System.out.println("age = " + age);
    System.out.println("[단일 파라미터 조회 끝]");
    System.out.println();

    System.out.println("[이름이 같은 복수 파라미터 조회] - 1");
    String[] usernames = request.getParameterValues("username");
    for (String name : usernames) {
      System.out.println("username = " + name);
    }
    System.out.println("[이름이 같은 복수 파라미터 조회] - 2");
    String[] ages = request.getParameterValues("age");
    for (String userage : ages){
      System.out.println("userage = " + userage);
    }
    response.getWriter().write("ok");

  }

}

```

  - 실행 - 파라미터 전송
    - `http://localhost:8080/request-param?username=hello&age=20`
```결과
[전체 파라미터 조회] - start
username=hello
age=20
[전체 파라미터 조회] - end
[단일 파라미터 조회]
request.getParameter(username) = hello
request.getParameter(age) = 20
[이름이 같은 복수 파라미터 조회]
request.getParameterValues(username)
username=hello
```
  - 실행 - 동일 파라미터 전송
    - `http://localhost:8080/request-param?username=hello&username=kim&age=20`
```결과
[전체 파라미터 조회] - start
username=hello
age=20
[전체 파라미터 조회] - end
[단일 파라미터 조회]
request.getParameter(username) = hello
request.getParameter(age) = 20
[이름이 같은 복수 파라미터 조회]
request.getParameterValues(username)
username=hello
username=kim
```

