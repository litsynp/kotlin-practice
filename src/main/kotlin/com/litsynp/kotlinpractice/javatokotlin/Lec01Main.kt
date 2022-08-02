package com.litsynp.kotlinpractice.javatokotlin

/** 1강. 코틀린에서 변수를 다루는 방법 */
fun main() {
    // 1. 변수 선언 키워드 - var vs. val
    // - 모든 변수에 수정 가능 여부(var / val)를 명시
    var number1 = 10L
    number1 = 5L

    val number2 = 10L
    // number2 = 5L // 컴파일 에러: Val cannot be reassigned

    // - 타입을 명시적으로 작성할 수도 있다 (타입 추론)
    var number3: Long = 10L

    // 초기값 지정 여부
    var number4: Long
    // println(number4) // 컴파일 에러: Variable 'number4' must be initialized

    val number5: Long
    number5 = 10  // 초기화되지 않았다면 최초 한번까지는 대입 허용
    println(number5)

    // val 컬렉션에서는 element를 추가할 수 있다 (Java final List여도 추가 가능한 것처럼)

    // -> 모든 변수는 우선 val, 꼭 필요한 경우 var로 변경!

    // 2. Kotlin에서의 Primitive Type
    var number6 = 10L
    var number7 = 1_000L
    // Java에서는 primitive / reference type 구분 존재
    // Kotlin은 구분 없음
    // 연산을 하게 될 경우에는 코틀린이 알아서 상황에 따라 내부적으로 primitive로 바꿔준다

    // 3. Kotlin에서의 nullable 변수
    // 그런데 Java reference type은 null도 들어갈 수 있음
    // Kotlin에서는 타입이 다른 것으로 간주
    var number8 = 10L
    var number9: Long? = null

    // 4. Kotlin에서의 객체 인스턴스화
    // 코틀린에서는 객체 인스터화에 new를 붙이지 않아야 한다
    val person =
        JavaPerson("Person1")
}
