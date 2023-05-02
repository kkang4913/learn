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
    age: <input type="text" name="age" />
    <button type="submit">전송</button>
</form>
</body>
</html>
```