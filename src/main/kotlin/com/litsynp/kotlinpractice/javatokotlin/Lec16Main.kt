package com.litsynp.kotlinpractice.javatokotlin

/** 16강. 코틀린에서 다양한 함수를 다루는 방법 */
fun main() {
    // 1. 확장함수 (Extension function)
    // -
    // 배경:
    // 코틀린은 자바와 100% 호환을 목표로 하고 있다.
    // 기존 Java 코드 위에 자연스럽게 코틀린 코드를 추가할 수는 없을까? 고민하게 됨.
    // Java로 만들어진 라이브러리를 유지보수, 확장할 때 Kotlin 코드를 덧붙이고 싶다는 니즈가 생기게 됨.
    // => 어떤 클래스 안에 있는 메소드처럼 호출할 수 있지만, 함수는 밖에 만들 수 있게 하자.
    //    함수 코드 자체는 클래스 밖에 있는데, 마치 클래스 안의 멤버 함수처럼 호출하는 것
    // "확장 함수"
    val a = "abcdef"
    println(a.lastChar())

    // -
    // 확장함수가 public이고 확장함수에서 수신객체클래스의 private 함수를 가져오면 캡슐화가 깨지는 건 아닌가?
    // => 애초에 확장함수는 클래스에 있는 private, protected 멤버를 가져올 수 없다!

    // -
    // 멤버함수와 확장함수의 시그니처가 같다면?
    // => 멤버함수가 우선적으로 호출된다.
    // 확장함수를 먼저 만들었는데 다른 기능의 똑같은 멤버함수가 생기면 오류가 발생할 수 있다!
    // 주의할 것!

    // -
    // 확장함수가 오버라이드 된다면? (부모 클래스와 상속 클래스에서 확장함수를 만든다면?)
    val train: Train = Train()
    train.isExpensive() // Train의 확장함수

    val srt1: Train = Srt()
    srt1.isExpensive() // Train의 확장함수

    val srt2: Srt = Srt()
    srt2.isExpensive() // Srt의 확장함수

    val srt3: Train = srt2
    srt3.isExpensive() // Train의 확장함수

    // => Train으로 선언하면 Train의, Srt으로 선언하면 Srt의 확장함수가 불린다
    // 해당 변수의 현재 타입, 즉 정적인 타입에 의해 어떤 확장함수가 호출될지 결정된다!

    // -
    // Java에서는 Kotlin 확장함수를 어떻게 사용하는가?
    // Java에서는 정적 메소드를 부르는 것처럼 사용 가능하다.
    // e.g., Lec16Main.lastChar("ABC");

    // -
    // 확장함수라는 개념은 확장프로퍼티와도 연결된다.
    // 확장 프로퍼티의 원리는 확장함수 + custom getter와 동일하다!
    println("ABC".lastChar)


    // 2. infix 함수 (중위 함수)
    // 함수를 호출하는 새로운 방법
    // downTo, step도 함수이다 (중위 호출 함수)
    // 변수.함수이름(argument) 대신 변수 함수이름 argument (각각 하나씩인 경우에만)

    // 일반 함수
    3.add(4)

    // infix 함수 (중위함수)
    3 add2 4 // 약간 dsl 느낌이 난다!
    // infix는 멤버 함수에도 붙일 수 있다.
    3.add2(4)


    // 3. inline 함수
    // 함수가 호출되는 대신, 함수를 호출한 지점에 함수 본문을 그대로 복사하고 싶은 경우 사용
    3.add4(3) // 함수가 호출되는 게 아니라 함수의 내용이 그대로 들어간다

    // -
    // 함수를 파라미터로 전달할 때의 오버헤드를 줄일 수 있다.
    // 하지만 inline 함수의 사용은 성능 측정과 함께 신중하게 사용돼야 한다!

    // -
    // 코틀린은 최적화가 되어있기 때문에 코틀린 함수에는 적절하게 inline이 붙어있다.

    // 4. 지역함수
    // 함수 안에 함수를 선언할 수 있다. (중복을 줄일 수 있다)
    // => 함수로 추출하면 좋을 것 같은데, 이 함수를 지금 함수 내에서만 사용하고 싶을 때 사용

    // BUT, depth가 깊어지기도 하고, 코드가 그렇게 깔끔하지는 않다. (구조를 바꾸는 것이 좋을 수 있다)
    // => 지역 함수를 잘 쓰지 않을 수 있다.
}

// 확장 함수
// String. 처럼 확장하려는 클래스를 붙여준다 (수신 객체 타입)
fun String.lastChar(): Char {
    // this(수신 객체)를 통해 인스턴스 접근
    return this[length - 1]
}

// 확장 프로퍼티
val String.lastChar: Char
    get() = this[this.length - 1]

open class Train(
    val name: String = "새마을기차",
    val price: Int = 5_000,
)

fun Train.isExpensive(): Boolean {
    println("Train의 확장함수")
    return this.price >= 10000
}

class Srt : Train("SRT", 40_000)

fun Srt.isExpensive(): Boolean {
    println("Srt의 확장함수")
    return this.price >= 10000
}

fun Int.add(other: Int): Int {
    return this + other
}

infix fun Int.add2(other: Int): Int {
    return this + other
}

infix fun Int.add3(other: Int): Int {
    return this + other
}

inline fun Int.add4(other: Int): Int {
    return this + other
}
