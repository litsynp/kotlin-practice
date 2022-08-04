package com.litsynp.kotlinpractice.javatokotlin

/** 4강. 코틀린에서 연산자를 다루는 방법 */
fun main() {
    // 1. 단항 연산자 / 산술 연산자
    val money1 =
        JavaMoney(
            2000
        )
    val money2 =
        JavaMoney(
            1000
        )

    // 자동으로 compareTo 호출 (직관적)
    if (money1 > money2) {
        println("Money1이 Money2보다 금액이 큽니다")
    }

    // 2. 비교 연산자와 동등성, 동일성
    // 동등성 (Equality): 두 객체의 값이 같은가?
    // 동일성 (Identity): 완전히 동일한 객체인가? 즉, 주소가 같은가?
    // * Java: 동일성에 ==, 동등성에 equals 직접 호출
    // * Kotlin: 동일성에 ===, 동등성에 == 호출 (==를 호출하면 간접적으로 equals 호출)
    val money3 =
        JavaMoney(
            1_000L
        )
    val money4 = money3
    val money5 =
        JavaMoney(
            1_000L
        )

    println(money3 == money4)  // true
    println(money3.equals(money4)) // true
    println(money3 === money5) // false

    // 3. 논리 연산자 / 코틀린에 있는 특이한 연산자
    // &&, ||, ! Java와 완전히 동일하며, 동일하게 Lazy 연산 수행

    // in / !in
    // - 컬렉션이나 범위에 포함되어 있다 / 포함되어 있지 않다
    //   e.g. println(1 in numbers)

    // a..b
    // a부터 b까지의 범위 객체를 생성한다

    // a[i]
    // a에서 특정 Index i로 값을 가져온다

    // a[i] = b
    // a의 특정 Index i에 값 b를 넣는다

    // 4. 연산자 오버로딩

    // Java way
    val money6 =
        JavaMoney(
            1000L
        )
    val money7 =
        JavaMoney(
            2000L
        )
    val money8 = money6.plus(money7);
    println(money8.amount)

    // Kotlin way
    // Kotlin에서는 객체마다 연산자를 직접 정의할 수 있다
    val money9 = Money(1000L)
    val money10 = Money(2000L)
    val money11 = money9 + money10
    println(money11.amount)
}

private data class Money(
    val amount: Long
) {
    operator fun plus(other: Money): Money {
        return Money(this.amount + other.amount)
    }
}
