package com.litsynp.kotlinpractice.javatokotlin

/** 6강. 코틀린에서 반복문을 다루는 방법 */
fun main() {
    // 1. for-each문 (향상된 for문)
    val numbers = listOf(1L, 2L, 3L)
    // Java와 동일하게 iterable이 구현된 타입이면 모두 가능
    // 단, 콜론 (:) 대신 in을 사용
    for (number in numbers) {
        println(number)
    }

    // 2. 전통적인 for문 -- .. 연산자
    // .. 연산자: 범위를 만들어 내는 연산자 (1..3: 1부터 3의 범위)
    // 3. Progression과 Range
    // 범위는 (Int)Range라는 클래스가 있는데, Progresssion (등차수열)을 상속받고 있음
    // => 등차수열을 만들어주는 코드
    for (i in 1..3) {
        println(i)
    }

    // 내려가는 경우 -- downTo
    // 공차 -1
    // downTo는 (중위) 함수
    // - "변수.함수이름(argument)" 대신 "변수 함수이름 argument"
    for (i in 3 downTo 1) {
        println(i)
    }

    // 2칸씩 올라가는 경우 -- step
    // 공차 2
    // step도 (중위) 함수 (.step(2))
    for (i in 1..5 step 2) {
        println(i)
    }

    // 4. while 문
    // Java와 완전히 동일하다
    // do-while도 동일
    var i = 1
    while (i <= 3) {
        println(i)
        i++
    }
}
