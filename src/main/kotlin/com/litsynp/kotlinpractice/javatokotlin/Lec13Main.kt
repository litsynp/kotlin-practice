package com.litsynp.kotlinpractice.javatokotlin

/** 13강. 코틀린에서 중첩 클래스를 다루는 방법 */
fun main() {
    // 1. 중첩 클래스의 종류

    // -
    // Java
    // 어딘가에 소속되어 있는 클래스는 여러 종류 존재
    // - static 사용하는 중첩 클래스: 밖의 클래스를 직접 참조할 수 없음
    // - static 사용하지 않는 중첩 클래스
    //      - 내부 클래스 (Inner Class): 밖의 클래스 직접 참조 가능
    //          Effective Java 3rd Ed. Item 24, 86
    //          - 내부 클래스는 숨겨진 외부 클래스 정보를 갖고 있어 참조를 해지하지 못하는 경우 메모리 누수가 생길 수 있고, 이를 디버깅하기 어렵다
    //          - 내부 클래스의 직렬화 형태가 명확히 정의되지 않아 직렬화에 있어 제한이 있다
    //         => Effective Java: "클래스 안에 클래스를 만들 때는 static 클래스를 사용하라"
    //         => 코틀린은 이 가이드를 충실히 따르고 있다.
    //      - 지역 클래스 (Local Class): 메소드 내부에 클래스 정의 (드문 편)
    //      - 익명 클래스 (Anonymous Class): 일회성 클래스

    // 2. 코틀린의 중첩 클래스와 내부 클래스
}

// 권장되는 클래스 방식 (static)
private class Lec13HouseRecommended(
    private val address: String,
    private val livingRoom: Lec13LivingRoom,
) {
    // 권장되는 중첩 클래스를 만들기 위해서는 그냥 class를 쓰면 된다.
    class Lec13LivingRoom(
        private val area: Double
    )
}

// 권장되지 않는 클래스 방식 (static 아닌 내부 클래스)
// 굳이 참조하고 싶다면!
private class Lec13HouseNotRecommended(
    private val address: String,
    private val livingRoom: Lec13LivingRoom,
) {
    // inner을 붙인다
    inner class Lec13LivingRoom(
        private val area: Double
    ) {
        val address: String
            // 바깥 클래스 참조를 위해 this@바깥클래스 사용
            get() = this@Lec13HouseNotRecommended.address
    }
}
