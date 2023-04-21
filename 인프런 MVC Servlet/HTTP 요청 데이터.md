# `HTTP 요청 데이터 -개요`

- ### HTTP 요청 메시지를 통해 클라이언트에서 서버로 데이터를 전달하는 방법을 알아보자.
- ### 주로 3가지 방법을 사용한다.
  - GET - 쿼리 파라미터
    - /url?<b>username=hello&age=20</b>
    - 메시지 바디 없이, URL의 쿼리 파라미터에 데이터를 포함해서 전달
    - 예) 검색, 필터, 페이징등에서 많이 사용하는 방식
  - POST - HTML Form
    - content-type:application/x-www-form-urlencoded
    - 메시지 바디에 쿼리 파라미터 형식으로 전달 username=hello&age=20
    - 예) 회원 가입, 상품 주문, HTML Form 사용
  - HTTP message body 에 데이터를 직접 담아서 요청
    - HTTP API에서 주로 사용, JSON,XML,TEXT
    - 데이터 형식은 주로 JSON 사용
    - POST, PUT, PATCH<br>
      <img width="600" alt="image" src="https://user-images.githubusercontent.com/100770651/233611803-6f94c8cd-ea31-4cc3-9347-a76284b5ecd5.png"><br>
<hr>



