# `HTTP 응답 데이터 - 단순 텍스트, HTML`

- ### HTTP 응답 메시지는 주로 다음 내용을 담아서 전달한다.
  - 단순 텍스트 응답
    - 앞에서 살펴봄 `(writer.println("ok");)`
    - [단순 텍스트 응답](https://github.com/kkang4913/learn/blob/master/src/main/java/hello/learn/basic/response/ResponseHeaderServlet.java)<br></br>
      <img width="500" alt="image" src="https://user-images.githubusercontent.com/100770651/235630274-001ee84f-091d-4715-a396-f7e34462f4b2.png">
  - HTML 응답
  - HTTP API - MessageBOdy JSON 응답

  - HttpServletResponse - HTML 응답
    - hello.servelt.web.response.ResponseHtmlServlet

```java
package hello.servelt.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name ="responseHtmlServlet",urlPatterns = "/response-html")
public class ResponseHtmlServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Content-Type: text/html;charset=utf-8
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter writer = response.getWriter();
        writer.println("<html>");
        writer.println("<body>");
        writer.println("<div>안녕?</div>");
        writer.println("</body>");
        writer.println("</html>");
    }
}

```
