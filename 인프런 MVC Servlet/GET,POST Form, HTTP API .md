# `HTTP 요청 데이터 - GET 쿼리 파라미터`

- ### HTTP 요청 데이터 - GET 쿼리 파라미터
    - 다음 데이터를 클라이언트에서 서버로 전송해보자.
    - 전달 데이터
        - username=hello
        - age=20
    - 단, 메시지 바디 없이, URL의 쿼리 파라미터를 사용
    - 예) 검색, 필터, 페이징등에서 많이 사용

- ### HTTP 요청 데이터 - POST HTML Form
  - HTML의 Form을 사용해서 클라이언트에서 서버로 전송해보자.
  - 주로 회원가입, 상품 주문 등에서 사용하는 방식이다.
- 특징
  - content-type:`applicatrion/x-www-form-urlencoded`
  - 메시지 바디에 쿼리 파라미터 형식으로 데이터를 전달.`username=hello&age=20`
  - 예) 회원 가입, 상품 주문, HTML Form 사용
- ### 'HTTP message body' 에 데이터를 직접 담아서 요청
  - HTTP API에서 주로 사용, JSON, XML, TEXT
  - 데이터 형식은 주로 JSON 사용
  - POST, PUT, PATCH
