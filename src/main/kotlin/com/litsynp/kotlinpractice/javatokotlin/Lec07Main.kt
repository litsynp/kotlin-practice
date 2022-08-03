package com.litsynp.kotlinpractice.javatokotlin

import java.io.BufferedReader
import java.io.File
import java.io.FileReader

/** 7강. 코틀린에서 예외를 다루는 방법 */
fun main() {
    // 1. try-catch-finally 구문

    // 2. Checked Exception, Unchecked Exception

    // 3. 코틀린의 try-with-resources (JDK 7+)
}

fun parseIntOrThrow(str: String): Int {
    try {
        // 1. 기본타입 형변환엔 toType() 사용
        return str.toInt()
    } catch (e: NumberFormatException) { // 타입이 뒤에 위치
        // 2. new 없음
        // 3. formatting 간결
        throw IllegalArgumentException("주어진 ${str}은 숫자가 아닙니다")
    }
}

fun parseIntOrThrowV2(str: String): Int? {
    // 코틀린에서 if-else처럼 try-catch는 expression
    // 최종 결과물을 한 번에 return 가능
    return try {
        str.toInt()
    } catch (e: NumberFormatException) {
        null
    }
}

fun readFile() {
    // Java에서는 throws IOException가 강제됨
    // Kotlin에서는 Checked Exception, Unchecked Exception 구분 없음
    val currentFile = File(".")
    val file = File(currentFile.absolutePath + "/a.txt")
    val reader = BufferedReader(FileReader(file))
    println(reader.readLine())
    reader.close()
}

fun readFileV2(path: String) {
    // Kotlin에서는 try-with-resources 구문 없음
    // Kotlin 특성을 이용해 use로 해결 가능
    BufferedReader(FileReader(path)).use { reader ->
        println(reader.readLine())
    }
}
