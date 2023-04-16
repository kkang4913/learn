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
  - <b>동시 처리 이전의 쓰레드의 경우<br>
  [단일요청 - 쓰레드 하나 사용]<br><img width="450" alt="image" src="https://user-images.githubusercontent.com/100770651/232290348-33b9aebc-3558-4cff-bcec-a8cc98fb6564.png"> <img width="450" alt="image" src="https://user-images.githubusercontent.com/100770651/232290394-73d390b3-1d8e-4302-a63d-c64581837dbb.png"><br>
    - 요청이 하나 들어오면 요청은 새로운 쓰레드를 생성하여 처리하게 된다.
    - 응답 후 쓰레드는 휴식
  - <b>동시 처리 쓰레드의 경우 1<br>
  [다중요청 - 쓰레드 하나 사용]<br><img width="450" alt="image" src="https://user-images.githubusercontent.com/100770651/232290944-03b16484-b794-457f-abe4-cdf8ab468274.png">
    - 첫 번째 요청에 의한 기존 쓰레드의 처리가 지연되는 상황이 발생하면
    - 두 번째 들어온 요청은 수행 자체가 불가능하다.
  - <b>동시 처리 쓰레드의 경우 2<br>
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