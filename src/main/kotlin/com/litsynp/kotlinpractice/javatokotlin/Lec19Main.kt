package com.litsynp.kotlinpractice.javatokotlin

/** 19강. 코틀린의 이모저모 */
fun main() {
    // 1. Type Alias와 as import
    // [Type Alias]
    // 긴 이름의 클래스 혹은 함수 타입이 있을 때 축약하거나 더 좋은 이름을 쓰고 싶을 때 사용
    // typealias 키워드 활용

    // [as import]
    // 다른 패키지의 같은 이름 함수를 동시에 가져오고 싶을 때
    // "어떤 클래스나 함수를 임포트 할 때 이름을 바꾸는 기능"
    // import com.litsynp.kotlinpractice.lec19.a.printHelloWorld as printHelloWorldA
    // import com.litsynp.kotlinpractice.lec19.b.printHelloWorld as printHelloWorldB

    // 2. 구조 분해와 componentN 함수
    // [구조 분해 (Destructuring Declarations)]
    // "복합적인 값을 분해하여 여러 변수를 한 번에 초기화하는 것"
    val person = Lec19Person("litsynp", 100)
    val (name, age) = person  // 구조 분해
    println("이름: ${name}, 나이: ${age}")
    // => 실제로 이것과 같다
    // val name = person.component1()
    // val age = person.component2()

    // => data class는 componentN 이라는 함수도 자동으로 만들어준다.
    // 구조분해 문법을 쓰면 componentN 함수를 호출하게 된다.
    val (age1, name1) = person
    println("이름: ${age1}, 나이: ${name1}") // 변수 이름이 아니라, 순서대로 읽는다.

    // data class 아닌 클래스에 대해서는 직접 componentN을 구현한다.
    val (name2, age2) = Lec19Person2("litsynp", 100)
    println("이름: ${name2}, 나이: ${age2}")

    // - for ((key, value) in map.entries) 또한 구조분해 문법이다.

    // 3. Jump와 Label
    // 코드의 흐름 제어 키워드: return / break / continue
    // return: 기본적으로 가장 가까운 enclosing function 또는 익명함수로 값이 반환된다
    // break: 가장 가까운 루프가 제거된다
    // continue: 가장 가까운 루프를 다음 step으로 보낸다

    // Java, Kotlin에서 for문 및 while문에서 break, continue 기능은 동일하다
    // 단!
    // forEach에서 주의할 점은, continue, break를 쓸 수 없다
    val numbers = listOf(1, 2, 3)
    numbers.map { number -> number + 1 }
        .forEach { number -> println(number) }

    // 그래도 break를 꼭 쓰고싶다면?
    run { // run 블록으로 감싸고
        numbers.map { number -> number + 1 }
            .forEach { number ->
                if (number == 3) {
                    return@run // return으로 해당 블록을 나가야 한다
                }
                println(number)
            }
    }

    // 그래도 continue를 꼭 쓰고싶다면?
    numbers.map { number -> number + 1 }
        .forEach { number ->
            if (number == 3) {
                return@forEach // forEach으로 다음 반복으로 넘어갈 것
            }
            println(number)
        }

    // break, continue를 사용할 땐 가급적 익숙한 for문 사용이 추천된다!


    // -
    // 코틀린에는 라벨이라는 기능이 있다
    // 특정 expression에 라벨이름@ 을 붙여 하나의 라벨로 간주하고, break, continue, return 등을 사용하는 기능이다

    loop@ for (i in 1..100) {
        for (j in 1..100) {
            if (j == 2) {
                break@loop // 인접한 for문이 아닌, 라벨 표기된 for문을 빠져나간다
            }
            print("${i} ${j}")
        }
    }

    // 라벨을 사용한 Jump는 사용하지 않는 것을 강력 추천한다.
    // 복잡해지고, 유지보수가 어려워진다.

    // 4. TakeIf와 TakeUnless
    fun getNumberOrNull(number: Int): Int? {
        return if (number <= 0) {
            null
        } else {
            number
        }
    }

    // takeIf를 사용하면 한 줄로 줄일 수 있다
    fun getNumberOrNull2(number: Int): Int? {
        return number.takeIf { it > 0 }
    }
    // 주어진 조건을 만족하면 그 값이 결과로, 그렇지 않으면 null이 반환된다

    // 반대로, takeUnless는
    fun getNumberOrNull3(number: Int): Int? {
        return number.takeUnless { it > 0 }
    }
    // 주어진 조건을 만족하지 않으면 그 값이 결과로, 그렇지 않으면 null이 반환된다
}

data class Lec19Fruit(
    val id: Long,
    val name: String,
    val factoryPrice: Long,
    val currentPrice: Long,
)

// Type Alias로 타입 축약
typealias FruitFilter = (Lec19Fruit) -> Boolean

private fun filterFruits(
    fruits: List<Lec19Fruit>,
    filter: FruitFilter
): List<Lec19Fruit> {
    return fruits.filter(filter)
}

data class UltraSuperGuardianTribe(
    val name: String
)

// 이름이 긴 클래스를 컬렉션에 사용할 때도 간단히 줄일 수 있다.
typealias USGTMap = Map<String, UltraSuperGuardianTribe>

data class Lec19Person(val name: String, val age: Int)

// componentN 직접 구현하기
class Lec19Person2(val name: String, val age: Int) {
    // componentN 함수는 연산자의 속성을 가지므로, operator 선언을 해줘야 한다.
    operator fun component1(): String {
        return this.name
    }

    operator fun component2(): Int {
        return this.age
    }
}
