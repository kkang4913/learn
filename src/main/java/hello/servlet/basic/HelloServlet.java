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
