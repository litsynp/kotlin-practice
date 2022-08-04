package com.litsynp.kotlinpractice.javatokotlin

/** 18강. 코틀린에서 컬렉션을 함수형으로 다루는 방법 */
fun main() {
    // 1. 필터와 맵
    // - filter { T -> R }: 사과만 주세요!
    // - filterIndexed { idx, T -> R }: 필터에서 인덱스도 같이 주세요
    // - map { T -> R }: 사과의 가격들을(만) 알려주세요!
    // - mapIndexed { idx, T -> R }: 맵에서 인덱스도 같이 주세요
    // - mapNotNull { fruit -> fruit.nullOrValue }: 맵핑의 결과가 null이 아닌 것만 가져오고 싶다

    // 2. 다양한 컬렉션 처리 기능
    // e.g., 모든 과일이 사과인가요?
    // - all { fruit -> fruit.name == "사과" }: 조건을 모두 만족하면 true, 아니면 false
    // - none { fruit -> fruit.name == "사과" }: 조건을 모두 불만족하면 true, 아니면 false
    // e.g., 출고가 10000원 이상의 과일이 하나라도 있나요?
    // - any { fruit -> fruit.factoryPrice >= 10_000 }: 조건을 하나라도 만족하면 true, 아니면 false
    // e.g., 총 과일 개수가 몇 개 인가요?
    // - fruits.count(): List의 size와 같다 (개수를 센다)
    // e.g., 낮은 가격 순으로 보여주세요!
    // - sortedBy { fruit -> fruit.currentPrice }: (오름차순) 정렬을 한다
    // - sortedByDescending { fruit -> fruit.currentPrice }: (내림차순) 정렬을 한다
    // - distinctBy { fruit -> fruit.name } .map { fruit -> fruit.name }: 변형된 값을 기준으로 중복을 제거한다
    // e.g., 첫 번째 과일만 주세요! 마지막 과일만 주세요!
    // - fruits.first(): 첫 번째 값을 가져온다 (무조건 null이 아니어야 함, null이면 exception)
    // - fruits.firstOrNull(): 첫 번째 값 또는 null을 가져온다 (비어있다면 null)
    // - fruits.last(): 마지막 값을 가져온다 (무조건 null이 아니어야 함, null이면 exception)
    // - fruits.lastOrNull(): 마지막 값 또는 null을 가져온다 (비어있다면 null)

    // 3. List를 Map으로
    // e.g., 과일 이름 -> List<과일> 인 map이 필요해요!
    val fruits = listOf(
        Lec18Fruit(1L, "사과", 1_000L, 1_000L),
        Lec18Fruit(2L, "사과", 1_200L, 1_200L),
        Lec18Fruit(3L, "바나나", 1_500L, 1_500L),
    )
    // groupBy를 사용하면 된다 (이름을 기준으로 그룹핑)
    val map: Map<String, List<Lec18Fruit>> = fruits.groupBy { fruit -> fruit.name }

    // e.g., id -> 과일 인 map이 필요해요!
    // value에 List 아닌 단일 객체가 들어가게 된다
    // id를 통해서 뭔가를 매핑할 때, 즉 중복되지 않는 키로 map을 만들 때 사용한다
    val map2: Map<Long, Lec18Fruit> = fruits.associateBy { fruit -> fruit.id }

    // e.g., 과일 이름 -> List<출고가> 인 map이 필요해요!
    // groupBy에서 key에는 name을, value에는 price가 들어가도록 할 수 있다
    val map3: Map<String, List<Long>> =
        fruits.groupBy({ fruit -> fruit.name }, { fruit -> fruit.factoryPrice })

    // e.g., 과일 id -> 출고가 인 map이 필요해요!
    // groupBy에서 key에는 id를, value에는 price가 들어가도록 할 수 있다
    val map4: Map<Long, Long> =
        fruits.associateBy({ fruit -> fruit.id }, { fruit -> fruit.factoryPrice })
    // 람다 (함수형 파라미터)가 두개면 소괄호 안에 같이 넣어주는 것이 convention
    // map에 대해서도 filter 등 기능 가능하다
    val map5: Map<String, List<Lec18Fruit>> = fruits.groupBy { fruit -> fruit.name }
        .filter { (key, value) -> key == "사과" }


    // 4. 중첩된 컬렉션 처리
    val fruitsInList: List<List<Lec18Fruit>> = listOf(
        listOf(
            Lec18Fruit(1L, "사과", 1_000L, 1_500L),
            Lec18Fruit(2L, "사과", 1_200L, 1_500L),
            Lec18Fruit(3L, "사과", 1_200L, 1_500L),
            Lec18Fruit(4L, "사과", 1_500L, 1_500L),
        ),
        listOf(
            Lec18Fruit(5L, "바나나", 3_000L, 3_200L),
            Lec18Fruit(6L, "바나나", 3_200L, 3_200L),
            Lec18Fruit(7L, "바나나", 2_500L, 3_200L),
        ),
        listOf(
            Lec18Fruit(8L, "수박", 10_000L, 10_000L),
        ),
    )

    // e.g., 출고가와 현재가가 동일한 과일을 골라주세요!
    // Java에도 있는 flatMap을 쓸 수 있다 (List<List<>> -> List<>)
    val samePriceFruits = fruitsInList.flatMap { list ->
        list.filter { fruit -> fruit.factoryPrice == fruit.currentPrice }
    } // 람다가 중첩되어 있다

    // => 리팩토링 가능
    // 확인 로직을 도메인에 넣어둔다 (isSamePrice)
    // 확장 함수를 추가한다 List<Fruit>.samePriceFilter
    // flatMap에서는 확장함수만 사용해 하나의 람다함수만 사용하는 것으로 변경
    val samePriceFruits2 = fruitsInList.flatMap { list -> list.samePriceFilter }

    // e.g., List<List<Fruit>> 를 List<Fruit>으로 바꿔주세요!
    // 중첩된 컬렉션이 중첩 해제가 되어 평탄화된다
    val flatFruits = fruitsInList.flatten()
    println(flatFruits)

    // 외우려하지 말고 그때 그때 필요한 게 있다면 검색하면 될 것
}

data class Lec18Fruit(
    val id: Long,
    val name: String,
    val factoryPrice: Long,
    val currentPrice: Long,
) {
    val isSamePrice: Boolean
        get() = factoryPrice == currentPrice
}

private fun filterFruits(
    fruits: List<Lec18Fruit>,
    filter: (Lec18Fruit) -> Boolean
): List<Lec18Fruit> {
    return fruits.filter(filter)
}

val List<Lec18Fruit>.samePriceFilter: List<Lec18Fruit>
    get() = this.filter(Lec18Fruit::isSamePrice)
