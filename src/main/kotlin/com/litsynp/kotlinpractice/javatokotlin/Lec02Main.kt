package com.litsynp.kotlinpractice.javatokotlin

/** 2강. 코틀린에서 null을 다루는 방법 */
fun main() {
    // 1. Kotlin에서의 null 체크
    // 코틀린에서는 null이 가능한 타입을 완전히 다르게 취급

    // 2. Safe call(?.)과 Elvis(?:) 연산자
    // Safe call (?.): null이 아니면 실행하고, null이면 실행하지 않는다 (그대로 null)
    // Elvis 연산자 (?:) : 앞의 연산 결과가 null이면 뒤의 값을 사용

    // 3. null 아님 단언 (!!)
    // nullable type이지만, 아무리 생각해도 null이 될 수 없는 경우

    // 4. 플랫폼 타입
    // 코틀린이 null 관련 정보를 알 수 없는 타입 (Runtime 시 Exception 발생할 수 있다)
    // Kotlin에서 Java 코드를 가져다 사용할 때 발생
    // @Nullable (javax.annotation, org.jetbrains.annotation, android.support.annotation 등) 사용해서
    // Null 반환할 수 있다고 명시해줘야 함
    // => Java 코드 읽으며 널 가능성 확인 / Kotlin으로 Wrapping
}

fun startsWithA1(str: String?): Boolean {
//    if (str == null) {
//        throw IllegalArgumentException("null이 들어왔습니다")
//    }
//    return str.startsWith("A")
    return str?.startsWith("A") ?: throw IllegalArgumentException("null이 들어왔습니다")
}

fun startsWithA2(str: String?): Boolean? {
//    if (str == null) {
//        return null
//    }
//    return str.startsWith("A")
    return str?.startsWith("A")
}

fun startsWithA3(str: String?): Boolean {
//    if (str == null) {
//        return false
//    }
//    return str.startsWith("A")
    return str?.startsWith("A") ?: false
}

fun calculate(number: Long?): Long {
    // - Elvis 연산은 early return에도 사용될 수 있다
//    if (number == null) {
//        return 0
//    }
    var n = number ?: return 0

    // 다음 로직
    return n + 1
}

fun startsWithA4(str: String?): Boolean {
    // 만약 null이 들어오면 NPE
    return str!!.startsWith("A")
}
