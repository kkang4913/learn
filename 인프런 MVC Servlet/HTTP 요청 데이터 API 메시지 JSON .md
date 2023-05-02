# `HTTP 요청 데이터 - API 메시지 바디 JSON`

- ### HTTP API 에서 주로 사용하는 JSON 형식으로 데이터를 전달.

- <b>JSON 형식 전송</b>
  - POST http://localhost:8080/request-body-json
  - content-type: application/json
  - message-body: `{"username": "hello","age":20}`
  - 결과: `messageBody = {"username": "hello","age":20}`<br>
- <b>JSON 형식 파싱 추가</b>
  - JSON 형식으로 파싱할 수 있게 객체를 하나 생성해보자.
  - `hello.servelt.basic.helloData`
```java
package hello.learn.basic;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HelloData {
    private String username;
    private int age;
/*
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
 */
}

```