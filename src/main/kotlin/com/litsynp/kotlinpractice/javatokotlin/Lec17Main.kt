package com.litsynp.kotlinpractice.javatokotlin

import java.io.BufferedReader
import java.io.FileReader

/** 17강. 코틀린에서 람다를 다루는 방법 */
fun main() {
    // 1. Java에서 람다를 다루기 위한 노력
    // 메소드 추가, 파라미터 추가만으론 어려우니 인터페이스 + 익명함수 지원
    // 익명 클래스는 복잡함
    // 다양한 필터가 필요할 수 있음 (과일 간 무게 비교, N개 과일 한 번에 비교, ...)

    // => JDK8부터 람다 (이름이 없는 함수 등장)
    // 또한 FruitFilter와 같은 인터페이스 Predicate, Consumer 등 많이 만들어 두었다

    // 그리고 for, if를 간결하게 하기 위해 스트림 등장 (병렬 처리에도 강점이 생김)
    // 메소드 레퍼런스 (Fruit::isApple)를 활용할 수도 있게 됨

    // 이렇게, "메소드 자체를 직접 넘겨주는 것 처럼" 쓸 수 있었다
    // 함수를 넘겨주는 것 "처럼"인 이유는, 실제 함수를 넘기는 게 아니라 인터페이스, 즉 미리 만들어 둔 Predicate 인터페이스이다

    // => "자바에서는 함수는 2급 시민으로 간주한다."
    // 자바에서 함수는 변수에 할당되거나, 파라미터로 전달할 수 없다.


    // 2. 코틀린에서의 람다
    // 코틀린에서는 자바와 근본적으로 다른 것이 한 가지 있다

    // => "Kotlin에서는 함수가 1급 시민이다."
    // - 코틀린에서는 함수 그 자체가 값이 될 수 있다
    // - 변수에 할당할 수도, 파라미터로 넘길 수 있다

    val fruits = listOf(
        Lec17Fruit("사과", 1_000),
        Lec17Fruit("사과", 1_200),
        Lec17Fruit("사과", 1_200),
        Lec17Fruit("사과", 1_500),
        Lec17Fruit("바나나", 3_000),
        Lec17Fruit("바나나", 3_200),
        Lec17Fruit("바나나", 25_000),
        Lec17Fruit("수박", 10_000),
    )

    // [람다 생성]
    // 함수의 타입: (파라미터 타입...) -> 반환 타입

    // 1번 방법
    // 바로 파라미터, 리턴 타입, 본문이 들어간다
    val isApple: (Lec17Fruit) -> Boolean = fun(fruit: Lec17Fruit): Boolean {
        return fruit.name == "사과"
    }

    // 2번 방법
    // 더 간결해서 함수에 직접 넣을 땐 이 방법 주로 사용
    val isApple2: (Lec17Fruit) -> Boolean = { fruit: Lec17Fruit -> fruit.name == "사과" }

    // [함수 호출]
    // 1번 방법
    isApple(fruits[0])

    // 2번 방법 -- invoke
    isApple.invoke(fruits[0])

    // 함수 자체를 파라미터에 넣는다
    filterFruits(fruits, isApple)

    // 바로 넣을 수도 있다
    filterFruits(fruits, { fruit: Lec17Fruit -> fruit.name == "사과" })

    // 함수에서 받는 함수 파라미터가 마지막에 위치해 있다면, 소괄호 밖으로 중괄호를 뺄 수 있다 (덜 어색)
    // Filter의 파라미터 타입이 (Fruit) -> Boolean 으로 추론 가능 => Fruit 생략 가능
    // fruit의 이름도 바꿀 수 있다 (e.g., a)
    filterFruits(fruits) { fruit -> fruit.name == "사과" }
    // => 하지만, 아래 it을 사용하게 되면 이 it이 어떤 데이터인지 모르니 이쪽이 추천됨

    // 파라미터가 한 개인 경우 그냥 it 이라고 해서 생략 가능 (it == fruit)
    filterFruits(fruits) { it.name == "사과" }

    // 여러 줄을 작성할 수도 있다.
    filterFruits(fruits) { fruit ->
        println("사과만 받는다...!")
        fruit.name == "사과"  // 마지막 줄의 결과가 람다의 반환값이 된다!
    }


    // 3. Closure
    // -
    // 자바에서는 람다를 쓸 때 사용할 수 있는 변수에 제약이 있다
    // final이거나 실질적으로 final인 변수만 내부적으로 사용할 수 있다

    // -
    // 코틀린에서는 아무 문제 없이 동작한다! (람다 함수 밖의 변수 접근 가능)
    // 코틀린에서는 람다가 시작하는 지점에 참조하고 있는 변수들을 모두 포획하여 그 정보를 갖고 있다
    // 이렇게 해야만 람다를 진정한 일급 시민으로 간주할 수 있다.
    // 이 데이터 구조를 Closure라고 부른다.
    // Closure: 람다가 시작하는 지점에 참조하고 있는 변수들을 모두 포획한 데이터 구조

    // 즉, 코틀린에서는 값이 바뀌는 가변 변수 역시 람다 함수에서 사용할 수 있다

    // 4. 다시 try with resources
    // 코틀린에서는 try with resources 없어지고 use를 사용한다
    // -
    // use "함수"
    // Closable을 구현한 T에 대해 use를 적용할 수 있다
    // block 이라는 함수를 받는다 (T) -> R
    // 즉, 람다를 받도록 만들어진 함수이다
    fun readFile(path: String) {
        // 실제로 use는 람다를 전달하고 있다.
        BufferedReader(FileReader(path)).use { reader ->
            println(reader.readLine())
        }
    }
}

class Lec17Fruit(
    val name: String,
    val price: Int
)

private fun filterFruits(
    fruits: List<Lec17Fruit>,
    filter: (Lec17Fruit) -> Boolean // Predicate처럼 인터페이스가 아닌, 진짜 함수를 전달 받음
): List<Lec17Fruit> {
    // 이하 코드도 선언식으로 바꿀 수 있다!
    val results = mutableListOf<Lec17Fruit>()

    for (fruit in fruits) {
        if (filter(fruit)) {
            results.add(fruit)
        }
    }
    return results
}
