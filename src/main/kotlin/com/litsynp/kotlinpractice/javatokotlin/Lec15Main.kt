package com.litsynp.kotlinpractice.javatokotlin

/** 15강. 코틀린에서 배열과 컬렉션을 다루는 방법 */
fun main() {
    // 1. 배열
    // 사실 배열은 잘 사용하지 않는다.
    // Effective Java Item 28: 배열보다 리스트를 사용하라. (공변, 실체화 => 컴파일 타임엔 Not type-safe)
    val array = arrayOf(100, 20)

    // index로 접근하기 (0부터 마지막 index의 range) -- .indices
    for (i in array.indices) {
        println("${i} ${array[i]}")
    }

    // index, value 한 번에 받아오기 -- .withIndex()
    for ((idx, value) in array.withIndex()) {
        println("${idx} ${value}")
    }

    // 배열에 새로운 element 추가 -- .plus()
    println(array.plus(300).joinToString())
    println(array.joinToString())


    // 2. 코틀린에서의 Collection - List, Set, Map
    // 코틀린에서는 컬렉션을 만들어 줄 때 불변인지, 가변인지를 설정해야 한다.
    // 가변 (Mutable) 컬렉션: 컬렉션에 element를 추가, 삭제할 수 있다.
    // 불변 (Immutable) 컬렉션: 컬렉션에 element를 추가, 삭제할 수 없다.
    //      => Collection을 만들자마자 Collections.unmodifiableList() 등을 붙여준다!
    //      => 불변 컬렉션이라 하더라도, Reference Type의 element 필드은 바꿀 수 있다.

    // -
    // List
    // Java: final List<Integer> numbers = Arrays.asList(100, 200); // JDK 9부터는 List.of()도 가능
    // listOf로 불변 리스트를, emptyList<Type>로 비어있는 리스트를 만들 수 있다
    val numbers = listOf(100, 20) // 비어 있지 않다면 타입을 정할 필요가 없다
    val emptyList = emptyList<Int>() // 비어 있는 리스트를 만들 때는 타입을 명시적으로 정해줘야 한다

    printNumbers(emptyList()) // 타입 추론이 가능하다면 생략할 수 있다!

    // 하나를 가져오기
    println(numbers.get(0))
    println(numbers[0]) // Kotlin에서는 가능

    // for-each
    for (number in numbers) {
        println(number)
    }

    // 전통적 for문
    for ((idx, value) in numbers.withIndex()) {
        println("${idx} ${value}")
    }

    // 가변 리스트를 사용하고 싶다면
    val mutableList = mutableListOf(100, 200)
    mutableList.add(300)

    // 기본 구현체는 ArrayList이며, 기타 사용법은 Java와 동일하다.
    // Tip: 우선 불변 리스트를 만들고, 꼭 필요한 경우 가변 리스트로 만들자.

    // -
    // Set
    // 집합은 List와 다르게 순서가 없고, 같은 element는 하나만 존재할 수 있다.
    // 자료구조적 의미만 제외하면 모든 기능이 List와 비슷하다.
    val set = setOf(100, 20)

    // for-each
    for (number in set) {
        println(number)
    }

    // 전통적 for문
    for ((idx, value) in numbers.withIndex()) {
        println("${idx} ${value}")
    }

    // 가변 집합
    // 기본 구현체는 LinkedHashSet
    val mutableSet = mutableSetOf(100L, 200L)

    // -
    // Map
    // JDK 8까지: new HashMap<>() => map.put(X, X)
    // JDK 9부터: Map.of(X, X, X, X, ...)
    val oldMap = mutableMapOf<Int, String>()
    oldMap.put(1, "MONDAY") // 기본 방식
    oldMap[1] = "MONDAY" // 이것도 가능
    oldMap[2] = "TUESDAY"

    // to 로 맵 초기화 (중위 호출 e.g., step) -> Pair 클래스를 만들어 a, b를 생성
    val immutableMap = mapOf(1 to "MONDAY", 2 to "TUESDAY")

    // keySet() 방식 대체 -- keys
    for (key in oldMap.keys) {
        println(key)
        println(oldMap[key])
    }

    // Map.Entry 방식 대체 -- entries
    for ((key, value) in oldMap.entries) {
        println("${key} ${value}")
    }

    // 3. 컬렉션의 null 가능성, Java와 함께 사용하기

    // [컬렉션의 null 가능성]
    // -
    // List<Int?>: 리스트에는 null이 들어갈 수 있지만, 리스트는 절대 null이 아님
    // List<Int>?: 리스트에 null이 들어갈 수 없지만, 리스트는 null일 수 있음
    // List<Int?>?: 리스트에 null이 들어갈 수도 있고, 리스트가 null일 수도 있음
    // ? 위치에 null 가능성 의미가 달라지므로, 차이를 잘 이해해야 한다.

    // [Java와 함께 사용하기]
    // -
    // Java는 읽기 전용 컬렉션과 변경 가능 컬렉션을 구분하지 않는다.
    // e.g., Kotlin에서 listOf()로 만들어도, Java에서는 변경할 수 있어서 오동작할 수 있다.

    // -
    // Java는 nullable 타입과 non-nullable 타입을 구분하지 않는다.
    // e.g., Kotlin에 null이 들어갈 수 있는 리스트(List<Int>)를 만들어도
    //       Java에 가져와서 사용하면 null을 추가한다음 돌려주면
    //       Kotlin에는 null을 발견하게 되고 오류가 날 수도 있다.

    // 따라서,
    // Kotlin 쪽의 컬렉션이 Java에서 호출되면 컬렉션 내용이 변할 수 있음을 감안해야 한다.
    // (방어 로직을 짠다던가 해야한다)
    //
    // 코틀린쪽에서 Collections.unmodifiableXXX()를 활용하면 변경 자체를 막을 수는 있다!

    // -
    // 반대로,
    // Kotlin에서 Java 컬렉션을 가져다 사용할 때 플랫폼 타입을 신경써야 한다.
    // e.g., Java에서 List<Integer>을 만들었다면, Kotlin에서 List<Int?>, List<Int>?, List<Int?>?인지 알 수가 없다.

    // 따라서, Java 코드를 보고 맥락을 확인하고, Java 코드를 가져오는 지점을 wrapping해서 영향 범위를 최소화해야 한다.
}

private fun printNumbers(numbers: List<Int>) {
    for (i in numbers.indices)
        println(numbers[i])
}
