package com.litsynp.kotlinpractice.javatokotlin

/** 3강. 코틀린에서 Type을 다루는 방법 */
fun main() {
    // 1. 기본 타입
    val number1: Int? = 3
    val number2: Long = number1?.toLong() ?: 0L

    // 2. 타입 캐스팅
    printNameIfPerson(
        JavaPerson(
            "hi"
        )
    )

    // 3. Kotlin의 3가지 특이한 타입
    // Object->Any, void->Unit, Nothing

    // 4. String interpolation, String indexing
    val person =
        JavaPerson("최태현")
    println("사람의 이름은 ${person.name}입니다.")
    val hi = """
        hihihi
        ${person.name}
    """
    println(hi.trimIndent())

    val str = "ABCDE"
    println(str[2])
}

fun printNameIfPerson(obj: Any?) {
    val person = obj as? JavaPerson
    println(person?.name)
}
