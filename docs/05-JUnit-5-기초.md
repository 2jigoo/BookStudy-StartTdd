# Chapter 5. JUnit 5 기초

- [JUnit 5 모듈 구성](#JUnit-5-모듈-구성)
- [테스트 코드의 구성 요소: 상황, 실행, 결과 확인](#테스트-코드의-구성-요소:-상황,-실행,-결과-확인)
- [`@Test`와 테스트 메서드](#`@Test`와-테스트-메서드)
- [주요 단언 메서드](#주요-단언-메서드)
  - [Exception 발생 유무 검사](#Exception-발생-유무-검사)
    - [Executable](#Executable)
    - [모든 검증을 실행한 후 실패 여부 확인](#모든-검증을-실행한-후-실패-여부-확인)
- [라이프 사이클](#라이프-사이클)
  - [`@BeforeEach`, `@AfterEach`](#`@BeforeEach`,-`@AfterEach`)
  - [`@BeforeAll`, `@AfterAll`](#`@BeforeAll`,-`@AfterAll`)
- [테스트 메서드 간 실행 순서 의존과 필드 공유하지 않기](#테스트-메서드-간-실행-순서-의존과-필드-공유하지-않기)
- [기타](#기타)
- [모든 테스트 실행하기](#모든-테스트-실행하기)

<br>

## JUnit 5 모듈 구성

* JUnit Platform  
  : 테스팅 프레임워크를 구동하기 런처와 테스트 엔진을 위한 API 제공
* Junit Jupiter  
  : JUnit 5를 위한 테스트 API와 실행 엔진 제공
* JUnit Vintage  
  : JUnit 3, 4로 작성된 테스트를 JUnit 5 플랫폼에서 실행하기 위한 모듈을 제공

```gradle
dependencies {
    testImplementation 'org.junit.jupiter:junit-jupiter'
}

// task test: JUnit 5 플랫폼을 사용하도록 설정
test {
    useJUnitPlatform()
}
```

* junit-jupiter 모듈은 Junit 5.4부터 제공  
  그 이하의 버전에선 `org.junit.jupiter:junit-jupiter-api`, `junit-jupiter-engine` 모듈 사용

<br>

## `@Test`와 테스트 메서드

* 테스트 클래스와 `@Test`가 붙은 메서드는 private이면 안 된다.
* `Assertions.assertEquals`

<br>

## 주요 단언 메서드

## 주요 단언 메서드
- Assetions 클래스가 제공하는 주요 단언 메서드

|메서드|설명|
|---|---|
|`assertEquals(expected, actual)`|실제 값(actual)이 기대하는 값(expected)과 같은지 검사한다.|
|`assertNotEquals(unexpected, actual)`|실제 값(unexpected)이 특정값(actual)과 같지 않는지 검사한다.|
|`assertSame(Object expected, Object actual)`|두 객체가 동일한 객체인지 검사한다.|
|`assertNotSame(Object unexpected, Object actual)`|두 객체가 동일하지 않은 객체인지 검사한다.|
|`assertTrue(boolean condition)`|값이 true 인지 검사한다.|
|`assertFalse(boolean condition)`|값이 false인지 검사한다.|
|`assertNull(Object actual)`|값이 null인지 검사한다.|
|`assertNotNull(Object actual)`|값이 null이 아닌지 검사한다.|
|`fail()`|테스트를 실패 처리한다.|

```java
// 값 비교
assertEquals(expected, actual);
assertNotEquals(unexpected, actual);

// 레퍼런스 비교
assertSame(Object expected, Object actual);
assertNotSame(Object unexpected, Object actual);

assertTrue(boolean condition);
assertFalse(boolean condition);
assertNull(Object actual);
assertNotNull(Object actual);

// 테스트를 실패 처리
fail();
```

<br>

### Exception 발생 유무 검사

```java
// executable을 실행한 결과, 지정한 타입의 Exception이 발생하는지 검사
assertThrows(Class<T> expectedType, Executable excutable);

// Exception이 발생하지 않는지 검사
assertDoesNotThrow(Executable executable);
```

exception 발생 여부가 검증 대상일 땐 `fail()` 메서드보다 위의 두 메서드를 사용하는 것이 더욱 명시적이다.

```java
// 발생한 Exception 객체를 리턴
IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
    AuthService authService = new AuthService();
    authService.authenticate(null, null);
});

// 발생한 Exception을 이용하여 *추가적인 검증*
assertTrue(thrown.getMessage().contains("id"));
```

#### Executable
```java
package org.junit.jupiter.api.function;

public interface Executable {
    void execute() throws Throwable;
}
```
* `assertThrows()`, `assertDoesNotThrow()`에서 사용하는 함수형 인터페이스

#### 모든 검증을 실행한 후 실패 여부 확인
```java
assertAll(
    () -> assertEquals(3, 5/2),
    () -> assertEquals(4, 2*2),
    () -> assertEquals(6, 11/2)
);
```
* Executable 목록을 가변 인자로 전달받아 실행한다.
* 검증에 실패한 코드가 있으면 그 목록을 모아 에러 메시지를 보여준다.

<br>

## 라이프 사이클

### `@BeforeEach`, `@AfterEach`

1. 테스트 메서드를 포함한 객체 생성 (생성자 호출)
   * 테스트 메서드를 실행할 때마다 객체를 새로 생성함
2. `@BeforeEach`` 메서드 실행
3. `@Test`가 붙은 메서드 실행
4. `@AfterEach` 메서드 실행

### `@BeforeAll`, `@AfterAll`

* 정적 메서드에 사용
* 클래스의 모든 테스트 메서드를 실행하기 전/실행한 후에 한 번 실행

<br>

## 테스트 메서드 간 실행 순서 의존과 필드 공유하지 않기

* 테스트 메서드가 특정 순서대로 실행된다는 가정하에 테스트 메서드를 작성하면 안된다.
* JUnit이 테스트 순서를 결정하긴 하지만 그 순서는 버전에 따라 달라질 수 있다.
* 각 테스트 메서드는 서로 독립적으로 동작해야 한다.
* 한 테스트 메서드의 결과에 따라 다른 테스트 메서드의 실행 결과가 달라지면 안 된다.  
* 따라서, 테스트 메서드가 서로 필드를 공유하거나, 실행 순서를 가정하고 테스트를 작성하면 안 된다.

<br>

## 기타

* `@DisplayName`: 테스트 이름 표현
* `@Disabled`: 테스트 실행 대상에서 제외
 
<br>

## 모든 테스트 실행하기

* `mvn test` (`mvnw test`)
* `gradle test` (`gradlew test`)
  
