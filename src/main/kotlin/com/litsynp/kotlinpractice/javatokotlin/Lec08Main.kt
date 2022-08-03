package com.litsynp.kotlinpractice.javatokotlin

/** 8강. 코틀린에서 함수를 다루는 방법 */
fun main() {
    // 1. 함수 선언 문법
    // 함수는 클래스 안에 있을 수도, 파일 최상단에 있을 수도 있다.
    // 또한, 한 파일 안에 여러 함수가 있을 수 있다.

    // 2. default parameter
    repeat("Hello World")
    repeat("Hello World", 3, true)

    // 3. named argument (parameter)
    // - 매개변수 이름을 직접 지정해서 두 번쨰 파라미터은 기본값 사용하도록 스킵할 수 있음
    repeat("Hello World", useNewLine = false)

    // - 빌더를 만들 필요 없이 빌더의 장점 이용 가능
    printNameAndGender(gender = "MALE", name = "litsynp")

    // Kotlin에서 Java 함수를 이용할 경우에는 named argument 사용할 수 없음!
    // JavaLec08Main.repeat(str = "Hello World")

    // 4. 같은 타입의 여러 파라미터 받기 (가변인자)
    // Java에서는 타입... 으로 가변인자 사용 가능, 배열이나 (,)를 통해 넣어줄 수 있었음
    // - Kotlin에서는 vararg로 사용
    // - Kotlin에서는 배열을 넣어줄 땐 spread 연산자 (*)를 같이 넣어줘야 한다 (,로 쓰듯이 변환)
    val array = arrayOf("A", "B", "C")
    printAll(*array)
}

// - public은 생략 가능
// - 하나의 결과값이라면 "중괄호 + return"을 "="으로 대체 가능
//   - (중괄호를 생략하는 경우에만 가능) if else return 값 같으니 반환 타입 ": Int" 생략 가능
fun max(a: Int, b: Int) = if (a > b) a else b

fun repeat(
    str: String,
    num: Int = 3,
    useNewLine: Boolean = true
) {
    // 오버로딩 필요 없이, 기본값을 지원
    for (i in 1..num) {
        if (useNewLine) {
            println(str)
        } else {
            print(str)
        }
    }
}

fun printNameAndGender(name: String, gender: String) {
    println(name)
    println(gender)
}

fun printAll(vararg strings: String) {
    for (str in strings) {
        println(str)
    }
}
