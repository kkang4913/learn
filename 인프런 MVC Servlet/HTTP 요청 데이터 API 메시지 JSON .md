# `HTTP 요청 데이터 - API 메시지 바디 JSON`

- ### HTTP API 에서 주로 사용하는 JSON 형식으로 데이터를 전달.

- <b>JSON 형식 전송</b>
  - POST http://localhost:8080/request-body-json
  - content-type: application/json
  - message-body: `{"username": "hello","age":20}`
  - 결과: `messageBody = {"username": "hello","age":20}`<br>
- <b>JSON 형식 파싱 추가</b>
  - JSON 형식으로 파싱할 수 있게 객체를 하나 생성해보자.
  - `hello.servelt.learn.helloData`
```java
package hello.learn.basic;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HelloData {
    private String username;
    private int age;
/*
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
 */
}
```
  - `hello.learn.basic.request.RequestBodyJsonServlet`
```java
package hello.learn.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.learn.basic.HelloData;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;
/**
 * http://localhost:8080/request-body-json
 *
 * JSON 형식 전송
 * content-type: application/json
 * message body: {"username": "hello", "age": 20}
 *
 */
@WebServlet(name = "requestBodyJsonServlet", urlPatterns ="/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        System.out.println("messageBody = " + messageBody);
        response.getWriter().write("JSON ok");
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);

        System.out.println("helloData = " + helloData.getUsername());
        System.out.println("helloData = " + helloData.getAge());


    }
}
```

- 참고
  - JSON 결과를 파싱해서 사용할 수 있는 자바 객체로 변환하려면 Jackson,Gson 같은 JSON 변환 라이브러리를 추가해서 사용해야 한다. 
    <p>스프링 부트로 spring MVC 를 선택하면 기본적으로 Jackson `라이브러리 (objackrMapper)`를 함께 제공</p>

    