# `HTTP 요청 데이터 - API 메시지 바디`

- ### HTTP message body 에 데이터를 직접 담아 요청
  - 먼저 가장 단순한 텍스트 메시지를 HTTP 메시지 바디에 담아 전송하고, 읽어보자.
  - HTTP 메시지 바디의 데이터를 InputStream을 사용해서 직접 읽을 수 있다.

- RequestBodyStringServlet

```java
package hello.servelt.basic.request;

import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyStringServlet",urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
         1.request.getInputStream(); -> 바이트 코드로 바로 얻을 수 있다.
         2.바이트 코드를 string으로 변환 - > StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
         3.변환시 인코딩 정보를 알려주어야 한다.
         */
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        System.out.println("messageBody = " + messageBody);

        response.getWriter().write("message body ok");
    }
}
```

- 참고
  -  inputStream은 byte 코드를 반환한다. byte 코드를 우리가 읽을 수 있는 문자(String)로 보려면 문자표
     (Charset)를 지정해주어야 한다. 여기서는 UTF_8 Charset을 지정해주었다.
- 문자 전송
  - POST http://localhost:8080/request-body-string
  - content-type: text/plain
  - message body: `hello`
  - 결과: `messageBody = hello`