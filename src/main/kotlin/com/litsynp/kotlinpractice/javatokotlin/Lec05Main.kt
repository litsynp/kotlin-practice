package com.litsynp.kotlinpractice.javatokotlin

/** 5강. 코틀린에서 제어문을 다루는 방법 */
fun main() {
    // 1. if문

    // 2. Expression과 Statement

    // 3. switch와 when
    // when은 Enum class, Sealed class와 함께 사용할 경우 더 진가를 발휘한다
}

fun validateScoreIsNotNegative(score: Int) {
    // 자바와 큰 차이가 없다
    if (score < 0) {
        throw IllegalArgumentException("${score}은 0보다 작을 수 없습니다")
    }
}

fun getPassOrFail(score: Int): String {
    // 자바에서 if-else는 Statement (프로그램의 문장, 하나의 값으로 도출되지 않음)
    // 코틀린에서는 if-else는 Expression (하나의 값으로 도출되는 문장) (Statement가 Expression을 포함)
    // 따라서 코틀린에는 3항 연산자가 없다
//    if (score >= 50) {
//        return "P"
//    } else {
//        return "F"
//    }
    return if (score >= 50) {
        "P"
    } else {
        "F"
    }
}

fun getGrade(score: Int): String {
    return if (score >= 90) {
        "A"
    } else if (score >= 80) {
        "B"
    } else if (score >= 70) {
        "C"
    } else {
        "F"
    }
}

fun validateScore(score: Int) {
    // TIP: 자바의 범위는 0 <= score && score <= 10으로 확인해야 했지만
    // in과 a..b를 이용해 범위 조건을 구현할 수 있다
    if (score !in 0..100) {
        throw IllegalArgumentException("${score}은 0과 100 사이여야 합니다")
    }
}

fun getGradeWithSwitch(score: Int): String {
    // 코틀린에서는 switch 문이 사라졌다
    // 대신 when을 쓸 수 있다 (expression)
    // switch 대신 when (값)
    return when (score) {
        // case보다 더 강력 -> 조건부에 범위, 기타 조건으로 분기할 수 있음
        // 조건부에는 어떤 expression이든 가능하다 (is Type)
        in 90..99 -> "A"  // case 대신 바로 그 경우를 쓰고 ->로 분기 표시
        in 80..89 -> "B"
        in 70..79 -> "C"
        else -> "D" // default 대신 else
    }
}

fun startsWithA(obj: Any): Boolean {
    return when (obj) {
        is String -> obj.startsWith("A") // is String을 통과해 smart casting 적용
        else -> false
    }
}

fun judgeNumber(number: Int) {
    return when (number) {
        // number == 1 || number == 0 || number == -1
        1, 0, -1 -> println("어디서 많이 본 숫자입니다")
        else -> println("1, 0, -1이 아닙니다")
    }
}

fun judgeNumber2(number: Int) {
    return when { // when에 값이 없을 수도 있다
        number == 0 -> println("주어진 숫자는 0입니다")
        number % 2 == 0 -> println("주어진 숫자는 짝수입니다")
        else -> println("주어진 숫자는 홀수입니다")
    }
}
