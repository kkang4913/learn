# `HTTP 요청 데이터 -POST HTML`

- ### HTML의 Form을 사용해서 클라이언트에서 서버로 데이터를 전송해보자.
- ### 주로 회원가입, 상품 주문 등에서 사용하는 방식.<br>

- 특징 
  - content-type: `application/x-www-form-urlencoded`
  - 메시지 바디에 쿼리 파리미터 형식으로 데이터를 전달한다. `username=hello&age=20`
    `src/main/webapp/basic/hello-form.html` 생성
```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form action="/request-param" method="post">
    username: <input type="text" name="username" />
    age:      <input type="text" name="age" />
    <button type="submit">전송</button>
</form>
</body>
</html>
```

- 실행해보자
  - `http://localhost:8080/basic/hello-form.html`
  - 요청 URL : http://localhost:8080/request-param
  - content-type: `application/x-www-form-urlencoded`
  - message body: `username=hello&age=20`<br>

  - `application/x-www-form-urlencoded` 형식은 앞서 GET에서 살펴본 쿼리 파라미터 형식과 같음
  - 따라서 쿼리 파라미터 조회 메서드를 그대로 사용하면 된다.
  - 클라이언트 입장에서는 두 방식에 차이가 있지만, 서버 입장에서는 둘의 형식이 동일하므로, `request.getParameter()` 로 편리하게 구분없이 조회 가능.
  - 즉, `request.getParameter()`는 GET URL 쿼리 파리미터와, POST Form 형식 둘 다 지원
