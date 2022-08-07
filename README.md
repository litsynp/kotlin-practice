# 자바 개발자를 위한 코틀린 입문(Java to Kotlin Starter Guide)

자바에서 코틀린으로 migration 하기 위해 코틀린을 학습한다.

코틀린 개념과 문법을 자바와의 비교를 통해 알아본다. 주석을 이용해 학습한 내용을 정리한다.

- [com.litsynp.kotlinpractice.javatokotlin](https://github.com/litsynp/kotlin-practice/tree/main/src/main/kotlin/com/litsynp/kotlinpractice/javatokotlin)

## Table of Contents

### [1강. 코틀린에서 변수를 다루는 방법](https://github.com/litsynp/kotlin-practice/blob/main/src/main/kotlin/com/litsynp/kotlinpractice/javatokotlin/Lec01Main.kt)

1. 변수 선언 키워드 - `var` vs. `val`
2. Kotlin에서의 Primitive Type
3. Kotlin에서의 nullable 변수
4. Kotlin에서의 객체 인스턴스화

### [2강. 코틀린에서 null을 다루는 방법](https://github.com/litsynp/kotlin-practice/blob/main/src/main/kotlin/com/litsynp/kotlinpractice/javatokotlin/Lec02Main.kt)

1.  Kotlin에서의 null 체크
2.  Safe call(`?.`)과 Elvis(`?:`) 연산자
3.  null 아님 단언 (`!!`)
4.  플랫폼 타입

### [3강. 코틀린에서 Type을 다루는 방법](https://github.com/litsynp/kotlin-practice/blob/main/src/main/kotlin/com/litsynp/kotlinpractice/javatokotlin/Lec03Main.kt)

1. 기본 타입
2. 타입 캐스팅
3. Kotlin의 3가지 특이한 타입 (`Object`->`Any`, `void`->`Unit`, `Nothing`)
4. String interpolation, String indexing

### [4강. 코틀린에서 연산자를 다루는 방법](https://github.com/litsynp/kotlin-practice/blob/main/src/main/kotlin/com/litsynp/kotlinpractice/javatokotlin/Lec04Main.kt)

1. 단항 연산자 / 산술 연산자
2. 비교 연산자와 동등성, 동일성 (`==`, `===`)
3. 논리 연산자 / 코틀린에 있는 특이한 연산자
   1. `&&,` `||`, `!`
   2. `in` / `!in`
   3. `a..b`
   4. `a[i]`
4. 연산자 오버로딩 `operator`

### [5강. 코틀린에서 제어문을 다루는 방법](https://github.com/litsynp/kotlin-practice/blob/main/src/main/kotlin/com/litsynp/kotlinpractice/javatokotlin/Lec05Main.kt)

    1. `if문`
    2. Expression과 Statement
    3. switch와 `when`

### [6강. 코틀린에서 반복문을 다루는 방법](https://github.com/litsynp/kotlin-practice/blob/main/src/main/kotlin/com/litsynp/kotlinpractice/javatokotlin/Lec06Main.kt)

1. for-each문 (향상된 for문)
2. 전통적인 for문 -- `..` 연산자, `downTo`, `step`
3. Progression과 Range
4. `while` 문

### [7강. 코틀린에서 예외를 다루는 방법](https://github.com/litsynp/kotlin-practice/blob/main/src/main/kotlin/com/litsynp/kotlinpractice/javatokotlin/Lec07Main.kt)

1. `try-catch-finally` 구문
2. Checked Exception, Unchecked Exception
3. 코틀린의 try-with-resources (JDK 7+) (`.use { ... }`)

### [8강. 코틀린에서 함수를 다루는 방법](https://github.com/litsynp/kotlin-practice/blob/main/src/main/kotlin/com/litsynp/kotlinpractice/javatokotlin/Lec08Main.kt)

1. 함수 선언 문법
2. default parameter
3. named argument (parameter)
4. 같은 타입의 여러 파라미터 받기 (가변인자) (`vararg`, `*배열`)

### [9강. 코틀린에서 클래스를 다루는 방법](https://github.com/litsynp/kotlin-practice/blob/main/src/main/kotlin/com/litsynp/kotlinpractice/javatokotlin/Lec09Main.kt)

1. 클래스와 프로퍼티
2. 생성자와 `init` (주생성자, 부생성자)
3. 커스텀 getter, setter
4. backing field

### [10강. 코틀린에서 상속을 다루는 방법](https://github.com/litsynp/kotlin-practice/blob/main/src/main/kotlin/com/litsynp/kotlinpractice/javatokotlin/Lec10Main.kt)

1. 추상 클래스 (abstract) - `open`
2. 인터페이스 (interface)
3. 클래스를 상속할 때 주의할 점
4. 상속 관련 지시어 정리
   1. `final`: override를 할 수 없게 한다. default로 보이지 않게 존재한다.
   2. `open`: override를 열어 준다.
   3. `abstract`: 반드시 override 해야 한다.
   4. `override`: 상위 타입을 오버라이드 하고 있다.

### [11강. 코틀린에서 접근 제어를 다루는 방법](https://github.com/litsynp/kotlin-practice/blob/main/src/main/kotlin/com/litsynp/kotlinpractice/javatokotlin/Lec11Main.kt)

1. 자바와 코틀린의 가시성 제어 (`public`, `protected`, `internal`, `private`)
2. 코틀린 파일의 접근 제어
3. 다양한 구성요소의 접근 제어 (클래스, 생성자, 프로퍼티)
4. 자바와 코틀린을 함께 사용할 경우 주의할 점

### [12강. 코틀린에서 object 키워드를 다루는 방법](https://github.com/litsynp/kotlin-practice/blob/main/src/main/kotlin/com/litsynp/kotlinpractice/javatokotlin/Lec12Main.kt)

1. static 함수와 변수 (`companion object`)
2. 싱글톤 (`object`)
3. 익명 클래스 (`object`)

### [13강. 코틀린에서 중첩 클래스를 다루는 방법](https://github.com/litsynp/kotlin-practice/blob/main/src/main/kotlin/com/litsynp/kotlinpractice/javatokotlin/Lec13Main.kt)

1. 중첩 클래스의 종류

   - `static` 사용하는 중첩 클래스: 밖의 클래스를 직접 참조할 수 없음
   - `static` 사용하지 않는 중첩 클래스
     1. 내부 클래스 (Inner Class): 밖의 클래스 직접 참조 가능
     2. 지역 클래스 (Local Class): 메소드 내부에 클래스 정의 (드문 편)
     3. 익명 클래스 (Anonymous Class): 일회성 클래스

2. 코틀린의 중첩 클래스와 내부 클래스 (`class` (권장), `inner class` & `this@`)

### [14강. 코틀린에서 다양한 클래스를 다루는 방법](https://github.com/litsynp/kotlin-practice/blob/main/src/main/kotlin/com/litsynp/kotlinpractice/javatokotlin/Lec14Main.kt)

1. Data class (`data class`)
2. Enum Class (`enum class` & `when` - 싱글톤)
3. Sealed Class, Sealed Interface (`sealed class` & `when` - 하위 클래스 봉인, 멀티 인스턴스)

### [15강. 코틀린에서 배열과 컬렉션을 다루는 방법](https://github.com/litsynp/kotlin-practice/blob/main/src/main/kotlin/com/litsynp/kotlinpractice/javatokotlin/Lec15Main.kt)

1. 배열 (배열보다는 리스트!) (`.indices`, `.withIndex()`, `.plus()`)
2. 코틀린에서의 Collection - `List`, `Set`, `Map` (가변 / 불변)
3. 컬렉션의 null 가능성, Java와 함께 사용하기

### [16강. 코틀린에서 다양한 함수를 다루는 방법](https://github.com/litsynp/kotlin-practice/blob/main/src/main/kotlin/com/litsynp/kotlinpractice/javatokotlin/Lec16Main.kt)

1. 확장함수 (Extension function) (`fun className.functionName() { ... }`), 확장 프로퍼티
2. infix 함수 (중위 함수) (e.g., `3 add 2`, `downTo`, `step`)
3. inline 함수
4. 지역함수

### [17강. 코틀린에서 람다를 다루는 방법](https://github.com/litsynp/kotlin-practice/blob/main/src/main/kotlin/com/litsynp/kotlinpractice/javatokotlin/Lec17Main.kt)

1. Java에서 람다를 다루기 위한 노력 (인터페이스, 익명함수, 람다, Stream, 함수 = 2급 시민)
2. 코틀린에서의 람다 (함수 = 1급 시민)
   - 람다 생성: `fun() {}`, `{ T -> R }`
   - 함수 호출: `func()`, `func.invoke()`
3. Closure: 람다가 시작하는 지점에 참조하고 있는 변수들을 모두 포획한 데이터 구조
4. 다시 try-with-resources (`use`)

### [18강. 코틀린에서 컬렉션을 함수형으로 다루는 방법](https://github.com/litsynp/kotlin-practice/blob/main/src/main/kotlin/com/litsynp/kotlinpractice/javatokotlin/Lec18Main.kt)

1. 필터와 맵

   - `filter { T -> R }`: 사과만 주세요!
   - `filterIndexed { idx, T -> R }`: 필터에서 인덱스도 같이 주세요
   - `map { T -> R }`: 사과의 가격들을(만) 알려주세요!
   - `mapIndexed { idx, T -> R }`: 맵에서 인덱스도 같이 주세요
   - `mapNotNull { fruit -> fruit.nullOrValue }`: 맵핑의 결과가 null이 아닌 것만 가져오고 싶다

2. 다양한 컬렉션 처리 기능

   - `all`, `none`, `any`, `count()`, `sortedBy`, `sortedByDescending`, `distinctBy`, `first()`, `firstOrNull()`, `last()`, `lastOrNull()`

3. `List`를 `Map`으로 (`groupBy`, `associateBy`, )
4. 중첩된 컬렉션 처리 (`flatMap`, 람다 사용하기, `flatten()`)

### [19강. 코틀린의 이모저모](https://github.com/litsynp/kotlin-practice/blob/main/src/main/kotlin/com/litsynp/kotlinpractice/javatokotlin/Lec19Main.kt)

1. Type Alias와 `as import`
2. 구조 분해와 `componentN` 함수
3. Jump와 Label (`return` / `break` / `continue`, `forEach`에서의 `run`, `return@run`, `return@forEach`)
4. `takeIf`와 `takeUnless`

### [20강. 코틀린의 scope function](https://github.com/litsynp/kotlin-practice/blob/main/src/main/kotlin/com/litsynp/kotlinpractice/javatokotlin/Lec20Main.kt)

1. scope function이란 무엇인가?
2. scope function의 분류 (`let`, `run`, `also`, `apply`, `with`)
3. 언제 어떤 scope function을 사용해야 할까?
4. scope function과 가독성

## REF

[Inflearn - 자바 개발자를 위한 코틀린 입문(Java to Kotlin Starter Guide)](https:www.inflearn.com/course/java-to-kotlin)
