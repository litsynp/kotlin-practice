package com.litsynp.kotlinpractice.javatokotlin

/** 14강. 코틀린에서 다양한 클래스를 다루는 방법 */
fun main() {
    // 1. Data class

    // -
    // 계층 간의 데이터를 전달하기 위한 DTO (Data Transfer Object)
    // ...는 보통 데이터(필드), 생성자, getter, equals, hashCode, toString 등 추가 메소드 필요
    // 너무 길다. 보통 Lombok 등 활용
    // 클래스 장황해지고, 어노테이션 추가 등 해야되는 단점이 있다

    // -
    // Data Class는 equals, hashCode, toString을 생성해준다.
    val dto1 = Lec14PersonDto("litsynp", 20)
    println(dto1)

    // -
    // named argument를 활용하면 빌더 패턴을 쓰는 것 같은 효과도 있다!
    val dto2 = Lec14PersonDto(name = "litsynp", age = 20)

    // -
    // Java에서는 JDK16부터 Kotlin의 data class 같은 record class 도입


    // 2. Enum Class
    // -
    // Enum의 특징
    // - 추가적인 클래스를 상속받을 수 없다.
    // - 인터페이스는 구현할 수 있으며, 각 코드가 싱글톤이다.

    // -
    // Kotlin에서도 다를 게 없다 (enum class를 사용)
    // + `when`은 Enum class와 함께 사용할 경우 더 진가를 발휘한다!
    // Java에서는 else 처리를 꼭 해줘야 함 (해당 상황이 일어날 일이 없더라도)


    // 3. Sealed Class, Sealed Interface
    // -
    // sealed: 봉인을 한
    // 상속이 가능하도록 추상 클래스를 만드려고 하는데, 외부에서는 이 클래스를 상속받지 않았으면 좋겠다!
    // => 하위 클래스를 봉인하자!

    // -
    // Sealed Class (abstract와 비슷하지만 차이 존재!)
    // - 컴파일 타임 때 하위 클래스의 타입을 모두 기억한다.
    //   즉, 런타임 때 클래스 타입이 추가될 수 없다. (Enum과 비슷하다)
    // - 하위 클래스는 같은 패키지에 있어야 한다.
    // - Enum과 다른 점
    //      - 클래스를 상속받을 수 있다.
    //      - 하위 클래스는 멀티 인스턴스가 가능하다. v.s. Enum class의 Singleton
    //
    // 서버 개발: 추상화가 필요한 Entity, DTO는 sealed class를 사용할 수 있다!

    // -
    // JDK17에도 Sealed class가 추가되었다.
}

// Data Class: equals, hashCode, toString 생성
data class Lec14PersonDto(
    val name: String,
    val age: Int,
)

enum class Lec14Country(
    private val code: String,
) {
    KOREA("KO"),
    AMERICA("KO"),
    ;
}

// 어차피 country는 KOREA 아니면 AMERICA (null은 없음)
fun handleCountry(country: Lec14Country) {
    // enum 추가하면 WARNING도 줌
    when (country) {
        Lec14Country.KOREA -> TODO()
        Lec14Country.AMERICA -> TODO()
    }
}

sealed class HyundaiCar(
    val name: String,
    val price: Long,
)

class Avante : HyundaiCar("아반떼", 1_000L)
class Sonata : HyundaiCar("소나타", 2_000L)
class Grandeur : HyundaiCar("그렌저", 3_000L)

private fun handleCar(car: HyundaiCar) {
    when (car) {
        // is Type으로 분기
        is Avante -> TODO()
        is Sonata -> TODO()
        is Grandeur -> TODO()
        // Enum class 처럼 else 필요 없음
        // Sealed 클래스 하위 클래스나 구현체 추가되면 알려줌
    }
}
