package hello.servelt.web.servelt;

import hello.servelt.domain.member.Member;
import hello.servelt.domain.member.MemberRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "memberSaveServlet",urlPatterns = "/servlet/members/save")
public class MemberSaveServlet extends HttpServlet {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MemberSaveServlet.service");
        String username = request.getParameter("username");
        // request.getParameter 응답 결과는 문자이기 때문에 숫자 타입으로 변환 해주어야한다.
        //String age = request.getParameter("age");
        int age = Integer.parseInt(request.getParameter("age"));

        //비즈니스 로직
        Member member = new Member(username,age);
        memberRepository.save(member);

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter w = response.getWriter();
        w.write("<html>\n" +
                "<head>\n" +
                "   <meta charset=\"UTF-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "성공\n" +
                "<ul>\n" +
                " <li>id="+member.getId()+"</li>\n" +
                " <li>username="+member.getUsername()+"</li>\n" +
                " <li>age="+member.getAge()+"</li>\n" +
                "</ul>\n" +
                "<a href=\"/index.html\">메인</a>\n" +
                "</body>\n" +
                "</html>");
    }
    /* 동작 방식 MemberSaveServlet
        1. 파라미터를 조회해서 Member 객체를 만든다.
        2. Member 객체를 MemberRepository를 통해서 저장한다.
        3. Member 객체를 사용해서 결과 화면용 HTML을 동적으로 만들어서 응답한다.
     */

}
