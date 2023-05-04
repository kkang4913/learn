package hello.servelt.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servelt.basic.HelloData;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

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
    /*
        JSON 결과를 파싱해서 사용할 수 있는 자바 객체로 변환하려면 Jackson,Gson 같은 JSON 변환
        라이브러리를 추가해서 사용해야 한다.
        스프링 부트로 spring MVC 를 선택하면 기본적으로 Jackson라이브러리(objackrMapper)를 함께 제공
     */
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
