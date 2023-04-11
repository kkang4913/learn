# `Servlet(서블릿) `

- ### 서버에서 처리해야하는 업무
  - 웹 애플리케이션 서버 직접 구현
    - 비즈니스 로직 실행 전,후의 과정이 복잡하고 비효율적 <br><br/>
   
      <img src="https://user-images.githubusercontent.com/100770651/229709697-124cecf3-b43d-4abf-bf21-cb73fe68d31d.jpg" width="300" height=""/>

- ### 서버에서 처리해야하는 업무 (서블릿을 지원하는 WAS)
  - 서블릿을 지원하는 WAS
    - 비즈니스 로직 실행 전,후의 과정에서 나오는 비효율적인 부분을 지원 <br><br/>

      <img width="300" alt="image" src="https://user-images.githubusercontent.com/100770651/229710356-fb2c1d90-2fc9-4b92-ac01-07c6b72248a1.png">
  
- ### 서블릿의 등장 배경  
  ```
  - 사용자의 요구에 맞춰 동적으로 반응하는 페이지를 만들기 위해 만들어진 것이 바로 서블릿이다.
  ```
  
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

- ### HTTP 스펙이란?
  - [HTTP 스펙](https://github.com/kkang4913/learn/blob/master/%EC%9D%B8%ED%94%84%EB%9F%B0%20%EC%8A%A4%ED%94%84%EB%A7%81%20MVC/Web%20Server,%20application.md)

- ### 서블릿 HTTP 요청의 흐름
  <img width="581" alt="image" src="https://user-images.githubusercontent.com/100770651/230127393-e4e116d4-56ce-46e7-80e4-25d0829048ea.png"><br>
- ###  HTTP 요청이 오면
  - WAS 는 Request(요청), Response(응답) 객체를 새로 만들어 서블릿 객체 호출
  - 개발자는 Request 객체에서 HTTP 요청 정보를 편리하게 꺼내 사용
  - 개발자는 Response 객체에 HTTP 응답 정보를 편리하게 입력
  - WAS 는 Response 객체에 담겨있는 내용으로 HTTP 응답 정보를 생성
  - 웹 브라우저에 전송 되고 HTML 를 렌더링하여 화면에 보여준다.
- ### 서블릿 컨테이너
  <img width="529" alt="image" src="https://user-images.githubusercontent.com/100770651/230132312-bf2ff3d1-0ceb-4793-b3f5-61b1150bc029.png"><br>
  -  #### 서블릿을 지원하는 WAS 를 서블릿 컨테이너라고 함.
  -  #### 서블릿 컨테이너는 서블릿 객체를 생성, 초기화, 호출, 종료하는 생명주기 관리<br><br>

  - ### 서블릿 컨테이너와 생명주기
    1. 클라이언트에서 요청 <br><br/>
    <img width="529" alt="image" src="https://user-images.githubusercontent.com/100770651/231062317-5e2665ac-7a34-4e13-9ec9-a6cc73ba2d64.png"><br> 
    <br> 
    2. 최초의 요청시 동작 <br><br/>
     <img width="500" alt="image" src="https://user-images.githubusercontent.com/100770651/231062680-868aa69d-3241-4e93-a5bf-f76f1a32b64c.png"><br><br/>
         - 최초로 ```java Resource 의 Request``` 를 응답받은 스프링은 서블릿 컨테이너를 통해 서블릿 객체를 생성
         - 객체는 ```init()``` 메서드를 통해 생성 및 초기화
         - ```service()``` 메소드는 어떤 요청이 들어왔는지 체크(```get, post, put, delete, patch```)<br>
    <br>
    3. 스레드 (thread) 동작 <br><br/>
    <img width="500" alt="image" src="https://user-images.githubusercontent.com/100770651/231063405-063c4d31-1b74-4427-bf0a-d83e51af6b94.png"><br>
        - ```init()``` 메서드의 경우, 기존의 스레드가 생성해줌
        - ```service()``` 메서드를 호출할 때, 새로운 스레드(스레드1)가 생성됨
        - 스레드는 DB연결도 하고 필요한 데이터를 찾아 HTML에 담아서 응답 등의 기능을 할 것
        - 두 번째 요청이 들어올 경우, 서블릿 객체를 재사용함
        - ```service()``` 메서드를 호출할 때, 새로운 스레드(스레드2)가 생성됨 <br><br/>

```
스레드란?
스레드는 어떠한 프로그램 내에서, 특히 프로세스 내에서 실행되는 흐름의 단위를 말한다. 
일반적으로 한 프로그램은 하나이 스레드를 가지고 있지만,프로그램 환경에 따라 둘 이상의 
스레드를 동시에 실행할 수 있다. 이러한 실행 방식을 멀티 스레드라고 한다.
```

- #### 서블릿 객체는 싱글톤으로 관리
  - 고객의 요청이 올 때 마다 계속 객체를 생성하는 것은 비효율
  - 최초 로딩 시점에 서블릿 객체를 미리 만들어두고 재활용
  - 모든 고객 요청은 동일한 서블릿 객체 인스턴스에 접근
  - <b>공유 변수 사용 주의</b>
  - 서블릿 컨테이너 종료시 함께 종료
- #### JSP 도 서블릿으로 변환 되어서 사용
- #### 동시 요청을 위한 멀티 쓰레드 처리 지원