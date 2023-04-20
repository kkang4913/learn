# `HTML, HTTP API, CSR, SSR `

- ### 정적 리소스
  - 고정된 HTML파일, CSS, JS, 이미지, 영상 등을 제공
  - 주로 웹 브라우저<br>
    <img width="600" alt="image" src="https://user-images.githubusercontent.com/100770651/232303176-48eadd3a-7088-4e31-bb8b-34c025052060.png"><br>

- ### HTML 페이지
  - 동적으로 필요한 HTML 파일을 생성해서 전달
  - 웹 브라우저: HTML 해석<br>
    <img width="600" alt="image" src="https://user-images.githubusercontent.com/100770651/232303500-65d24707-f51f-488f-ab44-b431439e5514.png"><br>

- ### HTTP API
  - HTML이 아니라 데이타를 전달
  - 주로 <b>JSON</b> 형식 사용 / [참고](https://developer.mozilla.org/ko/docs/Learn/JavaScript/Objects/JSON)
  - 다양한 시스템에서 호출<br>
    <img width="450" alt="image" src="https://user-images.githubusercontent.com/100770651/232305175-e5b8b41d-f04f-4376-b2c6-5f21d5f81deb.png"> <img width="450" alt="image" src="https://user-images.githubusercontent.com/100770651/233283562-245606f6-0411-4a21-9bcc-ae9963130fac.png"><br>
  - 데이터만 주고 받음, UI 화면이 필요하면, 클라이엍느가 별도 처리
  - 앱, 웹 클라이언트, 서버 to 서버
  - ## 주로 JSON 형태로 통신
  - ## UI 클라이언트 접점
    - 앱 클라이언트(아이폰, 안드로이드, PC 앱)
    - 웹 브라우저에서 자바스크립트를 통한 HTTP API 호출
    - React, Vue.js 같은 웹 클라이언트
  - ## 서버 to 서버
    - 주문 서버 -> 결제 서버
    - 기업간 데이터 통신

```
백엔드 개발자가 서비스를 제공할 때 고민해야하는 기초 3가지
 1. 정적 리소스
 2. 동적으로 제공되는 HTML 페이지
 3. HTTP API
```

- ### 서버사이드 렌더링, 클라이언트 사이드 렌더링
  - SSR - 서버 사이드 렌더링<br>
    <img width="600" alt="image" src="https://user-images.githubusercontent.com/100770651/233286147-23ace2dc-6fe1-4f0d-b1cf-062328a06f14.png"><br>
    - 그림 설명
      - 웹 브라우저에서 <b>주문내역</b>을 <b>서버에 요청</b>
      - 서버는 <b>DB에서 주문 정보 조회</b> 해서 동적으로 HTML을 생성
      - <b>최종적으로 서버에서 HTML을 생성해서 클라이언트에 전달</b>
  - CSR - 클라이언트 사이드 렌더링
    - HTML 결과를 자바스크립트를 사용해 웹 브라우저에서 동적으로 생성하여 적용
    - 주로 동적인 화면에 사용, 웹 환경을 마치 앱 처럼 필요한 부분부분을 변경할 수 있음
    - 예) 구글 지도, Gmail, 구글 캘린더
    - 관련기술: React, Vue.js -> 프론트엔드 개발자<br>
      <img width="600" alt="image" src="https://user-images.githubusercontent.com/100770651/233288908-1da40606-8faf-4713-9964-a708f76c245f.png">
    - 그림 설명
      - 웹 브라우저에서 HTML을 요청 -> HTML 내용X 자바스크립트 링크 보냄
      - 다시 웹 브라우저에서 자바스크립트를 요청 -> 자바스크립트(클라이언트 로직, HTML 렌더링 코드) 보냄
      - 웹 브라우저는 HTTP API 통해 서버를 호출 -> 서버에서 JSON 데이터를 보냄
      - 웹 브라우저는 클라이언트 로직, HTML 렌더링 코드를 사용해 동적으로 HTML을 만들어 낸다.


    
