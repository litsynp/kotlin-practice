package com.litsynp.kotlinpractice.javatokotlin

/** 11강. 코틀린에서 접근 제어를 다루는 방법 */
fun main() {
    // 1. 자바와 코틀린의 가시성 제어
    // [Java]
    // - public: 모든 곳에서 접근 가능
    // - protected: 같은 패키지 OR 하위 클래스에서만 접근 가능
    // - default: 같은 패키지에서만 접근 가능 (기본 접근 지시어)
    // - private: 선언된 클래스 내에서만 접근 가능

    // [Kotlin]
    // - public: 모든 곳에서 접근 가능 (기본 접근 지시어)
    // - protected: 선언된 클래스 OR 하위 클래스에서만 접근 가능
    //      - 코틀린에서는 패키지를 namespace에서만 사용하고, 접근(가시성) 제어에 사용하지 않음
    //      - 파일 (최상단)에는 사용 불가능
    // - internal: 같은 모듈에서만 접근 가능
    //      - default는 삭제됨
    //      - 모듈: 한번에 컴파일되는 Kotlin 코드 (IDEA 모듈, Maven, Gradle, ... 컴파일 파일 집합)
    // - private: 선언된 클래스 내에서만 접근 가능
    //      - 같은 파일에서만 접근 가능

    // 2. 코틀린 파일의 접근 제어
    // 코틀린은 .kt 파일에 변수, 함수, 클래스 여러 개를 바로 만들 수 있다.
    // [Kotlin 파일]
    // - public: 어디서나 접근할 수 있다. (기본 접근 지시어)
    // - protected: 파일 (최상단)에는 사용 불가능
    // - internal: 같은 모듈에서만 접근 가능
    // - private: 같은 파일에서만 접근 가능

    // 3. 다양한 구성요소의 접근 제어
    // 3.1. 다양한 구성요소의 접근 제어 - 클래스 안의 멤버
    // [Kotlin 클래스]
    // - public: 모든 곳에서 접근 가능
    // - protected: 선언된 클래스 또는 하위 클래스에서만 접근 가능
    // - internal: 같은 모듈에서만 접근 가능
    // - private: 선언된 클래스 내에서만 접근 가능

    // 3.2. 다양한 구성요소의 접근 제어 - 생성자
    // - 단, 생성자에 접근 지시어를 붙이려면, constructor를 붙여줘야 한다 (원래는 생략 가능)
    // - Java에서 유틸성 코드를 만들 때, abstract class + private constructor 이용해 인스턴스화를 막았다
    //  - 코틀린에서도 유사하게 할 수는 있다. 하지만,
    //    파일 최상단에 바로 유틸 함수를 작성하면 더 편하다!

    // 3.3. 다양한 구성요소의 접근 제어 - 프로퍼티
    // 프로퍼티도 가시성 범위는 동일하다. 단, 프로퍼티의 가시성을 제어하는 방법으로는...
    // 1. val, var 앞에 internal, public 등을 붙여서 getter setter 한번에 설정
    // 2. custom getter, setter의 get, set 앞에 가시성 부여

    // 4. 자바와 코틀린을 함께 사용할 경우 주의할 점

    // internal은 바이트 코드 상 public이 된다
    // => 때문에 Java 코드에서는 Kotlin 모듈의 internal 코드를 가져올 수 있다
    // => e.g., 상위 모듈이 Java로, 하위 모듈이 Kotlin이라면, 하위 모듈의 internal 함수, 필드 등을 상위 모듈에서 가져올 수 있음

    // Kotlin의 protected와 Java의 protected는 다르다
    // => Java는 같은 패키지의 Kotlin protected 멤버에 접근할 수 있다
}

private val a = 3
private fun add(a: Int, b: Int): Int {
    return a + b
}

private class Lec11Cat private constructor()

// StringUtils.kt (without private)
// 이렇게 구현하면 final class 안에 static 함수가 생기게 된다
private fun isDirectoryPath(path: String): Boolean {
    return path.endsWith("/")
}

private class Car(
    internal val name: String,
    private var owner: String,
    _price: Int
) {
    var price = _price
        private set
    // public set
}
