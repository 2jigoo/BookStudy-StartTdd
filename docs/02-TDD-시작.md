# Chapter 2. TDD 시작

- [TDD 도입 전의 개발](#tdd-도입-전의-개발)
- [TDD란?](#tdd란)
- [TDD 예: 덧셈 기능](#tdd-예-덧셈-기능)
- [TDD 예: 암호 검사기](#tdd-예-암호-검사기)
    - [첫번째 테스트: 모든 규칙을 충족하는 경우](#첫번째-테스트-모든-규칙을-충족하는-경우)
    - [두번째 테스트: 길이만 8글자 미만이고 나머지 조건을 충족하는 경우](#두번째-테스트-길이만-8글자-미만이고-나머지-조건을-충족하는-경우)
    - [세번째 테스트: 숫자를 포함하지 않고 나머지 조건은 충족하는 경우](#세번째-테스트-숫자를-포함하지-않고-나머지-조건은-충족하는-경우)
    - [코드정리: 테스트 코드 정리](#코드정리-테스트-코드-정리)

<br>

## TDD 도입 전의 개발

> 1. 만들 기능에 대해 고민한다. 어떤 클래스와 인터페이스를 도출할지 고민하고 각 타입에 어떤 메서드를 넣을지 시간을 들여 생각한다.
> 2. 과정 1을 수행하면서 구현에 대해서도 고민한다. 대략 어떻게 구현하면 될지 머릿속에 그려지면 코드를 쓰기 시작한다.
> 3. 기능에 대한 구현을 완료한 것 같으면 기능을 테스트 한다. 이 과정에서 **원하는 대로 동작하지 않거나 문제가 발생하면 과정2에서 작성한 코드를 디버깅하면서 원인을 찾는다.**

- 작성한 코드가 많을 수록 디버깅이 어렵고, 구현보다도 디버깅이 더 오래 걸리는 상황 발생
- 코드 작성자가 아닌 다른 개발자가 사용하면서 코드를 테스트하는 경우가 빈번
- 소스 수정 후 톰캣 서버 재구동, 테스트를 위해 DB 직접 조회 및 INSERT 등 테스트를 위한 부가적인 작업 수행
  
<br>

## TDD란?

테스트부터 시작하는 개발방법론
- 기능의 동작을 검증하는 테스트 코드를 먼저 작성
- 테스트를 통과시키기 위해 개발 진행

<br>

### TDD 예: 덧셈 기능

1. `plus()` 메서드 기능을 작성하기 전에 테스트부터 만들기  
    - 다음과 같은 조건들을 생각하며 테스트를 작성한다.  
        - 메서드 이름, 파라미터 갯수, 파라미터 타입, 반환값은?
        - 메서드는 정적 메서드, 인스턴스 메서드로 구현해야할지?
        - 메서드를 제공할 클래스 이름은?  
    ```java
    @Test 
    void plus() { 
        int result = Calculator.plus(1,2); 
        assertEquals(3, result); 
    
        int result2 = Calculator.plus(4,1);
        assertEquals(5, result2); 
    } 
    ```
    - `assertEquals()`를 사용하여 실행결과가 올바른지 검증
        - 실패 시 `AssertionFailedError` 발생
    - 아직 클래스를 생성하지 않았기 때문에 컴파일 에러 발생
2. 테스트 대상인 클래스 및 메서드 생성
   - 단순히 생성만 했기 때문에 아직 실패하는 테스트
3. 테스트를 통과시키는 코드 작성
   - 구현은 고려하지 않고, 일단 테스트를 통과할 수 있도록 임의의 return 지정
4. 테스트 성공 후 다른 테스트 추가 → 실패
5. 테스트를 통과할 만큼만 구현을 수정하기
6. 이러한 과정을 반복하며 점진적으로 구현해나가기
7. 구현이 완료된 후, 실제 빌드 대상 모듈로 이동  
    - src/test/java 폴더에서 코드를 만들면 완성되지 않은 코드가 배포되는 것을 방지하게 되는 효과가 있음

> TDD는 테스트를 먼저 작성하고, 테스트에 실패하면 테스트를 통과 시킬 만큼 코드를 추가하는 과정을 반복하며 점진적으로 기능을 완성한다.

<br>

### TDD 예: 암호 검사기

문자열을 검사해서 규칙을 준수하는지에 따라 암호를 `약함`, `보통`, `강함`으로 구분한다.

- 암호 검사 규칙
  1. 길이가 8글자 이상
  2. 0부터 9사이의 숫자를 포함
  3. 대문자 포함
- 3개의 규칙을 모두 충족하면 암호는 `강함`이다.
- 2개의 규칙을 충족하면 암호는 `보통`이다.
- 1개 이하의 규칙을 충족하면 암호는 `약함`이다.

<br>

#### 첫번째 테스트: 모든 규칙을 충족하는 경우

- 가장 쉽거나 가장 예외적인 상황부터 테스트
  - **모든 규칙을 충족**
  - ~모든 규칙을 충족하지 않음~
    - 각 조건을 검사하는 코드를 모두 구현해야 함

1. 테스트 작성  
    ```java
    @Test 
    void meetsAllCriteria_Then_Strong(){ 
        PasswordStrength result = meter.meter("ab12!@AB");
        assertEquals(PasswordStrength.STRONG, result);
    } 
    ```
2. 구현 없이 메소드를 생성 → 테스트 실패
3. 테스트를 통과하도록 코드를 수정  
  암호의 강도를 int 타입으로 표현할 수도 있지만, 열거타입을 사용하여 더욱 직관적으로 표현함  
    ```java
    public enum PasswordStrength { 
        STRONG 
    } 
    ```
4. 테스트 통과
5. 같은 조건을 만족하는 테스트 추가

<br>

#### 두번째 테스트: 길이만 8글자 미만이고 나머지 조건을 충족하는 경우
→ `PasswordStrength.NORMAL`
1. 테스트 추가  
    ```java
    @Test 
    void meetsOtherCriteria_except_for_Length_Then_Normal() { 
        PasswordStrength result = meter.meter("ab12!@A");
        assertEquals(PasswordStrength.NORMAL, result);
    }
    ```
2. 기존 테스트와 해당 테스트를 통과할만큼만 구현  
    ```java
    // PasswordStrengthMeter#meter
    if (s.length() < 8) {
        return PasswordStrength.NORMAL;
    }
    return PasswordStrength.STRONG;
    ```
3. 테스트 통과
4. 같은 조건을 만족하는 테스트 추가

<br>

#### 세번째 테스트: 숫자를 포함하지 않고 나머지 조건은 충족하는 경우
→ `PasswordStrength.NORMAL`
1. 테스트 추가  
    ```java
    @Test 
    void meetsOtherCriteria_except_for_number_Then_Normal() { 
        PasswordStrength result = meter.meter("ab!@ABqwer");
        assertEquals(PasswordStrength.NORMAL, result);
    } 
    ```
2. 기존 테스트와 해당 테스트를 통과할만큼만 구현  
3. 테스트 통과
4. 리팩토링이 필요하면 리팩토링  
    ```java
    // PasswordStrengthMeter#meter
    if (s.length() < 8) {
        return PasswordStrength.NORMAL;
    }
    boolean containsNumber = isContainingNumber(s); // 구현
    if (!containsNumber) {
        return PasswordStrength.NORMAL;
    }
    return PasswordStrength.STRONG;
    ```

<br>

#### 코드정리: 테스트 코드 정리

> **테스트 코드도 유지보수 대상**  
> 중복을 알맞게 제거하거나 의미가 잘 드러나게 수정해야 한다.  
> 오히려 테스트 코드 관리가 어려워지게 된다면 변경을 되돌린다.


- 테스트 메서드 내 지역 변수로 객체 생성  
  private 전역 변수로 수정하여 중복을 제거함  
  ```java
  // before
  @Test 
  void meetsAllCriteria_Then_Strong() { 
  PasswordStrengthMeter passwordStrengthMeter = new PasswordStrengthMeter();
  // 테스트 ...
  }

  // after
  class PasswordStrengthMeterTest {
      private PasswordStrengthMeter passwordStrengthMeter = new PasswordStrengthMeter();
  }
  ```
- 기능을 실행하고, 이를 확인하는 코드 중복  
  이를 한 번에 수행하는 메서드를 생성해 사용 (`assertStrength`)  
  ```java
  // before
  PasswordStrength result = passwordStrengthMeter.meter(암호);
  assertEquals(expected, result);


  // after
  assertStrength("ab!@ABqwer", PasswordStrength.NORMAL);

  private void assertStrength(String password, PasswordStrength expectedStrength) {
      PasswordStrength actualStrength = passwordStrengthMeter.meter(password);
      assertEquals(expectedStrength, actualStrength);
  }
  ```

- 수정 후에도 항상 테스트가 통과하는지 확인해야한다.
