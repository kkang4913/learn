# `Servlet(서블릿) `

- ### 서버에서 처리해야하는 업무
  - 웹 애플리케이션 서버 직접 구현
    - 비즈니스 로직 실행 전,후의 과정이 복잡하고 비효율적
   
      <img src="https://user-images.githubusercontent.com/100770651/229709697-124cecf3-b43d-4abf-bf21-cb73fe68d31d.jpg" width="300" height=""/>

- ### 서버에서 처리해야하는 업무 (서블릿을 지원하는 WAS)
  - 서블릿을 지원하는 WAS
    - 비즈니스 로직 실행 전,후의 과정에서 나오는 비효율적인 부분을 지원

      <img width="300" alt="image" src="https://user-images.githubusercontent.com/100770651/229710356-fb2c1d90-2fc9-4b92-ac01-07c6b72248a1.png">

- ### 특징  
``` Servlet 특징
@WebServlet(name = "helloServelt", urlPattenrns = "/hello") 
public class HelloServlet extends HttpServlet {

    @Override
    protected void service(HttpServeltRequest request, HttpServletResponse response){
      //애플리케이션 로직
    }
}      
```
  - HTTP 요청 정보를 편리하게 사용할 수 있는 HttpServletRequest
  - HTTP 응답 정보를 편리하게 제공할 수 있는 HttpServletResponse
  - 개발자는 HTTP 스펙을 편리하게 사용 가능

