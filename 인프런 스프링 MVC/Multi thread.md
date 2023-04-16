# `동시 요청 멀티 쓰레드(Multi thread) `

- ### Multi thread(멀티 쓰레드) 
  - 요청 시 서블릿 객체를 누가 호출하는지에 대한 개념부터가 중요하다.<br>
<img width="450" alt="image" src="https://user-images.githubusercontent.com/100770651/231070560-6584efb3-1ff6-4565-beef-516e0c77d43f.png"> <img width="450" alt="image" src="https://user-images.githubusercontent.com/100770651/231070632-f74d1051-ce4f-4a65-9eac-557e10e6bba2.png"><br>

- ### 쓰레드
  - 애플리케이션 코드를 하나하나 순차적으로 실행하는 것이 쓰레드
  - 자바 메인 메서드를 처음 실행하면 main 이라는 이름의 쓰레드가 실행
    - 즉, 쓰레드가 없으면 자바 애플리케이션 자체가 실행이 불가능
    - 쓰레드는 한번에 하나의 코드 라인만 수행
    - 동시 처리가 필요한 경우 쓰레드를 추가로 생성하여 실행
  - <b>동시 처리 이전의 쓰레드의 경우</b><br>
  [단일요청 - 쓰레드 하나 사용]<br><img width="450" alt="image" src="https://user-images.githubusercontent.com/100770651/232290348-33b9aebc-3558-4cff-bcec-a8cc98fb6564.png"> <img width="450" alt="image" src="https://user-images.githubusercontent.com/100770651/232290394-73d390b3-1d8e-4302-a63d-c64581837dbb.png"><br>
    - 요청이 하나 들어오면 요청은 새로운 쓰레드를 생성하여 처리하게 된다.
    - 응답 후 쓰레드는 휴식
  - <b>동시 처리 쓰레드의 경우</b> 1<br>
  [다중요청 - 쓰레드 하나 사용]<br><img width="450" alt="image" src="https://user-images.githubusercontent.com/100770651/232290944-03b16484-b794-457f-abe4-cdf8ab468274.png">
    - 첫 번째 요청에 의한 기존 쓰레드의 처리가 지연되는 상황이 발생하면
    - 두 번째 들어온 요청은 수행 자체가 불가능하다.
  - <b>동시 처리 쓰레드의 경우</b> 2<br>
  [다중요청 - 요청 마다 쓰레드 생성]<br><img width="450" alt="image" src="https://user-images.githubusercontent.com/100770651/232291190-0d09e888-e9de-4080-ab1e-7e61a67080d0.png">
    - 첫 번째 요청에 의한 기존 쓰레드의 처리가 지연되는 상황이 발생하여도
    - 두 번째 들어온 요청은 새로운 쓰레드를 생성하여 처리하게 된다.
- ### 장점
  - 동시 요청을 처리 할 수 있다.
  - 리소드(CPU, 메모리)가 허용할 때 까지 처리가 가능
  - 하나의 쓰레드가 지연되어도, 나머지 쓰레드는 정상 동작
- ### 단점
  - 쓰레드는 생성 비용이 매우 비싸다 (고객의 요청이 들어올 때 마다 쓰레드를 생성하면 응답속도가 느려진다.)
  - 쓰레드 생성에 제한이 없다 (고객이 요청이 너무 많거나 몰리는 경우 CPU, 메모리 임계점을 넘어서 서버가 죽는 경우가 발생.)
  - 쓰레드는 컨텍스트 스위칭 비용이 발생한다.
  - <b>컨텍스트 스위칭 :</b> <br>
    <img width="550" alt="image" src="https://user-images.githubusercontent.com/100770651/232291731-223ee059-dce5-428a-8b36-38fd81a1d31f.png">

- ### 단점을 보완하기 위한 WAS 의 쓰레드 풀
  - 필요한 쓰레드를  <b>쓰레드 풀</b>에 보관하고 관리
  - <b>쓰레드 풀</b>에 생성 가능한 쓰레드의 <b>최대치를 관리</b>, 톰캣은 최대 200개 기본 설정(변경 가능)<br>
    <img width="450" alt="image" src="https://user-images.githubusercontent.com/100770651/232294819-ae71f545-d165-487e-b35c-8e5b706bda06.png"><img width="450" alt="image" src="https://user-images.githubusercontent.com/100770651/232294919-c3626773-14eb-499c-af21-5b7982977a70.png"><br>
  - ### 사용
    - 쓰레드가 필요하면, 이미 생성되어 있는 쓰레드를 <b>쓰레드 풀</b>에서 꺼내서 사용
    - 사용을 종료하면, <b>쓰레드 풀</b>에 해당 쓰레드를 반납
    - 최대 쓰레드가 모두 사용중이어서 <b>쓰레드 풀</b>에 쓰레드가 없다면?
      - 기다리는 요청은 거절하거나 특정 숫자만큼 대기하도록 설정이 가능
  - ### 장점
    - 쓰레드가 미리 생성되어 있으므로, 쓰레드를 생성하고 종료한느 비용(CPU)이 절약되고, 응답 시간이 빠르다.
    - 생성 가능한 쓰레드의 최대치가 있으므로 너무 많은 요청이 들어와도 기존 요청은 안전하게 처리할 수 있다

- ### 쓰레드풀 (실무 팁)
  - WAS의 주요 튜닝 포인트는 <b>최대 쓰레드(MAX thread) 수</b>이다.
    - [톰캣 튜닝 가이드](https://ehdvudee.tistory.com/30)
    - (사용하고 있는 환경과 관련하여 검색하면 찾아볼 수 있다.ex Tomcat maxConnections, spring maxConnections 등등)
  - <b>위의 값을 너무 낮게 설정하면?</b>
    - 동시 요청이 많으면, 서버 리소스는 여유롭지만, 클라이언트는 금방 응답 지연
  - <b>위의 값을 너무 높게 설정하면?</b>
    - 동시 요청이 많으면, CPU, 메모리 리소스 임계점 초과로 서버 다운
  - <b>장애 발생시?</b>
    - 클라우드면 일단 서버부터 늘리고, 이후 튜밍(설정)
    - 클라우드가 아니라면 튜닝값을 적절히

- ### WAS의 멀티 쓰레드 지원 핵심
  - 개발자가 멀티 쓰레드 관련 코드를 신경쓰지 않아도 됨
  - 싱글 쓰레드 프로그래밍 하듯이 편리하게 소스 코드를 개발
  - 멀티 쓰레드 환경이므로 싱글톤 객체(서블릿, 스프링 빈)는 주의해서 사용 / [참고](https://beyondj2ee.wordpress.com/2013/02/28/%eb%a9%80%ed%8b%b0-%ec%93%b0%eb%a0%88%eb%93%9c-%ed%99%98%ea%b2%bd%ec%97%90%ec%84%9c-%ec%8a%a4%ed%94%84%eb%a7%81%eb%b9%88-%ec%a3%bc%ec%9d%98%ec%82%ac%ed%95%ad/)