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

#### 네 번째 테스트: 값이 없는 경우

* 예외상황에 대한 테스트
* null이거나 빈 문자열인 경우 
    * ~~`throws IllegalArgumentException`~~
    * `return PasswordStrength.INVALID`
* `PasswordStrength.INVALID` 추가
	```java
	public enum PasswordStrength {
		INVALID, NORMAL, STRONG
	}
	``````
https://github.com/2jigoo/BookStudy-StartTdd/blob/8835e7dc407c890586826122bac9b956b1381610/src/test/java/chap02/PasswordStrengthMeterTest.java#L29-L33

<br>

#### 다섯 번째 테스트: 대문자를 포함하지 않고 나머지 조건을 충족하는 경우
→ `PasswordStrength.NORMAL`

https://github.com/2jigoo/BookStudy-StartTdd/blob/8835e7dc407c890586826122bac9b956b1381610/src/test/java/chap02/PasswordStrengthMeterTest.java#L35-L38
<br>

#### 여섯 번째 테스트: 길이가 8글자 이상인 조건만 충족하는 경우
→ `PasswordStrength.WEAK`

* `PasswordStrength.WEAK` 추가
  ```java
  public enum PasswordStrength {
      INVALID, WEAK, NORMAL, STRONG
  }
  ```

  https://github.com/2jigoo/BookStudy-StartTdd/blob/8835e7dc407c890586826122bac9b956b1381610/src/test/java/chap02/PasswordStrengthMeterTest.java#L40-L43

https://github.com/2jigoo/BookStudy-StartTdd/blob/8835e7dc407c890586826122bac9b956b1381610/src/main/java/chap02/PasswordStrengthMeter.java#L6-L10

* if문 위치를 변경하여 로직을 분리함
    * 개별 규칙을 검사하는 로직
    * 규칙을 검사한 결과에 따라 암호 강도를 계산하는 로직
* 변경 후에도 테스트가 잘 통과하는지 확인

<br>

#### 일곱 번째 테스트: 숫자 포함 조건만 충족하는 경우
https://github.com/2jigoo/BookStudy-StartTdd/blob/8835e7dc407c890586826122bac9b956b1381610/src/test/java/chap02/PasswordStrengthMeterTest.java#L45-L48

> 코드를 정리하고 싶지만, 아직 아이디어가 떠오르지 않는다.  
> 일단 다음 테스트로 넘어가자.

<br>

#### 여덟 번째 테스트: 대문자 포함 조건만 충족하는 경우
→ `PasswordStrength.WEAK`

https://github.com/2jigoo/BookStudy-StartTdd/blob/8835e7dc407c890586826122bac9b956b1381610/src/test/java/chap02/PasswordStrengthMeterTest.java#L50-L53

<br>
#### 코드 정리: meter() 메서드 리팩토링

* 만족한 규칙의 개수를 세는 로직을 메서드로 분리하여, 전반적인 로직을 한 눈에 파악하기 쉬워짐

* metCounts 값을 조건에 충족될 경우 증가 시켜 충족된 조건에 따라 암호강도를 확인할 수 있다.
https://github.com/2jigoo/BookStudy-StartTdd/blob/8835e7dc407c890586826122bac9b956b1381610/src/main/java/chap02/PasswordStrengthMeter.java#L3-L11

<br>

#### 아홉 번째 테스트: 아무 조건도 충족하지 않는 경우
→ `PasswordStrength.WEAK`

1. 새로운 테스트를 추가하거나 기존 코드를 수정하면 테스트 실행
2. 실패하면 테스트를 통과시키기 위해 코드 추가

https://github.com/2jigoo/BookStudy-StartTdd/blob/8835e7dc407c890586826122bac9b956b1381610/src/test/java/chap02/PasswordStrengthMeterTest.java#L55-L58
<br>

#### 코드 정리: 코드 가독성 개선

https://github.com/2jigoo/BookStudy-StartTdd/blob/8835e7dc407c890586826122bac9b956b1381610/src/main/java/chap02/PasswordStrengthMeter.java#L30-L37

* 길이, 숫자 포함 여부, 대문자 포함 여부 규칙을 확인할 수 있는 getMetCriteriaCounts() 메서드를 만들어 가독성개선.

#### 테스트에서 메인으로 코드 이동

* 구현 완료 후 배포 대상인 메인 소스 폴더로 이동

<br>

## Chap.02 정리
암호검사기 기능을 TDD로 구현하는 예제를 보면서 TDD 개발 흐름을 알 수 있었다.

### TDD 흐름

1. 기능을 검증하는 테스트를 먼저 작성
2. 테스트를 통과하지 못하면 테스트를 *통과할 만큼만* 코드를 작성
3. 테스트를 통과한 후엔 필요 시 리팩토링
4. 리팩토링 후 다시 테스트를 실행하여 확인
5. 이 과정을 반복하며 점진적으로 기능을 완성
   
> **Red-Green-Refactor**  
> 실패 - 통과 - 리팩토링

![image](https://github.com/2jigoo/BookStudy-StartTdd/assets/90444513/64aaa40b-2171-4f14-9600-daf3628bd608)

**이 과정을 반복하면서 점진적으로 기능을 완성해 나가는 것.**

* *테스트가 개발을 주도하게 된다*
    * 테스트 코드를 만들면 다음 개발 범위가 정해진다.
  	* 검증하는 범위가 넓어질수록 구현도 점점 완성된다.
* *지속적인 코드 정리*
    * 구현 완료 후 리팩토링 진행
    * 리팩토링할 대상이 보이면 리팩토링 진행,  
  	별다른 방법이 생각나지 않을 땐 계속해서 테스트 코드 작성
    * 테스트 코드도 리팩토링의 대상
    * 테스트 코드가 있으면 해당 기능의 정상 동작을 검증할 수 있기 때문에 보다 과감한 리팩토링 가능
    * 지속적인 코드 정리 → 유지보수 비용 감소
* *빠른 피드백*
	* 코드 수정에 대해 빠르게 검증할 수 있기 때문에, 잘못된 코드가 배포되는 것을 방지할 수 있다.


